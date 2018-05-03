package com.synle.counterfeit_goods_tracker;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.DataUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Item;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

public class ItemCreation extends AppCompatActivity {
    EditText txtItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_creation);

        txtItemName = findViewById(R.id.txtItemName);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClickSave(View v){
        String pref_key_site_name = CommonUtil.getSettingValue(getApplicationContext(), getString(R.string.pref_key_site_name));
        String pref_key_site_location = CommonUtil.getSettingValue(getApplicationContext(), getString(R.string.pref_key_site_location));
        String pref_key_site_prikey = CommonUtil.getSettingValue(getApplicationContext(), getString(R.string.pref_key_site_prikey));
        String pref_key_site_pubkey = CommonUtil.getSettingValue(getApplicationContext(), getString(R.string.pref_key_site_pubkey));


        final String itemName = txtItemName.getText().toString();
        final String itemId = pref_key_site_name  + "-" + pref_key_site_location + "-" + CommonUtil.getUnixTimestamp();

        // generate the payload for the item
        final String payload = CommonUtil.getPayloadForItem(itemName, pref_key_site_prikey);

        final Item newItem = new Item();
        newItem.setName(itemName);
        newItem.setId(itemId);
        newItem.setNextpubkey(pref_key_site_pubkey);
        newItem.setPayload(payload);

        new MyAsyncTask(newItem).execute();
    }


    public void onClickCancel(View v) {
        finish();
    }

    public void onActionError(){

    }

    public void onActionSucess(Item i){
        System.out.println("action success");
        System.out.println(i);
    }


    private class MyAsyncTask extends AsyncTask<String, Void, Item> {
        Item item;
        public MyAsyncTask(Item newItem){
            this.item = newItem;
        }

        @Override
//        name, location
        protected Item doInBackground(String... params) {
            try {
                // register the item
                Item i = DataUtil.registerItem(this.item);
                return i;
            } catch (Exception e) {
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
