package com.xust.cache.bean;

public class Employee {
    private Integer id;
    private String lastName;
    private String email;
    private String gender;
    private Integer dId;

    public Employee() {
    }

    public Employee(Integer id, String lastName, String email, String gender, Integer dId) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.dId = dId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", dId=" + dId +
                '}';
    }
}
