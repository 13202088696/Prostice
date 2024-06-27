package com.itbaizhan.shopping_common.util;

import java.util.Random;

public class RandomUtil {

    public static String buildCheckCode(int digit){
        String str = "0987654321";
        str.charAt(digit);
        StringBuffer stringBuffer = new StringBuffer(digit);
        Random random = new Random();
        for (int i = 0; i < digit; i++) {
            char ch = str.charAt(random.nextInt(str.length()));
            stringBuffer.append(ch);
        }
        return stringBuffer.toString();
    }

}
