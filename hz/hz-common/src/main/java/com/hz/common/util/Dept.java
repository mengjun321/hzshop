package com.hz.common.util;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Dept {
    private Integer deptno;
    private String dname;
    private String loc;
}
