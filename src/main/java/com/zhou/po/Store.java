package com.zhou.po;

import java.util.Objects;

public class Store {
    /**
     * 店铺信息
     */
    private Integer id;
    private String description;
    private String storename;
    private String picture;
    private float score;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", storename='" + storename + '\'' +
                ", picture='" + picture + '\'' +
                ", score=" + score +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store)) return false;
        Store store = (Store) o;
        return getId().equals(store.getId()) &&
                getStorename().equals(store.getStorename());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStorename());
    }
}
