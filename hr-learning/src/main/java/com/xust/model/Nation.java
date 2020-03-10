package com.xust.model;

import java.io.Serializable;
import java.util.Objects;

public class Nation implements Serializable {
    private Integer id;

    private String name;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Nation nation = (Nation)obj;
        return Objects.equals(name,nation.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Nation() {
    }

    public Nation(String name) {
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
