
package com.jfeat.module.apicode.api;

import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import com.jfeat.crud.plus.META;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.SwitchEav;
import io.swagger.annotations.*;
import com.jfeat.crud.plus.CRUDObject;
import com.jfeat.crud.plus.DefaultFilterResult;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.module.apicode.services.domain.dao.QueryApiTableModelDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.module.apicode.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.module.apicode.services.domain.service.*;
import com.jfeat.module.apicode.services.domain.model.ApiTableModelRecord;
import com.jfeat.module.apicode.services.gen.crud.model.ApiTableModelModel;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModel;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
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
@Api("ApiTableModel")
@RequestMapping("/api/lc/apiTableModel/apiTableModels")
public class ApiTableModelOverModelEndpoint {

    @Resource
    ApiTableModelOverModelService apiTableModelOverModelService;

    @Resource
    QueryApiTableModelDao queryApiTableModelDao;


    // 要查询[从表]关联数据，取消下行注释
    // @Resource
    // QueryApiTableModelFieldDao queryApiTableModelFieldDao;

    @BusinessLog(name = "ApiTableModel", value = "create ApiTableModel")
    @Permission(ApiTableModelPermission.APITABLEMODEL_NEW)
    @PostMapping
    @ApiOperation(value = "新建 ApiTableModel", response = ApiTableModelModel.class)
    public Tip createApiTableModel(@RequestBody ApiTableModelModel entity) {
        Integer affected = 0;
        try {
            DefaultFilterResult filterResult = new DefaultFilterResult();
            affected = apiTableModelOverModelService.createMaster(entity, filterResult, null, null);
            if (affected > 0) {
                return SuccessTip.create(filterResult.result());
            }
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "ApiTableModel", value = "查看 ApiTableModelModel")
    @Permission(ApiTableModelPermission.APITABLEMODEL_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 ApiTableModel", response = ApiTableModelModel.class)
    public Tip getApiTableModel(@PathVariable Long id) {
        CRUDObject<ApiTableModelModel> entity = apiTableModelOverModelService
                .registerQueryMasterDao(queryApiTableModelDao)
                // 要查询[从表]关联数据，取消下行注释
                //.registerQuerySlaveModelListDao(ApiTableModelField.class, queryApiTableModelFieldDao)
                .retrieveMasterModel(id);

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

    @BusinessLog(name = "ApiTableModel", value = "update ApiTableModel")
    @Permission(ApiTableModelPermission.APITABLEMODEL_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 ApiTableModel", response = ApiTableModelModel.class)
    public Tip updateApiTableModel(@PathVariable Long id, @RequestBody ApiTableModelModel entity) {
        entity.setId(id);
        // use update flags
        int newOptions = META.UPDATE_CASCADING_DELETION_FLAG;  //default to delete not exist items
        // newOptions = FlagUtil.setFlag(newOptions, META.UPDATE_ALL_COLUMNS_FLAG);
        return SuccessTip.create(apiTableModelOverModelService.updateMaster(entity, null, null, null, newOptions));
    }


    @BusinessLog(name = "ApiTableModel", value = "patch update ApiTableModel")
    @Permission(ApiTableModelPermission.APITABLEMODEL_EDIT)
    @PatchMapping("/{id}")
    @ApiOperation(value = "局部修改 ApiTableModel", response = ApiTableModel.class)
    public Tip patchUpdateApiTableModel(@PathVariable Long id, @RequestBody ApiTableModel entity) {
        entity.setId(id);
        return SuccessTip.create(apiTableModelOverModelService.patchMaster(entity));
    }


    @BusinessLog(name = "ApiTableModel", value = "delete ApiTableModel")
    @Permission(ApiTableModelPermission.APITABLEMODEL_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 ApiTableModel")
    public Tip deleteApiTableModel(@PathVariable Long id) {

        CRUDObject<ApiTableModelModel> entity = apiTableModelOverModelService
                .registerQueryMasterDao(queryApiTableModelDao)
                // 要查询[从表]关联数据，取消下行注释
                //.registerQuerySlaveModelListDao(ApiTableModelField.class, queryApiTableModelFieldDao)
                .retrieveMasterModel(id);


        if (entity.toJSONObject().get("subtables") != null && !entity.toJSONObject().get("subtables").toString().equals("[]")) {
            return SuccessTip.create("模型里存在子表, 删除失败");
        }

        if (entity.toJSONObject().get("fields") != null && !entity.toJSONObject().get("fields").toString().equals("[]")) {
            return SuccessTip.create("模型里存在字段, 删除失败");
        }

        return SuccessTip.create(apiTableModelOverModelService.deleteMaster(id));
    }

    @Permission(ApiTableModelPermission.APITABLEMODEL_VIEW)
    @ApiOperation(value = "ApiTableModel 列表信息", response = ApiTableModelRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "key", dataType = "String"),
            @ApiImplicitParam(name = "modelName", dataType = "String"),
            @ApiImplicitParam(name = "name", dataType = "String"),
            @ApiImplicitParam(name = "notes", dataType = "String"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryApiTableModelPage(Page<ApiTableModelRecord> page,
                                      @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                      @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                      // for tag feature query
                                      @RequestParam(name = "tag", required = false) String tag,
                                      // end tag
                                      @RequestParam(name = "search", required = false) String search,

                                      @RequestParam(name = "key", required = false) String key,

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

        ApiTableModelRecord record = new ApiTableModelRecord();
        record.setModelName(modelName);
        record.setName(name);
        record.setNotes(notes);


        List<ApiTableModelRecord> apiTableModelPage = queryApiTableModelDao.findApiTableModelPage(page, record, tag, search, orderBy, null, null);


        page.setRecords(apiTableModelPage);

        return SuccessTip.create(page);
    }

    @ApiOperation("下载建表语句sql文件")
    @GetMapping("/download-create-table-sql/{id}")
    public void downloadCreateTableSql(@ApiParam(name = "数据模型id", value = "id") @PathVariable("id") Long id,
                                       @ApiParam(name = "响应", value = "response") HttpServletResponse response) {
        // 获取数据模型简单信息
        ApiTableModel model = apiTableModelOverModelService.get(id);
        if (model == null) {
            throw new BusinessException(BusinessCode.CodeBase, "无效的数据模型");
        }
        // 生成sql
        String sql = apiTableModelOverModelService.generateCreateTableSql(id);
        // 拼加注释
        String annotate = String.format("-- ----------------------------\n-- Table structure for %s \n-- ----------------------------\n", model.getModelName());
        sql = annotate + sql;
        // 将sql作为.sql附件返回给前端下载
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s.sql;", model.getModelName()));
        try (OutputStream os = response.getOutputStream()) {
            os.write(sql.getBytes(StandardCharsets.UTF_8));
            response.flushBuffer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation("转换为eav自定义字段")
    @PostMapping("/switch-eav/{id}")
    public Tip switchEav(
            @ApiParam(name = "数据模型id", value = "id") @PathVariable("id") Long id,
            @ApiParam(name = "转换eavDTO", value = "switchEav") @RequestBody SwitchEav switchEav,
            HttpServletRequest request) {
        // eav-api 需要使用 token，从请求中获取token，无需在这里进行token的空判断和正确性判断，交给switchEav()方法中调用的api进行处理即可
        String token = request.getHeader("Authorization");
        return SuccessTip.create(apiTableModelOverModelService.switchEav(id, switchEav, token));
    }
}

