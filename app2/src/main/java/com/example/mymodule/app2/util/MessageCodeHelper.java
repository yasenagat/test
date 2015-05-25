package com.example.mymodule.app2.util;

import java.security.PrivateKey;
import java.security.PublicKey;

public class MessageCodeHelper {

    private static final String TAG = MessageCodeHelper.class.toString();

    public static byte[] Base64Decode(String oriData) {
        return Base64.decode(oriData.replace(" ", ""));
    }

    public static String Base64Encode(byte[] oriData) {
        return Base64.encodeToString(oriData).replace("\r\n", "");
    }

    public static String DecodeBody(String encryptMode, String miData,
                                    String mingKey) throws Exception {
        if (encryptMode.equalsIgnoreCase("1")) {
            return DecodeMode1(miData);
        } else if (encryptMode.equalsIgnoreCase("2")) {
            return DecodeMode2(miData);
        } else if (encryptMode.equalsIgnoreCase("3")) {
            return DecodeMode3(miData, mingKey);
        } else if (encryptMode.equalsIgnoreCase("4")) {
            return DecodeMode4(miData, mingKey);
        }
        return "";
    }

    public static String EncodeBody(String encryptMode, String mingData,
                                    String mingKey) throws Exception {
        if (encryptMode.equalsIgnoreCase("1")) {
            return EncodeMode1(mingData);
        } else if (encryptMode.equalsIgnoreCase("2")) {
            return EncodeMode2(mingData);
        } else if (encryptMode.equalsIgnoreCase("3")) {
            return EncodeMode3(mingData, mingKey);
        } else if (encryptMode.equalsIgnoreCase("4")) {
            return EncodeMode4(mingData, mingKey);
        }

        return "";
    }

    public static String DecodeMode1(String oriData) throws Exception {
        String strMing = "";
        byte[] byteMing = null;
        try {
            System.out.println( "DecodeMode1:" + " ORI:" + oriData);

            byteMing = Base64Decode(oriData);

            strMing = new String(byteMing, "UTF8");

            System.out.println( "DecodeMode1:" + " Base64De:" + strMing);

        } finally {
            byteMing = null;
        }
        return strMing;
    }

    public static String DecodeMode2(String oriData) throws Exception {
        String strMing = "";
        byte[] byteMing = null;
        byte[] byteMi = null;
        try {
            System.out.println( "DecodeMode2:" + " ORI:" + oriData);

            byteMi = Base64Decode(oriData);

            System.out.println(
                    
                    "DecodeMode2:" + " Base64De:"
                            + StringTools.bytesToHexString(byteMi));

            byteMing = Zip.unjzlib(byteMi);

            strMing = new String(byteMing, "UTF8");

            System.out.println( "DecodeMode2:" + " UnZip:" + strMing);

        } finally {
            byteMing = null;
            byteMi = null;
        }
        return strMing;
    }

    public static String DecodeMode3(String oriData, String strKey)
            throws Exception {
        String strMing = "";
        byte[] byteMing = null;
        byte[] byteMi = null;
        byte[] byteKey = null;

        try {
            System.out.println( "DecodeMode3:" + " ORI:" + oriData);

            byteMi = Base64.decode(oriData);

            System.out.println(
                    
                    "DecodeMode3:" + " Base64De:"
                            + StringTools.bytesToHexString(byteMi));

            byteKey = StringTools.hexStringToByte(strKey);

            byteMing = CodeHelper.AesDecrypt(byteMi, byteKey);

            strMing = new String(byteMing, "UTF8");

            System.out.println(
                    
                    "DecodeMode3:" + " AesDecrypt:"
                            + StringTools.bytesToHexString(byteMing));
            System.out.println( "response body : " + strMing);
        } finally {
            byteMing = null;
            byteMi = null;
            byteKey = null;
        }
        return strMing;
    }

    public static String DecodeMode4(String oriData, String strKey)
            throws Exception {
        String strMing = "";
        byte[] byteMing = null;
        byte[] byteMi = null;
        byte[] byteKey = null;

        try {
            System.out.println( "DecodeMode4:" + " ORI:" + oriData);

            byteMi = Base64.decode(oriData);

            System.out.println(
                    
                    "DecodeMode4:" + " Base64De:"
                            + StringTools.bytesToHexString(byteMi));

            byteKey = StringTools.hexStringToByte(strKey);

            byteMing = CodeHelper.AesDecrypt(byteMi, byteKey);

            System.out.println(
                    
                    "DecodeMode4:" + " AesDecrypt:"
                            + StringTools.bytesToHexString(byteMing));

            byteMing = Zip.unjzlib(byteMing);

            strMing = new String(byteMing, "UTF8");

            System.out.println( "DecodeMode4:" + " UnZip:" + strMing);
        } finally {
            byteMing = null;
            byteMi = null;
            byteKey = null;
        }
        return strMing;
    }

    public static String EncodeMode1(String oriData) throws Exception {
        String strMi = "";
        byte[] byteMing = null;

        try {
            System.out.println( "EncodeMode1:" + " ORI:" + oriData);

            byteMing = oriData.getBytes("UTF8");

            strMi = Base64Encode(byteMing);

            System.out.println( "EncodeMode1:" + " Base64En:" + strMi);
        } finally {
            byteMing = null;
        }
        return strMi;
    }

    public static String EncodeMode2(String oriData) throws Exception {
        String strMi = "";
        byte[] byteMi = null;
        byte[] byteMing = null;

        try {
            System.out.println( "EncodeMode2:" + " ORI:" + oriData);

            byteMing = oriData.getBytes("UTF8");

            byteMi = Zip.jzlib(byteMing);

            System.out.println(
                    
                    "EncodeMode2:" + " Zip:"
                            + StringTools.bytesToHexString(byteMi));

            strMi = Base64Encode(byteMi);

            System.out.println( "EncodeMode2:" + " Base64En:" + strMi);
        } finally {
            byteMing = null;
            byteMi = null;
        }
        return strMi;
    }

    public static String EncodeMode3(String oriData, String strKey)
            throws Exception {
        String strMi = "";
        byte[] byteMing = null;
        byte[] byteMi = null;
        byte[] byteKey = null;

        try {
            System.out.println( "EncodeMode3:" + " ORI:" + oriData);

            byteMing = oriData.getBytes("UTF8");

            byteKey = StringTools.hexStringToByte(strKey);

            byteMi = CodeHelper.AesEncrypt(byteMing, byteKey);

            System.out.println(
                    
                    "EncodeMode3:" + " AesEncrypt:"
                            + StringTools.bytesToHexString(byteMi));

            strMi = Base64.encodeToString(byteMi);

            System.out.println( "EncodeMode3:" + " Base64En:" + strMi);
        } finally {
            byteMing = null;
            byteMi = null;
            byteKey = null;
        }
        return strMi;
    }

    public static String EncodeMode4(String oriData, String strKey)
            throws Exception {
        String strMi = "";
        byte[] byteMing = null;
        byte[] byteMi = null;
        byte[] byteKey = null;

        try {
            System.out.println( "EncodeMode4:" + " ORI:" + oriData);

            byteMing = oriData.getBytes("UTF8");

            byteMing = Zip.jzlib(byteMing);

            System.out.println(
                    
                    "EncodeMode4:" + " Zip:"
                            + StringTools.bytesToHexString(byteMing));

            byteKey = StringTools.hexStringToByte(strKey);

            byteMi = CodeHelper.AesEncrypt(byteMing, byteKey);

            System.out.println(
                    
                    "EncodeMode4:" + " AesEncrypt:"
                            + StringTools.bytesToHexString(byteMi));

            strMi = Base64.encodeToString(byteMi);

            System.out.println( "EncodeMode4:" + " Base64En:" + strMi);
        } finally {
            byteMing = null;
            byteMi = null;
            byteKey = null;
        }
        return strMi;
    }

    public static String DecodeKey(String oriData, PrivateKey pKey)
            throws Exception {
        String strMing = "";
        byte[] byteMing = null;
        byte[] byteMi = null;
        try {
            System.out.println( "DecodeKey:" + " ORI:" + oriData);

            byteMi = Base64Decode(oriData);

            System.out.println(
                    
                    "DecodeKey:" + " Base64De:"
                            + StringTools.bytesToHexString(byteMi));

            byteMing = CodeHelper.RsaDecrypt(byteMi, pKey);

            strMing = new String(byteMing, "UTF8");

            System.out.println( "DecodeKey:" + " RsaDecrypt:" + strMing);
        } finally {
            byteMing = null;
            byteMi = null;
        }
        return strMing;
    }

    public static String EncodeKey(String oriData, PublicKey pKey)
            throws Exception {
        String strMi = "";
        byte[] byteMing = null;
        byte[] byteMi = null;
        try {
            System.out.println( "EncodeKey:" + " ORI:" + oriData);

            byteMing = oriData.getBytes("UTF8");

            byteMi = CodeHelper.RsaEncrypt(byteMing, pKey);

            System.out.println(
                    
                    "EncodeKey:" + " RsaEncrypt:"
                            + StringTools.bytesToHexString(byteMi));

            strMi = Base64Encode(byteMi);

            System.out.println( "EncodeKey:" + " Base64En:" + strMi);
        } finally {
            byteMing = null;
            byteMi = null;
        }
        return strMi;
    }
}
