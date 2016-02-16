package com.mary.recyclerviewdemo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.mary.recyclerviewdemo.R;
import com.mary.recyclerviewdemo.adapter.StaggeredAdapter;

import java.util.ArrayList;
import java.util.List;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * File Name:   FiveActivity
 * Author:      Mary
 * Write Dates: 2016/2/16
 * Description: RecyclerView实现了瀑布流显示,动态设置高度,可设置水平或垂直显示
 * Change Log:
 * 2016/2/16-11-21---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 *
 */
public class FiveActivity extends Activity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggeredAdapter mStaggeredAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        initDatas();
        initViews();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        mStaggeredAdapter = new StaggeredAdapter(this, mDatas);
        mRecyclerView.setAdapter(mStaggeredAdapter);
    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

}
