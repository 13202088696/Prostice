package com.itbaizhan.shopping_common.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {

    public final static String md5key = "BAIZHAN"; // 秘钥

    public static String encode(String text){
        return DigestUtils.md5Hex(text+md5key);
    }

    public static boolean verify(String text,String cipher){
        String md5Text  = encode(text);
        if (md5Text.equalsIgnoreCase(cipher)){
            return true;
        }else {
            return false;
        }
    }

}
