package com.synle.counterfeit_goods_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;

/**
 * Created by syle on 5/3/18.
 */

public class DashboardBase extends AppCompatActivity {
    public void onClickNewItem(View v){
        Intent intent = new Intent(this, ItemCreation.class);
        startActivity(intent);
    }


    public void onClickSearchItem(View v){
        Intent intent = new Intent(this, ItemSearchForm.class);
        startActivity(intent);
    }


    public void onClickListItem(View v){
        Intent intent = new Intent(this, ItemSearchResult.class);
        startActivity(intent);
    }

    public void onClickLogOut(View v){
        CommonUtil.clearSetting(getApplicationContext());
        finish();
    }


    protected void onClickSettings(View v){
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }

    protected void onClickViewYourItems(View v){
        Intent intent = new Intent(this, ItemSearchResult.class);
        intent.putExtra(getString(R.string.intent_key_show_your_items), true);
        startActivity(intent);
    }
}
