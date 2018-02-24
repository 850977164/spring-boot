package com.example.Scheduled;

/**
 * Created by wuhaochao on 2017/7/21.
 */

import com.example.mapper.BlockMapper;
import com.example.mapper.TranscationMapper;
import com.example.theadPoolConfig.AsyncTranscationTask;
import com.example.unitl.Until;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 定时任务,每五分钟探测一次区块链中的是否存在最新交易信息
 */
@Component
public class MyScheduled {
    private static final Logger logger = LoggerFactory.getLogger(MyScheduled.class);
    @Autowired
    private AsyncTranscationTask asyncTranscationTask;
    @Autowired
    private BlockMapper blockMapper;

    /*每天每5秒执行一次*/
//    @Scheduled(cron = "0/5 * * * * ?")
    @Transactional
    public void executeTask2() {
        //        获取最新区块编号
        try {
            int blockNumberNew = Integer.parseInt(
                    Until.encodeHexTodecimal(Until.JSONRPCClient(new Object[]{},
                            "eth_blockNumber").toString()));
//          查询数据库中存储的最新区块数据
            int blockNumberDataSource = blockMapper.getBlockNum();
            for (int i = blockNumberDataSource + 1; i <= blockNumberNew; i++) {
                asyncTranscationTask.insertTransTask(i);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
