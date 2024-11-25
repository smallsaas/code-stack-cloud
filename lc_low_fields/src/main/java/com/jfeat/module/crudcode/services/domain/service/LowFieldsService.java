package com.jfeat.module.crudcode.services.domain.service;

import com.jfeat.module.crudcode.services.gen.crud.service.CRUDLowFieldsService;

/**
 * Created by vincent on 2017/10/19.
 */
public interface LowFieldsService extends CRUDLowFieldsService{
    Integer deleteByPage(Long pageId);
}