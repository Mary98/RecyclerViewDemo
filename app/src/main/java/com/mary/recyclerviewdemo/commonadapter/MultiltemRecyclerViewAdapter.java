package com.mary.recyclerviewdemo.commonadapter;

import android.content.Context;
import android.view.ViewGroup;


import java.util.List;

/**
 * File Name:   MultiltemRecyclerViewAdapter
 * Author:      Mary
 * Write Dates: 2016/02/16
 * Description: 支持多种样式的RecyclerView适配器
 * Change log:
 * 2016/02/16-21-55---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 *
 */
public abstract class MultiltemRecyclerViewAdapter<T> extends RecyclerViewAdapter<T> {

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiltemRecyclerViewAdapter(Context context, List<T> datas, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, datas, -1);
        this.mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        if (null != mMultiItemTypeSupport) {
            mItemLayoutId = mMultiItemTypeSupport.getLayoutId(position, mDatas.get(position));
            return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (null == mMultiItemTypeSupport)
            return super.onCreateViewHolder(parent, viewType);

        mConvertView = mInflater.inflate(mItemLayoutId, parent, false);
        RecyclerViewHolder holder = new RecyclerViewHolder(mContext, mConvertView, mItemLayoutId);
        return holder;
    }

}
