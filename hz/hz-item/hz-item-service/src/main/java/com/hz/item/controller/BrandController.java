package com.hz.item.controller;

import com.hz.common.entity.PageResult;
import com.hz.item.Brand;
import com.hz.item.service.IBrandService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {


    @Autowired
    private IBrandService brandService;

    @GetMapping("/page")
    public PageResult<Brand> page(String key, int page, int rows, String sortBy, boolean desc) {
        PageResult<Brand> result = brandService.page(key, page, rows, sortBy, desc);
        return result;
    }


    @PostMapping()
    public int brand(Brand brand, @RequestParam("cids") List<Integer> cids) {
        int row = brandService.saveBrand(brand, cids);
        return row;
    }

   @PutMapping()
    public int updatebBrandU(Brand brand, @RequestParam("cids") List<Integer> cids) {
        int row = brandService.updateBrand(brand, cids);
        return row;
    }

    @DeleteMapping("/{bid}")
    public int deleteBrandU(@PathVariable("bid") int bid) {
        int row = brandService.deleteBrand(bid);
        return row;
    }

    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> getBrandU(@PathVariable("cid") int cid) {

        List<Brand> brandList = brandService.findListByCid(cid);

        return ResponseEntity.ok(brandList);
    }

    /**
     * 根据bid获取对应的品牌
     * @param bid
     * @return
     */
    @GetMapping("/bid")
    public ResponseEntity<List<Brand>> queryBrandByIds(@RequestParam("bid")Long bid) {

        List<Brand> brandList = brandService.queryBrandByIds(bid);

        return ResponseEntity.ok(brandList);
    }


}
