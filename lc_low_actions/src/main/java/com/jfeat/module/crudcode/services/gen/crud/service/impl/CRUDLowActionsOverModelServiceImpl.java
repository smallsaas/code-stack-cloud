package com.jfeat.module.crudcode.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowActions;
import com.jfeat.module.crudcode.services.gen.persistence.dao.LowActionsMapper;
//import com.jfeat.module.crudcode.services.gen.persistence.dao.ModalItemBasicMapper;
//import com.jfeat.module.crudcode.services.gen.persistence.model.ModalItemBasic;
    
    import com.jfeat.module.crudcode.services.gen.crud.service.CRUDLowActionsOverModelService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import com.jfeat.module.crudcode.services.gen.crud.model.LowActionsModel;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDLowActionsOverModelService
 * @author Code generator
 * @since 2021-09-28
 */

@Service
public class CRUDLowActionsOverModelServiceImpl  extends CRUDServiceOverModelImpl<LowActions,LowActionsModel> implements CRUDLowActionsOverModelService {









    @Resource
    protected LowActionsMapper lowActionsMapper;

    
    @Override
    protected BaseMapper<LowActions> getMasterMapper() {
        return lowActionsMapper;
    }

    @Override
    protected Class<LowActions> masterClassName() {
        return LowActions.class;
    }

    @Override
    protected Class<LowActionsModel> modelClassName() {
        return LowActionsModel.class;
    }



    
//    @Resource
//    private ModalItemBasicMapper modalItemBasicMapper;

                        private final static String modalItemBasicFieldName = "modal_id";
    
        private final static String modalItemBasicKeyName = "items";
    
                        
    

    
    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                                             modalItemBasicKeyName
                                             };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {

        
                                                
            if (field.compareTo(modalItemBasicKeyName) == 0) {
                FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(modalItemBasicFieldName);
//            _field.setItemClassName(ModalItemBasic.class);
//            _field.setItemMapper(modalItemBasicMapper);
            
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


