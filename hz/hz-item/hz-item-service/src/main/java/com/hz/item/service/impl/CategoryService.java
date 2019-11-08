package com.hz.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hz.item.Category;
import com.hz.item.mapper.CategoryMapper;
import com.hz.item.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findCate(int pid) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id",pid);
        List<Category> categoryList = categoryMapper.selectList(queryWrapper);

        return categoryList;
    }

    @Override
    public List<Category> findCateByBid(int bid) {
        return  categoryMapper.findCategoryByBid(bid);
    }

    @Override
    public List<String> queryNameByIds(List<Long> ids) {

        QueryWrapper<Category> query = new QueryWrapper<>();

        query.in("id",ids);
        List<Category> list = categoryMapper.selectList(query);

        List<String> names = new ArrayList();
        for (Category category : list) {
            names.add(category.getName());
        }

        return names;
    }
}
