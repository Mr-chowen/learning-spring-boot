package com.xust.model;

import java.io.Serializable;
import java.util.Objects;

public class Politicsstatus implements Serializable {

    private Integer id;

    private String name;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return  true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Politicsstatus that = (Politicsstatus)obj;
        return Objects.equals(name,that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public Politicsstatus() {
    }

    public Politicsstatus(String name) {
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
}
