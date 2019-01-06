package com.jfeat.am.module.meta.services.domain.service;

import com.jfeat.am.module.meta.services.gen.crud.service.CRUDMetaStatusMachineService;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaStatusMachine;
import com.jfeat.crud.base.tips.BulkResult;
import com.jfeat.crud.plus.META;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface MetaStatusMachineService extends CRUDMetaStatusMachineService {
    List<MetaStatusMachine> findMetaStatusMachine(MetaStatusMachine queryEntity);

    Integer changeEntityStatus(String entity, Long id, String status);

    BulkResult bulkChangeEntityStatus(String entity, List<Long> ids, String status);
}