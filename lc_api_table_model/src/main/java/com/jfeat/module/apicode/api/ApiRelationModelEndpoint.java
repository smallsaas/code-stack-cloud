package com.jfeat.module.apicode.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.apicode.services.domain.service.ApiRelationModelService;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiRelationModel;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.CgParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description: TODO
 * @project: code-stack-cloud
 * @date: 2024/4/16 14:20
 * @author: hhhhhtao
 */
@Api("apiRelationModel")
@RestController
@RequestMapping("/api/lc/apiRelationModel/apiRelationModels")
public class ApiRelationModelEndpoint {

    @Resource()
    private ApiRelationModelService apiRelationModelService;

    @ApiOperation("查询")
    @GetMapping("/{id}")
    public Tip get(@ApiParam(name = "id", value = "api关系模型id") @PathVariable Long id) {
        return SuccessTip.create(apiRelationModelService.get(id));
    }

    @ApiOperation("新建")
    @PostMapping()
    public Tip insert(@ApiParam(name = "apiRelationModel", value = "api关系模型对象") @RequestBody ApiRelationModel apiRelationModel) {
        return SuccessTip.create(apiRelationModelService.insert(apiRelationModel));
    }

    @ApiOperation("更新")
    @PutMapping("/{id}")
    public Tip update(
            @ApiParam(name = "id", value = "api关系模型对象id") @PathVariable("id") Long id,
            @ApiParam(name = "apiRelationModel", value = "api关系模型对象") @RequestBody ApiRelationModel apiRelationModel) {
        apiRelationModel.setId(id);
        return SuccessTip.create(apiRelationModelService.update(apiRelationModel));
    }

    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public Tip delete(@ApiParam(name = "id", value = "api关系模型id") @PathVariable Long id) {
        return SuccessTip.create(apiRelationModelService.delete(id));
    }

    @ApiOperation("分页查询")
    @GetMapping
    public Tip page(@ApiParam(name = "modelName", value = "模型名") @RequestParam(value = "modelName", required = false) String modelName,
                    @ApiParam(name = "name", value = "标题名") @RequestParam(value = "name", required = false) String name,
                    @ApiParam(name = "pageNum", value = "页码") @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                    @ApiParam(name = "pageSize", value = "每页大小") @RequestParam(name = "pageSize", defaultValue = "20") Integer pageSize
                    ) {
        Page<ApiRelationModel> page = new Page<>(pageNum, pageSize);
        ApiRelationModel model = new ApiRelationModel();
        model.setModelName(modelName);
        model.setName(name);
        return SuccessTip.create(apiRelationModelService.page(page, model));
    }

    @ApiOperation("生成项目代码")
    @PostMapping("/generate-project-code/{id}")
    public Tip generateProjectCode(@ApiParam(name = "id", value = "关系模型id") @PathVariable("id") Long id,
                                   @ApiParam(name = "cgParam", value = "生成参数api所需参数实体") @RequestBody(required = false) CgParam cgParam) {
        if (cgParam == null) {
            cgParam = new CgParam();
        }
        apiRelationModelService.generateProjectCode(id, cgParam);
        return SuccessTip.create();
    }

}
