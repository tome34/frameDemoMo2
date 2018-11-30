package com.example.tome.module_shop_mall.fagment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import com.example.tome.projectCore.base.mvc.BaseEmptyVcFragment;
import com.example.tome.projectCore.helper.ImageLoaderHelper;
import com.example.tome.core.net.common_callback.INetCallback;
import com.example.tome.core.util.ConvertUtils;
import com.example.tome.core.util.L;
import com.example.tome.core.util.ObjectUtils;
import com.example.tome.core.widget.CircularImageView;
import com.example.tome.projectCore.bean.BaseObj;
import com.fec.core.router.arouter.IntentKV;
import com.example.tome.module_shop_mall.R;
import com.example.tome.module_shop_mall.R2;
import com.example.tome.module_shop_mall.activity.KnowledgeDetailActivity;
import com.example.tome.module_shop_mall.api.ApiService;
import com.example.tome.module_shop_mall.api.ModelVcService;
import com.example.tome.module_shop_mall.arouter.RouterCenter;
import com.example.tome.module_shop_mall.bean.KnowledgeSystemBean;
import com.example.tome.module_shop_mall.contract.KnowledgeSystemContract;
import com.example.tome.module_shop_mall.widget.RotateAnimation;
import io.reactivex.Observable;
import java.util.List;

//public class KnowledgeSystemFragment extends BaseVpFragment<KnowledgeSystemPresenter> implements KnowledgeSystemContract.View, RotateAnimation.InterpolatedTimeListener, Animation.AnimationListener {
public class KnowledgeSystemFragment extends BaseEmptyVcFragment implements KnowledgeSystemContract.View, RotateAnimation.InterpolatedTimeListener, Animation.AnimationListener {

    @BindView(R2.id.title_back)
    TextView mTitleBack;
    @BindView(R2.id.title_content_text)
    TextView mTitleContentText;
    @BindView(R2.id.title_right_text)
    TextView mTitleRightText;
    @BindView(R2.id.title_right_icon)
    ImageView mTitleRightIcon;
    @BindView(R2.id.v_title_container)
    LinearLayout mVTitleContainer;
    @BindView(R2.id.rl_title_bar_content)
    RelativeLayout mRlTitleBarContent;
    @BindView(R2.id.layout_knowledge_system_List)
    LinearLayout mLayoutKnowledgeSystemList;
    @BindView(R2.id.scrollView)
    ScrollView mScrollView;
    @BindView(R2.id.civ_welfare_one)
    CircularImageView mCivWelfareOne;
    @BindView(R2.id.civ_welfare_two)
    CircularImageView mCivWelfareTwo;

    /**
     * true:正面  false:反面
     */
    private boolean  rotateState = true;
    private boolean             enableRefresh;
    private boolean isAnimation;
    //轮播时间ms
    private static final int AUTO_PLAY_DURATION = 4000;

    List<KnowledgeSystemBean> mKnowledgeSystemList ;

    private String              mCircleImg1 = "http://ww1.sinaimg.cn/large/0065oQSqly1fsb0lh7vl0j30go0ligni.jpg";
    private String              mCircleImg2 = "http://ww1.sinaimg.cn/large/0065oQSqly1fs8tym1e8ej30j60ouwhz.jpg";
    private String              mCircleImg3 = "http://ww1.sinaimg.cn/large/0065oQSqly1fs8u1joq6fj30j60orwin.jpg";
    private String              mCircleImg4 = "http://ww1.sinaimg.cn/large/0065oQSqly1fs7l8ijitfj30jg0shdkc.jpg";

    //handler
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                //旋转动画
                if (isAnimation){
                    initAnimation();
                }

            }
        }
    };

//    @Override
//    public KnowledgeSystemPresenter createPresenter() {
//        return new KnowledgeSystemPresenter();
//    }


    @Override
    protected int getLayout() {
        return R.layout.mall_fragment_knowledge_system;
    }

    @Override
    protected void initTitle() {
        mTitleContentText.setText("知识体系");

    }

    @Override
    protected void initView() {
        //初始化圆形图片
        initCircularImage();
        //获取数据
       // mPresenter.getKnowledgeSystemData();
        loadData();
    }

    @Override
    public ViewGroup getViewGroup(View view) {
        return (ViewGroup) view.findViewById(com.example.tome.core.R.id.scrollView);
    }

    @Override
    public void onResume() {
        super.onResume();
        isAnimation = true;
    }

    protected void loadData() {
        addDisposable(ModelVcService.getRemoteData(false, mView, new ModelVcService.MethodSelect<List<KnowledgeSystemBean>>() {
            @Override
            public Observable<BaseObj<List<KnowledgeSystemBean>>> selectM(ApiService service) {
                return service.getKnowledgeHierarchyData();
            }
        }, new INetCallback<List<KnowledgeSystemBean>>() {
            @Override
            public void onSuccess(List<KnowledgeSystemBean> result) {
               showKnowledgeSystem(result);
            }
        }));
    }

    @Override
    public void showLoading() {
        super.showLoading();
        if (mEmptyView != null){
            mEmptyView.showLoading();
        }
    }


    private void initCircularImage() {
        setCivHeight(mCivWelfareOne);
        setCivHeight(mCivWelfareTwo);
        //翻转动画
        initAnimation();
    }

    private void setCivHeight(CircularImageView civWelfare) {
        //设置圆形长度
        ViewGroup.LayoutParams params = civWelfare.getLayoutParams();
        params.height = ((ConvertUtils.getResolutionX(mContext)) - ConvertUtils.dip2px(mContext, 40)) / 2;
        civWelfare.setLayoutParams(params);

        //设置圆形外宽
        civWelfare.setBorderWidth(0);
    }

    /**
     * 翻转动画
     */
    private void initAnimation() {
        enableRefresh = true;
        float             cY         = mCivWelfareOne.getHeight() / 2.0f;
        float             cX1        = (ConvertUtils.getResolutionX(mContext) - ConvertUtils.dip2px(mContext, 40)) / 4;
        RotateAnimation rotateAnim = new RotateAnimation(cX1, cY, RotateAnimation.ROTATE_DECREASE);
        rotateAnim.setFillAfter(true);
        rotateAnim.setInterpolatedTimeListener(this);
        mCivWelfareOne.startAnimation(rotateAnim);
        mCivWelfareTwo.startAnimation(rotateAnim);
        rotateAnim.setAnimationListener(this);
    }



    @Override
    public void showKnowledgeSystem(List<KnowledgeSystemBean> result) {
        L.d("数据成功" + result.size());
        mKnowledgeSystemList = result;
        //圆形图美女
        initCircleAd();
        //显示知识体系列表
        initViewData();
    }

    private void initViewData() {
        mLayoutKnowledgeSystemList.removeAllViews();

        for (int i = 0 ; i < mKnowledgeSystemList.size(); i ++){
            View inflate = getLayoutInflater().inflate(R.layout.mall_item_knowledge_list, null);
            View       view01     = inflate.findViewById(R.id.view01);
            View       view02     = inflate.findViewById(R.id.view02);
            View       vSpace     = inflate.findViewById(R.id.v_space);
            ImageView  imgView    = (ImageView) inflate.findViewById(R.id.imgView);
            TextView   tvText     = (TextView) inflate.findViewById(R.id.tvText);
            TextView   tvTextItem = (TextView) inflate.findViewById(R.id.tvTextItem);

            if (i == 0) {
                view01.setVisibility(View.INVISIBLE);
                vSpace.setVisibility(View.INVISIBLE);
            } else if (i == (mKnowledgeSystemList.size() - 1)) {
                view02.setVisibility(View.INVISIBLE);
            } else if (i != 0) {
                imgView.setImageResource(R.mipmap.icon_circular_unselect);
            }

            tvText.setText(mKnowledgeSystemList.get(i).getName());
            List<KnowledgeSystemBean.ChildrenBean> children = mKnowledgeSystemList.get(i).getChildren();
            StringBuffer nameStr = new StringBuffer();
            //增强for
            for (KnowledgeSystemBean.ChildrenBean childItem : children){
                nameStr.append(childItem.getName() +"\n"+" ; ");
            }
            tvTextItem.setText(Html.fromHtml("<font color='#666666'>"+ nameStr +"</font>"));
            //设置item的点击事件
            int finalI = i;
            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, KnowledgeDetailActivity.class);
                    intent.putExtra(IntentKV.K_KNOWLEDGE_DATA, mKnowledgeSystemList.get(finalI));
                    startActivity(intent);
                }
            });

            mLayoutKnowledgeSystemList.addView(inflate, i);
        }


    }

    private void initCircleAd() {
        if (ObjectUtils.isNotEmpty(mCircleImg1)) {
            //加载第一张
            ImageLoaderHelper.getInstance().load(mContext, mCircleImg1, mCivWelfareOne);
        }
        if (ObjectUtils.isNotEmpty(mCircleImg3)) {
            //加载第三张
            ImageLoaderHelper.getInstance().load(mContext, mCircleImg3, mCivWelfareTwo);
        }

        /**
         * 第一张圆图点击
         */
        mCivWelfareOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rotateState ){
                    RouterCenter.toWelfareHome();
                }else if (! rotateState){
                    RouterCenter.toWelfareHome();
                }
            }
        });

        /**
         * 第二张圆图点击
         */
        mCivWelfareTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rotateState ){
                    RouterCenter.toWelfareHome();
                }else if (! rotateState){
                    RouterCenter.toWelfareHome();
                }
            }
        });
    }

    /**
     * 监听到翻转进度过半时，更新图片显示内容。
     * @param interpolatedTime
     */
    @Override
    public void interpolatedTime(float interpolatedTime) {
        if (enableRefresh && interpolatedTime > 0.5f) {
            if (rotateState) {
                //反面
                if (ObjectUtils.isNotEmpty(mCircleImg1)) {
                    ImageLoaderHelper.getInstance().load(mContext, mCircleImg1, mCivWelfareOne);
                }
                if (ObjectUtils.isNotEmpty(mCircleImg4)) {
                    ImageLoaderHelper.getInstance().load(mContext, mCircleImg4, mCivWelfareTwo);
                }
                rotateState = false;
            } else {
                //正面
                if (ObjectUtils.isNotEmpty(mCircleImg2)) {
                    ImageLoaderHelper.getInstance().load(mContext, mCircleImg2, mCivWelfareOne);

                }
                if (ObjectUtils.isNotEmpty(mCircleImg3)) {
                    ImageLoaderHelper.getInstance().load(mContext, mCircleImg3, mCivWelfareTwo);
                }
                rotateState = true;
            }
            enableRefresh = false;
        }
    }

    /**
     * 翻转动画监听
     * @param animation
     */
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        mHandler.sendEmptyMessageDelayed(1 , AUTO_PLAY_DURATION);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        L.d("onHiddenChanged:"+hidden);
        if (hidden){
            isAnimation = false;
        }else{
            isAnimation = true;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        isAnimation = false;
        L.d("onHiddenChanged:+");
    }


}
