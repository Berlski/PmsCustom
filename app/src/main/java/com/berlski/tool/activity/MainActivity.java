package com.berlski.tool.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.berlski.tool.ItemViewAdapter;
import com.berlski.tool.R;
import com.berlski.tool.RecyclerUtil;
import com.berlski.tool.test.ItemBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemClickListener {

    List<ItemBean> itemBeans = Arrays.asList(
            new ItemBean("ClickActivity", ClickActivity.class),
            new ItemBean("InfoEntryActivity", InfoEntryActivity.class),
            new ItemBean("MenuButtonActivity", MenuButtonActivity.class),
            new ItemBean("NumberRangeActivity", NumberRangeActivity.class),
            new ItemBean("NumberRangeSliderActivity", NumberRangeSliderActivity.class),
            new ItemBean("SelectBeanActivity", SelectBeanActivity.class),
            new ItemBean("ShadowButtonActivity", ShadowButtonActivity.class),
            new ItemBean("SpeechInputActivity", SpeechInputActivity.class),
            new ItemBean("条目选项卡", SwitchOptionActivity.class)
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.rv_rlv_recycler);
        SmartRefreshLayout refreshLayout = findViewById(R.id.srl_rlv_refresh);


//给列表设定数据适配器
        ItemViewAdapter mAdapter = new ItemViewAdapter(itemBeans);

        RecyclerUtil recyclerUtil = new RecyclerUtil(this, mAdapter, recyclerView, refreshLayout);

        //refreshLayout.setOnRefreshLoadmoreListener(this);     //同时设置上下拉两个监听器

        //设置继承BaseQuickAdapter的数据适配器的条目点击监听
        mAdapter.setOnItemClickListener(this);

        //recyclerUtil.setItemDecorationLine(R.drawable.line_recycyer_decoration);

        //设定底部提示
        recyclerUtil.setFooterHint("没有更多了");

        //开始刷新数据
        //recyclerUtil.autoRefresh();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ItemBean itemBean = itemBeans.get(position);

        startActivity(new Intent(this, itemBean.getaClass()));
    }
}
