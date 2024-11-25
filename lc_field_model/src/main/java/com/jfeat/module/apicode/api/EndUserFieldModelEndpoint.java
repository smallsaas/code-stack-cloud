
package com.jfeat.module.apicode.api;


import com.jfeat.crud.base.annotation.BusinessLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.module.apicode.services.domain.dao.QueryFieldModelDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.module.apicode.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.module.apicode.services.domain.service.*;
import com.jfeat.module.apicode.services.domain.model.FieldModelRecord;
import com.jfeat.module.apicode.services.gen.persistence.model.FieldModel;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * api
 * </p>
 *
 * @author Code generator
 * @since 2021-10-20
 */
@RestController
@Api("FieldModel")
@RequestMapping("/api/u/fieldModel/fieldModels")
public class EndUserFieldModelEndpoint {

    @Resource
    FieldModelService fieldModelService;

    @Resource
    QueryFieldModelDao queryFieldModelDao;


    @BusinessLog(name = "FieldModel", value = "create FieldModel")
    @Permission(FieldModelPermission.FIELDMODEL_NEW)
    @PostMapping
    @ApiOperation(value = "新建 FieldModel", response = FieldModel.class)
    public Tip createFieldModel(@RequestBody FieldModel entity) {
        Integer affected = 0;
        String[] Type = new String[]{"long","smallint","int","decimal","varchar"};
        List<String> list = Arrays.asList(Type);
        Assert.isTrue(list.contains(entity.getDataType()),"输入类型错误,请输入正确类型：字母或数字");
        try {
            affected = fieldModelService.createMaster(entity);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @Permission(FieldModelPermission.FIELDMODEL_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 FieldModel", response = FieldModel.class)
    public Tip getFieldModel(@PathVariable Long id) {
        return SuccessTip.create(fieldModelService.queryMasterModel(queryFieldModelDao, id));
    }

    @BusinessLog(name = "FieldModel", value = "update FieldModel")
    @Permission(FieldModelPermission.FIELDMODEL_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 FieldModel", response = FieldModel.class)
    public Tip updateFieldModel(@PathVariable Long id, @RequestBody FieldModel entity) {
        entity.setId(id);
        return SuccessTip.create(fieldModelService.updateMaster(entity));
    }

    @BusinessLog(name = "FieldModel", value = "delete FieldModel")
    @Permission(FieldModelPermission.FIELDMODEL_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 FieldModel")
    public Tip deleteFieldModel(@PathVariable Long id) {
        return SuccessTip.create(fieldModelService.deleteMaster(id));
    }

    @Permission(FieldModelPermission.FIELDMODEL_VIEW)
    @ApiOperation(value = "FieldModel 列表信息", response = FieldModelRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "modelName", dataType = "String"),
            @ApiImplicitParam(name = "modelLabel", dataType = "String"),
            @ApiImplicitParam(name = "defaultValue", dataType = "String"),
            @ApiImplicitParam(name = "isNotNull", dataType = "Integer"),
            @ApiImplicitParam(name = "isUnique", dataType = "Integer"),
            @ApiImplicitParam(name = "defaultFieldName", dataType = "String"),
            @ApiImplicitParam(name = "optionalFieldName", dataType = "String"),
            @ApiImplicitParam(name = "fieldType", dataType = "String"),
            @ApiImplicitParam(name = "fieldLength", dataType = "Integer"),
            @ApiImplicitParam(name = "fieldFloatLength", dataType = "Integer"),
            @ApiImplicitParam(name = "comments", dataType = "String"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryFieldModelPage(Page<FieldModelRecord> page,
                                   @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "search", required = false) String search,
                                   @RequestParam(name = "id", required = false) Long id,

                                   @RequestParam(name = "modelName", required = false) String modelName,

                                   @RequestParam(name = "name", required = false) String name,

                                   @RequestParam(name = "defaultValue", required = false) String defaultValue,

                                   @RequestParam(name = "isNotNull", required = false) Integer isNotNull,

                                   @RequestParam(name = "isUnique", required = false) Integer isUnique,

                                   @RequestParam(name = "defaultFieldName", required = false) String defaultFieldName,

                                   @RequestParam(name = "optionalFieldName", required = false) String optionalFieldName,

                                   @RequestParam(name = "dataType", required = false) String dataType,

                                   @RequestParam(name = "fieldLength", required = false) Integer fieldLength,

                                   @RequestParam(name = "fieldFloatLength", required = false) Integer fieldFloatLength,

                                   @RequestParam(name = "comments", required = false) String comments,
                                   @RequestParam(name = "orderBy", required = false) String orderBy,
                                   @RequestParam(name = "sort", required = false) String sort) {

        if (orderBy != null && orderBy.length() > 0) {
            if (sort != null && sort.length() > 0) {
                String pattern = "(ASC|DESC|asc|desc)";
                if (!sort.matches(pattern)) {
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            } else {
                sort = "ASC";
            }
            orderBy = "`" + orderBy + "`" + " " + sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        FieldModelRecord record = new FieldModelRecord();
        record.setId(id);
        record.setModelName(modelName);
        record.setName(name);
        record.setDefaultValue(defaultValue);
        record.setIsNotNull(isNotNull);
        record.setIsUnique(isUnique);
//        record.setDefaultFieldName(defaultFieldName);
        record.setOptionalFieldName(optionalFieldName);
        record.setDataType(dataType);
        record.setFieldLength(fieldLength);
        record.setFieldFloatLength(fieldFloatLength);
        record.setComments(comments);


        List<FieldModelRecord> fieldModelPage = queryFieldModelDao.findFieldModelPage(page, record, search, orderBy, null, null);

        page.setRecords(fieldModelPage);

        return SuccessTip.create(page);
    }
}

