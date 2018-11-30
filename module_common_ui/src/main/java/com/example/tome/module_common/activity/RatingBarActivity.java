package com.example.tome.module_common.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tome.core.util.L;
import com.example.tome.module_common.R;
import com.example.customview.widget.RatingBar;
import com.example.customview.widget.RatingBarV2;

public class RatingBarActivity extends AppCompatActivity implements View.OnClickListener {

    private RatingBar mRatingBar;
    private RatingBarV2 mRatingBarV2;
    private EditText mEtNumber;
    private TextView mBtSubmit;
    private float mNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);
        initView();
    }

    private void initView() {
        mRatingBar = (RatingBar) findViewById(R.id.ratingbar);
        mRatingBarV2 = (RatingBarV2) findViewById(R.id.ratingbar_v2);
        mEtNumber = (EditText) findViewById(R.id.et_number);
        mBtSubmit = (TextView) findViewById(R.id.bt_submit);

        mBtSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_submit){
            String numberStr = mEtNumber.getText().toString();
            try {
                mNumber = Float.valueOf(numberStr);
            }catch (Exception e){
                mNumber = 0 ;
                L.d(e);
            }
            mRatingBar.setStar(mNumber);
            mRatingBarV2.setStarMark(mNumber);
        }
    }
}
