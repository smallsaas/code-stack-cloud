package com.jfeat.module.apicode.services.domain.service;

import cn.hutool.http.server.HttpServerResponse;
import com.alibaba.fastjson.JSONObject;
import com.jfeat.crud.plus.CRUDFilterResult;
import com.jfeat.crud.plus.CRUDHandler;
import com.jfeat.module.apicode.services.gen.crud.service.CRUDApiTableModelOverModelService;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModel;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.SwitchEav;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vincent on 2017/10/19.
 */
public interface ApiTableModelOverModelService extends CRUDApiTableModelOverModelService {

    /**
     * 获取数据模型
     *
     * @param id 数据模型id
     */
    ApiTableModel get(Long id);

    /**
     * 生成建表sql字符串
     * <p>如果数据模型中没有字段则会返回null</p>
     *
     * @param id 数据模型id
     * @return 返回sql字符串
     */
    String generateCreateTableSql(Long id);

    /**
     * 将数据模型转换为eav
     *
     * @param id 数据模型id
     * @param switchEav 转换eavDTO
     * @param token token令牌
     */
    Integer switchEav(Long id, SwitchEav switchEav, String token);
}