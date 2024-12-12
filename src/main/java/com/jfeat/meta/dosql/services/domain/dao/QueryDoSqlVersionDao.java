package com.jfeat.meta.dosql.services.domain.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import com.jfeat.meta.dosql.services.gen.persistence.model.DoSqlField;
import org.apache.ibatis.annotations.Param;
import com.jfeat.meta.dosql.services.gen.persistence.model.DoSqlVersion;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-09-16
 */
@Deprecated
public interface QueryDoSqlVersionDao extends QueryMasterDao<DoSqlVersion> {
    /*
     * Query entity list by page
     */
    List<DoSqlVersion> findDevVersionPage(Page<DoSqlVersion> page, @Param("record") DoSqlVersion record,
                                                @Param("tag") String tag,
                                                @Param("search") String search, @Param("orderBy") String orderBy,
                                                @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    DoSqlVersion queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<DoSqlVersion> queryMasterModelList(@Param("masterId") Object masterId);

    List<DoSqlVersion> queryVersionDetail(Page<DoSqlField> page, @Param("record") DoSqlVersion record,
                                                @Param("search") String search);

    List<DoSqlField> devVersionsSubquery(@Param("sqlVersion") String sqlVersion);
}