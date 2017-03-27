package com.yude.auctionhelp.adapter.markadapter;

import android.content.Context;

import com.yude.auctionhelp.R;
import com.yude.auctionhelp.base.ViewHolder;
import com.yude.auctionhelp.entity.Test;
import com.yude.auctionhelp.base.CommAdapter;

import java.util.List;

/**
 * Created by hexiang on 17/3/4.
 */
public class List_ReleasEcompleteAdapter extends CommAdapter <Test>{


    public List_ReleasEcompleteAdapter(List t, Context context) {
        super(t, context, R.layout.item_pull_mark_releas_ecomplete);
    }



    @Override
    public void convert(ViewHolder holder, Test data) {

       // holder.setText(R.id.test,data.getName());

    }
}



//
//public class AuditaAdapter extends CommAdapter<AcceptanceItemListResul> {
//    public AuditaAdapter(List<AcceptanceItemListResul> t, Context context) {
//        super(t, context, R.layout.item_grid_audita);
//    }
//
//    @Override
//    public void convert(ViewHolder holder, AcceptanceItemListResul data) {
//        holder.setText(R.id.nameTV,data.getName());
//    }
//}
