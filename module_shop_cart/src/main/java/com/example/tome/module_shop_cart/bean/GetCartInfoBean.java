package com.example.tome.module_shop_cart.bean;

import java.util.List;

/**
 * 购物车列表
 * Created by Administrator on 2017/12/24.
 */

public class GetCartInfoBean extends ObjectBeans {

    /**
     * total_amount : 8835.00
     * productCount : 7
     * productList : [{"cartId":"100","full_path":"http://img.d2shop.com/57/pimg/20171220/21/pimg0_500_500.jpg","brandName":"","brandId":"1","startQty":1,"unitValue":"个","subBid":"57","id":"21","scPrice":"909.0000","className":"金立","commission":"909.00","bid":"57","productTime":"2017-12-20 16:58:03","postTime":"2017-12-20 16:58:03","marketPrice":"909.0000","classId":"153","productCode":"sdf45456","truePrice":"909.00","property":["高亮"],"subProductId":"21","shortDesc":"","packId":"21","item_amount":"909.00","pfPrice":"909.00","total_stock":0,"itemCount":1,"productName":"是地方撒地方","unitId":1},{"cartId":"87","full_path":"http://img.d2shop.com/57/pimg/20171224/26/pimg0_500_500.jpg","brandName":"","brandId":"2","startQty":1,"unitValue":"件","subBid":"57","id":"26","scPrice":"1321.0000","className":"金立","commission":"1321.00","bid":"57","productTime":"2017-12-24 16:38:44","postTime":"2017-12-24 16:38:44","marketPrice":"1321.0000","classId":"153","productCode":"1321","truePrice":"1321.00","property":["5英寸","高亮","1T"],"subProductId":"26","shortDesc":"","packId":"26","item_amount":"7926.00","pfPrice":"1321.00","total_stock":0,"itemCount":6,"productName":"诺基亚","unitId":4}]
     * classSum : 2
     * proPriceList : [{"proId":"21","itemAmount":"909.00","truePrice":"909.00","cartId":"100","fullCutPrice":0},{"proId":"26","itemAmount":"7926.00","truePrice":"1321.00","cartId":"87","fullCutPrice":0}]
     */

    private String total_amount;
    private int productCount;
    private int classSum;
    private List<ProductListBean> productList;
    private List<ProPriceListBean> proPriceList;

    public String getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(String total_amount) {
        this.total_amount = total_amount;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public int getClassSum() {
        return classSum;
    }

    public void setClassSum(int classSum) {
        this.classSum = classSum;
    }

    public List<ProductListBean> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductListBean> productList) {
        this.productList = productList;
    }

    public List<ProPriceListBean> getProPriceList() {
        return proPriceList;
    }

    public void setProPriceList(List<ProPriceListBean> proPriceList) {
        this.proPriceList = proPriceList;
    }

    public static class ProductListBean {
        /**
         * cartId : 100
         * full_path : http://img.d2shop.com/57/pimg/20171220/21/pimg0_500_500.jpg
         * brandName :
         * brandId : 1
         * startQty : 1
         * unitValue : 个
         * subBid : 57
         * id : 21
         * scPrice : 909.0000
         * className : 金立
         * commission : 909.00
         * bid : 57
         * productTime : 2017-12-20 16:58:03
         * postTime : 2017-12-20 16:58:03
         * marketPrice : 909.0000
         * classId : 153
         * productCode : sdf45456
         * truePrice : 909.00
         * property : ["高亮"]
         * subProductId : 21
         * shortDesc :
         * packId : 21
         * item_amount : 909.00
         * pfPrice : 909.00
         * total_stock : 0
         * itemCount : 1
         * productName : 是地方撒地方
         * unitId : 1
         */

        private String cartId;
        private String full_path;
        private String brandName;
        private String brandId;
        private int startQty;
        private String unitValue;
        private String subBid;
        private String id;
        private double scPrice;
        private String className;
        private String commission;
        private String bid;
        private String productTime;
        private String postTime;
        private String marketPrice;
        private String classId;
        private String productCode;
        private String truePrice;
        private String subProductId;
        private String shortDesc;
        private String packId;
        private String item_amount;
        private String pfPrice;
        private int total_stock;
        private int itemCount;
        private String productName;
        private int unitId;
        private boolean check;
        private List<String> property;
        private List<MarketRuleList> marketRuleList;
        private String marketRuleName;

        public List<MarketRuleList> getMarketRuleList() {
            return marketRuleList;
        }

        public void setMarketRuleList(List<MarketRuleList> marketRuleList) {
            this.marketRuleList = marketRuleList;
        }

        public String getMarketRuleName() {
            return marketRuleName;
        }

        public void setMarketRuleName(String marketRuleName) {
            this.marketRuleName = marketRuleName;
        }

        public boolean isCheck() {
            return check;
        }

        public void setCheck(boolean check) {
            this.check = check;
        }


        public String getCartId() {
            return cartId;
        }

        public void setCartId(String cartId) {
            this.cartId = cartId;
        }

        public String getFull_path() {
            return full_path;
        }

        public void setFull_path(String full_path) {
            this.full_path = full_path;
        }

        public String getBrandName() {
            return brandName;
        }

        public void setBrandName(String brandName) {
            this.brandName = brandName;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public int getStartQty() {
            return startQty;
        }

        public void setStartQty(int startQty) {
            this.startQty = startQty;
        }

        public String getUnitValue() {
            return unitValue;
        }

        public void setUnitValue(String unitValue) {
            this.unitValue = unitValue;
        }

        public String getSubBid() {
            return subBid;
        }

        public void setSubBid(String subBid) {
            this.subBid = subBid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public double getScPrice() {
            return scPrice;
        }

        public void setScPrice(double scPrice) {
            this.scPrice = scPrice;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getBid() {
            return bid;
        }

        public void setBid(String bid) {
            this.bid = bid;
        }

        public String getProductTime() {
            return productTime;
        }

        public void setProductTime(String productTime) {
            this.productTime = productTime;
        }

        public String getPostTime() {
            return postTime;
        }

        public void setPostTime(String postTime) {
            this.postTime = postTime;
        }

        public String getMarketPrice() {
            return marketPrice;
        }

        public void setMarketPrice(String marketPrice) {
            this.marketPrice = marketPrice;
        }

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getTruePrice() {
            return truePrice;
        }

        public void setTruePrice(String truePrice) {
            this.truePrice = truePrice;
        }

        public String getSubProductId() {
            return subProductId;
        }

        public void setSubProductId(String subProductId) {
            this.subProductId = subProductId;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public String getPackId() {
            return packId;
        }

        public void setPackId(String packId) {
            this.packId = packId;
        }

        public String getItem_amount() {
            return item_amount;
        }

        public void setItem_amount(String item_amount) {
            this.item_amount = item_amount;
        }

        public String getPfPrice() {
            return pfPrice;
        }

        public void setPfPrice(String pfPrice) {
            this.pfPrice = pfPrice;
        }

        public int getTotal_stock() {
            return total_stock;
        }

        public void setTotal_stock(int total_stock) {
            this.total_stock = total_stock;
        }

        public int getItemCount() {
            return itemCount;
        }

        public void setItemCount(int itemCount) {
            this.itemCount = itemCount;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public int getUnitId() {
            return unitId;
        }

        public void setUnitId(int unitId) {
            this.unitId = unitId;
        }

        public List<String> getProperty() {
            return property;
        }

        public void setProperty(List<String> property) {
            this.property = property;
        }
    }

    public static class MarketRuleList {

        private String marketId;
        private String rangeType;
        private String processValue;
        private String memberRangeCity;
        private String giftProductNum;
        private String productId;
        private String ruleInfo;
        private String id;
        private String reachStandardVaule;
        private String memberGangeProvince;
        private String giftProductId;
        private String district;
        private String marketStatus;
        private String memberGangeArea;

        public String getMarketId() {
            return marketId;
        }

        public void setMarketId(String marketId) {
            this.marketId = marketId;
        }

        public String getRangeType() {
            return rangeType;
        }

        public void setRangeType(String rangeType) {
            this.rangeType = rangeType;
        }

        public String getProcessValue() {
            return processValue;
        }

        public void setProcessValue(String processValue) {
            this.processValue = processValue;
        }

        public String getMemberRangeCity() {
            return memberRangeCity;
        }

        public void setMemberRangeCity(String memberRangeCity) {
            this.memberRangeCity = memberRangeCity;
        }

        public String getGiftProductNum() {
            return giftProductNum;
        }

        public void setGiftProductNum(String giftProductNum) {
            this.giftProductNum = giftProductNum;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getRuleInfo() {
            return ruleInfo;
        }

        public void setRuleInfo(String ruleInfo) {
            this.ruleInfo = ruleInfo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getReachStandardVaule() {
            return reachStandardVaule;
        }

        public void setReachStandardVaule(String reachStandardVaule) {
            this.reachStandardVaule = reachStandardVaule;
        }

        public String getMemberGangeProvince() {
            return memberGangeProvince;
        }

        public void setMemberGangeProvince(String memberGangeProvince) {
            this.memberGangeProvince = memberGangeProvince;
        }

        public String getGiftProductId() {
            return giftProductId;
        }

        public void setGiftProductId(String giftProductId) {
            this.giftProductId = giftProductId;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getMarketStatus() {
            return marketStatus;
        }

        public void setMarketStatus(String marketStatus) {
            this.marketStatus = marketStatus;
        }

        public String getMemberGangeArea() {
            return memberGangeArea;
        }

        public void setMemberGangeArea(String memberGangeArea) {
            this.memberGangeArea = memberGangeArea;
        }
    }



    public static class ProPriceListBean {
        /**
         * proId : 21
         * itemAmount : 909.00
         * truePrice : 909.00
         * cartId : 100
         * fullCutPrice : 0
         */

        private String proId;
        private String itemAmount;
        private String truePrice;
        private String cartId;
        private int fullCutPrice;

        public String getProId() {
            return proId;
        }

        public void setProId(String proId) {
            this.proId = proId;
        }

        public String getItemAmount() {
            return itemAmount;
        }

        public void setItemAmount(String itemAmount) {
            this.itemAmount = itemAmount;
        }

        public String getTruePrice() {
            return truePrice;
        }

        public void setTruePrice(String truePrice) {
            this.truePrice = truePrice;
        }

        public String getCartId() {
            return cartId;
        }

        public void setCartId(String cartId) {
            this.cartId = cartId;
        }

        public int getFullCutPrice() {
            return fullCutPrice;
        }

        public void setFullCutPrice(int fullCutPrice) {
            this.fullCutPrice = fullCutPrice;
        }
    }
}
