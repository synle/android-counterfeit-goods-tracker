package com.synle.counterfeit_goods_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

public class Setting extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(getString(R.string.settings));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setViewDataForSettingsForm();
    }

    protected void setViewDataForSettingsForm(){
        Site site = new Site(getApplicationContext(), getString(R.string.pref_key_site_name), getString(R.string.pref_key_site_location), getString(R.string.pref_key_site_prikey), getString(R.string.pref_key_site_pubkey));

//        do the view
        TextView txtPublicKey = findViewById(R.id.txtPublicKey);
        TextView txtPrivateKey = findViewById(R.id.txtPrivateKey);
        TextView txtName = findViewById(R.id.txtName);
        TextView txtLocation = findViewById(R.id.txtLocation);

        txtPublicKey.setText(site.getPubkey());
        txtPrivateKey.setText(site.getPrikey());
        txtName.setText(site.getName());
        txtLocation.setText(site.getLocation());
    }

    public void onClickCancel(View v){
        finish();
    }
}