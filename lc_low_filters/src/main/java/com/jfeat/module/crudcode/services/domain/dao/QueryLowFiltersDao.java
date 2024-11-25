package com.jfeat.module.crudcode.services.domain.dao;

import com.jfeat.module.crudcode.services.domain.model.LowFiltersRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowFilters;
import com.jfeat.module.crudcode.services.gen.crud.model.LowFiltersModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2021-09-06
 */
public interface QueryLowFiltersDao extends QueryMasterDao<LowFilters> {
   /*
    * Query entity list by page
    */
    List<LowFiltersRecord> findLowFiltersPage(Page<LowFiltersRecord> page, @Param("record") LowFiltersRecord record,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    LowFiltersModel queryMasterModel(@Param("id") Long id);
}