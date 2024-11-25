package com.jfeat.module.apicode.services.domain.dao;

import com.jfeat.module.apicode.services.domain.model.ApiRelationModelRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.apicode.services.gen.persistence.model.ApiRelationModel;
import com.jfeat.module.apicode.services.gen.crud.model.ApiRelationModelModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2023-12-04
 */
public interface QueryApiRelationModelDao extends QueryMasterDao<ApiRelationModel> {
   /*
    * Query entity list by page
    */
    List<ApiRelationModelRecord> findApiRelationModelPage(Page<ApiRelationModelRecord> page, @Param("record") ApiRelationModelRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    ApiRelationModelModel queryMasterModel(@Param("id") Long id);


    /*
     * Query slave items list for entity model
     */
    List<ApiRelationModelModel> queryMasterModelList(@Param("masterId") Object masterId);
}