package com.example.controller;

/**
 * Created by wuhaochao on 2017/7/25.
 */

import com.example.service.ReadJSONFileService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * menu菜单解析 json
 */
@RequestMapping("/menu")
@RestController
public class MenuController {
    @Autowired
    private ReadJSONFileService readJSON;

    @RequestMapping("/getMenuInfo")
    public Object getMenuInfo() {
        return readJSON.getData();
    }
}
