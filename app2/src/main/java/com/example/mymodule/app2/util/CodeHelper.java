package com.example.mymodule.app2.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class CodeHelper {
    //DES解密
    public static byte[] DesDecrypt(byte[] byteMi, byte[] byteKey)
            throws InvalidKeyException, InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        return CodeHelper.Des(byteMi, byteKey, Cipher.DECRYPT_MODE);
    }

    //DES加密
    public static byte[] DesEncrypt(byte[] byteMi, byte[] byteKey)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

        return CodeHelper.Des(byteMi, byteKey, Cipher.ENCRYPT_MODE);
    }

    //DES加解密
    private static byte[] Des(byte[] byteData, byte[] byteKey, int opmode)
            throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = null;
        try {
            SecretKeySpec desKey = new SecretKeySpec(byteKey, "DES");

            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(opmode, desKey);

            return cipher.doFinal(byteData);
        } finally {
            cipher = null;
        }
    }

    public static byte[] AesDecrypt(byte[] byteMi, byte[] byteKey) throws Exception {
        return CodeHelper.Aes(byteMi, byteKey, Cipher.DECRYPT_MODE);
    }

    public static byte[] AesEncrypt(byte[] byteMi, byte[] byteKey) throws Exception {
        return CodeHelper.Aes(byteMi, byteKey, Cipher.ENCRYPT_MODE);
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

    public static byte[] RsaDecrypt(byte[] byteMi, Key pKey) throws Exception {
        return CodeHelper.Rsa(byteMi, pKey, Cipher.DECRYPT_MODE);
    }

    public static byte[] RsaEncrypt(byte[] byteMi, Key pKey) throws Exception {
        return CodeHelper.Rsa(byteMi, pKey, Cipher.ENCRYPT_MODE);
    }

    private static byte[] Rsa(byte[] byteData, Key pKey, int opmode) throws Exception {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(opmode, pKey);

            return cipher.doFinal(byteData);
        } finally {
            cipher = null;
        }
    }

}
