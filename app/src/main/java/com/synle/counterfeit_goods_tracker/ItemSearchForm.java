package com.synle.counterfeit_goods_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ItemSearchForm extends AppCompatActivity {
    EditText txtSearchKeyWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(getString(R.string.search_item));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search_form);

        txtSearchKeyWord = findViewById(R.id.txtSearchKeyWord);
    }

    public void onClickSearch(View v){
        Intent intent = new Intent(this, ItemSearchResult.class);
        intent.putExtra( getString(R.string.intent_key_search_keyword), txtSearchKeyWord.getText().toString().trim());
        startActivity(intent);
    }

    public void onClickCancel(View v) {
        finish();
    }
}
