package com.jfeat.am.module.meta.services.domain.model;

import java.util.List;

public class MetaTableInfo {

    /*数据表信息命名 */
    private String name;
    private String table;
    private List<MetaField> field;
    private List<MetaWhereColumn> where;

    public MetaField getFieldByName(String name){
        for(MetaField f : field){
            if(f!=null && f.getName()!=null && f.getName().equals(name)){
                return f;
            }
        }
        return null;
    }

    public List<MetaWhereColumn> getWhere() {
        return where;
    }

    public void setWhere(List<MetaWhereColumn> where) {
        this.where = where;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<MetaField> getField() {
        return field;
    }

    public void setField(List<MetaField> field) {
        this.field = field;
    }
}
