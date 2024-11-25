package com.jfeat.module.crudcode.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowOperations;
import com.jfeat.module.crudcode.services.gen.persistence.dao.LowOperationsMapper;
//import com.jfeat.module.crudcode.services.gen.persistence.dao.ModalItemBasicOMapper;
//import com.jfeat.module.crudcode.services.gen.persistence.model.ModalItemBasicO;
    
    import com.jfeat.module.crudcode.services.gen.crud.service.CRUDLowOperationsOverModelService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import com.jfeat.module.crudcode.services.gen.crud.model.LowOperationsModel;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDLowOperationsOverModelService
 * @author Code generator
 * @since 2021-10-11
 */

@Service
public class CRUDLowOperationsOverModelServiceImpl  extends CRUDServiceOverModelImpl<LowOperations,LowOperationsModel> implements CRUDLowOperationsOverModelService {









    @Resource
    protected LowOperationsMapper lowOperationsMapper;

    
    @Override
    protected BaseMapper<LowOperations> getMasterMapper() {
        return lowOperationsMapper;
    }

    @Override
    protected Class<LowOperations> masterClassName() {
        return LowOperations.class;
    }

    @Override
    protected Class<LowOperationsModel> modelClassName() {
        return LowOperationsModel.class;
    }



    
//    @Resource
//    private ModalItemBasicOMapper modalItemBasicOMapper;

                        private final static String modalItemBasicOFieldName = "modal_id";
    
        private final static String modalItemBasicOKeyName = "items";
    
                        
    

    
    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                                             modalItemBasicOKeyName
                                             };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {

        
                                                
            if (field.compareTo(modalItemBasicOKeyName) == 0) {
                FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(modalItemBasicOFieldName);
//            _field.setItemClassName(ModalItemBasicO.class);
//            _field.setItemMapper(modalItemBasicOMapper);
            
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


