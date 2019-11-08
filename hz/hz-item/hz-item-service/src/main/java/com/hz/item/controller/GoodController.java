package com.hz.item.controller;

import com.hz.common.entity.PageResult;
import com.hz.item.Sku;
import com.hz.item.Spu;
import com.hz.item.SpuDetail;
import com.hz.item.service.IGoodsService;
import com.hz.item.vo.SpuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodController {


    @Autowired
    private IGoodsService goodsService;


    @GetMapping("/spu/{id}")
    public ResponseEntity<Spu> querySpuById(@PathVariable("id") Long id){
        Spu spu = this.goodsService.querySpuById(id);
        if(spu == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(spu);
    }



    /**
     * 分页查询SPU
     *
     * @param page
     * @param rows
     * @param key
     * @param saleable :是否上架下架 默认全部
     * @return
     */
    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<SpuVO>> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "saleable", required = false) Boolean saleable) {
        // 分页查询spu信息
        PageResult<SpuVO> result = this.goodsService.querySpuByPageAndSort(page, rows, key, saleable);
        /*if (result == null || result.getItems().size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
        return ResponseEntity.ok(result);
    }

    //http://api.hz.com/api/item/spu/detail/2
    @GetMapping("/spu/detail/{spuid}")
    public ResponseEntity<SpuDetail> findSpuDetailBySpuId(@PathVariable("spuid") Long spuid) {
        SpuDetail spuDetail = goodsService.findSpuDetailBySpuId(spuid);
        return ResponseEntity.ok(spuDetail);
    }

    //http://api.hz.com/api/item/sku/list?id=218
    @GetMapping("/sku/list")
    public ResponseEntity<List<Sku>> findSkusBySpuid(@RequestParam("id") Long spuid) {
        List<Sku> skuList = goodsService.findSkusBySpuid(spuid);
        return ResponseEntity.ok(skuList);
    }

    @PostMapping("/goods")
    public int addGoods(@RequestBody Spu spu) {

        int row = goodsService.addGoods(spu);
        System.out.println(spu);
        return 1;
    }


}
