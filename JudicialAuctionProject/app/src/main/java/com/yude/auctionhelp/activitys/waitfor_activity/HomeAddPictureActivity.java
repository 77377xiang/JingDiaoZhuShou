package com.yude.auctionhelp.activitys.waitfor_activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yude.auctionhelp.R;
import com.yude.auctionhelp.adapter.CommAdapter;
import com.yude.auctionhelp.adapter.RecycleHolder;
import com.yude.auctionhelp.adapter.ViewHolder;
import com.yude.auctionhelp.adapter.markadapter.Pull_Mark_releaseComplteRecyclerAdapter;
import com.yude.auctionhelp.base.BaseActivity;
import com.yude.auctionhelp.entity.response.TestItem;
import com.yude.auctionhelp.utils.GridDivider;
import com.yude.auctionhelp.utils.RecycleViewDivider;
import com.yude.auctionhelp.views.view.CustomeGridView;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * 编辑 外景图片
 */
public class HomeAddPictureActivity extends BaseActivity implements View.OnClickListener {

    private static final int REQUEST_IMAGE = 2;
    protected static final int REQUEST_STORAGE_READ_ACCESS_PERMISSION = 101;
    protected static final int REQUEST_STORAGE_WRITE_ACCESS_PERMISSION = 102;

    private ArrayList<String> mSelectPath;


    List<TestItem> recyclerViewData = new ArrayList<>();
    List<Integer> bitmaps = new ArrayList<>();
    RecyclerView catalog_rv, photo_rv;
    CustomeGridView photo_lv;

    Pull_Mark_releaseComplteRecyclerAdapter adapter;
    TextView tile_tv, l_title_tv, r_title_tv;
    ImageView l_title_iv, r_title_iv;
    ImageView addPhoto_iv;
    TextView hint_tv;

    @Override
    public int getContentViewId() {
        return R.layout.activity_home_add_picture;
    }

    @Override
    protected void initViews(Bundle bundle) {
        photo_lv = (CustomeGridView) findViewById(R.id.photo_lv);
        catalog_rv = (RecyclerView) findViewById(R.id.catalog_rv);
        photo_rv = (RecyclerView) findViewById(R.id.photo_rv);
        addPhoto_iv = (ImageView) findViewById(R.id.addPhoto_iv);
        hint_tv = (TextView) findViewById(R.id.hint_tv);
        initTite();
        setAdapterCatalog_rv();  // 设置条目
       // setAdapterPhoto_rv(); // 设置图片
        setListViewAdapter();
        adapter.setOnItemClickListener(new Pull_Mark_releaseComplteRecyclerAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(HomeAddPictureActivity.this, HomeNewActivity.class));
                        Toast.makeText(HomeAddPictureActivity.this, "房屋类别", Toast.LENGTH_LONG).show();

                        break;
                    case 1:
                        Toast.makeText(HomeAddPictureActivity.this, "总楼层", Toast.LENGTH_LONG).show();

                        break;
                    case 2:
                        Toast.makeText(HomeAddPictureActivity.this, "所在楼层", Toast.LENGTH_LONG).show();


                        break;


                }

            }

        });


        photo_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(bitmaps.size()==i+1){
                    pickImage();
                }
            }
        });

    }


    @Override
    protected void initData() {

    }




    private  void setListViewAdapter(){
        initDataRecyclerViePhoto();
        if (bitmaps.size() > 0 && bitmaps != null) {
            photo_rv.setVisibility(View.VISIBLE);
            hint_tv.setVisibility(View.GONE);
            addPhoto_iv.setVisibility(View.GONE);

            photo_lv.setAdapter(new CommAdapter< Integer>(bitmaps,this,R.layout.item_rv_add_photo) {
                @Override
                public void convert(ViewHolder holder, Integer data, int position) {

                    holder.setImg(R.id.addPhoto_iv, bitmaps.get(position));
                    if (bitmaps.size()==position+1){
                        //holder.setImg(R.id.addPhoto_iv, R.drawable.test_jiaohao1);
                        holder.getView(R.id.delete_iv).setVisibility(View.GONE);
                    }

                }
            });



        }else {

            photo_rv.setVisibility(View.GONE);
            hint_tv.setVisibility(View.VISIBLE);
            addPhoto_iv.setVisibility(View.VISIBLE);
        }






    }




    //给图片设置数据源
    private void setAdapterPhoto_rv() {

        initDataRecyclerViePhoto();

        if (bitmaps.size() > 0 && bitmaps != null) {

            photo_lv.setVisibility(View.VISIBLE);
            hint_tv.setVisibility(View.GONE);
            addPhoto_iv.setVisibility(View.GONE);


            //  设置未网状
            final GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
            //设置颜色分割线
            photo_rv.setLayoutManager(layoutManager);
            photo_rv.addItemDecoration(new GridDivider(this, 20, this.getResources().getColor(R.color._000000)));


            photo_rv.setLayoutManager(layoutManager);
            adapter = new Pull_Mark_releaseComplteRecyclerAdapter<Integer>(this, bitmaps, R.layout.item_rv_add_photo) {
                @Override
                public void convert(RecycleHolder holder, Integer data, int position) {
                    holder.setImageResource(R.id.addPhoto_iv, bitmaps.get(position));
                    if (bitmaps.size()==position+1){
                        holder.setImageResource(R.id.addPhoto_iv, R.drawable.test_jiaohao);

                    }

                }
            };

            photo_rv.setAdapter(adapter);

        } else {

            photo_lv.setVisibility(View.GONE);
            hint_tv.setVisibility(View.VISIBLE);
            addPhoto_iv.setVisibility(View.VISIBLE);
        }


    }


    //  给条目设置数据源
    private void setAdapterCatalog_rv() {
        initRecyclerViewData();
        catalog_rv.setLayoutManager(new LinearLayoutManager(this));
        catalog_rv.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.HORIZONTAL, 1, ContextCompat.getColor(this, R.color._b0b0b0)));

        adapter = new Pull_Mark_releaseComplteRecyclerAdapter<TestItem>(this, recyclerViewData, R.layout.item_waitfor_detailed_type) {
            @Override
            public void convert(RecycleHolder holder, TestItem data, int position) {
                holder.setText(R.id.number_tv, recyclerViewData.get(position).getNumber());
                holder.setText(R.id.new_tv, recyclerViewData.get(position).getTitle());

            }
        };

        catalog_rv.setAdapter(adapter);

    }


    // 初始化 title
    private void initTite() {
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
        addPhoto_iv.setOnClickListener(this);

    }


    //  图片的数据源
    private void initDataRecyclerViePhoto() {

        bitmaps.add(R.drawable.test2);
        bitmaps.add(R.drawable.test2);
        bitmaps.add(R.drawable.test2);
        bitmaps.add(R.drawable.test2);
        bitmaps.add(R.drawable.test2);
        bitmaps.add(R.drawable.test3);
        bitmaps.add(R.drawable.test_jiaohao1);

    }





    // 条目数据
    private void initRecyclerViewData() {
        TestItem testItem = new TestItem();
        testItem.setTitle("房屋类别");
        testItem.setNumber("套间");
        recyclerViewData.add(testItem);

        TestItem testItem2 = new TestItem();
        testItem2.setTitle("总楼层");
        testItem2.setNumber("18");
        recyclerViewData.add(testItem2);


        TestItem testItem3 = new TestItem();
        testItem3.setTitle("所在楼层");
        testItem3.setNumber("9");
        recyclerViewData.add(testItem3);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.l_title_iv:
                finish();
                break;
            case R.id.r_title_iv:

                Toast.makeText(HomeAddPictureActivity.this, "点击了右边图片", Toast.LENGTH_LONG).show();


                break;
            case R.id.addPhoto_iv:
                pickImage();
                Toast.makeText(HomeAddPictureActivity.this, "调取照相 或者选取相册", Toast.LENGTH_LONG).show();

                break;

        }


    }


    private void pickImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN // Permission was added in API Level 16
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission(Manifest.permission.READ_EXTERNAL_STORAGE,
                    getString(R.string.mis_permission_rationale),
                    REQUEST_STORAGE_READ_ACCESS_PERMISSION);
        } else {
            //boolean showCamera = mShowCamera.getCheckedRadioButtonId() == R.id.show;
            boolean showCamera = true;
            int maxNum = 9;

//            if (!TextUtils.isEmpty(mRequestNum.getText())) {
//                try {
//                    maxNum = Integer.valueOf(mRequestNum.getText().toString());
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                }
//            }
//


            MultiImageSelector selector = MultiImageSelector.create(HomeAddPictureActivity.this);
            selector.showCamera(showCamera);
            selector.count(maxNum);

//            if (mChoiceMode.getCheckedRadioButtonId() == R.id.single) {
//                selector.single();
//            } else {
//
//            }

            selector.multi();


            selector.origin(mSelectPath);
            selector.start(HomeAddPictureActivity.this, REQUEST_IMAGE);
        }
    }


    private void requestPermission(final String permission, String rationale, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.mis_permission_dialog_title)
                    .setMessage(rationale)
                    .setPositiveButton(R.string.mis_permission_dialog_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(HomeAddPictureActivity.this, new String[]{permission}, requestCode);
                        }
                    })
                    .setNegativeButton(R.string.mis_permission_dialog_cancel, null)
                    .create().show();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_STORAGE_READ_ACCESS_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickImage();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelector.EXTRA_RESULT);
                StringBuilder sb = new StringBuilder();
                for (String p : mSelectPath) {
                    sb.append(p);
                    sb.append("\n");
                }
                //  做返回的处理
                Log.e("mSelectPath", "" + sb.toString());


            }
        }
    }

}
