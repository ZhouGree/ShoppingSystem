package com.zhou.po;

import java.util.Objects;

public class DetailUser {
    /**
     * 用户详细信息
     */
    private Integer id;
    private String autonym;
    private String mail;
    private String address;
    private String signature;
    private Integer mainUser_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutonym() {
        return autonym;
    }

    public void setAutonym(String autonym) {
        this.autonym = autonym;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getMainUser_id() {
        return mainUser_id;
    }

    public void setMainUser_id(Integer mainUser_id) {
        this.mainUser_id = mainUser_id;
    }

    @Override
    public String toString() {
        return "DetailUser{" +
                "id=" + id +
                ", autonym='" + autonym + '\'' +
                ", mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                ", signature='" + signature + '\'' +
                ", MainUserId=" + mainUser_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DetailUser)) return false;
        DetailUser user = (DetailUser) o;
        return getId().equals(user.getId()) &&
                Objects.equals(getAutonym(), user.getAutonym()) &&
                getMainUser_id().equals(user.getMainUser_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAutonym(), getMainUser_id());
    }
}
