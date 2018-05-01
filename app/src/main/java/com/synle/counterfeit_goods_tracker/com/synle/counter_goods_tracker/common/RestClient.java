package com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common;

import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

/**
 * Created by syle on 5/1/18.
 */

// https://github.com/androidannotations/androidannotations/wiki/Rest-API
// https://github.com/androidannotations/androidannotations/wiki/Building-Project-Gradle#adding-more-aa-plugins
@Rest(rootUrl = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828", converters = { MappingJackson2HttpMessageConverter.class, StringHttpMessageConverter.class })
public interface RestClient {
    @Get("/rest/sites")
    List<Site> getSites();
}