package com.zhou.po;

import javax.swing.*;
import java.util.Objects;

public class MainUser {
    /**
     * 主要信息
     */
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String picture;
    private boolean status;
    private Integer role;
    private Integer Store_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getStore_id() {
        return Store_id;
    }

    public void setStore_id(Integer store_id) {
        Store_id = store_id;
    }

    @Override
    public String toString() {
        return "MainUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", picture='" + picture + '\'' +
                ", status=" + status +
                ", role=" + role +
                ", Store_id=" + Store_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainUser)) return false;
        MainUser user = (MainUser) o;
        return getId().equals(user.getId()) &&
                getUsername().equals(user.getUsername()) &&
                getPassword().equals(user.getPassword()) &&
                getPhone().equals(user.getPhone()) &&
                getRole().equals(user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getPhone(), getRole());
    }
}
