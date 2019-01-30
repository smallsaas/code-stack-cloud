package com.jfeat.am.module.meta.services.domain.service.impl;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.meta.constant.StatusConstant;
import com.jfeat.am.module.meta.services.domain.dao.QueryMetaStatusMachineDao;
import com.jfeat.am.module.meta.services.domain.model.BulkChangStatusModel;
import com.jfeat.am.module.meta.services.domain.model.ChangeStatusModel;
import com.jfeat.am.module.meta.services.domain.model.EntityCurrentStatus;
import com.jfeat.am.module.meta.services.domain.service.MetaStatusMachineService;
import com.jfeat.am.module.meta.services.domain.service.MetaWorkflowLiteActivityService;
import com.jfeat.am.module.meta.services.domain.utils.MetaUtils;
import com.jfeat.am.module.meta.services.gen.crud.service.impl.CRUDMetaStatusMachineServiceImpl;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaStatusMachine;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaWorkflowLiteActivity;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.BulkMessage;
import com.jfeat.crud.base.tips.BulkResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("metaStatusMachineService")
public class MetaStatusMachineServiceImpl extends CRUDMetaStatusMachineServiceImpl
        implements MetaStatusMachineService {

    @Resource
    private MetaWorkflowLiteActivityService metaWorkflowLiteActivityService;

    @Resource
    private QueryMetaStatusMachineDao queryMetaStatusMachineDao;

    @Override
    public List<MetaStatusMachine> findMetaStatusMachine(MetaStatusMachine queryEntity) {
        return queryMetaStatusMachineDao.findMetaStatusMachine(queryEntity);
    }

    @Override
    public List<MetaStatusMachine> getLinkedEntityStatusList(String entity) {
        // 查询参数
        MetaStatusMachine queryParams = new MetaStatusMachine();
        queryParams.setEntity(entity);
        // 根据entity查询列表
        List<MetaStatusMachine> metaList = queryMetaStatusMachineDao.findMetaStatusMachine(queryParams);
        if (CollectionUtils.isEmpty(metaList)) {
            throw new BusinessException(
                    BusinessCode.CodeBase.getCode(), "[Meta]未配置状态流，请检查：entity="+entity);
        }
        String fromStatus = StatusConstant.START;
        String handledToStatus = null;
        List<MetaStatusMachine> linkedList = new ArrayList<>();
        while (true) {
            MetaStatusMachine next = getByFromStatus(fromStatus, handledToStatus, metaList);
            linkedList.add(next);
            // 如果状态流toStatus为“end”，跳出循环
            if (next.getToStatus().equals(StatusConstant.END)) {
                break;
            }
            fromStatus = next.getToStatus();
            handledToStatus = next.getFromStatus();
        }
        return linkedList;
    }

    @Override
    @Transactional
    public Integer changeEntityStatus(String entity, Long id, ChangeStatusModel model) {
        return doChangeEntityStatus(entity, id ,model, false);
    }

    @Override
    public Integer changeEntityStatusWithLog(String entity, Long id, ChangeStatusModel model) {
        return doChangeEntityStatus(entity, id, model, true);
    }

    @Override
    @Transactional
    public BulkResult bulkChangeEntityStatus(String entity, BulkChangStatusModel model) {
        return doBulkChangeEntityStatus(entity, model, false);
    }

    @Override
    @Transactional
    public BulkResult bulkChangeEntityStatusWithLog(String entity, BulkChangStatusModel model) {
        return doBulkChangeEntityStatus(entity, model, true);
    }

    /**
     * 根据fromStatus从列表中获取下一个未被处理的meta配置
     * @param fromStatus
     * @param handledToStatus
     * @param metaList
     * @return
     */
    private MetaStatusMachine getByFromStatus(String fromStatus, String handledToStatus, List<MetaStatusMachine> metaList) {
        List<MetaStatusMachine> targetList =
                metaList.stream().filter(meta -> StringUtils.isBlank(handledToStatus)
                ? meta.getFromStatus().equals(fromStatus)
                : meta.getFromStatus().equals(fromStatus) && !meta.getToStatus().equals(handledToStatus))
                        .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(targetList)) {
            throw new BusinessException(
                    BusinessCode.CodeBase.getCode(), "[Meta]未获取到非退回toStatus，fromStatus："+fromStatus);
        }
        if (targetList.size() > 1) {
            // 获取非退回toStatus List
            List<String> toStatusList =
                    targetList.stream().map(MetaStatusMachine::getToStatus).collect(Collectors.toList());
            throw new BusinessException(BusinessCode.CodeBase.getCode(),
                    new StringBuilder("[Meta]获取到多个非退回toStatus：").append(toStatusList)
                            .append("，fromStatus：").append(fromStatus).toString());
        }
        return targetList.get(0);
    }

    /**
     * 更改实体状态
     * @param entity 实体
     * @param id 实体id
     * @param model 参数
     * @param shouldSaveLog 是否需要保存日志（true，false）
     * @return
     */
    private Integer doChangeEntityStatus(String entity, Long id, ChangeStatusModel model, boolean shouldSaveLog) {
        if (null == model || StringUtils.isBlank(model.getStatus())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "[Meta]缺失参数：status");
        }
        String status = model.getStatus();
        List<MetaStatusMachine> metaStatusMachineList = getMetaStatusMachineList(entity, status);
        String entityTableName = metaStatusMachineList.get(0).getEntityTableName();
        // 获取实体当前状态
        String entityCurrentStatus = queryMetaStatusMachineDao.getEntityCurrentStatus(id, entityTableName);
        // 不允许状态跳转
        if (!isInMetaStatusMachineList(entityCurrentStatus, metaStatusMachineList)) {
            throw new BusinessException(BusinessCode.ErrorStatus.getCode(),
                    new StringBuilder().append("[Meta]不允许状态跳转：")
                            .append("From ").append(entityCurrentStatus).append(" To ").append(status)
                            .toString());
        }
        // 更新实体
        int affected = queryMetaStatusMachineDao.updateEntityStatus(entityTableName, id, status);
        // 需要保存日志
        if (shouldSaveLog) {
            saveWorkflow(entity, id, entityCurrentStatus, status, model.getNote());
        }
        return affected;
    }

    /**
     * 批量改变实体状态
     * @param entity 实体
     * @param model 参数
     * @param shouldSaveLog 是否需要保存日志（true，false）
     * @return
     */
    private BulkResult doBulkChangeEntityStatus(String entity, BulkChangStatusModel model, boolean shouldSaveLog) {
        // 参数校验
        if (null == model || null == model.getIds() || model.getIds().isEmpty()
                || StringUtils.isBlank(model.getStatus())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "[Meta]缺失参数");
        }
        List<Long> ids = model.getIds();
        String status = model.getStatus();
        int forbiddenCount = 0;
        int successCount = 0;
        int failCount = 0;

        // 获取可以到达该状态的状态机
        List<MetaStatusMachine> metaStatusMachineList = getMetaStatusMachineList(entity, status);
        // 获取表名
        String entityTableName = metaStatusMachineList.get(0).getEntityTableName();
        List<EntityCurrentStatus> entityCurrentStatuses =
                queryMetaStatusMachineDao.getEntitiesCurrentStatus(ids, entityTableName);
        // 能更新的实体id
        List<Long> canUpdateIds = new ArrayList<>();

        for (EntityCurrentStatus entityCurrentStatus : entityCurrentStatuses) {
            if (!isInMetaStatusMachineList(entityCurrentStatus.getStatus(), metaStatusMachineList)) {
                forbiddenCount++;
                continue;
            }
            // 更新实体
            int result = queryMetaStatusMachineDao.updateEntityStatus(
                    entityTableName, entityCurrentStatus.getId(), status);
            // 更新失败
            if (result < 0) {
                failCount++;
                continue;
            }
            successCount++;
            // 需要保存日志
            if (shouldSaveLog) {
                saveWorkflow(entity, entityCurrentStatus.getId(), entityCurrentStatus.getStatus(), status,
                        model.getNote());
            }
        }
        // 构建返回
        return MetaUtils.createBulkResult(new BulkMessage(200, successCount, "[Meta]更改状态成功"),
                failCount > 0 ? new BulkMessage(BusinessCode.DatabaseUpdateError.getCode(), failCount,
                        "[Meta]更新失败，数据库错误") : null,
                forbiddenCount > 0 ? new BulkMessage(BusinessCode.CRUD_UPDATE_FAILURE.getCode(), forbiddenCount,
                        "[Meta]不允许更新，不允许跳转到状态:["+status+"]") : null);
    }


    /**
     * 获取可以更新的状态机
     * @param entity 实体
     * @param status 目的状态
     * @return
     */
    private List<MetaStatusMachine> getMetaStatusMachineList(String entity, String status) {
        MetaStatusMachine queryEntity = new MetaStatusMachine();
        queryEntity.setEntity(entity);
        queryEntity.setToStatus(status);
        List<MetaStatusMachine> metaStatusMachineList = queryMetaStatusMachineDao.findMetaStatusMachine(queryEntity);
        if (null == metaStatusMachineList || metaStatusMachineList.isEmpty()) {
            throw new BusinessException(
                    BusinessCode.ErrorStatus.getCode(),
                    new StringBuilder()
                            .append("[Meta]没有配置状态流：")
                            .append("entity=").append(entity)
                            .append(",toStatus=").append(status)
                            .toString());
        }
        return metaStatusMachineList;
    }

    private boolean isInMetaStatusMachineList(String status, List<MetaStatusMachine> metaStatusMachineList) {
        for (MetaStatusMachine e : metaStatusMachineList) {
            // 判断能不能更新
            if (e.getFromStatus().equals(status)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 保存操作到工作流
     * @param entity 实体
     * @param entityId 实体id
     * @param fromStatus from status
     * @param toStatus to status
     * @param note 意见、备注
     * @return
     */
    private int saveWorkflow(String entity, Long entityId, String fromStatus, String toStatus,
                             String note) {
        MetaWorkflowLiteActivity activity = new MetaWorkflowLiteActivity();
        // 设置实体
        activity.setEntity(entity);
        // 设置实体id
        activity.setEntityId(entityId);
        // 有意见、备注，保存
        if (StringUtils.isNotBlank(note)) {
            activity.setNote(note);
        }
        // 设置 from status
        activity.setFromStatus(fromStatus);
        // 设置 to status
        activity.setToStatus(toStatus);
        // 设置创建者id
        activity.setCreatedById(JWTKit.getUserId());
        // 设置创建者
        activity.setCreatedBy(JWTKit.getAccount());
        // 设置创建时间
        activity.setCreatedTime(new Date());
        int result = metaWorkflowLiteActivityService.createMaster(activity);
        if (result < 0) {
            throw new BusinessException(BusinessCode.DatabaseInsertError.getCode(), "[Meta]保留工作流活动失败");
        }
        return result;
    }
}
