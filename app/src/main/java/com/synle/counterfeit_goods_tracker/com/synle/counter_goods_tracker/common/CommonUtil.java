package com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.synle.counterfeit_goods_tracker.R;

/**
 * Created by syle on 4/26/2018.
 */

public class CommonUtil {
    public static void showToastMessage(Context context, String toastMessage){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, toastMessage, duration);
        toast.show();
    }

//  common util
    public static long getUnixTimestamp(){
        long unixTime = System.currentTimeMillis() / 1000L;
        return unixTime;
    }


//    pref
    public static String getSettingValue(Context context, String key){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getString(key, "");
    }

    public static boolean getSettingValueAsBoolean(Context context, String key){
        SharedPreferences sharedPref = getSharedPreferences(context);
        return sharedPref.getBoolean(key, false);
    }

    public static void clearSetting(Context context){
        SharedPreferences sharedPref = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }

    public static void setSettingValue(Context context, String key, String val){
        SharedPreferences sharedPref = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, val);
        editor.commit();
    }


    public static void setSettingValue(Context context, String key, boolean val){
        SharedPreferences sharedPref = getSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, val);
        editor.commit();
    }

    private static SharedPreferences getSharedPreferences(Context context){
        final String MY_PREFERENCE = "MY_PREFERENCE";
        SharedPreferences sharedpreferences = context.getSharedPreferences(MY_PREFERENCE, Context.MODE_PRIVATE);
        return sharedpreferences;
    }



//    key...
    public static String getPayloadForItem(String itemName, String publicKey){
        String payload = "";

        return payload;
    }
}
