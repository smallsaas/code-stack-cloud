package com.jfeat.module.apicode.services.domain.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.http.server.HttpServerResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.CRUD;
import com.jfeat.crud.plus.CRUDFilterResult;
import com.jfeat.crud.plus.CRUDHandler;
import com.jfeat.crud.plus.CRUDObject;
import com.jfeat.module.apicode.services.domain.dao.QueryApiTableModelDao;
import com.jfeat.module.apicode.services.domain.model.ApiTableModelRecord;
import com.jfeat.module.apicode.services.domain.service.ApiTableModelFieldService;
import com.jfeat.module.apicode.services.domain.service.ApiTableModelOverModelService;
import com.jfeat.module.apicode.services.gen.crud.model.ApiTableModelModel;
import com.jfeat.module.apicode.services.gen.crud.service.impl.CRUDApiTableModelOverModelServiceImpl;
import com.jfeat.module.apicode.services.gen.persistence.dao.ApiTableModelFieldMapper;
import com.jfeat.module.apicode.services.gen.persistence.dao.FieldModelMapper;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiSubTableModel;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModel;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModelField;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.ApiTableModelDTO;
import com.jfeat.module.apicode.services.gen.persistence.model.DTO.SwitchEav;
import com.jfeat.module.apicode.services.gen.persistence.model.FieldModel;
import com.jfeat.module.apicode.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("apiTableModelService")
public class ApiTableModelOverModelServiceImpl extends CRUDApiTableModelOverModelServiceImpl implements ApiTableModelOverModelService {

    @Resource
    FieldModelMapper fieldModelMapper;

    @Resource
    ApiTableModelFieldMapper apiTableModelFieldMapper;

    @Resource
    QueryApiTableModelDao queryApiTableModelDao;

    @Resource
    ApiTableModelFieldService apiTableModelFieldService;

    @Override
    protected String entityName() {
        return "ApiTableModel";
    }

    @Value("${switch-eav.url}")
    String switchEavUrl;

    /**
     * handle fields and subtables
     * @return
     */
    @Override
    public Integer updateMaster(ApiTableModelModel model, CRUDFilterResult<ApiTableModel> filter, String itemsKey, CRUDHandler<ApiTableModel, ApiTableModelModel> handler, int updateOptions){
        // pre handle model for fields, and subtables
        if(Objects.nonNull(model.getFields())){

            var newFields = new ArrayList<ApiTableModelField>();
            for(ApiTableModelField field : model.getFields()){
                Assert.notNull(field.getFieldName(), "fieldName should not be null !");

                // check default filed name
                if(StringUtils.isNotBlank(field.getDefaultFieldName())){
                    FieldModel fieldModel = new FieldModel();
                    fieldModel.setModelName(field.getDefaultFieldName());

                    var one = fieldModelMapper.selectOne(new QueryWrapper<>(fieldModel));
                    Assert.notNull(one, "Bad Request: "+field.getDefaultFieldName()+" invalid defaultFieldName !");

                    one.setId(null);
                    field = CRUD.copyFromIfEmpty(field, one);
                }else if(Objects.nonNull(field.getFieldModelId()) && field.getFieldModelId()>0){
                    var one = fieldModelMapper.selectById(field.getTableModelId());
                    Assert.notNull(one, "Bad Request: "+field.getDefaultFieldName()+" invalid defaultFieldName !");

                    one.setId(null);
                    field = CRUD.copyFromIfEmpty(field, one);
                }

                newFields.add(field);
            }
            model.setFields(newFields);
        }

        // handle subtables
        if(Objects.nonNull(model.getSubtables())){
            // set sub table modelName and name
            for(ApiSubTableModel subtable : model.getSubtables()){
                Assert.isTrue(Objects.nonNull(subtable.getSubTableId()) || Objects.nonNull(subtable.getSubTableModelName()), "sub_table_id or sub_table_model_name should not be null !");

                var one = Objects.nonNull(subtable.getSubTableId())?
                            apiTableModelMapper.selectById(subtable.getSubTableId())
                        : apiTableModelMapper.selectOne(new LambdaQueryWrapper<ApiTableModel>().eq(ApiTableModel::getModelName, subtable.getSubTableModelName()));

                Assert.notNull(one, "Bad Request: "+subtable.getSubTableId()+" invalid subTableId !");
                subtable.setSubTableModelName(one.getModelName());
                subtable.setSubTableName(one.getName());
                subtable.setSubTableId(one.getId());
            }
        }


        return super.updateMaster(model, filter, itemsKey, handler, updateOptions);
    }


    @Override
    public CRUDObject<ApiTableModelModel> retrieveMasterModel(Long id){
        var model =  super.retrieveMasterModel(id).toJavaObject(ApiTableModelModel.class);

        // convert all subtables fields into current fields
        if(Objects.nonNull(model.getSubtables())) {
            for (ApiSubTableModel subtable : model.getSubtables()) {
                var subfields = new LambdaQueryChainWrapper<>(apiTableModelFieldMapper)
                        .eq(ApiTableModelField::getTableModelId, subtable.getSubTableId())
                        .select();

                model.getFields().addAll(subfields.list());
            }
        }

        return new CRUDObject<ApiTableModelModel>().from(model);
    }

    /**
     * 获取数据模型
     *
     * @param id 数据模型id
     */
    @Override
    public ApiTableModel get(Long id) {
        return queryApiTableModelDao.selectById(id);
    }

    /**
     * 生成建表sql字符串
     * <p>如果数据模型中没有字段则会返回null</p>
     *
     * @param id 数据模型id
     */
    @Override
    public String generateCreateTableSql(Long id) {
        // 获取数据模型详情
        ApiTableModelDTO model = queryApiTableModelDao.getDTO(id);

        // 开始构建sql字符串
        StringBuilder sql = new StringBuilder();
        // 删除语句
        sql.append(String.format("DROP TABLE IF EXISTS `%s`;\n", model.getModelName()));
        // 建表语句
        sql.append(String.format("CREATE TABLE `%s` (\n", model.getModelName()));
        // 获取数据模型字段
        List<ApiTableModelField> fields = listTableModelFields(model);
        // id 默认
        sql.append("`id` bigint unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '主键id'");
        List<ApiTableModelField> uniqueFields = new ArrayList<>(); // 该列表用于存放唯一的字段，因为唯一索引只能在表定义的最后添加，所以需要在字段都定义完后再使用这个数组进行添加
        for (int i = 0; i < fields.size(); i++) {
            ApiTableModelField field = fields.get(i);
            sql.append(", \n");
            // “{字段名} {字段类型}{长度} {非空} COMMENT {注释}”
            sql.append(String.format("`%s` %s%s %s COMMENT '%s'", field.getFieldName(),
                    field.getDataType(),
                    field.getFieldLength() == null ? "" : "(" + field.getFieldLength() + ")",
                    Integer.valueOf("1").equals(field.getIsNotNull()) ? "NOT NULL" : "",
                    field.getComments() == null ? "" : field.getComments()));
            // 判断是否是唯一字段
            if (Integer.valueOf("1").equals(field.getIsUnique())) {
                uniqueFields.add(field);
            }
            // 判断字段是否已经定义完成，以完成的话进行添加唯一索引
            if (i == fields.size() - 1) {
                for (ApiTableModelField uniqueField : uniqueFields) {
                    sql.append(",\n");
                    sql.append(String.format("UNIQUE KEY `%s` (`%s`) USING BTREE COMMENT '%s'",
                            uniqueField.getFieldName(),
                            uniqueField.getFieldName(),
                            uniqueField.getComments() == null ? "" : uniqueField.getComments()));
                }
            }
        }
        // 数据库引擎 和 默认字符集
        sql.append("\n");
        sql.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;\n");
        return sql.toString();
    }

    /**
     * 将数据模型转换为eav
     *
     * @param id 数据模型id
     * @param switchEav 转换eavDTO
     * @param token token令牌
     */
    @Override
    public Integer switchEav(Long id, SwitchEav switchEav, String token) {
        // 获取数据模型详情
        ApiTableModelDTO model = queryApiTableModelDao.getDTO(id);
        // 获取字段列表
        List<ApiTableModelField> fields = listTableModelFields(model);
        // 封装进switchEav
        switchEav.setFields(fields);
        // 发送http请求eav-api进行处理
        JSONObject result = HttpUtils.post(switchEavUrl, JSON.parseObject(JSON.toJSONString(switchEav)), token);
        // 判断请求是否成功
        if (result.getInteger("code") != 200) {
            throw new BusinessException(result.getInteger("code"), result.getString("message"));
        }
        return result.getInteger("data");
    }

    /**
     * 获取数据模型字段列表
     * <p>进行了字段去重处理</p>
     *
     * @param model 数据模型实体
     * @return 数据模型的字段列表
     */
    private List<ApiTableModelField> listTableModelFields(ApiTableModelDTO model) {
        // 构建字段定义部分
        List<ApiTableModelField> fields = new ArrayList<>(); // 字段列表
        // 将父模型和子模型的字段加入到字段列表中
        fields.addAll(model.getFields());
        for (ApiTableModelDTO subModel : model.getSubModels()) {
            if (subModel.getFields() != null && !subModel.getFields().isEmpty()) {
                fields.addAll(subModel.getFields());
            }
        }
        // 如果字段列表为空则直接返回null
        if (fields.isEmpty()) {
            return null;
        }
        // 字段去重
        fields = new ArrayList<>(fields.stream()
                .collect(Collectors.toMap(ApiTableModelField::getFieldName, e -> e, (existing, replacement) -> existing))
                .values());
        return fields;
    }
}
