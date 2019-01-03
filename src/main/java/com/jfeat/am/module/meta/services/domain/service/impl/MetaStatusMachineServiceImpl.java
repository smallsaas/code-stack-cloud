package com.jfeat.am.module.meta.services.domain.service.impl;

import com.jfeat.am.module.meta.services.domain.dao.QueryMetaStatusMachineDao;
import com.jfeat.am.module.meta.services.domain.model.EntityCurrentStatus;
import com.jfeat.am.module.meta.services.domain.service.MetaStatusMachineService;
import com.jfeat.am.module.meta.services.gen.crud.service.impl.CRUDMetaStatusMachineServiceImpl;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaStatusMachine;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.BulkMessage;
import com.jfeat.crud.base.tips.BulkResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("metaStatusMachineService")
public class MetaStatusMachineServiceImpl extends CRUDMetaStatusMachineServiceImpl implements MetaStatusMachineService {

    @Resource
    private QueryMetaStatusMachineDao queryMetaStatusMachineDao;
    @Override
    public Integer changeEntityStatus(String entity, Long id, String status) {

        List<MetaStatusMachine> metaStatusMachineList = getMetaStatusMachineList(entity, status);
        String entityTableName = metaStatusMachineList.get(0).getEntityTableName();
        // 获取实体当前状态
        String entityCurrentStatus = queryMetaStatusMachineDao.getEntityCurrentStatus(id, entityTableName);
        if (isInMetaStatusMachineList(entityCurrentStatus, metaStatusMachineList)) {
            return queryMetaStatusMachineDao.updateEntityStatus(entityTableName, id, status);
        }
        throw new BusinessException(BusinessCode.ErrorStatus.getCode(), "不允许状态跳转");
    }

    @Override
    public BulkResult bulkChangeEntityStatus(String entity, List<Long> ids, String status) {
        // 成功
        BulkMessage success = new BulkMessage(200, 0, "更新成功");

        // 失败
        BulkMessage fail = new BulkMessage(
                BusinessCode.CRUD_UPDATE_FAILURE.getCode(), 0, "更新失败");

        // 禁止
        BulkMessage forbidden = new BulkMessage(
                BusinessCode.CRUD_UPDATE_FAILURE.getCode(),
                0,
                "不允许更新，不允许跳转到状态:["+status+"]");

        List<BulkMessage> failure = Arrays.asList(fail, forbidden);
        BulkResult bulkResult = new BulkResult(success, failure);

        // 获取可以到达该状态的状态机
        List<MetaStatusMachine> metaStatusMachineList = getMetaStatusMachineList(entity, status);
        // 获取表名
        String entityTableName = metaStatusMachineList.get(0).getEntityTableName();
        List<EntityCurrentStatus> entityCurrentStatuses =
                queryMetaStatusMachineDao.getEntitiesCurrentStatus(ids, entityTableName);
        // 能更新的实体id
        List<Long> canUpdateIds = new ArrayList<>();

        for (EntityCurrentStatus entityCurrentStatus : entityCurrentStatuses) {
            if (isInMetaStatusMachineList(entityCurrentStatus.getStatus(), metaStatusMachineList)) {
                canUpdateIds.add(entityCurrentStatus.getId());
            } else {
                forbidden.setCount(forbidden.getCount()+1);
            }
        }

        Integer affected = queryMetaStatusMachineDao.batchUpdateEntityStatus(entityTableName, canUpdateIds, status);
        success.setCount(affected);
        fail.setCount(canUpdateIds.size() - affected);
        return bulkResult;
    }

    /**
     * 功能描述：获取可以更新的状态机
     * @return
     */
    private List<MetaStatusMachine> getMetaStatusMachineList(String entity, String status) {
        MetaStatusMachine queryEntity = new MetaStatusMachine();
        queryEntity.setEntity(entity);
        queryEntity.setToStatus(status);
        List<MetaStatusMachine> metaStatusMachineList = queryMetaStatusMachineDao.findMetaStatusMachine(queryEntity);
        if (null == metaStatusMachineList || metaStatusMachineList.isEmpty()) {
            throw new BusinessException(
                    BusinessCode.ErrorStatus.getCode(), "获取状态机失败：entity=" + entity + ",toStatus=" + status);
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
}
