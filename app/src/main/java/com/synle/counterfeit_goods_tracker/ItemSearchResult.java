package com.synle.counterfeit_goods_tracker;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.DataUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Item;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemSearchResult extends ListActivity {
    String searchKeyword;
    TextView txtSearchKeyWord;

//    mainly used for the list.
    List<Item> listItems=new ArrayList<Item>();
    ArrayAdapter<Item> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search_result);

        // get the search keyword and populate it in the text
        Intent intent = getIntent();
        searchKeyword = intent.getStringExtra(getString(R.string.intent_key_search_keyword));
        txtSearchKeyWord = findViewById(R.id.txtSearchKeyWord);
        txtSearchKeyWord.setText(searchKeyword);

        // set list view adapter
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);

        //poulate the search list
        new MyAsyncTask().execute();
    }

    public void onClickCancel(View v) {
        finish();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        System.out.println("clicked on: " + position + " : " + id);
        Item clickedItem = listItems.get(position);

//        either item history or item list...
        Intent intent = new Intent(this, ItemHistory.class);
        intent.putExtra(getString(R.string.intent_key_item_id), clickedItem.getId());
        intent.putExtra(getString(R.string.intent_key_item_name), clickedItem.getName());
        startActivity(intent);
    }

    public void onActionError(){
        CommonUtil.showToastMessage(getApplicationContext(), "Something is wrongw with the search result");
    }

    public void onActionSucess(Item[] items){
//        dynamically add items...
//        https://stackoverflow.com/questions/4540754/dynamically-add-elements-to-a-listview-android
        listItems.clear();
        for(Item item : items){
            if(searchKeyword == null || searchKeyword.length() == 0){
                // empty search keyword, show all
                listItems.add(item);

            } else if(item.getName().contains(searchKeyword)){
                // has keyword, then search for the text...
                listItems.add(item);
            }
        }
        System.out.println("all items:" + items.length);
        System.out.println("matched items:" + listItems.size());
        adapter.notifyDataSetChanged();
    }


    private class MyAsyncTask extends AsyncTask<String, Void, Item[]> {
        @Override
        protected Item[] doInBackground(String... params) {
            try {
                return DataUtil.getAllItems();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public void onPostExecute(Item[] items) {
            if(items == null && items.length > 0){
                onActionError();
            } else {
                onActionSucess(items);
            }
        }
    }
}
