package com.example.mapper;

import com.example.entity.BlockInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wuhaochao on 2017/7/26.
 */
@Mapper
public interface BlockMapper {
    //获取数据库中最新的区块编号
    int getBlockNum();

    //获取区块信息
    List<BlockInfoBean> getBlockInfo();

    //插入单个区块信息
    int insertBlockInfo(@Param("blockinfoBean") BlockInfoBean blockinfoBean);

    //批量插入区块信息
    int insertBlockInfoList(@Param("blockinfoBeanlist") List<BlockInfoBean> blockbeanList);
}
