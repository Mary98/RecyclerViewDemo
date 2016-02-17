package com.mary.recyclerviewdemo.commonadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mary.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * File Name:   RecyclerViewAdapter
 * Author:      Mary
 * Write Dates: 2016/2/16
 * Description: 瀑布流适配器
 * Change Log:
 * 2016/2/16-11-21---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 *
 */
public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewHolder> {
    /**数据*/
    protected List<T> mDatas;
    /**上下文对象*/
    protected Context mContext;
    /**Item的主布局视图*/
    protected View mConvertView;
    /**布局打气筒*/
    protected LayoutInflater mInflater;
    /**布局ID*/
    protected int mItemLayoutId;
    /**Item的点击事件监听*/
    private OnItemClickLitener mOnItemClickLitener;

    public RecyclerViewAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext       = context;
        this.mInflater     = LayoutInflater.from(context);
        this.mDatas         = (null != datas) ? datas : new ArrayList<T>();
        this.mItemLayoutId = layoutId;
    }

    @Override
    public int getItemCount() {
        return (null == mDatas) ? 0 : mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mConvertView = mInflater.inflate(mItemLayoutId, parent, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(mContext, mConvertView, mItemLayoutId);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int position) {
        recyclerViewHolder.setIndex(position);
        convert(recyclerViewHolder, mDatas.get(position));
    }

    public abstract void convert(RecyclerViewHolder recyclerViewHolder, T item);

    /**
     * 添加Item
     * @param position  下标
     * @param t     泛型
     */
    public void addData(int position, T t) {
        mDatas.add(position, t);
        notifyItemInserted(position);
    }

    /**
     * 移除Item
     * @param position  下标
     * @return  是否移除成功
     */
    public boolean removeData(int position) {
        try {
            mDatas.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

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

}
