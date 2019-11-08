package com.hz.search.controller;

import com.hz.search.client.CategoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private CategoryClient categoryClient;

    @RequestMapping("/test")
    public Object testQueryCategories() {
        List<String> names = this.categoryClient.queryNameByIds(Arrays.asList(1L, 2L, 3L));
        return names;
    }

}
