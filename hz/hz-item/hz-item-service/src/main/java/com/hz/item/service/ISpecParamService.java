package com.hz.item.service;

import com.hz.item.SpecGroup;
import com.hz.item.SpecParam;

import java.util.List;

public interface ISpecParamService {
    List<SpecParam> findParmaByGid(long gid,Boolean gen);

    int save(SpecParam specParam);

    List<SpecParam> findParmaByCid(Long cid,Boolean gen);

}
