package com.mary.recyclerviewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.mary.recyclerviewdemo.Bean;
import com.mary.recyclerviewdemo.R;
import com.mary.recyclerviewdemo.commonadapter.RecyclerViewHolder;
import com.mary.recyclerviewdemo.commonadapter.StaggeredAdapter;
import com.mary.recyclerviewdemo.divider.DividerGridItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * File Name:   NineActivity
 * Author:      Mary
 * Write Dates: 2016/2/16
 * Description: RecyclerView显示瀑布流，封装了多功能适配器
 * Change Log:
 * 2016/2/16-11-21---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 * 
 */
public class NineActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Bean> mDatas;
    private StaggeredAdapter mAdapter;
    /**Item的高度集合*/
    private List<Integer> mHeights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nine);
        initDatas();
        initViews();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        // 设置分割线
        // mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        //设置Item增加、移除动画(不设置也是默认动画)
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // 瀑布流布局
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        // ListView布局
        // mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // GridView布局
        // mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.setAdapter(mAdapter = new StaggeredAdapter<Bean>(this, mDatas, R.layout.item_one) {
            @Override
            public void convert(RecyclerViewHolder holder, final Bean bean) {
                // 设置高度
                ViewGroup.LayoutParams params = holder.getConvertView().getLayoutParams();
                params.height = mHeights.get(holder.getIndex());
                holder.getConvertView().setLayoutParams(params);

                holder.setText(R.id.id_num, bean.getTitle());
                holder.setOnClickListener(R.id.id_num, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(NineActivity.this, bean.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(NineActivity.this, "你点击了：" + bean.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        mDatas = new ArrayList<>();
        Bean bean;
        for (int i = 0; i < 56; i++) {
            bean = new Bean("Mary:" + i, "全能的适配器", "2016-02-16", "10086");
            mDatas.add(bean);
        }

        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add( (int) (100 + Math.random() * 300));
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
                mHeights.add( (int) (100 + Math.random() * 300));
                mAdapter.addData(3, mDatas.get(0));
                break;
            case R.id.id_action_delete:
                boolean b = mAdapter.removeData(3);
                if (b) {
                    Toast.makeText(NineActivity.this, "移除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NineActivity.this, "移除失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }

}
