package com.example.entity;

import com.example.unitl.Until;
import net.sf.json.JSONObject;

import java.io.Serializable;

/**
 * Created by wuhaochao on 2017/7/26.
 */

/**
 * 区块详情
 */
public class BlockInfoBean implements Serializable {
    private static final long serialVersionUID = 2120869894112984147L;
    private String difficulty;//设置当前区块的难度，如果难度过大，cpu挖矿就很难，这里设置较小难度
    private String extraData;//附加信息，随便填，可以填你的个性信息
    private String gasLimit;//该值设置对GAS的消耗总量限制
    private String gasUsed;
    private String hash;//哈希值
    private String logsBloom;
    private String miner;
    //	private String mixHash;//与nonce配合用于挖矿，由上一个区块的一部分生成的hash
    private String nonce;//nonce就是一个64位随机数，用于挖矿
    private String number;//块号
    private String parentHash;//上一个区块的hash值
    //	private String receiptsRoot;
    private String sha3Uncles;
    private String size;
    private String stateRoot;
    private String timestamp;//时间戳
    private String totalDifficulty;
    private String transactions;
    private String transactionsRoot;
    private String uncles;

    public BlockInfoBean(String difficulty, String extraData, String gasLimit, String gasUsed, String hash, String logsBloom, String miner, String nonce, String number, String parentHash, String sha3Uncles, String size, String stateRoot, String timestamp, String totalDifficulty, String transactions, String transactionsRoot, String uncles) {
        this.difficulty = difficulty;
        this.extraData = extraData;
        this.gasLimit = gasLimit;
        this.gasUsed = gasUsed;
        this.hash = hash;
        this.logsBloom = logsBloom;
        this.miner = miner;
        this.nonce = nonce;
        this.number = number;
        this.parentHash = parentHash;
        this.sha3Uncles = sha3Uncles;
        this.size = size;
        this.stateRoot = stateRoot;
        this.timestamp = timestamp;
        this.totalDifficulty = totalDifficulty;
        this.transactions = transactions;
        this.transactionsRoot = transactionsRoot;
        this.uncles = uncles;
    }

    public BlockInfoBean(JSONObject obj) {
        this.difficulty = Until.encodeHexTodecimal(obj.getString("difficulty"));
        this.extraData = obj.getString("extraData");
        this.gasLimit = Until.encodeHexTodecimal(obj.getString("gasLimit"));
        this.gasUsed = Until.encodeHexTodecimal(obj.getString("gasUsed"));
        this.hash = obj.getString("hash");
        this.logsBloom = obj.getString("logsBloom");
        this.miner = obj.getString("miner");
//		this.mixHash=obj.getString("mixHash");
        this.nonce = obj.getString("nonce");
        this.number = Until.encodeHexTodecimal(obj.getString("number"));
        this.parentHash = obj.getString("parentHash");
//		this.receiptsRoot=obj.getString("receiptsRoot");
        this.sha3Uncles = obj.getString("sha3Uncles");
        this.size = Until.encodeHexTodecimal(obj.getString("size"));
        this.stateRoot = obj.getString("stateRoot");
        this.timestamp = Until.formatTimeMillis(obj.getString("timestamp"));
        this.totalDifficulty = Until.encodeHexTodecimal(obj.getString("totalDifficulty"));
        this.transactions = obj.get("transactions").toString();
        this.transactionsRoot = obj.getString("transactionsRoot");
        this.uncles = obj.get("uncles").toString();
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public String getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(String gasLimit) {
        this.gasLimit = gasLimit;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(String gasUsed) {
        this.gasUsed = gasUsed;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getLogsBloom() {
        return logsBloom;
    }

    public void setLogsBloom(String logsBloom) {
        this.logsBloom = logsBloom;
    }

    public String getMiner() {
        return miner;
    }

    public void setMiner(String miner) {
        this.miner = miner;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getParentHash() {
        return parentHash;
    }

    public void setParentHash(String parentHash) {
        this.parentHash = parentHash;
    }

    public String getSha3Uncles() {
        return sha3Uncles;
    }

    public void setSha3Uncles(String sha3Uncles) {
        this.sha3Uncles = sha3Uncles;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStateRoot() {
        return stateRoot;
    }

    public void setStateRoot(String stateRoot) {
        this.stateRoot = stateRoot;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTotalDifficulty() {
        return totalDifficulty;
    }

    public void setTotalDifficulty(String totalDifficulty) {
        this.totalDifficulty = totalDifficulty;
    }

    public Object getTransactions() {
        return transactions;
    }

    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }

    public String getTransactionsRoot() {
        return transactionsRoot;
    }

    public void setTransactionsRoot(String transactionsRoot) {
        this.transactionsRoot = transactionsRoot;
    }

    public Object getUncles() {
        return uncles;
    }

    public void setUncles(String uncles) {
        this.uncles = uncles;
    }
}
