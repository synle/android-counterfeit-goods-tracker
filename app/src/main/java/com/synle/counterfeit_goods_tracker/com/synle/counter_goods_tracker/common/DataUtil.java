package com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common;

import android.util.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by syle on 4/26/18.
 */

public class DataUtil {
    private static final String URL_SITE_LIST = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/sites";
    private static final String URL_SITE_REG = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/regsite";
    private static final String URL_SITE_DOWNLOAD = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/download?name=site5";
    private static final String URL_ITEM_REG = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/regitem";
    private static final String URL_ITEM_SEND = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/fetch?pk=MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAskifo4Vr0+dILiXnoOTEkOMKEBbnJyXigZemO5hSOWQaI9jftH3+sFtn5Z1dzeUDtJlUGUlUqRxssdH0OOw8tPIYy2ao5wQuRymBQ9r14/PIxl0JEomnw1hz7N22QGZDjPwy7tuxm7J0zfX2oOe902bdW18lvTNi/tCm+P7lC3PpfofsuWVvij0fFQwuYxyxZZIM1yQqfVZ3+y2yFY5KRXBMON9vBmQzCa4qMNZOLnQjgwL2TaapAlMts/ZBuGMD8jHcXGZmC0X6vOBPv4nax8Gll/W85G6vFq4H5/+aq7RObcFvMIhN+gdIMtlvgZyCOnVxBBYgWIcNw/63FxUHJwIDAQAB";
    private static final String URL_ITEM_BLOCK_APPEND = "http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/append";
    private static final String URL_ITEM_HISTORY = " http://ec2-34-192-241-153.compute-1.amazonaws.com:2828/rest/history?id=ABC123";


    public static void getAllSites(){

    }
}
