package com.mary.recyclerviewdemo.commonadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Mary on 16/1/29.
 */
public class ViewHolder {

    // SparseArray这个类，和Map类似，但是比Map效率，不过键只能为Integer.
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;
    private int mPosition;
    private int mLayoutId;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews       = new SparseArray<>();
        this.mContext     = context;
        this.mPosition    = position;
        this.mLayoutId    = layoutId;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {

        if (null == convertView) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.mPosition = position;
            return viewHolder;
        }

    }

    /**
     * 获取下标
     * @return 下标
     */
    public int getPosition() {
        return mPosition;
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
     * @return viewHolder
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
     * @return viewHolder
     */
    public ViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     * @param viewId
     * @param drawableId
     * @return viewHolder
     */
    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

    /**
     * 为ImageView设置图片
     * @param viewId
     * @param bitmap
     * @return viewHolder
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 为ImageView设置图片
     * @param viewId
     * @param drawable
     * @return viewHolder
     */
    public ViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * 为控件设置背景颜色
     * @param viewId
     * @param color
     * @return viewHolder
     */
    public ViewHolder setBackgroundColor(int viewId, int color) {
        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 为控件设置背景资源
     * @param viewId
     * @param backgroundResource
     * @return viewHolder
     */
    public ViewHolder setBackgroundResource(int viewId, int backgroundResource) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundResource);
        return this;
    }

    /**
     * 设置TextView字体颜色
     * @param viewId 控件ID
     * @param color 例如：Color.red
     * @return viewHolder
     */
    public ViewHolder setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
        return this;
    }

    /**
     * 设置TextView字体的颜色资源
     * @param viewId 控件ID
     * @param colorRes 例如：R.color.red
     * @return viewHolder
     */
    public ViewHolder setTextColorRes(int viewId, int colorRes) {
        TextView view = getView(viewId);
        view.setTextColor(mContext.getResources().getColor(colorRes));
        return this;
    }

    /**
     * 设置TextView字体大小
     * @param viewId 控件ID
     * @param value 数值
     * @return viewHolder
     */
    public ViewHolder setTextSize(int viewId, float value) {
        TextView view = getView(viewId);
        view.setTextSize(value);
        return this;
    }

    /**
     * 为控件设置透明度
     * @param viewId 控件ID
     * @param value 透明度
     * @return viewHolder
     */
    public ViewHolder setAlpha(int viewId, float value) {
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
     * @return viewHolder
     */
    public ViewHolder setVisible(int viewId, boolean visible) {
        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public ViewHolder linkify(int viewId) {
        TextView view = getView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }

    public ViewHolder setTypeface(Typeface typeface, int... viewIds) {
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
     * @return viewHolder
     */
    public ViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * 为控件设置进度
     * @param viewId 控件ID
     * @param progress 进度
     * @param max 最大进度
     * @return viewHolder
     */
    public ViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    /**
     *
     * @param viewId 控件ID
     * @param rating
     * @return viewHolder
     */
    public ViewHolder setRating(int viewId, float rating) {
        RatingBar view = getView(viewId);
        view.setRating(rating);
        return this;
    }

    /**
     *
     * @param viewId 控件ID
     * @param rating
     * @param max 最大值
     * @return viewHolder
     */
    public ViewHolder setRating(int viewId, float rating, int max) {
        RatingBar view = getView(viewId);
        view.setMax(max);
        view.setRating(rating);
        return this;
    }

    /**
     * 设置标签
     * @param viewId 控件ID
     * @param tag 标签
     * @return viewHolder
     */
    public ViewHolder setTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * 设置标签
     * @param viewId 控件ID
     * @param key 键值
     * @param tag 标签
     * @return viewHolder
     */
    public ViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    /**
     *
     * @param viewId 控件ID
     * @param checked 是否被选中
     * @return viewHolder
     */
    public ViewHolder setChecked(int viewId, boolean checked) {
        Checkable view = getView(viewId);
        view.setChecked(checked);
        return this;
    }

    /**
     * 设置点击事件
     * @param viewId 控件ID
     * @param listener 监听
     * @return viewHolder
     */
    public ViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置触摸监听
     * @param viewId 控件ID
     * @param listener 监听
     * @return viewHolder
     */
    public ViewHolder setOnTouchListener(int viewId, View.OnTouchListener listener) {
        View view = getView(viewId);
        view.setOnTouchListener(listener);
        return this;
    }

    /**
     * 设置长按事件
     * @param viewId 控件ID
     * @param listener 监听
     * @return viewHolder
     */
    public ViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

}
