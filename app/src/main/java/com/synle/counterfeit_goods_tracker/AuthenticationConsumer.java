package com.synle.counterfeit_goods_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;

public class AuthenticationConsumer extends AuthenticationBase {
    public AuthenticationConsumer(){
        this.isAgency = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_consumer);


        txtSiteName = findViewById(R.id.txtSiteName);
        txtSiteLocation = findViewById(R.id.txtSiteLocation);

        txtSiteName.setText("Consumer-Santa-Clara");
        txtSiteLocation.setText("Santa Clara");

        intent = new Intent(this, DashboardConsumer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
    }
}
