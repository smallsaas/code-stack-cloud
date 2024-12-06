package com.jfeat.meta.dosql.services.domain.service.impl;
import com.jfeat.meta.dosql.services.domain.service.DoSqlVersionService;
import com.jfeat.meta.dosql.services.gen.crud.service.impl.CRUDDoSqlVersionServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("devVersionService")
public class DoSqlVersionServiceImpl extends CRUDDoSqlVersionServiceImpl implements DoSqlVersionService {

    @Override
    protected String entityName() {
        return "DevVersion";
    }


                            }
