package com.hz.item.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hz.item.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {


    @Select("SELECT * FROM tb_category WHERE id IN (SELECT category_id FROM tb_category_brand WHERE brand_id=#{bid})")
    public List<Category> findCategoryByBid(@Param("bid") int bid);

    //1.根据三级 标签cid查询 cname以及上级的cname  手机/手机通讯/对讲机
    @Select("SELECT  CONCAT(t1.name,\"/\",t2.name,\"/\",t3.name) FROM tb_category t3 INNER JOIN tb_category t2 ON t3.parent_id = t2.id  INNER JOIN tb_category t1 ON t2.parent_id = t1.id WHERE t3.id=#{cid}")
    public String findParentsCnameByCid(@Param("cid") Long cid);

}
