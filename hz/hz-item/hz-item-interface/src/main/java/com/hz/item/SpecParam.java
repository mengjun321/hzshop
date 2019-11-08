package com.hz.item;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("tb_spec_param")
@Data
public class SpecParam {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long cid;
   // @TableField("group_id")
    private Long groupId;
    private String name;
    @TableField("`numeric`") //数据库关键字
    private Boolean numeric;
    private String unit;
    private Boolean generic;
    private Boolean searching;
    private String segments;

}
