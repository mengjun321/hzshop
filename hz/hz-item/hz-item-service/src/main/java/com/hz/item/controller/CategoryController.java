package com.hz.item.controller;

import com.hz.item.Category;
import com.hz.item.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/category")
//@CrossOrigin  //解决跨域问题
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    @RequestMapping("/list")
    public List<Category> find(@RequestParam(value = "pid",defaultValue = "0") int pid){
        List<Category> categoryList = categoryService.findCate(pid);
        return categoryList;
    }

    @GetMapping("/bid/{bid}")
    public ResponseEntity<List<Category>> get(@PathVariable(value = "bid") int bid){
        List<Category> categoryList = categoryService.findCateByBid(bid);
        if(categoryList==null || categoryList.size()<1)
        {
            return new ResponseEntity<List<Category>>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(categoryList);
    }

    /**
     * 根据商品分类id查询名称
     * @param ids 要查询的分类id集合
     * @return 多个名称的集合
     */
    @GetMapping("/names")
    public ResponseEntity<List<String>> queryNameByIds(@RequestParam("ids") List<Long> ids){
        List<String > list = this.categoryService.queryNameByIds(ids);
        if (list == null || list.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }


}
