package com.mary.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.ViewGroup.LayoutParams;
import com.mary.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * File Name:   StaggeredAdapter
 * Author:      Mary
 * Write Dates: 2016/2/16
 * Description: 瀑布流适配器
 * Change Log:
 * 2016/2/16-11-21---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 *
 */
public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {
    /**数据*/
    private List<String> mDatas;
    /**布局打气筒*/
    private LayoutInflater mInflater;
    /**Item的高度集合*/
    private List<Integer> mHeights;
    /**Item的点击事件监听*/
    private OnItemClickLitener mOnItemClickLitener;
    /**
     * 监听事件
     */
    public interface OnItemClickLitener {
        /**点击事件*/
        void onItemClick(View view, int position);
        /**长按事件*/
        void onItemLongClick(View view, int position);
    }

    /**
     * 设置Item的点击监听
     * @param mOnItemClickLitener Item点击事件
     */
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public StaggeredAdapter(Context context, List<String> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;

        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add( (int) (100 + Math.random() * 300));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.item_one, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        LayoutParams lp = holder.tv.getLayoutParams();
        lp.height = mHeights.get(position);

        holder.tv.setLayoutParams(lp);
        holder.tv.setText(mDatas.get(position));

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (null == mDatas) ? 0 : mDatas.size();
    }

    public void addData(int position) {
        mDatas.add(position, "Insert One");
        mHeights.add( (int) (100 + Math.random() * 300));
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
}
