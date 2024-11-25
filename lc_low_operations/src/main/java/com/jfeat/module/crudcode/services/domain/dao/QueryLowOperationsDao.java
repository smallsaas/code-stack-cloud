package com.jfeat.module.crudcode.services.domain.dao;

import com.jfeat.module.crudcode.services.domain.model.LowOperationsRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowOperations;
import com.jfeat.module.crudcode.services.gen.crud.model.LowOperationsModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2021-10-11
 */
public interface QueryLowOperationsDao extends QueryMasterDao<LowOperations> {
   /*
    * Query entity list by page
    */
    List<LowOperationsRecord> findLowOperationsPage(Page<LowOperationsRecord> page, @Param("record") LowOperationsRecord record,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    LowOperationsModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
//    List<LowOperationsModel> queryMasterModelList(@Param("masterId") Object masterId);
}