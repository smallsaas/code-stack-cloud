package com.jfeat.module.apicode.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.apicode.services.domain.service.ApiRelationModelTablesService;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiRelationModelTables;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.ApiRelationModelTablesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description: TODO
 * @project: code-stack-cloud
 * @date: 2024/4/16 14:22
 * @author: hhhhhtao
 */
@Api("apiRelationModelTables")
@RestController
@RequestMapping("/api/apicode/apiRelationModelTables/apiRelationModelTables")
public class ApiRelationModelTablesEndpoint {

    @Resource
    private ApiRelationModelTablesService apiRelationModelTablesService;

    @ApiOperation("查询")
    @GetMapping("/{id}")
    public Tip get(@ApiParam(name = "id", value = "api关系模型表id") @PathVariable Long id) {
        return SuccessTip.create(apiRelationModelTablesService.get(id));
    }

    @ApiOperation(value = "查询详情", notes = "查询包含tableModel信息的详情")
    @GetMapping("/detail/{id}")
    public Tip getDetail(@ApiParam(name = "id", value = "api关系模型表id") @PathVariable Long id) {
        return SuccessTip.create(apiRelationModelTablesService.getDetail(id));
    }

    @ApiOperation("新建")
    @PostMapping
    public Tip insert(
            @ApiParam(name = "model", value = "api关系模型表对象") @RequestBody ApiRelationModelTables model) {
        return SuccessTip.create(apiRelationModelTablesService.insert(model));
    }

    @ApiOperation("更新")
    @PutMapping("/{id}")
    public Tip update(
            @ApiParam(name = "id", value = "api关系模型表对象id") @PathVariable("id") Long id,
            @ApiParam(name = "model", value = "api关系模型表对象") @RequestBody ApiRelationModelTables model) {
        model.setId(id);
        return SuccessTip.create(apiRelationModelTablesService.update(model));
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public Tip delete(@ApiParam(name = "id", value = "api关系模型表id") @PathVariable Long id) {
        return SuccessTip.create(apiRelationModelTablesService.delete(id));
    }

    @ApiOperation("分页查询")
    @GetMapping()
    public Tip page(
            @ApiParam(name = "relationModelId", value = "关系模型id") @RequestParam(name = "relationModelId", required = false) Long relationModelId,
            @ApiParam(name = "pageNum", value = "页码") @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @ApiParam(name = "pageSize", value = "每页大小") @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize
    ) {
        Page<ApiRelationModelTables> page = new Page<>(pageNum, pageSize);

        ApiRelationModelTables model = new ApiRelationModelTables();
        model.setRelationModelId(relationModelId);

        return SuccessTip.create(apiRelationModelTablesService.page(page, model));
    }

    @ApiOperation("分页查询DTO")
    @GetMapping("/view")
    public Tip pageDTO(
            @ApiParam(name = "relationModelId", value = "关系模型id") @RequestParam(name = "relationModelId", required = false) Long relationModelId,
            @ApiParam(name = "pageNum", value = "页码") @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @ApiParam(name = "pageSize", value = "每页大小") @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize
    ) {
        Page<ApiRelationModelTablesDTO> page = new Page<>(pageNum, pageSize);

        ApiRelationModelTables model = new ApiRelationModelTables();
        model.setRelationModelId(relationModelId);

        return SuccessTip.create(apiRelationModelTablesService.pageDTO(page, model));
    }
}
