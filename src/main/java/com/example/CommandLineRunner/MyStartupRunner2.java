package com.example.CommandLineRunner;

import com.example.mapper.BlockMapper;
import com.example.mapper.TranscationMapper;
import com.example.theadPoolConfig.AsyncTranscationTask;
import com.example.unitl.Until;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhaochao on 2017/7/18.
 */

/**
 * 服务启动后开始初始化交易信息
 */
//@Component
//@Order(value = 1)
public class MyStartupRunner2 implements CommandLineRunner {
    @Autowired
    private AsyncTranscationTask asyncTranscationTask;
    @Autowired
    private BlockMapper blockMapper;

    @Override
    public void run(String... strings) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载交易数据操作 <<<<<<<<<<<<<");
//        获取最新区块编号
        try {
            int blockNumberNew = Integer.parseInt(
                    Until.encodeHexTodecimal(Until.JSONRPCClient(new Object[]{},
                            "eth_blockNumber").toString()));
            System.out.println(">>>>>>>>>>>>>>>" + blockNumberNew + " <<<<<<<<<<<<<");
//          查询数据库中存储的最新区块数据
            int blockNumberDataSource = blockMapper.getBlockNum();
            for (int i = blockNumberDataSource + 1; i <= blockNumberNew; i++) {
                asyncTranscationTask.insertTransTask(i);
            }
            System.out.println(">>>>>>>>>>>>>>>" + blockNumberDataSource + " <<<<<<<<<<<<<");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
