package com.zhou.po;

import java.util.Objects;

public class follow {
    private Integer id;
    private Integer mainuser_id;
    private Integer store_id;
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

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "follow{" +
                "id=" + id +
                ", mainuser_id=" + mainuser_id +
                ", store_id=" + store_id +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof follow)) return false;
        follow follow = (follow) o;
        return getId().equals(follow.getId()) &&
                getMainuser_id().equals(follow.getMainuser_id()) &&
                getStore_id().equals(follow.getStore_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMainuser_id(), getStore_id());
    }
}
