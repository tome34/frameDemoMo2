package com.example.tome.module_shop_cart.bean;

import android.text.TextUtils;
import java.util.List;

/**
 * 商品详情
 * Created by Administrator on 2017/12/20.
 */

public class GetProductDetailsBean extends ObjectBeans {

    /**
     * data : {"attrMapStr":"23=屏幕尺寸_96,香型_82","ids":"23","property":[{"propertyList":[{"attrId":"28","attrvalueId":"96","attrName":"屏幕尺寸","attrValue":"5英寸"}],"attrName":"屏幕尺寸"},{"propertyList":[{"attrId":"24","attrvalueId":"82","attrName":"香型","attrValue":"玫瑰香"}],"attrName":"香型"}],"selectAttrStr":"屏幕尺寸_96,香型_82","selectAttrMap":{"屏幕尺寸":"96","香型":"82"},"subProductMap":{"quJianPriceList":[],"onSaleTime":{"date":24,"day":0,"hours":11,"minutes":14,"month":11,"nanos":0,"seconds":15,"time":1514085255000,"timezoneOffset":-480,"year":117},"seoDesc":"","brandId":2,"brandName":"安踏","memberRange":"全部","unitValue":"摩尔","productDesignatedPriceMap":{},"productType":1,"seoTitle":"","handleProPrice":"0.00","marketType":"1","isInvoice":-1,"barCode":"2121","aliveflag":null,"marketStatus":"2","updateBy":"77","keywords":null,"namePre":null,"param1":"0","updateTime":{"date":24,"day":0,"hours":11,"minutes":14,"month":11,"nanos":0,"seconds":15,"time":1514085255000,"timezoneOffset":-480,"year":117},"classId":153,"param2":"12.00","busiLevelPriceList":{},"param3":"48","marketStatusName":"促销中","param4":null,"marketEnd":"2017-12-31 23:59","nameSuf":null,"param5":null,"subProductId":"23","saleProductId":23,"property":["5英寸","玫瑰香"],"marketRuleList":[{"marketId":"37","rangeType":"","processValue":"","memberRangeCity":"","giftProductNum":"1","productId":"23","ruleInfo":"订购数量满12件  , 获赠品魅族【亮度:高亮】 1件","id":"60","reachStandardVaule":"12","memberGangeProvince":"","giftProductId":"19","district":"","marketStatus":"0","memberGangeArea":""},{"marketId":"37","rangeType":"","processValue":"","memberRangeCity":"","giftProductNum":"1","productId":"23","ruleInfo":"订购数量满19件  , 获赠品闽东汽车【屏幕尺寸:5英寸|香型:玫瑰香】 1件","id":"61","reachStandardVaule":"19","memberGangeProvince":"","giftProductId":"23","district":"","marketStatus":"0","memberGangeArea":""}],"memberLevelName":"全部","intervalPriceMap":{},"marketRuleName":"买赠","shareUrl":"http://cloud.d2shop.com/business/product.do?action=detail&pid=23","pfPrice":"12.00","hasFavorety":"-1","marketStart":"2017-12-01 00:00","mainProductId":18,"itemCount":1,"totalStock":0,"unitId":3,"purchasePrice":212,"createTime":{"date":24,"day":0,"hours":11,"minutes":11,"month":11,"nanos":0,"seconds":49,"time":1514085109000,"timezoneOffset":-480,"year":117},"sort":9999,"seoKeywords":"","isMainSku":1,"marketPattern":"2","createBy":"77","startQty":1,"sourceId":"57","detailDescTouch":"\r\n\t\r\n\r\n\r\n\t水电开发架空历史角度来看房间老师的课件法律思考角度来看风景受到了开发经理说的就发了sdlk\r\n\r\n\r\n\t\r\n","productImgPath":[],"id":23,"packUnitList":[],"isDel":0,"pfOffSaleTime":null,"marketName":"","pfOnSaleTime":{"date":24,"day":0,"hours":11,"minutes":14,"month":11,"nanos":0,"seconds":15,"time":1514085255000,"timezoneOffset":-480,"year":117},"scPrice":121,"className":"金立","busiPriceList":{},"marketRule":"1","bid":57,"postTime":"2017-12-24 11:11:49","marketPrice":121,"onSale":-1,"useType":"-1","productCode":"11112323","pf_price":"12.0000","ids":",23","useTypeName":"全部","pfOnSale":1,"offSaleTime":{"date":24,"day":0,"hours":11,"minutes":11,"month":11,"nanos":0,"seconds":49,"time":1514085109000,"timezoneOffset":-480,"year":117},"shortDesc":"","detailDesc":"\r\n\t\r\n\r\n\r\n\t水电开发架空历史角度来看房间老师的课件法律思考角度来看风景受到了开发经理说的就发了sdlk\r\n\r\n\r\n\t\r\n","specifications":null,"buys":0,"hasCart":"-1","auditFlag":null,"packId":23,"productPrice":"12.00","productName":"闽东汽车","stockPid":23}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * attrMapStr : 23=屏幕尺寸_96,香型_82
         * ids : 23
         * property : [{"propertyList":[{"attrId":"28","attrvalueId":"96","attrName":"屏幕尺寸","attrValue":"5英寸"}],"attrName":"屏幕尺寸"},{"propertyList":[{"attrId":"24","attrvalueId":"82","attrName":"香型","attrValue":"玫瑰香"}],"attrName":"香型"}]
         * selectAttrStr : 屏幕尺寸_96,香型_82
         * selectAttrMap : {"屏幕尺寸":"96","香型":"82"}
         * subProductMap : {"quJianPriceList":[],"onSaleTime":{"date":24,"day":0,"hours":11,"minutes":14,"month":11,"nanos":0,"seconds":15,"time":1514085255000,"timezoneOffset":-480,"year":117},"seoDesc":"","brandId":2,"brandName":"安踏","memberRange":"全部","unitValue":"摩尔","productDesignatedPriceMap":{},"productType":1,"seoTitle":"","handleProPrice":"0.00","marketType":"1","isInvoice":-1,"barCode":"2121","aliveflag":null,"marketStatus":"2","updateBy":"77","keywords":null,"namePre":null,"param1":"0","updateTime":{"date":24,"day":0,"hours":11,"minutes":14,"month":11,"nanos":0,"seconds":15,"time":1514085255000,"timezoneOffset":-480,"year":117},"classId":153,"param2":"12.00","busiLevelPriceList":{},"param3":"48","marketStatusName":"促销中","param4":null,"marketEnd":"2017-12-31 23:59","nameSuf":null,"param5":null,"subProductId":"23","saleProductId":23,"property":["5英寸","玫瑰香"],"marketRuleList":[{"marketId":"37","rangeType":"","processValue":"","memberRangeCity":"","giftProductNum":"1","productId":"23","ruleInfo":"订购数量满12件  , 获赠品魅族【亮度:高亮】 1件","id":"60","reachStandardVaule":"12","memberGangeProvince":"","giftProductId":"19","district":"","marketStatus":"0","memberGangeArea":""},{"marketId":"37","rangeType":"","processValue":"","memberRangeCity":"","giftProductNum":"1","productId":"23","ruleInfo":"订购数量满19件  , 获赠品闽东汽车【屏幕尺寸:5英寸|香型:玫瑰香】 1件","id":"61","reachStandardVaule":"19","memberGangeProvince":"","giftProductId":"23","district":"","marketStatus":"0","memberGangeArea":""}],"memberLevelName":"全部","intervalPriceMap":{},"marketRuleName":"买赠","shareUrl":"http://cloud.d2shop.com/business/product.do?action=detail&pid=23","pfPrice":"12.00","hasFavorety":"-1","marketStart":"2017-12-01 00:00","mainProductId":18,"itemCount":1,"totalStock":0,"unitId":3,"purchasePrice":212,"createTime":{"date":24,"day":0,"hours":11,"minutes":11,"month":11,"nanos":0,"seconds":49,"time":1514085109000,"timezoneOffset":-480,"year":117},"sort":9999,"seoKeywords":"","isMainSku":1,"marketPattern":"2","createBy":"77","startQty":1,"sourceId":"57","detailDescTouch":"\r\n\t\r\n\r\n\r\n\t水电开发架空历史角度来看房间老师的课件法律思考角度来看风景受到了开发经理说的就发了sdlk\r\n\r\n\r\n\t\r\n","productImgPath":[],"id":23,"packUnitList":[],"isDel":0,"pfOffSaleTime":null,"marketName":"","pfOnSaleTime":{"date":24,"day":0,"hours":11,"minutes":14,"month":11,"nanos":0,"seconds":15,"time":1514085255000,"timezoneOffset":-480,"year":117},"scPrice":121,"className":"金立","busiPriceList":{},"marketRule":"1","bid":57,"postTime":"2017-12-24 11:11:49","marketPrice":121,"onSale":-1,"useType":"-1","productCode":"11112323","pf_price":"12.0000","ids":",23","useTypeName":"全部","pfOnSale":1,"offSaleTime":{"date":24,"day":0,"hours":11,"minutes":11,"month":11,"nanos":0,"seconds":49,"time":1514085109000,"timezoneOffset":-480,"year":117},"shortDesc":"","detailDesc":"\r\n\t\r\n\r\n\r\n\t水电开发架空历史角度来看房间老师的课件法律思考角度来看风景受到了开发经理说的就发了sdlk\r\n\r\n\r\n\t\r\n","specifications":null,"buys":0,"hasCart":"-1","auditFlag":null,"packId":23,"productPrice":"12.00","productName":"闽东汽车","stockPid":23}
         */

        private String attrMapStr;
        private String ids;
        private String selectAttrStr;
        private SubProductMapBean subProductMap;
        private List<PropertyBean> property;

        public String getAttrMapStr() {
            return attrMapStr;
        }

        public void setAttrMapStr(String attrMapStr) {
            this.attrMapStr = attrMapStr;
        }

        public String getIds() {
            return ids;
        }

        public void setIds(String ids) {
            this.ids = ids;
        }

        public String getSelectAttrStr() {
            return selectAttrStr;
        }

        public void setSelectAttrStr(String selectAttrStr) {
            this.selectAttrStr = selectAttrStr;
        }


        public SubProductMapBean getSubProductMap() {
            return subProductMap;
        }

        public void setSubProductMap(SubProductMapBean subProductMap) {
            this.subProductMap = subProductMap;
        }

        public List<PropertyBean> getProperty() {
            return property;
        }

        public void setProperty(List<PropertyBean> property) {
            this.property = property;
        }


        public static class SubProductMapBean {
            /**
             * quJianPriceList : []
             * onSaleTime : {"date":24,"day":0,"hours":11,"minutes":14,"month":11,"nanos":0,"seconds":15,"time":1514085255000,"timezoneOffset":-480,"year":117}
             * seoDesc :
             * brandId : 2
             * brandName : 安踏
             * memberRange : 全部
             * unitValue : 摩尔
             * productDesignatedPriceMap : {}
             * productType : 1
             * seoTitle :
             * handleProPrice : 0.00
             * marketType : 1
             * isInvoice : -1
             * barCode : 2121
             * aliveflag : null
             * marketStatus : 2
             * updateBy : 77
             * keywords : null
             * namePre : null
             * param1 : 0
             * updateTime : {"date":24,"day":0,"hours":11,"minutes":14,"month":11,"nanos":0,"seconds":15,"time":1514085255000,"timezoneOffset":-480,"year":117}
             * classId : 153
             * param2 : 12.00
             * busiLevelPriceList : {}
             * param3 : 48
             * marketStatusName : 促销中
             * param4 : null
             * marketEnd : 2017-12-31 23:59
             * nameSuf : null
             * param5 : null
             * subProductId : 23
             * saleProductId : 23
             * property : ["5英寸","玫瑰香"]
             * marketRuleList : [{"marketId":"37","rangeType":"","processValue":"","memberRangeCity":"","giftProductNum":"1","productId":"23","ruleInfo":"订购数量满12件  , 获赠品魅族【亮度:高亮】 1件","id":"60","reachStandardVaule":"12","memberGangeProvince":"","giftProductId":"19","district":"","marketStatus":"0","memberGangeArea":""},{"marketId":"37","rangeType":"","processValue":"","memberRangeCity":"","giftProductNum":"1","productId":"23","ruleInfo":"订购数量满19件  , 获赠品闽东汽车【屏幕尺寸:5英寸|香型:玫瑰香】 1件","id":"61","reachStandardVaule":"19","memberGangeProvince":"","giftProductId":"23","district":"","marketStatus":"0","memberGangeArea":""}]
             * memberLevelName : 全部
             * intervalPriceMap : {}
             * marketRuleName : 买赠
             * shareUrl : http://cloud.d2shop.com/business/product.do?action=detail&pid=23
             * pfPrice : 12.00
             * hasFavorety : -1
             * marketStart : 2017-12-01 00:00
             * mainProductId : 18
             * itemCount : 1
             * totalStock : 0
             * unitId : 3
             * purchasePrice : 212
             * createTime : {"date":24,"day":0,"hours":11,"minutes":11,"month":11,"nanos":0,"seconds":49,"time":1514085109000,"timezoneOffset":-480,"year":117}
             * sort : 9999
             * seoKeywords :
             * isMainSku : 1
             * marketPattern : 2
             * createBy : 77
             * startQty : 1
             * sourceId : 57
             * detailDescTouch :



             水电开发架空历史角度来看房间老师的课件法律思考角度来看风景受到了开发经理说的就发了sdlk




             * productImgPath : []
             * id : 23
             * packUnitList : []
             * isDel : 0
             * pfOffSaleTime : null
             * marketName :
             * pfOnSaleTime : {"date":24,"day":0,"hours":11,"minutes":14,"month":11,"nanos":0,"seconds":15,"time":1514085255000,"timezoneOffset":-480,"year":117}
             * scPrice : 121
             * className : 金立
             * busiPriceList : {}
             * marketRule : 1
             * bid : 57
             * postTime : 2017-12-24 11:11:49
             * marketPrice : 121
             * onSale : -1
             * useType : -1
             * productCode : 11112323
             * pf_price : 12.0000
             * ids : ,23
             * useTypeName : 全部
             * pfOnSale : 1
             * offSaleTime : {"date":24,"day":0,"hours":11,"minutes":11,"month":11,"nanos":0,"seconds":49,"time":1514085109000,"timezoneOffset":-480,"year":117}
             * shortDesc :
             * detailDesc :



             水电开发架空历史角度来看房间老师的课件法律思考角度来看风景受到了开发经理说的就发了sdlk




             * specifications : null
             * buys : 0
             * hasCart : -1
             * auditFlag : null
             * packId : 23
             * productPrice : 12.00
             * productName : 闽东汽车
             * stockPid : 23
             */

            private OnSaleTimeBean onSaleTime;
            private String seoDesc;
            private int brandId;
            private String brandName;
            private String memberRange;
            private String unitValue;
            private ProductDesignatedPriceMapBean productDesignatedPriceMap;
            private int productType;
            private String seoTitle;
            private String handleProPrice;
            private String marketType;
            private int isInvoice;
            private String barCode;
            private Object aliveflag;
            private String marketStatus;
            private String mainImg;
            private String updateBy;
            private Object keywords;
            private Object namePre;
            private String param1;
            private UpdateTimeBean updateTime;
            private int classId;
            private String param2;
            private BusiLevelPriceListBean busiLevelPriceList;
            private String param3;
            private String marketStatusName;
            private Object param4;
            private String marketEnd;
            private Object nameSuf;
            private Object param5;
            private String subProductId;
            private int saleProductId;
            private String memberLevelName;
            private IntervalPriceMapBean intervalPriceMap;
            private String marketRuleName;
            private String shareUrl;
            private String pfPrice;
            private String hasFavorety;
            private String marketStart;
            private int mainProductId;
            private int itemCount;
            private int totalStock;
            private int unitId;
            private int purchasePrice;
            private CreateTimeBean createTime;
            private int sort;
            private String seoKeywords;
            private int isMainSku;
            private String marketPattern;
            private String createBy;
            private int startQty;
            private String sourceId;
            private String detailDescTouch;
            private int id;
            private int isDel;
            private Object pfOffSaleTime;
            private String marketName;
            private PfOnSaleTimeBean pfOnSaleTime;
            private int scPrice;
            private String className;
            private BusiPriceListBean busiPriceList;
            private String marketRule;
            private int bid;
            private String postTime;
            private int marketPrice;
            private String onSale;
            private String useType;
            private String productCode;
            private String pf_price;
            private String ids;
            private String useTypeName;
            private String pfOnSale;
            private OffSaleTimeBean offSaleTime;
            private String shortDesc;
            private String detailDesc;
            private Object specifications;
            private int buys;
            private String hasCart;
            private Object auditFlag;
            private int packId;
            private String productPrice;
            private String productName;
            private int stockPid;
            private List<?> quJianPriceList;
            private List<String> property;
            private List<MarketRuleListBean> marketRuleList;
            private List<String> productImgPath;
            private List<?> packUnitList;

            public String getMainImg() {
                return mainImg;
            }

            public OnSaleTimeBean getOnSaleTime() {
                return onSaleTime;
            }

            public void setOnSaleTime(OnSaleTimeBean onSaleTime) {
                this.onSaleTime = onSaleTime;
            }

            public String getSeoDesc() {
                return seoDesc;
            }

            public void setSeoDesc(String seoDesc) {
                this.seoDesc = seoDesc;
            }

            public int getBrandId() {
                return brandId;
            }

            public void setBrandId(int brandId) {
                this.brandId = brandId;
            }

            public String getBrandName() {
                return brandName;
            }

            public void setBrandName(String brandName) {
                this.brandName = brandName;
            }

            public String getMemberRange() {
                return memberRange;
            }

            public void setMemberRange(String memberRange) {
                this.memberRange = memberRange;
            }

            public String getUnitValue() {
                return unitValue;
            }

            public void setUnitValue(String unitValue) {
                this.unitValue = unitValue;
            }

            public ProductDesignatedPriceMapBean getProductDesignatedPriceMap() {
                return productDesignatedPriceMap;
            }

            public void setProductDesignatedPriceMap(ProductDesignatedPriceMapBean productDesignatedPriceMap) {
                this.productDesignatedPriceMap = productDesignatedPriceMap;
            }

            public int getProductType() {
                return productType;
            }

            public void setProductType(int productType) {
                this.productType = productType;
            }

            public String getSeoTitle() {
                return seoTitle;
            }

            public void setSeoTitle(String seoTitle) {
                this.seoTitle = seoTitle;
            }

            public String getHandleProPrice() {
                return handleProPrice;
            }

            public void setHandleProPrice(String handleProPrice) {
                this.handleProPrice = handleProPrice;
            }

            public String getMarketType() {
                return marketType;
            }

            public void setMarketType(String marketType) {
                this.marketType = marketType;
            }

            public int getIsInvoice() {
                return isInvoice;
            }

            public void setIsInvoice(int isInvoice) {
                this.isInvoice = isInvoice;
            }

            public String getBarCode() {
                return barCode;
            }

            public void setBarCode(String barCode) {
                this.barCode = barCode;
            }

            public Object getAliveflag() {
                return aliveflag;
            }

            public void setAliveflag(Object aliveflag) {
                this.aliveflag = aliveflag;
            }

            public String getMarketStatus() {
                return marketStatus;
            }

            public void setMarketStatus(String marketStatus) {
                this.marketStatus = marketStatus;
            }

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
            }

            public Object getKeywords() {
                return keywords;
            }

            public void setKeywords(Object keywords) {
                this.keywords = keywords;
            }

            public Object getNamePre() {
                return namePre;
            }

            public void setNamePre(Object namePre) {
                this.namePre = namePre;
            }

            public String getParam1() {
                return param1;
            }

            public void setParam1(String param1) {
                this.param1 = param1;
            }

            public UpdateTimeBean getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(UpdateTimeBean updateTime) {
                this.updateTime = updateTime;
            }

            public int getClassId() {
                return classId;
            }

            public void setClassId(int classId) {
                this.classId = classId;
            }

            public String getParam2() {
                return param2;
            }

            public void setParam2(String param2) {
                this.param2 = param2;
            }

            public BusiLevelPriceListBean getBusiLevelPriceList() {
                return busiLevelPriceList;
            }

            public void setBusiLevelPriceList(BusiLevelPriceListBean busiLevelPriceList) {
                this.busiLevelPriceList = busiLevelPriceList;
            }

            public String getParam3() {
                return param3;
            }

            public void setParam3(String param3) {
                this.param3 = param3;
            }

            public String getMarketStatusName() {
                return marketStatusName;
            }

            public void setMarketStatusName(String marketStatusName) {
                this.marketStatusName = marketStatusName;
            }

            public Object getParam4() {
                return param4;
            }

            public void setParam4(Object param4) {
                this.param4 = param4;
            }

            public String getMarketEnd() {
                return marketEnd;
            }

            public void setMarketEnd(String marketEnd) {
                this.marketEnd = marketEnd;
            }

            public Object getNameSuf() {
                return nameSuf;
            }

            public void setNameSuf(Object nameSuf) {
                this.nameSuf = nameSuf;
            }

            public Object getParam5() {
                return param5;
            }

            public void setParam5(Object param5) {
                this.param5 = param5;
            }

            public String getSubProductId() {
                return subProductId;
            }

            public void setSubProductId(String subProductId) {
                this.subProductId = subProductId;
            }

            public int getSaleProductId() {
                return saleProductId;
            }

            public void setSaleProductId(int saleProductId) {
                this.saleProductId = saleProductId;
            }

            public String getMemberLevelName() {
                return memberLevelName;
            }

            public void setMemberLevelName(String memberLevelName) {
                this.memberLevelName = memberLevelName;
            }

            public IntervalPriceMapBean getIntervalPriceMap() {
                return intervalPriceMap;
            }

            public void setIntervalPriceMap(IntervalPriceMapBean intervalPriceMap) {
                this.intervalPriceMap = intervalPriceMap;
            }

            public String getMarketRuleName() {
                if (TextUtils.isEmpty(marketRuleName)){
                    return "";
                }
                return marketRuleName;
            }

            public void setMarketRuleName(String marketRuleName) {
                this.marketRuleName = marketRuleName;
            }

            public String getShareUrl() {
                return shareUrl;
            }

            public void setShareUrl(String shareUrl) {
                this.shareUrl = shareUrl;
            }

            public String getPfPrice() {
                if (TextUtils.isEmpty(pfPrice)) {
                    return "0";
                }
                return pfPrice;
            }

            public void setPfPrice(String pfPrice) {
                this.pfPrice = pfPrice;
            }

            public String getHasFavorety() {
                return hasFavorety;
            }

            public void setHasFavorety(String hasFavorety) {
                this.hasFavorety = hasFavorety;
            }

            public String getMarketStart() {
                return marketStart;
            }

            public void setMarketStart(String marketStart) {
                this.marketStart = marketStart;
            }

            public int getMainProductId() {
                return mainProductId;
            }

            public void setMainProductId(int mainProductId) {
                this.mainProductId = mainProductId;
            }

            public int getItemCount() {
                return itemCount;
            }

            public void setItemCount(int itemCount) {
                this.itemCount = itemCount;
            }

            public int getTotalStock() {
                if (TextUtils.isEmpty(totalStock+"")) {
                    return 0;
                }
                return totalStock;
            }

            public void setTotalStock(int totalStock) {
                this.totalStock = totalStock;
            }

            public int getUnitId() {
                return unitId;
            }

            public void setUnitId(int unitId) {
                this.unitId = unitId;
            }

            public int getPurchasePrice() {
                return purchasePrice;
            }

            public void setPurchasePrice(int purchasePrice) {
                this.purchasePrice = purchasePrice;
            }

            public CreateTimeBean getCreateTime() {
                return createTime;
            }

            public void setCreateTime(CreateTimeBean createTime) {
                this.createTime = createTime;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getSeoKeywords() {
                return seoKeywords;
            }

            public void setSeoKeywords(String seoKeywords) {
                this.seoKeywords = seoKeywords;
            }

            public int getIsMainSku() {
                return isMainSku;
            }

            public void setIsMainSku(int isMainSku) {
                this.isMainSku = isMainSku;
            }

            public String getMarketPattern() {
                return marketPattern;
            }

            public void setMarketPattern(String marketPattern) {
                this.marketPattern = marketPattern;
            }

            public String getCreateBy() {
                return createBy;
            }

            public void setCreateBy(String createBy) {
                this.createBy = createBy;
            }

            public int getStartQty() {
                return startQty;
            }

            public void setStartQty(int startQty) {
                this.startQty = startQty;
            }

            public String getSourceId() {
                return sourceId;
            }

            public void setSourceId(String sourceId) {
                this.sourceId = sourceId;
            }

            public String getDetailDescTouch() {
                return detailDescTouch;
            }

            public void setDetailDescTouch(String detailDescTouch) {
                this.detailDescTouch = detailDescTouch;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIsDel() {
                return isDel;
            }

            public void setIsDel(int isDel) {
                this.isDel = isDel;
            }

            public Object getPfOffSaleTime() {
                return pfOffSaleTime;
            }

            public void setPfOffSaleTime(Object pfOffSaleTime) {
                this.pfOffSaleTime = pfOffSaleTime;
            }

            public String getMarketName() {
                return marketName;
            }

            public void setMarketName(String marketName) {
                this.marketName = marketName;
            }

            public PfOnSaleTimeBean getPfOnSaleTime() {
                return pfOnSaleTime;
            }

            public void setPfOnSaleTime(PfOnSaleTimeBean pfOnSaleTime) {
                this.pfOnSaleTime = pfOnSaleTime;
            }

            public int getScPrice() {
                return scPrice;
            }

            public void setScPrice(int scPrice) {
                this.scPrice = scPrice;
            }

            public String getClassName() {
                return className;
            }

            public void setClassName(String className) {
                this.className = className;
            }

            public BusiPriceListBean getBusiPriceList() {
                return busiPriceList;
            }

            public void setBusiPriceList(BusiPriceListBean busiPriceList) {
                this.busiPriceList = busiPriceList;
            }

            public String getMarketRule() {
                return marketRule;
            }

            public void setMarketRule(String marketRule) {
                this.marketRule = marketRule;
            }

            public int getBid() {
                return bid;
            }

            public void setBid(int bid) {
                this.bid = bid;
            }

            public String getPostTime() {
                return postTime;
            }

            public void setPostTime(String postTime) {
                this.postTime = postTime;
            }

            public int getMarketPrice() {
                return marketPrice;
            }

            public void setMarketPrice(int marketPrice) {
                this.marketPrice = marketPrice;
            }

            public String getOnSale() {
                return onSale;
            }

            public void setOnSale(String onSale) {
                this.onSale = onSale;
            }

            public String getUseType() {
                return useType;
            }

            public void setUseType(String useType) {
                this.useType = useType;
            }

            public String getProductCode() {
                return productCode;
            }

            public void setProductCode(String productCode) {
                this.productCode = productCode;
            }

            public String getPf_price() {
                return pf_price;
            }

            public void setPf_price(String pf_price) {
                this.pf_price = pf_price;
            }

            public String getIds() {
                return ids;
            }

            public void setIds(String ids) {
                this.ids = ids;
            }

            public String getUseTypeName() {
                return useTypeName;
            }

            public void setUseTypeName(String useTypeName) {
                this.useTypeName = useTypeName;
            }

            public String getPfOnSale() {
                return pfOnSale;
            }

            public void setPfOnSale(String pfOnSale) {
                this.pfOnSale = pfOnSale;
            }

            public OffSaleTimeBean getOffSaleTime() {
                return offSaleTime;
            }

            public void setOffSaleTime(OffSaleTimeBean offSaleTime) {
                this.offSaleTime = offSaleTime;
            }

            public String getShortDesc() {
                return shortDesc;
            }

            public void setShortDesc(String shortDesc) {
                this.shortDesc = shortDesc;
            }

            public String getDetailDesc() {
                return detailDesc;
            }

            public void setDetailDesc(String detailDesc) {
                this.detailDesc = detailDesc;
            }

            public Object getSpecifications() {
                return specifications;
            }

            public void setSpecifications(Object specifications) {
                this.specifications = specifications;
            }

            public int getBuys() {
                return buys;
            }

            public void setBuys(int buys) {
                this.buys = buys;
            }

            public String getHasCart() {
                return hasCart;
            }

            public void setHasCart(String hasCart) {
                this.hasCart = hasCart;
            }

            public Object getAuditFlag() {
                return auditFlag;
            }

            public void setAuditFlag(Object auditFlag) {
                this.auditFlag = auditFlag;
            }

            public int getPackId() {
                return packId;
            }

            public void setPackId(int packId) {
                this.packId = packId;
            }

            public String getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(String productPrice) {
                this.productPrice = productPrice;
            }

            public String getProductName() {
                if (TextUtils.isEmpty(productName)) {
                    return "";
                }
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public int getStockPid() {
                return stockPid;
            }

            public void setStockPid(int stockPid) {
                this.stockPid = stockPid;
            }

            public List<?> getQuJianPriceList() {
                return quJianPriceList;
            }

            public void setQuJianPriceList(List<?> quJianPriceList) {
                this.quJianPriceList = quJianPriceList;
            }

            public List<String> getProperty() {
                return property;
            }

            public void setProperty(List<String> property) {
                this.property = property;
            }

            public List<MarketRuleListBean> getMarketRuleList() {
                return marketRuleList;
            }

            public void setMarketRuleList(List<MarketRuleListBean> marketRuleList) {
                this.marketRuleList = marketRuleList;
            }

            public List<String> getProductImgPath() {
                return productImgPath;
            }

            public void setProductImgPath(List<String> productImgPath) {
                this.productImgPath = productImgPath;
            }

            public List<?> getPackUnitList() {
                return packUnitList;
            }

            public void setPackUnitList(List<?> packUnitList) {
                this.packUnitList = packUnitList;
            }

            public static class OnSaleTimeBean {
            }

            public static class ProductDesignatedPriceMapBean {
            }

            public static class UpdateTimeBean {
            }

            public static class BusiLevelPriceListBean {
            }

            public static class IntervalPriceMapBean {
            }

            public static class CreateTimeBean {
                /**
                 * date : 24
                 * day : 0
                 * hours : 11
                 * minutes : 11
                 * month : 11
                 * nanos : 0
                 * seconds : 49
                 * time : 1514085109000
                 * timezoneOffset : -480
                 * year : 117
                 */

                private int date;
                private int day;
                private int hours;
                private int minutes;
                private int month;
                private int nanos;
                private int seconds;
                private long time;
                private int timezoneOffset;
                private int year;

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getNanos() {
                    return nanos;
                }

                public void setNanos(int nanos) {
                    this.nanos = nanos;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }
            }

            public static class PfOnSaleTimeBean {
            }

            public static class BusiPriceListBean {
            }

            public static class OffSaleTimeBean {
                /**
                 * date : 24
                 * day : 0
                 * hours : 11
                 * minutes : 11
                 * month : 11
                 * nanos : 0
                 * seconds : 49
                 * time : 1514085109000
                 * timezoneOffset : -480
                 * year : 117
                 */

                private int date;
                private int day;
                private int hours;
                private int minutes;
                private int month;
                private int nanos;
                private int seconds;
                private long time;
                private int timezoneOffset;
                private int year;

                public int getDate() {
                    return date;
                }

                public void setDate(int date) {
                    this.date = date;
                }

                public int getDay() {
                    return day;
                }

                public void setDay(int day) {
                    this.day = day;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }

                public int getMinutes() {
                    return minutes;
                }

                public void setMinutes(int minutes) {
                    this.minutes = minutes;
                }

                public int getMonth() {
                    return month;
                }

                public void setMonth(int month) {
                    this.month = month;
                }

                public int getNanos() {
                    return nanos;
                }

                public void setNanos(int nanos) {
                    this.nanos = nanos;
                }

                public int getSeconds() {
                    return seconds;
                }

                public void setSeconds(int seconds) {
                    this.seconds = seconds;
                }

                public long getTime() {
                    return time;
                }

                public void setTime(long time) {
                    this.time = time;
                }

                public int getTimezoneOffset() {
                    return timezoneOffset;
                }

                public void setTimezoneOffset(int timezoneOffset) {
                    this.timezoneOffset = timezoneOffset;
                }

                public int getYear() {
                    return year;
                }

                public void setYear(int year) {
                    this.year = year;
                }
            }

            public static class MarketRuleListBean {
                /**
                 * marketId : 37
                 * rangeType :
                 * processValue :
                 * memberRangeCity :
                 * giftProductNum : 1
                 * productId : 23
                 * ruleInfo : 订购数量满12件  , 获赠品魅族【亮度:高亮】 1件
                 * id : 60
                 * reachStandardVaule : 12
                 * memberGangeProvince :
                 * giftProductId : 19
                 * district :
                 * marketStatus : 0
                 * memberGangeArea :
                 */

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
        }

        public static class PropertyBean {
            /**
             * propertyList : [{"attrId":"28","attrvalueId":"96","attrName":"屏幕尺寸","attrValue":"5英寸"}]
             * attrName : 屏幕尺寸
             */

            private String attrName;
            private List<PropertyListBean> propertyList;

            public String getAttrName() {
                if (TextUtils.isEmpty(attrName)) {
                    return "";
                }
                return attrName;
            }

            public void setAttrName(String attrName) {
                this.attrName = attrName;
            }

            public List<PropertyListBean> getPropertyList() {
                return propertyList;
            }

            public void setPropertyList(List<PropertyListBean> propertyList) {
                this.propertyList = propertyList;
            }

            public static class PropertyListBean {
                /**
                 * attrId : 28
                 * attrvalueId : 96
                 * attrName : 屏幕尺寸
                 * attrValue : 5英寸
                 */

                private String attrId;
                private String attrvalueId;
                private String attrName;
                private String attrValue;
                private boolean check;

                public boolean isCheck() {

                    return check;
                }

                public void setCheck(boolean check) {
                    this.check = check;
                }

                public String getAttrId() {
                    return attrId;
                }

                public void setAttrId(String attrId) {
                    this.attrId = attrId;
                }

                public String getAttrvalueId() {
                    if (TextUtils.isEmpty(attrvalueId)) {
                        return "";
                    }
                    return attrvalueId;
                }

                public void setAttrvalueId(String attrvalueId) {
                    this.attrvalueId = attrvalueId;
                }

                public String getAttrName() {
                    return attrName;
                }

                public void setAttrName(String attrName) {
                    this.attrName = attrName;
                }

                public String getAttrValue() {
                    return attrValue;
                }

                public void setAttrValue(String attrValue) {
                    this.attrValue = attrValue;
                }
            }
        }
    }
}
