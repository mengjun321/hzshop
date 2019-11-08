package com.hz.item;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("tb_brand")
public class Brand {

    @TableId(value = "id",type = IdType.AUTO) //主键自增
    private int id;
    private String name;
    private String image;
    private char letter;

}
