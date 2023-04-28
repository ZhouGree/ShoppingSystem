package com.zhou.po;

import java.util.Objects;

public class comment {
    /**
     * 评论信息
     */
    private Integer id;
    private Integer mainuser_id;
    private Integer storing_id;
    private String comment;
    private boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMainuser_id() {
        return mainuser_id;
    }

    public void setMainuser_id(Integer mainuser_id) {
        this.mainuser_id = mainuser_id;
    }

    public Integer getStoring_id() {
        return storing_id;
    }

    public void setStoring_id(Integer storing_id) {
        this.storing_id = storing_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "comment{" +
                "id=" + id +
                ", mainuser_id=" + mainuser_id +
                ", storing_id=" + storing_id +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof comment)) return false;
        comment comment = (comment) o;
        return getId().equals(comment.getId()) &&
                getMainuser_id().equals(comment.getMainuser_id()) &&
                getStoring_id().equals(comment.getStoring_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMainuser_id(), getStoring_id());
    }
}
