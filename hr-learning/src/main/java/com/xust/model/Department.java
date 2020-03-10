package com.xust.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department {

    private Integer id;

    private String name;

    private Integer parentId;

    private String deptPath;

    private Boolean enabled;

    private Boolean isParent;

    private List<Department> children = new ArrayList<>();

    private Integer result;

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Department department = (Department) obj;
        return Objects.equals(name,department.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDeptPath() {
        return deptPath;
    }

    public void setDeptPath(String deptPath) {
        this.deptPath = deptPath;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getParent() {
        return isParent;
    }

    public void setParent(Boolean parent) {
        isParent = parent;
    }

    public List<Department> getChildren() {
        return children;
    }

    public void setChildren(List<Department> children) {
        this.children = children;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
