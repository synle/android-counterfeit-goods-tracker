package com.synle.counterfeit_goods_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.RestClient;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.rest.spring.annotations.RestService;

import java.util.List;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @RestService
    RestClient restClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        testNetwork();

        String pref_key_site_prikey = CommonUtil.getSettingValue(getApplicationContext(), getString(R.string.pref_key_site_prikey));
        if(pref_key_site_prikey.length() > 0){
            boolean isAgency = CommonUtil.getSettingValueAsBoolean(getApplicationContext(), getString(R.string.pref_key_is_agency));
            onResumeToDashboard(isAgency);
        }
    }

    public void onResumeToDashboard(boolean isAgency){
        if(isAgency){
            Intent intent = new Intent(this, DashboardAgency.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, DashboardConsumer.class);
            startActivity(intent);
        }
    }


    public void onClickLogin(View v){
        Intent intent = new Intent(this, AuthenticationOption.class);
        startActivity(intent);
    }

    public void testNetwork(){
        try{
            List<Site> listSize = restClient.getSites();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
