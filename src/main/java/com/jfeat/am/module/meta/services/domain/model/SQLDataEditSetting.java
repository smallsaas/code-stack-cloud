package com.jfeat.am.module.meta.services.domain.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;


@Deprecated(since = "不支持配置文件配置")
@Component
@Configuration
@ConfigurationProperties(prefix = "mbcs")
public class SQLDataEditSetting {

    private List<MetaTableInfo> tableInfo;

    public List<MetaTableInfo> getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(List<MetaTableInfo> tableInfo) {
        this.tableInfo = tableInfo;
    }
}
