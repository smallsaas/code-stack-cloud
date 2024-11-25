package com.jfeat.module.apicode.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.module.apicode.constant.CgParamConst;
import com.jfeat.module.apicode.constant.RelationTypeEnum;
import com.jfeat.module.apicode.services.domain.dao.QueryApiRelationModelDao;
import com.jfeat.module.apicode.services.domain.service.ApiRelationModelService;
import com.jfeat.module.apicode.services.domain.service.ApiTableModelOverModelService;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiRelationModel;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiRelationModelTables;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModel;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.ApiRelationModelDTO;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.CgParam;
import com.jfeat.module.apicode.util.HttpUtils;
import com.jfeat.module.apicode.util.SnowflakeIdUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO
 * @project: code-stack-cloud
 * @date: 2024/4/16 14:17
 * @author: hhhhhtao
 */
@Service("ApiRelationModelService")
public class ApiRelationModelServiceImpl implements ApiRelationModelService {

    private static final Logger log = LoggerFactory.getLogger(ApiRelationModelServiceImpl.class);
    @Resource(type = QueryApiRelationModelDao.class)
    QueryApiRelationModelDao queryApiRelationModelDao;

    @Resource
    ApiTableModelOverModelService apiTableModelOverModelService;

    @Value("${code-generator.url}")
    String codeGeneratorUrl;

    /**
     * 新增
     *
     * @param model api关系模型
     */
    @Override
    public ApiRelationModel insert(ApiRelationModel model) {
        queryApiRelationModelDao.insert(model);
        return queryApiRelationModelDao.selectById(model.getId());
    }

    /**
     * 获取
     *
     * @param id api关系模型id
     */
    @Override
    public ApiRelationModel get(Long id) {
        return queryApiRelationModelDao.selectById(id);
    }

    /**
     * 部分更新
     *
     * @param model api关系模型
     * @return 影响行数
     */
    @Override
    public int update(ApiRelationModel model) {
        return queryApiRelationModelDao.updateById(model);
    }

    /**
     * 删除
     *
     * @param id api关系模型id
     * @return 影响行数
     */
    @Override
    public int delete(Long id) {
        return queryApiRelationModelDao.deleteById(id);
    }

    /**
     * 分页查询
     *
     * @param page             Page对象
     * @param model api关系模型
     * @return Page<ApiRelationModel>页面对象
     */
    @Override
    public Page<ApiRelationModel> page(Page<ApiRelationModel> page, ApiRelationModel model) {
        // 查询条件构建
        LambdaQueryWrapper<ApiRelationModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(model.getId() != null, ApiRelationModel::getId, model.getId());
        queryWrapper.eq(StringUtils.isNotBlank(model.getModelName()), ApiRelationModel::getModelName, model.getModelName());
        queryWrapper.eq(StringUtils.isNotBlank(model.getName()), ApiRelationModel::getName, model.getName());
        queryWrapper.eq(model.getRelationship() != null, ApiRelationModel::getRelationship, model.getRelationship());
        queryWrapper.eq(StringUtils.isNotBlank(model.getNotes()), ApiRelationModel::getNotes, model.getNotes());
        // 执行查询
        return queryApiRelationModelDao.selectPage(page, queryWrapper);
    }

    /**
     * 生成项目代码
     *
     * @param id api关系模型id
     */
    @Override
    public JSONObject generateProjectCode(Long id, CgParam cgParam) {
        // 获取关系模型详情
        ApiRelationModelDTO relationModelDTO = queryApiRelationModelDao.getDTO(id);
        if (relationModelDTO == null) {
            throw new BusinessException(BusinessCode.CodeBase, "无效关系模型");
        }
        if (relationModelDTO.getRelationTables().isEmpty()) {
            throw new BusinessException(BusinessCode.CodeBase, "关系模型中缺少实际的表关系");
        }

        // 构建参数
        StringBuilder sql = new StringBuilder();
        List<CgParam.Slave> slaves = new ArrayList<>();
        // 项目名称为关系模型名称
        cgParam.setProject(relationModelDTO.getModelName() + "_" + SnowflakeIdUtils.getInstance().nextId());
        for (ApiRelationModelTables e : relationModelDTO.getRelationTables()) {
            // 获取数据模型简单信息
            ApiTableModel tableModel = apiTableModelOverModelService.get(e.getTableModelId());
            if (tableModel == null) {
                throw new BusinessException(BusinessCode.CodeBase, String.format("id = %s 的数据模型无效，请检查", e.getTableModelId()));
            }
            // 获取sql字符串
            String currentTableSql = apiTableModelOverModelService.generateCreateTableSql(tableModel.getId());
            if (StringUtils.isBlank(currentTableSql)) {
                log.warn("id={}的数据模型生成sql为空", tableModel.getId());
                throw new BusinessException(BusinessCode.CodeBase, String.format("数据模型名=`%s`的数据模型没有字段信息，请确认是否已配置字段", tableModel.getModelName()));
            }
            sql.append(handleCgRequiredSql(currentTableSql)).append(" ");
            // 判断关系, 根据关系设定参数
            if (RelationTypeEnum.MASTER.equals(e.getRelationType())) { // 主表
                cgParam.setModule("cg"); // 默认为cg
                cgParam.setTableName(tableModel.getModelName());

            } else if (RelationTypeEnum.SLAVE.equals(e.getRelationType())) { // 从表
                CgParam.Slave slave = new CgParam.Slave();
                slave.setTableName(tableModel.getModelName());
                if (StringUtils.isBlank(e.getRelationData())) {
                    throw new BusinessException(BusinessCode.CodeBase, String.format("数据模型名=`%s`的从表缺少了绑定主表的字段", tableModel.getModelName()));
                }
                slave.setMasterId(e.getRelationData());
                slaves.add(slave);
            } else if (RelationTypeEnum.RELATION.equals(e.getRelationType())) { // 中间表
                throw new BusinessException(BusinessCode.CodeBase, "中间表关系暂时未完成");
            } else {
                // 暂时不支持别的关系
                throw new BusinessException(BusinessCode.CodeBase, "不支持的关系，目前只支持主表，从表，中间表");
            }
        }
        cgParam.setSql(sql.toString());
        cgParam.setSlaves(slaves);

        // 请求 代码生成
        JSONObject result = HttpUtils.post(codeGeneratorUrl, JSON.parseObject(JSON.toJSONString(cgParam)));
        if (result.get("code") == null || result.getInteger("code") != 200) {
            log.warn("代码生成api调用错误：请求url:{}, 请求方法:{}, 请求参数：{}, 返回结果：{}", codeGeneratorUrl, "POST", cgParam, result);
            throw new BusinessException(BusinessCode.CodeBase, "代码生成异常");
        }

        return result;
    }

    /**
     * 处理成生成代码工具api所需要的sql格式。
     * <p>因为生成代码api中对于sql没有很规范，所以会导致正常的建表sql无法正常使用，所以这里需要针对性做处理</p>
     * @param untreatedSql
     * @return
     */
    private String handleCgRequiredSql(String untreatedSql) {
        // 1、去除唯一索引
        untreatedSql = untreatedSql.replaceAll("\n", "");
//        String beginStr = untreatedSql.substring(0, untreatedSql.indexOf(",UNIQUE"));
        String beginStr = StringUtils.substringBefore(untreatedSql, ",UNIQUE");
//        String endStr = untreatedSql.substring(untreatedSql.lastIndexOf(")"));
        String endStr =  ")" + StringUtils.substringAfterLast(untreatedSql, ")");
        String processedSql = beginStr + endStr;

        return processedSql;
    }

}
