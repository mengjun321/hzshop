package com.hz.item.service;

import com.hz.common.entity.PageResult;
import com.hz.item.Brand;

import java.util.List;

public interface IBrandService {
    /**
     *
     * @param key 模糊查询的条件
     * @param page 页码
     * @param rows 没有大小
     * @param sortBy 排序字段
     * @param desc 是否降序
     * @return
     */
    PageResult<Brand> page(String key, int page, int rows, String sortBy, boolean desc);

    int saveBrand(Brand brand, List<Integer> cids);

    int updateBrand(Brand brand, List<Integer> cids);

    int deleteBrand(int bid);

    List<Brand> findListByCid(int cid);

    List<Brand> queryBrandByIds(Long bid);
}
