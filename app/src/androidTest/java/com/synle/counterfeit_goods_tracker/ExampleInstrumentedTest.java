package com.synle.counterfeit_goods_tracker;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CryptoUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.DataUtil;

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
    public void testEncryptDescryptWithAndroidContext(){
        try {
            String description = "a-sy-test";
            List<String> strings1 = Files.readAllLines(FileSystems.getDefault().getPath("/tmp/" + description + ".key"));
            List<String> strings2 = Files.readAllLines(FileSystems.getDefault().getPath("/tmp/" + description + ".pub"));

            String newLine = "\n";
            String priKey = strings1.stream().collect(Collectors.joining(newLine));
            String pubKey = strings2.stream().collect(Collectors.joining(newLine));

            String code1 = CryptoUtil.rsaEncWithPlainKey("abcdef", priKey);
            String code2 = CryptoUtil.rsaDecWithPlainKey(code1, pubKey);



            System.out.println("123");
            System.out.println(code1);
            System.out.println(code2);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        List<String> strings2 = Files.readAllLines(new Path("/tmp/" + description + ".pub"));

//        try {
//            String keyfile = "/tmp/" + description + ".key";
//            String code1 = CryptoUtil.rsaEnc("abc", keyfile);
////            String code2 = CryptoUtil.rsaDec("abc", description);
//
//            System.out.println(code1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
