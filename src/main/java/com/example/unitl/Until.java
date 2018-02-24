package com.example.unitl;

import java.math.BigInteger;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author wuhaochao
 *         <p>
 *         Mar 15, 2017 2:03:11 PM
 */
public class Until {
    //    public static String URL = "http://10.132.97.29:8545";
    public static String URL = "http://127.0.0.1:8545";
    public static String DATE_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

    /**
     * 十六进制转十进制 整型
     *
     * @param encodeHex 十六进制字符串
     * @return 整型十进制字符串
     */
    public static String encodeHexTodecimal(String encodeHex) {
        String hex = encodeHex.substring(2);
        BigInteger bigInteger = new BigInteger(hex, 16);
        return bigInteger.toString();
    }

    /**
     * 转换为十六进制
     *
     * @param number 整型数字
     * @param uint   字节数
     * @return xxx
     */
    public static String decimalToencodeHex(int number, int uint) {
        String encodeHex = Integer.toHexString(number);
        StringBuffer b = new StringBuffer(encodeHex);
        //1个字节=2个16进制字符，不够字节填充0
        while (b.length() < uint * 2) {
            b.insert(0, "0");
        }
        return b.toString();
    }

    /**
     * 转换为十六进制字符串
     *
     * @param s
     * @return
     */
    public static String stringToHexString(String s) {
        String str = "0x";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

    /**
     * 16进制字符串转换为字符串
     *
     * @param hexstring
     * @return
     */
    public static String hexStringToString(String hexstring) {
        if (hexstring == null || hexstring.equals("")) {
            return null;
        }
        hexstring = hexstring.replace(" ", "");
        byte[] baKeyword = new byte[hexstring.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(
                        hexstring.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            hexstring = new String(baKeyword);
            new String();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return hexstring;
    }

    /**
     * 格式化时间
     *
     * @param timestamp 十六进制字符串
     * @return
     */
    public static String formatTimeMillis(String timestamp) {
        String times = encodeHexTodecimal(timestamp);
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_STRING);
        Date date = new Date(Long.parseLong(times) * 1000);
        return df.format(date);
    }

    /**
     * 调用JSON RPC
     *
     * @param params     参数
     * @param methodName 方法名
     * @return
     * @throws Throwable
     */
    public static Object JSONRPCClient(Object[] params, String methodName) throws Throwable {
        JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(URL));
        Object result = client.invoke(methodName, params, Object.class);
        return result;
    }

    /**
     * java对象转JSON字符串
     *
     * @param obj
     * @return
     */
    public static String ObjectToJSON(Object obj) {
        System.out.println(obj.getClass().getName());
        if (obj.getClass().getName().contains("ArrayList")) {
            JSONArray objects = JSONArray.fromObject(obj);
            return objects.toString();
        }
        JSONObject object = JSONObject.fromObject(obj);
        return object.toString();
    }

    public static void main(String[] args) {
        List list = new ArrayList<>();
        ObjectToJSON(list);
    }
}
