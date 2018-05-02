package com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by syle on 4/26/18.
 */

public class DataUtil {
//    private static final String URL_SITE_LIST = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/sites";
//    private static final String URL_SITE_DOWNLOAD = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/download?name=site5";
    private static final String URL_SITE_REG = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/regsite";
    private static final String URL_ITEM_REG = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/regitem";
    private static final String URL_ITEM_SEND = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/fetch?pk=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAskifo4Vr0+dILiXnoOTEkOMKEBbnJyXigZemO5hSOWQaI9jftH3+sFtn5Z1dzeUDtJlUGUlUqRxssdH0OOw8tPIYy2ao5wQuRymBQ9r14/PIxl0JEomnw1hz7N22QGZDjPwy7tuxm7J0zfX2oOe902bdW18lvTNi/tCm+P7lC3PpfofsuWVvij0fFQwuYxyxZZIM1yQqfVZ3+y2yFY5KRXBMON9vBmQzCa4qMNZOLnQjgwL2TaapAlMts/ZBuGMD8jHcXGZmC0X6vOBPv4nax8Gll/W85G6vFq4H5/+aq7RObcFvMIhN+gdIMtlvgZyCOnVxBBYgWIcNw/63FxUHJwIDAQAB";
    private static final String URL_ITEM_BLOCK_APPEND = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/append";
    private static final String URL_ITEM_HISTORY = " http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/history?id=ABC123";


    public static String getURL(String url){
        return "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828" + url;
    }

    public static Site[] getAllSites(){
        // The connection URL
        String url = getURL("/rest/sites");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String result = restTemplate.getForObject(url, String.class);


//        due to back end return text/plain
//        hacky way to parse json...
//        https://springframework.guru/processing-json-jackson/
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Site[] sites = objectMapper.readValue(result, Site[].class);
            return sites;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getPrivateKey(String siteId){
        // The connection URL
        String url = getURL("/rest/download?name={siteId}");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String result = restTemplate.getForObject(url, String.class, siteId);

        return result;
    }
}
