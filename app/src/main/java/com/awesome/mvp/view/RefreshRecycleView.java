package com.awesome.mvp.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.BaseAdapter;

import com.awesome.mvp.App;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class RefreshRecycleView extends SmartRefreshLayout{

    private RecyclerView mRecyclerView;

    public RefreshRecycleView(Context context) {
        this(context,null);
    }

    public RefreshRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRecyclerView = new RecyclerView(context);
        addView(mRecyclerView);
    }
    public void setRecycleManager(RecyclerView.LayoutManager manager){
        mRecyclerView.setLayoutManager(manager);
    }

    public void setLineManager(boolean isVertical){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(App.getAppContext());
        linearLayoutManager.setOrientation(isVertical?
                LinearLayoutManager.VERTICAL:LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    public void setRcyAdapter(RecyclerView.Adapter adapter){
        mRecyclerView.setAdapter(adapter);
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
