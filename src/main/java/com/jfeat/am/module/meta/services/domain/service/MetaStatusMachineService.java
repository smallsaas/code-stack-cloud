package com.jfeat.am.module.meta.services.domain.service;

import com.jfeat.am.module.meta.services.domain.model.BulkChangStatusModel;
import com.jfeat.am.module.meta.services.domain.model.ChangeStatusModel;
import com.jfeat.am.module.meta.services.gen.crud.service.CRUDMetaStatusMachineService;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaStatusMachine;
import com.jfeat.crud.base.tips.BulkResult;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface MetaStatusMachineService extends CRUDMetaStatusMachineService {
    List<MetaStatusMachine> findMetaStatusMachine(MetaStatusMachine queryEntity);

    /**
     * 获取实体完成状态流
     * @param entity 实体
     * @return
     */
    List<MetaStatusMachine> getLinkedEntityStatusList(String entity);

    Integer changeEntityStatus(String entity, Long id, ChangeStatusModel model);

    Integer changeEntityStatusWithLog(String entity, Long id, ChangeStatusModel model);

    BulkResult bulkChangeEntityStatus(String entity, BulkChangStatusModel model);

    BulkResult bulkChangeEntityStatusWithLog(String entity, BulkChangStatusModel model);
}