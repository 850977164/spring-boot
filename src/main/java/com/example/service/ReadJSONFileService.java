package com.example.service;

/**
 * Created by wuhaochao on 2017/7/25.
 */

import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;


/**
 * 读取json文件
 */
@Service
public class ReadJSONFileService {
    public JSONObject getData() {
        File file = new File(ReadJSONFileService.class
                .getClassLoader()
                .getResource("menu.json")
                .getPath());
        InputStream reader = null;
        StringBuffer jsonstr = new StringBuffer();
        try {
            reader = new FileInputStream(file);
            byte[] b = new byte[1024];
            int length = 0;
            while ((length = reader.read(b)) != -1) {
                //以前在这出现乱码问题，后来在这设置了编码格式
                jsonstr.append(new String(b, 0, length, "UTF-8"));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return JSONObject.fromObject(jsonstr.toString());
    }
}
