package com.jfeat.module.apicode.services.gen.persistence.model.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModel;
import com.jfeat.module.apicode.services.gen.persistence.model.DO.ApiTableModelField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @description: TODO
 * @project: code-stack-cloud
 * @date: 2024/5/6 11:15
 * @author: hhhhhtao
 */
@Data
public class ApiTableModelDTO {

    /**
     * id
     */
    private Long id;

    /**
     * 数据模型标识，相当于英文名
     */
    private String modelName;

    /**
     * 数据模型名，相当于中文名
     */
    private String name;

    /**
     * 描述
     */
    private String notes;

    /**
     * 字段列表
     */
    private List<ApiTableModelField> fields;

    /**
     * 子数据模型列表
     */
    private List<ApiTableModelDTO> subModels;
}
