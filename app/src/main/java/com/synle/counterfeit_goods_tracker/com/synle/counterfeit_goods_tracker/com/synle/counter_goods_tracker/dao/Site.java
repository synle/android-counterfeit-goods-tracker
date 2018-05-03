package com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao;

import android.content.Context;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;

/**
 * Created by syle on 4/26/2018.
 */

public class Site {
    private String name;
    private String location;
    private String pubkey;
    private String prikey;

    public Site() {
    }

    public Site(Context context, String pref_key_site_name, String pref_key_site_location, String pref_key_site_prikey, String pref_key_site_pubkey) {
        this.name = CommonUtil.getSettingValue(context, pref_key_site_name);
        this.location = CommonUtil.getSettingValue(context, pref_key_site_location);
        this.prikey = CommonUtil.getSettingValue(context, pref_key_site_prikey);
        this.pubkey = CommonUtil.getSettingValue(context, pref_key_site_pubkey);
    }

    public Site(String name, String location, String pubkey, String prikey) {
        this.name = name;
        this.location = location;
        this.pubkey = pubkey;
        this.prikey = prikey;
    }

    @Override
    public String toString() {
        return name + " - " + location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPubkey() {
        return pubkey;
    }

    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

    public String getPrikey() {
        return prikey;
    }

    public void setPrikey(String prikey) {
        this.prikey = prikey;
    }
}
