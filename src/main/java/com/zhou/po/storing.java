package com.zhou.po;

import java.util.Objects;

public class storing {
    private Integer id;
    private Integer commodity_id;
    private Integer store_id;
    private Integer status;
    private long sales;
    private long repertory;
    private String picture;
    private String description;
    private int price;
    private String commodityname;
    private String storename;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(Integer commodity_id) {
        this.commodity_id = commodity_id;
    }

    public Integer getStore_id() {
        return store_id;
    }

    public void setStore_id(Integer store_id) {
        this.store_id = store_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public long getSales() {
        return sales;
    }

    public void setSales(long sales) {
        this.sales = sales;
    }

    public long getRepertory() {
        return repertory;
    }

    public void setRepertory(long repertory) {
        this.repertory = repertory;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCommodityname() {
        return commodityname;
    }

    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    @Override
    public String toString() {
        return "storing{" +
                "id=" + id +
                ", commodity_id=" + commodity_id +
                ", store_id=" + store_id +
                ", status=" + status +
                ", sales=" + sales +
                ", repertory=" + repertory +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", commodityname='" + commodityname + '\'' +
                ", storename='" + storename + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof storing)) return false;
        storing storing = (storing) o;
        return getId().equals(storing.getId()) &&
                getCommodity_id().equals(storing.getCommodity_id()) &&
                getStore_id().equals(storing.getStore_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCommodity_id(), getStore_id());
    }
}
