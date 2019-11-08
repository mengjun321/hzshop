package com.hz.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hz.item.SpecGroup;
import com.hz.item.SpecParam;
import com.hz.item.mapper.SpecGroupMapper;
import com.hz.item.mapper.SpecParamMapper;
import com.hz.item.service.ISpecGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecGroupServiceImpl implements ISpecGroupService {

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    public List<SpecGroup> findByCid(Long cid) {
        QueryWrapper<SpecGroup> query = new QueryWrapper<>();
        query.eq("cid",cid);
        List<SpecGroup> specGroups = specGroupMapper.selectList(query);
        return specGroups;
    }

    @Override
    public List<SpecGroup> querySpecsByCid(Long cid) {
        List<SpecGroup> specGroups = findByCid(cid);
        for (SpecGroup specGroup : specGroups) {
            //通过goup_id获取 param列表
            QueryWrapper<SpecParam> query = new QueryWrapper<>();
            query.eq("group_id",specGroup.getId());
            List<SpecParam> params = specParamMapper.selectList(query);
            specGroup.setParams(params);
        }


        return specGroups;
    }
}
