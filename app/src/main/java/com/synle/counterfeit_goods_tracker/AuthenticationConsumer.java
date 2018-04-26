package com.synle.counterfeit_goods_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;

public class AuthenticationConsumer extends AppCompatActivity {
    EditText txtSiteName;
    EditText txtSiteLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_consumer);


        txtSiteName = findViewById(R.id.txtSiteName);
        txtSiteLocation = findViewById(R.id.txtSiteLocation);

        txtSiteName.setText("Consumer-Santa-Clara");
        txtSiteLocation.setText("Santa Clara");
    }

    public void onClickGenerateNewIdentity(View v){
        final String location = txtSiteLocation.getText().toString();
        final String name = txtSiteName.getText().toString();

        if(location.length() == 0 || name.length() == 0){
            CommonUtil.showToastMessage(getApplicationContext(), getString(R.string.errorSiteLocSiteNameRequired));
            return;
        }

        String pubkey = "consumer-123";
        String prikey = "consumer-abc";
        boolean isAgency = false;


//        TODO: call back end for the above data...


        CommonUtil.setSettingValue(getApplicationContext(), getString(R.string.pref_key_site_name), name);
        CommonUtil.setSettingValue(getApplicationContext(), getString(R.string.pref_key_site_location), location);
        CommonUtil.setSettingValue(getApplicationContext(), getString(R.string.pref_key_site_prikey), prikey);
        CommonUtil.setSettingValue(getApplicationContext(), getString(R.string.pref_key_site_pubkey), pubkey);
        CommonUtil.setSettingValue(getApplicationContext(), getString(R.string.pref_key_is_agency), isAgency);


        Intent intent = new Intent(this, DashboardConsumer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intent);
    }
}
