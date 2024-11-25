package com.jfeat.module.crudcode.services.domain.dao;

import com.jfeat.module.crudcode.services.domain.model.LowMainPageRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import com.jfeat.module.crudcode.services.domain.model.LowMainPageRecordTwo;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowMainPage;
import com.jfeat.module.crudcode.services.gen.crud.model.LowMainPageModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2021-09-07
 */
public interface QueryLowMainPageDao extends QueryMasterDao<LowMainPage> {
   /*
    * Query entity list by page
    */
    List<LowMainPageRecord> findLowMainPagePage(Page<LowMainPageRecord> page, @Param("record") LowMainPageRecord record,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    LowMainPageModel queryMasterModel(@Param("id") Long id);


    List<LowMainPageRecordTwo> findLowMainPagePageTwo(Page<LowMainPageRecordTwo> page, @Param("record") LowMainPageRecordTwo record,
                                                      @Param("search") String search, @Param("orderBy") String orderBy,
                                                      @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}