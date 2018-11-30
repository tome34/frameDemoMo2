package com.example.tome.module_shop_mall.presenter;

import com.example.tome.core.base.mvp.BasePresenter;
import com.example.tome.module_shop_mall.contract.ArticleDetailContract;

/**
 * @Created by TOME .
 * @时间 2018/6/4 11:54
 * @描述 ${文章详情的presenter}
 */

public class ArticleDetailPresenter extends BasePresenter<ArticleDetailContract.View, ArticleDetailContract.Model> implements ArticleDetailContract.Presenter{
    @Override
    public void getArticleData() {

    }

    @Override
    protected ArticleDetailContract.Model createModel() {
        return null;
    }
}
