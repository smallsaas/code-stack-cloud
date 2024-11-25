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
@TableName("lc_api_sub_table_model")
@ApiModel(value="ApiSubTableModel对象", description="")
public class ApiSubTableModel extends Model<ApiSubTableModel> {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "主键id")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty(value = "主表关联ID")
      private Long tableModelId;

      @ApiModelProperty(value = "主表标识名称")
      @TableField(exist = false)
      private String tableModelName;

      @ApiModelProperty(value = "主表显示名称")
      @TableField(exist = false)
      private String tableName;

      @ApiModelProperty(value = "主表作为一多对的子表")
      private Long subTableId;

      @ApiModelProperty(value = "子表标识名称（冗余主表名称）")
      private String subTableModelName;

      @ApiModelProperty(value = "子表显示名称(冗余主表名称)")
      private String subTableName;


    
    public Long getId() {
        return id;
    }

      public ApiSubTableModel setId(Long id) {
          this.id = id;
          return this;
      }
    
    public Long getTableModelId() {
        return tableModelId;
    }

      public ApiSubTableModel setTableModelId(Long tableModelId) {
          this.tableModelId = tableModelId;
          return this;
      }
    
    public Long getSubTableId() {
        return subTableId;
    }

      public ApiSubTableModel setSubTableId(Long subTableId) {
          this.subTableId = subTableId;
          return this;
      }
    
    public String getSubTableModelName() {
        return subTableModelName;
    }

      public ApiSubTableModel setSubTableModelName(String subTableModelName) {
          this.subTableModelName = subTableModelName;
          return this;
      }
    
    public String getSubTableName() {
        return subTableName;
    }

      public ApiSubTableModel setSubTableName(String subTableName) {
          this.subTableName = subTableName;
          return this;
      }

      public static final String ID = "id";

      public static final String TABLE_MODEL_ID = "table_model_id";

      public static final String SUB_TABLE_ID = "sub_table_id";

      public static final String SUB_TABLE_MODEL_NAME = "sub_table_model_name";

      public static final String SUB_TABLE_NAME = "sub_table_name";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "ApiSubTableModel{" +
              "id=" + id +
                  ", tableModelId=" + tableModelId +
                  ", subTableId=" + subTableId +
                  ", subTableModelName=" + subTableModelName +
                  ", subTableName=" + subTableName +
              "}";
    }

    public String getTableModelName() {
        return tableModelName;
    }

    public void setTableModelName(String tableModelName) {
        this.tableModelName = tableModelName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
