package com.jfeat.meta.dosql.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author Code generator
 * @since 2022-09-16
 */
@Deprecated
@TableName("t_dev_version")
@ApiModel(value = "DoSqlVersion", description = "")
public class DoSqlVersion extends Model<DoSqlVersion> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "数据库版本")
    private String sqlVersion;

    @ApiModelProperty(value = "appid")
    private String appid;

    @ApiModelProperty(value = "备用")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "当前app版本的操作列表")
    @TableField(exist = false)
    private List<DoSqlField> devDevelopList;


    public List<DoSqlField> getDoSqlFieldList() {
        return devDevelopList;
    }

    public void setDoSqlFieldList(List<DoSqlField> devDevelopList) {
        this.devDevelopList = devDevelopList;
    }

    public Long getId() {
        return id;
    }

    public DoSqlVersion setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSqlVersion() {
        return sqlVersion;
    }

    public DoSqlVersion setSqlVersion(String sqlVersion) {
        this.sqlVersion = sqlVersion;
        return this;
    }

    public String getAppid() {
        return appid;
    }

    public DoSqlVersion setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getNote() {
        return note;
    }

    public DoSqlVersion setNote(String note) {
        this.note = note;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public DoSqlVersion setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public DoSqlVersion setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static final String ID = "id";

    public static final String SQL_VERSION = "sql_version";

    public static final String APPID = "appid";

    public static final String NOTE = "note";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "DevVersion{" +
                "id=" + id +
                ", sqlVersion=" + sqlVersion +
                ", appid=" + appid +
                ", note=" + note +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
