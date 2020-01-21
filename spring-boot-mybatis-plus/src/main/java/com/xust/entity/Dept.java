package com.xust.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.Generated;
import java.io.Serializable;

@Data
@TableName("t_dept")
@AllArgsConstructor
public class Dept implements Serializable {

    private static final Long UUID=1L;

    @TableId
    private Long id;

    private String dept_name;

    private String dept_local;

}
