package com.jfeat.module.crudcode.services.domain.dao;

import com.jfeat.module.crudcode.services.domain.model.LowActionsRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowActions;
import com.jfeat.module.crudcode.services.gen.crud.model.LowActionsModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2021-09-28
 */
public interface QueryLowActionsDao extends QueryMasterDao<LowActions> {
   /*
    * Query entity list by page
    */
    List<LowActionsRecord> findLowActionsPage(Page<LowActionsRecord> page, @Param("record") LowActionsRecord record,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    LowActionsModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<LowActionsModel> queryMasterModelList(@Param("masterId") Object masterId);
}