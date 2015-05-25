package com.example.mymodule.app2.util;

import com.jcraft.jzlib.JZlib;
import com.jcraft.jzlib.ZInputStream;
import com.jcraft.jzlib.ZOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@SuppressWarnings("deprecation")
public class Zip {
    /**
     * 压缩数据
     *
     * @param object
     * @return
     * @throws java.io.IOException
     */
    public static byte[] jzlib(byte[] object) {

        byte[] data = null;
        ByteArrayOutputStream out = null;
        ZOutputStream zOut = null;
        DataOutputStream objOut = null;

        try {
            out = new ByteArrayOutputStream();
            zOut = new ZOutputStream(out, JZlib.Z_DEFAULT_COMPRESSION);
            objOut = new DataOutputStream(zOut);
            objOut.write(object);
            objOut.flush();
            zOut.close();
            data = out.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
            data = null;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return data;
    }

    /**
     * 解压被压缩的数据
     *
     * @param object
     * @return
     * @throws java.io.IOException
     */
    public static byte[] unjzlib(byte[] object) {

        byte[] data = null;
        ByteArrayInputStream in = null;
        ZInputStream zIn = null;
        ByteArrayOutputStream baos = null;
        try {
            in = new ByteArrayInputStream(object);
            zIn = new ZInputStream(in);
            byte[] buf = new byte[1024];
            int num = -1;
            baos = new ByteArrayOutputStream();
            while ((num = zIn.read(buf, 0, buf.length)) != -1) {
                baos.write(buf, 0, num);
            }
            data = baos.toByteArray();
            baos.flush();
            // baos.close();
            // zIn.close();
            // in.close();

        } catch (IOException e) {
            e.printStackTrace();
            data = null;
        } finally {
            if (zIn != null) {
                try {
                    zIn.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                }
            }
        }
        return data;
    }

}
