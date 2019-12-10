package com.xust.springcloud.entities;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public @Data class Dept implements Serializable {

    private  Long id;

    private  String dname;

    private  String dsource;


}
