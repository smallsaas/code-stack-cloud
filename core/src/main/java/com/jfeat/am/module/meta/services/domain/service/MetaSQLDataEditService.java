package com.jfeat.am.module.meta.services.domain.service;

public interface MetaSQLDataEditService {

    Integer putField(String tableName, String fieldName, String value);

    Integer postField(String tableName, String fieldName, String value);
}
