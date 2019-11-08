package com.hz.search.service;

import com.hz.common.entity.PageResult;
import com.hz.search.bean.Goods;
import com.hz.search.bean.SearchRequest;

public interface ISearchService {
    PageResult<Goods> search(SearchRequest request);
}
