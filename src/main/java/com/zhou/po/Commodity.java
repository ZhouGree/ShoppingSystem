package com.zhou.po;

import java.util.Objects;

public class Commodity {
    /**
     * 商品
     */
    private Integer id;
    private String commodityname;
    private String description;
    private boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommodityname() {
        return commodityname;
    }

    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
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
    public String toString() {
        return "Commodity{" +
                "id=" + id +
                ", commodityname='" + commodityname + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Commodity)) return false;
        Commodity commodity = (Commodity) o;
        return getId().equals(commodity.getId()) &&
                getCommodityname().equals(commodity.getCommodityname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCommodityname());
    }
}
