package com.hz.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.common.entity.PageResult;
import com.hz.item.*;
import com.hz.item.mapper.*;
import com.hz.item.service.IGoodsService;
import com.hz.item.vo.SpuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GoodsServiceImpl implements IGoodsService {

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private StockMapper stockMapper;

    @Override
    public PageResult<SpuVO> querySpuByPageAndSort(Integer page, Integer rows, String key, Boolean saleable) {

        //1.分页查询
        IPage<Spu> ipage = new Page<>();
        ipage.setCurrent(page);
        ipage.setPages(page);
        ipage.setSize(rows);

        //2.根据关键字查询
        QueryWrapper<Spu> query = new QueryWrapper<>();

        if (!StringUtils.isEmpty(key)) {
            query.like("title", key);
        }
        //2.1 saleable:如果没有true或者false表示查询所有商品
        if (!StringUtils.isEmpty(saleable)) {
            int falg = 1;//上架
            if (!saleable) {
                falg = 0;
            }
            query.eq("saleable", falg);
        }

        //3.将数据库中的spu，转换为SpuVO对象
        IPage<Spu> spuIPage = spuMapper.selectPage(ipage, query);
        long total = spuIPage.getTotal();
        List<Spu> spuList = spuIPage.getRecords(); //所有的spu  转换成spuVo
        List<SpuVO> items = new ArrayList<>(); //spuVo集合

        SpuVO spuVO = null; //新对象
        for (Spu spu : spuList) {
            spuVO = new SpuVO();
            //根据cid获取 分类信息
            String cname = categoryMapper.findParentsCnameByCid(spu.getCid3());
            spuVO.setCname(cname);

            //根据bid获取品牌信息
            Brand brand = brandMapper.selectById(spu.getBrandId());
            if(brand!=null) {
                spuVO.setBname(brand.getName());
            }


            spuVO.setId(spu.getId());
            spuVO.setTitle(spu.getTitle());
            spuVO.setSubTitle(spu.getSubTitle());
            spuVO.setBrandId(spu.getBrandId());
            spuVO.setValid(spu.getValid());
            spuVO.setSaleable(spu.getSaleable());
            spuVO.setCid1(spu.getCid1());
            spuVO.setCid2(spu.getCid2());
            spuVO.setCid3(spu.getCid3());
            spuVO.setCreateTime(spu.getCreateTime());
            spuVO.setLastUpdateTime(spu.getLastUpdateTime());

            items.add(spuVO);
        }

        //4.查询品牌 和 分类名称


        //5.返回结果
        PageResult pageResult = new PageResult();
        pageResult.setTotal(total);
        pageResult.setItems(items);


        return pageResult;
    }

    @Override
    @Transactional //事务
    public int addGoods(Spu spu) {

        //1.添加spu
        //1.1  补全spu的信息
        spu.setId(null); //自增
        spu.setSaleable(true); //是否上架 默认上架
        spu.setValid(true); //默认有效
        spu.setCreateTime(new Date());
        spu.setLastUpdateTime(new Date());

        int insert = spuMapper.insert(spu);
        if(insert==0)
        {
            throw  new RuntimeException("添加spu商品失败");
        }

        //2.添加spu_detail
        SpuDetail spuDetail = spu.getSpuDetail();
        //2.1补全 detail信息  尤其sup_id
        spuDetail.setSpuId(spu.getId()); //自动回显
        insert = spuDetailMapper.insert(spuDetail);
        if(insert==0)
        {
            throw  new RuntimeException("添加商品spuDetail详情失败");
        }


        //3.添加sku :一个商品 对应多个sku信息
        List<Sku> skus = spu.getSkus();
        for (Sku sku : skus) {
            //3.1 补全sku信息  包括属于哪个spu
            sku.setId(null);
            sku.setSpuId(spu.getId());
            sku.setCreateTime(new Date());
            sku.setLastUpdateTime(new Date());
            skuMapper.insert(sku);



            //4.添加stock 库存表  和  sku关系: 一个sku对应一个库存  一对一
            int num = sku.getStock(); //获取填写的库存
            Stock stock1 = new Stock().setSkuId(sku.getId()).setStock(num);
            stockMapper.insert(stock1);
        }
        return insert;
    }

    @Override
    public SpuDetail findSpuDetailBySpuId(Long spuid) {
        SpuDetail spuDetail = spuDetailMapper.selectById(spuid);
        return spuDetail;
    }

    @Override
    public List<Sku> findSkusBySpuid(Long spuid) {
        QueryWrapper<Sku> query = new QueryWrapper<>();
        query.eq("spu_id",spuid);

        List<Sku> skuList = skuMapper.selectList(query);
        return skuList;
    }

    @Override
    public Spu querySpuById(Long id) {
        Spu spu = spuMapper.selectById(id);
        return spu;
    }


}
