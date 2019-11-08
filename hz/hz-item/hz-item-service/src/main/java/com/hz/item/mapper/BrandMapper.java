package com.hz.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hz.item.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper extends BaseMapper<Brand> {
    /**
     * 自定义添加维护关系表
     * @param cid
     * @param bid
     * @return
     */
    @Insert("INSERT INTO tb_category_brand(category_id,brand_id) VALUES(#{cid},#{bid})")
    public int saveBrandCategory(@Param("cid") int cid,@Param("bid") int bid );


    @Delete("DELETE FROM tb_category_brand where brand_id=#{bid} ")
    public int deleteBrandCategoryByBid(@Param("bid") int bid);

    @Select("SELECT * FROM tb_brand WHERE id IN(SELECT brand_id FROM tb_category_brand WHERE category_id=#{cid})")
    public List<Brand> findListByCid(@Param("cid") int cid);

}
