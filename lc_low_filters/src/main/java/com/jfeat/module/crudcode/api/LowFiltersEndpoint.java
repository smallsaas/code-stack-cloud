package com.jfeat.module.crudcode.api;


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
import com.jfeat.module.crudcode.services.domain.dao.QueryLowFiltersDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.module.crudcode.api.permission.*;
import com.jfeat.am.common.annotation.Permission;


import com.jfeat.module.crudcode.services.domain.service.*;
import com.jfeat.module.crudcode.services.domain.model.LowFiltersRecord;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowFilters;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code generator
 * @since 2021-09-06
 */
@RestController

@Api("LowFilters")
@RequestMapping("/api/pageconfig/lowFilters/lowFilterses")
public class LowFiltersEndpoint {

    @Resource
    LowFiltersService lowFiltersService;


    @Resource
    QueryLowFiltersDao queryLowFiltersDao;

    @BusinessLog(name = "LowFilters", value = "create LowFilters")
    @Permission(LowFiltersPermission.LOWFILTERS_NEW)
    @PostMapping
    @ApiOperation(value = "新建 LowFilters", response = LowFilters.class)
    public Tip createLowFilters(@RequestBody LowFilters entity) {

        Integer affected = 0;
        try {
            affected = lowFiltersService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @Permission(LowFiltersPermission.LOWFILTERS_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 LowFilters", response = LowFilters.class)
    public Tip getLowFilters(@PathVariable Long id) {
        return SuccessTip.create(lowFiltersService.queryMasterModel(queryLowFiltersDao, id));
    }

    @BusinessLog(name = "LowFilters", value = "update LowFilters")
    @Permission(LowFiltersPermission.LOWFILTERS_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 LowFilters", response = LowFilters.class)
    public Tip updateLowFilters(@PathVariable Long id, @RequestBody LowFilters entity) {
        entity.setId(id);
        return SuccessTip.create(lowFiltersService.updateMaster(entity));
    }

    @BusinessLog(name = "LowFilters", value = "delete LowFilters")
    @Permission(LowFiltersPermission.LOWFILTERS_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 LowFilters")
    public Tip deleteLowFilters(@PathVariable Long id) {
        return SuccessTip.create(lowFiltersService.deleteMaster(id));
    }

    @Permission(LowFiltersPermission.LOWFILTERS_VIEW)
    @ApiOperation(value = "LowFilters 列表信息", response = LowFiltersRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "contentLayout", dataType = "String"),
            @ApiImplicitParam(name = "searchFields", dataType = "String"),
            @ApiImplicitParam(name = "defaultSearchHint", dataType = "String"),
            @ApiImplicitParam(name = "pageId", dataType = "Integer"),
            @ApiImplicitParam(name = "fieldName", dataType = "String"),
            @ApiImplicitParam(name = "fieldType", dataType = "String"),
            @ApiImplicitParam(name = "fieldTitle", dataType = "String"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryLowFiltersPage(Page<LowFiltersRecord> page,
                                   @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "search", required = false) String search,
                                   @RequestParam(name = "id", required = false) Long id,

                                   @RequestParam(name = "contentLayout", required = false) String contentLayout,

                                   @RequestParam(name = "searchFields", required = false) String searchFields,

                                   @RequestParam(name = "defaultSearchHint", required = false) String defaultSearchHint,

                                   @RequestParam(name = "pageId", required = false) Integer pageId,

                                   @RequestParam(name = "fieldName", required = false) String fieldName,

                                   @RequestParam(name = "fieldType", required = false) String fieldType,

                                   @RequestParam(name = "fieldTitle", required = false) String fieldTitle,
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

        LowFiltersRecord record = new LowFiltersRecord();
        record.setId(id);
        record.setContentLayout(contentLayout);
        record.setSearchFields(searchFields);
        record.setDefaultSearchHint(defaultSearchHint);
        record.setPageId(pageId);
        record.setFieldName(fieldName);
        record.setFieldType(fieldType);
        record.setFieldTitle(fieldTitle);


        List<LowFiltersRecord> lowFiltersPage = queryLowFiltersDao.findLowFiltersPage(page, record, search, orderBy, null, null);

        page.setRecords(lowFiltersPage);

        return SuccessTip.create(page);
    }
}
