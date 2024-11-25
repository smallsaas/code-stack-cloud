package com.jfeat.module.crudcode.services.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.module.crudcode.services.domain.service.LowFieldsService;
import com.jfeat.module.crudcode.services.gen.crud.service.impl.CRUDLowFieldsServiceImpl;
import com.jfeat.module.crudcode.services.gen.persistence.dao.LowFieldsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Wrapper;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("lowFieldsService")
public class LowFieldsServiceImpl extends CRUDLowFieldsServiceImpl implements LowFieldsService {

    @Resource
    private LowFieldsMapper lowFieldsMapper;

    @Override
    public Integer deleteByPage(Long pageId) {
        Integer i = 0;
        if (pageId != null) {
            QueryWrapper lowWrapper = new QueryWrapper<>();
            lowWrapper.eq("page_id", pageId);
            int delete = lowFieldsMapper.delete(lowWrapper);
            i += delete;
        }

        return i;
    }


}
