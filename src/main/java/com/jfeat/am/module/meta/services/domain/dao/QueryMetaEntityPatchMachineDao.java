package com.jfeat.am.module.meta.services.domain.dao;

import com.jfeat.am.module.meta.services.domain.model.MetaEntityPatchMachineRecord;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.meta.services.domain.model.SortNumberRecord;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaEntityPatchMachine;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Code Generator on 2019-01-07
 */
public interface QueryMetaEntityPatchMachineDao extends BaseMapper<MetaEntityPatchMachineRecord> {

    List<MetaEntityPatchMachine> findMetaEntityPatchMachines(MetaEntityPatchMachine metaEntityPatchMachine);

    Integer updateEntity(@Param("entityTableName") String entityTableName,
                         @Param("params") Map<String, String> params,
                         @Param("id") Long id);

    Integer bulkDeleteEntity(@Param("entityTableName") String entityTableName,
                             @Param("ids") List<Long> ids);

    List<SortNumberRecord> findSortNumberRecord(@Param("entityTableName") String entityTableName,
                                                @Param("entityFieldName") String EntityFieldName,
                                                @Param("ids") List<Long> ids);
}