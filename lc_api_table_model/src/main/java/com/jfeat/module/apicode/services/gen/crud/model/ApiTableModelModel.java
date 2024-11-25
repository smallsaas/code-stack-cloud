package com.jfeat.module.apicode.services.gen.crud.model;
// this is serviceModel.java.vm




import java.util.List;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModelField;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiSubTableModel;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModel;

/**
 * Created by Code generator on 2023-11-22
 *  * slaves.size() : 2
 *  * modelpack : import com.jfeat.module.apicode.services.gen.crud.model.ApiTableModelModel;
 */
public class ApiTableModelModel extends ApiTableModel{
    private List<ApiTableModelField> fields;

    private List<ApiSubTableModel> subtables;

    public List<ApiTableModelField> getFields() {
        return fields;
    }

    public void setFields(List<ApiTableModelField> fields) {
        this.fields = fields;
    }

    public List<ApiSubTableModel> getSubtables() {
        return subtables;
    }

    public void setSubtables(List<ApiSubTableModel> subtables) {
        this.subtables = subtables;
    }
}
