package com.synle.counterfeit_goods_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;

public class DashboardAgency extends DashboardBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(getString(R.string.dashboard_welcome_agency));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_agency);
    }

}
