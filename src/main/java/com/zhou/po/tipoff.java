package com.zhou.po;

import java.util.Objects;

public class tipoff {
    private Integer id;
    private Integer storing_id;
    private Integer mainuser_id;
    private String description;
    private boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoring_id() {
        return storing_id;
    }

    public void setStoring_id(Integer storing_id) {
        this.storing_id = storing_id;
    }

    public Integer getMainuser_id() {
        return mainuser_id;
    }

    public void setMainuser_id(Integer mainuser_id) {
        this.mainuser_id = mainuser_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof tipoff)) return false;
        tipoff tipoff = (tipoff) o;
        return getId().equals(tipoff.getId()) &&
                getStoring_id().equals(tipoff.getStoring_id()) &&
                getMainuser_id().equals(tipoff.getMainuser_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStoring_id(), getMainuser_id());
    }

    @Override
    public String toString() {
        return "tipoff{" +
                "id=" + id +
                ", storing_id=" + storing_id +
                ", mainuser_id=" + mainuser_id +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
