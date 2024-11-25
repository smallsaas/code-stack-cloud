
package com.jfeat.module.apicode.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

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
import com.jfeat.module.apicode.services.domain.dao.QueryApiSubTableModelDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.module.apicode.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.module.apicode.services.domain.service.*;
import com.jfeat.module.apicode.services.domain.model.ApiSubTableModelRecord;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiSubTableModel;

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
@Api("ApiSubTableModel")
@RequestMapping("/api/apicode/apiSubTableModel/apiSubTableModels")
public class ApiSubTableModelEndpoint {

    @Resource
    ApiSubTableModelService apiSubTableModelService;

    @Resource
    QueryApiSubTableModelDao queryApiSubTableModelDao;


    @BusinessLog(name = "ApiSubTableModel", value = "create ApiSubTableModel")
    @Permission(ApiSubTableModelPermission.APISUBTABLEMODEL_NEW)
    @PostMapping
    @ApiOperation(value = "新建 ApiSubTableModel", response = ApiSubTableModel.class)
    public Tip createApiSubTableModel(@RequestBody ApiSubTableModel entity) {
        Integer affected = 0;
        try {
            affected = apiSubTableModelService.createMaster(entity);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @Permission(ApiSubTableModelPermission.APISUBTABLEMODEL_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 ApiSubTableModel", response = ApiSubTableModel.class)
    public Tip getApiSubTableModel(@PathVariable Long id) {
        return SuccessTip.create(apiSubTableModelService.queryMasterModel(queryApiSubTableModelDao, id));
    }

    @BusinessLog(name = "ApiSubTableModel", value = "update ApiSubTableModel")
    @Permission(ApiSubTableModelPermission.APISUBTABLEMODEL_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 ApiSubTableModel", response = ApiSubTableModel.class)
    public Tip updateApiSubTableModel(@PathVariable Long id, @RequestBody ApiSubTableModel entity) {
        entity.setId(id);
        return SuccessTip.create(apiSubTableModelService.updateMaster(entity));
    }


    @BusinessLog(name = "ApiSubTableModel", value = "patch update ApiSubTableModel")
    @Permission(ApiSubTableModelPermission.APISUBTABLEMODEL_EDIT)
    @PatchMapping("/{id}")
    @ApiOperation(value = "局部修改 ApiSubTableModel", response = ApiSubTableModel.class)
    public Tip patchUpdateApiSubTableModel(@PathVariable Long id, @RequestBody ApiSubTableModel entity) {
        entity.setId(id);
        return SuccessTip.create(apiSubTableModelService.patchMaster(entity));
    }


    @BusinessLog(name = "ApiSubTableModel", value = "delete ApiSubTableModel")
    @Permission(ApiSubTableModelPermission.APISUBTABLEMODEL_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 ApiSubTableModel")
    public Tip deleteApiSubTableModel(@PathVariable Long id) {
        return SuccessTip.create(apiSubTableModelService.deleteMaster(id));
    }

    @Permission(ApiSubTableModelPermission.APISUBTABLEMODEL_VIEW)
    @ApiOperation(value = "ApiSubTableModel 列表信息", response = ApiSubTableModelRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "tableModelId", dataType = "Long"),
            @ApiImplicitParam(name = "subTableId", dataType = "Long"),
            @ApiImplicitParam(name = "subTableModelName", dataType = "String"),
            @ApiImplicitParam(name = "subTableModelName", dataType = "String"),
            @ApiImplicitParam(name = "subTableName", dataType = "String"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryApiSubTableModelPage(Page<ApiSubTableModelRecord> page,
                                         @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                         // for tag feature query
                                         @RequestParam(name = "tag", required = false) String tag,
                                         // end tag
                                         @RequestParam(name = "search", required = false) String search,

                                         @RequestParam(name = "tableModelId", required = false) Long tableModelId,

                                         @RequestParam(name = "subTableId", required = false) Long subTableId,

                                         @RequestParam(name = "subTableModelName", required = false) String subTableModelName,
                                         @RequestParam(name = "subTableName", required = false) String subTableName,
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

        ApiSubTableModelRecord record = new ApiSubTableModelRecord();
        record.setTableModelId(tableModelId);
        record.setSubTableId(subTableId);
        record.setSubTableName(subTableModelName);
        record.setSubTableName(subTableName);

        List<ApiSubTableModelRecord> apiSubTableModelPage = queryApiSubTableModelDao.findApiSubTableModelPage(page, record, tag, search, orderBy, null, null);


        page.setRecords(apiSubTableModelPage);

        return SuccessTip.create(page);
    }
}

