package com.jfeat.am.module.meta.services.domain.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.meta.services.domain.model.SortNumberRecord;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaEntityPatchMachine;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Code Generator on 2019-01-07
 */
public interface QueryMetaEntityPatchMachineDao extends BaseMapper<MetaEntityPatchMachine> {

    List<MetaEntityPatchMachine> findMetaEntityPatchMachines(MetaEntityPatchMachine metaEntityPatchMachine);

    List<Map<String, Object>> selectEntity(@Param("entityTableName") String entityTableName,
                                           @Param("conditions") Map<String, Object> conditions);

    Integer updateEntity(@Param("entityTableName") String entityTableName,
                         @Param("params") Map<String, String> params,
                         @Param("id") Long id);

    Integer updateOnlyEntity(@Param("entityTableName") String entityTableName,
                         @Param("entityField") String entityField,
                         @Param("entityValue") String entityValue,
                         @Param("id") Long id);

    Integer updateEntityByConditions(@Param("entityTableName") String entityTableName,
                         @Param("params") Map<String, String> params,
                         @Param("conditions") Map<String, String> conditions);

    Integer insertEntity(@Param("entityTableName") String entityTableName,
                         @Param("params") Map<String, String> params);

    /**
     * 批量更新多个实体相同字段为某个值
     * @param entityTableName 表名
     * @param params 参数
     * @param ids id列表
     * @return
     */
    Integer bulkUpdateEntityBySameParams(@Param("entityTableName") String entityTableName,
                                         @Param("params") Map<String, String> params,
                                         @Param("ids") List<Long> ids);

    Integer bulkDeleteEntity(@Param("entityTableName") String entityTableName,
                             @Param("ids") List<Long> ids);

    List<SortNumberRecord> findSortNumberRecord(@Param("entityTableName") String entityTableName,
                                                @Param("entityFieldName") String EntityFieldName,
                                                @Param("ids") List<Long> ids);
}