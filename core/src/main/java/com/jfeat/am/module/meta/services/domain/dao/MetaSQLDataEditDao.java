package com.jfeat.am.module.meta.services.domain.dao;

import com.jfeat.am.module.meta.services.domain.model.MetaWhereColumn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MetaSQLDataEditDao {

    public Integer putField(@Param("table") String table
            ,@Param("field")  String field
            , @Param("value") String value
            ,@Param("items") List<MetaWhereColumn> items);

//    public Integer postField(@Param("table") String table
//            ,@Param("field")  String field
//            , @Param("value") String value
//            ,@Param("items") List<MetaWhereColumn> items);

    String getOldValueString(@Param("table") String table
            ,@Param("field")  String field
            , @Param("value") String value
            ,@Param("items") List<MetaWhereColumn> items);

    Integer getOldValueInteger(@Param("table") String table
            ,@Param("field")  String field
            , @Param("value") String value
            ,@Param("items") List<MetaWhereColumn> items);

}
