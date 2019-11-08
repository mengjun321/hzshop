package com.hz.rabbit.bean;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class Dept{
    private int deptno;
    private String dname;
    private String loc;
}
