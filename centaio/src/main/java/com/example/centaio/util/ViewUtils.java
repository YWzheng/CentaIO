package com.example.centaio.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;

/**
 * 和View相关的工具类
 */

public class ViewUtils {


    /**
     * dp转px
     *
     * @param dpValue dp
     * @return px
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param pxValue px
     * @return dp
     */

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 移除父控件（让父控件和子控件之间没有联系）：
     *
     * @param v view
     */
    public static void removeParent(View v) {

        ViewParent parent = v.getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent;
            group.removeView(v);
        }
    }


    /**
     * 根据资源id获取资源文件名字 --- 带包名的全路径。
     *
     * @param context 上下文
     * @param resId   资源id
     * @return 路径
     */
    public static String getResourceName(@NonNull Context context, @AnyRes int resId) {
        String res = "";
        if (-1 == resId) return res;
        try {
            res = context.getResources().getResourceName(resId);
        } catch (Resources.NotFoundException e) {
            // e.printStackTrace();
            //LogUtils.d("out", "未获取到ResourceName ---id="+resid);
        }
        return res;
    }

    /**
     * 根据资源id获取资源文件名字   --- 简单名称。
     *
     * @param context 上下文
     * @param resid   资源id
     * @return 名称
     */
    public static String getSimpleResourceName(@NonNull Context context, @AnyRes int resid) {
        String res = "";
        if (-1 == resid) return res;
        try {
            res = context.getResources().getResourceName(resid);
            res = res.substring(res.indexOf("/") + 1);
        } catch (Resources.NotFoundException e) {
            // e.printStackTrace();
            //LogUtils.d("out", "未获取到ResourceName ---id="+resid);
        }
        return res;
    }

    /**
     * 获取Activity的根view
     *
     * @param activity activity
     * @return view
     */
    @NonNull
    public static FrameLayout getRootFrame(@NonNull Activity activity) {
        View re = activity.findViewById(android.R.id.content);
        if (re != null && re instanceof FrameLayout) {
            return (FrameLayout) re;
        }
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        re = viewGroup.getChildAt(viewGroup.getChildCount() - 1);
        if (re != null && re instanceof FrameLayout) {
            return (FrameLayout) re;
        } else {
            re = new FrameLayout(activity);
            activity.getActionBar().getHeight();
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            viewGroup.addView(re, lp);
            return (FrameLayout) re;
        }
    }

    /**
     * 获取Activity的根view
     */
    public static ViewGroup getRootView(Activity context) {
        return (ViewGroup) ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }

    public static String getButtonName(View view) {
        String viewName = "";
        //获取文本值
        if (view instanceof TextView) {
            viewName = ((TextView) view).getText().toString();
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int count = viewGroup.getChildCount();
            for (int i = 0; i < count; i++) {
                View itemView = viewGroup.getChildAt(i);
                String id = ViewUtils.getSimpleResourceName(itemView.getContext(), itemView.getId());
                if (itemView instanceof TextView) {
                    viewName = ((TextView) itemView).getText().toString();
                    break;
                } else if (itemView instanceof ViewGroup) {
                    String name = getButtonName(itemView);
                    if (null != name) {
                        return name;
                    }
                }
            }
        }
        return viewName;
    }
}