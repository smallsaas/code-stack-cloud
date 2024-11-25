package com.jfeat.module.crudcode.services.domain.dao;

import com.jfeat.module.crudcode.services.domain.model.LowFieldPropertiesRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowFieldProperties;
import com.jfeat.module.crudcode.services.gen.crud.model.LowFieldPropertiesModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2021-08-31
 */
public interface QueryLowFieldPropertiesDao extends QueryMasterDao<LowFieldProperties> {
   /*
    * Query entity list by page
    */
    List<LowFieldPropertiesRecord> findLowFieldPropertiesPage(Page<LowFieldPropertiesRecord> page, @Param("record") LowFieldPropertiesRecord record,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    LowFieldPropertiesModel queryMasterModel(@Param("id") Long id);
}