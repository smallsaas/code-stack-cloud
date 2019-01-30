package com.jfeat.am.module.meta.services.domain.model;

import java.io.Serializable;

public class ChangeStatusModel implements Serializable{

    private String status;

    /**
     * 意见、备注
     */
    private String note;

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
        return "ChangeStatusModel{" +
                "status='" + status + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
