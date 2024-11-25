
package com.jfeat.module.crudcode.api;


import com.jfeat.crud.plus.META;
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
import com.jfeat.module.crudcode.services.domain.dao.QueryLowActionsDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.DefaultFilterResult;
import com.jfeat.module.crudcode.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.module.crudcode.services.domain.service.*;
import com.jfeat.module.crudcode.services.domain.model.LowActionsRecord;
import com.jfeat.module.crudcode.services.gen.crud.model.LowActionsModel;
import com.jfeat.module.crudcode.services.gen.persistence.model.LowActions;

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
 * @since 2021-09-28
 */
@RestController
@Api("LowActions")
@RequestMapping("/api/lc/lowActions/lowActionses")
public class LowActionsOverModelEndpoint {

    @Resource
    LowActionsOverModelService lowActionsOverModelService;

    @Resource
    QueryLowActionsDao queryLowActionsDao;


    // 要查询[从表]关联数据，取消下行注释
    // @Resource
    // QueryModalItemBasicDao queryModalItemBasicDao;

    @BusinessLog(name = "LowActions", value = "create LowActions")
    @Permission(LowActionsPermission.LOWACTIONS_NEW)
    @PostMapping
    @ApiOperation(value = "新建 LowActions", response = LowActionsModel.class)
    public Tip createLowActions(@RequestBody LowActionsModel entity) {
        Integer affected = 0;
        try {
            String[] entityType = new String[]{"request", "path", "delete", "modal.add", "modal.update"};
            List<String> type = Arrays.asList(entityType);
            if (!type.contains(entity.getType())) {
                throw new BusinessException(BusinessCode.BadRequest, "请输入正确操作类型：" + type);
            }

            String[] entityRequestMethod = new String[]{"GET", "POST", "UPDATE", "DELETE"};
            List<String> requestMethod = Arrays.asList(entityRequestMethod);
            if (entity.getType().equals("request") && !requestMethod.contains(entity.getRequestMethod())) {
                throw new BusinessException(BusinessCode.BadRequest, "请输入正确的请求方法：" + requestMethod);
            }

            String[] entityRequestApi = new String[]{"getApi", "updateApi", "createApi"};
            List<String> requestApi = Arrays.asList(entityRequestApi);
            if (entity.getType().equals("modal") && !requestApi.contains(entity.getRequestApi())) {
                throw new BusinessException(BusinessCode.BadRequest, "请按规定输入请求API动作：" + requestApi);
            }

            DefaultFilterResult filterResult = new DefaultFilterResult();
            affected = lowActionsOverModelService.createMaster(entity, filterResult, null, null);
            if (affected > 0) {
                return SuccessTip.create(filterResult.result());
            }
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "LowActions", value = "查看 LowActionsModel")
    @Permission(LowActionsPermission.LOWACTIONS_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 LowActions", response = LowActionsModel.class)
    public Tip getLowActions(@PathVariable Long id) {
        LowActions entity = lowActionsOverModelService
                .registerQueryMasterDao(queryLowActionsDao)
                // 要查询[从表]关联数据，取消下行注释
                //.registerQuerySlaveModelListDao(ModalItemBasic.class, queryModalItemBasicDao)
                .retrieveMaster(id);


        // sample query for registerQueryMasterDao
        // e.g. <select id="queryMasterModel" resultType="PlanModel">
        //       SELECT t_plan_model.*, t_org.name as orgName
        //       FROM t_plan_model
        //       LEFT JOIN t_org ON t_org.id==t_plan_model.org_id
        //       WHERE t_plan_model.id=#{id}
        //       GROUP BY t_plan_model.id
        //    </select>

        if (entity != null) {
            return SuccessTip.create(entity);
        } else {
            return SuccessTip.create();
        }

    }

    @BusinessLog(name = "LowActions", value = "update LowActions")
    @Permission(LowActionsPermission.LOWACTIONS_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 LowActions", response = LowActionsModel.class)
    public Tip updateLowActions(@PathVariable Long id, @RequestBody LowActionsModel entity) {
        entity.setId(id);
        // use update flags
        int newOptions = META.UPDATE_CASCADING_DELETION_FLAG;  //default to delete not exist items
        // newOptions = FlagUtil.setFlag(newOptions, META.UPDATE_ALL_COLUMNS_FLAG);

        return SuccessTip.create(lowActionsOverModelService.updateMaster(entity, null, null, null, newOptions));
    }

    @BusinessLog(name = "LowActions", value = "delete LowActions")
    @Permission(LowActionsPermission.LOWACTIONS_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 LowActions")
    public Tip deleteLowActions(@PathVariable Long id) {
        return SuccessTip.create(lowActionsOverModelService.deleteMaster(id));
    }

    @Permission(LowActionsPermission.LOWACTIONS_VIEW)
    @ApiOperation(value = "LowActions 列表信息", response = LowActionsRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "title", dataType = "String"),
            @ApiImplicitParam(name = "type", dataType = "String"),
            @ApiImplicitParam(name = "path", dataType = "String"),
            @ApiImplicitParam(name = "requestApi", dataType = "String"),
            @ApiImplicitParam(name = "requestRefreshApi", dataType = "String"),
            @ApiImplicitParam(name = "requestMethod", dataType = "String"),
            @ApiImplicitParam(name = "requestBody", dataType = "String"),
            @ApiImplicitParam(name = "requestOptions", dataType = "String"),
            @ApiImplicitParam(name = "pageId", dataType = "Long"),
            @ApiImplicitParam(name = "modalTitle", dataType = "String"),
            @ApiImplicitParam(name = "modalWidth", dataType = "Integer"),
            @ApiImplicitParam(name = "modalLayout", dataType = "String"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryLowActionsPage(Page<LowActionsRecord> page,
                                   @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "search", required = false) String search,
                                   @RequestParam(name = "id", required = false) Long id,

                                   @RequestParam(name = "title", required = false) String title,

                                   @RequestParam(name = "type", required = false) String type,

                                   @RequestParam(name = "path", required = false) String path,

                                   @RequestParam(name = "requestApi", required = false) String requestApi,

                                   @RequestParam(name = "requestRefreshApi", required = false) String requestRefreshApi,

                                   @RequestParam(name = "requestMethod", required = false) String requestMethod,

                                   @RequestParam(name = "requestBody", required = false) String requestBody,

                                   @RequestParam(name = "requestOptions", required = false) String requestOptions,

                                   @RequestParam(name = "pageId", required = false) Long pageId,

                                   @RequestParam(name = "modalTitle", required = false) String modalTitle,

                                   @RequestParam(name = "modalWidth", required = false) Integer modalWidth,

                                   @RequestParam(name = "modalLayout", required = false) String modalLayout,
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

        LowActionsRecord record = new LowActionsRecord();
        record.setId(id);
        record.setTitle(title);
        record.setType(type);
        record.setPath(path);
        record.setRequestApi(requestApi);
        record.setRequestRefreshApi(requestRefreshApi);
        record.setRequestMethod(requestMethod);
        record.setRequestBody(requestBody);
        record.setRequestOptions(requestOptions);
        record.setPageId(pageId);
        record.setModalTitle(modalTitle);
        record.setModalWidth(modalWidth);
        record.setModalLayout(modalLayout);


        List<LowActionsRecord> lowActionsPage = queryLowActionsDao.findLowActionsPage(page, record, search, orderBy, null, null);

        page.setRecords(lowActionsPage);

        return SuccessTip.create(page);
    }
}

