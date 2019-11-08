package com.hz.item;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_spu_detail")
@Data
public class SpuDetail {


    @TableId
    private long spuId; //spuid没有自己的id
    private String description; //描述
    private String genericSpec; //公共属性
    private String specialSpec; //特有属性
    private String packingList; //包装清单
    private String afterService; //售后服务

}
