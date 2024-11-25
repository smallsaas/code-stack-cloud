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
 * @since 2021-09-07
 */
@TableName("lc_low_main_page")
public class LowMainPage extends Model<LowMainPage> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    /**
     * pageId
     */
    private Long pageId;

    /**
     * 页面标题
     */
    private String pageTitle;

    /**
     * 页面名称
     */
    private String pageName;


    /**
     * 行对齐
     */
    private String columnAlign;

    /**
     * 数据接口
     */
    private String apiEndpoint;

    /**
     * 新建页面标题
     */
    private String formAddTitle;

    /**
     * 页面视图标题
     */
    private String formViewTitle;

    /**
     * 页面编辑标题
     */
    private String formEditTitle;

    /**
     * 页面内容布局
     */
    private String contentLayout;

    /**
     * 组件列表排序
     */
    private String contentItems;

    /**
     * 组件容器属性
     */
    private String contentItemContainerStyle;

    /**
     * 列表字段
     */
    private String listFields;

    /**
     * 列表操作排列
     */
    private String listOperationFields;

    /**
     * 表单新建字段排列
     */
    private String formAddFields;

    /**
     * 列表视图字段排列
     */
    private String formViewFields;

    /**
     * 列表编辑字段排列
     */
    private String formEditFields;

    /**
     * 表单默认内容布局
     */
    private String formDefaultContentLayout;

    /**
     * 表单模态框默认宽度
     */
    private Integer formDefaultWidth;

    /**
     * 页面最小宽度
     */
    private Integer pageMinWidth;

    /**
     * 搜索类型
     */
    private String searchType;

    /**
     * 搜索按钮类型
     */
    private String searchButtonType;

    /*
     * 前端物理路由
     * */
    private String frontUrl;

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }

    public String getSearchType() {
        return searchType;
    }

    public LowMainPage setSearchType(String searchType) {
        this.searchType = searchType;
        return this;
    }

    public String getSearchButtonType() {
        return searchButtonType;
    }

    public LowMainPage setSearchButtonType(String searchButtonType) {
        this.searchButtonType = searchButtonType;
        return this;
    }

    public Integer getPageMinWidth() {
        return pageMinWidth;
    }

    public LowMainPage setPageMinWidth(Integer pageMinWidth) {
        this.pageMinWidth = pageMinWidth;
        return this;
    }

    public Long getId() {
        return id;
    }

    public LowMainPage setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public LowMainPage setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
        return this;
    }

    public String getPageName() {
        return pageName;
    }

    public LowMainPage setPageName(String pageName) {
        this.pageName = pageName;
        return this;
    }

    public String getColumnAlign() {
        return columnAlign;
    }

    public LowMainPage setColumnAlign(String columnAlign) {
        this.columnAlign = columnAlign;
        return this;
    }

    public String getApiEndpoint() {
        return apiEndpoint;
    }

    public LowMainPage setApiEndpoint(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
        return this;
    }

    public String getFormAddTitle() {
        return formAddTitle;
    }

    public LowMainPage setFormAddTitle(String formAddTitle) {
        this.formAddTitle = formAddTitle;
        return this;
    }

    public String getFormViewTitle() {
        return formViewTitle;
    }

    public LowMainPage setFormViewTitle(String formViewTitle) {
        this.formViewTitle = formViewTitle;
        return this;
    }

    public String getFormEditTitle() {
        return formEditTitle;
    }

    public LowMainPage setFormEditTitle(String formEditTitle) {
        this.formEditTitle = formEditTitle;
        return this;
    }

    public String getContentLayout() {
        return contentLayout;
    }

    public LowMainPage setContentLayout(String contentLayout) {
        this.contentLayout = contentLayout;
        return this;
    }

    public String getContentItems() {
        return contentItems;
    }

    public LowMainPage setContentItems(String contentItems) {
        this.contentItems = contentItems;
        return this;
    }

    public String getContentItemContainerStyle() {
        return contentItemContainerStyle;
    }

    public LowMainPage setContentItemContainerStyle(String contentItemContainerStyle) {
        this.contentItemContainerStyle = contentItemContainerStyle;
        return this;
    }

    public String getListFields() {
        return listFields;
    }

    public LowMainPage setListFields(String listFields) {
        this.listFields = listFields;
        return this;
    }

    public String getListOperationFields() {
        return listOperationFields;
    }

    public LowMainPage setListOperationFields(String listOperationFields) {
        this.listOperationFields = listOperationFields;
        return this;
    }

    public String getFormAddFields() {
        return formAddFields;
    }

    public LowMainPage setFormAddFields(String formAddFields) {
        this.formAddFields = formAddFields;
        return this;
    }

    public String getFormViewFields() {
        return formViewFields;
    }

    public LowMainPage setFormViewFields(String formViewFields) {
        this.formViewFields = formViewFields;
        return this;
    }

    public String getFormEditFields() {
        return formEditFields;
    }

    public LowMainPage setFormEditFields(String formEditFields) {
        this.formEditFields = formEditFields;
        return this;
    }

    public String getFormDefaultContentLayout() {
        return formDefaultContentLayout;
    }

    public LowMainPage setFormDefaultContentLayout(String formDefaultContentLayout) {
        this.formDefaultContentLayout = formDefaultContentLayout;
        return this;
    }

    public Integer getFormDefaultWidth() {
        return formDefaultWidth;
    }

    public LowMainPage setFormDefaultWidth(Integer formDefaultWidth) {
        this.formDefaultWidth = formDefaultWidth;
        return this;
    }

    public static final String ID = "id";

    public static final String PAGE_TITLE = "page_title";

    public static final String PAGE_NAME = "page_name";

    public static final String COLUMN_ALIGN = "column_align";

    public static final String API_ENDPOINT = "api_endpoint";

    public static final String FORM_ADD_TITLE = "form_add_title";

    public static final String FORM_VIEW_TITLE = "form_view_title";

    public static final String FORM_EDIT_TITLE = "form_edit_title";

    public static final String CONTENT_LAYOUT = "content_layout";

    public static final String CONTENT_ITEMS = "content_items";

    public static final String CONTENT_ITEM_CONTAINER_STYLE = "content_item_container_style";

    public static final String LIST_FIELDS = "list_fields";

    public static final String LIST_OPERATION_FIELDS = "list_operation_fields";

    public static final String FORM_ADD_FIELDS = "form_add_fields";

    public static final String FORM_VIEW_FIELDS = "form_view_fields";

    public static final String FORM_EDIT_FIELDS = "form_edit_fields";

    public static final String FORM_DEFAULT_CONTENT_LAYOUT = "form_default_content_layout";

    public static final String FORM_DEFAULT_WIDTH = "form_default_width";

    public static final String PAGE_MIN_WIDTH = "page_min_width";

    public static final String SEARCH_TYPE = "search_type";

    public static final String SEARCH_BUTTON_TYPE = "search_button_type";

    public static final String PAGE_ID = "page_id";


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "LowMainPage{" +
                "id=" + id +
                ", pageTitle=" + pageTitle +
                ", pageName=" + pageName +
                ", columnAlign=" + columnAlign +
                ", apiEndpoint=" + apiEndpoint +
                ", formAddTitle=" + formAddTitle +
                ", formViewTitle=" + formViewTitle +
                ", formEditTitle=" + formEditTitle +
                ", contentLayout=" + contentLayout +
                ", contentItems=" + contentItems +
                ", contentItemContainerStyle=" + contentItemContainerStyle +
                ", listFields=" + listFields +
                ", listOperationFields=" + listOperationFields +
                ", formAddFields=" + formAddFields +
                ", formViewFields=" + formViewFields +
                ", formEditFields=" + formEditFields +
                ", formDefaultContentLayout=" + formDefaultContentLayout +
                ", formDefaultWidth=" + formDefaultWidth +
                ", pageMinWidth=" + pageMinWidth +
                ", searchType=" + searchType +
                ", searchButtonType=" + searchButtonType +
                "}";
    }
}
