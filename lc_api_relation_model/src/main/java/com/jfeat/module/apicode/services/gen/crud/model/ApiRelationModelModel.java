package com.jfeat.module.apicode.services.gen.crud.model;
// this is serviceModel.java.vm




import java.util.List;
import com.jfeat.module.apicode.services.gen.persistence.model.ApiRelationModelItem;
import com.jfeat.module.apicode.services.gen.persistence.model.ApiRelationModel;

/**
 * Created by Code generator on 2023-12-04
 *  * slaves.size() : 1
 *  * modelpack : import com.jfeat.module.apicode.services.gen.crud.model.ApiRelationModelModel;
 */
public class ApiRelationModelModel extends ApiRelationModel{


    // apiRelationModelItem
    // ApiRelationModelItem
    // apiRelationModelItem
    private List<ApiRelationModelItem> relations;

    public List<ApiRelationModelItem> getRelations() {
        return this.relations;
    }

    public void setRelations(List<ApiRelationModelItem> relations) {
        this.relations = relations;
    }
    
}
