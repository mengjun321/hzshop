package com.hz.item.api;

import com.hz.item.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/category")
public interface CategoryApi {


    @GetMapping
    @RequestMapping("/list")
    public List<Category> find(@RequestParam(value = "pid",defaultValue = "0") int pid);

    @GetMapping("/bid/{bid}")
    public List<Category> get(@PathVariable(value = "bid") int bid);

    /**
     * 根据商品分类id查询名称
     * @param ids 要查询的分类id集合
     * @return 多个名称的集合
     */
    @GetMapping("/names")
    public List<String> queryNameByIds(@RequestParam("ids") List<Long> ids);
}
