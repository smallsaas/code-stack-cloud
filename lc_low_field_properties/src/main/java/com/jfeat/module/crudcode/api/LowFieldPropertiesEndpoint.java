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
import com.jfeat.module.crudcode.services.domain.dao.QueryLowFieldPropertiesDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.module.crudcode.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.module.crudcode.services.domain.service.*;
import com.jfeat.module.crudcode.services.domain.model.LowFieldPropertiesRecord;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowFieldProperties;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * api
 * </p>
 *
 * @author Code generator
 * @since 2021-08-31
 */
@RestController

@Api("LowFieldProperties")
@RequestMapping("/api/pageconfig/lowFieldProperties/lowFieldPropertieses")
public class LowFieldPropertiesEndpoint {

    @Resource
    LowFieldPropertiesService lowFieldPropertiesService;


    @Resource
    QueryLowFieldPropertiesDao queryLowFieldPropertiesDao;

    @BusinessLog(name = "LowFieldProperties", value = "create LowFieldProperties")
    @Permission(LowFieldPropertiesPermission.LOWFIELDPROPERTIES_NEW)
    @PostMapping
    @ApiOperation(value = "新建 LowFieldProperties", response = LowFieldProperties.class)
    public Tip createLowFieldProperties(@RequestBody LowFieldProperties entity) {

        Integer affected = 0;
        try {
            affected = lowFieldPropertiesService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @Permission(LowFieldPropertiesPermission.LOWFIELDPROPERTIES_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 LowFieldProperties", response = LowFieldProperties.class)
    public Tip getLowFieldProperties(@PathVariable Long id) {
        return SuccessTip.create(lowFieldPropertiesService.queryMasterModel(queryLowFieldPropertiesDao, id));
    }

    @BusinessLog(name = "LowFieldProperties", value = "update LowFieldProperties")
    @Permission(LowFieldPropertiesPermission.LOWFIELDPROPERTIES_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 LowFieldProperties", response = LowFieldProperties.class)
    public Tip updateLowFieldProperties(@PathVariable Long id, @RequestBody LowFieldProperties entity) {
        entity.setId(id);
        return SuccessTip.create(lowFieldPropertiesService.updateMaster(entity));
    }

    @BusinessLog(name = "LowFieldProperties", value = "delete LowFieldProperties")
    @Permission(LowFieldPropertiesPermission.LOWFIELDPROPERTIES_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 LowFieldProperties")
    public Tip deleteLowFieldProperties(@PathVariable Long id) {
        return SuccessTip.create(lowFieldPropertiesService.deleteMaster(id));
    }

    @Permission(LowFieldPropertiesPermission.LOWFIELDPROPERTIES_VIEW)
    @ApiOperation(value = "LowFieldProperties 列表信息", response = LowFieldPropertiesRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "fieldItemName", dataType = "String"),
            @ApiImplicitParam(name = "propertyName", dataType = "String"),
            @ApiImplicitParam(name = "propertyValue", dataType = "String"),
            @ApiImplicitParam(name = "scope", dataType = "String"),
            @ApiImplicitParam(name = "fieldId", dataType = "Integer"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryLowFieldPropertiesPage(Page<LowFieldPropertiesRecord> page,
                                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                           @RequestParam(name = "search", required = false) String search,
                                           @RequestParam(name = "id", required = false) Long id,

                                           @RequestParam(name = "fieldItemName", required = false) String fieldItemName,

                                           @RequestParam(name = "propertyName", required = false) String propertyName,

                                           @RequestParam(name = "propertyValue", required = false) String propertyValue,

                                           @RequestParam(name = "scope", required = false) String scope,

                                           @RequestParam(name = "fieldId", required = false) Integer fieldId,
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

        LowFieldPropertiesRecord record = new LowFieldPropertiesRecord();
        record.setId(id);
        record.setFieldItemName(fieldItemName);
        record.setPropertyName(propertyName);
        record.setPropertyValue(propertyValue);
        record.setScope(scope);
        record.setFieldId(fieldId);


        List<LowFieldPropertiesRecord> lowFieldPropertiesPage = queryLowFieldPropertiesDao.findLowFieldPropertiesPage(page, record, search, orderBy, null, null);

        page.setRecords(lowFieldPropertiesPage);

        return SuccessTip.create(page);
    }
}
