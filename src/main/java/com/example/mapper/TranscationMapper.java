package com.example.mapper;

import com.example.entity.TranscationBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wuhaochao on 2017/7/24.
 */
@Mapper
public interface TranscationMapper {
    //    获取交易信息
    List<TranscationBean> getTranscation();

    //根据hash值获取交易信息
    TranscationBean getTranscationInfoByHash(@Param("hash") String hash);

    //根据账户信息获取交易信息
    List<TranscationBean> getTranscationInfoByfrom(@Param("from") String from);

    //    插入交易信息
    int insertTranscationBean(@Param("transcationBean") TranscationBean transcationBean);

    //    批量插入交易信息
    int insertTranscationList(@Param("transcationList") List<TranscationBean> transcationList);
}
