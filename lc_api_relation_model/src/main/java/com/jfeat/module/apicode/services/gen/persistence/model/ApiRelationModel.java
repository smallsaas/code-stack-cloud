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
 * @since 2023-12-04
 */
@TableName("lc_api_relation_model")
@ApiModel(value="ApiRelationModel对象", description="")
public class ApiRelationModel extends Model<ApiRelationModel> {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "主键id")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty(value = "[onemany,manymany,category]")
      private String relationship;

      @ApiModelProperty(value = "标识名称")
      private String modelName;

      @ApiModelProperty(value = "显示名称")
      private String name;

      @ApiModelProperty(value = "模板说明")
      private String notes;

    
    public Long getId() {
        return id;
    }

      public ApiRelationModel setId(Long id) {
          this.id = id;
          return this;
      }
    
    public String getRelationship() {
        return relationship;
    }

      public ApiRelationModel setRelationship(String relationship) {
          this.relationship = relationship;
          return this;
      }
    
    public String getModelName() {
        return modelName;
    }

      public ApiRelationModel setModelName(String modelName) {
          this.modelName = modelName;
          return this;
      }
    
    public String getName() {
        return name;
    }

      public ApiRelationModel setName(String name) {
          this.name = name;
          return this;
      }
    
    public String getNotes() {
        return notes;
    }

      public ApiRelationModel setNotes(String notes) {
          this.notes = notes;
          return this;
      }

      public static final String ID = "id";

      public static final String RELATIONSHIP = "relationship";

      public static final String MODEL_NAME = "model_name";

      public static final String NAME = "name";

      public static final String NOTES = "notes";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "ApiRelationModel{" +
              "id=" + id +
                  ", relationship=" + relationship +
                  ", modelName=" + modelName +
                  ", name=" + name +
                  ", notes=" + notes +
              "}";
    }
}
