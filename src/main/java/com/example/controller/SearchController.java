package com.example.controller;

import com.example.entity.BlockInfoBean;
import com.example.entity.TranscationBean;
import com.example.unitl.Until;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wuhaochao on 2017/8/1.
 */
@RequestMapping("/search")
@RestController
public class SearchController {
    /**
     * 根据前台传递参数type判断是查询区块信息还是交易信息
     *
     * @param searchStr 查询的参数
     * @param type      查询的类型
     * @return
     */
    @RequestMapping("/searchInfo")
    public Object searchByType(@RequestParam("searchStr") String searchStr,
                               @RequestParam("type") String type) throws Throwable {
        if ("blockNum".equals(type)) {//根据区块编号查询区块信息
            JSONObject objects = JSONObject.fromObject(Until
                    .JSONRPCClient(
                            new Object[]{Integer.parseInt(searchStr), true},
                            "eth_getBlockByNumber"));
            return new BlockInfoBean(objects);
        }
        if ("transcationinfo".equals(type)) {//根据交易hash值查询交易信息
            JSONObject objects = JSONObject.fromObject(Until
                    .JSONRPCClient(
                            new Object[]{searchStr},
                            "eth_getTransactionByHash"));
            return new TranscationBean(objects);
        }
        return new Object();
    }
}
