package com.yude.auctionhelp.fragment.callfragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yude.auctionhelp.R;
import com.yude.auctionhelp.adapter.RecycleHolder;
import com.yude.auctionhelp.adapter.markadapter.Pull_Mark_releaseComplteRecyclerAdapter;
import com.yude.auctionhelp.base.BaseFragment;
import com.yude.auctionhelp.entity.TestWaitfor;

import java.util.ArrayList;
import java.util.List;

/**
 *  交接
 */
public class TransferFragment extends BaseFragment {

    PullLoadMoreRecyclerView waitfor_pmrv;
    private List<TestWaitfor> testWaitfors;
    Pull_Mark_releaseComplteRecyclerAdapter<TestWaitfor> adapter;
    String uil = "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg";


    @Override
    public int getContentViewId() {
        return R.layout.fragment_waitfor;
    }


    @Override
    protected void initViews(Bundle bundle) {

        waitfor_pmrv = (PullLoadMoreRecyclerView) rootView.findViewById(R.id.waitfor_pmrv);
        initData();
        waitfor_pmrv.setLinearLayout();
        adapter = new Pull_Mark_releaseComplteRecyclerAdapter<TestWaitfor>(context, testWaitfors, R.layout.item_pull_waitfor) {
            @Override
            public void convert(RecycleHolder holder, TestWaitfor data, int position) {

                holder.setText(R.id.title_tv,data.getTitle());
                holder.setText(R.id.start_tv,data.getStartEdit());
                holder.setText(R.id.type_tv,data.getType());
                holder.setText(R.id.address_tv,data.getAddress());
                holder.setText(R.id.waitfor_tv,data.getWaitForType());
                holder.findView(R.id.start_tv).setVisibility(View.GONE);

            }
        };
        waitfor_pmrv.setAdapter(adapter);


        waitfor_pmrv.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        adapter.notifyDataSetChanged();
                        waitfor_pmrv.setPullLoadMoreCompleted();
                    }

                }, 1000);
            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    public void run() {

                        adapter.notifyDataSetChanged();
                        waitfor_pmrv.setPullLoadMoreCompleted();

                    }
                }, 1000);


            }
        });

    }


    private void initData() {

        testWaitfors = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            TestWaitfor testWaitfor = new TestWaitfor();
            testWaitfor.setTitle("测试");
            testWaitfor.setAddress(" 测试");
            testWaitfor.setPhoto(uil);
            testWaitfor.setType("测试");
            testWaitfor.setWaitForType("测试");
            testWaitfor.setStartEdit("测试");
            testWaitfors.add(testWaitfor);
        }


    }

}
