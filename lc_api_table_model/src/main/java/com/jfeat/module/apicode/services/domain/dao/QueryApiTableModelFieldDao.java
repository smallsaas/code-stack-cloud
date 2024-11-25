package com.jfeat.module.apicode.services.domain.dao;

import com.jfeat.module.apicode.services.domain.model.ApiTableModelFieldRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import com.jfeat.module.apicode.services.domain.model.FieldModelRecord;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModelField;
import com.jfeat.module.apicode.services.gen.crud.model.ApiTableModelFieldModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2023-11-22
 */
public interface QueryApiTableModelFieldDao extends QueryMasterDao<ApiTableModelField> {
   /*
    * Query entity list by page
    */
    List<ApiTableModelFieldRecord> findApiTableModelFieldPage(Page<ApiTableModelFieldRecord> page, @Param("record") ApiTableModelFieldRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    ApiTableModelFieldModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<ApiTableModelFieldModel> queryMasterModelList(@Param("masterId") Object masterId);

    /*
    *
    * */
    FieldModelRecord findFieldModeDetail(@Param("defaultFieldName") String defaultFieldName);

 /**
  * 查询列表，根据table_model_id
  *
  * @param tableModelId 数据模型id
  */
 List<ApiTableModelField> listByTableModeLId(@Param("tableModelId") Long tableModelId);
}