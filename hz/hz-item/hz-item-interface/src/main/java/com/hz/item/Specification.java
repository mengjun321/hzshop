package com.hz.item;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_specification")
public class Specification {
    @TableId(value = "category_id",type = IdType.AUTO)
    private Long categoryId;
    private String specifications;
}
