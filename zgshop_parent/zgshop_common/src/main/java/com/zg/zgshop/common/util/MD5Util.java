package com.zg.zgshop.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 *
 * @Author: zg
 * @Date: 2019/3/21 14:14
 */
public class MD5Util {
    /***
     * MD5加密 生成32位md5码
     * @param 待加密字符串
     * @return 返回32位md5码
     */
    public static String md5Encode(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = inStr.getBytes(StandardCharsets.UTF_8);
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
        //卖家密码：981c57a5cfb0f868e064904b8745766f
        System.out.println(md5Encode("relles"));
        //买家密码：37254660e226ea65ce6f1efd54233424
        System.out.println(md5Encode("reyub"));
    }
}
