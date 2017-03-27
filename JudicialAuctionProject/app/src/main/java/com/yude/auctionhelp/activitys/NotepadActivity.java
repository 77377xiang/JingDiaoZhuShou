package com.yude.auctionhelp.activitys;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yude.auctionhelp.R;
import com.yude.auctionhelp.base.BaseActivity;

/**
 * 记事本
 */
public class NotepadActivity  extends BaseActivity{

    TextView tile_tv, l_title_tv, r_title_tv;
    ImageView l_title_iv, r_title_iv;
    RecyclerView show_rv;

    @Override
    public int getContentViewId() {
        return R.layout.activity_notepad;
    }

    @Override
    protected void initViews(Bundle bundle) {
        initTitle();

    }

    @Override
    protected void initData() {

    }


    private  void  initTitle(){

        tile_tv = (TextView) findViewById(R.id.tile_tv);
        l_title_iv = (ImageView) findViewById(R.id.l_title_iv);
        r_title_iv = (ImageView) findViewById(R.id.r_title_iv);
        l_title_tv = (TextView) findViewById(R.id.l_title_tv);
        r_title_tv = (TextView) findViewById(R.id.r_title_tv);
        tile_tv.setText("记事本");

        l_title_iv.setImageResource(R.mipmap.h_fanhui);
        l_title_iv.setVisibility(View.VISIBLE);
        r_title_iv.setVisibility(View.INVISIBLE);
        l_title_tv.setVisibility(View.GONE);
        r_title_tv.setVisibility(View.GONE);
        // l_title_tv.setText("房产");
        // r_title_tv.setText("我的");
        l_title_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });


    }



}
