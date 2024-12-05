
package com.jfeat.module.apicode.api;

import cn.hutool.core.lang.Assert;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.module.apicode.services.domain.dao.QueryFieldModelDao;
import com.jfeat.module.apicode.services.gen.crud.model.ApiTableModelFieldModel;
import com.jfeat.module.apicode.services.gen.persistence.model.FieldModel;

import org.springframework.web.bind.annotation.PatchMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.module.apicode.services.domain.dao.QueryApiTableModelFieldDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.module.apicode.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.module.apicode.services.domain.service.*;
import com.jfeat.module.apicode.services.domain.model.ApiTableModelFieldRecord;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModelField;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * api
 * </p>
 *
 * @author Code generator
 * @since 2023-11-22
 */
@RestController
@Api("ApiTableModelField")
@RequestMapping("/api/lc/apiTableModelField/apiTableModelFields")
public class ApiTableModelFieldEndpoint {

    @Resource
    ApiTableModelFieldService apiTableModelFieldService;

    @Resource
    QueryApiTableModelFieldDao queryApiTableModelFieldDao;

    @Resource
    FieldModelService fieldModelService;

    @Resource
    QueryFieldModelDao queryFieldModelDao;


    @BusinessLog(name = "ApiTableModelField", value = "create ApiTableModelField")
    @Permission(ApiTableModelFieldPermission.APITABLEMODELFIELD_NEW)
    @PostMapping
    @ApiOperation(value = "新建 ApiTableModelField", response = ApiTableModelField.class)
    public Tip createApiTableModelField(@RequestBody ApiTableModelField entity) {
        Integer affected = 0;
        Assert.notBlank(entity.getFieldName(), "fieldName is empty !");
//        Assert.notNull(entity.getFieldModelId(), "fieldModelId is null");
//        Assert.isTrue(entity.getFieldModelId()!=0, "fieldModelId is 0");

        try {

            affected = apiTableModelFieldService.createMaster(entity);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    /*
    * id 为 tableModelId
    * */
    @PostMapping("/{id}")
    @ApiOperation(value = "根据标准字段ID, 新增表模型字段", response = ApiTableModelFieldModel.class)
    public Tip createApiTableModelFieldById(
            @PathVariable Long id,
            @RequestBody JSONObject bodyObj
    ) {

        Integer affected = 0;
        //标准字段ID
        Long standardFieldId = bodyObj.getLong("standardFieldId");
        FieldModel fieldModel = fieldModelService.queryMasterModel(queryFieldModelDao, standardFieldId);

        ApiTableModelFieldModel requestBody = new ApiTableModelFieldModel();
        requestBody.setTableModelId(id);
        requestBody.setFieldLength(fieldModel.getFieldLength());
        requestBody.setFieldModelId(fieldModel.getId());
        requestBody.setComments(fieldModel.getComments());
        requestBody.setDataType(fieldModel.getDataType());
        requestBody.setDefaultFieldName(fieldModel.getModelName());
        requestBody.setFieldName(fieldModel.getOptionalFieldName());
        requestBody.setIsNotNull(fieldModel.getIsNotNull());
        requestBody.setIsUnique(fieldModel.getIsUnique());

        try {

            affected = apiTableModelFieldService.createMaster(requestBody);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @Permission(ApiTableModelFieldPermission.APITABLEMODELFIELD_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 ApiTableModelField", response = ApiTableModelField.class)
    public Tip getApiTableModelField(@PathVariable Long id) {
        ApiTableModelFieldModel apiTableModelFieldModel = apiTableModelFieldService.queryMasterModel(queryApiTableModelFieldDao, id);
        return SuccessTip.create(apiTableModelFieldModel);
    }

    @BusinessLog(name = "ApiTableModelField", value = "update ApiTableModelField")
    @Permission(ApiTableModelFieldPermission.APITABLEMODELFIELD_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 ApiTableModelField", response = ApiTableModelField.class)
    public Tip updateApiTableModelField(@PathVariable Long id, @RequestBody ApiTableModelField entity) {
        entity.setId(id);
        return SuccessTip.create(apiTableModelFieldService.updateMaster(entity));
    }


    @BusinessLog(name = "ApiTableModelField", value = "patch update ApiTableModelField")
    @Permission(ApiTableModelFieldPermission.APITABLEMODELFIELD_EDIT)
    @PatchMapping("/{id}")
    @ApiOperation(value = "局部修改 ApiTableModelField", response = ApiTableModelField.class)
    public Tip patchUpdateApiTableModelField(@PathVariable Long id, @RequestBody ApiTableModelField entity) {
        entity.setId(id);
        return SuccessTip.create(apiTableModelFieldService.patchMaster(entity));
    }


    @BusinessLog(name = "ApiTableModelField", value = "delete ApiTableModelField")
    @Permission(ApiTableModelFieldPermission.APITABLEMODELFIELD_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 ApiTableModelField")
    public Tip deleteApiTableModelField(@PathVariable Long id) {
        return SuccessTip.create(apiTableModelFieldService.deleteMaster(id));
    }

    @Permission(ApiTableModelFieldPermission.APITABLEMODELFIELD_VIEW)
    @ApiOperation(value = "ApiTableModelField 列表信息", response = ApiTableModelFieldRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "tableModelId", dataType = "Long"),
            @ApiImplicitParam(name = "fieldModelId", dataType = "Long"),
            @ApiImplicitParam(name = "fieldName", dataType = "String"),
            @ApiImplicitParam(name = "defaultValue", dataType = "String"),
            @ApiImplicitParam(name = "isNotNull", dataType = "Integer"),
            @ApiImplicitParam(name = "isUnique", dataType = "Integer"),
            @ApiImplicitParam(name = "dataType", dataType = "String"),
            @ApiImplicitParam(name = "fieldLength", dataType = "Integer"),
            @ApiImplicitParam(name = "fieldFloatLength", dataType = "Integer"),
            @ApiImplicitParam(name = "comments", dataType = "String"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryApiTableModelFieldPage(Page<ApiTableModelFieldRecord> page,
                                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                           // for tag feature query
                                           @RequestParam(name = "tag", required = false) String tag,
                                           // end tag
                                           @RequestParam(name = "search", required = false) String search,

                                           @RequestParam(name = "tableModelId", required = false) Long tableModelId,

                                           @RequestParam(name = "fieldModelId", required = false) Long fieldModelId,

                                           @RequestParam(name = "fieldName", required = false) String fieldName,

                                           @RequestParam(name = "defaultValue", required = false) String defaultValue,

                                           @RequestParam(name = "isNotNull", required = false) Integer isNotNull,

                                           @RequestParam(name = "isUnique", required = false) Integer isUnique,

                                           @RequestParam(name = "dataType", required = false) String dataType,

                                           @RequestParam(name = "fieldLength", required = false) Integer fieldLength,

                                           @RequestParam(name = "fieldFloatLength", required = false) Integer fieldFloatLength,

                                           @RequestParam(name = "comments", required = false) String comments,
                                           @RequestParam(name = "orderBy", required = false) String orderBy,
                                           @RequestParam(name = "sort", required = false) String sort) {

        if (orderBy != null && orderBy.length() > 0) {
            if (sort != null && sort.length() > 0) {
                String sortPattern = "(ASC|DESC|asc|desc)";
                if (!sort.matches(sortPattern)) {
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            } else {
                sort = "ASC";
            }
            orderBy = "`" + orderBy + "`" + " " + sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        ApiTableModelFieldRecord record = new ApiTableModelFieldRecord();
        record.setTableModelId(tableModelId);
        record.setFieldModelId(fieldModelId);
        record.setFieldName(fieldName);
        record.setDefaultValue(defaultValue);
        record.setIsNotNull(isNotNull);
        record.setIsUnique(isUnique);
        record.setDataType(dataType);
        record.setFieldLength(fieldLength);
        record.setFieldFloatLength(fieldFloatLength);
        record.setComments(comments);


        List<ApiTableModelFieldRecord> apiTableModelFieldPage = queryApiTableModelFieldDao.findApiTableModelFieldPage(page, record, tag, search, orderBy, null, null);


        page.setRecords(apiTableModelFieldPage);

        return SuccessTip.create(page);
    }
}

