package com.synle.counterfeit_goods_tracker;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.DataUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Item;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemHistory extends ListActivity {
    String itemId;
    String itemName;

    TextView txtItemId;
    TextView txtItemName;

    //    mainly used for the list.
    List<Site> listItems = new ArrayList<Site>();
    ArrayAdapter<Site> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(getString(R.string.item_history));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_history);

        Intent intent = getIntent();
        itemId = intent.getStringExtra(getString(R.string.intent_key_item_id));
        itemName = intent.getStringExtra(getString(R.string.intent_key_item_name));

        txtItemId = findViewById(R.id.txtItemId);
        txtItemName = findViewById(R.id.txtItemName);

        txtItemId.setText(itemId);
        txtItemName.setText(itemName);


        // set list view adapter
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);

        new MyAsyncTask().execute(itemId);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new MyAsyncTask().execute(itemId);
    }

    public void onClickCancel(View v) {
        finish();
    }


//    click to select the item
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        System.out.println("clicked on: " + position + " : " + id);
        Site clickedItem = listItems.get(position);
        CommonUtil.showToastMessage(getApplicationContext(), clickedItem.toString());
    }


//    click to update the item
    public void onUpdateItem(View v){
        Intent intent = new Intent(this, ItemUpdate.class);
        intent.putExtra(getString(R.string.intent_key_item_id), itemId);
        intent.putExtra(getString(R.string.intent_key_item_name), itemName);
        startActivity(intent);
    }

    public void onActionError(){
        CommonUtil.showToastMessage(getApplicationContext(), "Something is wrong with the history result");
    }

    public void onActionSucess(Site[] sites){
//        dynamically add items...
//        https://stackoverflow.com/questions/4540754/dynamically-add-elements-to-a-listview-android
        listItems.clear();
        for(Site site : sites){
            listItems.add(site);
        }
        System.out.println("all items:" + sites.length);
        adapter.notifyDataSetChanged();
    }

    private class MyAsyncTask extends AsyncTask<String, Void, Site[]> {
        @Override
        protected Site[] doInBackground(String... params) {
            try {
                String[] historySiteIds = DataUtil.getItemHistory(params[0]);
                return CommonUtil.getMappedSitesBySiteIds(historySiteIds);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public void onPostExecute(Site[] items) {
            if(items == null && items.length > 0){
                onActionError();
            } else {
                onActionSucess(items);
            }
        }
    }
}
