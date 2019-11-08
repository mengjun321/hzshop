package com.hz.item;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.beans.Transient;
import java.util.List;

@TableName("tb_spec_group")
@Data
public class SpecGroup {

    @TableId(type = IdType.AUTO)
    private int id;

    private int cid;
    private String name;

    @TableField(exist = false) //不参与数据库操作
    private List<SpecParam> params; // 该组下的所有规格参数集合
}
