package com.yude.auctionhelp.activitys.mark_activity.mark_details;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yude.auctionhelp.R;
import com.yude.auctionhelp.activitys.mark_activity.mark_details.activitys.DetailsFavoriteNewActivity;
import com.yude.auctionhelp.activitys.mark_activity.mark_details.activitys.DetailsMustKnowActivity;
import com.yude.auctionhelp.activitys.mark_activity.mark_details.activitys.DetailsNoticeActivity;
import com.yude.auctionhelp.adapter.markadapter.DirectoriesTextAdapter;
import com.yude.auctionhelp.adapter.markadapter.ReleaseComplete_PagerAdapter;
import com.yude.auctionhelp.base.BaseActivity;
import com.yude.auctionhelp.entity.dsf.NoScrollListViewDate;
import com.yude.auctionhelp.utils.ImageUtil;
import com.yude.auctionhelp.views.view.NoScrollListView;
import com.yude.auctionhelp.activitys.mark_activity.mark_details.activitys.DetailsMarkBriefActivity;

import java.util.ArrayList;
import java.util.List;

/**
 *   通过的详情
 */
public  class UnpublishedDateilsActivity extends BaseActivity implements View.OnClickListener{

    TextView pager_r_text_tv,
            pager_r_time_tv,
            page_b_text_tv,
            pager_b_time_tv,
            position_tv,
            Determine_tv,
            start_money_tv,
            money_number_tv,
            people_money_tv,
            people_money_number_tv,
            in_number_tv,
            see_number_tv,
            importance_number_tv,
            start_money_two_tv,
            add_money_tv,
            guarantee_money_tv,
            type_tv,
            quick_pople_tv,
            implement_number_tv,
            corporate_name_tv,
            Division_TV;


    LinearLayout pager_r_time_ll,
            pager_b_time_ll,
            w_ll,
            button_ll,
            quick_ll;
    FrameLayout pople_number_fl;


    Button Release_btn,back_btn;

    ImageView Notice_iv,know_iv,information_iv;
    NoScrollListView directories_nss_LL;
    List<String> directoriesText = new ArrayList<>();
    DirectoriesTextAdapter adapter;

    boolean isGZ;//  关注与没关注的触摸标识
    private ViewPager mViewPager;
    private List<ImageView> mImageViewList = new ArrayList<>();
    private int currentPosition = 1;
    private int dotPosition = 0;
    private int prePosition = 0;
    private LinearLayout mLinearLayoutDot;
    private List<String> urlList; // viewpager 数据源
    String uil1 ="http://s3.ifengimg.com/2014/09/16/7fa036d73307d21e397ed89b86975138.jpg";
    String uil2 ="http://img4.imgtn.bdimg.com/it/u=3079581662,1609145946&fm=23&gp=0.jpg";
    String uil3="http://www.wygfw.com/kindeditor/attached/image/20150316/20150316131042614261.jpg";
    String uil4 = "http://img1.emfc.net.cn/emfcimg/userfiles/image/20150310/1014564635ae2bd2d18459.jpg";
    String uil5 = "http://pic1.nipic.com/2008-09-02/200892224140147_2.jpg";


    TextView tile_tv;
    ImageView l_title_iv, r_title_iv;


    private List<ImageView> mImageViewDotList= new ArrayList<>();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                mViewPager.setCurrentItem(currentPosition, false);
            }
        }
    };




    @Override
    public int getContentViewId() {
        return R.layout.activity_dateils;
    }




    //  设置自动播放
    private void autoPlay() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                while (true) {
                    SystemClock.sleep(3000);
                    currentPosition++;
                    handler.sendEmptyMessage(1);
                }
            }
        }.start();
    }


    // 网络加载 图片
    private void init() {
        urlList = new ArrayList<>();
        urlList = new ArrayList<String>();
        //  mImageViewDotList = new ArrayList();
        urlList.add(uil1);
        urlList.add(uil2);
        urlList.add(uil3);
        urlList.add(uil4);
        urlList.add(uil5);
        ImageView imageView;

        for (int i = 0; i < urlList.size() + 2; i++) {
            if (i == 0) {   //判断当i=0为该处的ImageView设置最后一张图片作为背景
                imageView = new ImageView(this);
                //imageView.setBackgroundResource(images[images.length - 1]);

                ImageUtil.setImgwithOptions(urlList.get(urlList.size() - 1), imageView);
                mImageViewList.add(imageView);
            } else if (i == urlList.size() + 1) {   //判断当i=images.length+1时为该处的ImageView设置第一张图片作为背景
                imageView = new ImageView(this);

                ImageUtil.setImgwithOptions(urlList.get(0), imageView);
                // imageView.setBackgroundResource(images[0]);

                mImageViewList.add(imageView);
            } else {  //其他情况则为ImageView设置images[i-1]的图片作为背景
                imageView = new ImageView(this);

                // imageView.setBackgroundResource(images[i - 1]);
                ImageUtil.setImgwithOptions(urlList.get(i - 1), imageView);
                mImageViewList.add(imageView);
            }
        }


    }

    //   改写  设置轮播小圆点
    private void setDotD() {
        //  设置LinearLayout的子控件的宽高，这里单位是像素。
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
        params.rightMargin = 20;
        //  for循环创建images.length个ImageView（小圆点）


        for (int i = 0; i < urlList.size(); i++) {
            ImageView imageViewDot = new ImageView(this);
            imageViewDot.setLayoutParams(params);
            //  设置小圆点的背景为暗红图片
            imageViewDot.setBackgroundResource(R.drawable.red_dot_night);
            mLinearLayoutDot.addView(imageViewDot);
            mImageViewDotList.add(imageViewDot);
        }
        //设置第一个小圆点图片背景为红色
        mImageViewDotList.get(dotPosition).setBackgroundResource(R.drawable.red_dot);
    }


    //   改写 给view pager 设置小原点
    private void setViewPagerD() {
        ReleaseComplete_PagerAdapter adapter = new ReleaseComplete_PagerAdapter(mImageViewList, this,urlList);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(currentPosition);
        //页面改变监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {    //判断当切换到第0个页面时把currentPosition设置为images.length,即倒数第二个位置，小圆点位置为length-1
                    currentPosition = urlList.size();
                    dotPosition = urlList.size() - 1;
                } else if (position == urlList.size() + 1) {    //当切换到最后一个页面时currentPosition设置为第一个位置，小圆点位置为0
                    currentPosition = 1;
                    dotPosition = 0;
                } else {
                    currentPosition = position;
                    dotPosition = position - 1;
                }
                //  把之前的小圆点设置背景为暗红，当前小圆点设置为红色
                mImageViewDotList.get(prePosition).setBackgroundResource(R.drawable.red_dot_night);
                mImageViewDotList.get(dotPosition).setBackgroundResource(R.drawable.red_dot);
                prePosition = dotPosition;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //当state为SCROLL_STATE_IDLE即没有滑动的状态时切换页面
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    mViewPager.setCurrentItem(currentPosition, false);
                }
            }
        });
    }






    private void setOnItemClickListenerNoScrollListView() {
        directories_nss_LL.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(UnpublishedDateilsActivity.this, DetailsNoticeActivity.class));

                        Toast.makeText(UnpublishedDateilsActivity.this, "竞买公告", Toast.LENGTH_LONG).show();

                        break;
                    case 1:
                        startActivity(new Intent(UnpublishedDateilsActivity.this, DetailsMustKnowActivity.class));
                        Toast.makeText(UnpublishedDateilsActivity.this, "竞买须知", Toast.LENGTH_LONG).show();

                        break;
                    case 2:
                        startActivity(new Intent(UnpublishedDateilsActivity.this, DetailsMarkBriefActivity.class));

                        Toast.makeText(UnpublishedDateilsActivity.this, "竞买标的物介绍", Toast.LENGTH_LONG).show();

                        break;
                    case 3:
                        Toast.makeText(UnpublishedDateilsActivity.this, "特殊消息", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(UnpublishedDateilsActivity.this, DetailsFavoriteNewActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });
    }


    private void initViewsAll() {
        mViewPager = (ViewPager) findViewById(R.id.vp_main);
        mLinearLayoutDot = (LinearLayout) findViewById(R.id.ll_main_dot);
        pager_r_time_ll = (LinearLayout) findViewById(R.id.pager_r_time_ll);
        pager_b_time_ll = (LinearLayout) findViewById(R.id.pager_b_time_ll);
        quick_ll = (LinearLayout) findViewById(R.id.quick_ll);
        pople_number_fl = (FrameLayout) findViewById(R.id.pople_number_fl);
        button_ll =(LinearLayout) findViewById(R.id.button_ll);
        w_ll = (LinearLayout) findViewById(R.id.w_ll);

        pager_r_text_tv = (TextView) findViewById(R.id.pager_r_text_tv);
        pager_r_time_tv = (TextView) findViewById(R.id.pager_r_time_tv);
        page_b_text_tv = (TextView) findViewById(R.id.page_b_text_tv);
        pager_b_time_tv = (TextView) findViewById(R.id.pager_b_time_tv);
        position_tv = (TextView) findViewById(R.id.position_tv);
        Determine_tv = (TextView) findViewById(R.id.Determine_tv);
        start_money_tv = (TextView) findViewById(R.id.start_money_tv);
        money_number_tv = (TextView) findViewById(R.id.money_number_tv);
        people_money_tv = (TextView) findViewById(R.id.people_money_tv);
        people_money_number_tv = (TextView) findViewById(R.id.people_money_number_tv);
        in_number_tv = (TextView) findViewById(R.id.in_number_tv);
        see_number_tv = (TextView) findViewById(R.id.see_number_tv);
        importance_number_tv = (TextView) findViewById(R.id.importance_number_tv);
        start_money_two_tv = (TextView) findViewById(R.id.ensure_money_tv);
        add_money_tv = (TextView) findViewById(R.id.add_money_tv);
        guarantee_money_tv = (TextView) findViewById(R.id.guarantee_money_tv);
        type_tv = (TextView) findViewById(R.id.type_tv);
        quick_pople_tv = (TextView) findViewById(R.id.quick_pople_tv);
        implement_number_tv = (TextView) findViewById(R.id.implement_number_tv);
        corporate_name_tv = (TextView) findViewById(R.id.corporate_name_tv);
        pager_r_text_tv = (TextView) findViewById(R.id.pager_r_text_tv);
        Division_TV = (TextView) findViewById(R.id.Division_TV);

        Release_btn = (Button) findViewById(R.id.Release_btn);//
        back_btn = (Button) findViewById(R.id.back_btn);//

        Notice_iv = (ImageView) findViewById(R.id.Notice_iv);//
        know_iv = (ImageView) findViewById(R.id.know_iv);//
        information_iv = (ImageView) findViewById(R.id.information_iv);//
        directories_nss_LL = (NoScrollListView) findViewById(R.id.directories_nss_LL);

        // pager_r_time_ll .setVisibility(View.GONE);// 隐藏右边时间
        quick_ll.setVisibility(View.VISIBLE); //显示优先购买人布局
        back_btn.setVisibility(View.GONE); //隐藏回退按钮
        Division_TV.setVisibility(View.GONE); //按键直接的分割
        pople_number_fl.setVisibility(View.VISIBLE);  // 显示 报名 围观 关注 布局
        Release_btn.setText("撤回");
        pager_r_text_tv.setText("距拍卖开始");
        button_ll.setVisibility(View.GONE);  //隐藏按键布局


        // 获取最顶端的布局空间焦点
        w_ll.setFocusable(true);
        w_ll.setFocusableInTouchMode(true);
        w_ll.requestFocus();


        // initDirectoriesDate();//  初始化 目录
        NoScrollListViewDate scrollListViewDate = new NoScrollListViewDate();
        scrollListViewDate.initDirectoriesDate(directoriesText);
        adapter = new DirectoriesTextAdapter(directoriesText,this);
        directories_nss_LL.setAdapter(adapter);
    }

    @Override
    protected void initViews(Bundle bundle) {
        initViewsAll();
        //mViewPager = (ViewPager) findViewById(R.id.vp_main);
        //mLinearLayoutDot = (LinearLayout) findViewById(R.id.ll_main_dot);
        initTitle();
        init();
        autoPlay();
        setDotD();
        setViewPagerD();
        viewClickListener();
        setOnItemClickListenerNoScrollListView();
    }

    @Override
    protected void initData() {

    }


    private  void viewClickListener(){
        l_title_iv.setOnClickListener(this);
        back_btn.setOnClickListener(this);
        Release_btn.setOnClickListener(this);
        know_iv.setOnClickListener(this);
        Notice_iv.setOnClickListener(this);
        information_iv.setOnClickListener(this);
        r_title_iv.setOnClickListener(this);

    }


    private void initDirectoriesDate() {
        directoriesText = new ArrayList<>();
        // directoriesText.add("竞价成功确认书");
        // directoriesText.add("余款缴付证明");
        // directoriesText.add("缴费清单");
        directoriesText.add("竞买公告");
        directoriesText.add("竞买须知");
        directoriesText.add("标的物介绍");
        directoriesText.add("特殊消息");

        adapter = new DirectoriesTextAdapter(directoriesText,this);
        directories_nss_LL.setAdapter(adapter);

    }

    private void initTitle() {
        tile_tv = (TextView) findViewById(R.id.tile_tv);
        l_title_iv = (ImageView) findViewById(R.id.l_title_iv);
        r_title_iv = (ImageView) findViewById(R.id.r_title_iv);
        tile_tv.setText("国颢伺辅");
        l_title_iv.setImageResource(R.mipmap.h_fanhui);
        l_title_iv.setVisibility(View.VISIBLE);
        r_title_iv.setImageResource(R.mipmap.h_guanzhu_t);
        r_title_iv.setVisibility(View.VISIBLE);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.l_title_iv :
               finish();
            break;

            case R.id.r_title_iv :
                if (!isGZ){
                    r_title_iv.setImageResource(R.mipmap.r_gaunzhu_t);
                    isGZ=true;
                }else {
                    r_title_iv.setImageResource(R.mipmap.h_guanzhu_t);
                    isGZ=false;
                }
break;
            case R.id.Release_btn :
                Toast.makeText(this,"撤回",Toast.LENGTH_LONG).show();

                break;
            case R.id.back_btn :
                Toast.makeText(this,"回退",Toast.LENGTH_LONG).show();

                break;

            case R.id.Notice_iv :
                Toast.makeText(this,"竞买公告",Toast.LENGTH_LONG).show();

                break;

            case R.id.know_iv :
                Toast.makeText(this,"竞买须知",Toast.LENGTH_LONG).show();

                break;

            case R.id.information_iv :
                Toast.makeText(this,"标物介绍",Toast.LENGTH_LONG).show();

                break;


        }



    }
}
