package com.jfeat.module.apicode.services.domain.dao;

import com.jfeat.module.apicode.services.domain.model.ApiTableModelRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.ApiTableModelDTO;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModel;
import com.jfeat.module.apicode.services.gen.crud.model.ApiTableModelModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2023-11-22
 */
public interface QueryApiTableModelDao extends QueryMasterDao<ApiTableModel> {
    /*
     * Query entity list by page
     */
    List<ApiTableModelRecord> findApiTableModelPage(Page<ApiTableModelRecord> page, @Param("record") ApiTableModelRecord record,
                                                    @Param("tag") String tag,
                                                    @Param("search") String search, @Param("orderBy") String orderBy,
                                                    @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    ApiTableModelModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<ApiTableModelModel> queryMasterModelList(@Param("masterId") Object masterId);

    ApiTableModelRecord getTableWithSubtables(@Param("id") Long id);

    /**
     * 查询DTO
     *
     * @param id 数据模型id
     */
    ApiTableModelDTO getDTO(@Param("id") Long id);

    /**
     * 获取子数据模型列表
     * <p>这个方法提供的目的是给getDTO使用的，用于获取DTO中的子模型列表</p>
     *
     * @param id 数据模型id
     * @return 子数据模型列表
     */
    List<ApiTableModelDTO> listSubModel(@Param("id") Long id);

}