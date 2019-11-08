package com.hz.item.controller;

import com.hz.item.SpecGroup;
import com.hz.item.SpecParam;
import com.hz.item.service.ISpecGroupService;
import com.hz.item.service.ISpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/spec")
public class SpecificationController {


    @Autowired
    private ISpecGroupService specGroupService;

    @Autowired
    private ISpecParamService specParamService;

    @GetMapping("/groups/{cid}")
    public ResponseEntity<List<SpecGroup>> findById(@PathVariable("cid") Long cid) {
        List<SpecGroup> specGroupList = specGroupService.findByCid(cid);

        if (specGroupList == null || specGroupList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(specGroupList);
    }

    /**
     * 查询参数, 都是可选参数
     * @param gid
     * @param cid
     * @param gen  表示是否是公共参数
     * @return
     */
    @GetMapping("/params")
    public ResponseEntity<List<SpecParam>> findParmaById(@RequestParam(value = "gid",required = false) Long gid, @RequestParam(value = "cid",required = false) Long cid,@RequestParam(value = "gen",required = false) Boolean gen){

        List<SpecParam> specParamList = null;
        if(!StringUtils.isEmpty(gid)) {
            System.out.println("spec---->gid:"+gid);
            specParamList = specParamService.findParmaByGid(gid,gen);
        }
        if(!StringUtils.isEmpty(cid)) {
            System.out.println("spec---->cid:"+cid);
            specParamList =  specParamService.findParmaByCid(cid,gen);
        }

        if (specParamList == null || specParamList.size() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(specParamList);
    }

    @PostMapping("/param")
    public int save(@RequestBody  SpecParam specParam) {
       int row =  specParamService.save(specParam);
        return row;

    }

    /**
     *  查询规格参数组，及组内参数
     */
    @GetMapping("{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecsByCid(@PathVariable("cid") Long cid){
        List<SpecGroup> list = this.specGroupService.querySpecsByCid(cid);
        if(list == null || list.size() == 0){
            System.out.println("spec下没有对应参数....");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }


}

