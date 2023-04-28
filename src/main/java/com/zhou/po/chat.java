package com.zhou.po;

import java.sql.Timestamp;
import java.util.Objects;

public class chat {
    private Integer id;
    private String content;
    private Timestamp time;
    private boolean status;
    private Integer mainuser_id;
    private Integer store_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "chat{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", status=" + status +
                ", mainuser_id=" + mainuser_id +
                ", store_id=" + store_id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof chat)) return false;
        chat chat = (chat) o;
        return getId().equals(chat.getId()) &&
                getTime().equals(chat.getTime()) &&
                getMainuser_id().equals(chat.getMainuser_id()) &&
                getStore_id().equals(chat.getStore_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTime(), getMainuser_id(), getStore_id());
    }
}
