package com.yude.auctionhelp.activitys.waitfor_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.readystatesoftware.viewbadger.BadgeView;
import com.yude.auctionhelp.R;
import com.yude.auctionhelp.activitys.NotepadActivity;
import com.yude.auctionhelp.adapter.EditTypePagerAdapter;
import com.yude.auctionhelp.base.BaseActivity;
import com.yude.auctionhelp.fragment.callfragment.home_data.EditAlivingRoomFragment;
import com.yude.auctionhelp.fragment.callfragment.home_data.EditEnvironmentalFragment;
import com.yude.auctionhelp.fragment.callfragment.home_data.EditHomeOutFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 房屋 编辑完成的展示
 */
public class HomeNewActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tv6)
    TextView tv6;
    @BindView(R.id.tv7)
    TextView tv7;
    @BindView(R.id.tv8)
    TextView tv8;


    @BindView(R.id.tv1_b)
    TextView tv1_b;
    @BindView(R.id.tv2_b)
    TextView tv2_b;
    @BindView(R.id.tv3_b)
    TextView tv3_b;
    @BindView(R.id.tv4_b)
    TextView tv4_b;
    @BindView(R.id.tv5_b)
    TextView tv5_b;
    @BindView(R.id.tv6_b)
    TextView tv6_b;
    @BindView(R.id.tv7_b)
    TextView tv7_b;
    @BindView(R.id.tv8_b)
    TextView tv8_b;

   @BindView(R.id.ll_8)
    LinearLayout  ll_8;


    @BindView(R.id.ll_1)
    LinearLayout  ll_1;


    ViewPager type_vp;
    List<Fragment> typeFragments = new ArrayList<>();

    TextView tile_tv, l_title_tv, r_title_tv;
    ImageView l_title_iv, r_title_iv;

    BadgeView isShowUnpublished_bv;
    EditTypePagerAdapter adapter;
    @Override
    public int getContentViewId() {
        return R.layout.activity_home_new;
    }


    @Override
    protected void initViews(Bundle bundle) {
        type_vp = (ViewPager) findViewById(R.id.type_vp);
        addFragment();
        adapter = new EditTypePagerAdapter(getSupportFragmentManager(),typeFragments,HomeNewActivity.this);
        type_vp.setAdapter(adapter);
        initTitle();
        initTab();
        type_vp.setOffscreenPageLimit(7);// 设置缓存个数
        viewPgaerListener();
    }

    //向viewpager 添加 数据源
    private void addFragment() {
        typeFragments.add(new EditEnvironmentalFragment()); // 环境
        typeFragments.add(new EditHomeOutFragment()); //  房屋外景
        typeFragments.add(new EditAlivingRoomFragment()); // 客厅
        typeFragments.add(new EditEnvironmentalFragment());
        typeFragments.add(new EditEnvironmentalFragment());
        typeFragments.add(new EditEnvironmentalFragment());
        typeFragments.add(new EditEnvironmentalFragment());
        typeFragments.add(new EditEnvironmentalFragment());

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
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.l_title_iv:
                finish();
                break;
            case R.id.r_title_iv:
                startActivity(new Intent(HomeNewActivity.this, NotepadActivity.class));
                break;

        }

    }

    private void showBadgeView_ReleaseComplete(View view) {
        //http://www.th7.cn/Program/Android/201606/874990.shtml
        isShowUnpublished_bv = new BadgeView(this, view);
        //  BadgeView badgeView = new BadgeView(getContext(), view);  // 将需要设置角标的View 传递进去
        isShowUnpublished_bv.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);// 设置在右上角
        // isShowUnpublished_bv.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);// 设置在右上角
        isShowUnpublished_bv.setTextSize(9);// 设置文本大小
        isShowUnpublished_bv.setText("50"); // 设置要显示的文本
        isShowUnpublished_bv.show();// 将角标显示出来

        // badgeView.toggle();//打开关闭或者角标
        // badgeView.setTextColor(Color.BLUE);//角标内文字颜色
        // badgeView.setBadgeBackgroundColor(Color.YELLOW);//角标背景颜色
        // badgeView.setTextSize(12);//角标内数字大小
        //badgeView.toggle(true);//默认动画效果

    }


    // viewPagr滑动监听
    private void  viewPgaerListener(){

        type_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               // Log.e("positionOffsetPixels","我是滑动像素"+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

                if(position>3){
                    // 获取最顶端的布局空间焦点
                    ll_8.setFocusable(true);
                    ll_8.setFocusableInTouchMode(true);
                    ll_8.requestFocus();

                }else {
                    ll_1.setFocusable(true);
                    ll_1.setFocusableInTouchMode(true);
                    ll_1.requestFocus();
                }

                switch (position) {
                    case 0:
                        tv1.setTextColor(0xffc82015);
                        tv2.setTextColor(0xffb0b0b0);
                        tv3.setTextColor(0xffb0b0b0);
                        tv4.setTextColor(0xffb0b0b0);
                        tv5.setTextColor(0xffb0b0b0);
                        tv6.setTextColor(0xffb0b0b0);
                        tv7.setTextColor(0xffb0b0b0);
                        tv8.setTextColor(0xffb0b0b0);



                        break;

                    case 1:

                        tv1.setTextColor(0xffb0b0b0);
                        tv2.setTextColor(0xffc82015);
                        tv3.setTextColor(0xffb0b0b0);
                        tv4.setTextColor(0xffb0b0b0);
                        tv5.setTextColor(0xffb0b0b0);
                        tv6.setTextColor(0xffb0b0b0);
                        tv7.setTextColor(0xffb0b0b0);
                        tv8.setTextColor(0xffb0b0b0);

                        break;
                    case 2:
                        tv1.setTextColor(0xffb0b0b0);
                        tv2.setTextColor(0xffb0b0b0);
                        tv3.setTextColor(0xffc82015);
                        tv4.setTextColor(0xffb0b0b0);
                        tv5.setTextColor(0xffb0b0b0);
                        tv6.setTextColor(0xffb0b0b0);
                        tv7.setTextColor(0xffb0b0b0);
                        tv8.setTextColor(0xffb0b0b0);

                        break;
                    case 3:

                        tv1.setTextColor(0xffb0b0b0);
                        tv2.setTextColor(0xffb0b0b0);
                        tv3.setTextColor(0xffb0b0b0);
                        tv4.setTextColor(0xffc82015);
                        tv5.setTextColor(0xffb0b0b0);
                        tv6.setTextColor(0xffb0b0b0);
                        tv7.setTextColor(0xffb0b0b0);
                        tv8.setTextColor(0xffb0b0b0);

                        break;
                    case 4:
                        tv1.setTextColor(0xffb0b0b0);
                        tv2.setTextColor(0xffb0b0b0);
                        tv3.setTextColor(0xffb0b0b0);
                        tv4.setTextColor(0xffb0b0b0);
                        tv5.setTextColor(0xffc82015);
                        tv6.setTextColor(0xffb0b0b0);
                        tv7.setTextColor(0xffb0b0b0);
                        tv8.setTextColor(0xffb0b0b0);


                        break;
                    case 5:

                        tv1.setTextColor(0xffb0b0b0);
                        tv2.setTextColor(0xffb0b0b0);
                        tv3.setTextColor(0xffb0b0b0);
                        tv4.setTextColor(0xffb0b0b0);
                        tv5.setTextColor(0xffb0b0b0);
                        tv6.setTextColor(0xffc82015);
                        tv7.setTextColor(0xffb0b0b0);
                        tv8.setTextColor(0xffb0b0b0);

                        break;
                    case 6:
                        tv1.setTextColor(0xffb0b0b0);
                        tv2.setTextColor(0xffb0b0b0);
                        tv3.setTextColor(0xffb0b0b0);
                        tv4.setTextColor(0xffb0b0b0);
                        tv5.setTextColor(0xffb0b0b0);
                        tv6.setTextColor(0xffb0b0b0);
                        tv7.setTextColor(0xffc82015);
                        tv8.setTextColor(0xffb0b0b0);


                        break;
                    case 7:
                        tv1.setTextColor(0xffb0b0b0);
                        tv2.setTextColor(0xffb0b0b0);
                        tv3.setTextColor(0xffb0b0b0);
                        tv4.setTextColor(0xffb0b0b0);
                        tv5.setTextColor(0xffb0b0b0);
                        tv6.setTextColor(0xffb0b0b0);
                        tv7.setTextColor(0xffb0b0b0);
                        tv8.setTextColor(0xffc82015);


                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    private void initTab() {
        tv1.setTextColor(0xffc82015);
        tv2.setTextColor(0xffb0b0b0);
        tv3.setTextColor(0xffb0b0b0);
        tv4.setTextColor(0xffb0b0b0);
        tv5.setTextColor(0xffb0b0b0);
        tv6.setTextColor(0xffb0b0b0);
        tv7.setTextColor(0xffb0b0b0);
        tv8.setTextColor(0xffb0b0b0);

        showBadgeView_ReleaseComplete(tv1_b);
        showBadgeView_ReleaseComplete(tv2_b);
        showBadgeView_ReleaseComplete(tv3_b);
        showBadgeView_ReleaseComplete(tv4_b);
        showBadgeView_ReleaseComplete(tv5_b);


    }


    @OnClick({R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7, R.id.tv8})
    void click(View v) {
        switch (v.getId()) {
            //标的
            case R.id.tv1:
                tv1.setTextColor(0xffc82015);
                tv2.setTextColor(0xffb0b0b0);
                tv3.setTextColor(0xffb0b0b0);
                tv4.setTextColor(0xffb0b0b0);
                tv5.setTextColor(0xffb0b0b0);
                tv6.setTextColor(0xffb0b0b0);
                tv7.setTextColor(0xffb0b0b0);
                tv8.setTextColor(0xffb0b0b0);
                type_vp.setCurrentItem(0);

                break;

            case R.id.tv2:
                tv1.setTextColor(0xffb0b0b0);
                tv2.setTextColor(0xffc82015);
                tv3.setTextColor(0xffb0b0b0);
                tv4.setTextColor(0xffb0b0b0);
                tv5.setTextColor(0xffb0b0b0);
                tv6.setTextColor(0xffb0b0b0);
                tv7.setTextColor(0xffb0b0b0);
                tv8.setTextColor(0xffb0b0b0);
                type_vp.setCurrentItem(1);
                break;

            case R.id.tv3:
                tv1.setTextColor(0xffb0b0b0);
                tv2.setTextColor(0xffb0b0b0);
                tv3.setTextColor(0xffc82015);
                tv4.setTextColor(0xffb0b0b0);
                tv5.setTextColor(0xffb0b0b0);
                tv6.setTextColor(0xffb0b0b0);
                tv7.setTextColor(0xffb0b0b0);
                tv8.setTextColor(0xffb0b0b0);
                type_vp.setCurrentItem(2);
                break;

            case R.id.tv4:
                tv1.setTextColor(0xffb0b0b0);
                tv2.setTextColor(0xffb0b0b0);
                tv3.setTextColor(0xffb0b0b0);
                tv4.setTextColor(0xffc82015);
                tv5.setTextColor(0xffb0b0b0);
                tv6.setTextColor(0xffb0b0b0);
                tv7.setTextColor(0xffb0b0b0);
                tv8.setTextColor(0xffb0b0b0);
                type_vp.setCurrentItem(3);
                break;
            case R.id.tv5:
                tv1.setTextColor(0xffb0b0b0);
                tv2.setTextColor(0xffb0b0b0);
                tv3.setTextColor(0xffb0b0b0);
                tv4.setTextColor(0xffb0b0b0);
                tv5.setTextColor(0xffc82015);
                tv6.setTextColor(0xffb0b0b0);
                tv7.setTextColor(0xffb0b0b0);
                tv8.setTextColor(0xffb0b0b0);
                type_vp.setCurrentItem(4);
                break;
            case R.id.tv6:
                tv1.setTextColor(0xffb0b0b0);
                tv2.setTextColor(0xffb0b0b0);
                tv3.setTextColor(0xffb0b0b0);
                tv4.setTextColor(0xffb0b0b0);
                tv5.setTextColor(0xffb0b0b0);
                tv6.setTextColor(0xffc82015);
                tv7.setTextColor(0xffb0b0b0);
                tv8.setTextColor(0xffb0b0b0);
                type_vp.setCurrentItem(5);
                break;
            case R.id.tv7:

                tv1.setTextColor(0xffb0b0b0);
                tv2.setTextColor(0xffb0b0b0);
                tv3.setTextColor(0xffb0b0b0);
                tv4.setTextColor(0xffb0b0b0);
                tv5.setTextColor(0xffb0b0b0);
                tv6.setTextColor(0xffb0b0b0);
                tv7.setTextColor(0xffc82015);
                tv8.setTextColor(0xffb0b0b0);
                type_vp.setCurrentItem(6);
                break;
            case R.id.tv8:

                tv1.setTextColor(0xffb0b0b0);
                tv2.setTextColor(0xffb0b0b0);
                tv3.setTextColor(0xffb0b0b0);
                tv4.setTextColor(0xffb0b0b0);
                tv5.setTextColor(0xffb0b0b0);
                tv6.setTextColor(0xffb0b0b0);
                tv7.setTextColor(0xffb0b0b0);
                tv8.setTextColor(0xffc82015);
                type_vp.setCurrentItem(7);
                break;


        }
    }


}