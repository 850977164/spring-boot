pragma solidity ^0.4.0;

//代币合约
contract CoinContract {
    address issuer;
    uint coinid;
    mapping(address => userBean) public userInfos;//用户信息
    mapping(uint => coinBean) public coins;//代币信息
    event Transfer(address from, address to, uint amount);
    //分配映射空间
    userBean u;

    //用户
    struct userBean{
        address account;//用户地址
        mapping(uint => uint) account_balance;//代币余额
    }
    //代币
    struct coinBean{
        address account;//代币发行方
        bytes32 coinNam;//代币名称
        uint coinid;//代币ID
        uint balance;//代币总额
    }
    function CoinContract(){
        issuer=msg.sender;
    }
    //新增代币
    function newcoin(address createaccount,bytes32 name,uint amount) constant returns (uint){
        coinid += coinid;
        coins[coinid]=coinBean(createaccount,name,coinid,amount);
    }
    //充值
    function issue(address account,uint amount,bytes32 coinCod){
        userInfos[account].account_balance[coinCod]+=amount;
    }
    //返回账户详情
    function userinfo(address account) constant returns (address,uint,uint){
        return userInfos[account];
    }
    //交易
    function trans(address to, uint amount,bytes32 coinCod) {
        if (userInfos[msg.sender].account_balance[coinCod] < amount) throw;
        userInfos[msg.sender].account_balance[coinCod] -= amount;
        userInfos[to].account_balance[coinCod] += amount;
        Transfer(msg.sender, to, amount);
    }
    //查询
    function getBalance(address account) constant returns (mapping(bytes32 => uint)) {
        return userInfos[account].account_balance;
    }
}
