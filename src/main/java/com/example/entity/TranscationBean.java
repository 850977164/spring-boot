package com.example.entity;

/**
 * Created by wuhaochao on 2017/7/21.
 */

import com.example.unitl.Until;
import net.sf.json.JSONObject;

import java.io.Serializable;

/**
 * 交易实体
 */
public class TranscationBean implements Serializable {
    private static final long serialVersionUID = 2120869894112984147L;
    private int id;
    private String blockHash;
    private String blockNumber;
    private String from;
    private String gas;
    private String gasPrice;
    private String hash;
    private String input;
    private String nonce;
    private String to;
    private String transactionIndex;
    private String value;
    private String timestamp;

    public TranscationBean() {

    }

    public TranscationBean(int id, String blockHash, String blockNumber, String from, String gas, String gasPrice, String hash, String input, String nonce, String to, String transactionIndex, String value, String timestamp) {
        this.id = id;
        this.blockHash = blockHash;
        this.blockNumber = blockNumber;
        this.from = from;
        this.gas = gas;
        this.gasPrice = gasPrice;
        this.hash = hash;
        this.input = input;
        this.nonce = nonce;
        this.to = to;
        this.transactionIndex = transactionIndex;
        this.value = value;
        this.timestamp = timestamp;
    }

    public TranscationBean(JSONObject obj) {
        this.blockHash = obj.getString("blockHash");
        this.blockNumber = Until.encodeHexTodecimal(obj.getString("blockNumber"));
        this.from = obj.getString("from");
        this.gas = Until.encodeHexTodecimal(obj.getString("gas"));
        this.gasPrice = Until.encodeHexTodecimal(obj.getString("gasPrice"));
        this.hash = obj.getString("hash");
        this.input = obj.getString("input");
        this.nonce = Until.encodeHexTodecimal(obj.getString("nonce"));
        this.to = obj.getString("to");
        this.transactionIndex = Until.encodeHexTodecimal(obj.getString("transactionIndex"));
        this.value = Until.encodeHexTodecimal(obj.getString("value"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getFrom() {
        return from;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getGas() {
        return gas;
    }

    public void setGas(String gas) {
        this.gas = gas;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTransactionIndex() {
        return transactionIndex;
    }

    public void setTransactionIndex(String transactionIndex) {
        this.transactionIndex = transactionIndex;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
