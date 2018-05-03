package com.synle.counterfeit_goods_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;

public class ItemUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(getString(R.string.update_item));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_update);
    }

    public void onClickSave(View v) {
        String pref_key_site_name = CommonUtil.getSettingValue(getApplicationContext(), getString(R.string.pref_key_site_name));
        String pref_key_site_location = CommonUtil.getSettingValue(getApplicationContext(), getString(R.string.pref_key_site_location));
        String pref_key_site_prikey = CommonUtil.getSettingValue(getApplicationContext(), getString(R.string.pref_key_site_prikey));
        String pref_key_site_pubkey = CommonUtil.getSettingValue(getApplicationContext(), getString(R.string.pref_key_site_pubkey));
    }

    public void onClickCancel(View v) {
        finish();
    }
}
