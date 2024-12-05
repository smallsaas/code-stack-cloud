
package com.jfeat.module.apicode.api;

import com.jfeat.crud.plus.META;
import com.jfeat.am.core.jwt.JWTKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.jfeat.module.apicode.services.domain.dao.QueryApiRelationModelItemDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.module.apicode.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.module.apicode.services.domain.service.*;
import com.jfeat.module.apicode.services.domain.model.ApiRelationModelItemRecord;
import com.jfeat.module.apicode.services.gen.persistence.model.ApiRelationModelItem;

    import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
/**
 * <p>
 *  api
 * </p>
 *
 * @author Code generator
 * @since 2023-12-04
 */
    @RestController
    @Api("ApiRelationModelItem")
            @RequestMapping("/api/lc/apiRelationModelItem/apiRelationModelItems")
    public class ApiRelationModelItemEndpoint {

    @Resource
    ApiRelationModelItemService apiRelationModelItemService;

    @Resource
    QueryApiRelationModelItemDao queryApiRelationModelItemDao;



    @BusinessLog(name = "ApiRelationModelItem", value = "create ApiRelationModelItem")
    @Permission(ApiRelationModelItemPermission.APIRELATIONMODELITEM_NEW)
    @PostMapping
    @ApiOperation(value = "新建 ApiRelationModelItem", response = ApiRelationModelItem.class)
    public Tip createApiRelationModelItem(@RequestBody ApiRelationModelItem entity) {
        Integer affected = 0;
        try{
                affected = apiRelationModelItemService.createMaster(entity);
            } catch (DuplicateKeyException e){
             throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
}

    @Permission(ApiRelationModelItemPermission.APIRELATIONMODELITEM_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 ApiRelationModelItem", response = ApiRelationModelItem.class)
    public Tip getApiRelationModelItem(@PathVariable Long id) {
                        return SuccessTip.create(apiRelationModelItemService.queryMasterModel(queryApiRelationModelItemDao, id));
            }

    @BusinessLog(name = "ApiRelationModelItem", value = "update ApiRelationModelItem")
    @Permission(ApiRelationModelItemPermission.APIRELATIONMODELITEM_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 ApiRelationModelItem", response = ApiRelationModelItem.class)
    public Tip updateApiRelationModelItem(@PathVariable Long id, @RequestBody ApiRelationModelItem entity) {
        entity.setId(id);
                return SuccessTip.create(apiRelationModelItemService.updateMaster(entity));
            }


    @BusinessLog(name = "ApiRelationModelItem", value = "patch update ApiRelationModelItem")
    @Permission(ApiRelationModelItemPermission.APIRELATIONMODELITEM_EDIT)
    @PatchMapping("/{id}")
        @ApiOperation(value = "局部修改 ApiRelationModelItem", response = ApiRelationModelItem.class)
    public Tip patchUpdateApiRelationModelItem(@PathVariable Long id, @RequestBody ApiRelationModelItem entity){
            entity.setId(id);
        return SuccessTip.create(apiRelationModelItemService.patchMaster(entity));
    }


    @BusinessLog(name = "ApiRelationModelItem", value = "delete ApiRelationModelItem")
    @Permission(ApiRelationModelItemPermission.APIRELATIONMODELITEM_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 ApiRelationModelItem")
    public Tip deleteApiRelationModelItem(@PathVariable Long id) {
            return SuccessTip.create(apiRelationModelItemService.deleteMaster(id));
        }

    @Permission(ApiRelationModelItemPermission.APIRELATIONMODELITEM_VIEW)
    @ApiOperation(value = "ApiRelationModelItem 列表信息", response = ApiRelationModelItemRecord.class)
    @GetMapping
    @ApiImplicitParams({
        @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
        @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
        @ApiImplicitParam(name = "search", dataType = "String"),
                                                                                        @ApiImplicitParam(name = "id", dataType = "Long"),
                                                                                                            @ApiImplicitParam(name = "relationModelId", dataType = "Long"),
                                                                                                            @ApiImplicitParam(name = "tableModelId", dataType = "Long"),
                                                                                            @ApiImplicitParam(name = "dataKey", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "relationType", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "relationData", dataType = "String"),
                                                                                                    @ApiImplicitParam(name = "multi_relationDataJson", dataType = "String"),
                @ApiImplicitParam(name = "orderBy", dataType = "String"),
                @ApiImplicitParam(name = "sort", dataType = "String")
            })
    public Tip queryApiRelationModelItemPage(Page<ApiRelationModelItemRecord> page,
    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    // for tag feature query
    @RequestParam(name = "tag", required = false) String tag,
    // end tag
    @RequestParam(name = "search", required = false) String search,
                                                                                                                                        
                                                                                                                                                    @RequestParam(name = "relationModelId", required = false) Long relationModelId,
                    
                                                                                                                                                    @RequestParam(name = "tableModelId", required = false) Long tableModelId,
                    
                                                                                                                                    @RequestParam(name = "dataKey", required = false) String dataKey,
                    
                                                                                                                                    @RequestParam(name = "relationType", required = false) String relationType,
                    
                                                                                                                                    @RequestParam(name = "relationData", required = false) String relationData,
                    
                                                                                                                                                    @RequestParam(name = "multiRelationDataJson", required = false) String multiRelationDataJson,
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

    ApiRelationModelItemRecord record = new ApiRelationModelItemRecord();
                                                                                                                                                                                        record.setRelationModelId(relationModelId);
                                                                                                                        record.setTableModelId(tableModelId);
                                                                                                                record.setDataKey(dataKey);
                                                                                                                record.setRelationType(relationType);
                                                                                                                record.setRelationData(relationData);
                                                                                                                                record.setMultiRelationDataJson(multiRelationDataJson);
                                                            
    List<ApiRelationModelItemRecord> apiRelationModelItemPage = queryApiRelationModelItemDao.findApiRelationModelItemPage(page, record, tag, search, orderBy, null, null);

            page.setRecords(apiRelationModelItemPage);

        return SuccessTip.create(page);
    }
}

