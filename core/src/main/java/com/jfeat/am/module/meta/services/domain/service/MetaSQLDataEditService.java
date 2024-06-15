package com.jfeat.am.module.meta.services.domain.service;

public interface MetaSQLDataEditService {

    Integer putField(String entityName, String fieldName, String value);

    Integer postField(String entityName, String fieldName, String value);
}
