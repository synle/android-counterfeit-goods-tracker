package com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common;

import android.net.Uri;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Item;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by syle on 4/26/18.
 */

public class DataUtil {
    public final static String baseUrl = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828";
    static final ObjectMapper objectMapper = new ObjectMapper();


    public static String getURL(String url){
//        String url2 = "http://10.0.2.2:3000" + url;
        String url2 = baseUrl + url;

        System.out.println("url: " + url2);

        return url2;
    }

    public static Site[] getAllSites(){
        // The connection URL
        final String url = getURL("/rest/sites");

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        final String result = restTemplate.getForObject(url, String.class);


//        due to back end return text/plain
//        hacky way to parse json...
//        https://springframework.guru/processing-json-jackson
        try {
            return objectMapper.readValue(result, Site[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Site createSite(Site s) {
        final String url = getURL("/rest/regsite");
        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());


//            https://www.google.com/search?q=jackson+to+json&oq=jackson+to+json&aqs=chrome..69i57.6205j0j7&sourceid=chrome&ie=UTF-8
//            Object result = restTemplate.postForEntity(url, s, String.class);
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            final HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(s), headers);
            final String result = restTemplate.postForObject(url, entity, String.class);


            if("Site name already exists.".equals(result)){
                // key already existed
                // BE is super funky, need to do this
                return null;
            }

            // good response, set the public key
            // set public key
            s.setPubkey(result);

            return s;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getPrivateKeyForSite(String siteId){
        // The connection URL
        final String url = getURL("/rest/download?name={siteId}");

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        final String result = restTemplate.getForObject(url, String.class, siteId);

        return result;
    }


    public static Site createSite(String siteName, String siteLocation) throws Exception{
        Site s = new Site();
        s.setName(siteName);
        s.setLocation(siteLocation);

        // create the site if possible
        s = DataUtil.createSite(s);

        if(s != null && s.getPubkey() != null){
            // has pub key, now go and get private key...
            String prikey = DataUtil.getPrivateKeyForSite(s.getName());
            if(prikey != null){
                // successfully create the site...
                s.setPrikey(prikey);
                return s;
            } else {
                throw new Exception("Private key cannot be downloaded");
            }
        }

        throw new Exception("Site already existed");
    }


    public static void searchItems(){

    }

    public static Item registerItem(Item newItem){
        // The connection URL
        final String url = getURL("/rest/regitem");


        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            final HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(newItem), headers);
            final String result = restTemplate.postForObject(url, entity, String.class);

            if(result.indexOf(" ") >= 0){
                throw new Exception(result);
            }

            newItem.setHash(result);
            return newItem;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    public static Item sendItem(Item newItem){
        final String url = getURL("/rest/append");


        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            final HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(newItem), headers);
            final String result = restTemplate.postForObject(url, entity, String.class);

            if(result.indexOf(" ") >= 0){
                throw new Exception(result);
            }

            newItem.setHash(result);
            return newItem;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String encrypt(String siteName, String text){
        final String url = getURL("/rest/enc?text={text}&site={siteName}");

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        return restTemplate.getForObject(url, String.class, text, siteName);
    }


    public static Item[] getAllItems(){
        final String url = getURL("/rest/items");

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String result = restTemplate.getForObject(url, String.class);

        try {
            return objectMapper.readValue(result, Item[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] getItemHistory(String itemId) {
        final String url = getURL("/rest/history?id={itemId}");

        final RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String result = restTemplate.getForObject(url, String.class, itemId);

        try {
            return objectMapper.readValue(result, String[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Item[] getYourItems(String pubkey) {
        final String url = getURL("/rest/fetch");


        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            final HttpEntity<String> entity = new HttpEntity<>(pubkey, headers);
            final String result = restTemplate.postForObject(url, entity, String.class);
            return objectMapper.readValue(result, Item[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
