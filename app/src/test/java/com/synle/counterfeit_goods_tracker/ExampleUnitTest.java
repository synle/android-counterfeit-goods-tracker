package com.synle.counterfeit_goods_tracker;

import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CommonUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.CryptoUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common.DataUtil;
import com.synle.counterfeit_goods_tracker.com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.dao.Site;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }





//    String description = "a-sy-test";
//    @Test
//    public void testGenerateKeys(){
//        try {
//            CryptoUtil.generateKeypair(description);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//

//    @Test
//    public void testEncryptDescrypt(){
//        try {
//            List<String> strings1 = Files.readAllLines(FileSystems.getDefault().getPath("/tmp/" + description + ".key"));
//            List<String> strings2 = Files.readAllLines(FileSystems.getDefault().getPath("/tmp/" + description + ".pub"));
//
//            String newLine = "\n";
//            String priKey = strings1.stream().collect(Collectors.joining(newLine));
//            String pubKey = strings2.stream().collect(Collectors.joining(newLine));
//
//            String code1 = CryptoUtil.rsaEncWithPlainKey("abcdef", priKey);
//            String code2 = CryptoUtil.rsaDecWithPlainKey(code1, pubKey);
//
//
//
//            System.out.println("123");
//            System.out.println(code1);
//            System.out.println(code2);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        List<String> strings2 = Files.readAllLines(new Path("/tmp/" + description + ".pub"));
//
////        try {
////            String keyfile = "/tmp/" + description + ".key";
////            String code1 = CryptoUtil.rsaEnc("abc", keyfile);
//////            String code2 = CryptoUtil.rsaDec("abc", description);
////
////            System.out.println(code1);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }
}