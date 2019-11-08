package com.hz.item;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import sun.rmi.runtime.Log;

import java.beans.Transient;
import java.util.Date;

@Data
@TableName("tb_sku")
public class Sku {

    @TableId(type = IdType.AUTO)
    private Long id;
    private long spuId;
    private String title;
    private String images;
    private double price;
    private String indexes; //记录特有属性下标
    private String ownSpec; //保存的是特有属性的键值对。
    private boolean enable;

    @TableField(exist = false) //表示该属性不为数据库表字段，但又是必须使用的。
    private int stock;//库存量

    private Date createTime;// 创建时间
    private Date lastUpdateTime;// 最后修改时间

}
