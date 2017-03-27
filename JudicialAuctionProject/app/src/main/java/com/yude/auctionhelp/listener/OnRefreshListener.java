package com.yude.auctionhelp.listener;

/**
 * Created by PersonalFolder on 16/11/7.
 */
public interface OnRefreshListener {

    /**
     * 下拉刷新
     */
    void onDownPullRefresh();

    /**
     * 上拉加载更多
     */
    void onLoadingMore();
}
