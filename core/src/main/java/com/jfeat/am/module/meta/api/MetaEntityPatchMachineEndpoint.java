package com.jfeat.am.module.meta.api;

import com.jfeat.am.module.meta.services.domain.service.MetaEntityPatchMachineService;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaEntityPatchMachine;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2019-01-07
 */
@RestController

@Api("MetaEntityPatchMachine")
@RequestMapping("/api/meta")
public class MetaEntityPatchMachineEndpoint {

    @Resource
    MetaEntityPatchMachineService metaEntityPatchMachineService;

    @BusinessLog(name = "MetaEntityPatchMachine", value = "get MetaEntityPatchMachine")
    @ApiOperation(value = "查看所有meta entity patch配置")
    @GetMapping("/patch/config/machines")
    public Tip queryMetaEntityPatchMachines(@RequestParam(name = "entity", required = false) String entity,
                                            @RequestParam(name = "entityTableName", required = false) String entityTableName,
                                            @RequestParam(name = "entityFieldName", required = false) String entityFieldName,
                                            @RequestParam(name = "entityFieldType", required = false) String entityFieldType,
                                            @RequestParam(name = "whereFieldName", required = false) String whereFieldName) {

        MetaEntityPatchMachine queryEntity = new MetaEntityPatchMachine();
        queryEntity.setEntity(entity);
        queryEntity.setEntityTableName(entityTableName);
        queryEntity.setEntityFieldName(entityFieldName);
        queryEntity.setEntityFieldType(entityFieldType);
        queryEntity.setWhereFieldName(whereFieldName);
        return SuccessTip.create(metaEntityPatchMachineService.findMetaEntityPatchMachines(queryEntity));
    }

    @BusinessLog(name = "MetaEntityPatchMachine", value = "get MetaEntityPatchMachine")
    @ApiOperation(value = "查看meta entity patch配置")
    @GetMapping("/patch/config/machines/{entity}")
    public Tip getMetaEntityPatchMachines(@PathVariable(name = "entity") String entity,
                                          @RequestParam(name = "entityTableName", required = false) String entityTableName,
                                          @RequestParam(name = "entityFieldName", required = false) String entityFieldName,
                                          @RequestParam(name = "entityFieldType", required = false) String entityFieldType) {

        MetaEntityPatchMachine queryEntity = new MetaEntityPatchMachine();
        queryEntity.setEntity(entity);
        queryEntity.setEntityTableName(entityTableName);
        queryEntity.setEntityFieldName(entityFieldName);
        queryEntity.setEntityFieldType(entityFieldType);
        return SuccessTip.create(metaEntityPatchMachineService.findMetaEntityPatchMachines(queryEntity));
    }

    @BusinessLog(name = "MetaEntityPatchMachine", value = "create MetaEntityPatchMachine")
    @PostMapping("/patch/config/machines")
    @ApiOperation(value = "添加meta entity patch配置")
    public Tip createMetaEntityPatchMachine(@RequestBody MetaEntityPatchMachine entity) {
        return SuccessTip.create(metaEntityPatchMachineService.createMeta(entity));
    }



    /**
     * meta - 字段更新与查询
     */


    @BusinessLog(name = "MetaEntityPatchMachine", value = "update entity")
    @PostMapping("/entity/{entity}/entities/{entityId}/action/update")
    @ApiOperation(value = "更新实体多个字段")
    public Tip updateEntity(@PathVariable(name = "entityId") String entityId,
                            @PathVariable(name = "entity") String entity,
                            @RequestBody Map<String, String> params) {
        return SuccessTip.create(metaEntityPatchMachineService.updateEntity(entity, entityId, params));
    }

    @BusinessLog(name = "MetaEntityPatchMachine", value = "update entity")
    @GetMapping("/entity/{entity}/entities/{entityId}")
    @ApiOperation(value = "获取实体字段值")
    public Tip updateEntity(@PathVariable(name = "entityId") String entityId,
                            @PathVariable(name = "entity") String entity) {
        return SuccessTip.create(metaEntityPatchMachineService.queryEntity(entity, entityId));
    }


//    @BusinessLog(name = "MetaEntityPatchMachine", value = "update entity")
//    @GetMapping("/entity/{entity}/onlyEntity/{condition}/action/select")
//    @ApiOperation(value = "单条件查询")
//    public Tip selectOnlyEntity(@PathVariable(name = "condition") String condition,
//                                @PathVariable(name = "entity") String entity) {
//        return SuccessTip.create(metaEntityPatchMachineService.selectEntityWithDynamicFields(entity, condition));
//    }

    //@BusinessLog(name = "MetaEntityPatchMachine", value = "bulk update entity")

//    @BusinessLog(name = "MetaEntityPatchMachine", value = "update entity")
//    @PostMapping("/entity/{entity}/onlyEntity/{id}/action/update")
//    @ApiOperation(value = "更新实体一个字段")
//    public Tip updateOnlyEntity(@PathVariable(name = "id") String condition,
//                                @PathVariable(name = "entity") String entity,
//                                @RequestBody MateFieldBody fieldBody) {
//        return SuccessTip.create(metaEntityPatchMachineService.updateEntity(entity, fieldBody.getValue(), condition));
//    }


//    @ApiOperation(value = "创建一个WHERE条件")
//    @PostMapping("/patch/config/whereFiled/{entity}/create")
//    public Tip createWhereFiled(@PathVariable(name = "entity") String entity,
//                                @RequestParam(name = "whereFieldName") String whereFieldName){
//        return SuccessTip.create(metaEntityPatchMachineService.createWhereFiled(entity,whereFieldName));
//    }

//    @ApiOperation(value = "更新实体一个字段")
//    @PutMapping("/patch/config/whereFiled/{entity}/update")
//    public Tip updateWhereFiled(@PathVariable(name = "entity") String entity,
//                                @RequestParam(name = "oldWhereFieldName") String oldWhereFieldName,
//                                @RequestParam(name = "newWhereFieldName") String newWhereFieldName,
//                                @RequestParam(name = "status",required = false,defaultValue = "filed") String status){
//        return SuccessTip.create(metaEntityPatchMachineService.updateWhereFiled(entity,oldWhereFieldName,newWhereFieldName,status));
//    }
//
//    @ApiOperation(value = "查询实体一个字段")
//    @GetMapping("/patch/config/whereFiled/{entity}/select")
//    public Tip selectWhereFiled(@PathVariable(name = "entity") String entity){
//        return SuccessTip.create(metaEntityPatchMachineService.selectWhereFiled(entity));
//    }
//
//
//    @ApiOperation(value = "删除实体一个字段")
//    @PostMapping("/patch/config/whereFiled/{entity}/delete")
//    public Tip deleteWhereFiled(@PathVariable(name = "entity") String entity,
//                                @RequestParam(name = "whereFieldName") String whereFieldName,
//                                @RequestParam(name = "status",required = false,defaultValue = "filed") String status){
//        return SuccessTip.create(metaEntityPatchMachineService.deleteWhereFiled(entity,whereFieldName,status));
//    }



    /**
     * 批量操作
     */

    @BusinessLog(name = "MetaEntityPatchMachine", value = "bulk update entity")
    @PostMapping("/entity/{entity}/entities/action/bulk/update")
    @ApiOperation(value = "批量更新实体")
    public Tip bulkUpdateEntity(@PathVariable(name = "entity") String entity,
                                @RequestBody List<Map<String, String>> params) {
        return SuccessTip.create(metaEntityPatchMachineService.bulkUpdateEntity(entity, params));
    }

    @BusinessLog(name = "MetaEntityPatchMachine", value = "bulk delete entity")
    @PostMapping("/entity/{entity}/entities/action/bulk/delete")
    @ApiOperation(value = "批量删除实体")
    public Tip bulkDeleteEntity(@PathVariable(name = "entity") String entity,
                                @RequestBody Ids ids) {
        if (null == ids || CollectionUtils.isEmpty(ids.getIds())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "ids[]不能为空");
        }
        return SuccessTip.create(metaEntityPatchMachineService.bulkDeleteEntity(entity, ids.getIds()));
    }

    @BusinessLog(name = "MetaEntityPatchMachine", value = "逻辑删除实体")
    @PostMapping("/entity/{entity}/entities/{id}/action/logicDelete")
    @ApiOperation(value = "逻辑删除实体")
    public Tip logicDeleteEntity(@PathVariable(name = "id") Long id,
                            @PathVariable(name = "entity") String entity) {
        return SuccessTip.create(metaEntityPatchMachineService.handleLogicDelete(entity, id, false));
    }

    @BusinessLog(name = "MetaEntityPatchMachine", value = "恢复逻辑删除的实体")
    @PostMapping("/entity/{entity}/entities/{id}/action/logicDelete/recovery")
    @ApiOperation(value = "恢复逻辑删除的实体")
    public Tip recoveryLogicDeleteEntity(@PathVariable(name = "id") Long id,
                                 @PathVariable(name = "entity") String entity) {
        return SuccessTip.create(metaEntityPatchMachineService.handleLogicDelete(entity, id, true));
    }

    @BusinessLog(name = "MetaEntityPatchMachine", value = "批量逻辑删除的实体")
    @PostMapping("/entity/{entity}/entities/action/bulk/logicDelete")
    @ApiOperation(value = "批量逻辑删除的实体")
    public Tip bulkLogicDeleteEntity(@PathVariable(name = "entity") String entity,
                                             @RequestBody Ids ids) {
        if (null == ids|| CollectionUtils.isEmpty(ids.getIds())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "ids[]不能为空");
        }
        return SuccessTip.create(
                metaEntityPatchMachineService.handleBulkLogicDelete(entity, ids.getIds(), false));
    }

    @BusinessLog(name = "MetaEntityPatchMachine", value = "批量恢复逻辑删除的实体")
    @PostMapping("/entity/{entity}/entities/action/bulk/logicDelete/recovery")
    @ApiOperation(value = "批量恢复逻辑删除的实体")
    public Tip bulkRecoveryLogicDeleteEntity(@PathVariable(name = "entity") String entity,
                                     @RequestBody Ids ids) {
        if (null == ids|| CollectionUtils.isEmpty(ids.getIds())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "ids[]不能为空");
        }
        return SuccessTip.create(
                metaEntityPatchMachineService.handleBulkLogicDelete(entity, ids.getIds(), true));
    }


    /**
     * 排序
     */

    @BusinessLog(name = "排序", value = "列表记录上移内容")
    @PostMapping("/entity/{entity}/action/moveup/row/{id}/row/{nextId}")
    @ApiOperation(value = "上移实体排序")
    public Tip moveUpEntity(@PathVariable(name = "entity") String entity,
                            @PathVariable(name = "id") Long id,
                            @PathVariable(name = "nextId") Long nextId) {
        return SuccessTip.create(metaEntityPatchMachineService.moveUpEntity(entity, id, nextId));
    }

    @BusinessLog(name = "排序", value = "列表记录下移内容")
    @PostMapping("/entity/{entity}/action/movedown/row/{id}/row/{nextId}")
    @ApiOperation(value = "下移实体排序")
    public Tip moveDownEntity(@PathVariable(name = "entity") String entity,
                            @PathVariable(name = "id") Long id,
                            @PathVariable(name = "nextId") Long nextId) {
        return SuccessTip.create(metaEntityPatchMachineService.moveDownEntity(entity, id, nextId));
    }

}
