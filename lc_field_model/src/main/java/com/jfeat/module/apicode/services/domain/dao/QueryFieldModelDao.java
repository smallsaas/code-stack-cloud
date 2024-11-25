package com.jfeat.module.apicode.services.domain.dao;

import com.jfeat.module.apicode.services.domain.model.FieldModelRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.apicode.services.gen.persistence.model.FieldModel;
import com.jfeat.module.apicode.services.gen.crud.model.FieldModelModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2021-10-20
 */
public interface QueryFieldModelDao extends QueryMasterDao<FieldModel> {
   /*
    * Query entity list by page
    */
    List<FieldModelRecord> findFieldModelPage(Page<FieldModelRecord> page, @Param("record") FieldModelRecord record,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    FieldModelModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<FieldModelModel> queryMasterModelList(@Param("masterId") Object masterId);
}