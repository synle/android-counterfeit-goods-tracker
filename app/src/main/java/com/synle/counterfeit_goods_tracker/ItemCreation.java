package com.synle.counterfeit_goods_tracker;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CryptoUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.DataUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Item;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

public class ItemCreation extends AppCompatActivity {
    EditText txtItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle(getString(R.string.new_item));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_creation);

        txtItemName = findViewById(R.id.txtItemName);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClickSave(View v){
        Site site = new Site(getApplicationContext(), getString(R.string.pref_key_site_name), getString(R.string.pref_key_site_location), getString(R.string.pref_key_site_prikey), getString(R.string.pref_key_site_pubkey));

        final String itemName = txtItemName.getText().toString();
        final String itemId = site.getName() + "-" + CommonUtil.getUnixTimestamp();

        // generate the payload for the item
        final Item newItem = new Item();
        newItem.setName(itemName);
        newItem.setId(itemId);
        newItem.setNextpubkey(site.getPubkey());
        new MyAsyncTask(newItem, site).execute();
    }


    public void onClickCancel(View v) {
        finish();
    }

    public void onActionError(){

    }

    public void onActionSucess(Item i){
        CommonUtil.showToastMessage(getApplicationContext(), "Item created...");
        finish();
    }


    private class MyAsyncTask extends AsyncTask<String, Void, Item> {
        Item item;
        Site site;

        public MyAsyncTask(Item item, Site site){
            this.item = item;
            this.site = site;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
//        name, location
        protected Item doInBackground(String... params) {
            try {
//                1.	Find an item ID and encrypt it with the private key file
                final String newPayload = DataUtil.encrypt(site.getName(), item.getId());

                // register the item
                item.setPayload(newPayload);
                item = DataUtil.registerItem(item);
                System.out.println("1-regItem: " + item.getHash());


//                2.	Encrypt MD5 with private key like III.1.
                final String newPayload2 = DataUtil.encrypt(site.getName(), item.getHash());
                item.setPayload(newPayload);

                // append it
                item = DataUtil.sendItem(item);
                System.out.println("2-appendItem: " + item.getHash());

                return item;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        public void onPostExecute(Item i) {
            if(i == null){
                onActionError();
            } else {
                onActionSucess(i);
            }
        }
    }
}
