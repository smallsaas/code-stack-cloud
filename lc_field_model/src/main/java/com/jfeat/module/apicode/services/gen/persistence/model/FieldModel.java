package com.jfeat.module.apicode.services.gen.persistence.model;

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
 * @since 2023-11-24
 */
@TableName("lc_field_model")
@ApiModel(value="FieldModel对象", description="")
public class FieldModel extends Model<FieldModel> {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "主键id")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty(value = "模板标识名称")
      private String modelName;

      @ApiModelProperty(value = "模板显示名称")
      private String name;

      @ApiModelProperty(value = "默认值")
      private String defaultValue;

      @ApiModelProperty(value = "是否非空")
      private Integer isNotNull;

      @ApiModelProperty(value = "是否唯一")
      private Integer isUnique;

      @ApiModelProperty(value = "可选的字段名称")
      private String optionalFieldName;

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

      public FieldModel setId(Long id) {
          this.id = id;
          return this;
      }
    
    public String getModelName() {
        return modelName;
    }

      public FieldModel setModelName(String modelName) {
          this.modelName = modelName;
          return this;
      }
    
    public String getName() {
        return name;
    }

      public FieldModel setName(String name) {
          this.name = name;
          return this;
      }
    
    public String getDefaultValue() {
        return defaultValue;
    }

      public FieldModel setDefaultValue(String defaultValue) {
          this.defaultValue = defaultValue;
          return this;
      }
    
    public Integer getIsNotNull() {
        return isNotNull;
    }

      public FieldModel setIsNotNull(Integer isNotNull) {
          this.isNotNull = isNotNull;
          return this;
      }
    
    public Integer getIsUnique() {
        return isUnique;
    }

      public FieldModel setIsUnique(Integer isUnique) {
          this.isUnique = isUnique;
          return this;
      }
    
    public String getOptionalFieldName() {
        return optionalFieldName;
    }

      public FieldModel setOptionalFieldName(String optionalFieldName) {
          this.optionalFieldName = optionalFieldName;
          return this;
      }
    
    public String getDataType() {
        return dataType;
    }

      public FieldModel setDataType(String dataType) {
          this.dataType = dataType;
          return this;
      }
    
    public Integer getFieldLength() {
        return fieldLength;
    }

      public FieldModel setFieldLength(Integer fieldLength) {
          this.fieldLength = fieldLength;
          return this;
      }
    
    public Integer getFieldFloatLength() {
        return fieldFloatLength;
    }

      public FieldModel setFieldFloatLength(Integer fieldFloatLength) {
          this.fieldFloatLength = fieldFloatLength;
          return this;
      }
    
    public String getComments() {
        return comments;
    }

      public FieldModel setComments(String comments) {
          this.comments = comments;
          return this;
      }

      public static final String ID = "id";

      public static final String MODEL_NAME = "model_name";

      public static final String NAME = "name";

      public static final String DEFAULT_VALUE = "default_value";

      public static final String IS_NOT_NULL = "is_not_null";

      public static final String IS_UNIQUE = "is_unique";

      public static final String OPTIONAL_FIELD_NAME = "optional_field_name";

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
        return "FieldModel{" +
              "id=" + id +
                  ", modelName=" + modelName +
                  ", name=" + name +
                  ", defaultValue=" + defaultValue +
                  ", isNotNull=" + isNotNull +
                  ", isUnique=" + isUnique +
                  ", optionalFieldName=" + optionalFieldName +
                  ", dataType=" + dataType +
                  ", fieldLength=" + fieldLength +
                  ", fieldFloatLength=" + fieldFloatLength +
                  ", comments=" + comments +
              "}";
    }
}
