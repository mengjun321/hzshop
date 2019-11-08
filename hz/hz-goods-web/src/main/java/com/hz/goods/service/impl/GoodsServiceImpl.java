package com.hz.goods.service.impl;

import com.hz.goods.client.BrandClient;
import com.hz.goods.client.CategoryClient;
import com.hz.goods.client.GoodsClient;
import com.hz.goods.client.SpecificationClient;
import com.hz.goods.service.IGoodsService;
import com.hz.item.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoodsServiceImpl implements IGoodsService {


    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private BrandClient brandClient;

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private SpecificationClient specificationClient;

    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    public Map<String, Object> loadModel(Long spuId){

        try {
            // 查询spu
            Spu spu = this.goodsClient.querySpuById(spuId);

            // 查询spu详情
            SpuDetail spuDetail = this.goodsClient.findSpuDetailBySpuId(spuId);

            // 查询sku
            List<Sku> skus = this.goodsClient.findSkusBySpuid(spuId);

            // 查询品牌
            List<Brand> brands = this.brandClient.queryBrandByIds(spu.getBrandId());

            // 查询分类
            List<Category> categories = getCategories(spu);

            // 查询组内参数
            List<SpecGroup> specGroups = this.specificationClient.querySpecsByCid(spu.getCid3());

            // 查询所有特有规格参数
            //false:表示特有
            List<SpecParam> specParams = specificationClient.findParmaById(null, spu.getCid3(), false);

            // 处理规格参数
            Map<Long, String> paramMap = new HashMap<>();
            for (SpecParam param : specParams) {
                paramMap.put(param.getId(), param.getName());
            }


            Map<String, Object> map = new HashMap<>();
            map.put("spu", spu);
            map.put("spuDetail", spuDetail);
            map.put("skus", skus);
            map.put("brand", brands.get(0));
            map.put("categories", categories);
            map.put("groups", specGroups);
            map.put("params", paramMap);
            return map;
        } catch (Exception e) {
            logger.error("加载商品数据出错,spuId:{}", spuId, e);
        }
        return null;
    }

    private List<Category> getCategories(Spu spu) {
        try {
            List<String> names = this.categoryClient.queryNameByIds(
                    Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            Locale.Category c1 = new Category();
            c1.setName(names.get(0));
            long cid1 = spu.getCid1();
            c1.setId((int)cid1);

            Category c2 = new Category();
            c2.setName(names.get(1));
            long cid2 = spu.getCid2();
            c2.setId((int)cid2);

            Category c3 = new Category();
            c3.setName(names.get(2));
            long cid3 = spu.getCid3();
            c3.setId((int)cid3);
            return Arrays.asList(c1, c2, c3);
        } catch (Exception e) {
            logger.error("查询商品分类出错，spuId：{}", spu.getId(), e);
        }
        return null;
    }
}
