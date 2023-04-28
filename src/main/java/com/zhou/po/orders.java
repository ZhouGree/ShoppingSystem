package com.zhou.po;

import java.util.Objects;

public class orders {
    private Integer id;
    private Integer count;
    private Integer status;
    private Integer storing_id;
    private Integer mainuser_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof orders)) return false;
        orders orders = (orders) o;
        return getId().equals(orders.getId()) &&
                getStoring_id().equals(orders.getStoring_id()) &&
                getMainuser_id().equals(orders.getMainuser_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStoring_id(), getMainuser_id());
    }

    @Override
    public String toString() {
        return "orders{" +
                "id=" + id +
                ", count=" + count +
                ", status=" + status +
                ", storing_id=" + storing_id +
                ", mainuser_id=" + mainuser_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
