package com.jfeat.module.crudcode.api;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.jfeat.crud.plus.DefaultFilterResult;
import com.jfeat.module.crudcode.constant.ApiListConst;
import com.jfeat.module.crudcode.services.domain.dao.QueryLowActionsDao;
import com.jfeat.module.crudcode.services.domain.dao.QueryLowFieldsDao;
import com.jfeat.module.crudcode.services.domain.dao.QueryLowOperationsDao;
import com.jfeat.module.crudcode.services.domain.model.LowMainPageRecordTwo;
import com.jfeat.module.crudcode.services.gen.persistence.dao.LowMainPageMapper;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowActions;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowMainPage;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowOperations;
import com.jfeat.module.crudcode.util.HttpRequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.undertow.util.BadRequestException;
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
import com.jfeat.module.crudcode.services.domain.dao.QueryLowMainPageDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.CRUDObject;
import com.jfeat.module.crudcode.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.module.crudcode.services.domain.service.*;
import com.jfeat.module.crudcode.services.domain.model.LowMainPageRecord;
import com.jfeat.module.crudcode.services.gen.crud.model.LowMainPageModel;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * api
 * </p>
 *
 * @author Code generator
 * @since 2021-09-07
 */
@RestController
@Api("LowMainPage")
@RequestMapping("/api/lc/lowMainPage/lowMainPages")
public class LowMainPageOverModelEndpoint {

    @Resource
    LowMainPageOverModelService lowMainPageOverModelService;


    @Resource
    QueryLowMainPageDao queryLowMainPageDao;

    @Resource
    QueryLowActionsDao queryLowActionsDao;

    @Resource
    QueryLowOperationsDao queryLowOperationsDao;

    @Resource
    LowMainPageMapper lowMainPageMapper;

    @BusinessLog(name = "LowMainPage", value = "create LowMainPage")
    @Permission(LowMainPagePermission.LOWMAINPAGE_NEW)
    @PostMapping
    @ApiOperation(value = "新建 LowMainPage", response = LowMainPageModel.class)
    public Tip createLowMainPage(@RequestBody LowMainPageModel entity) {

        if (entity.getPageId()==null){
            entity.setPageId(IdWorker.getId());
        }
        Integer affected = 0;
        DefaultFilterResult filterResult = new DefaultFilterResult();

        try {

            affected = lowMainPageOverModelService.createMaster(entity, filterResult, null, null);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(filterResult.result());
    }

    @BusinessLog(name = "LowMainPage", value = "查看 LowMainPageModel")
    @Permission(LowMainPagePermission.LOWMAINPAGE_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 LowMainPage", response = LowMainPageModel.class)
    public Tip getLowMainPage(@PathVariable Long id) {
        CRUDObject<LowMainPageModel> entity = lowMainPageOverModelService
                .registerQueryMasterDao(queryLowMainPageDao)
                // 这里加些说明 , 替换 SlaveModel 为从表实体模型类名， 替换 querySlaveModelDao为从表查询Dao
//                .registerQuerySlaveModelListDao(LowActions.class, queryLowActionsDao)

//                .registerQuerySlaveModelListDao(LowOperations.class, queryLowOperationsDao)

                .retrieveMaster(id, null, null, null);
        if (entity != null) {
            return SuccessTip.create(entity.toJSONObject());
        } else {
            return SuccessTip.create();
        }
    }



    @BusinessLog(name = "LowMainPage", value = "update LowMainPage")
    @Permission(LowMainPagePermission.LOWMAINPAGE_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 LowMainPage", response = LowMainPageModel.class)
    public Tip updateLowMainPage(@PathVariable Long id, @RequestBody LowMainPageModel entity) {
        entity.setId(id);
        return SuccessTip.create(lowMainPageOverModelService.updateMaster(entity));
    }

    @BusinessLog(name = "LowMainPage", value = "delete LowMainPage")
    @Permission(LowMainPagePermission.LOWMAINPAGE_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 LowMainPage")
    public Tip deleteLowMainPage(@PathVariable Long id) {
        return SuccessTip.create(lowMainPageOverModelService.deleteMaster(id));
    }

    @Permission(LowMainPagePermission.LOWMAINPAGE_VIEW)
    @ApiOperation(value = "快捷查询 LowMainPage", httpMethod = "GET")
    @GetMapping("/getPageId")
    @ApiImplicitParams({

            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "pageTitle", dataType = "String"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryLowMainPagePage(Page<LowMainPageRecordTwo> page,
                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                    @RequestParam(name = "search", required = false) String search,
                                    @RequestParam(name = "id", required = false) Long id,
                                    @RequestParam(name = "pageTitle", required = false) String pageTitle,
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

        LowMainPageRecordTwo record = new LowMainPageRecordTwo();
        record.setId(id);
        record.setPageTitle(pageTitle);
        List<LowMainPageRecordTwo> lowMainPagePageTwo = queryLowMainPageDao.findLowMainPagePageTwo(page, record, search, orderBy, null, null);

        page.setRecords(lowMainPagePageTwo);
        return SuccessTip.create(page);
    }

    @Permission(LowMainPagePermission.LOWMAINPAGE_VIEW)
    @ApiOperation(value = "LowMainPage 列表信息", response = LowMainPageRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "pageName", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "pageTitle", dataType = "String"),
            @ApiImplicitParam(name = "columnAlign", dataType = "String"),
            @ApiImplicitParam(name = "apiEndpoint", dataType = "String"),
            @ApiImplicitParam(name = "formAddTitle", dataType = "String"),
            @ApiImplicitParam(name = "formViewTitle", dataType = "String"),
            @ApiImplicitParam(name = "formEditTitle", dataType = "String"),
            @ApiImplicitParam(name = "contentLayout", dataType = "String"),
            @ApiImplicitParam(name = "contentItems", dataType = "String"),
            @ApiImplicitParam(name = "content_itemContainerStyle", dataType = "String"),
            @ApiImplicitParam(name = "listFields", dataType = "String"),
            @ApiImplicitParam(name = "listOperationFields", dataType = "String"),
            @ApiImplicitParam(name = "formAddFields", dataType = "String"),
            @ApiImplicitParam(name = "formViewFields", dataType = "String"),
            @ApiImplicitParam(name = "formEditFields", dataType = "String"),
            @ApiImplicitParam(name = "form_defaultContentLayout", dataType = "String"),
            @ApiImplicitParam(name = "formDefaultWidth", dataType = "Integer"),
            @ApiImplicitParam(name = "pageMinWidth", dataType = "Integer"),
            @ApiImplicitParam(name = "searchType", dataType = "String"),
            @ApiImplicitParam(name = "searchButtonType", dataType = "String"),

            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryLowMainPagePage(Page<LowMainPageRecord> page,
                                    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                    @RequestParam(name = "search", required = false) String search,
                                    @RequestParam(name = "id", required = false) Long id,
                                    @RequestParam(name = "pageName", required = false) String pageName,
                                    @RequestParam(name = "pageTitle", required = false) String pageTitle,

                                    @RequestParam(name = "columnAlign", required = false) String columnAlign,

                                    @RequestParam(name = "apiEndpoint", required = false) String apiEndpoint,

                                    @RequestParam(name = "formAddTitle", required = false) String formAddTitle,

                                    @RequestParam(name = "formViewTitle", required = false) String formViewTitle,

                                    @RequestParam(name = "formEditTitle", required = false) String formEditTitle,

                                    @RequestParam(name = "contentLayout", required = false) String contentLayout,

                                    @RequestParam(name = "contentItems", required = false) String contentItems,

                                    @RequestParam(name = "contentItemContainerStyle", required = false) String contentItemContainerStyle,

                                    @RequestParam(name = "listFields", required = false) String listFields,

                                    @RequestParam(name = "listOperationFields", required = false) String listOperationFields,

                                    @RequestParam(name = "formAddFields", required = false) String formAddFields,

                                    @RequestParam(name = "formViewFields", required = false) String formViewFields,

                                    @RequestParam(name = "formEditFields", required = false) String formEditFields,

                                    @RequestParam(name = "formDefaultContentLayout", required = false) String formDefaultContentLayout,

                                    @RequestParam(name = "formDefaultWidth", required = false) Integer formDefaultWidth,

                                    @RequestParam(name = "pageMinWidth", required = false) Integer pageMinWidth,

                                    @RequestParam(name = "pageMinWidth", required = false) String searchType,

                                    @RequestParam(name = "pageMinWidth", required = false) String searchButtonType,

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

        LowMainPageRecord record = new LowMainPageRecord();
        record.setId(id);
        record.setPageTitle(pageTitle);
        record.setPageName(pageName);
        record.setColumnAlign(columnAlign);
        record.setApiEndpoint(apiEndpoint);
        record.setFormAddTitle(formAddTitle);
        record.setFormViewTitle(formViewTitle);
        record.setFormEditTitle(formEditTitle);
        record.setContentLayout(contentLayout);
        record.setContentItems(contentItems);
        record.setContentItemContainerStyle(contentItemContainerStyle);
        record.setListFields(listFields);
        record.setListOperationFields(listOperationFields);
        record.setFormAddFields(formAddFields);
        record.setFormViewFields(formViewFields);
        record.setFormEditFields(formEditFields);
        record.setFormDefaultContentLayout(formDefaultContentLayout);
        record.setFormDefaultWidth(formDefaultWidth);
        record.setPageMinWidth(pageMinWidth);
        record.setSearchButtonType(searchButtonType);
        record.setSearchType(searchType);


        List<LowMainPageRecord> lowMainPagePage = queryLowMainPageDao.findLowMainPagePage(page, record, search, orderBy, null, null);

        page.setRecords(lowMainPagePage);

        return SuccessTip.create(page);
    }


    @BusinessLog(name = "LowMainPage", value = "查看 LowMainPageModel")
    @Permission(LowMainPagePermission.LOWMAINPAGE_VIEW)
    @GetMapping("/config")
    @ApiOperation(value = "查看 LowMainPage", response = LowMainPageModel.class)
    public Tip getLowMainPageConfig(@RequestParam(name = "id", required = false) Long id,
                                    @RequestParam(name = "pageId", required = false) Long pageId,
                                    HttpServletRequest request) {


        if (id==null && pageId==null){
            return SuccessTip.create();
        }

        QueryWrapper<LowMainPage>  queryWrapper = new QueryWrapper<>();
        if (id!=null){
            queryWrapper.eq(LowMainPage.ID,id);
        }
        if (pageId!=null){
            queryWrapper.eq(LowMainPage.PAGE_ID,pageId);
        }

        LowMainPage lowMainPage = lowMainPageMapper.selectOne(queryWrapper);
        if (lowMainPage==null){
            return SuccessTip.create();
        }
        id = lowMainPage.getId();
        CRUDObject<LowMainPageModel> entity = lowMainPageOverModelService
                .registerQueryMasterDao(queryLowMainPageDao)
                // 这里加些说明 , 替换 SlaveModel 为从表实体模型类名， 替换 querySlaveModelDao为从表查询Dao
//                .registerQuerySlaveModelListDao(LowActions.class, queryLowActionsDao)

//                .registerQuerySlaveModelListDao(LowOperations.class, queryLowOperationsDao)

                .retrieveMaster(id, null, null, null);
        if (entity != null) {
            /*
             * 2023-08-29 新增需求：根据请求携带的域名来拼接该接口需要请求的api
             */
            String protocol = request.getScheme();
            String domainName = request.getServerName();
            String toConfigApiUrl = protocol + "://" + domainName + ApiListConst.GET_PAGE_CONFIG;
            String s =  HttpRequestUtil.post(toConfigApiUrl ,entity.toJSONObject().toJSONString());
            if (s==null){
                return SuccessTip.create();
            }
            JSONObject jsonObject = (JSONObject) JSONObject.parse(s);
            if (jsonObject.containsKey("data")){
                return SuccessTip.create(jsonObject.getJSONObject("data"));
            }
            return SuccessTip.create();
        } else {
            return SuccessTip.create();
        }
    }
}
