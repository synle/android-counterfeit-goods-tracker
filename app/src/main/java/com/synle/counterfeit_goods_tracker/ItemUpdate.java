package com.synle.counterfeit_goods_tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ItemUpdate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_update);
    }

    public void onClickSave(View v) {
    }

    public void onClickCancel(View v) {
        finish();
    }
}