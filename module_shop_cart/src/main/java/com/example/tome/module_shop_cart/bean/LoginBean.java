package com.example.tome.module_shop_cart.bean;

import java.util.List;

/**
 * 登录
 * Created by Administrator on 2017/12/12.
 */

public class LoginBean extends ObjectBeans {
    /**
     * customterType : 0
     * message : 登陆功
     * hxUserName : user_77_93_1_0
     * token :
     * state : 0
     * hxPassword : 888888
     * businessType : 1
     * uuid :
     * isKeFu : 0
     * suMap : {"uid":"77","busiCompName":"深圳市筷云股份有限公司","busiCompType":"0","state":"1","lastTime":"2017-12-12 15:23:14.0","pid":"0","aid":"93","busiCompLevel":"0","defaultUid":"77","busiCompNick":"chaichai","topBid":"57","userTel":"15820400825","account":"13800138000","userName":"13800138000","brid":"","bid":"57","defaultAid":"93"}
     */

    private String customterType;
    private String hxUserName;
    private String token;
    private String hxPassword;
    private String businessType;
    private String uuid;
    private String isKeFu;
    private SuMapBean suMap;

    public String getCustomterType() {
        return customterType;
    }

    public void setCustomterType(String customterType) {
        this.customterType = customterType;
    }


    public String getHxUserName() {
        return hxUserName;
    }

    public void setHxUserName(String hxUserName) {
        this.hxUserName = hxUserName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public String getHxPassword() {
        return hxPassword;
    }

    public void setHxPassword(String hxPassword) {
        this.hxPassword = hxPassword;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getIsKeFu() {
        return isKeFu;
    }

    public void setIsKeFu(String isKeFu) {
        this.isKeFu = isKeFu;
    }

    public SuMapBean getSuMap() {
        return suMap;
    }

    public void setSuMap(SuMapBean suMap) {
        this.suMap = suMap;
    }

    public static class SuMapBean {
        /**
         * uid : 77
         * busiCompName : 深圳市筷云股份有限公司
         * busiCompType : 0
         * state : 1
         * lastTime : 2017-12-12 15:23:14.0
         * pid : 0
         * aid : 93
         * busiCompLevel : 0
         * defaultUid : 77
         * busiCompNick : chaichai
         * topBid : 57
         * userTel : 15820400825
         * account : 13800138000
         * userName : 13800138000
         * brid :
         * bid : 57
         * defaultAid : 93
         */

        private String uid;
        private String busiCompName;
        private String busiCompType;
        private String state;
        private String lastTime;
        private String pid;
        private String aid;
        private String busiCompLevel;
        private String defaultUid;
        private String busiCompNick;
        private String topBid;
        private String userTel;
        private String account;
        private String userName;
        private String brid;
        private String bid;
        private String  mid;
        private String defaultAid;
        private String bmRealmobile;
        private String bmRealName;

        public String getBmRealmobile() {
            return bmRealmobile;
        }

        public void setBmRealmobile(String bmRealmobile) {
            this.bmRealmobile = bmRealmobile;
        }

        public String getBmRealName() {
            return bmRealName;
        }

        public void setBmRealName(String bmRealName) {
            this.bmRealName = bmRealName;
        }

        public String getMid() {
            return mid;
        }

        public void setMid(String mid) {
            this.mid = mid;
        }

        public List<String> getPurviewResuorceList() {
            return purviewResuorceList;
        }

        public void setPurviewResuorceList(List<String> purviewResuorceList) {
            this.purviewResuorceList = purviewResuorceList;
        }

        private List<String> purviewResuorceList;

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getBusiCompName() {
            return busiCompName;
        }

        public void setBusiCompName(String busiCompName) {
            this.busiCompName = busiCompName;
        }

        public String getBusiCompType() {
            return busiCompType;
        }

        public void setBusiCompType(String busiCompType) {
            this.busiCompType = busiCompType;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getLastTime() {
            return lastTime;
        }

        public void setLastTime(String lastTime) {
            this.lastTime = lastTime;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getBusiCompLevel() {
            return busiCompLevel;
        }

        public void setBusiCompLevel(String busiCompLevel) {
            this.busiCompLevel = busiCompLevel;
        }

        public String getDefaultUid() {
            return defaultUid;
        }

        public void setDefaultUid(String defaultUid) {
            this.defaultUid = defaultUid;
        }

        public String getBusiCompNick() {
            return busiCompNick;
        }

        public void setBusiCompNick(String busiCompNick) {
            this.busiCompNick = busiCompNick;
        }

        public String getTopBid() {
            return topBid;
        }

        public void setTopBid(String topBid) {
            this.topBid = topBid;
        }

        public String getUserTel() {
            return userTel;
        }

        public void setUserTel(String userTel) {
            this.userTel = userTel;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getBrid() {
            return brid;
        }

        public void setBrid(String brid) {
            this.brid = brid;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getDefaultAid() {
            return defaultAid;
        }

        public void setDefaultAid(String defaultAid) {
            this.defaultAid = defaultAid;
        }
    }
}
