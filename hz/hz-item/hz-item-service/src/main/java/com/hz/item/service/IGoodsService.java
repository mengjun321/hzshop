package com.hz.item.service;

import com.hz.common.entity.PageResult;
import com.hz.item.Sku;
import com.hz.item.Spu;
import com.hz.item.SpuDetail;
import com.hz.item.vo.SpuVO;

import java.util.List;

public interface IGoodsService {
    PageResult<SpuVO> querySpuByPageAndSort(Integer page, Integer rows, String key, Boolean saleable);

    int addGoods(Spu spu);

    SpuDetail findSpuDetailBySpuId(Long spuid);

    List<Sku> findSkusBySpuid(Long spuid);

    Spu querySpuById(Long id);
}
