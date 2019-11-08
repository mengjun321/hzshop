package com.hz.search.service.impl;

import com.hz.common.entity.PageResult;
import com.hz.common.util.JsonUtils;
import com.hz.item.Sku;
import com.hz.item.vo.SpuVO;
import com.hz.search.bean.Goods;
import com.hz.search.bean.SearchRequest;
import com.hz.search.client.CategoryClient;
import com.hz.search.client.GoodsClient;
import com.hz.search.service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private GoodsClient goodsClient;

    @Override
    public PageResult<Goods> search(SearchRequest request) {

        //获取所有商品信息
        PageResult<SpuVO> result = goodsClient.querySpuByPage(request.getPage(), request.getSize(), request.getKey(), true);

        //获取所有数据
        List<SpuVO> items = result.getItems();

        //创建临时集合 存储数据
        List<Goods> goodsList = new ArrayList<>();

        for (SpuVO spu : items) {
            Goods goods = new Goods();
            goods.setId(spu.getId());
            goods.setSubTitle(spu.getSubTitle());
            //获取所有的sku信息
            List<Sku> skuList = goodsClient.findSkusBySpuid(spu.getId());
            String serialize = JsonUtils.serialize(skuList); //转换成json
            goods.setSkus(serialize);
            goodsList.add(goods);
        }
        PageResult<Goods> pageResult = new PageResult<>();
        pageResult.setItems(goodsList);
        pageResult.setTotal(items.size());
        return pageResult;
    }
}
