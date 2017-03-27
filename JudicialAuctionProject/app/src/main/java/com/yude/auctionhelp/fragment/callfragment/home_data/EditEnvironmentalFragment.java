package com.yude.auctionhelp.fragment.callfragment.home_data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yude.auctionhelp.R;
import com.yude.auctionhelp.activitys.waitfor_activity.HomeAddPictureActivity;
import com.yude.auctionhelp.adapter.RecycleHolder;
import com.yude.auctionhelp.adapter.markadapter.Pull_Mark_releaseComplteRecyclerAdapter;
import com.yude.auctionhelp.base.BaseFragment;
import com.yude.auctionhelp.entity.TestWaitfor;
import com.yude.auctionhelp.entity.response.TestComplete;
import com.yude.auctionhelp.utils.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * 编辑环境
 */
public class EditEnvironmentalFragment extends BaseFragment implements View.OnClickListener {
    RecyclerView show_rv;
    ImageView add_edit_iv;
    List<TestComplete> testCompletes = new ArrayList<>();
    Pull_Mark_releaseComplteRecyclerAdapter<TestComplete> adapter;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_edit;
    }


    @Override
    protected void initViews(Bundle bundle) {
        add_edit_iv = (ImageView) rootView.findViewById(R.id.add_edit_iv);
        show_rv = (RecyclerView) rootView.findViewById(R.id.show_rv);
        initData();
        show_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        show_rv.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, ContextCompat.getColor(getContext(), R.color._b0b0b0)));

        adapter = new Pull_Mark_releaseComplteRecyclerAdapter<TestComplete>(context, testCompletes, R.layout.item_rv_edit_show_complete) {
            @Override
            public void convert(RecycleHolder holder, TestComplete data, int position) {


            }
        };
        show_rv.setAdapter(adapter);
        add_edit_iv.setOnClickListener(this);

    }


    private void initData() {

        TestComplete testComplete = new TestComplete();
        testComplete.setPhotoUil("SSS");
        testComplete.setEditText("");
        testCompletes.add(testComplete);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.add_edit_iv:
                Toast.makeText(getContext(), "点击了编辑", Toast.LENGTH_LONG).show();
                startActivity(new Intent(getContext(),HomeAddPictureActivity.class));

                break;
        }

    }
}
