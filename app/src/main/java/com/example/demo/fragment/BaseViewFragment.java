package com.example.demo.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.centaio.CentaIO;

import java.lang.reflect.Field;


/**
 * 新版BaseViewFragment
 */

public abstract class BaseViewFragment extends Fragment {

    /**
     * Tab切换时会回调此方法。对于没有Tab的页面，{@link Fragment#getUserVisibleHint()}默认为true。
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        CentaIO.getInstance().setUserVisibleHint(this, isVisibleToUser);
    }

    //hide/show方式调用此方法
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        CentaIO.getInstance().onFragmentHiddenChanged(this, hidden);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        CentaIO.getInstance().onFragmentResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        CentaIO.getInstance().onFragmentPaused(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        /**
         * 解决Activity has been destroyed的错误。
         */
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
