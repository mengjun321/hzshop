package com.hz.item.api;

import com.hz.common.entity.PageResult;
import com.hz.item.Sku;
import com.hz.item.Spu;
import com.hz.item.SpuDetail;
import com.hz.item.vo.SpuVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface GoodsApi {


    /**
     * 根据spu的id查询spu
     * @param id
     * @return
     */
    @GetMapping("spu/{id}")
    public Spu querySpuById(@PathVariable("id") Long id);

    /**
     * 分页查询SPU
     * @param page
     * @param rows
     * @param key
     * @param saleable :是否上架下架 默认全部
     * @return
     */


    @GetMapping("/spu/page")
    public PageResult<SpuVO> querySpuByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "saleable",required = false) Boolean saleable ) ;

    //http://api.hz.com/api/item/spu/detail/2
    @GetMapping("/spu/detail/{spuid}")
    public SpuDetail findSpuDetailBySpuId(@PathVariable("spuid") Long spuid);
    //http://api.hz.com/api/item/sku/list?id=218
    @GetMapping("/sku/list")
    public List<Sku> findSkusBySpuid(@RequestParam("id") Long spuid);

}
