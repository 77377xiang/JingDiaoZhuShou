package com.yude.auctionhelp.fragment.callfragment.home_data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.yude.auctionhelp.R;
import com.yude.auctionhelp.activitys.waitfor_activity.edit_home.HomeAddPicture2Activity;
import com.yude.auctionhelp.activitys.waitfor_activity.edit_home.HomeAddPictureActivity;
import com.yude.auctionhelp.activitys.waitfor_activity.edit_home.HomeEnvironmentalActivity;
import com.yude.auctionhelp.adapter.AddRecycleHolder;
import com.yude.auctionhelp.adapter.AddRecyclerAdapter;
import com.yude.auctionhelp.adapter.RecycleHolder;
import com.yude.auctionhelp.adapter.markadapter.Pull_Mark_releaseComplteRecyclerAdapter;
import com.yude.auctionhelp.adapter.myadapter.CodeGridViewAdapter;
import com.yude.auctionhelp.base.BaseFragment;
import com.yude.auctionhelp.entity.response.TestComplete;
import com.yude.auctionhelp.utils.RecycleViewDivider;
import com.yude.auctionhelp.views.view.CustomeGridView;

import java.util.ArrayList;
import java.util.List;

import me.nereo.multi_image_selector.MultiImageSelector;

/**
 * 编辑 完成  环境 的展示
 */
public class EditEnvironmentalFragment extends BaseFragment {

    RecyclerView show_rv;
    List<TestComplete> testCompletes = new ArrayList<>();   // 外层数据
    private List<String> listPhoto = new ArrayList<>(); // 内层数据
    private  List<List<String> > addPhpto = new ArrayList<>();
    String getEditText;
    ImageView image_iv;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_edit;
    }


    @Override
    protected void initViews(Bundle bundle) {
        show_rv = (RecyclerView) rootView.findViewById(R.id.show_rv);
        image_iv = (ImageView) rootView.findViewById(R.id.image_iv);
        image_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(new Intent(getContext(), HomeEnvironmentalActivity.class), 1);

            }
        });

        setRecyclerView();

    }


    private void setRecyclerView() {

        if (listPhoto.size() > 0) {
            initData();
            show_rv.setVisibility(View.VISIBLE);
        } else {
            show_rv.setVisibility(View.GONE);
        }
        show_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        // show_rv.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, ContextCompat.getColor(getContext(), R.color._b0b0b0)));

        //  注意没有数据可用String  不可以用null
        AddRecyclerAdapter addAdapter = new AddRecyclerAdapter<TestComplete, String, Integer>((getContext()),
                R.layout.item_rv_edit_show_complete,
                testCompletes,
                0, null,
                R.layout.code_add_footer, R.drawable.test_jiaohao1) {
            @Override
            public void convertBody(AddRecycleHolder holder, TestComplete data, int position) {
                holder.setText(R.id.showDeitText, data.getEditText());
                holder.setText(R.id.keTing_tv, data.getKeTing());
                // 需要隐藏单选框
                holder.findView(R.id.checkbox_type_ll).setVisibility(View.GONE);

                CustomeGridView show_cgw = holder.findView(R.id.show_cgw);
                CodeGridViewAdapter codeGridViewAdapter = new CodeGridViewAdapter(getContext(), listPhoto);
                show_cgw.setAdapter(codeGridViewAdapter);
            }

            @Override
            public void convertFooter(AddRecycleHolder holder, Integer data, int position) {
                super.convertFooter(holder, data, position);
                ImageView image = holder.findView(R.id.add_edit_iv);
                image.setImageResource(data);

                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(getContext(), "点击了编辑", Toast.LENGTH_LONG).show();
                        //  startActivity(new Intent(getContext(),HomeAddPicture2Activity.class));
                        startActivityForResult(new Intent(getContext(), HomeEnvironmentalActivity.class), 1);

                    }
                });
            }
        };
        show_rv.setAdapter(addAdapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case 1:
                Bundle b = data.getExtras(); //data为B中回传的Intent
                getEditText = b.getString("getEditText");//str即为回传的值

                List<String> bitmaps = b.getStringArrayList("bitmaps");
                //  只可以显示4个    大于4 默认加载前4个

                if (bitmaps.size() > 4) {

                    for (int i = 0; i < 4; i++) {
                        listPhoto.add(bitmaps.get(i));
                        addPhpto.add(listPhoto);
                    }
                } else {
                    listPhoto = b.getStringArrayList("bitmaps");
                    addPhpto.add(listPhoto);
                }

                setRecyclerView();
               /* Log.e("getEditText"," 我是实验参数"+str);
                Log.e("getEditText"," 我是实验参数"+bitmaps.size());

                for (int i = 0; i < bitmaps.size(); i++) {
                    Log.e("getEditText",bitmaps.get(i)+"bitmaps");
                }
*/
                break;
            default:
                break;
        }

    }


    private void initData() {

        TestComplete testComplete = new TestComplete();
        testComplete.setPhotoUil("SSS");
        testComplete.setEditText(getEditText);
        testComplete.setKeTing("客厅1");
        testComplete.setListPhoto(listPhoto);
        testCompletes.add(testComplete);

    }

}
