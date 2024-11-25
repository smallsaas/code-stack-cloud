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
 * @since 2021-10-11
 */
@TableName("lc_low_operations")
public class LowOperations extends Model<LowOperations> {

    private static final long serialVersionUID=1L;

      /**
     * 主键id
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 标题
     */
      private String title;

      /**
     * 操作类型
     */
      private String type;

      /**
     * 操作路由路径
     */
      private String path;

      /**
     * 是否显示在列表中
     */
      private Integer outside;

      /**
     * 请求API
     */
      private String requestApi;

      /**
     * 请求结果API
     */
      private String requestRefreshApi;

      /**
     * 请求方法
     */
      private String requestMethod;

      /**
     * 请求数据
     */
      private String requestBody;

      /**
     * 请求参数
     */
      private String requestOptions;

      /**
     * 所属页面ID
     */
      private Long pageId;

      /**
     * 过滤字段
     */
      private String expectField;

      /**
     * 过滤值
     */
      private String expectValue;

      /**
     * 模态框标题
     */
      private String modalTitle;

      /**
     * 模态框宽度
     */
      private Integer modalWidth;

      /**
     * 模态框外部布局
     */
      private String modalLayout;

    
    public Long getId() {
        return id;
    }

      public LowOperations setId(Long id) {
          this.id = id;
          return this;
      }
    
    public String getTitle() {
        return title;
    }

      public LowOperations setTitle(String title) {
          this.title = title;
          return this;
      }
    
    public String getType() {
        return type;
    }

      public LowOperations setType(String type) {
          this.type = type;
          return this;
      }
    
    public String getPath() {
        return path;
    }

      public LowOperations setPath(String path) {
          this.path = path;
          return this;
      }
    
    public Integer getOutside() {
        return outside;
    }

      public LowOperations setOutside(Integer outside) {
          this.outside = outside;
          return this;
      }
    
    public String getRequestApi() {
        return requestApi;
    }

      public LowOperations setRequestApi(String requestApi) {
          this.requestApi = requestApi;
          return this;
      }
    
    public String getRequestRefreshApi() {
        return requestRefreshApi;
    }

      public LowOperations setRequestRefreshApi(String requestRefreshApi) {
          this.requestRefreshApi = requestRefreshApi;
          return this;
      }
    
    public String getRequestMethod() {
        return requestMethod;
    }

      public LowOperations setRequestMethod(String requestMethod) {
          this.requestMethod = requestMethod;
          return this;
      }
    
    public String getRequestBody() {
        return requestBody;
    }

      public LowOperations setRequestBody(String requestBody) {
          this.requestBody = requestBody;
          return this;
      }
    
    public String getRequestOptions() {
        return requestOptions;
    }

      public LowOperations setRequestOptions(String requestOptions) {
          this.requestOptions = requestOptions;
          return this;
      }
    
    public Long getPageId() {
        return pageId;
    }

      public LowOperations setPageId(Long pageId) {
          this.pageId = pageId;
          return this;
      }
    
    public String getExpectField() {
        return expectField;
    }

      public LowOperations setExpectField(String expectField) {
          this.expectField = expectField;
          return this;
      }
    
    public String getExpectValue() {
        return expectValue;
    }

      public LowOperations setExpectValue(String expectValue) {
          this.expectValue = expectValue;
          return this;
      }
    
    public String getModalTitle() {
        return modalTitle;
    }

      public LowOperations setModalTitle(String modalTitle) {
          this.modalTitle = modalTitle;
          return this;
      }
    
    public Integer getModalWidth() {
        return modalWidth;
    }

      public LowOperations setModalWidth(Integer modalWidth) {
          this.modalWidth = modalWidth;
          return this;
      }
    
    public String getModalLayout() {
        return modalLayout;
    }

      public LowOperations setModalLayout(String modalLayout) {
          this.modalLayout = modalLayout;
          return this;
      }

      public static final String ID = "id";

      public static final String TITLE = "title";

      public static final String TYPE = "type";

      public static final String PATH = "path";

      public static final String OUTSIDE = "outside";

      public static final String REQUEST_API = "request_api";

      public static final String REQUEST_REFRESH_API = "request_refresh_api";

      public static final String REQUEST_METHOD = "request_method";

      public static final String REQUEST_BODY = "request_body";

      public static final String REQUEST_OPTIONS = "request_options";

      public static final String PAGE_ID = "page_id";

      public static final String EXPECT_FIELD = "expect_field";

      public static final String EXPECT_VALUE = "expect_value";

      public static final String MODAL_TITLE = "modal_title";

      public static final String MODAL_WIDTH = "modal_width";

      public static final String MODAL_LAYOUT = "modal_layout";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "LowOperations{" +
              "id=" + id +
                  ", title=" + title +
                  ", type=" + type +
                  ", path=" + path +
                  ", outside=" + outside +
                  ", requestApi=" + requestApi +
                  ", requestRefreshApi=" + requestRefreshApi +
                  ", requestMethod=" + requestMethod +
                  ", requestBody=" + requestBody +
                  ", requestOptions=" + requestOptions +
                  ", pageId=" + pageId +
                  ", expectField=" + expectField +
                  ", expectValue=" + expectValue +
                  ", modalTitle=" + modalTitle +
                  ", modalWidth=" + modalWidth +
                  ", modalLayout=" + modalLayout +
              "}";
    }
}
