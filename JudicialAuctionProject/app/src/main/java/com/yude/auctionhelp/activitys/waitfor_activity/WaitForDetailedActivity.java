package com.yude.auctionhelp.activitys.waitfor_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yude.auctionhelp.R;
import com.yude.auctionhelp.activitys.waitfor_activity.edit_home.EditTaxationActivity;
import com.yude.auctionhelp.adapter.RecycleHolder;
import com.yude.auctionhelp.adapter.markadapter.Pull_Mark_releaseComplteRecyclerAdapter;
import com.yude.auctionhelp.base.BaseActivity;
import com.yude.auctionhelp.entity.TestWaitfor;
import com.yude.auctionhelp.utils.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * 等待尽调详细
 */
public class WaitForDetailedActivity extends BaseActivity  implements View.OnClickListener{



    RecyclerView catalog_rv;
    Pull_Mark_releaseComplteRecyclerAdapter adapter;
    List<String> recyclerViewData = new ArrayList<>();
    TextView tile_tv, l_title_tv, r_title_tv;
    ImageView l_title_iv, r_title_iv;
    TextView  reason_tv,attention_eara,type_tv;

    @Override
    public int getContentViewId() {
        return R.layout.activity_detailed_waitfor;
    }

    @Override
    protected void initViews(Bundle bundle) {
        catalog_rv = (RecyclerView) findViewById(R.id.catalog_rv);
        reason_tv = (TextView) findViewById(R.id.reason_tv);
        attention_eara = (TextView) findViewById(R.id.attention_eara);
        type_tv = (TextView) findViewById(R.id.type_tv);

        TestWaitfor testWaitfor = (TestWaitfor) getIntent().getSerializableExtra("key");

        reason_tv.setText(testWaitfor.getTitle());
        attention_eara.setText(testWaitfor.getType());
        type_tv.setText(testWaitfor.getWaitForType());

        initTite();
        initRecyclerViewData();
        catalog_rv.setLayoutManager(new LinearLayoutManager(this));
        catalog_rv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 1, ContextCompat.getColor(this, R.color._b0b0b0)));

        adapter = new Pull_Mark_releaseComplteRecyclerAdapter<String>(this, recyclerViewData, R.layout.item_waitfor_detailed_type) {
            @Override
            public void convert(RecycleHolder holder, String data, int position) {
                holder.setText(R.id.new_tv, recyclerViewData.get(position));
                holder.findView(R.id.number_tv).setVisibility(View.INVISIBLE);

            }
        };



        catalog_rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new Pull_Mark_releaseComplteRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(WaitForDetailedActivity.this,HomeNewActivity.class));
                       // Toast.makeText(WaitForDetailedActivity.this,"房屋信息",Toast.LENGTH_LONG).show();

                        break;
                    case 1:
                        startActivity(new Intent(WaitForDetailedActivity.this,PropertysActivity.class));

                        // Toast.makeText(WaitForDetailedActivity.this,"物业信息",Toast.LENGTH_LONG).show();


                        break;
                    case 2:
                        startActivity(new Intent(WaitForDetailedActivity.this,EditTaxationActivity.class));
                       // Toast.makeText(WaitForDetailedActivity.this,"税费信息",Toast.LENGTH_LONG).show();
                        break;




                }

            }

        });
    }



    private   void  initTite(){

            tile_tv = (TextView) findViewById(R.id.tile_tv);
            l_title_iv = (ImageView) findViewById(R.id.l_title_iv);
            r_title_iv = (ImageView) findViewById(R.id.r_title_iv);
            l_title_tv = (TextView) findViewById(R.id.l_title_tv);
            r_title_tv = (TextView) findViewById(R.id.r_title_tv);
            tile_tv.setText("套间3室2厅2卫");

            l_title_iv.setImageResource(R.mipmap.h_fanhui);
            l_title_iv.setVisibility(View.VISIBLE);
            r_title_iv.setVisibility(View.VISIBLE);
            l_title_tv.setVisibility(View.GONE);
            r_title_tv.setVisibility(View.GONE);
           // l_title_tv.setText("房产");
           // r_title_tv.setText("我的");
        l_title_iv.setOnClickListener(this);
        r_title_iv.setOnClickListener(this);


    }


    @Override
    protected void initData() {

    }


    private void initRecyclerViewData() {
        recyclerViewData.add("房屋信息");
        recyclerViewData.add("物业信息");
        recyclerViewData.add("税费信息");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.l_title_iv :
            finish();
            break;
            case R.id.r_title_iv :

                Toast.makeText(WaitForDetailedActivity.this,"点击了右边图片",Toast.LENGTH_LONG).show();


                break;

        }

    }
}
