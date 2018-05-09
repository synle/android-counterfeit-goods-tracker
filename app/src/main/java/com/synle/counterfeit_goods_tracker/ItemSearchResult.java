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
import java.util.Set;

public class ItemSearchResult extends ListActivity {
    Site currentSite;
    String searchKeyword;
    boolean showYourItems = false;
    TextView txtSearchKeyWord;

//    mainly used for the list.
    List<Item> listItems=new ArrayList<Item>();
    ArrayAdapter<Item> adapter;

    boolean shouldUsePrefForYourItems = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTitle(getString(R.string.search_item_results));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search_result);

        currentSite = new Site(getApplicationContext(), getString(R.string.pref_key_site_name), getString(R.string.pref_key_site_location), getString(R.string.pref_key_site_prikey), getString(R.string.pref_key_site_pubkey));


        // get the search keyword and populate it in the text
        Intent intent = getIntent();
        searchKeyword = intent.getStringExtra(getString(R.string.intent_key_search_keyword));
        showYourItems = intent.getBooleanExtra(getString(R.string.intent_key_show_your_items), false);

//        populate the search
        txtSearchKeyWord = findViewById(R.id.txtSearchKeyWord);
        txtSearchKeyWord.setText(searchKeyword);

        // set list view adapter
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

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
        CommonUtil.showToastMessage(getApplicationContext(), "Something is wrong with the search result");
    }

    public void onActionSucess(Item[] items){
//        dynamically add items...
//        https://stackoverflow.com/questions/4540754/dynamically-add-elements-to-a-listview-android
        listItems.clear();


        if(shouldUsePrefForYourItems){
            Set<String> myItemIds = CommonUtil.getSettingValueAsStringSet(getApplicationContext(), getString(R.string.pref_key_my_item_ids));

            for(Item item : items){
                if(showYourItems){
                    if(myItemIds.contains(item.getId())){
                        listItems.add(item);
                    }
                } else if(searchKeyword == null || searchKeyword.length() == 0){
                    // empty search keyword, show all
                    listItems.add(item);

                } else if(item.getName().contains(searchKeyword)){
                    // has keyword, then search for the text...
                    listItems.add(item);
                }
            }
        } else {
            for(Item item : items){
                if(searchKeyword == null || searchKeyword.length() == 0){
                    // empty search keyword, show all
                    listItems.add(item);

                } else if(item.getName().contains(searchKeyword)){
                    // has keyword, then search for the text...
                    listItems.add(item);
                }
            }
        }

        System.out.println("all items:" + items.length);
        System.out.println("matched items:" + listItems.size());

        adapter.notifyDataSetChanged();


        if(listItems.size() == 0){
            CommonUtil.showToastMessage(getApplicationContext(), "No Matched Item Found...");
        }
    }


    private class MyAsyncTask extends AsyncTask<String, Void, Item[]> {
        @Override
        protected Item[] doInBackground(String... params) {
            try {
                if(shouldUsePrefForYourItems){
                    return DataUtil.getAllItems();
                } else {
                    if(showYourItems == false){
                        return DataUtil.getAllItems();
                    } else {
                        return DataUtil.getYourItems(currentSite.getPubkey());
                    }
                }

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
