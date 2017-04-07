package com.khoubyari;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created with IntelliJ IDEA.
 * User: liji
 * Date: 17/3/28
 * Time: 上午10:16
 * To change this template use File | Settings | File Templates.
 */
public class Test {

    public static void main(String[] args) {
        byte[] encodedBytes = Base64.encodeBase64("1098987876112312312".getBytes());
        System.out.println("encodedBytes " + new String(encodedBytes));
        byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
        System.out.println("decodedBytes " + new String(decodedBytes));

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
