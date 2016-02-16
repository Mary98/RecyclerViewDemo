package com.mary.recyclerviewdemo.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import android.view.View;

import android.widget.Toast;

import com.mary.recyclerviewdemo.R;
import com.mary.recyclerviewdemo.adapter.StaggeredAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * File Name:   EightActivity
 * Author:      Mary
 * Write Dates: 2016/2/16
 * Description: RecyclerView显示瀑布流，实现了Item的事件监听
 * Change Log:
 * 2016/2/16-11-21---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 * 
 */
public class EightActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggeredAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eight);
        initDatas();
        initViews();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new StaggeredAdapter(this, mDatas);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickLitener(new StaggeredAdapter.OnItemClickLitener() {

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(EightActivity.this, position + " click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(EightActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
        });
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
