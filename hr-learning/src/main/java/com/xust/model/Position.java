package com.xust.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Position implements Serializable {
    private Integer id;

    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/beijing")
    private Date createDate;

    private boolean enabled;

    @Override
    public boolean equals(Object obj) {
        if(this == obj)return  true;
        if(obj == null || getClass() != obj.getClass())return false;
        Position position = (Position)obj;
        return Objects.equals(name,position.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Position() {
    }

    public Position(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
