package com.yude.auctionhelp.fragment.markfagment.moreFragment;

import com.yude.auctionhelp.R;
import com.yude.auctionhelp.entity.Attention;
import com.yude.auctionhelp.fragment.markfagment.GroupFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 暂缓
 */
public class SuspendedFrgment extends GroupFragment {

    List<Attention> attentionList;
    private String img = "http://img2.imgtn.bdimg.com/it/u=2610254496,4076847534&fm=23&gp=0.jpg";

    @Override
    public int type() {
        return 1;
    }

    @Override
    public List<Attention> initListData() {
        initData_rv();
        return attentionList;
    }

    @Override
    public int itemId() {
        return R.layout.item_groupfragment_nobackground;
    }

    @Override
    public List<Attention> onRefreshDate() {
       // List<Attention> attentionListRefresh = new ArrayList<>();
        attentionList.clear();
        Attention attention;
        for (int i = 0; i < 3; i++) {
            attention = new Attention();
            attention.setImgUrl(img);
            attention.setTitle("城西银泰" + i + "号楼");
            attention.setLocaiton("浙江杭州市");
            attention.setEara("面积5" + i + "0平方");
            attention.setTime("开拍时间:2017年5月1日" + i + ":30");
            attentionList.add(attention);
        }


        return attentionList;
    }

    @Override
    public List<Attention> onLoadMoreDate() {

       // List<Attention> attentionListMore = new ArrayList<>();
        Attention attention;
        for (int i = 0; i < 2; i++) {
            attention = new Attention();
            attention.setImgUrl(img);
            attention.setTitle("滨江花园" + i + "号楼");
            attention.setLocaiton("浙江杭州市");
            attention.setEara("面积1" + i + "0平方");
            attention.setTime("一拍中 加载数据");
            attentionList.add(attention);

        }

        return attentionList;
    }



//初始化数据
    private void initData_rv() {
       // attentionList =  initListData();

        attentionList = new ArrayList<>();
        Attention attention;
        for (int i = 0; i < 10; i++) {
            attention = new Attention();
            attention.setImgUrl(img);
            attention.setTitle("未来科技成" + i + "号楼");
            attention.setLocaiton("浙江杭州市");
            attention.setEara("面积1" + i + "0平方");
            attention.setTime("2017年7月3日  12:30");
            attentionList.add(attention);
        }


    }



}
