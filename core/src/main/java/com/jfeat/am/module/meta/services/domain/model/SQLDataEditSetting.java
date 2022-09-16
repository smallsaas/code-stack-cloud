package com.jfeat.am.module.meta.services.domain.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Configuration
@ConfigurationProperties(prefix = "sqlDataEdit")
public class SQLDataEditSetting {

    private List<MetaTableInfo> tableInfo;

    public List<MetaTableInfo> getTableInfo() {
        return tableInfo;
    }

    public void setTableInfo(List<MetaTableInfo> tableInfo) {
        this.tableInfo = tableInfo;
    }
}
