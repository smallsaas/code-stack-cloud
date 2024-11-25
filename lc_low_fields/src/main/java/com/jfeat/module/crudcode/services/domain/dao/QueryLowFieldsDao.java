package com.jfeat.module.crudcode.services.domain.dao;

import com.jfeat.module.crudcode.services.domain.model.LowFieldsRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowFields;
import com.jfeat.module.crudcode.services.gen.crud.model.LowFieldsModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2021-10-09
 */
public interface QueryLowFieldsDao extends QueryMasterDao<LowFields> {
   /*
    * Query entity list by page
    */
    List<LowFieldsRecord> findLowFieldsPage(Page<LowFieldsRecord> page, @Param("record") LowFieldsRecord record,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    LowFieldsModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<LowFieldsModel> queryMasterModelList(@Param("masterId") Object masterId);
}