package com.mary.recyclerviewdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mary.recyclerviewdemo.bean.Bean;
import com.mary.recyclerviewdemo.R;
import com.mary.recyclerviewdemo.bean.ChatMessage;
import com.mary.recyclerviewdemo.commonadapter.MultiItemTypeSupport;
import com.mary.recyclerviewdemo.commonadapter.MultiltemRecyclerViewAdapter;
import com.mary.recyclerviewdemo.commonadapter.RecyclerViewHolder;
import com.mary.recyclerviewdemo.commonadapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * File Name:   TenActivity
 * Author:      Mary
 * Write Dates: 2016/2/16
 * Description: RecyclerView显示瀑布流，封装了多功能适配器(多样式支持)
 * Change Log:
 * 2016/2/16-11-21---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 *
 */
public class TenActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<ChatMessage> mDatas;
    private MultiltemRecyclerViewAdapter mAdapter;
    /**Item的高度集合*/
    private List<Integer> mHeights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten);
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
        //mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
        // ListView布局
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // GridView布局
        // mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.setAdapter(mAdapter = new MultiltemRecyclerViewAdapter<ChatMessage>(this, mDatas,
                new MultiItemTypeSupport<ChatMessage>() {
                    @Override
                    public int getLayoutId(int position, ChatMessage chatMessage) {
                        if (chatMessage.isComMeg())
                            return R.layout.main_chat_from_msg;
                        else
                            return R.layout.main_chat_send_msg;
                    }

                    @Override
                    public int getViewTypeCount() {
                        return 2;
                    }

                    @Override
                    public int getItemViewType(int position, ChatMessage chatMessage) {
                        if (chatMessage.isComMeg())
                            return ChatMessage.RECIEVE_MSG;
                        else
                            return ChatMessage.SEND_MSG;
                    }
                }) {

            @Override
            public void convert(RecyclerViewHolder holder, final ChatMessage chatMessage) {
                /**
                 * 判断 Item 布局，再设置数据
                 */
                switch (holder.getLayoutId()) {
                    case R.layout.main_chat_from_msg:
                        holder.setText(R.id.chat_from_content, chatMessage.getContent());
                        holder.setText(R.id.chat_from_name, chatMessage.getName());
                        holder.setImageResource(R.id.chat_from_icon, chatMessage.getIcon())
                        .setOnClickListener(R.id.chat_from_icon, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(TenActivity.this, "你点击了：" + chatMessage.getName(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case R.layout.main_chat_send_msg:
                        holder.setText(R.id.chat_send_content, chatMessage.getContent());
                        holder.setText(R.id.chat_send_name, chatMessage.getName());
                        holder.setImageResource(R.id.chat_send_icon, chatMessage.getIcon())
                        .setOnLongClickListener(R.id.chat_send_icon, new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {
                                Toast.makeText(TenActivity.this, "你长按了：" + chatMessage.getName(), Toast.LENGTH_SHORT).show();
                                return true;
                            }
                        });
                        break;
                }
            }
        });

    }

    /**
     * 初始化数据
     */
    private void initDatas() {
        mDatas = new ArrayList<>();
        ChatMessage msg;
        for (int i = 0; i < 15; i++) {
            if ( 0 == (i % 2))
                msg = new ChatMessage(R.drawable.mary, "Mary", "I am Mary", null, false);
            else
                msg = new ChatMessage(R.drawable.demon, "Demon", "I am Demon", null, true);
            mDatas.add(msg);
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
                    Toast.makeText(TenActivity.this, "移除成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TenActivity.this, "移除失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return true;
    }
}
