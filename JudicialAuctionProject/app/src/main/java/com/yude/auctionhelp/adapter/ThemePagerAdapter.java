package com.yude.auctionhelp.adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.yude.auctionhelp.R;
import com.yude.auctionhelp.fragment.callfragment.CompleteFragment;
import com.yude.auctionhelp.fragment.callfragment.TransferFragment;

import java.util.List;

/**
 * Created by hexiang on 17/3/23.
 */
public class ThemePagerAdapter extends FragmentPagerAdapter {


    private List<Fragment> fragments;
    ViewPager mPager;
    Context mContext;

    public ThemePagerAdapter(FragmentManager fm, List<Fragment> fragments, ViewPager mPager, Context context) {
        super(fm);
        this.fragments = fragments;
        this.mPager = mPager;
        this.mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }


    private int mChildCount = 0;

   @Override
    public void notifyDataSetChanged() {
        // 重写这个方法，取到子Fragment的数量，用于下面的判断，以执行多少次刷新
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }


//    @Override
//    public int getItemPosition(Object object) {
////        if (mChildCount > 0) {
////            // 这里利用判断执行若干次不缓存，刷新
////            mChildCount--;
////            // 返回这个是强制ViewPager不缓存，每次滑动都刷新视图
////            return POSITION_NONE;
////        }
////        // 这个则是缓存不刷新视图
////        return super.getItemPosition(0);
//        return POSITION_NONE;
//    }



}
