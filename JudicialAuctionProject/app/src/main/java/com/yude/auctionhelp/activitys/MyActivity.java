package com.yude.auctionhelp.activitys;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.readystatesoftware.viewbadger.BadgeView;
import com.yude.auctionhelp.R;
import com.yude.auctionhelp.activitys.my_activity.ClipImageActivity;
import com.yude.auctionhelp.activitys.my_activity.ConcernActivity;
import com.yude.auctionhelp.activitys.my_activity.SettingActivity;
import com.yude.auctionhelp.base.BaseActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by hexiang on 17/3/24.
 */
public class MyActivity extends BaseActivity {

    //请求相机
    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    //调用照相机返回图片临时文件
    private File tempFile;
    private int type = 2;

    TextView tile_tv, l_title_tv, r_title_tv;
    ImageView l_title_iv, r_title_iv;

    //  特别关心
    @BindView(R.id.concern_bt)
    Button concern_bt;

    //特殊消息
    @BindView(R.id.setting_bt)
    Button setting_bt;
    //  头像
    @BindView(R.id.photo_iv)
    ImageView photo_iv;
    //
    @BindView(R.id.name_tv)
    TextView name_tv;
    //  职务
    @BindView(R.id.post_tv)
    TextView post_tv;
    //  所在地区
    @BindView(R.id.position_tv)
    TextView position_tv;
    //  所在单位
    @BindView(R.id.company_tv)
    TextView company_tv;

    @BindView(R.id.number_tv)
    TextView number_tv;

    @BindView(R.id.sing_up_btn)
    Button sing_up_btn;


    BadgeView isShowUnpublished_bv;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_main_my;
    }

    @Override
    protected void initViews(Bundle bundle) {
        setting_bt.setText("通知");
        initTitle();
        //创建拍照存储的临时文件
        createCameraTempFile(bundle);
        setText();
        showBadgeView_ReleaseComplete(number_tv);
    }


    private void showBadgeView_ReleaseComplete(View view) {
        //http://www.th7.cn/Program/Android/201606/874990.shtml
        isShowUnpublished_bv = new BadgeView(this, view);
        //  BadgeView badgeView = new BadgeView(getContext(), view);  // 将需要设置角标的View 传递进去
        isShowUnpublished_bv.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);// 设置在右上角
        isShowUnpublished_bv.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);// 设置在右上角
        isShowUnpublished_bv.setTextSize(9);// 设置文本大小
        isShowUnpublished_bv.setText("50"); // 设置要显示的文本
        isShowUnpublished_bv.show();// 将角标显示出来

        // badgeView.toggle();//打开关闭或者角标
        // badgeView.setTextColor(Color.BLUE);//角标内文字颜色
        // badgeView.setBadgeBackgroundColor(Color.YELLOW);//角标背景颜色
        // badgeView.setTextSize(12);//角标内数字大小
        //badgeView.toggle(true);//默认动画效果

    }

    @Override
    protected void initData() {

    }


    //  初始化数据
    private void setText() {

        name_tv.setText("老王");
        post_tv.setText("法官");
        position_tv.setText("杭州市");
        company_tv.setText("杭州人民法院");
    }


    // 初始化 title
    private void initTitle() {
        tile_tv = (TextView) findViewById(R.id.tile_tv);
        l_title_tv = (TextView) findViewById(R.id.l_title_tv);
        r_title_tv = (TextView) findViewById(R.id.r_title_tv);
        l_title_iv = (ImageView) findViewById(R.id.l_title_iv);
        r_title_iv = (ImageView) findViewById(R.id.r_title_iv);

        tile_tv.setText("我的");

        l_title_tv.setVisibility(View.GONE);
        l_title_iv.setImageResource(R.mipmap.h_fanhui);
        l_title_iv.setVisibility(View.VISIBLE);

        r_title_tv.setVisibility(View.VISIBLE);
        r_title_iv.setVisibility(View.GONE);
        r_title_tv.setText("设置");

        r_title_tv.setTextSize(17);
        r_title_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyActivity.this, SettingActivity.class));

            }
        });
    }


    @OnClick({R.id.concern_bt, R.id.setting_bt, R.id.photo_iv,R.id.sing_up_btn})
    void click(View v) {
        switch (v.getId()) {

            //已发布
            case R.id.concern_bt:
                Toast.makeText(this, "点击了关注", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this, ConcernActivity.class));

                break;
            case R.id.photo_iv:
                uploadHeadImage();
                Toast.makeText(this, "点击了头像", Toast.LENGTH_SHORT).show();
                break;

            case R.id.setting_bt:
                Toast.makeText(this, "点击了通知", Toast.LENGTH_SHORT).show();
                // startActivity(new Intent(getActivity(), DetailsFavoriteNewActivity.class));

                break;
            case R.id.sing_up_btn:
                Toast.makeText(this, "点击我要报名", Toast.LENGTH_SHORT).show();

                break;


        }

    }

    /**
     * 外部存储权限申请返回
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoCarema();
            } else {
                // Permission Denied
            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoPhoto();
            } else {
                // Permission Denied
            }
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("tempFile", tempFile);
    }


    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }


    /**
     * 跳转到照相机
     */
    private void gotoCarema() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, REQUEST_CAPTURE);
    }


    /**
     * 创建调用系统照相机待存储的临时文件
     *
     * @param savedInstanceState
     */
    private void createCameraTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }


    /**
     * 上传头像
     */
    private void uploadHeadImage() {
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_photo, null);
        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });

        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(MyActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(MyActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统相机
                    gotoCarema();


                }
                popupWindow.dismiss();
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(MyActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(MyActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {

                    //跳转到调用系统图库
                    gotoPhoto();


                }
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == MyActivity.RESULT_OK) {
                    Toast.makeText(MyActivity.this, "点击了调用系统相机返回", Toast.LENGTH_SHORT).show();
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == MyActivity.RESULT_OK) {
                    Toast.makeText(MyActivity.this, "点击了调用系统相册返回", Toast.LENGTH_SHORT).show();
                    Uri uri = data.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回

                Toast.makeText(MyActivity.this, "返回", Toast.LENGTH_SHORT).show();

                if (resultCode == MyActivity.RESULT_OK) {
                    final Uri uri = data.getData();
                    if (uri == null) {
                        return;
                    }
                    String cropImagePath = getRealFilePathFromUri(MyActivity.this.getApplicationContext(), uri);
                    Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    photo_iv.setImageBitmap(bitMap);

                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......

                }
                break;
        }

    }


    /**
     * 打开截图界面
     *
     * @param uri
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(MyActivity.this, ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);
        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }


    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


}
