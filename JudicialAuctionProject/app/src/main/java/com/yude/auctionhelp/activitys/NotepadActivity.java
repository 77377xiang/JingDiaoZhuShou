package com.yude.auctionhelp.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yude.auctionhelp.R;
import com.yude.auctionhelp.activitys.waitfor_activity.edit_home.HomeEnvironmentalActivity;
import com.yude.auctionhelp.adapter.AddRecycleHolder;
import com.yude.auctionhelp.adapter.AddRecyclerAdapter;
import com.yude.auctionhelp.adapter.myadapter.CodeGridViewAdapter;
import com.yude.auctionhelp.base.BaseActivity;
import com.yude.auctionhelp.entity.response.TestComplete;
import com.yude.auctionhelp.views.view.CustomeGridView;

import java.util.ArrayList;
import java.util.List;
/**
 * 记事本
 */
public class NotepadActivity extends BaseActivity {

    TextView tile_tv, l_title_tv, r_title_tv;
    ImageView l_title_iv, r_title_iv;
    List<TestComplete> mList = new ArrayList<>();
    private List<String> listPhoto = new ArrayList<>(); // 内层数据
    RecyclerView  mRecyclerView;

    AddRecyclerAdapter addRecyclerAdapter;
    @Override
    public int getContentViewId() {
        return R.layout.activity_notepad;
    }

    @Override
    protected void initViews(Bundle bundle) {
        mRecyclerView = (RecyclerView) findViewById(R.id.show_rv);
        initTitle();
        initNoteData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //  注意没有数据可用String  不可以用null
        addRecyclerAdapter = new AddRecyclerAdapter<TestComplete, String, Integer>(this,
                R.layout.item_rv_edit_show_complete,mList,
                0, null,
                R.layout.code_add_footer, R.drawable.test_jiaohao1) {
            @Override
            public void convertBody(AddRecycleHolder holder, TestComplete data, int position) {
                holder.setText(R.id.showDeitText, data.getEditText());
                holder.setText(R.id.keTing_tv, data.getKeTing());
                // 需要隐藏单选框
                holder.findView(R.id.checkbox_type_ll).setVisibility(View.GONE);

                CustomeGridView show_cgw = holder.findView(R.id.show_cgw);
                CodeGridViewAdapter codeGridViewAdapter = new CodeGridViewAdapter(NotepadActivity.this, listPhoto);
                show_cgw.setAdapter(codeGridViewAdapter);
            }

            @Override
            public void convertFooter(AddRecycleHolder holder, Integer data, int position) {
                super.convertFooter(holder, data, position);
                ImageView image = holder.findView(R.id.add_edit_iv);
                image.setImageResource(data);

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(NotepadActivity.this, "点击了编辑", Toast.LENGTH_LONG).show();
                        // startActivity(new Intent(getContext(),HomeAddPicture2Activity.class));
                        startActivityForResult(new Intent(NotepadActivity.this, HomeEnvironmentalActivity.class), 1);

                    }
                });
            }
        };

        mRecyclerView.setAdapter(addRecyclerAdapter);
    }





    @Override
    protected void initData() {

    }


    private void initTitle() {

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




    private void initNoteData() {
        TestComplete  testWaitfor = new TestComplete();
        testWaitfor.setEditText("我是描述文字");
        testWaitfor.setKeTing("我是笔记");
        mList.add(testWaitfor);

    }






}
