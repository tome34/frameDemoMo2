package com.example.tome.core.util;

import android.support.annotation.NonNull;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author liubp
 * @function 编码解码相关
 */
public class EncodeUtils {

    /**
     * 排序
     * @param params
     * @return
     */
    public static String getSign(HashMap<String, String> params) {

        L.i("11111111111111111", params.toString());
        // 先将参数以其参数名的字典序升序进行排序
        Map<String, String> sortedParams = new TreeMap<String, String>(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();


        // 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
        StringBuilder basestring = new StringBuilder();
        for (Map.Entry<String, String> param : entrys) {
            basestring.append(param.getKey()).append("=").append(chinaToUnicode(param.getValue()));
        }
        L.i("11111111111111111", basestring.toString());
        return getMD5String(basestring.toString());
    }

    @NonNull
    public static String getMD5String(String basestring) {
        /*****************对排序后的参数进行MD5散列函数运算***********************/
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(basestring.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        /*****************对排序后的参数进行MD5散列函数运算***********************/
        //返回md5加密后的字符串注意统一转化为大写
        return hex.toString().toUpperCase();
    }

    /**
     * 中文转Unicode编码
     *
     * @param str 字符串
     * @return 如果是中文则返回对应的Unicode编码字符串
     */
    private static String chinaToUnicode(String str) {
        String result = "";
        String regEx = "[^\\x00-\\xff]";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        boolean rs = matcher.matches();
        for (int i = 0; i < str.length(); i++) {
            int chr1 = str.charAt(i);
            if (rs) {
                result += "\\u" + Integer.toHexString(chr1);
            } else {
                result += str.charAt(i);
            }
        }
        return result;
    }

}
