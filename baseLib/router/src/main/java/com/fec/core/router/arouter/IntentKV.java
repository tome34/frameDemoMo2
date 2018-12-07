package com.fec.core.router.arouter;

/**
 * @Created by TOME .
 * @时间 2018/4/26 10:18
 * @描述 ${Intent 键 值}
 */

public interface IntentKV {

    //K
    String K_TOKEN_VALUE = "K_TOKEN_VALUE";//TOKEN存储
    String K_IDENTITY_TYPE = "K_IDENTITY_TYPE";//会员身份


    //home页面
    String K_ARTICLE_ID = "K_ARTICLE_ID";
    String K_ARTICLE_TITLE = "K_ARTICLE_TITLE";
    String K_ARTICLE_LINK = "K_ARTICLE_LINK";
    String K_IS_COLLECT = "K_IS_COLLECT";
    String K_IS_COLLECT_PAGE = "K_IS_COLLECT_PAGE";
    String K_IS_COMMON_SITE = "K_IS_COMMON_SITE";

    String K_SEARCH_KEYWORD = "k_search_keyword";

    //welfare页面
    String K_WELFARE_PHOTO = "K_WELFARE_PHOTO";
    String K_WELFARE_POSITION = "K_WELFARE_POSITION";

    //knowledge 页面
    String K_KNOWLEDGE_DATA = "K_KNOWLEDGE_DATA";
    String K_KNOWLEDGE_ID = "K_KNOWLEDGE_ID";


    //=========================================================================

    //V
    int V_ADDRESS_EDIT_ADD = 1001;//地址新增
    int V_ADDRESS_EDIT_EDIT = 1002;//地址编辑
    int FLAG_IMAGE_FROM_ALBUM = 60017;
    int FLAG_IMAGE_FROM_CAMERA = 60018;
    int FLAG_IMAGE_CUTTING = 60019;
    int FLAG_IMAGE_CUTTING2 = 60020;
    int FLAG_IMAGE_video = 60021;
    int FLAG_IMAGE_video2 = 60022;

}
