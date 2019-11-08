package com.hz.item.api;

import com.hz.item.SpecGroup;
import com.hz.item.SpecParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/spec")
public interface SpecificationApi {


    @GetMapping("/groups/{cid}")
    public List<SpecGroup> findById(@PathVariable("cid") int cid);

    @GetMapping("/params")
    public List<SpecParam> findParmaById(@RequestParam(value = "gid",required = false) Long gid, @RequestParam(value = "cid",required = false) Long cid,@RequestParam(value = "gen",required = false) Boolean gen);

    @PostMapping("/param")
    public int save(@RequestBody SpecParam specParam);

    // 查询规格参数组，及组内参数
    @GetMapping("{cid}")
    List<SpecGroup> querySpecsByCid(@PathVariable("cid") Long cid);

}
