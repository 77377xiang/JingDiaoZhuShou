package com.yude.auctionhelp.adapter.markadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.yude.auctionhelp.R;
import com.yude.auctionhelp.entity.CheckBoxData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hexiang on 17/3/22.
 */
public class CheckBoxRecyclerAdapter extends RecyclerView.Adapter <CheckBoxRecyclerAdapter.ViewHolder>
        implements View.OnClickListener, View.OnLongClickListener{


   //    http://blog.csdn.net/a_zhon/article/details/52928791

    //数据源
    public List<CheckBoxData> list;
    private Context context;
    //是否显示单选框,默认false
    private boolean isshowBox = false;
    // 存储勾选框状态的map集合
    private Map<Integer, Boolean> map = new HashMap<>();

    private RecyclerViewOnItemClickListener onItemClickListener;



    public CheckBoxRecyclerAdapter(List<CheckBoxData> list, Context context) {
        this.list = list;
        this.context = context;
        initMap();
    }


    //初始化map集合,默认为不选中
    private void initMap() {
        for (int i = 0; i < list.size(); i++) {
            map.put(i, false);
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_backdateils_checkbox, parent, false);
        ViewHolder vh = new ViewHolder(root);
        //为Item设置点击事件
        root.setOnClickListener(this);
        root.setOnLongClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.title.setText(list.get(position).getTitle());


        //长按显示/隐藏
        if (isshowBox) {
           // holder.checkBox.setVisibility(View.VISIBLE);
        } else {
           // holder.checkBox.setVisibility(View.INVISIBLE);
        }
        //Animation animation = AnimationUtils.loadAnimation(context, R.anim.list_anim);
        //设置checkBox显示的动画
        if (isshowBox)
           // holder.checkBox.startAnimation(animation);
        //设置Tag
        holder.root.setTag(position);
        //设置checkBox改变监听
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //用map集合保存
                map.put(position, isChecked);
            }
        });
        // 设置CheckBox的状态
        if (map.get(position) == null) {
            map.put(position, false);
        }
        holder.checkBox.setChecked(map.get(position));


    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {

        if (onItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            onItemClickListener.onItemClickListener(view, (Integer) view.getTag());
        }
    }

    @Override
    public boolean onLongClick(View view) {

        //不管显示隐藏，清空状态
        initMap();
        return onItemClickListener != null && onItemClickListener.onItemLongClickListener(view, (Integer) view.getTag());

    }




    //设置点击事件
    public void setRecyclerViewOnItemClickListener(RecyclerViewOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }




    //设置是否显示CheckBox
    public void setShowBox() {
        //取反
        isshowBox = !isshowBox;
    }


    //点击item选中CheckBox
    public void setSelectItem(int position) {
        //对当前状态取反
        if (map.get(position)) {
            map.put(position, false);
        } else {
            map.put(position, true);
        }
        notifyItemChanged(position);
    }

    //返回集合给Activity
    public Map<Integer, Boolean> getMap() {
        return map;
    }

    //视图管理
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private CheckBox checkBox;
        private View root;

        public ViewHolder(View root) {
            super(root);
            this.root = root;
            title = (TextView) root.findViewById(R.id.text_tv);
            checkBox = (CheckBox) root.findViewById(R.id.checkbox_cb);
        }
    }



    //接口回调设置点击事件
    public interface RecyclerViewOnItemClickListener {
        //点击事件
        void onItemClickListener(View view, int position);

        //长按事件
        boolean onItemLongClickListener(View view, int position);
    }





}
