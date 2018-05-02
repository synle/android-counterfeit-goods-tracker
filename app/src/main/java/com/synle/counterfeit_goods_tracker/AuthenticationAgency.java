package com.synle.counterfeit_goods_tracker;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.DataUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

public class AuthenticationAgency extends AuthenticationBase {
    public AuthenticationAgency(){
        this.isAgency = true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_agency);


        txtSiteName = findViewById(R.id.txtSiteName);
        txtSiteLocation = findViewById(R.id.txtSiteLocation);

        txtSiteName.setText("Agency-Santa-Clara");
        txtSiteLocation.setText("Santa Clara");

        intent = new Intent(this, DashboardAgency.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
    }
}
