package com.yude.auctionhelp.adapter.markadapter;

import android.content.Context;

import com.yude.auctionhelp.R;
import com.yude.auctionhelp.base.CommAdapter;
import com.yude.auctionhelp.base.ViewHolder;

import java.util.List;

/**
 * Created by hexiang on 17/3/15.
 */
public class DirectoriesTextAdapter extends CommAdapter<String> {


    public DirectoriesTextAdapter(List<String> t, Context context) {
        super(t, context, R.layout.group);
    }

    @Override
    public void convert(ViewHolder holder, String data) {
        holder.setText(R.id.groupto_tv,data);

    }

}
