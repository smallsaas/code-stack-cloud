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
@TableName("lc_api_relation_model_item")
@ApiModel(value="ApiRelationModelItem对象", description="")
public class ApiRelationModelItem extends Model<ApiRelationModelItem> {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "主键id")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty(value = "主表关联字段ID")
      private Long relationModelId;

      @ApiModelProperty(value = "从关联表ID")
      private Long tableModelId;

      @ApiModelProperty(value = "从表数据的一对多键值")
      private String dataKey;

      @ApiModelProperty(value = "[master,slave,peer,relation,category]")
      private String relationType;

      @ApiModelProperty(value = "默认绑定主表id字段")
      private String relationData;

      @ApiModelProperty(value = "多字段关联JSON表示")
      private String multiRelationDataJson;

    
    public Long getId() {
        return id;
    }

      public ApiRelationModelItem setId(Long id) {
          this.id = id;
          return this;
      }
    
    public Long getRelationModelId() {
        return relationModelId;
    }

      public ApiRelationModelItem setRelationModelId(Long relationModelId) {
          this.relationModelId = relationModelId;
          return this;
      }
    
    public Long getTableModelId() {
        return tableModelId;
    }

      public ApiRelationModelItem setTableModelId(Long tableModelId) {
          this.tableModelId = tableModelId;
          return this;
      }
    
    public String getDataKey() {
        return dataKey;
    }

      public ApiRelationModelItem setDataKey(String dataKey) {
          this.dataKey = dataKey;
          return this;
      }
    
    public String getRelationType() {
        return relationType;
    }

      public ApiRelationModelItem setRelationType(String relationType) {
          this.relationType = relationType;
          return this;
      }
    
    public String getRelationData() {
        return relationData;
    }

      public ApiRelationModelItem setRelationData(String relationData) {
          this.relationData = relationData;
          return this;
      }
    
    public String getMultiRelationDataJson() {
        return multiRelationDataJson;
    }

      public ApiRelationModelItem setMultiRelationDataJson(String multiRelationDataJson) {
          this.multiRelationDataJson = multiRelationDataJson;
          return this;
      }

      public static final String ID = "id";

      public static final String RELATION_MODEL_ID = "relation_model_id";

      public static final String TABLE_MODEL_ID = "table_model_id";

      public static final String DATA_KEY = "data_key";

      public static final String RELATION_TYPE = "relation_type";

      public static final String RELATION_DATA = "relation_data";

      public static final String MULTI_RELATION_DATA_JSON = "multi_relation_data_json";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "ApiRelationModelItem{" +
              "id=" + id +
                  ", relationModelId=" + relationModelId +
                  ", tableModelId=" + tableModelId +
                  ", dataKey=" + dataKey +
                  ", relationType=" + relationType +
                  ", relationData=" + relationData +
                  ", multiRelationDataJson=" + multiRelationDataJson +
              "}";
    }
}
