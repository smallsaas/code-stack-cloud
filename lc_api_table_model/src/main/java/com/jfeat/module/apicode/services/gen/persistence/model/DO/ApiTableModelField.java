package com.jfeat.module.apicode.services.gen.persistence.model.DO;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code generator
 * @since 2023-11-23
 */
@TableName("lc_api_table_model_field")
@ApiModel(value="ApiTableModelField对象", description="")
public class ApiTableModelField extends Model<ApiTableModelField> {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "主键id")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty(value = "主表关联ID")
      private Long tableModelId;

      @ApiModelProperty(value = "字段模型关联名称")
      @TableField(exist = false)
      private String tableModelName;

      @ApiModelProperty(value = "字段模型关联ID")
      private Long fieldModelId;

      @ApiModelProperty(value = "标准字段绑定名称")
      private String defaultFieldName;

      @ApiModelProperty(value = "字段名称")
      private String fieldName;

      @ApiModelProperty(value = "默认值")
      private String defaultValue;

      @ApiModelProperty(value = "是否非空")
      private Integer isNotNull;

      @ApiModelProperty(value = "是否唯一")
      private Integer isUnique;

      @ApiModelProperty(value = "字段类型")
      private String dataType;

      @ApiModelProperty(value = "字段大小")
      private Integer fieldLength;

      @ApiModelProperty(value = "字段小数大小")
      private Integer fieldFloatLength;

      @ApiModelProperty(value = "注释")
      private String comments;

    
    public Long getId() {
        return id;
    }

      public ApiTableModelField setId(Long id) {
          this.id = id;
          return this;
      }
    
    public Long getTableModelId() {
        return tableModelId;
    }

      public ApiTableModelField setTableModelId(Long tableModelId) {
          this.tableModelId = tableModelId;
          return this;
      }
    
    public Long getFieldModelId() {
        return fieldModelId;
    }

      public ApiTableModelField setFieldModelId(Long fieldModelId) {
          this.fieldModelId = fieldModelId;
          return this;
      }
    
    public String getDefaultFieldName() {
        return defaultFieldName;
    }

      public ApiTableModelField setDefaultFieldName(String defaultFieldName) {
          this.defaultFieldName = defaultFieldName;
          return this;
      }
    
    public String getFieldName() {
        return fieldName;
    }

      public ApiTableModelField setFieldName(String fieldName) {
          this.fieldName = fieldName;
          return this;
      }
    
    public String getDefaultValue() {
        return defaultValue;
    }

      public ApiTableModelField setDefaultValue(String defaultValue) {
          this.defaultValue = defaultValue;
          return this;
      }
    
    public Integer getIsNotNull() {
        return isNotNull;
    }

      public ApiTableModelField setIsNotNull(Integer isNotNull) {
          this.isNotNull = isNotNull;
          return this;
      }
    
    public Integer getIsUnique() {
        return isUnique;
    }

      public ApiTableModelField setIsUnique(Integer isUnique) {
          this.isUnique = isUnique;
          return this;
      }
    
    public String getDataType() {
        return dataType;
    }

      public ApiTableModelField setDataType(String dataType) {
          this.dataType = dataType;
          return this;
      }
    
    public Integer getFieldLength() {
        return fieldLength;
    }

      public ApiTableModelField setFieldLength(Integer fieldLength) {
          this.fieldLength = fieldLength;
          return this;
      }
    
    public Integer getFieldFloatLength() {
        return fieldFloatLength;
    }

      public ApiTableModelField setFieldFloatLength(Integer fieldFloatLength) {
          this.fieldFloatLength = fieldFloatLength;
          return this;
      }
    
    public String getComments() {
        return comments;
    }

      public ApiTableModelField setComments(String comments) {
          this.comments = comments;
          return this;
      }

      public static final String ID = "id";

      public static final String TABLE_MODEL_ID = "table_model_id";

      public static final String FIELD_MODEL_ID = "field_model_id";

      public static final String DEFAULT_FIELD_NAME = "default_field_name";

      public static final String FIELD_NAME = "field_name";

      public static final String DEFAULT_VALUE = "default_value";

      public static final String IS_NOT_NULL = "is_not_null";

      public static final String IS_UNIQUE = "is_unique";

      public static final String DATA_TYPE = "data_type";

      public static final String FIELD_LENGTH = "field_length";

      public static final String FIELD_FLOAT_LENGTH = "field_float_length";

      public static final String COMMENTS = "comments";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "ApiTableModelField{" +
              "id=" + id +
                  ", tableModelId=" + tableModelId +
                  ", fieldModelId=" + fieldModelId +
                  ", defaultFieldName=" + defaultFieldName +
                  ", fieldName=" + fieldName +
                  ", defaultValue=" + defaultValue +
                  ", isNotNull=" + isNotNull +
                  ", isUnique=" + isUnique +
                  ", dataType=" + dataType +
                  ", fieldLength=" + fieldLength +
                  ", fieldFloatLength=" + fieldFloatLength +
                  ", comments=" + comments +
              "}";
    }

    public String getTableModelName() {
        return tableModelName;
    }

    public void setTableModelName(String tableModelName) {
        this.tableModelName = tableModelName;
    }
}
