
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
import com.jfeat.module.crudcode.services.domain.dao.QueryLowOperationsDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.CRUDObject;
import com.jfeat.crud.plus.DefaultFilterResult;
import com.jfeat.module.crudcode.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.module.crudcode.services.domain.service.*;
import com.jfeat.module.crudcode.services.domain.model.LowOperationsRecord;
import com.jfeat.module.crudcode.services.gen.crud.model.LowOperationsModel;

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
 * @since 2021-10-11
 */
@RestController
@Api("LowOperations")
@RequestMapping("/api/lc/lowOperations/lowOperationses")
public class LowOperationsOverModelEndpoint {

    @Resource
    LowOperationsOverModelService lowOperationsOverModelService;

    @Resource
    QueryLowOperationsDao queryLowOperationsDao;


    // 要查询[从表]关联数据，取消下行注释
    // @Resource
    // QueryModalItemBasicODao queryModalItemBasicODao;

    @BusinessLog(name = "LowOperations", value = "create LowOperations")
    @Permission(LowOperationsPermission.LOWOPERATIONS_NEW)
    @PostMapping
    @ApiOperation(value = "新建 LowOperations", response = LowOperationsModel.class)
    public Tip createLowOperations(@RequestBody LowOperationsModel entity) {
        Integer affected = 0;
        try {
            String[] entityType = new String[]{"request", "path", "delete", "modal.add", "modal.update"};
            List<String> type = Arrays.asList(entityType);
            if (!type.contains(entity.getType())) {
                throw new BusinessException(BusinessCode.BadRequest, "请输入正确操作类型：" + type);
            }

            String[] entityRequestMethod = new String[]{"GET", "POST", "UPDATE", "DELETE"};
            /*List<String> requestMethod = Arrays.asList(entityRequestMethod);
            if (!requestMethod.contains(entity.getRequestMethod())) {
                throw new BusinessException(BusinessCode.BadRequest, "请输入正确的请求方法：" + requestMethod);
            }*/

            String[] entityRequestApi = new String[]{"getApi", "updateApi", "createApi"};
/*            List<String> requestApi = Arrays.asList(entityRequestApi);
            if (!requestApi.contains(entity.getRequestApi())) {
                throw new BusinessException(BusinessCode.BadRequest, "请按规定输入请求API动作：" + requestApi);
            }*/

            DefaultFilterResult filterResult = new DefaultFilterResult();
            affected = lowOperationsOverModelService.createMaster(entity, filterResult, null, null);
            if (affected > 0) {
                return SuccessTip.create(filterResult.result());
            }
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "LowOperations", value = "查看 LowOperationsModel")
    @Permission(LowOperationsPermission.LOWOPERATIONS_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 LowOperations", response = LowOperationsModel.class)
    public Tip getLowOperations(@PathVariable Long id) {
        CRUDObject<LowOperationsModel> entity = lowOperationsOverModelService
                .registerQueryMasterDao(queryLowOperationsDao)
                // 要查询[从表]关联数据，取消下行注释
                //.registerQuerySlaveModelListDao(ModalItemBasicO.class, queryModalItemBasicODao)
                .retrieveMaster(id, null, null, null);

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

    @BusinessLog(name = "LowOperations", value = "update LowOperations")
    @Permission(LowOperationsPermission.LOWOPERATIONS_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 LowOperations", response = LowOperationsModel.class)
    public Tip updateLowOperations(@PathVariable Long id, @RequestBody LowOperationsModel entity) {
        entity.setId(id);
        // use update flags
        int newOptions = META.UPDATE_CASCADING_DELETION_FLAG;  //default to delete not exist items
        // newOptions = FlagUtil.setFlag(newOptions, META.UPDATE_ALL_COLUMNS_FLAG);

        return SuccessTip.create(lowOperationsOverModelService.updateMaster(entity, null, null, null, newOptions));
    }

    @BusinessLog(name = "LowOperations", value = "delete LowOperations")
    @Permission(LowOperationsPermission.LOWOPERATIONS_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 LowOperations")
    public Tip deleteLowOperations(@PathVariable Long id) {
        return SuccessTip.create(lowOperationsOverModelService.deleteMaster(id));
    }

    @Permission(LowOperationsPermission.LOWOPERATIONS_VIEW)
    @ApiOperation(value = "LowOperations 列表信息", response = LowOperationsRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "title", dataType = "String"),
            @ApiImplicitParam(name = "type", dataType = "String"),
            @ApiImplicitParam(name = "path", dataType = "String"),
            @ApiImplicitParam(name = "outside", dataType = "Integer"),
            @ApiImplicitParam(name = "requestApi", dataType = "String"),
            @ApiImplicitParam(name = "requestRefreshApi", dataType = "String"),
            @ApiImplicitParam(name = "requestMethod", dataType = "String"),
            @ApiImplicitParam(name = "requestBody", dataType = "String"),
            @ApiImplicitParam(name = "requestOptions", dataType = "String"),
            @ApiImplicitParam(name = "pageId", dataType = "Long"),
            @ApiImplicitParam(name = "expectField", dataType = "String"),
            @ApiImplicitParam(name = "expectValue", dataType = "String"),
            @ApiImplicitParam(name = "modalTitle", dataType = "String"),
            @ApiImplicitParam(name = "modalWidth", dataType = "Integer"),
            @ApiImplicitParam(name = "modalLayout", dataType = "String"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryLowOperationsPage(Page<LowOperationsRecord> page,
                                      @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                      @RequestParam(name = "search", required = false) String search,
                                      @RequestParam(name = "id", required = false) Long id,
                                      @RequestParam(name = "title", required = false) String title,
                                      @RequestParam(name = "type", required = false) String type,
                                      @RequestParam(name = "path", required = false) String path,
                                      @RequestParam(name = "outside", required = false) Integer outside,
                                      @RequestParam(name = "requestApi", required = false) String requestApi,
                                      @RequestParam(name = "requestRefreshApi", required = false) String requestRefreshApi,
                                      @RequestParam(name = "requestMethod", required = false) String requestMethod,
                                      @RequestParam(name = "requestBody", required = false) String requestBody,
                                      @RequestParam(name = "requestOptions", required = false) String requestOptions,
                                      @RequestParam(name = "pageId", required = false) Long pageId,
                                      @RequestParam(name = "expectField", required = false) String expectField,
                                      @RequestParam(name = "expectValue", required = false) String expectValue,
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

        LowOperationsRecord record = new LowOperationsRecord();
        record.setId(id);
        record.setTitle(title);
        record.setType(type);
        record.setPath(path);
        record.setOutside(outside);
        record.setRequestApi(requestApi);
        record.setRequestRefreshApi(requestRefreshApi);
        record.setRequestMethod(requestMethod);
        record.setRequestBody(requestBody);
        record.setRequestOptions(requestOptions);
        record.setPageId(pageId);
        record.setExpectField(expectField);
        record.setExpectValue(expectValue);
        record.setModalTitle(modalTitle);
        record.setModalWidth(modalWidth);
        record.setModalLayout(modalLayout);


        List<LowOperationsRecord> lowOperationsPage = queryLowOperationsDao.findLowOperationsPage(page, record, search, orderBy, null, null);

        page.setRecords(lowOperationsPage);

        return SuccessTip.create(page);
    }
}

