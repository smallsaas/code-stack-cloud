package com.jfeat.am.module.meta.services.domain.model;

import java.io.Serializable;
import java.util.List;

public class BulkChangStatusModel implements Serializable {

    private List<Long> ids;

    private String status;

    /**
     * 意见、备注
     */
    private String note;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "BulkChangStatusModel{" +
                "ids=" + ids +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
