package com.yude.auctionhelp.fragment.callfragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yude.auctionhelp.R;
import com.yude.auctionhelp.adapter.RecycleHolder;
import com.yude.auctionhelp.adapter.markadapter.Pull_Mark_releaseComplteRecyclerAdapter;
import com.yude.auctionhelp.base.BaseFragment;
import com.yude.auctionhelp.entity.Attention;
import com.yude.auctionhelp.entity.TestWaitfor;

import java.util.ArrayList;
import java.util.List;

/**
 * 尽调完成
 */
public class CompleteFragment extends BaseFragment {
    PullLoadMoreRecyclerView waitfor_pmrv;
    private List<TestWaitfor> testWaitfors, testJJWaitfors;
    List<Attention> attentionList; //带审核
    //  Pull_Mark_releaseComplteRecyclerAdapter<TestWaitfor> adapter;
    String uil = "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg";
    private String img = "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1488858167&di=0a6d7668c9ef53ee75873f540b2e6600&src=http://pic.35pic.com/normal/00/55/11/1363360_173955077_2.jpg";

    @Override
    public int getContentViewId() {
        return R.layout.fragment_waitfor;
    }

    @Override
    protected void initViews(Bundle bundle) {
        waitfor_pmrv = (PullLoadMoreRecyclerView) rootView.findViewById(R.id.waitfor_pmrv);
        initData();

        waitfor_pmrv.setLinearLayout();
        setRecyclerAdapter(initAdapter(context, testWaitfors, R.layout.item_pull_waitfor));
        pull(initAdapter(context, testWaitfors, R.layout.item_pull_waitfor));


    }


    // 默认初始化适配器
    public Pull_Mark_releaseComplteRecyclerAdapter<TestWaitfor> initAdapter(Context context, List<TestWaitfor> testWaitfors, int layout) {
        Pull_Mark_releaseComplteRecyclerAdapter<TestWaitfor> adapter = new Pull_Mark_releaseComplteRecyclerAdapter<TestWaitfor>(context, testWaitfors, layout) {
            @Override
            public void convert(RecycleHolder holder, TestWaitfor data, int position) {

                holder.setText(R.id.title_tv, data.getTitle());
                holder.setText(R.id.start_tv, data.getStartEdit());
                holder.setText(R.id.type_tv, data.getType());
                holder.setText(R.id.address_tv, data.getAddress());
                holder.setText(R.id.waitfor_tv, data.getWaitForType());
                holder.findView(R.id.start_tv).setVisibility(View.GONE);

            }
        };

        return adapter;
    }


    // 刷新数据
    private void pull(final Pull_Mark_releaseComplteRecyclerAdapter<TestWaitfor> adapter) {

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


    //  交接 适配器
    public void refreshJiaoJie(Pull_Mark_releaseComplteRecyclerAdapter<TestWaitfor> adapter) {

        if (adapter != null) {
            adapter = null;
        }
        initJJData();
        adapter = new Pull_Mark_releaseComplteRecyclerAdapter<TestWaitfor>(context, testJJWaitfors, R.layout.item_pull_waitfor) {
            @Override
            public void convert(RecycleHolder holder, TestWaitfor data, int position) {

                holder.setText(R.id.title_tv, data.getTitle());
                holder.setText(R.id.start_tv, data.getStartEdit());
                holder.setText(R.id.type_tv, data.getType());
                holder.setText(R.id.address_tv, data.getAddress());
                holder.setText(R.id.waitfor_tv, data.getWaitForType());
                holder.findView(R.id.start_tv).setVisibility(View.GONE);

            }
        };
        waitfor_pmrv.setAdapter(adapter);


    }

    //  交接数据源
    public List<TestWaitfor> initJJData() {
        testJJWaitfors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TestWaitfor testWaitfor = new TestWaitfor();
            testWaitfor.setTitle("测试");
            testWaitfor.setAddress(" 测试");
            testWaitfor.setPhoto(uil);
            testWaitfor.setType("测试");
            testWaitfor.setWaitForType("测试");
            testWaitfor.setStartEdit("测试");
            testJJWaitfors.add(testWaitfor);
        }
        return testJJWaitfors;
    }


    //  尽调完成 适配器
    public void setRecyclerAdapter(Pull_Mark_releaseComplteRecyclerAdapter<TestWaitfor> adapter) {
        adapter = new Pull_Mark_releaseComplteRecyclerAdapter<TestWaitfor>(context, testWaitfors, R.layout.item_pull_waitfor) {
            @Override
            public void convert(RecycleHolder holder, TestWaitfor data, int position) {
                holder.setText(R.id.title_tv, data.getTitle());
                holder.setText(R.id.start_tv, data.getStartEdit());
                holder.setText(R.id.type_tv, data.getType());
                holder.setText(R.id.address_tv, data.getAddress());
                holder.setText(R.id.waitfor_tv, data.getWaitForType());
                holder.findView(R.id.start_tv).setVisibility(View.GONE);
            }
        };
        waitfor_pmrv.setAdapter(adapter);
    }


    // 尽调完成数据源
    public List<TestWaitfor> initData() {
        testWaitfors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TestWaitfor testWaitfor = new TestWaitfor();
            testWaitfor.setTitle("杭州西溪北湾一区一单元");
            testWaitfor.setAddress("杭州市余杭区");
            testWaitfor.setPhoto(uil);
            testWaitfor.setType("房产");
            testWaitfor.setWaitForType("已完成");
            testWaitfor.setStartEdit("开始尽调");
            testWaitfors.add(testWaitfor);
        }
        return testWaitfors;
    }


    // 带审核 适配器


    // 带审核  数据源
    public List<Attention> initData_rv() {
        List<Attention> attentionList = new ArrayList<>();
        Attention attention0;
        attention0 = new Attention();
        attention0.setImgUrl(img);
        attention0.setTitle("城西银泰" + 1 + "号楼");
        attention0.setLocaiton("浙江杭州市");
        attention0.setEara("面积1" + 1 + "0平方");
        attention0.setLastTime("2017年3月 17:45");
        attention0.setTime("2017年6月3日  8:30");
        attention0.setDelayTime("延时 04:30");
        attention0.setSellState("一拍");
        attention0.setBackReason("证件不齐全");
        attention0.setDistanceStart(Attention.Distance.No3);

        attentionList.add(attention0);


        Attention attention;
        attention = new Attention();
        attention.setImgUrl(img);
        attention.setTitle("城西银泰" + 2 + "号楼");
        attention.setLocaiton("浙江杭州市");
        attention.setEara("面积1" + 2 + "0平方");
        attention.setLastTime("2017年3月 17:45");
        attention.setTime("2017年6月3日  8:30");
        attention.setDelayTime("延时 04:30");
        attention.setSellState("二拍");
        attention.setBackReason("房产有纠纷");
        attention.setDistanceStart(Attention.Distance.No3);
        attentionList.add(attention);




        Attention attention1;
        attention1 = new Attention();
        attention1.setImgUrl(img);
        attention1.setTitle("城西银泰" + 4 + "号楼");
        attention1.setLocaiton("浙江杭州市");
        attention1.setEara("面积1" + 4 + "0平方");
        attention1.setLastTime("2017年3月 17:45");
        attention1.setTime("2017年6月3日  8:30");
        attention1.setDelayTime("延时 04:30");
        attention1.setSellState("变拍");
        attention1.setBackReason("厂房不明确");
        attention1.setDistanceStart(Attention.Distance.No3);
        attentionList.add(attention1);



        Attention attention3;
        attention3 = new Attention();
        attention3.setImgUrl(img);
        attention3.setTitle("城西银泰" + 3 + "号楼");
        attention3.setLocaiton("浙江杭州市");
        attention3.setEara("面积1" + 3 + "0平方");
        attention3.setLastTime("2017年3月 17:45");
        attention3.setTime("2017年6月3日  8:30");
        attention3.setDelayTime("延时 04:30");
        attention3.setSellState("一拍");
        attention1.setBackReason("厂房已塌陷");
        attention3.setDistanceStart(Attention.Distance.No3);
        attentionList.add(attention3);

        return attentionList;
    }




    //  尽调完成 适配器
    public void setReleaseCompleteRecyclerAdapter(Pull_Mark_releaseComplteRecyclerAdapter<Attention> adapter) {
        initData_rv();
        adapter = new Pull_Mark_releaseComplteRecyclerAdapter<Attention>(context, attentionList, R.layout.item_pull_waitfor) {
            @Override
            public void convert(RecycleHolder holder, Attention data, int position) {

                holder.setImg(R.id.attention_iv, data.getImgUrl());  // 图片
                holder.setText(R.id.attention_title, data.getTitle());  // 标题
                holder.setText(R.id.attention_location, data.getLocaiton());  //位置
                holder.setText(R.id.attention_eara, data.getEara()); //  面积
                holder.setText(R.id.attention_time, data.getTime());  // 时间
                holder.findView(R.id.time_tv).setVisibility(View.GONE);
                holder.findView(R.id.number_p_tv).setVisibility(View.GONE);



            }
        };
        waitfor_pmrv.setAdapter(adapter);
    }






}
