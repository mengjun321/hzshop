package com.hz.item;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 庫存
 */

@Data
@TableName("tb_stock")
@Accessors(chain = true)
public class Stock {
    @TableId
    private Long skuId;
    private int seckillStock; // 剩余秒杀
    private int seckillTotal; // 秒杀总数
    private int stock; //总库存量
}
