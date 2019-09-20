package com.berlski.tool;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.berlski.tool.custom.util.NetUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

/**
 * 用来做Recycler列表 的工具类，
 * Created by Berlski on 2017/6/21.
 */

public class RecyclerUtil {

    private int current;        //页码（第几页）
    private int pages;    //总页数
    private int size;     //每页条数
    private int total;  //总条数
    private Context mContext;
    private int listSize;

    private String footerHint = "没有更多数据了";

    private int index = 0;              //没有数据的 吐司 弹出的次数
    private boolean isLoad = false;     //标识是否可以加载
    private boolean isComplete = true;  //标识加载已经完成
    private boolean isAddFooterView = true;  //标识是否加载没有更多数据时的尾布局，默认开启

    private BaseQuickAdapter mAdapter;          //数据加载用到的  数据适配器
    private RecyclerView mRecyclerView;         //列表页用到的 列表 view
    private SmartRefreshLayout mRefreshLayout;  //列表页用到的 刷新加载 view

    public void canAddFooterView(boolean addFooterView) {
        isAddFooterView = addFooterView;
    }

    public BaseQuickAdapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(BaseQuickAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public void setRecyclerView(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    public SmartRefreshLayout getRefreshLayout() {
        return mRefreshLayout;
    }

    public void setRefreshLayout(SmartRefreshLayout mRefreshLayout) {
        this.mRefreshLayout = mRefreshLayout;
    }

    public int getListSize() {
        return listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    /*public RecyclerUtil(Context context) {
        mContext = context;
    }*/

    /**
     * @param context       上下文
     * @param adapter       数据加载用到的  数据适配器
     * @param recyclerView  列表页用到的 列表 view
     * @param refreshLayout 列表页用到的 刷新加载 view
     */
    public RecyclerUtil(Context context, BaseQuickAdapter adapter, RecyclerView recyclerView, SmartRefreshLayout refreshLayout) {
        mContext = context;
        this.mAdapter = adapter;
        this.mRecyclerView = recyclerView;
        this.mRefreshLayout = refreshLayout;


        if (null != mAdapter && null != mRecyclerView) {
            //设置布局管理器
            mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

            //设定数据适配器
            mRecyclerView.setAdapter(mAdapter);

        }

        if (null != mRefreshLayout) {
            mRefreshLayout.setEnableRefresh(true);     //是否开启下拉刷新功能（默认true）
            mRefreshLayout.setEnableLoadmore(true);     //是否开启上拉加载功能（默认true）
            mRefreshLayout.setEnableHeaderTranslationContent(true);//是否下拉Header的时候向下平移列表或者内容
        }
    }

    /**
     * @param context       上下文
     * @param refreshLayout 列表页用到的 刷新加载 view
     */
    public RecyclerUtil(Context context, SmartRefreshLayout refreshLayout) {
        mContext = context;
        this.mRefreshLayout = refreshLayout;

        if (null != mRefreshLayout) {
            mRefreshLayout.setEnableRefresh(true);     //是否开启下拉刷新功能（默认true）
            mRefreshLayout.setEnableLoadmore(true);     //是否开启上拉加载功能（默认true）
            mRefreshLayout.setEnableHeaderTranslationContent(true);//是否下拉Header的时候向下平移列表或者内容
        }
    }

    /**
     * 修改加载刷新方案，默认开启上拉加载、下拉刷新
     *
     * @param refresh
     * @param loadmore
     */
    public void setRefreshAndLoadmore(boolean refresh, boolean loadmore) {
        if (null != mRefreshLayout) {
            mRefreshLayout.setEnableRefresh(refresh);     //是否开启下拉刷新功能（默认true）
            mRefreshLayout.setEnableLoadmore(loadmore);     //是否开启上拉加载功能（默认true）
        }
    }

    /**
     * 开始刷新
     */
    public void autoRefresh() {
        if (null != mRefreshLayout) {
            mRefreshLayout.autoRefresh();
        }
    }

    /**
     * 页标初始化
     */
    public void pageLabelInit() {
        current = 1;
        listSize = 0;
    }

    /**
     * 数据初始化、刷新完成
     */
    public void refreshComplete(String s) {
        isComplete(s);

        //结束刷新动画
        finishRefresh();
    }

    /**
     * 加载更多完成
     */
    public void loadmoreComplete(String s) {
        isComplete(s);

        //结束加载动画
        finishLoadmore();
    }

    /**
     * 根据数据返回值，判断数据结果，以及下次加载更改是否开启
     *
     * @param s
     */
    private void isComplete(String s) {
        try {
            JSONObject json = JSON.parseObject(s);
            JSONObject data = JSON.parseObject(json.getString("data"));
            current = data.getInteger("current");           //页码（第几页）
            pages = data.getInteger("pages");     //总页数
            size = data.getInteger("size");       //每页条数
            total = data.getInteger("total"); //总条数
        } catch (Exception e) {

        }

        /*if (total == 0) {
            mAdapter.setEmptyView(R.layout.layout_empty_view);
        }*/

        //如果页码等于总页数，说明已经加载到最后一页
        if (current >= this.pages) {
            isLoad = false;

            addFooterView();
        }

        if (current < this.pages) {
            //this.listSize += list.size();
            this.current++;
            isLoad = true;

            deleteFooterView();
        }

        //标识网络请求已经结束
        isComplete = true;
    }

    /**
     * 判断是否加载更多，并根据上拉次数判断吐司内容
     *
     * @return
     */
    public boolean isLoad() {
        if (!isLoad) {
            if (index == 0) {
                //ToastUtil.showToast("已加载全部!");
                index++;
                return false;
            } else if (index == 1) {
                //ToastUtil.showToast("没有更多数据!");
                index++;
                return false;
            } else if (index == 2) {
                //ToastUtil.showToast("真的没有啦!");
                index = 0;
                return false;
            }
        }

        return true;
    }


    /**
     * 添加列表底部的提示view
     */
    public void addFooterView() {
        if (isAddFooterView && null != mAdapter && null != mRecyclerView) {
            if (mAdapter.getFooterLayoutCount() == 0) {
                View footerView = LayoutInflater.from(mContext).inflate(R.layout.layout_list_footer_view, mRecyclerView, false);
                TextView hint = footerView.findViewById(R.id.llfv_hint);
                hint.setText(footerHint);

                mAdapter.addFooterView(footerView);

                //mRefreshLayout.setEnableLoadMore(false);
                mRefreshLayout.setEnableLoadmore(false);
            }
        }
    }

    /**
     * 删除列表底部的提示view
     */
    public void deleteFooterView() {
        if (isAddFooterView && null != mAdapter) {
            if (mAdapter.getFooterLayoutCount() > 0) {
                mAdapter.removeAllFooterView();
            }

            mRefreshLayout.setEnableLoadmore(true);
        }
    }


    public void setFooterHint(String footerHint) {
        this.footerHint = footerHint;
    }

    /**
     * 条目分割线，可自定义
     *
     * @param drawable
     */
    public void setItemDecorationLine(@DrawableRes int drawable) {
        if (null != mRecyclerView) {
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL);
            dividerItemDecoration.setDrawable(ContextCompat.getDrawable(mContext, drawable));
            mRecyclerView.addItemDecoration(dividerItemDecoration);
        }
    }

    /**
     * 默认条目分割线
     */
    public void setItemDecorationLine() {
        if (null != mRecyclerView) {
            mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        }
    }

    /**
     * 是否可以刷新
     */
    public boolean canRefresh() {
        //检查网络是否可用，如果不可用则恢复下拉状态
        //判断上次请求是否完成，如果没完成则恢复下拉状态
        if (NetUtil.isNetworkAvailable() && isComplete) {
            isComplete = false;        //标识请求开始

            return true;
        } else {
            //恢复下拉状态
            finishRefresh();
            return false;
        }
    }

    /**
     * 是否可以加载
     *
     * @return
     */
    public boolean canLoadmore() {
        //判断是否可以加载更多，如果不可以则不再触发加载更多事件，并结束本次加载状态
        if (isLoad) {

            //检查网络是否可用，如果不可用则恢复加载状态
            //判断上次请求是否完成，如果没完成则恢复加载状态
            if (NetUtil.isNetworkAvailable() && isComplete) {               //getList为true时，表示上一次请求已完成，可以开始发起请求
                isComplete = false;        //标识请求开始
                return true;
            } else {

                if (null != mRefreshLayout) {
                    mRefreshLayout.finishLoadmore();       //完成加载，结束加载动画
                }

                return false;
            }
        } else {
            //不再触发加载更多事件
            completeLoadMore();
            return false;
        }
    }


    /**
     * 不再触发加载更多事件
     */
    public void completeLoadMore() {
        //显示没有更多数据
        if (mAdapter != null) {
            mAdapter.loadMoreComplete();
        }

        if (null != mRefreshLayout) {
            //将不会再次触发加载更多事件
            mRefreshLayout.finishLoadmore();       //完成加载，结束加载动画
        }
    }

    /**
     * 完成数据刷新，结束刷新动画，恢复下拉状态
     */
    public void finishRefresh() {
        isComplete = true;        //标识请求结束

        if (null != mRefreshLayout) {
            mRefreshLayout.finishRefresh();                //完成刷新，结束刷新动画
            mRefreshLayout.setLoadmoreFinished(false);     //设置全部数据加载完成，之后不会触发加载事件
        }
    }

    /**
     * 完成加载更多，结束加载动画，恢复上拉状态
     */
    public void finishLoadmore() {
        isComplete = true;        //标识请求结束
        if (null != mRefreshLayout) {
            mRefreshLayout.finishLoadmore();       //完成加载，结束加载动画
        }
    }


    /*
    *
    //下面示例中的值等于默认值
        RefreshLayout refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
        refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
        refreshLayout.setDragRate(0.5f);//显示下拉高度/手指真实下拉高度=阻尼效果
        refreshLayout.setReboundDuration(300);//回弹动画时长（毫秒）

        refreshLayout.setHeaderHeight(100);//Header标准高度（显示下拉高度>=标准高度 触发刷新）
        refreshLayout.setHeaderHeightPx(100);//同上-像素为单位 （V1.1.0删除）
        refreshLayout.setFooterHeight(100);//Footer标准高度（显示上拉高度>=标准高度 触发加载）
        refreshLayout.setFooterHeightPx(100);//同上-像素为单位 （V1.1.0删除）

        refreshLayout.setFooterHeaderInsetStart(0);//设置 Header 起始位置偏移量 1.0.5
        refreshLayout.setFooterHeaderInsetStartPx(0);//同上-像素为单位 1.0.5 （V1.1.0删除）
        refreshLayout.setFooterFooterInsetStart(0);//设置 Footer 起始位置偏移量 1.0.5
        refreshLayout.setFooterFooterInsetStartPx(0);//同上-像素为单位 1.0.5 （V1.1.0删除）

        refreshLayout.setHeaderMaxDragRate(2);//最大显示下拉高度/Header标准高度
        refreshLayout.setFooterMaxDragRate(2);//最大显示下拉高度/Footer标准高度
        refreshLayout.setHeaderTriggerRate(1);//触发刷新距离 与 HeaderHeight 的比率1.0.4
        refreshLayout.setFooterTriggerRate(1);//触发加载距离 与 FooterHeight 的比率1.0.4

        refreshLayout.setEnableRefresh(true);//是否启用下拉刷新功能
        refreshLayout.setEnableLoadMore(false);//是否启用上拉加载功能
        refreshLayout.setEnableAutoLoadMore(true);//是否启用列表惯性滑动到底部时自动加载更多
        refreshLayout.setEnablePureScrollMode(false);//是否启用纯滚动模式
        refreshLayout.setEnableNestedScroll(false);//是否启用嵌套滚动
        refreshLayout.setEnableOverScrollBounce(true);//是否启用越界回弹
        refreshLayout.setEnableScrollContentWhenLoaded(true);//是否在加载完成时滚动列表显示新的内容
        refreshLayout.setEnableHeaderTranslationContent(true);//是否下拉Header的时候向下平移列表或者内容
        refreshLayout.setEnableFooterTranslationContent(true);//是否上拉Footer的时候向上平移列表或者内容
        refreshLayout.setEnableLoadMoreWhenContentNotFull(true);//是否在列表不满一页时候开启上拉加载功能
        refreshLayout.setEnableFooterFollowWhenLoadFinished(false);//是否在全部加载结束之后Footer跟随内容1.0.4
        refreshLayout.setEnableOverScrollDrag(false);//是否启用越界拖动（仿苹果效果）1.0.4

        refreshLayout.setEnableScrollContentWhenRefreshed(true);//是否在刷新完成时滚动列表显示新的内容 1.0.5
        refreshLayout.srlEnableClipHeaderWhenFixedBehind(true);//是否剪裁Header当时样式为FixedBehind时1.0.5
        refreshLayout.srlEnableClipFooterWhenFixedBehind(true);//是否剪裁Footer当时样式为FixedBehind时1.0.5

        refreshLayout.setDisableContentWhenRefresh(false);//是否在刷新的时候禁止列表的操作
        refreshLayout.setDisableContentWhenLoading(false);//是否在加载的时候禁止列表的操作

        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener());//设置多功能监听器
        refreshLayout.setScrollBoundaryDecider(new ScrollBoundaryDecider());//设置滚动边界判断
        refreshLayout.setScrollBoundaryDecider(new ScrollBoundaryDeciderAdapter());//自定义滚动边界

        refreshLayout.setRefreshHeader(new ClassicsHeader(context));//设置Header
        refreshLayout.setRefreshFooter(new ClassicsFooter(context));//设置Footer
        refreshLayout.setRefreshContent(new View(context));//设置刷新Content（用于非xml布局代替addView）1.0.4

        refreshLayout.autoRefresh();//自动刷新
        refreshLayout.autoLoadMore();//自动加载
        refreshLayout.autoRefresh(400);//延迟400毫秒后自动刷新
        refreshLayout.autoLoadMore(400);//延迟400毫秒后自动加载
        refreshLayout.finishRefresh();//结束刷新
        refreshLayout.finishLoadMore();//结束加载
        refreshLayout.finishRefresh(3000);//延迟3000毫秒后结束刷新
        refreshLayout.finishLoadMore(3000);//延迟3000毫秒后结束加载
        refreshLayout.finishRefresh(false);//结束刷新（刷新失败）
        refreshLayout.finishLoadMore(false);//结束加载（加载失败）
        refreshLayout.finishLoadMoreWithNoMoreData();//完成加载并标记没有更多数据 1.0.4
        refreshLayout.closeHeaderOrFooter();//关闭正在打开状态的 Header 或者 Footer（1.1.0）
        refreshLayout.resetNoMoreData();//恢复没有更多数据的原始状态 1.0.4（1.1.0删除）
        refreshLayout.setNoMoreData(false);//恢复没有更多数据的原始状态 1.0.5

    * */


    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    public static void moveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }
    }

}
