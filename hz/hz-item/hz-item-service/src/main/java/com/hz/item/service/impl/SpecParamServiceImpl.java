package com.hz.item.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hz.item.SpecParam;
import com.hz.item.mapper.SpecParamMapper;
import com.hz.item.service.ISpecGroupService;
import com.hz.item.service.ISpecParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SpecParamServiceImpl implements ISpecParamService {

    @Autowired
    private SpecParamMapper specParamMapper;
    @Override
    public List<SpecParam> findParmaByGid(long gid,Boolean gen) {
        QueryWrapper<SpecParam> query = new QueryWrapper<>();
        if(!StringUtils.isEmpty(gen)){
            query.eq("generic",gen);
        }
        query.eq("group_id",gid);
        List<SpecParam> specParamList = specParamMapper.selectList(query);
        return specParamList;
    }

    @Override
    public int save(SpecParam specParam) {



        return  specParamMapper.insert(specParam);
    }

    @Override
    public List<SpecParam> findParmaByCid(Long cid,Boolean gen) {
        QueryWrapper<SpecParam> query = new QueryWrapper<>();
        if(!StringUtils.isEmpty(gen)){
            query.eq("generic",gen);
        }
        query.eq("cid",cid);
        List<SpecParam> specParamList = specParamMapper.selectList(query);
        return specParamList;
    }
}
