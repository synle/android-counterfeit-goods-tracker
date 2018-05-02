package com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common;

/**
 * Created by syle on 5/2/18.
 */

// src:
// https://github.com/xzchenglin/sjsu/blob/master/bcTracker/bcTracker-common/src/java/helper/Utils.java
//
// workaround for javax.xml.bind.DatatypeConverter
// https://stackoverflow.com/questions/34342136/alternatives-for-datatypeconverter-in-android
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class CryptoUtil{

//    public static String generateKeypair(String name) throws Exception{
//        KeyPairGenerator keyGen = null;
//        try {
//            keyGen = KeyPairGenerator.getInstance("RSA");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        keyGen.initialize(2048);
//        KeyPair kp = keyGen.generateKeyPair();
//        Key pub = kp.getPublic();
//        Key pvt = kp.getPrivate();
//
//        writePemFile(pvt, "RSA PRIVATE KEY", "/opt/bc/" + name + ".key");
//        writePemFile(pub, "RSA PUBLIC KEY", "/opt/bc/" + name + ".pub");
//
////        String pubB64 = Base64.getEncoder().encodeToString(pub.getEncoded());
////        workaround for android
////        https://stackoverflow.com/questions/47431337/base64-support-for-different-api-levels
//        String pubB64 = android.util.Base64.encodeToString(pub.getEncoded(), android.util.Base64.DEFAULT);
//        return pubB64;
//    }
//
//    public static String rsaEnc(String text, String keyFile) throws Exception{
//
//        KeyFactory factory = KeyFactory.getInstance("RSA");
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey(factory, keyFile));
//        byte[] encrypted = cipher.doFinal(text.getBytes());
//        String hex = DatatypeConverter.printHexBinary(encrypted);
//        return hex;
//    }
//
//    public static String rsaDec(String text, String key) throws Exception{
//
//        KeyFactory factory = KeyFactory.getInstance("RSA");
//        Cipher cipher = Cipher.getInstance("RSA");
//
//
////        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));
////      workaround for android
//        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(android.util.Base64.decode(key, android.util.Base64.DEFAULT));
//
//        cipher.init(Cipher.DECRYPT_MODE, factory.generatePublic(pubKeySpec));
//        byte[] bt = cipher.doFinal(DatatypeConverter.parseHexBinary(text));
//
//        String ret = new String(bt);
//        return ret;
//    }
//
//    public static String md5(String text){
//
//        MessageDigest md = null;
//        try {
//            md = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        md.update(text.getBytes());
//        byte byteData[] = md.digest();
//
//        String hexString = DatatypeConverter.printHexBinary(byteData);
//        return hexString.toString();
//    }
//
//    private static void writePemFile(Key key, String description, String filename)
//            throws Exception {
//        PemFile pemFile = new PemFile(key, description);
//        pemFile.write(filename);
//    }
//
//    private static PrivateKey getPrivateKey(KeyFactory factory, String filename) throws Exception {
//        PemFile pemFile = new PemFile(filename);
//        byte[] content = pemFile.getPemObject().getContent();
//        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
//        return factory.generatePrivate(privKeySpec);
//    }
//
//    private static PublicKey getPublicKey(KeyFactory factory, String filename) throws Exception {
//        PemFile pemFile = new PemFile(filename);
//        byte[] content = pemFile.getPemObject().getContent();
//        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
//        return factory.generatePublic(pubKeySpec);
//    }
//
//    private static class PemFile {
//
//        private PemObject pemObject;
//
//        public PemFile (Key key, String description) {
//            this.pemObject = new PemObject(description, key.getEncoded());
//        }
//
//        public PemFile(String filename) throws IOException {
//            PemReader pemReader = new PemReader(new InputStreamReader(new FileInputStream(filename)));
//            try {
//                this.pemObject = pemReader.readPemObject();
//            } finally {
//                pemReader.close();
//            }
//        }
//
//        public void write(String filename) throws Exception {
//            PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)));
//            try {
//                pemWriter.writeObject(this.pemObject);
//            } finally {
//                pemWriter.close();
//            }
//        }
//
//        public PemObject getPemObject() {
//            return pemObject;
//        }
//    }

}