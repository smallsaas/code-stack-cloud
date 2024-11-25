package com.jfeat.module.apicode.services.domain.dao;

import com.jfeat.module.apicode.services.domain.model.ApiRelationModelItemRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.apicode.services.gen.persistence.model.ApiRelationModelItem;
import com.jfeat.module.apicode.services.gen.crud.model.ApiRelationModelItemModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2023-12-04
 */
public interface QueryApiRelationModelItemDao extends QueryMasterDao<ApiRelationModelItem> {
   /*
    * Query entity list by page
    */
    List<ApiRelationModelItemRecord> findApiRelationModelItemPage(Page<ApiRelationModelItemRecord> page, @Param("record") ApiRelationModelItemRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    ApiRelationModelItemModel queryMasterModel(@Param("id") Long id);


    /*
     * Query slave items list for entity model
     */
    List<ApiRelationModelItemModel> queryMasterModelList(@Param("masterId") Object masterId);
}