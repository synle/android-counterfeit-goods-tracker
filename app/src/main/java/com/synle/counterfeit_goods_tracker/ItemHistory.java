package com.synle.counterfeit_goods_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ItemHistory extends AppCompatActivity {
    String itemId;
    String itemName;

    TextView txtItemId;
    TextView txtItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_history);

        Intent intent = getIntent();
        itemId = intent.getStringExtra(getString(R.string.intent_key_item_id));
        itemName = intent.getStringExtra(getString(R.string.intent_key_item_name));

        txtItemId = findViewById(R.id.txtItemId);
        txtItemName = findViewById(R.id.txtItemName);

        txtItemId.setText(itemId);
        txtItemName.setText(itemName);
    }

    public void onClickCancel(View v) {
        finish();
    }
}
