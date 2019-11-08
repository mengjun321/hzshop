package com.hz.item;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_category")
public class Category {

private int id;
private String name;
private int parentId;
private int isParent;
private int sort;
}
