package com.jfeat.module.apicode.services.domain.dao;

import com.jfeat.module.apicode.services.domain.model.ApiSubTableModelRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiSubTableModel;
import com.jfeat.module.apicode.services.gen.crud.model.ApiSubTableModelModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2023-11-22
 */
public interface QueryApiSubTableModelDao extends QueryMasterDao<ApiSubTableModel> {
   /*
    * Query entity list by page
    */
    List<ApiSubTableModelRecord> findApiSubTableModelPage(Page<ApiSubTableModelRecord> page, @Param("record") ApiSubTableModelRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    ApiSubTableModelModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<ApiSubTableModelModel> queryMasterModelList(@Param("masterId") Object masterId);
}