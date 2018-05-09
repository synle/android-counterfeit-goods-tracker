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
import java.util.List;

public class ItemUpdate extends ListActivity {
    String itemId;
    String itemName;
    String md5Hash;
    String encryptedPayload;
    Site currentSite;
    Site destSite;
    TextView txtItemId;
    TextView txtItemName;
    TextView txtLastOwner;

    //    mainly used for the list.
    List<Site> listItems = new ArrayList<Site>();
    ArrayAdapter<Site> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        currentSite = new Site(getApplicationContext(), getString(R.string.pref_key_site_name), getString(R.string.pref_key_site_location), getString(R.string.pref_key_site_prikey), getString(R.string.pref_key_site_pubkey));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_update);

        Intent intent = getIntent();
        itemId = intent.getStringExtra(getString(R.string.intent_key_item_id));
        itemName = intent.getStringExtra(getString(R.string.intent_key_item_name));
        md5Hash = CommonUtil.getSettingValue(getApplicationContext(), itemId);

        txtItemId = findViewById(R.id.txtItemId);
        txtItemName = findViewById(R.id.txtItemName);
        txtLastOwner = findViewById(R.id.txtLastOwner);

        txtItemId.setText(itemId);
        txtItemName.setText(itemName);


        // set list view adapter
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);

        new MyAsyncTaskGetItemOwners().execute(itemId);
        new MyAsyncTaskGetAllOwners().execute();
    }


    public void onClickCancel(View v) {
        finish();
    }


    //    click to assign new owner
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        System.out.println("clicked on: " + position + " : " + id);
        Site clickedItem = listItems.get(position);
        destSite = clickedItem;
        CommonUtil.showToastMessage(getApplicationContext(), "Assigning the item to site: " + clickedItem.toString());

        Item updatedItem = new Item();
        updatedItem.setName(itemName);
        updatedItem.setId(itemId);
        updatedItem.setNextpubkey(clickedItem.getPubkey());
        new MyAsyncTaskAssignItem().execute(updatedItem);
    }


    public void onActionError(){
        CommonUtil.showToastMessage(getApplicationContext(), "Something is wrong with API");
    }

    public void onActionSucessUpdateLastOwner(Site lastSite){
        txtLastOwner.setText(lastSite.toString());
    }

    public void onActionSucessUpdateAllOwners(Site[] sites){
        listItems.clear();
        for(Site site : sites){
            listItems.add(site);
        }
        System.out.println("all owners:" + sites.length);
        adapter.notifyDataSetChanged();
    }

    public void onActionSuccessAssigned(){
        CommonUtil.showToastMessage(getApplicationContext(), "Item assigned to: " + destSite.toString());
        finish();
    }

    private class MyAsyncTaskGetItemOwners extends AsyncTask<String, Void, Site[]> {
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
                onActionSucessUpdateLastOwner(items[items.length - 1]);
            }
        }
    }


    private class MyAsyncTaskGetAllOwners extends AsyncTask<String, Void, Site[]> {
        @Override
        protected Site[] doInBackground(String... strings) {
            return DataUtil.getAllSites();
        }

        @Override
        public void onPostExecute(Site[] items) {
            if(items == null && items.length > 0){
                onActionError();
            } else {
                onActionSucessUpdateAllOwners(items);
            }
        }
    }


    private class MyAsyncTaskAssignItem extends AsyncTask<Item, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Item... items) {
            if(items.length == 1){
                try{
                    Item item = items[0];
                    // doing the encryption
                    if(md5Hash != null && !"".equals(md5Hash)){
                        encryptedPayload = DataUtil.encrypt(currentSite.getName(), md5Hash);
                        item.setPayload(encryptedPayload);
                    }
                    DataUtil.sendItem(item);
                    return true;
                } catch(Exception e){
                    e.printStackTrace();
                    return false;
                }
            }

            return false;
        }

        @Override
        public void onPostExecute(Boolean isSuccess) {
            if(isSuccess == true){
                onActionSuccessAssigned();
            } else {
                onActionError();
            }
        }
    }
}
