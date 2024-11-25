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
 * @since 2021-10-09
 */
@TableName("lc_low_fields")
public class LowFields extends Model<LowFields> {

    private static final long serialVersionUID=1L;

      /**
     * 主键id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 列表字体加粗
     */
      private String listFontWeight;

      /**
     * 列表字体大小
     */
      private Integer listFontSize;

      /**
     * 列表字体颜色
     */
      private String listFontColor;

      /**
     * 生成列表项组合
     */
      private String listColumnKey;

      /**
     * 列表项名称
     */
      private String listColumnName;

      /**
     * 列表项组件类型
     */
      private String listColumnType;

      /**
     * 列表布局
     */
      private String listColumnLayout;

      /**
     * 列表对齐
     */
      private String listColumnAlign;

      /**
     * 列表项宽度
     */
      private Integer listColumnWidth;

      /**
     * 列表项指引路由
     */
      private String listColumnReference;

      /**
     * 列表项显示格式
     */
      private String listColumnFormat;

      /**
     * 组件附加属性
     */
      private String listColumnOptions;

      /**
     * 复合列表
     */
      private String listColumnMultiType;

      /**
     * 复合列表项
     */
      private String listColumnMultiKeys;

      /**
     * 字段组件唯一名称
     */
      private String fieldItemName;

      /**
     * 绑定字段名称
     */
      private String fieldBinding;

      /**
     * 字段名称
     */
      private String fieldLabel;

      /**
     * 字段值下拉框选项
     */
      private String fieldValueOptions;

      /**
     * 数据转换
     */
      private String fieldValueFilter;

      /**
     * 字段范围
     */
      private String fieldScopes;

      /**
     * 字段范围（唯一范围）
     */
      private String fieldScope;

      /**
     * 字段表单标题
     */
      private String formFieldTitle;

      /**
     * 字段表单提示
     */
      private String formFieldHint;

      /**
     * 字段表单输入错误提示
     */
      private String formFieldTips;

      /**
     * 表单输入组件类型
     */
      private String formInputType;

      /**
     * 表单输入组件属性
     */
      private String formInputOptions;

      /**
     * 表单显示属性
     */
      private String formViewType;

      /**
     * 表单输入组件属性
     */
      private String formViewOptions;

      /**
     * 表单是否必填
     */
      private Integer formInputRequired;

      /**
     * 对输入选项进行说明
     */
      private String formFieldQuestion;

      /**
     * 所属页面id
     */
      private Long pageId;

      /**
     * 列表图片宽度
     */
      private Integer listImageWidth;

      /**
     * 列表图片高度
     */
      private Integer listImageHeight;

      /**
     * 输入框宽度
     */
      private Integer inputWidth;

      /*
      * 排序 小数在上 大数在下
      *
      * */

      private String sortNum;


      private String fieldViewOneManyOptions;

    public String getFieldViewOneManyOptions() {
        return fieldViewOneManyOptions;
    }

    public void setFieldViewOneManyOptions(String fieldViewOneManyOptions) {
        this.fieldViewOneManyOptions = fieldViewOneManyOptions;
    }

    public String getSortNum() {
        return sortNum;
    }

    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }

    public Long getId() {
        return id;
    }

      public LowFields setId(Long id) {
          this.id = id;
          return this;
      }
    
    public String getListFontWeight() {
        return listFontWeight;
    }

      public LowFields setListFontWeight(String listFontWeight) {
          this.listFontWeight = listFontWeight;
          return this;
      }
    
    public Integer getListFontSize() {
        return listFontSize;
    }

      public LowFields setListFontSize(Integer listFontSize) {
          this.listFontSize = listFontSize;
          return this;
      }
    
    public String getListFontColor() {
        return listFontColor;
    }

      public LowFields setListFontColor(String listFontColor) {
          this.listFontColor = listFontColor;
          return this;
      }
    
    public String getListColumnKey() {
        return listColumnKey;
    }

      public LowFields setListColumnKey(String listColumnKey) {
          this.listColumnKey = listColumnKey;
          return this;
      }
    
    public String getListColumnName() {
        return listColumnName;
    }

      public LowFields setListColumnName(String listColumnName) {
          this.listColumnName = listColumnName;
          return this;
      }
    
    public String getListColumnType() {
        return listColumnType;
    }

      public LowFields setListColumnType(String listColumnType) {
          this.listColumnType = listColumnType;
          return this;
      }
    
    public String getListColumnLayout() {
        return listColumnLayout;
    }

      public LowFields setListColumnLayout(String listColumnLayout) {
          this.listColumnLayout = listColumnLayout;
          return this;
      }
    
    public String getListColumnAlign() {
        return listColumnAlign;
    }

      public LowFields setListColumnAlign(String listColumnAlign) {
          this.listColumnAlign = listColumnAlign;
          return this;
      }
    
    public Integer getListColumnWidth() {
        return listColumnWidth;
    }

      public LowFields setListColumnWidth(Integer listColumnWidth) {
          this.listColumnWidth = listColumnWidth;
          return this;
      }
    
    public String getListColumnReference() {
        return listColumnReference;
    }

      public LowFields setListColumnReference(String listColumnReference) {
          this.listColumnReference = listColumnReference;
          return this;
      }
    
    public String getListColumnFormat() {
        return listColumnFormat;
    }

      public LowFields setListColumnFormat(String listColumnFormat) {
          this.listColumnFormat = listColumnFormat;
          return this;
      }
    
    public String getListColumnOptions() {
        return listColumnOptions;
    }

      public LowFields setListColumnOptions(String listColumnOptions) {
          this.listColumnOptions = listColumnOptions;
          return this;
      }
    
    public String getListColumnMultiType() {
        return listColumnMultiType;
    }

      public LowFields setListColumnMultiType(String listColumnMultiType) {
          this.listColumnMultiType = listColumnMultiType;
          return this;
      }
    
    public String getListColumnMultiKeys() {
        return listColumnMultiKeys;
    }

      public LowFields setListColumnMultiKeys(String listColumnMultiKeys) {
          this.listColumnMultiKeys = listColumnMultiKeys;
          return this;
      }
    
    public String getFieldItemName() {
        return fieldItemName;
    }

      public LowFields setFieldItemName(String fieldItemName) {
          this.fieldItemName = fieldItemName;
          return this;
      }
    
    public String getFieldBinding() {
        return fieldBinding;
    }

      public LowFields setFieldBinding(String fieldBinding) {
          this.fieldBinding = fieldBinding;
          return this;
      }
    
    public String getFieldLabel() {
        return fieldLabel;
    }

      public LowFields setFieldLabel(String fieldLabel) {
          this.fieldLabel = fieldLabel;
          return this;
      }
    
    public String getFieldValueOptions() {
        return fieldValueOptions;
    }

      public LowFields setFieldValueOptions(String fieldValueOptions) {
          this.fieldValueOptions = fieldValueOptions;
          return this;
      }
    
    public String getFieldValueFilter() {
        return fieldValueFilter;
    }

      public LowFields setFieldValueFilter(String fieldValueFilter) {
          this.fieldValueFilter = fieldValueFilter;
          return this;
      }
    
    public String getFieldScopes() {
        return fieldScopes;
    }

      public LowFields setFieldScopes(String fieldScopes) {
          this.fieldScopes = fieldScopes;
          return this;
      }
    
    public String getFieldScope() {
        return fieldScope;
    }

      public LowFields setFieldScope(String fieldScope) {
          this.fieldScope = fieldScope;
          return this;
      }
    
    public String getFormFieldTitle() {
        return formFieldTitle;
    }

      public LowFields setFormFieldTitle(String formFieldTitle) {
          this.formFieldTitle = formFieldTitle;
          return this;
      }
    
    public String getFormFieldHint() {
        return formFieldHint;
    }

      public LowFields setFormFieldHint(String formFieldHint) {
          this.formFieldHint = formFieldHint;
          return this;
      }
    
    public String getFormFieldTips() {
        return formFieldTips;
    }

      public LowFields setFormFieldTips(String formFieldTips) {
          this.formFieldTips = formFieldTips;
          return this;
      }
    
    public String getFormInputType() {
        return formInputType;
    }

      public LowFields setFormInputType(String formInputType) {
          this.formInputType = formInputType;
          return this;
      }
    
    public String getFormInputOptions() {
        return formInputOptions;
    }

      public LowFields setFormInputOptions(String formInputOptions) {
          this.formInputOptions = formInputOptions;
          return this;
      }
    
    public String getFormViewType() {
        return formViewType;
    }

      public LowFields setFormViewType(String formViewType) {
          this.formViewType = formViewType;
          return this;
      }
    
    public String getFormViewOptions() {
        return formViewOptions;
    }

      public LowFields setFormViewOptions(String formViewOptions) {
          this.formViewOptions = formViewOptions;
          return this;
      }
    
    public Integer getFormInputRequired() {
        return formInputRequired;
    }

      public LowFields setFormInputRequired(Integer formInputRequired) {
          this.formInputRequired = formInputRequired;
          return this;
      }
    
    public String getFormFieldQuestion() {
        return formFieldQuestion;
    }

      public LowFields setFormFieldQuestion(String formFieldQuestion) {
          this.formFieldQuestion = formFieldQuestion;
          return this;
      }
    
    public Long getPageId() {
        return pageId;
    }

      public LowFields setPageId(Long pageId) {
          this.pageId = pageId;
          return this;
      }
    
    public Integer getListImageWidth() {
        return listImageWidth;
    }

      public LowFields setListImageWidth(Integer listImageWidth) {
          this.listImageWidth = listImageWidth;
          return this;
      }
    
    public Integer getListImageHeight() {
        return listImageHeight;
    }

      public LowFields setListImageHeight(Integer listImageHeight) {
          this.listImageHeight = listImageHeight;
          return this;
      }
    
    public Integer getInputWidth() {
        return inputWidth;
    }

      public LowFields setInputWidth(Integer inputWidth) {
          this.inputWidth = inputWidth;
          return this;
      }

      public static final String ID = "id";

      public static final String LIST_FONT_WEIGHT = "list_font_weight";

      public static final String LIST_FONT_SIZE = "list_font_size";

      public static final String LIST_FONT_COLOR = "list_font_color";

      public static final String LIST_COLUMN_KEY = "list_column_key";

      public static final String LIST_COLUMN_NAME = "list_column_name";

      public static final String LIST_COLUMN_TYPE = "list_column_type";

      public static final String LIST_COLUMN_LAYOUT = "list_column_layout";

      public static final String LIST_COLUMN_ALIGN = "list_column_align";

      public static final String LIST_COLUMN_WIDTH = "list_column_width";

      public static final String LIST_COLUMN_REFERENCE = "list_column_reference";

      public static final String LIST_COLUMN_FORMAT = "list_column_format";

      public static final String LIST_COLUMN_OPTIONS = "list_column_options";

      public static final String LIST_COLUMN_MULTI_TYPE = "list_column_multi_type";

      public static final String LIST_COLUMN_MULTI_KEYS = "list_column_multi_keys";

      public static final String FIELD_ITEM_NAME = "field_item_name";

      public static final String FIELD_BINDING = "field_binding";

      public static final String FIELD_LABEL = "field_label";

      public static final String FIELD_VALUE_OPTIONS = "field_value_options";

      public static final String FIELD_VALUE_FILTER = "field_value_filter";

      public static final String FIELD_SCOPES = "field_scopes";

      public static final String FIELD_SCOPE = "field_scope";

      public static final String FORM_FIELD_TITLE = "form_field_title";

      public static final String FORM_FIELD_HINT = "form_field_hint";

      public static final String FORM_FIELD_TIPS = "form_field_tips";

      public static final String FORM_INPUT_TYPE = "form_input_type";

      public static final String FORM_INPUT_OPTIONS = "form_input_options";

      public static final String FORM_VIEW_TYPE = "form_view_type";

      public static final String FORM_VIEW_OPTIONS = "form_view_options";

      public static final String FORM_INPUT_REQUIRED = "form_input_required";

      public static final String FORM_FIELD_QUESTION = "form_field_question";

      public static final String PAGE_ID = "page_id";

      public static final String LIST_IMAGE_WIDTH = "list_image_width";

      public static final String LIST_IMAGE_HEIGHT = "list_image_height";

      public static final String INPUT_WIDTH = "input_width";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "LowFields{" +
              "id=" + id +
                  ", listFontWeight=" + listFontWeight +
                  ", listFontSize=" + listFontSize +
                  ", listFontColor=" + listFontColor +
                  ", listColumnKey=" + listColumnKey +
                  ", listColumnName=" + listColumnName +
                  ", listColumnType=" + listColumnType +
                  ", listColumnLayout=" + listColumnLayout +
                  ", listColumnAlign=" + listColumnAlign +
                  ", listColumnWidth=" + listColumnWidth +
                  ", listColumnReference=" + listColumnReference +
                  ", listColumnFormat=" + listColumnFormat +
                  ", listColumnOptions=" + listColumnOptions +
                  ", listColumnMultiType=" + listColumnMultiType +
                  ", listColumnMultiKeys=" + listColumnMultiKeys +
                  ", fieldItemName=" + fieldItemName +
                  ", fieldBinding=" + fieldBinding +
                  ", fieldLabel=" + fieldLabel +
                  ", fieldValueOptions=" + fieldValueOptions +
                  ", fieldValueFilter=" + fieldValueFilter +
                  ", fieldScopes=" + fieldScopes +
                  ", fieldScope=" + fieldScope +
                  ", formFieldTitle=" + formFieldTitle +
                  ", formFieldHint=" + formFieldHint +
                  ", formFieldTips=" + formFieldTips +
                  ", formInputType=" + formInputType +
                  ", formInputOptions=" + formInputOptions +
                  ", formViewType=" + formViewType +
                  ", formViewOptions=" + formViewOptions +
                  ", formInputRequired=" + formInputRequired +
                  ", formFieldQuestion=" + formFieldQuestion +
                  ", pageId=" + pageId +
                  ", listImageWidth=" + listImageWidth +
                  ", listImageHeight=" + listImageHeight +
                  ", inputWidth=" + inputWidth +
              "}";
    }
}
