package com.synle.counterfeit_goods_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;

public class DashboardAgency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_agency);
    }

    public void onClickNewItem(View v){
        Intent intent = new Intent(this, ItemCreation.class);
        startActivity(intent);
    }


    public void onClickSearchItem(View v){
        Intent intent = new Intent(this, ItemSearchForm.class);
        startActivity(intent);
    }

    public void onClickLogOut(View v){
        CommonUtil.clearSetting(getApplicationContext());
        finish();
    }


    protected void onClickSettings(View v){
        Intent intent = new Intent(this, SettingAgency.class);
        startActivity(intent);
    }



}
