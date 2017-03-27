package com.yude.auctionhelp.adapter.markadapter;

import android.content.Context;

import com.yude.auctionhelp.R;
import com.yude.auctionhelp.base.CommAdapter;
import com.yude.auctionhelp.base.ViewHolder;
import com.yude.auctionhelp.entity.Test;

import java.util.List;

/**
 * Created by hexiang on 17/3/5.
 */
public class Pull_Mark_ReleasEcompleteAdapter extends CommAdapter<Test> {



    public Pull_Mark_ReleasEcompleteAdapter(List t, Context context) {
        super(t, context, R.layout.item_pull_mark_releas_ecomplete);
    }



    @Override
    public void convert(ViewHolder holder, Test data) {

        // holder.setText(R.id.test,data.getName());

    }




}
