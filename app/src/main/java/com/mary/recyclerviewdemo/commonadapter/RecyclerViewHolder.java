package com.mary.recyclerviewdemo.commonadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * File Name:   RecyclerViewHolder
 * Author:      Mary
 * Write Dates: 2016/2/16
 * Description: RecyclerView的适配ViewHolder
 * Change Log:
 * 2016/2/16-12-03---[公司]---[姓名]
 * ......Added|Changed|Delete......
 * --------------------------------
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    // SparseArray这个类，和Map类似，但是比Map效率，但是键只能为Integer.
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;
    private int mPosition;
    private int mLayoutId;

    public RecyclerViewHolder(Context context, View view, int layoutId) {
        super(view);
        this.mViews        = new SparseArray<>();
        this.mContext      = context;
        this.mLayoutId    = layoutId;
        this.mConvertView = view;
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个RecyclerViewHolder对象
     * @param context       上下文对象
     * @param convertView  Item视图
     * @param layoutId      布局ID
     * @return
     */
    public static RecyclerViewHolder get(Context context, View convertView, int layoutId) {
        if (null == convertView) {
            return new RecyclerViewHolder(context, convertView, layoutId);
        } else {
            RecyclerViewHolder recyclerViewHolder = (RecyclerViewHolder) convertView.getTag();
            return recyclerViewHolder;
        }
    }

    /**
     * 获取下标
     * @return 下标
     */
    public int getIndex() {
        return mPosition;
    }

    /**
     * 设置下标
     */
    public void setIndex(int index) {
        this.mPosition = index;
    }

    /**
     * 获取布局ID
     * @return 布局ID
     */
    public int getLayoutId() {
        return mLayoutId;
    }

    /**
     * Item布局
     * @return Item布局
     */
    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     * @param viewId 控件ID
     * @param <T> 泛型
     * @return recyclerViewHolder
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (null == view) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     * @param viewId
     * @param text
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     * @param viewId
     * @param drawableId
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    /**
     * 为ImageView设置图片
     * @param viewId
     * @param bitmap
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 为ImageView设置图片
     * @param viewId
     * @param drawable
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * 为控件设置背景颜色
     * @param viewId
     * @param color
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 为控件设置背景资源
     * @param viewId
     * @param backgroundResource
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setBackgroundResource(int viewId, int backgroundResource) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundResource);
        return this;
    }

    /**
     * 设置TextView字体颜色
     * @param viewId 控件ID
     * @param color 例如：Color.red
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
        return this;
    }

    /**
     * 设置TextView字体的颜色资源
     * @param viewId 控件ID
     * @param colorRes 例如：R.color.red
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setTextColorRes(int viewId, int colorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(colorRes));
        return this;
    }

    /**
     * 设置TextView字体大小
     * @param viewId 控件ID
     * @param value 数值
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setTextSize(int viewId, float value) {
        TextView view = getView(viewId);
        view.setTextSize(value);
        return this;
    }

    /**
     * 为控件设置透明度
     * @param viewId 控件ID
     * @param value 透明度
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setAlpha(int viewId, float value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getView(viewId).setAlpha(value);
        } else {
            // Pre-honeycomb hack to set Alpha value
            AlphaAnimation alphaAnimation = new AlphaAnimation(value, value);
            alphaAnimation.setDuration(0);
            alphaAnimation.setFillAfter(true);
            getView(viewId).startAnimation(alphaAnimation);
        }
        return this;
    }

    /**
     * 设置控件是否可见
     * @param viewId 控件ID
     * @param visible 是否可见
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public RecyclerViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public RecyclerViewHolder setTypeface(Typeface typeface, int... viewIds) {
        for (int viewId : viewIds) {
            TextView view = getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
        }
        return this;
    }

    /**
     * 为控件设置进度
     * @param viewId 控件ID
     * @param progress 进度
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * 为控件设置进度
     * @param viewId 控件ID
     * @param progress 进度
     * @param max 最大进度
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     *
     * @param viewId 控件ID
     * @param rating
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     *
     * @param viewId 控件ID
     * @param rating
     * @param max 最大值
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /**
     * 设置标签
     * @param viewId 控件ID
     * @param tag 标签
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * 设置标签
     * @param viewId 控件ID
     * @param key 键值
     * @param tag 标签
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    /**
     *
     * @param viewId 控件ID
     * @param checked 是否被选中
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 设置点击事件
     * @param viewId 控件ID
     * @param listener 监听
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置触摸监听
     * @param viewId 控件ID
     * @param listener 监听
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    /**
     * 设置长按事件
     * @param viewId 控件ID
     * @param listener 监听
     * @return recyclerViewHolder
     */
    public RecyclerViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }
}
