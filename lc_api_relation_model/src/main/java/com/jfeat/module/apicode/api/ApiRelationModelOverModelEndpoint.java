
package com.jfeat.module.apicode.api;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.crud.plus.CRUD;
import com.jfeat.crud.plus.META;
import com.jfeat.am.core.jwt.JWTKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.module.apicode.services.domain.dao.QueryApiRelationModelItemDao;
import com.jfeat.module.apicode.services.gen.crud.model.ApiRelationModelItemModel;
import com.jfeat.module.apicode.services.gen.persistence.model.ApiRelationModelItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.plus.CRUDObject;
import com.jfeat.crud.plus.DefaultFilterResult;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.JSONArray;
import org.springframework.web.bind.annotation.PatchMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
import com.jfeat.module.apicode.services.domain.dao.QueryApiRelationModelDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.module.apicode.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.module.apicode.services.domain.service.*;
import com.jfeat.module.apicode.services.domain.model.ApiRelationModelRecord;
import com.jfeat.module.apicode.services.gen.crud.model.ApiRelationModelModel;
import com.jfeat.module.apicode.services.gen.persistence.model.ApiRelationModel;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * api
 * </p>
 *
 * @author Code generator
 * @since 2023-12-04
 */
@RestController
@Api("ApiRelationModel")
@RequestMapping("/api/lc/apiRelationModel/apiRelationModels")
public class ApiRelationModelOverModelEndpoint {

    @Resource
    ApiRelationModelOverModelService apiRelationModelOverModelService;

    @Resource
    QueryApiRelationModelDao queryApiRelationModelDao;


    // 要查询[从表]关联数据，取消下行注释
    @Resource
    QueryApiRelationModelItemDao queryApiRelationModelItemDao;

    @BusinessLog(name = "ApiRelationModel", value = "create ApiRelationModel")
    @Permission(ApiRelationModelPermission.APIRELATIONMODEL_NEW)
    @PostMapping
    @ApiOperation(value = "新建 ApiRelationModel", response = ApiRelationModelModel.class)
    public Tip createApiRelationModel(@RequestBody ApiRelationModelModel entity) {
        var supportedRelationship = new String[]{"onemany", "manymany", "category"};
        CRUD.Assert_matchOptions(supportedRelationship, entity.getRelationship());

        // assert items
        if(Objects.nonNull(entity.getRelations())) {
            var supportedRelationTypes = new String[]{"master","slave","peer","relation","category"};
            entity.getRelations().stream().forEach(it->{
                CRUD.Assert_matchOptions(supportedRelationTypes, it.getRelationType());
            });
        }


        Integer affected = 0;
        try {
            var filterResult = new DefaultFilterResult<ApiRelationModel>();
            affected = apiRelationModelOverModelService.createMaster(entity, filterResult, null, null);
            if (affected > 0) {
                return SuccessTip.create(filterResult.result());
            }
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "ApiRelationModel", value = "查看 ApiRelationModelModel")
    @Permission(ApiRelationModelPermission.APIRELATIONMODEL_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 ApiRelationModel", response = ApiRelationModelModel.class)
    public Tip getApiRelationModel(@PathVariable Long id) {
        CRUDObject<ApiRelationModelModel> entity = apiRelationModelOverModelService
                .registerQueryMasterDao(queryApiRelationModelDao)
                // 要查询[从表]关联数据，取消下行注释
                .registerQuerySlaveModelListDao(ApiRelationModelItem.class, queryApiRelationModelItemDao)
                //.retrieveMaster(id,null,null,null);
                .retrieveMasterModel(id);

//        // convert bindingDataJsonString to association
//        var model = entity.toJSONObject();
//        var relations = model.getJSONArray(CRUD.ITEMS);
//        for(var r : relations){
//            var R = (JSONObject)r;
//            var jsonString = R.getString(ApiRelationModelItem.MULTI_RELATION_DATA_JSON);
//            if(StringUtils.isNotBlank(jsonString)){
//                var association = ApiRelationModelItemModel.associationOf(jsonString);
//                R.put("association", association);
//            }
//
//            // get fields from ApiRelationModelItem.tableModelId
//        }

        // sample query for registerQueryMasterDao
        // e.g. <select id="queryMasterModel" resultType="PlanModel">
        //       SELECT t_plan_model.*, t_org.name as orgName
        //       FROM t_plan_model
        //       LEFT JOIN t_org ON t_org.id==t_plan_model.org_id
        //       WHERE t_plan_model.id=#{id}
        //       GROUP BY t_plan_model.id
        //    </select>

        if (entity != null) {
            return SuccessTip.create(entity.toJSONObject());
        } else {
            return SuccessTip.create();
        }

    }

    @BusinessLog(name = "ApiRelationModel", value = "update ApiRelationModel")
    @Permission(ApiRelationModelPermission.APIRELATIONMODEL_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 ApiRelationModel", response = ApiRelationModelModel.class)
    public Tip updateApiRelationModel(@PathVariable Long id, @RequestBody ApiRelationModelModel entity) {
        var supportedRelationship = new String[]{"onemany", "manymany", "category"};
        CRUD.Assert_matchOptions(supportedRelationship, entity.getRelationship());

        // assert items
        if(Objects.nonNull(entity.getRelations())) {
            var supportedRelationTypes = new String[]{"master","slave","peer","relation","category"};
            entity.getRelations().stream().forEach(it->{
                CRUD.Assert_matchOptions(supportedRelationTypes, it.getRelationType());
            });
        }

        // assert items
        if(Objects.nonNull(entity.getRelations())) {
            var supportedRelationTypes = new String[]{"master","slave","peer","relation","category"};
            entity.getRelations().stream().forEach(it->{
                CRUD.Assert_matchOptions(supportedRelationTypes, it.getRelationType());
            });
        }


        entity.setId(id);
        // use update flags
        int newOptions = META.UPDATE_CASCADING_DELETION_FLAG;  //default to delete not exist items
        // newOptions = FlagUtil.setFlag(newOptions, META.UPDATE_ALL_COLUMNS_FLAG);

        return SuccessTip.create(apiRelationModelOverModelService.updateMaster(entity, null, null, null, newOptions));
    }


    @BusinessLog(name = "ApiRelationModel", value = "patch update ApiRelationModel")
    @Permission(ApiRelationModelPermission.APIRELATIONMODEL_EDIT)
    @PatchMapping("/{id}")
    @ApiOperation(value = "局部修改 ApiRelationModel", response = ApiRelationModel.class)
    public Tip patchUpdateApiRelationModel(@PathVariable Long id, @RequestBody ApiRelationModel entity) {
        entity.setId(id);
        return SuccessTip.create(apiRelationModelOverModelService.patchMaster(entity));
    }


    @BusinessLog(name = "ApiRelationModel", value = "delete ApiRelationModel")
    @Permission(ApiRelationModelPermission.APIRELATIONMODEL_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 ApiRelationModel")
    public Tip deleteApiRelationModel(@PathVariable Long id) {
        return SuccessTip.create(apiRelationModelOverModelService.deleteMaster(id));
    }

    @Permission(ApiRelationModelPermission.APIRELATIONMODEL_VIEW)
    @ApiOperation(value = "ApiRelationModel 列表信息", response = ApiRelationModelRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "relationship", dataType = "String"),
            @ApiImplicitParam(name = "modelName", dataType = "String"),
            @ApiImplicitParam(name = "name", dataType = "String"),
            @ApiImplicitParam(name = "notes", dataType = "String"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryApiRelationModelPage(Page<ApiRelationModelRecord> page,
                                         @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                         // for tag feature query
                                         @RequestParam(name = "tag", required = false) String tag,
                                         // end tag
                                         @RequestParam(name = "search", required = false) String search,

                                         @RequestParam(name = "relationship", required = false) String relationship,

                                         @RequestParam(name = "modelName", required = false) String modelName,

                                         @RequestParam(name = "name", required = false) String name,

                                         @RequestParam(name = "notes", required = false) String notes,
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

        ApiRelationModelRecord record = new ApiRelationModelRecord();
        record.setRelationship(relationship);
        record.setModelName(modelName);
        record.setName(name);
        record.setNotes(notes);

        List<ApiRelationModelRecord> apiRelationModelPage = queryApiRelationModelDao.findApiRelationModelPage(page, record, tag, search, orderBy, null, null);

        page.setRecords(apiRelationModelPage);

        return SuccessTip.create(page);
    }
}

