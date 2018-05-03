package com.synle.counterfeit_goods_tracker.com.synle.counter_goods_tracker.common;

/**
 * Created by syle on 5/2/18.
 */

// src:
// https://github.com/xzchenglin/sjsu/blob/master/bcTracker/bcTracker-common/src/java/helper/Utils.java
// freaking work around the damn file reader using input stream from string...
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.apache.commons.codec.DecoderException;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.bouncycastle.util.io.pem.PemWriter;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CryptoUtil{
    public static String generateKeypair(String name) throws Exception{
        KeyPairGenerator keyGen = null;
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyGen.initialize(2048);
        KeyPair kp = keyGen.generateKeyPair();
        Key pub = kp.getPublic();
        Key pvt = kp.getPrivate();

        writePemFile(pvt, "RSA PRIVATE KEY", "/tmp/" + name + ".key");
        writePemFile(pub, "RSA PUBLIC KEY", "/tmp/" + name + ".pub");

        String pubB64 = Base64.getEncoder().encodeToString(pub.getEncoded());
        return pubB64;
    }


    public static String rsaEncWithPlainKey(String text, String keyContent) throws Exception{
        KeyFactory factory = KeyFactory.getInstance("RSA");
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, getPrivateKeyWithPlainKey(factory, keyContent));
        byte[] encrypted = cipher.doFinal(text.getBytes());
        String hex = printHexBinary(encrypted);
        return hex;
    }


    public static String rsaDecWithPlainKey(String text, String keyContent) throws Exception{
        KeyFactory factory = KeyFactory.getInstance("RSA");
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, getPublicKeyWithPlainKey(factory, keyContent));
        byte[] bt = cipher.doFinal(parseHexBinary(text));

        String ret = new String(bt);
        return ret;
    }


    public static String md5(String text){

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(text.getBytes());
        byte byteData[] = md.digest();

        String hexString = printHexBinary(byteData);
        return hexString.toString();
    }

    private static void writePemFile(Key key, String description, String filename)
            throws Exception {
        PemFile pemFile = new PemFile(key, description);
        pemFile.write(filename);
    }


    private static PrivateKey getPrivateKeyWithPlainKey(KeyFactory factory, String plainKey) throws Exception {
        PemFile pemFile = new PemFile(new ByteArrayInputStream( plainKey.getBytes() ));
        byte[] content = pemFile.getPemObject().getContent();
        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(content);
        return factory.generatePrivate(privKeySpec);
    }

    private static PublicKey getPublicKeyWithPlainKey(KeyFactory factory, String plainKey) throws Exception {
        PemFile pemFile = new PemFile(new ByteArrayInputStream( plainKey.getBytes() ));
        byte[] content = pemFile.getPemObject().getContent();
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(content);
        return factory.generatePublic(pubKeySpec);
    }

    //    https://stackoverflow.com/questions/34342136/alternatives-for-datatypeconverter-in-android?noredirect=1&lq=1
    private static byte[] parseHexBinary(String text){
        try {
            return org.apache.commons.codec.binary.Hex.decodeHex(text.toCharArray());
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static String printHexBinary (byte[] encrypted){
        return android.util.Base64.encodeToString(encrypted, 16);
    }


//    private static byte[] parseHexBinary2(String text){
//        return DatatypeConverter.parseHexBinary(text);
//    }
//
//    private static String printHexBinary2 (byte[] encrypted){
//        return DatatypeConverter.printHexBinary(encrypted);
//    }


// pem files
    private static class PemFile {

        private PemObject pemObject;

        public PemFile (Key key, String description) {
            this.pemObject = new PemObject(description, key.getEncoded());
        }

        public PemFile(InputStream inputStream){
//            https://stackoverflow.com/questions/247161/how-do-i-turn-a-string-into-a-inputstreamreader-in-java
//            InputStream is = new ByteArrayInputStream( myString.getBytes( charset ) );
            PemReader pemReader = new PemReader(new InputStreamReader(inputStream));
            try {
                this.pemObject = pemReader.readPemObject();
                pemReader.close();
                pemReader.readPemObject();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        public void write(String filename) throws Exception {
            PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            try {
                pemWriter.writeObject(this.pemObject);
            } finally {
                pemWriter.close();
            }
        }

        public PemObject getPemObject() {
            return pemObject;
        }
    }
}