package com.example.mymodule.app2.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by zf on 2015/4/1.
 */
public class BBB {

    public static byte[] AesDecrypt(byte[] byteMi, byte[] byteKey) throws Exception {
        return Aes(byteMi, byteKey, Cipher.DECRYPT_MODE);
    }

    public static byte[] AesEncrypt(byte[] byteMi, byte[] byteKey) throws Exception {
        return Aes(byteMi, byteKey, Cipher.ENCRYPT_MODE);
    }

    private static byte[] Aes(byte[] byteData, byte[] byteKey, int opmode) throws Exception {
        Cipher cipher = null;
        try {
            SecretKeySpec aesKey = new SecretKeySpec(byteKey, "AES");
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(opmode, aesKey);

            return cipher.doFinal(byteData);
        } finally {
            cipher = null;
        }
    }
}
