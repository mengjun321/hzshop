package com.hz.item.service;

import com.hz.item.SpecGroup;
import com.hz.item.SpecParam;

import java.util.List;

public interface ISpecGroupService {
    List<SpecGroup> findByCid(Long cid);


    List<SpecGroup> querySpecsByCid(Long cid);
}
