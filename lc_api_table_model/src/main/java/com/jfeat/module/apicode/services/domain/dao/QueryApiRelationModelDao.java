package com.jfeat.module.apicode.services.domain.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiRelationModel;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.ApiRelationModelDTO;
import org.apache.ibatis.annotations.Param;

public interface QueryApiRelationModelDao extends BaseMapper<ApiRelationModel> {

    /**
     * 获取DTO
     *
     * @param id 关系模型id
     */
    ApiRelationModelDTO getDTO(@Param("id") Long id);
}