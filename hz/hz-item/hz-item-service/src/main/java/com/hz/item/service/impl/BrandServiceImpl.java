package com.hz.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.common.entity.PageResult;
import com.hz.item.Brand;
import com.hz.item.mapper.BrandMapper;
import com.hz.item.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class BrandServiceImpl implements IBrandService {


    @Autowired
    public BrandMapper brandMapper;


    @Override
    public PageResult<Brand> page(String key, int page, int rows, String sortBy, boolean desc) {


        IPage<Brand> ipage = new Page<>();
        ipage.setCurrent(page);
        ipage.setSize(rows);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.like("name",key);
        queryWrapper.or(true).likeLeft("letter",key.toUpperCase());

        //desc:true 表示降序
        //desc:fase 表示升序

        //orderBy:true 升序
        //orderBy:fasle 降序
        if(!StringUtils.isEmpty(sortBy)) {
            queryWrapper.orderBy(true, !desc, sortBy);
        }
        IPage iPage = brandMapper.selectPage(ipage, queryWrapper);

        PageResult<Brand> result = new PageResult<>();
        result.setTotal(iPage.getTotal());
        result.setItems(iPage.getRecords());

        return result;
    }

    /**
     * 添加
     * @return
     * @param brand
     * @param cids
     */
    @Override
    public int saveBrand(Brand brand, List<Integer> cids) {
        //1.先保存 brand，在保存 cids
        int insert = brandMapper.insert(brand);
        //brand添加成功之后，会自动回调数据
        //2.保存关系表
        for (int cid : cids) {
            System.out.println("--->"+cid);
            brandMapper.saveBrandCategory(cid,brand.getId());
        }
        return insert;
    }

    @Transactional
    @Override
    public int updateBrand(Brand brand, List<Integer> cids) {
        //1.修改关系表
        //1.1删除原来的关系表信息
        brandMapper.deleteBrandCategoryByBid(brand.getId());

       // int a = 10/0;
        //1.2 新增新的关系表信息
        for (int cid : cids) {
            System.out.println("--->"+cid);
            brandMapper.saveBrandCategory(cid,brand.getId());
        }

        //2.修改商品表
        int row = brandMapper.updateById(brand);
        return row;
    }

    @Transactional
    @Override
    public int deleteBrand(int bid) {

        //1.删除关系表
        int row = brandMapper.deleteBrandCategoryByBid(bid);

        //2.删除对应表
        int row2 = brandMapper.deleteById(bid);

        return row2;
    }

    @Override
    public List<Brand> findListByCid(int cid) {
        List<Brand> list = brandMapper.findListByCid(cid);
        return list;
    }

    @Override
    public List<Brand> queryBrandByIds(Long bid) {
        QueryWrapper<Brand> query = new QueryWrapper<>();
        query.eq("id",bid);
        List<Brand> brands = brandMapper.selectList(query);
        return brands;
    }
}
