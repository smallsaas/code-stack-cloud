package com.jfeat.module.apicode.services.domain.model;

import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModel;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModelField;
import lombok.Data;

import java.util.List;

/**
 * Created by Code generator on 2023-11-22
 */
@Data
public class ApiTableModelRecord extends ApiTableModel{
    /**
     * 字段列表
     */
    private List<ApiTableModelField> fields;
    /**
     * 子表列表
     */
    private List<ApiTableModelRecord> subtables;
}
