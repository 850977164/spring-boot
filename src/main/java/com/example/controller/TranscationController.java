package com.example.controller;

import com.example.entity.TranscationBean;
import com.example.mapper.TranscationMapper;
import com.example.unitl.Until;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wuhaochao on 2017/7/21.
 */

/**
 * 交易
 */
@RequestMapping("/transcation")
@RestController
public class TranscationController {
    @Autowired
    private TranscationMapper transcationMapper;

    /**
     * 获取全部交易信息
     *
     * @return
     * @throws Throwable
     */
    @RequestMapping("/getTransInfo")
    public Object getTranscationInfo() throws Throwable {
        List<TranscationBean> list = transcationMapper.getTranscation();
        return list;
    }

    /**
     * 分页获取交易信息
     *
     * @param pagesize
     * @return
     * @throws Throwable
     */
    @RequestMapping("/getTransInfo/{pagesize}")
    public Object getTranscationInfoByPage(@PathVariable("pagesize") Integer pagesize) throws Throwable {
        PageHelper.startPage(1, pagesize);
        List<TranscationBean> list = transcationMapper.getTranscation();
        return new PageInfo(list);
    }

    /**
     * 通过交易hash获取交易信息
     *
     * @param hash
     * @return
     */
    @RequestMapping("/getTransInfoByHash/{hash}")
    public Object getTranscationInfoByHash(@PathVariable("hash") String hash) {
        return transcationMapper.getTranscationInfoByHash(hash);
    }
}
