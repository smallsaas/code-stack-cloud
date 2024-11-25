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
 * @since 2021-09-06
 */
@TableName("lc_low_filters")
public class LowFilters extends Model<LowFilters> {

    private static final long serialVersionUID=1L;

      /**
     * 主键id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 搜索字段分布名称
     */
      private String contentLayout;

      /**
     * 引用字段组件属性
     */
      private String searchFields;

      /**
     * 搜索框内提示
     */
      private String defaultSearchHint;

      /**
     * 所属页面ID
     */
      private Integer pageId;

      /**
     * 绑定字段名称
     */
      private String fieldName;

      /**
     * 组件类型
     */
      private String fieldType;

      /**
     * 搜索标题
     */
      private String fieldTitle;

    
    public Long getId() {
        return id;
    }

      public LowFilters setId(Long id) {
          this.id = id;
          return this;
      }
    
    public String getContentLayout() {
        return contentLayout;
    }

      public LowFilters setContentLayout(String contentLayout) {
          this.contentLayout = contentLayout;
          return this;
      }
    
    public String getSearchFields() {
        return searchFields;
    }

      public LowFilters setSearchFields(String searchFields) {
          this.searchFields = searchFields;
          return this;
      }
    
    public String getDefaultSearchHint() {
        return defaultSearchHint;
    }

      public LowFilters setDefaultSearchHint(String defaultSearchHint) {
          this.defaultSearchHint = defaultSearchHint;
          return this;
      }
    
    public Integer getPageId() {
        return pageId;
    }

      public LowFilters setPageId(Integer pageId) {
          this.pageId = pageId;
          return this;
      }
    
    public String getFieldName() {
        return fieldName;
    }

      public LowFilters setFieldName(String fieldName) {
          this.fieldName = fieldName;
          return this;
      }
    
    public String getFieldType() {
        return fieldType;
    }

      public LowFilters setFieldType(String fieldType) {
          this.fieldType = fieldType;
          return this;
      }
    
    public String getFieldTitle() {
        return fieldTitle;
    }

      public LowFilters setFieldTitle(String fieldTitle) {
          this.fieldTitle = fieldTitle;
          return this;
      }

      public static final String ID = "id";

      public static final String CONTENT_LAYOUT = "content_layout";

      public static final String SEARCH_FIELDS = "search_fields";

      public static final String DEFAULT_SEARCH_HINT = "default_search_hint";

      public static final String PAGE_ID = "page_id";

      public static final String FIELD_NAME = "field_name";

      public static final String FIELD_TYPE = "field_type";

      public static final String FIELD_TITLE = "field_title";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "LowFilters{" +
              "id=" + id +
                  ", contentLayout=" + contentLayout +
                  ", searchFields=" + searchFields +
                  ", defaultSearchHint=" + defaultSearchHint +
                  ", pageId=" + pageId +
                  ", fieldName=" + fieldName +
                  ", fieldType=" + fieldType +
                  ", fieldTitle=" + fieldTitle +
              "}";

    }
}
