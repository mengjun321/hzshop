package com.hz.item.service;


import com.hz.item.Category;

import java.util.List;

public interface ICategoryService {

    public List<Category> findCate(int pid);

    List<Category> findCateByBid(int bid);

    List<String> queryNameByIds(List<Long> ids);
}


