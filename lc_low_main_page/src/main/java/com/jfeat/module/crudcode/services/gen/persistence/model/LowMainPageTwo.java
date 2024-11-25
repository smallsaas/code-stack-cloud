package com.jfeat.module.crudcode.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

public class LowMainPageTwo extends Model<LowMainPage> {
    private static final long serialVersionUID=1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String pageTitle;

    public Long getId() {
        return id;
    }

    public LowMainPageTwo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getPageTitle() {
        return pageTitle;
    }

    public LowMainPageTwo setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
        return this;
    }
    public static final String ID = "id";

    public static final String PAGE_TITLE = "page_title";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }



    @Override
    public String toString() {
        return "LowMainPage{" +
                "id=" + id +
                ", pageTitle=" + pageTitle +
                "}";
    }

}
