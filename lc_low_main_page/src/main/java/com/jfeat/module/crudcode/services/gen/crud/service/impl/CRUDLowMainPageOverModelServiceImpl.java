package com.jfeat.module.crudcode.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowMainPage;
import com.jfeat.module.crudcode.services.gen.persistence.dao.LowMainPageMapper;
import com.jfeat.module.crudcode.services.gen.persistence.dao.LowActionsMapper;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowActions;
    
    import com.jfeat.module.crudcode.services.gen.persistence.dao.LowOperationsMapper;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowOperations;
    
    import com.jfeat.module.crudcode.services.gen.persistence.dao.LowFiltersMapper;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowFilters;
    
    import com.jfeat.module.crudcode.services.gen.persistence.dao.LowFieldsMapper;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowFields;
    
    import com.jfeat.module.crudcode.services.gen.crud.service.CRUDLowMainPageOverModelService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import com.jfeat.module.crudcode.services.gen.crud.model.LowMainPageModel;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDLowMainPageOverModelService
 * @author Code generator
 * @since 2021-09-07
 */

@Service
public class CRUDLowMainPageOverModelServiceImpl  extends CRUDServiceOverModelImpl<LowMainPage,LowMainPageModel> implements CRUDLowMainPageOverModelService {









    @Resource
    protected LowMainPageMapper lowMainPageMapper;

    
    @Override
    protected BaseMapper<LowMainPage> getMasterMapper() {
        return lowMainPageMapper;
    }

    @Override
    protected Class<LowMainPage> masterClassName() {
        return LowMainPage.class;
    }

    @Override
    protected Class<LowMainPageModel> modelClassName() {
        return LowMainPageModel.class;
    }



    
    @Resource
    private LowActionsMapper lowActionsMapper;

                        private final static String lowActionsFieldName = "page_id";
    
        private final static String lowActionsKeyName = "lowActionss";
    
                        
    

    
    
    @Resource
    private LowOperationsMapper lowOperationsMapper;

                        private final static String lowOperationsFieldName = "page_id";
    
        private final static String lowOperationsKeyName = "lowOperationss";
    
                        
    

    
    
    @Resource
    private LowFiltersMapper lowFiltersMapper;

                        private final static String lowFiltersFieldName = "page_id";
    
        private final static String lowFiltersKeyName = "lowFilterss";
    
                        
    

    
    
    @Resource
    private LowFieldsMapper lowFieldsMapper;

                        private final static String lowFieldsFieldName = "page_id";
    
        private final static String lowFieldsKeyName = "lowFieldss";
    
                        
    

    
    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                                             lowActionsKeyName
                                                                 ,lowOperationsKeyName
                                                ,lowFiltersKeyName
                                                ,lowFieldsKeyName
                            };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {

        
                                                
            if (field.compareTo(lowActionsKeyName) == 0) {
                FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(lowActionsFieldName);
            _field.setItemClassName(LowActions.class);
            _field.setItemMapper(lowActionsMapper);
            
            return _field;
        }


            
                                                
            else if (field.compareTo(lowOperationsKeyName) == 0) {
                FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(lowOperationsFieldName);
            _field.setItemClassName(LowOperations.class);
            _field.setItemMapper(lowOperationsMapper);
            
            return _field;
        }


            
                                                
            else if (field.compareTo(lowFiltersKeyName) == 0) {
                FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(lowFiltersFieldName);
            _field.setItemClassName(LowFilters.class);
            _field.setItemMapper(lowFiltersMapper);
            
            return _field;
        }


            
                                                
            else if (field.compareTo(lowFieldsKeyName) == 0) {
                FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(lowFieldsFieldName);
            _field.setItemClassName(LowFields.class);
            _field.setItemMapper(lowFieldsMapper);
            
            return _field;
        }


            
        throw new BusinessException(BusinessCode.BadRequest);
    }


    @Override
    protected String[] childFieldNames() {
        return new String[]{
                                };
    }

    @Override
    protected FIELD onChildFieldItem(String field) {
                
        throw new BusinessException(BusinessCode.BadRequest);
    }




}


