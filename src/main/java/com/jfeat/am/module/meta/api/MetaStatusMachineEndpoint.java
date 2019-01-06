package com.jfeat.am.module.meta.api;


import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.meta.services.domain.dao.QueryMetaStatusMachineDao;
import com.jfeat.am.module.meta.services.domain.model.BulkChangStatusModel;
import com.jfeat.am.module.meta.services.domain.service.MetaStatusMachineService;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaStatusMachine;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;


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

    @Resource
    QueryMetaStatusMachineDao queryMetaStatusMachineDao;

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
                                  @RequestBody Map<String, String> params) {
        if (null == params || StringUtils.isBlank(params.get("status"))) {
            throw new BusinessException(BusinessCode.EmptyNotAllowed);
        }
        return SuccessTip.create(metaStatusMachineService.changeEntityStatus(entity, id, params.get("status")));
    }

    @BusinessLog(name = "MetaStatusMachine", value = "bulk change 状态")
    @PostMapping("/entity/{entity}/entities/action/bulk/updateStatus")
    @ApiOperation("改变状态")
    public Tip bulkChangeEntityStatus(@PathVariable(name = "entity") String entity,
                                      @RequestBody BulkChangStatusModel model) {
        if (null == model || null == model.getIds() || model.getIds().isEmpty() || StringUtils.isBlank(model.getStatus())) {
            throw new BusinessException(BusinessCode.EmptyNotAllowed);
        }
        return SuccessTip.create(metaStatusMachineService.bulkChangeEntityStatus(entity, model.getIds(), model.getStatus()));
    }
}
