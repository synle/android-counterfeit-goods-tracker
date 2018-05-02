package com.synle.counterfeit_goods_tracker;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.DataUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}
