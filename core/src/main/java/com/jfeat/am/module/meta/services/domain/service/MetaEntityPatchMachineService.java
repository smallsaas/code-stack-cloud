package com.jfeat.am.module.meta.services.domain.service;

import com.jfeat.am.module.meta.services.gen.crud.service.CRUDMetaEntityPatchMachineService;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaEntityPatchMachine;
import com.jfeat.crud.base.tips.BulkResult;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 2017/10/19.
 */
public interface MetaEntityPatchMachineService extends CRUDMetaEntityPatchMachineService {

    List<MetaEntityPatchMachine> findMetaEntityPatchMachines(MetaEntityPatchMachine queryEntity);

    /**
     * 根据条件查询
     * @param tableName
     * @param conditions
     * @return
     */
    List<Map<String, Object>> selectEntity(String tableName, Map<String, Object> conditions);

    /**
     * 新增配置
     * @param meta 配置
     * @return
     */
    Integer createMeta(MetaEntityPatchMachine meta);

    /**
     * 添加新记录
     * @param entity 实体模块
     * @param params 添加参数
     * @return
     */
    Integer insertEntity(String entity, Map<String, String> params);


    /**
     * 更新单个实体
     * @param entityName
     * @param condition
     * @param value
     * @return
     */
    Integer updateEntity(String entityName, String value,String condition);

    /**
     * 更新实体
     * @param entity 实体模块
     * @param id 实体id, 可以是唯一标识的其他字段
     * @param params 更新参数
     * @return
     */
    Integer updateEntity(String entity, String id, Map<String, String> params);


    /**
     * 根据条件更新实体字段
     * @param entity
     * @param params
     * @param conditions
     * @return
     */
    Integer updateEntity(String entity, Map<String, String> params, Map<String, String> conditions);


    /**
     * 批量更新实体
     * @param entity 实体模块
     * @param params 更新参数，包含实体id
     * @return
     */
    BulkResult bulkUpdateEntity(String entity, List<Map<String, String>> params);

    /**
     * 批量删除实体
     * @param entity 实体模块
     * @param ids id列表
     * @return
     */
    BulkResult bulkDeleteEntity(String entity, List<Long> ids);

    /**
     * 将entity排序上移
     * @param entity 实体
     * @param id 被移动实体id
     * @param nextId 需移动到下一个位置实体的id
     * @return
     */
    Integer moveUpEntity(String entity, Long id, Long nextId);

    /**
     * 将entity排序下移
     * @param entity 实体
     * @param id 被移动实体id
     * @param nextId 需移动到下一个位置实体的id
     * @return
     */
    Integer moveDownEntity(String entity, Long id, Long nextId);

    /**
     * 逻辑删除单个实体
     * @param entity 实体
     * @param id 实体id
     * @param isReverse 是否为反向操作
     * @return
     */
    Integer handleLogicDelete(String entity, Long id, boolean isReverse);

    /**
     * 逻辑批量删除实体
     * @param entity 实体
     * @param ids 实体id列表
     * @param isReverse 是否为反向操作
     * @return
     */
    BulkResult handleBulkLogicDelete(String entity, List<Long> ids, boolean isReverse);



    Integer createWhereFiled(String entity,String filedName);

    Integer updateWhereFiled(String entity,String oldFiledName,String newFiledName,String status);

    List<String> selectWhereFiled(String entity);

    Integer deleteWhereFiled(String entity,String filedName,String status);


}