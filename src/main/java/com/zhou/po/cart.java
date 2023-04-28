package com.zhou.po;

import java.util.Objects;

public class cart {
    /**
     * 购物车
     */
    private Integer id;
    private Integer count;
    private Integer mainuser_id;
    private Integer storing_id;
    private boolean status;

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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "cart{" +
                "id=" + id +
                ", count=" + count +
                ", mainuser_id=" + mainuser_id +
                ", storing_id=" + storing_id +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof cart)) return false;
        cart cart = (cart) o;
        return getId().equals(cart.getId()) &&
                getMainuser_id().equals(cart.getMainuser_id()) &&
                getStoring_id().equals(cart.getStoring_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMainuser_id(), getStoring_id());
    }
}
