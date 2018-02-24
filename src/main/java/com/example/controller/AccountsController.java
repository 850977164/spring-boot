package com.example.controller;

/**
 * Created by wuhaochao on 2017/8/2.
 */

import com.example.mapper.TranscationMapper;
import com.example.unitl.Until;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 账户信息
 */
@RequestMapping("/accounts")
@RestController
public class AccountsController {
    @Autowired
    private TranscationMapper transcationMapper;

    /**
     * 获取账户资金
     *
     * @param address
     * @return
     * @throws Throwable
     */
    @RequestMapping("/getbalance/{address}")
    public Object getBalance(@PathVariable("address") String address) throws Throwable {
        return Until.encodeHexTodecimal(Until.JSONRPCClient(new Object[]{address, ""},
                "eth_getBalance").toString());
    }

    /**
     * 获取存在账户列表
     *
     * @return
     * @throws Throwable
     */
    @RequestMapping("/getAccounts")
    public Object getAccounts() throws Throwable {
        return Until.JSONRPCClient(new Object[]{},
                "eth_accounts");
    }

    /**
     * 获取账户发起交易数量及交易列表
     *
     * @param address
     * @return
     * @throws Throwable
     */
    @RequestMapping("/getTransactionCount/{address}")
    public Object getTransactionCount(@PathVariable("address") String address) throws Throwable {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("address", address);
        map.put("list", transcationMapper.getTranscationInfoByfrom(address));
        map.put("count", Until.encodeHexTodecimal(Until.JSONRPCClient(new Object[]{address, ""},
                "eth_getTransactionCount").toString()));
        return map;
    }
}
