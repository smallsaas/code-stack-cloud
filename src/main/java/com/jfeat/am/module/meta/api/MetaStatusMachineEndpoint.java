package com.jfeat.am.module.meta.api;


import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.meta.services.domain.model.AdditionModel;
import com.jfeat.am.module.meta.services.domain.model.BulkApprovalModel;
import com.jfeat.am.module.meta.services.domain.model.BulkChangStatusModel;
import com.jfeat.am.module.meta.services.domain.model.ChangeStatusModel;
import com.jfeat.am.module.meta.services.domain.service.MetaStatusMachineService;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaStatusMachine;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-12-19
 */
@RestController

@Api("MetaStatusMachine")
@RequestMapping("/api/meta")
public class MetaStatusMachineEndpoint {


    @Resource
    MetaStatusMachineService metaStatusMachineService;

    @BusinessLog(name = "MetaStatusMachine", value = "get 状态")
    @GetMapping("/entity/{entity}/status")
    @ApiOperation("查看实体状态流")
    public Tip getEntityStatus(@PathVariable(name = "entity") String entity,
                               @RequestParam(name = "fromStatus", required = false) String fromStatus,
                               @RequestParam(name = "toStatus", required = false) String toStatus) {
        MetaStatusMachine queryEntity = new MetaStatusMachine();
        queryEntity.setEntity(entity);
        queryEntity.setFromStatus(fromStatus);
        queryEntity.setToStatus(toStatus);
        return SuccessTip.create(metaStatusMachineService.findMetaStatusMachine(queryEntity));
    }

    @BusinessLog(name = "MetaStatusMachine", value = "获取实体完成状态流")
    @GetMapping("/entity/{entity}/status/linked")
    @ApiOperation("获取实体完成状态流")
    public Tip getLinkedEntityStatusList(@PathVariable(name = "entity") String entity) {
        return SuccessTip.create(metaStatusMachineService.getLinkedEntityStatusList(entity));
    }

    @BusinessLog(name = "MetaStatusMachine", value = "add 状态")
    @PostMapping("/entity/{entity}/status")
    @ApiOperation("增加状态配置")
    public Tip createEntityStatus(@PathVariable(name = "entity") String entity,
                                  @RequestBody MetaStatusMachine metaStatusMachine) {
        metaStatusMachine.setEntity(entity);
        return SuccessTip.create(metaStatusMachineService.createMaster(metaStatusMachine));
    }

    @BusinessLog(name = "MetaStatusMachine", value = "change 状态")
    @PostMapping("/entity/{entity}/entities/{id}/action/updateStatus")
    @ApiOperation("改变状态")
    public Tip changeEntityStatus(@PathVariable(name = "entity") String entity,
                                  @PathVariable(name = "id") Long id,
                                  @RequestBody ChangeStatusModel model) {
        return SuccessTip.create(metaStatusMachineService.changeEntityStatus(entity, id, model));
    }

    @BusinessLog(name = "MetaStatusMachine", value = "批量更新状态")
    @PostMapping("/entity/{entity}/entities/action/bulk/updateStatus")
    @ApiOperation("批量更新状态")
    public Tip bulkChangeEntityStatus(@PathVariable(name = "entity") String entity,
                                      @RequestBody BulkChangStatusModel model) {
        return SuccessTip.create(metaStatusMachineService.bulkChangeEntityStatus(entity, model));
    }

    @BusinessLog(name = "MetaStatusMachine", value = "审批通过，创建日志保存工作流")
    @PostMapping("/entity/{entity}/entities/{id}/action/pass")
    @ApiOperation("审批通过，创建日志保存工作流")
    public Tip pass(@PathVariable(name = "entity") String entity, @PathVariable(name = "id") Long id,
                    @RequestBody AdditionModel model) {
        return SuccessTip.create(metaStatusMachineService.pass(entity, id, model));
    }

    @BusinessLog(name = "MetaStatusMachine", value = "审批不通过，创建日志保存工作流")
    @PostMapping("/entity/{entity}/entities/{id}/action/reject")
    @ApiOperation("审批不通过，创建日志保存工作流")
    public Tip reject(@PathVariable(name = "entity") String entity, @PathVariable(name = "id") Long id,
                      @RequestBody AdditionModel model) {
        return SuccessTip.create(metaStatusMachineService.reject(entity, id, model));
    }

    @BusinessLog(name = "MetaStatusMachine", value = "关闭，创建日志保存工作流")
    @PostMapping("/entity/{entity}/entities/{id}/action/cancel")
    @ApiOperation("关闭，创建日志保存工作流")
    public Tip cancel(@PathVariable(name = "entity") String entity, @PathVariable(name = "id") Long id,
                      @RequestBody AdditionModel model) {
        return SuccessTip.create(metaStatusMachineService.cancel(entity, id, model));
    }

    @BusinessLog(name = "MetaStatusMachine", value = "批量审批通过，创建日志保存工作流")
    @PostMapping("/entity/{entity}/entities/action/bulk/pass")
    @ApiOperation("批量审批通过，创建日志保存工作流")
    public Tip bulkPass(@PathVariable(name = "entity") String entity,
                                      @RequestBody BulkApprovalModel model) {
        return SuccessTip.create(metaStatusMachineService.bulkPass(entity, model));
    }

    @BusinessLog(name = "MetaStatusMachine", value = "批量审批不通过，创建日志保存工作流")
    @PostMapping("/entity/{entity}/entities/action/bulk/reject")
    @ApiOperation("批量审批不通过，创建日志保存工作流")
    public Tip bulkReject(@PathVariable(name = "entity") String entity,
                        @RequestBody BulkApprovalModel model) {
        return SuccessTip.create(metaStatusMachineService.bulkReject(entity, model));
    }

    @BusinessLog(name = "MetaStatusMachine", value = "批量关闭，创建日志保存工作流")
    @PostMapping("/entity/{entity}/entities/action/bulk/cancel")
    @ApiOperation("批量关闭，创建日志保存工作流")
    public Tip bulkCancel(@PathVariable(name = "entity") String entity,
                        @RequestBody BulkApprovalModel model) {
        return SuccessTip.create(metaStatusMachineService.bulkCancel(entity, model));
    }
}
