package com.synle.counterfeit_goods_tracker;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.DataUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

/**
 * Created by syle on 5/1/18.
 */

public class AuthenticationBase extends AppCompatActivity {
    EditText txtSiteName;
    EditText txtSiteLocation;
    boolean isAgency;
    Intent intent;

    public void onClickGenerateNewIdentity(View v){
        final String name = txtSiteName.getText().toString();
        final String location = txtSiteLocation.getText().toString();

        if(location.length() == 0 || name.length() == 0){
            CommonUtil.showToastMessage(getApplicationContext(), getString(R.string.errorSiteLocSiteNameRequired));
            return;
        }

        new MyAsyncTask(name, location).execute();
    }

    public void onClickChangeOption(View v){
        Intent intentLoginOption = new Intent(this, AuthenticationOption.class);
        intentLoginOption.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        startActivity(intentLoginOption);
    }

    private void onActionSucess(Site s){
        CommonUtil.setSettingValue(getApplicationContext(), getString(R.string.pref_key_site_name), s.getName());
        CommonUtil.setSettingValue(getApplicationContext(), getString(R.string.pref_key_site_location), s.getLocation());
        CommonUtil.setSettingValue(getApplicationContext(), getString(R.string.pref_key_site_prikey), s.getPrikey());
        CommonUtil.setSettingValue(getApplicationContext(), getString(R.string.pref_key_site_pubkey), s.getPubkey());
        CommonUtil.setSettingValue(getApplicationContext(), getString(R.string.pref_key_is_agency), isAgency);

        startActivity(intent);
    }

    private void onActionError(){
        CommonUtil.showToastMessage(getApplicationContext(), "This currentSite name is no longer available, please choose a different name...");
    }




    private class MyAsyncTask extends AsyncTask<String, Void, Site> {
        String name;
        String location;
        public MyAsyncTask(String name, String location){
            this.name = name;
            this.location = location;
        }

        @Override
//        name, location
        protected Site doInBackground(String... params) {
            try {
                final Site s = DataUtil.createSite(name, location);
                return s;
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        public void onPostExecute(Site s) {
            if(s == null){
                onActionError();
            } else {
                onActionSucess(s);
            }
        }
    }

}