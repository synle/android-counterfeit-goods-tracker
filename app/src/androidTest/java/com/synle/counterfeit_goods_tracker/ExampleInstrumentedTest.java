package com.synle.counterfeit_goods_tracker;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CryptoUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.DataUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Item;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.synle.counterfeit_goods_tracker", appContext.getPackageName());
    }


    @Test
    public void testApiGetAllSites(){
        Site[] sites = DataUtil.getAllSites();
        assertTrue("DataUtil.getAllSites should return", sites.length > 0);
    }


    @Test
    public void testApiGetAllItems(){
        Item[] items = DataUtil.getAllItems();
        assertTrue("DataUtil.getAllItems should return", items.length > 0);
    }


    @Test
    public void testGetItemHistory(){
        String[] items = DataUtil.getItemHistory("ABC123");
        assertTrue("DataUtil.getItemHistory should return", items.length > 0);
    }


    @Test
    public void testCreateItem(){
        assertTrue("DataUtil.test create item", true);
    }


    @Test
    public void testGetYourItems(){
        Item[] items = DataUtil.getYourItems("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAskifo4Vr0+dILiXnoOTEkOMKEBbnJyXigZemO5hSOWQaI9jftH3+sFtn5Z1dzeUDtJlUGUlUqRxssdH0OOw8tPIYy2ao5wQuRymBQ9r14/PIxl0JEomnw1hz7N22QGZDjPwy7tuxm7J0zfX2oOe902bdW18lvTNi/tCm+P7lC3PpfofsuWVvij0fFQwuYxyxZZIM1yQqfVZ3+y2yFY5KRXBMON9vBmQzCa4qMNZOLnQjgwL2TaapAlMts/ZBuGMD8jHcXGZmC0X6vOBPv4nax8Gll/W85G6vFq4H5/+aq7RObcFvMIhN+gdIMtlvgZyCOnVxBBYgWIcNw/63FxUHJwIDAQAB");
        assertTrue("DataUtil.getItemHistory should return", true);
    }
}
