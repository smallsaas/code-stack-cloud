package com.jfeat.meta.dosql.services.gen.crud.service.impl;
// ServiceImpl start


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import com.jfeat.meta.dosql.services.gen.crud.service.CRUDDoSqlVersionService;
import com.jfeat.meta.dosql.services.gen.persistence.dao.DoSqlVersionMapper;
import com.jfeat.meta.dosql.services.gen.persistence.model.DoSqlVersion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDDevVersionService
 * @author Code generator
 * @since 2022-09-16
 */

@Service
public class CRUDDoSqlVersionServiceImpl extends CRUDServiceOnlyImpl<DoSqlVersion> implements CRUDDoSqlVersionService {

        @Resource
        protected DoSqlVersionMapper devVersionMapper;

        @Override
        protected BaseMapper<DoSqlVersion> getMasterMapper() {
                return devVersionMapper;
        }
}


