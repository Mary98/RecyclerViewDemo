package com.mary.recyclerviewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.mary.recyclerviewdemo.R;
import com.mary.recyclerviewdemo.adapter.StaggeredAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * File Name:   SixActivity
 * Author:      Mary
 * Write Dates: 2016/2/16
 * Description: RecyclerView实现了瀑布流显示,并实现了Item的添加和移除动画
 * Change Log:
 * 2016/2/16-11-21---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 *
 */
public class SixActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private StaggeredAdapter mStaggeredAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six);
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
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_staggered, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_action_add:
                // 添加到第四项，原第四项变为第五项(以此类推)
                mStaggeredAdapter.addData(3);
                break;
            case R.id.id_action_delete:
                mStaggeredAdapter.removeData(3);
                break;
        }
        return true;
    }


}
