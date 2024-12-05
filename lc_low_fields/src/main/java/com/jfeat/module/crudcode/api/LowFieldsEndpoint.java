
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
import com.jfeat.module.crudcode.services.domain.dao.QueryLowFieldsDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.module.crudcode.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.module.crudcode.services.domain.service.*;
import com.jfeat.module.crudcode.services.domain.model.LowFieldsRecord;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowFields;

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
 * @since 2021-10-09
 */
@RestController
@Api("LowFields")
@RequestMapping("/api/pageconfig/lowFields/lowFieldses")
public class LowFieldsEndpoint {

    @Resource
    LowFieldsService lowFieldsService;

    @Resource
    QueryLowFieldsDao queryLowFieldsDao;


    @BusinessLog(name = "LowFields", value = "create LowFields")
    @Permission(LowFieldsPermission.LOWFIELDS_NEW)
    @PostMapping
    @ApiOperation(value = "新建 LowFields", response = LowFields.class)
    public Tip createLowFields(@RequestBody LowFields entity) {
        Integer affected = 0;
        try {
            String[] aligns = new String[]{ColumnAlign.Center, ColumnAlign.Left, ColumnAlign.Right};
            List<String> list = Arrays.asList(aligns);
            if (!list.contains(entity.getListColumnAlign())) {
                throw new BusinessException(BusinessCode.BadRequest,"请输入规定的排列方式："+list);
            }
//            List<String> scopes = new ArrayList<>(ListUtil.toList("page","filter","table","edit","add","view"));
//            List<String> fieldScopes = new ArrayList<>(ListUtil.toList(entity.getFieldScopes().split(",")));
//            List<String> error = fieldScopes.stream()
//                    .filter(a-> !scopes.contains(a))
//                    .collect(Collectors.toList());
//            Assert.isTrue(error.size()==0,"请输入正确的字段范围:"+ scopes);
            affected = lowFieldsService.createMaster(entity);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @Permission(LowFieldsPermission.LOWFIELDS_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 LowFields", response = LowFields.class)
    public Tip getLowFields(@PathVariable Long id) {
        return SuccessTip.create(lowFieldsService.queryMasterModel(queryLowFieldsDao, id));
    }

    @BusinessLog(name = "LowFields", value = "update LowFields")
    @Permission(LowFieldsPermission.LOWFIELDS_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 LowFields", response = LowFields.class)
    public Tip updateLowFields(@PathVariable Long id, @RequestBody LowFields entity) {
        entity.setId(id);
        return SuccessTip.create(lowFieldsService.updateMaster(entity));
    }

    @BusinessLog(name = "LowFields", value = "delete LowFields")
    @Permission(LowFieldsPermission.LOWFIELDS_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 LowFields")
    public Tip deleteLowFields(@PathVariable Long id) {
        return SuccessTip.create(lowFieldsService.deleteMaster(id));
    }

    @Permission(LowFieldsPermission.LOWFIELDS_VIEW)
    @ApiOperation(value = "LowFields 列表信息", response = LowFieldsRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "listFontWeight", dataType = "String"),
            @ApiImplicitParam(name = "listFontSize", dataType = "Integer"),
            @ApiImplicitParam(name = "listFontColor", dataType = "String"),
            @ApiImplicitParam(name = "listColumnKey", dataType = "String"),
            @ApiImplicitParam(name = "listColumnName", dataType = "String"),
            @ApiImplicitParam(name = "listColumnType", dataType = "String"),
            @ApiImplicitParam(name = "listColumnLayout", dataType = "String"),
            @ApiImplicitParam(name = "listColumnAlign", dataType = "String"),
            @ApiImplicitParam(name = "listColumnWidth", dataType = "Integer"),
            @ApiImplicitParam(name = "listColumnReference", dataType = "String"),
            @ApiImplicitParam(name = "listColumnFormat", dataType = "String"),
            @ApiImplicitParam(name = "listColumnOptions", dataType = "String"),
            @ApiImplicitParam(name = "list_columnMultiType", dataType = "String"),
            @ApiImplicitParam(name = "list_columnMultiKeys", dataType = "String"),
            @ApiImplicitParam(name = "fieldItemName", dataType = "String"),
            @ApiImplicitParam(name = "fieldBinding", dataType = "String"),
            @ApiImplicitParam(name = "fieldLabel", dataType = "String"),
            @ApiImplicitParam(name = "fieldValueOptions", dataType = "String"),
            @ApiImplicitParam(name = "fieldValueFilter", dataType = "String"),
            @ApiImplicitParam(name = "fieldScopes", dataType = "String"),
            @ApiImplicitParam(name = "fieldScope", dataType = "String"),
            @ApiImplicitParam(name = "formFieldTitle", dataType = "String"),
            @ApiImplicitParam(name = "formFieldHint", dataType = "String"),
            @ApiImplicitParam(name = "formFieldTips", dataType = "String"),
            @ApiImplicitParam(name = "formInputType", dataType = "String"),
            @ApiImplicitParam(name = "formInputOptions", dataType = "String"),
            @ApiImplicitParam(name = "formViewType", dataType = "String"),
            @ApiImplicitParam(name = "formViewOptions", dataType = "String"),
            @ApiImplicitParam(name = "formInputRequired", dataType = "Integer"),
            @ApiImplicitParam(name = "formFieldQuestion", dataType = "String"),
            @ApiImplicitParam(name = "pageId", dataType = "Long"),
            @ApiImplicitParam(name = "listImageWidth", dataType = "Integer"),
            @ApiImplicitParam(name = "listImageHeight", dataType = "Integer"),
            @ApiImplicitParam(name = "inputWidth", dataType = "Integer"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryLowFieldsPage(Page<LowFieldsRecord> page,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                  @RequestParam(name = "search", required = false) String search,
                                  @RequestParam(name = "id", required = false) Long id,

                                  @RequestParam(name = "listFontWeight", required = false) String listFontWeight,

                                  @RequestParam(name = "listFontSize", required = false) Integer listFontSize,

                                  @RequestParam(name = "listFontColor", required = false) String listFontColor,

                                  @RequestParam(name = "listColumnKey", required = false) String listColumnKey,

                                  @RequestParam(name = "listColumnName", required = false) String listColumnName,

                                  @RequestParam(name = "listColumnType", required = false) String listColumnType,

                                  @RequestParam(name = "listColumnLayout", required = false) String listColumnLayout,

                                  @RequestParam(name = "listColumnAlign", required = false) String listColumnAlign,

                                  @RequestParam(name = "listColumnWidth", required = false) Integer listColumnWidth,

                                  @RequestParam(name = "listColumnReference", required = false) String listColumnReference,

                                  @RequestParam(name = "listColumnFormat", required = false) String listColumnFormat,

                                  @RequestParam(name = "listColumnOptions", required = false) String listColumnOptions,

                                  @RequestParam(name = "listColumnMultiType", required = false) String listColumnMultiType,

                                  @RequestParam(name = "listColumnMultiKeys", required = false) String listColumnMultiKeys,

                                  @RequestParam(name = "fieldItemName", required = false) String fieldItemName,

                                  @RequestParam(name = "fieldBinding", required = false) String fieldBinding,

                                  @RequestParam(name = "fieldLabel", required = false) String fieldLabel,

                                  @RequestParam(name = "fieldValueOptions", required = false) String fieldValueOptions,

                                  @RequestParam(name = "fieldValueFilter", required = false) String fieldValueFilter,

                                  @RequestParam(name = "fieldScopes", required = false) String fieldScopes,

                                  @RequestParam(name = "fieldScope", required = false) String fieldScope,

                                  @RequestParam(name = "formFieldTitle", required = false) String formFieldTitle,

                                  @RequestParam(name = "formFieldHint", required = false) String formFieldHint,

                                  @RequestParam(name = "formFieldTips", required = false) String formFieldTips,

                                  @RequestParam(name = "formInputType", required = false) String formInputType,

                                  @RequestParam(name = "formInputOptions", required = false) String formInputOptions,

                                  @RequestParam(name = "formViewType", required = false) String formViewType,

                                  @RequestParam(name = "formViewOptions", required = false) String formViewOptions,

                                  @RequestParam(name = "formInputRequired", required = false) Integer formInputRequired,

                                  @RequestParam(name = "formFieldQuestion", required = false) String formFieldQuestion,

                                  @RequestParam(name = "pageId", required = false) Long pageId,

                                  @RequestParam(name = "listImageWidth", required = false) Integer listImageWidth,

                                  @RequestParam(name = "listImageHeight", required = false) Integer listImageHeight,

                                  @RequestParam(name = "inputWidth", required = false) Integer inputWidth,
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

        LowFieldsRecord record = new LowFieldsRecord();
        record.setId(id);
        record.setListFontWeight(listFontWeight);
        record.setListFontSize(listFontSize);
        record.setListFontColor(listFontColor);
        record.setListColumnKey(listColumnKey);
        record.setListColumnName(listColumnName);
        record.setListColumnType(listColumnType);
        record.setListColumnLayout(listColumnLayout);
        record.setListColumnAlign(listColumnAlign);
        record.setListColumnWidth(listColumnWidth);
        record.setListColumnReference(listColumnReference);
        record.setListColumnFormat(listColumnFormat);
        record.setListColumnOptions(listColumnOptions);
        record.setListColumnMultiType(listColumnMultiType);
        record.setListColumnMultiKeys(listColumnMultiKeys);
        record.setFieldItemName(fieldItemName);
        record.setFieldBinding(fieldBinding);
        record.setFieldLabel(fieldLabel);
        record.setFieldValueOptions(fieldValueOptions);
        record.setFieldValueFilter(fieldValueFilter);
        record.setFieldScopes(fieldScopes);
        record.setFieldScope(fieldScope);
        record.setFormFieldTitle(formFieldTitle);
        record.setFormFieldHint(formFieldHint);
        record.setFormFieldTips(formFieldTips);
        record.setFormInputType(formInputType);
        record.setFormInputOptions(formInputOptions);
        record.setFormViewType(formViewType);
        record.setFormViewOptions(formViewOptions);
        record.setFormInputRequired(formInputRequired);
        record.setFormFieldQuestion(formFieldQuestion);
        record.setPageId(pageId);
        record.setListImageWidth(listImageWidth);
        record.setListImageHeight(listImageHeight);
        record.setInputWidth(inputWidth);


        List<LowFieldsRecord> lowFieldsPage = queryLowFieldsDao.findLowFieldsPage(page, record, search, orderBy, null, null);

        page.setRecords(lowFieldsPage);

        return SuccessTip.create(page);
    }

    @BusinessLog(name = "LowFields", value = "delete LowFields")
    @Permission(LowFieldsPermission.LOWFIELDS_DELETE)
    @DeleteMapping("/byPage/{id}")
    @ApiOperation("根据pageId 批量删除 LowFields")
    public Tip deleteLowFieldsByPageId(@PathVariable Long id) {
        return SuccessTip.create(lowFieldsService.deleteByPage(id));
    }



}

