package com.hz.item;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

@TableName("tb_spu")
@Data
public class Spu {

    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("brand_id")
    private Long brandId;
    private Long cid1;// 1级类目
    private Long cid2;// 2级类目
    private Long cid3;// 3级类目
    private String title;// 标题
    private String subTitle;// 子标题
    private Boolean saleable;// 是否上架
    private Boolean valid;// 是否有效，逻辑删除用
    @TableField(exist = false)
    private Date createTime;// 创建时间
    @TableField(exist = false)
    private Date lastUpdateTime;// 最后修改时间
    // 省略getter和setter
    @TableField(exist = false)
    private SpuDetail spuDetail;

    @TableField(exist = false)
    private List<Sku> skus;
}