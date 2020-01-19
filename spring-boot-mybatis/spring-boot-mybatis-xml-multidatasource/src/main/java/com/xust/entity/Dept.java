package com.xust.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
@NoArgsConstructor
public class Dept implements Serializable{

    private static final Long serialVersionUID=1L;

    public Dept(String deptName, String deptLoca) {
        this.deptName = deptName;
        this.deptLoca = deptLoca;
    }

    private Long id;



    private String deptName;

    private String deptLoca;

}
