package com.jfeat.module.apicode.services.domain.service.impl;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.crud.plus.CRUD;
import com.jfeat.module.apicode.services.domain.dao.QueryApiTableModelFieldDao;
import com.jfeat.module.apicode.services.domain.model.FieldModelRecord;
import com.jfeat.module.apicode.services.domain.service.ApiTableModelFieldService;
import com.jfeat.module.apicode.services.gen.crud.service.impl.CRUDApiTableModelFieldServiceImpl;
import com.jfeat.module.apicode.services.gen.persistence.dao.FieldModelMapper;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModelField;
import com.jfeat.module.apicode.services.gen.persistence.model.FieldModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("apiTableModelFieldService")
public class ApiTableModelFieldServiceImpl extends CRUDApiTableModelFieldServiceImpl implements ApiTableModelFieldService {

    @Resource
    FieldModelMapper fieldModelMapper;

    @Resource
    QueryApiTableModelFieldDao queryApiTableModelFieldDao;

    @Override
    protected String entityName() {
        return "ApiTableModelField";
    }

    @Override
    public Integer createMaster(ApiTableModelField entity) {
        Integer affected = 0;

        // check default filed name
        if(StringUtils.isNotBlank(entity.getDefaultFieldName())){
            FieldModel fieldModel = new FieldModel();
            fieldModel.setModelName(entity.getDefaultFieldName());

            var one = fieldModelMapper.selectOne(new QueryWrapper<>(fieldModel));
            Assert.notNull(one, "Bad Request: " + entity.getDefaultFieldName() + " invalid defaultFieldName !");

            // ignore id
            one.setId(null);
            entity = CRUD.copyFromIfEmpty(entity, one);

            FieldModelRecord fieldModelRecord = queryApiTableModelFieldDao.findFieldModeDetail(entity.getDefaultFieldName());
            entity.setFieldModelId(fieldModelRecord.getId());
            Assert.notNull(entity.getFieldModelId(), "fieldModelId is null");
            Assert.isTrue(entity.getFieldModelId()!=0, "fieldModelId is 0");

        }else if(Objects.nonNull(entity.getFieldModelId()) && entity.getFieldModelId()>0){
            var one = fieldModelMapper.selectById(entity.getTableModelId());
            Assert.notNull(one, "Bad Request: " + entity.getFieldModelId() + " invalid fieldModelId !");

            one.setId(null); // do not copy id into
            entity = CRUD.copyFromIfEmpty(entity, one);
        }

        affected += super.createMaster(entity);

        return affected;
    }

    /**
     * 获取字段列表
     *
     * @param tableModuleId 表id
     * @return 字段列表
     */
    @Override
    public List<ApiTableModelField> listField(Long tableModuleId) {
        LambdaQueryWrapper<ApiTableModelField> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ApiTableModelField::getTableModelId, tableModuleId);
        return queryApiTableModelFieldDao.selectList(queryWrapper);
    }
}