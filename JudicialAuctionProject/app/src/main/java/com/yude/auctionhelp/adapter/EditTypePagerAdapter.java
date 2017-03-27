package com.yude.auctionhelp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by hexiang on 17/3/26.
 */
public class EditTypePagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;
    private  Context mContext;

    public EditTypePagerAdapter(FragmentManager fm,List<Fragment> fragments, Context context) {
        super(fm);
        this.mFragments = fragments;
        this.mContext = context;

    }



    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }



    @Override
    public int getCount() {
        return mFragments.size();
    }



}
