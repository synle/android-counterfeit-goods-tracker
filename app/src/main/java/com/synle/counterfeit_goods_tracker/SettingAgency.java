package com.synle.counterfeit_goods_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

public class SettingAgency extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_agency);
        setViewDataForSettingsForm();
    }

    protected void setViewDataForSettingsForm(){
        String pref_key_site_name = CommonUtil.getSettingValue(getApplicationContext(), getString(R.string.pref_key_site_name));
        String pref_key_site_location = CommonUtil.getSettingValue(getApplicationContext(), getString(R.string.pref_key_site_location));
        String pref_key_site_prikey = CommonUtil.getSettingValue(getApplicationContext(), getString(R.string.pref_key_site_prikey));
        String pref_key_site_pubkey = CommonUtil.getSettingValue(getApplicationContext(), getString(R.string.pref_key_site_pubkey));


//        do the view
        TextView txtPublicKey = findViewById(R.id.txtPublicKey);
        TextView txtPrivateKey = findViewById(R.id.txtPrivateKey);
        TextView txtName = findViewById(R.id.txtName);
        TextView txtLocation = findViewById(R.id.txtLocation);

        txtPublicKey.setText(pref_key_site_pubkey);
        txtPrivateKey.setText(pref_key_site_prikey);
        txtName.setText(pref_key_site_name);
        txtLocation.setText(pref_key_site_location);
    }

    public void onClickCancel(View v){
        finish();
    }
}
