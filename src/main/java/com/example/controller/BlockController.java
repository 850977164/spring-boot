package com.example.controller;

/**
 * Created by wuhaochao on 2017/7/26.
 */

import com.example.entity.BlockInfoBean;
import com.example.mapper.BlockMapper;
import com.example.unitl.Until;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 区块
 */
@RequestMapping("/block")
@RestController
public class BlockController {
    @Autowired
    private BlockMapper blockMapper;

    /**
     * 获取最新区块数量
     *
     * @return
     * @throws Throwable
     */
    @RequestMapping("/getBlockNumber")
    public Object getBlockNumber() throws Throwable {
        return Until.encodeHexTodecimal(Until.JSONRPCClient(new Object[]{},
                "eth_blockNumber").toString());
    }

    /**
     * 获取区块详情
     *
     * @return
     */
    @RequestMapping("/getBlockInfo/{blockNumber}")
    public Object getBlockInfo(@PathVariable("blockNumber") Integer blockNumber) throws Throwable {
        JSONObject objects = JSONObject.fromObject(Until
                .JSONRPCClient(
                        new Object[]{blockNumber, true},
                        "eth_getBlockByNumber"));

        return new BlockInfoBean(objects);
    }

    /**
     * 分页获取区块信息
     *
     * @param pagesize
     * @return
     */
    @RequestMapping("/getBlockInfoByPage/{pagesize}")
    public Object getBlockInfoByPage(@PathVariable("pagesize") Integer pagesize) {
        PageHelper.startPage(1, pagesize);
        List<BlockInfoBean> list = blockMapper.getBlockInfo();
        return new PageInfo(list);
    }

}
