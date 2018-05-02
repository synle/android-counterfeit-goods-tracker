package com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by syle on 4/26/18.
 */

public class DataUtil {
//    private static final String URL_SITE_LIST = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/sites";
//    private static final String URL_SITE_DOWNLOAD = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/download?name=site5";
//    private static final String URL_SITE_REG = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/regsite";
    private static final String URL_ITEM_REG = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/regitem";
    private static final String URL_ITEM_SEND = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/fetch?pk=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAskifo4Vr0+dILiXnoOTEkOMKEBbnJyXigZemO5hSOWQaI9jftH3+sFtn5Z1dzeUDtJlUGUlUqRxssdH0OOw8tPIYy2ao5wQuRymBQ9r14/PIxl0JEomnw1hz7N22QGZDjPwy7tuxm7J0zfX2oOe902bdW18lvTNi/tCm+P7lC3PpfofsuWVvij0fFQwuYxyxZZIM1yQqfVZ3+y2yFY5KRXBMON9vBmQzCa4qMNZOLnQjgwL2TaapAlMts/ZBuGMD8jHcXGZmC0X6vOBPv4nax8Gll/W85G6vFq4H5/+aq7RObcFvMIhN+gdIMtlvgZyCOnVxBBYgWIcNw/63FxUHJwIDAQAB";
    private static final String URL_ITEM_BLOCK_APPEND = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/append";
    private static final String URL_ITEM_HISTORY = " http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/history?id=ABC123";


    public static String getURL(String url){
        return "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828" + url;
    }

    public static Site[] getAllSites(){
        // The connection URL
        final String url = getURL("/rest/sites");

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        final String result = restTemplate.getForObject(url, String.class);


//        due to back end return text/plain
//        hacky way to parse json...
//        https://springframework.guru/processing-json-jackson/
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            final Site[] sites = objectMapper.readValue(result, Site[].class);
            return sites;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Site createSite(Site s) {
        final String url = getURL("/rest/regsite");
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());


//            https://www.google.com/search?q=jackson+to+json&oq=jackson+to+json&aqs=chrome..69i57.6205j0j7&sourceid=chrome&ie=UTF-8
//            Object result = restTemplate.postForEntity(url, s, String.class);

        final ObjectMapper objectMapper = new ObjectMapper();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            final HttpEntity<String> entity = new HttpEntity<String>(objectMapper.writeValueAsString(s), headers);
            final String result = restTemplate.postForObject(url, entity, String.class);


            if("Site name already exists.".equals(result)){
                // key already existed
                // BE is super funky, need to do this
                return s;
            }

            // good response, set the public key
            // set public key
            s.setPubkey(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s;
    }


    public static String getPrivateKeyForSite(String siteId){
        // The connection URL
        final String url = getURL("/rest/download?name={siteId}");

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        final String result = restTemplate.getForObject(url, String.class, siteId);

        return result;
    }
}
