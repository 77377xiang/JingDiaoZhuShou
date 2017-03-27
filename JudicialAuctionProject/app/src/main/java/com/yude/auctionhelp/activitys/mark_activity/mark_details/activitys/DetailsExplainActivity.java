package com.yude.auctionhelp.activitys.mark_activity.mark_details.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yude.auctionhelp.R;
import com.yude.auctionhelp.base.BaseActivity;

/**
 *  余款缴纳证明
 */
public class DetailsExplainActivity extends BaseActivity {

    TextView tile_tv;
    ImageView l_title_iv,r_title_iv;


    @Override
    public int getContentViewId() {
        return R.layout.activity_details_explain;
    }

    @Override
    protected void initViews(Bundle bundle) {

    }

    @Override
    protected void initData() {
        initTitle();
    }

    private  void  initTitle(){
        tile_tv = (TextView) findViewById(R.id.tile_tv);
        l_title_iv = (ImageView) findViewById(R.id.l_title_iv);
        r_title_iv = (ImageView) findViewById(R.id.r_title_iv);
        tile_tv.setText("余款缴付证明");
        l_title_iv.setImageResource(R.mipmap.h_fanhui);
        l_title_iv.setVisibility(View.VISIBLE);
        r_title_iv.setVisibility(View.INVISIBLE);
        tile_tv.setTextColor(0Xff000000);
        l_title_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



}
