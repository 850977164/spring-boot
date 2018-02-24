package com.example.theadPoolConfig;

import com.example.entity.BlockInfoBean;
import com.example.entity.TranscationBean;
import com.example.mapper.BlockMapper;
import com.example.mapper.TranscationMapper;
import com.example.unitl.Until;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuhaochao on 2017/7/25.
 */
@Component
public class AsyncTranscationTask {
    private static final Logger logger = LoggerFactory.getLogger(AsyncTranscationTask.class);
    @Autowired
    private TranscationMapper transcationMapper;
    @Autowired
    private BlockMapper blockMapper;

    //myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    @Async("myTaskAsyncPool")
    public void insertTransTask(int i) throws InterruptedException {
        logger.info("TranscationTask" + i + " started.");
        //开始导入交易信息
        String method_BlockByNumber = "eth_getBlockByNumber";
        JSONObject objects = null;
        List<TranscationBean> list = null;
        JSONObject transtionObject = null;
        TranscationBean transcation = null;
        try {
            objects = JSONObject.fromObject(Until.JSONRPCClient(new Object[]{i, true}, method_BlockByNumber));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        //插入区块信息
        BlockInfoBean blockInfoBean = new BlockInfoBean(objects);
        blockMapper.insertBlockInfo(blockInfoBean);
        if (JSONArray
                .fromObject(objects.get("transactions")).size() > 0) {
            list = new ArrayList<TranscationBean>();
            for (java.util.Iterator tor = JSONArray
                    .fromObject(objects.get("transactions"))
                    .iterator(); tor.hasNext(); ) {
                transtionObject = (JSONObject) tor.next();
                transcation = new TranscationBean(transtionObject);
                transcation.setTimestamp(Until.formatTimeMillis(objects.get("timestamp").toString()));
                list.add(transcation);
            }
            //批量插入
            transcationMapper.insertTranscationList(list);
        }
    }

}
