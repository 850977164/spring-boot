package com.example.Websocket;

import com.example.entity.BlockInfoBean;
import com.example.entity.TranscationBean;
import com.example.mapper.BlockMapper;
import com.example.mapper.TranscationMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuhaochao on 2017/8/2.
 */
@EnableScheduling
@RestController
public class MyWebSocketMessage {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private BlockMapper blockMapper;
    @Autowired
    private TranscationMapper transcationMapper;

    @Scheduled(fixedRate = 10000)
    @SendTo("/topic/callback")
    public void sendback() {
        PageHelper.startPage(1, 5);
        List<TranscationBean> translist = transcationMapper.getTranscation();
        PageHelper.startPage(1, 5);
        List<BlockInfoBean> blocklist = blockMapper.getBlockInfo();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("translist", new PageInfo(translist).getList());
        map.put("blocklist", new PageInfo(blocklist).getList());
        messagingTemplate.convertAndSend("/topic/callback", map);
    }
}

