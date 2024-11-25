package com.jfeat.module.crudcode.services.gen.crud.model;
// this is serviceModel.java.vm




import java.util.List;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowActions;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowOperations;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowFilters;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowFields;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowMainPage;

/**
 * Created by Code generator on 2021-09-07
 *  * slaves.size() : 4
 *  * modelpack : import com.jfeat.module.crudcode.services.gen.crud.model.LowMainPageModel;
 */
public class LowMainPageModel extends LowMainPage{
    private List<LowActions> lowActionss;

    // lowActions
    // LowActions
    // lowActions
    public List<LowActions> getLowActionss() {
        return lowActionss;
    }

    public void setLowActionss(List<LowActions> lowActionss) {
        this.lowActionss = lowActionss;
    }
    private List<LowOperations> lowOperationss;

    // lowOperations
    // LowOperations
    // lowOperations
    public List<LowOperations> getLowOperationss() {
        return lowOperationss;
    }

    public void setLowOperationss(List<LowOperations> lowOperationss) {
        this.lowOperationss = lowOperationss;
    }
    private List<LowFilters> lowFilterss;

    // lowFilters
    // LowFilters
    // lowFilters
    public List<LowFilters> getLowFilterss() {
        return lowFilterss;
    }

    public void setLowFilterss(List<LowFilters> lowFilterss) {
        this.lowFilterss = lowFilterss;
    }
    private List<LowFields> lowFieldss;

    // lowFields
    // LowFields
    // lowFields
    public List<LowFields> getLowFieldss() {
        return lowFieldss;
    }

    public void setLowFieldss(List<LowFields> lowFieldss) {
        this.lowFieldss = lowFieldss;
    }
}
