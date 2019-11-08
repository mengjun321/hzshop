package com.hz.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageResult<T> {

    private int id;
    private long total; //总大小
    private List<T> items;

}
