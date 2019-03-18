package com.berlski.tool.custom.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.berlski.tool.custom.util.LogUtil;

/**
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 懒加载Fragment基类
 * Created by Berlski on 2018/7/24.
 */
public abstract class LazyLoadFragment extends Fragment implements View.OnClickListener {

    protected View view;            //页面布局

    protected Context mContext;     //上下文

    private boolean isFirstVisible = true;      //第一次加载时可见

    private boolean isFirstInvisible = true;    //第一次加载时不可见

    //Fragment的View加载完毕的标记
    private boolean isViewCreated;

    //Fragment对用户可见的标记
    private boolean isUIVisible;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getLayoutId() != 0) {
            view = inflater.inflate(getLayoutId(), container, false);
            return view;
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;

        //初始化 视图和事件，并进行初次可见时的加载
        lazyLoad(); // 执行懒加载,因为无法确定setUserVisibleHint和onViewCreated哪个方法先执行，因此两个方法里面都需要调用lazyLoad
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {
            isUIVisible = true; //当前fragment可见
            if (isFirstVisible) {
                //如果是第一次可见，则进行懒加载
                isFirstVisible = false;

                //初始化 视图和事件，并进行初次可见时的加载
                lazyLoad();
            } else {
                //不是第一次可见，则调用onUserVisible()
                //用户可见
                //Log.i("LazyLoad", getClass().getName() + "不是第一次可见");
                onUserVisible();
            }


        } else {
            isUIVisible = false;    //当前fragment不可见
            if (isFirstInvisible) {
                isFirstInvisible = false;

                //首次加载时不可见
                //Log.i("LazyLoad", getClass().getName() + "首次加载时不可见");
                onFirstUserInvisible();
            } else {
                //非第一次不可见
                //Log.i("LazyLoad", getClass().getName() + "当前不可见");
                onUserInvisible();
            }
        }
    }

    /**
     * 初始化 视图和事件，并进行初次可见时的加载
     */
    private void lazyLoad() {
        if (isViewCreated && isUIVisible) { //需要进行双重判断，避免因为setUserVisibleHint先于onViewCreaetd调用时，出现空指针
            LogUtil.i("LazyLoad", getClass().getName() + "初始化 视图和事件，并进行初次可见时的加载");

            initViewsAndEvents();
            onFirstUserVisible();  //进行初次可见时的加载
            initOnclickListenter();
        }
    }

    /**
     * 继承了TestFragment需要实现
     *
     * @return Fragment 视图布局资源文件id
     */
    protected abstract int getLayoutId();

    /**
     * 继承了TestFragment需要实现
     * <p>
     * 初始化 视图和事件
     */
    protected abstract void initViewsAndEvents();

    /**
     * 继承了TestFragment需要实现
     * <p>
     * 首次加载时可见
     * 进行初次可见时的加载
     */
    protected abstract void onFirstUserVisible();

    /**
     * 首次加载时不可见
     */
    protected abstract void onFirstUserInvisible();

    /**
     * Fragment 当前可见
     */
    protected abstract void onUserVisible();

    /**
     * Fragment 当前不可见
     */
    protected abstract void onUserInvisible();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //页面销毁,恢复标记
        /*isViewCreated = false;
        isUIVisible = false;*/

        //printLog(mTextviewContent + "销毁了");
    }


    /**
     * 初始化监听
     * 继承了BaseActivity的页面需要实现
     */
    protected abstract void initOnclickListenter();

    /**
     * Android3.0后
     * activity 添加 fragment 后，对 fragment 的传值，通过构造函数传值会丢失
     *
     *     Bundle bundle = new Bundle();
     *     bundle.putString("checkStatus", "1");
     *     fragment.setArguments(bundle);
     *
     *
     * fragment 的取值
     *     checkStatus = (String) getArguments().get("checkStatus");
     */


    /**
     * 将资源文件中定义的dip值乘以屏幕密度
     *
     * @param id
     * @return
     */
    protected int getCount(int id) {
        return getResources().getDimensionPixelSize(id);
    }


    /**
     * getView
     *
     * @param id
     * @return
     */
    protected View getView(int id) {
        return view.findViewById(id);
    }
}
