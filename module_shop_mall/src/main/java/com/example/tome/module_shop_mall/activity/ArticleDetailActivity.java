package com.example.tome.module_shop_mall.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import butterknife.BindView;
import com.example.tome.core.base.mvc.BaseVcActivity;
import com.example.tome.core.util.widgetUtils.WebViewInitUtils;
import com.fec.core.router.arouter.IntentKV;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;

/**
 * 文章详情页
 */
public class ArticleDetailActivity extends BaseVcActivity {

    @BindView(R2.id.common_toolbar_title)
    TextView mTitle;
    @BindView(R2.id.common_toolbar)
    Toolbar mToolbar;
    @BindView(R2.id.article_detail_web_view)
    WebView mWebView;

    private Bundle bundle;
    private int articleId;
    private String articleLink;
    private String title;
    private boolean isCollect;
    private boolean isCommonSite;
    private boolean isCollectPage;

    @Override
    protected int getLayoutId() {
        return R.layout.mall_activity_home_detail;
    }

    @Override
    protected void initTitle() {
        Intent intent = getIntent();
        //断言判断 assert
        assert intent != null ;

        //取代原本的ActionBar
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        //状态栏
       // mImmersionBar.titleBar(R.id.common_toolbar).init();
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.colorPrimary).init();
        String title = intent.getStringExtra(IntentKV.K_ARTICLE_TITLE);
        mTitle.setText(title);
        articleLink = intent.getStringExtra(IntentKV.K_ARTICLE_LINK);
        articleId = intent.getIntExtra(IntentKV.K_ARTICLE_ID , 0);
        isCommonSite = intent.getBooleanExtra(IntentKV.K_IS_COMMON_SITE, false);
        isCollect = intent.getBooleanExtra(IntentKV.K_IS_COLLECT , false);
        isCollectPage = intent.getBooleanExtra(IntentKV.K_IS_COLLECT_PAGE, false);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void initView() {
        WebViewInitUtils.init(mActivity, mWebView);

        mWebView.loadUrl(articleLink);

       // mWebView.loadDataWithBaseURL(null, WebViewInitUtils.getHtmlData(productDetail), "text/html", "utf-8", null);

        //点击拦截 true表示拦截, false表示不拦截
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //view.loadUrl(articleLink);
                return false;

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
