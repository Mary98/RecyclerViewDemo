package com.mary.recyclerviewdemo.activity;

import android.app.Activity;
import android.os.Bundle;

import com.mary.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * File Name:   FourActivity
 * Author:      Mary
 * Write Dates: 2016/2/16
 * Description: RecyclerView实现了瀑布流显示,固定高度，
 *              显示类似GridView,可设置水平或垂直显示
 * Change Log:
 * 2016/2/16-11-21---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 *
 */
public class FourActivity extends Activity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;

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
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
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

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(
                    LayoutInflater.from(FourActivity.this).inflate(R.layout.item_one, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }
}
