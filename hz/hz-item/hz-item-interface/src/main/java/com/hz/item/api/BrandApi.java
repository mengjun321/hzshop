package com.hz.item.api;

import com.hz.common.entity.PageResult;
import com.hz.item.Brand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/brand")
public interface BrandApi {
    //@GetMapping("/page")
    //public PageResult<Brand> page(String key, int page, int rows, String sortBy, boolean desc);


    @PostMapping()
    public int brand(Brand brand, @RequestParam("cids") List<Integer> cids);

    @PutMapping()
    public int updatebBrandU(Brand brand, @RequestParam("cids") List<Integer> cids);

    @DeleteMapping("/{bid}")
    public int deleteBrandU(@PathVariable("bid") int bid);

    @GetMapping("cid/{cid}")
    public List<Brand> getBrandU(@PathVariable("cid") int cid);

    /**
     * 根据bid获取对应的品牌
     * @param bid
     * @return
     */
    @GetMapping("/bid")
    public List<Brand> queryBrandByIds(@RequestParam("bid") Long bid);


}
