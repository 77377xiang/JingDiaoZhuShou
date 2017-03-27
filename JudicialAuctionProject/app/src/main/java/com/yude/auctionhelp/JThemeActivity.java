package com.yude.auctionhelp;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.yude.auctionhelp.activitys.MyActivity;
import com.yude.auctionhelp.adapter.ThemePagerAdapter;
import com.yude.auctionhelp.base.BaseActivity;
import com.yude.auctionhelp.fragment.callfragment.CompleteFragment;
import com.yude.auctionhelp.fragment.callfragment.TransferFragment;
import com.yude.auctionhelp.fragment.callfragment.WaitForFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hexiang on 17/3/23.
 */
public class JThemeActivity extends BaseActivity implements View.OnClickListener {

    TextView tile_tv, l_title_tv, r_title_tv;
    ImageView l_title_iv, r_title_iv;

    TextView tv_1, tv_2;
    ViewPager pager;
    ThemePagerAdapter adapter;
    private PopupWindow mPopWindow;
    TextView antique_tv, factory_home_tv, car_tv, home_tv, all_tv;
    LinearLayout linearLayout; //弹框布局
    int tv_2_width=300;
    private List<Fragment> fraglist = new ArrayList<>();
    String uil = "http://pic6.huitu.com/res/20130116/84481_20130116142820494200_1.jpg";
    CompleteFragment comFragment;

    @Override
    public int getContentViewId() {
        return R.layout.activity_theme;
    }

    @Override
    protected void initViews(Bundle bundle) {
        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);
        pager = (ViewPager) findViewById(R.id.pager_vp);
        initTitle();
        defauitTab();
        initPWWidth();
        fraglist.add(new WaitForFragment());
        fraglist.add(comFragment=new CompleteFragment());

        adapter = new ThemePagerAdapter(getSupportFragmentManager(), fraglist, pager, this);
        pager.setAdapter(adapter);

        pager.setOffscreenPageLimit(2);
        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        r_title_tv.setOnClickListener(this);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tv_1.setTextColor(0xffC82015);
                        tv_2.setTextColor(0xff000000);

                        break;
                    case 1:
                        tv_1.setTextColor(0xff000000);
                        tv_2.setTextColor(0xffC82015);

                        break;

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }



    private void initTitle() {
        tile_tv = (TextView) findViewById(R.id.tile_tv);
        l_title_iv = (ImageView) findViewById(R.id.l_title_iv);
        r_title_iv = (ImageView) findViewById(R.id.r_title_iv);
        l_title_tv = (TextView) findViewById(R.id.l_title_tv);
        r_title_tv = (TextView) findViewById(R.id.r_title_tv);
        tile_tv.setText("尽调助手");
        l_title_iv.setImageResource(R.mipmap.h_fanhui);
        l_title_iv.setVisibility(View.GONE);
        r_title_iv.setVisibility(View.GONE);
        l_title_tv.setVisibility(View.VISIBLE);
        r_title_tv.setVisibility(View.VISIBLE);
        l_title_tv.setText("房产");
        r_title_tv.setText("我的");

    }


    // 默认初始化tab
    private void defauitTab() {
        tv_1.setText("等待尽调");
        tv_2.setText("尽调完成");
        tv_1.setTextColor(0xffC82015);
        tv_2.setTextColor(0xff000000);
    }


    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_1:
                pager.setCurrentItem(0);
                tv_1.setTextColor(0xffC82015);
                tv_2.setTextColor(0xff000000);
                break;

            case R.id.tv_2:
                 pager.setCurrentItem(1);
                tv_1.setTextColor(0xff000000);
                tv_2.setTextColor(0xffC82015);
                showPopupWindow();
                break;


            case R.id.r_title_tv:
                Toast.makeText(JThemeActivity.this, "我的", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(JThemeActivity.this, MyActivity.class));

                break;

            case R.id.all_tv:
                pager.setCurrentItem(1);
                mPopWindow.dismiss();

                tv_1.setTextColor(0xff000000);
                tv_2.setTextColor(0xffC82015);
                tv_2.setText("尽调完成");
                Toast.makeText(JThemeActivity.this, "尽调完成", Toast.LENGTH_SHORT).show();
                break;
            case R.id.home_tv:
                pager.setCurrentItem(1);
                mPopWindow.dismiss();
                tv_1.setTextColor(0xff000000);
                tv_2.setTextColor(0xffC82015);
                tv_2.setText("待审核");
                Toast.makeText(JThemeActivity.this, "待审核", Toast.LENGTH_SHORT).show();
                break;
            case R.id.car_tv:
                pager.setCurrentItem(1);
                mPopWindow.dismiss();
                tv_1.setTextColor(0xff000000);
                tv_2.setTextColor(0xffC82015);
                tv_2.setText("已退回");
                Toast.makeText(JThemeActivity.this, "已退回", Toast.LENGTH_SHORT).show();
                break;
            case R.id.factory_home_tv:
                pager.setCurrentItem(1);
                mPopWindow.dismiss();
                tv_1.setTextColor(0xff000000);
                tv_2.setTextColor(0xffC82015);
                tv_2.setText("上拍");
                Toast.makeText(JThemeActivity.this, " 上拍", Toast.LENGTH_SHORT).show();
                break;
            case R.id.antique_tv:
                mPopWindow.dismiss();
                tv_1.setTextColor(0xff000000);
                tv_2.setTextColor(0xffC82015);
                tv_2.setText("交接");
                comFragment.refreshJiaoJie();
                break;


        }
    }

    private void initPWWidth(){

        //  获取宽度  异步执行

        tv_2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tv_2_width = tv_2.getWidth();
            }
        });

    }

    private void showPopupWindow() {

        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popupwindow_help_type, null);
        mPopWindow = new PopupWindow(contentView, tv_2_width, 180);
        //mPopWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        /*final PopupWindow popup = new PopupWindow(contentView, ViewPager.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT, false);
        WindowManager manager=(WindowManager) getSystemService(getContext().WINDOW_SERVICE);
        int xpos=manager.getDefaultDisplay().getWidth()/2-popup.getWidth()/2;
        popup.setOutsideTouchable(true);
        popup.showAsDropDown(appendIV,xpos, 0);
*/

        //设置各个控件的点击响应
        antique_tv = (TextView) contentView.findViewById(R.id.antique_tv);
        factory_home_tv = (TextView) contentView.findViewById(R.id.factory_home_tv);
        car_tv = (TextView) contentView.findViewById(R.id.car_tv);
        home_tv = (TextView) contentView.findViewById(R.id.home_tv);
        all_tv = (TextView) contentView.findViewById(R.id.all_tv);
        linearLayout = (LinearLayout) contentView.findViewById(R.id.ll);
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_theme, null);
        // mPopWindow.showAtLocation(rootview, Gravity.RIGHT, 0, 0);
        //点击外部消失
        mPopWindow.setFocusable(true);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        //控制透明度
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
        //显示
        mPopWindow.showAsDropDown(tv_2);
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams p = getWindow().getAttributes();
                p.alpha = 1f;
                getWindow().setAttributes(p);
            }
        });

        linearLayout.getBackground().setAlpha(0);       //设置透明度
        all_tv.setOnClickListener(this);
        factory_home_tv.setOnClickListener(this);
        car_tv.setOnClickListener(this);
        home_tv.setOnClickListener(this);
        antique_tv.setOnClickListener(this);
    }


    //屏幕变暗
    private void setScreenBgDarken() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        lp.dimAmount = 0.5f;
        getWindow().setAttributes(lp);
    }


}
