package com.jfeat.module.crudcode.services.domain.filter;

import com.jfeat.crud.plus.CRUDFilter;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowFilters;


/**
 * Created by Code generator on 2021-09-06
 */
public class LowFiltersFilter implements CRUDFilter<LowFilters> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};

    @Override
    public void filter(LowFilters entity, boolean insertOrUpdate) {

        //if insertOrUpdate is true,means for insert, do this
        if (insertOrUpdate){

            //then insertOrUpdate is false,means for update,do this
        }else {

        }

    }

    @Override
    public String[] ignore(boolean retrieveOrUpdate) {
        //if retrieveOrUpdate is true,means for retrieve ,do this
        if (retrieveOrUpdate){
            return ignoreFields;
            //then retrieveOrUpdate  if false ,means for update,do this
        }else {
            return updateIgnoreFields;
        }
    }
}
