package com.jfeat.module.crudcode.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code generator
 * @since 2021-08-31
 */
@TableName("lc_low_field_properties")
public class LowFieldProperties extends Model<LowFieldProperties> {

    private static final long serialVersionUID=1L;

      /**
     * 主键id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 组件标识名称
     */
      private String fieldItemName;

      /**
     * 属性名称
     */
      private String propertyName;

      /**
     * 属性值
     */
      private String propertyValue;

      /**
     * 组件属性作用域
     */
      private String scope;

      /**
     * 所属字段组件ID
     */
      private Integer fieldId;

    
    public Long getId() {
        return id;
    }

      public LowFieldProperties setId(Long id) {
          this.id = id;
          return this;
      }
    
    public String getFieldItemName() {
        return fieldItemName;
    }

      public LowFieldProperties setFieldItemName(String fieldItemName) {
          this.fieldItemName = fieldItemName;
          return this;
      }
    
    public String getPropertyName() {
        return propertyName;
    }

      public LowFieldProperties setPropertyName(String propertyName) {
          this.propertyName = propertyName;
          return this;
      }
    
    public String getPropertyValue() {
        return propertyValue;
    }

      public LowFieldProperties setPropertyValue(String propertyValue) {
          this.propertyValue = propertyValue;
          return this;
      }
    
    public String getScope() {
        return scope;
    }

      public LowFieldProperties setScope(String scope) {
          this.scope = scope;
          return this;
      }
    
    public Integer getFieldId() {
        return fieldId;
    }

      public LowFieldProperties setFieldId(Integer fieldId) {
          this.fieldId = fieldId;
          return this;
      }

      public static final String ID = "id";

      public static final String FIELD_ITEM_NAME = "field_item_name";

      public static final String PROPERTY_NAME = "property_name";

      public static final String PROPERTY_VALUE = "property_value";

      public static final String SCOPE = "scope";

      public static final String FIELD_ID = "field_id";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "LowFieldProperties{" +
              "id=" + id +
                  ", fieldItemName=" + fieldItemName +
                  ", propertyName=" + propertyName +
                  ", propertyValue=" + propertyValue +
                  ", scope=" + scope +
                  ", fieldId=" + fieldId +
              "}";
    }
}
