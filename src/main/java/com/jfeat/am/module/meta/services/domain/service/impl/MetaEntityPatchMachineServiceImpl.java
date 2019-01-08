package com.jfeat.am.module.meta.services.domain.service.impl;

import com.google.common.base.CaseFormat;
import com.jfeat.am.module.meta.constant.EntityFieldType;
import com.jfeat.am.module.meta.services.domain.dao.QueryMetaEntityPatchMachineDao;
import com.jfeat.am.module.meta.services.domain.service.MetaEntityPatchMachineService;
import com.jfeat.am.module.meta.services.domain.utils.MetaUtils;
import com.jfeat.am.module.meta.services.gen.crud.service.impl.CRUDMetaEntityPatchMachineServiceImpl;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaEntityPatchMachine;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.BulkMessage;
import com.jfeat.crud.base.tips.BulkResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("metaCrudPatchMachineService")
public class MetaEntityPatchMachineServiceImpl extends CRUDMetaEntityPatchMachineServiceImpl implements MetaEntityPatchMachineService {

    @Resource
    private QueryMetaEntityPatchMachineDao queryMetaEntityPatchMachineDao;

    @Override
    public List<MetaEntityPatchMachine> findMetaEntityPatchMachines(MetaEntityPatchMachine queryEntity) {
        return queryMetaEntityPatchMachineDao.findMetaEntityPatchMachines(queryEntity);
    }

    @Override
    public Integer createMeta(MetaEntityPatchMachine meta) {
        if (null == meta) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "meta参数不能为空");
        }
        if (null != meta.getEntityFieldType() && !EntityFieldType.isEntityFieldType(meta.getEntityFieldType())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(),
                    "[meta]参数：entityFieldType选取范围"+ Arrays.asList(EntityFieldType.values()).toString());
        }
        return createMaster(meta);
    }

    @Override
    @Transactional
    public Integer updateEntity(String entity, Long id, Map<String, String> params) {
        // 没有参数，不需要更新
        if (CollectionUtils.isEmpty(params)) {
            return 0;
        }
        // 获取meta配置
        List<MetaEntityPatchMachine> metaList = findMetaList(entity);
        Map<String, MetaEntityPatchMachine> metaMap = createMetaMap(metaList);

        // 更新实体
        return update(id, entity, metaList.get(0).getEntityTableName(), params,  metaMap);
    }

    @Override
    @Transactional
    public BulkResult bulkUpdateEntity(String entity, List<Map<String, String>> paramsList) {
        // 没有参数，不需要更新
        if (CollectionUtils.isEmpty(paramsList)) {
            return null;
        }
        // 获取meta配置
        List<MetaEntityPatchMachine> metaList = findMetaList(entity);
        Map<String, MetaEntityPatchMachine> metaMap = createMetaMap(metaList);
        int success = 0; // 数据库更新成功数
        for (Map<String, String> params : paramsList) {
            if (!params.containsKey("id") || null == params.get("id")) {
                throw new BusinessException(BusinessCode.BadRequest.getCode(), "存在某些更新实体缺失id");
            }
            Long id;
            try {
                id = Long.parseLong(params.get("id"));
                params.remove("id");
            } catch (NumberFormatException e) {
                throw new BusinessException(
                        BusinessCode.BadRequest.getCode(), "[id:"+params.get("id")+"]，id非整数，请检查");
            }
            // 更新实体
            success += update(id, entity, metaList.get(0).getEntityTableName(), params, metaMap);
        }
        int fail = paramsList.size() - success;
        return MetaUtils.createBulkResult(new BulkMessage(200, success, "更新成功"),
                fail > 0 ? new BulkMessage(BusinessCode.DatabaseUpdateError.getCode(), fail, "更新失败，数据库错误") : null);
    }

    @Override
    @Transactional
    public BulkResult bulkDeleteEntity(String entity, List<Long> ids) {
        // 获取id配置
        MetaEntityPatchMachine meta = createMetaMap(findMetaList(entity)).get("id");
        if (null == meta) {
            throw new BusinessException(BusinessCode.CodeBase.getCode(),
                    "["+entity+"]缺失字段配置：entityFieldName=id");
        }
        int success = queryMetaEntityPatchMachineDao.bulkDeleteEntity(meta.getEntityTableName(), ids);
        int fail = ids.size() - success;
        return MetaUtils.createBulkResult(new BulkMessage(200, success, "删除成功"),
                fail > 0 ? new BulkMessage(BusinessCode.DatabaseDeleteError.getCode(), fail, "删除失败，数据库错误") : null);
    }

    /**
     * 获取meta Map
     * @param entity 实体
     * @return
     */
    private List<MetaEntityPatchMachine> findMetaList(String entity) {
        // 获取meta配置列表
        MetaEntityPatchMachine queryEntity = new MetaEntityPatchMachine();
        queryEntity.setEntity(entity);
        List<MetaEntityPatchMachine> metaList = findMetaEntityPatchMachines(queryEntity);
        if (CollectionUtils.isEmpty(metaList)) {
            throw new BusinessException(
                    BusinessCode.CodeBase.getCode(),
                    "获取meta crud patch配置失败，entity="+entity+"。请联系相关人员配置。");
        }
        return metaList;
    }

    /**
     * 构建meta map，建立键值对关系 entityFieldName：（Object）MetaEntityPatchMachine
     * @param metaList meta配置列表
     * @return
     */
    private Map<String, MetaEntityPatchMachine> createMetaMap(List<MetaEntityPatchMachine> metaList) {
        Map<String ,MetaEntityPatchMachine> metaMap = new HashMap<>();
        for (MetaEntityPatchMachine meta : metaList) {
            metaMap.put(meta.getEntityFieldName(), meta);
        }
        return metaMap;
    }

    /**
     * 更新实体
     * @param id 实体id
     * @param entity 实体模块
     * @param params 更新参数
     * @param metaMap meta配置
     * @return
     */
    private Integer update(Long id, String entity, String entityTableName, Map<String, String> params, Map<String, MetaEntityPatchMachine> metaMap) {
        // 用于将驼峰命名的参数转换为蛇形命名
        Map<String, String> queryMap = new HashMap<>();
        // 校验参数
        for (Map.Entry<String, String> param : params.entrySet()) {

            // 通过查询参数获取配置，这里处理了一下，web请求参数是驼峰命名法，但数据库里存的字段可能是：驼峰或蛇形命名
            String camelCaseKey = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE,param.getKey());
            MetaEntityPatchMachine meta = metaMap.get(param.getKey());
            if (null == meta) {
                meta = metaMap.get(camelCaseKey);
            }
            if (null == meta) {
                throw new BusinessException(BusinessCode.CodeBase.getCode(),
                        "[id:"+id+"]缺失字段配置：entity="+entity+"，entityFieldName="+param.getKey());
            }
            queryMap.put(meta.getEntityFieldName(), param.getValue());
            // 检查参数
            check(id, meta, param);
        }
        return queryMetaEntityPatchMachineDao.updateEntity(entityTableName, queryMap, id);
    }

    /**
     * 检查参数
     * @param id 实体id
     * @param meta meta配置
     * @param param 参数
     */
    private void check(Long id, MetaEntityPatchMachine meta, Map.Entry<String, String> param) {
        // value为null或空，继续迭代
        if (StringUtils.isBlank(param.getValue())) {
            return;
        }
        // 字符串类型，跳过检查，继续迭代
        if (EntityFieldType.STRING.toString().equals(meta.getEntityFieldType())) {
            return;
        }
        // 数字类型，格式检查，范围检查
        if (EntityFieldType.NUMBER.toString().equals(meta.getEntityFieldType())) {
            if (!param.getValue().matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
                throw new BusinessException(
                        BusinessCode.BadRequest.getCode(), "[id:"+id+"]参数："+meta.getEntityFieldName()+"要求数字格式");
            }
            // 转为long格式，如果是小数，向上取整再转换
            long value = param.getValue().indexOf(".") == -1
                    ? Long.parseLong(param.getValue())
                    : (long)Math.ceil(Double.parseDouble(param.getValue()));
            if ((null != meta.getNumberRangeMin() && value < meta.getNumberRangeMin())
                    || (null != meta.getNumberRangeMax() && value > meta.getNumberRangeMax())) {
                throw new BusinessException(BusinessCode.CodeBase.getCode(),
                        // [entity]参数：key=value，不在配置范围内[min,max]。
                        "[id:"+id+"]参数："+param.getKey()+"="+param.getValue()+"，不在配置范围内["
                                +meta.getNumberRangeMin()+","+meta.getNumberRangeMax()+"]");
            }
        } else if (EntityFieldType.DATE.toString().equals(meta.getEntityFieldType())) { // 日期类型，格式检查，有效检查
            SimpleDateFormat format_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat format_2 = new SimpleDateFormat("yyyy-MM-dd");
            Date date;
            int index;
            try {
                date = format_1.parse(param.getValue());
                index = 1;
            } catch (ParseException e1) {
                try {
                    date = format_2.parse(param.getValue());
                    index = 2;
                } catch (ParseException e2) {
                    throw new BusinessException(
                            BusinessCode.BadRequest.getCode(),
                            "[id:"+id+"]参数："+param.getKey()
                                    +"格式错误，请输入指定日期格式，yyyy-MM-dd HH:mm:ss或yyyy-MM-dd");
                }
            }
            String dateString = index == 1 ? format_1.format(date) : format_2.format(date);
            if (!param.getValue().equals(dateString)) {
                throw new BusinessException(
                        BusinessCode.BadRequest.getCode(),
                        "[id:"+id+"]参数："+param.getKey() +"日期无效，请仔细检查");
            }
        }
    }
}