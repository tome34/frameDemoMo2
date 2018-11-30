package com.example.tome.module_common.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.tome.projectCore.adapter.CommViewHolder;
import com.example.tome.projectCore.adapter.CommonAdapter;
import com.example.tome.core.util.L;
import com.example.tome.module_common.R;
import com.example.customview.widget.dialog.tipDialog.TipDialogView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TipDialogActivity extends AppCompatActivity {

    private ListView mListView;
    private TipDialogView tipDialog;
    private CommonAdapter<String> mCommonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_dialog);
        mListView = (ListView)findViewById(R.id.listview);
        initListView();
    }

    private void initListView() {
        String[] listItems = new String[]{
            "dialog提示框",
            "成功提示类型提示框",
            "失败提示类型提示框",
            "信息提示类型提示框",
            "单独图片类型提示框",
            "单独文字类型提示框",
            "自定义内容提示框"
        };
        List<String> data = new ArrayList<>();

        Collections.addAll(data, listItems);
        //adapter的通用写法
        mCommonAdapter = new CommonAdapter<String>(TipDialogActivity.this, data, R.layout.simple_list_item) {
            @Override
            public void convert(CommViewHolder holder,String item) {
                holder.setText(R.id.text, item);
            }
        };
        mListView.setAdapter(mCommonAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,View view,int position,long id) {
                L.d("点击了item哈哈"+position);
                if (position == 0) {
                            return;
                            //无
                            //tipDialog = new TipDialogView.Builder(TipDialogActivity.this).setIconType(TipDialogView.Builder.ICON_TYPE_LOADING).setTipWord("正在加载").create();
                        }else if (position == 1){
                            tipDialog = new TipDialogView.Builder(TipDialogActivity.this)
                                .setIconType(TipDialogView.Builder.ICON_TYPE_SUCCESS)
                                .setTipWord("发送成功")
                                .create();
                        }else if (position == 2){
                            tipDialog = new TipDialogView.Builder(TipDialogActivity.this)
                                .setIconType(TipDialogView.Builder.ICON_TYPE_FAIL)
                                .setTipWord("发送失败")
                                .create();
                        }else if (position == 3){
                            tipDialog = new TipDialogView.Builder(TipDialogActivity.this)
                                .setIconType(TipDialogView.Builder.ICON_TYPE_INFO)
                                .setTipWord("请勿重复操作")
                                .create();
                        }else if (position == 4){
                            tipDialog = new TipDialogView.Builder(TipDialogActivity.this)
                                .setIconType(TipDialogView.Builder.ICON_TYPE_SUCCESS)
                                .create();
                        }else if (position == 5){
                            tipDialog = new TipDialogView.Builder(TipDialogActivity.this)
                                .setTipWord("请勿重复操作")
                                .create();
                        }else if (position == 6){
                            tipDialog = new TipDialogView.CustomBuilder(TipDialogActivity.this)
                                .setContent(R.layout.tipdialog_custom)
                                .create();
                        }else if (position == 7){
                            tipDialog = new TipDialogView.Builder(TipDialogActivity.this)
                                .setIconType(TipDialogView.Builder.ICON_TYPE_LOADING)
                                .setTipWord("正在加载")
                                .create();
                        }
                        //显示
                        tipDialog.show();

                        //1.5秒后隐藏
                        mListView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                tipDialog.dismiss();
                            }
                        }, 1500);
                       }

        });
    }
}
