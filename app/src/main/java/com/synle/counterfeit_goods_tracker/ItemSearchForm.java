package com.synle.counterfeit_goods_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ItemSearchForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search_form);
    }

    public void onClickSearch(View v){
        Intent intent = new Intent(this, ItemSearchResult.class);
        startActivity(intent);
    }

    public void onClickCancel(View v) {
        finish();
    }
}
