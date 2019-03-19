package com.berlski.tool.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.adapter.SwitchViewAdapter;
import com.berlski.tool.custom.inter.SwitchButtonInter;
import com.berlski.tool.custom.util.GridSpacingItemDecoration;
import com.berlski.tool.custom.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class SwitchView extends LinearLayout {

    private List<String> itemNames = new ArrayList<>();

    private SwitchButtonInter inter;
    private RecyclerView mRecyclerView;
    private String maxText = "";
    private SwitchViewAdapter mAdapter;

    public SwitchView(Context context) {
        super(context);
    }

    public SwitchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_switch, this);

        mRecyclerView = findViewById(R.id.rv_vs_recycler_view);


        //对属性进行解析
        // 由attrs 获得 TypeArray
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SwitchView);

        //默认选中项
        int defaultSelect = ta.getInteger(R.styleable.SwitchView_sv_default_select, 0);

        String item_one = ta.getString(R.styleable.SwitchView_sv_option_one);
        String item_two = ta.getString(R.styleable.SwitchView_sv_option_two);
        String item_three = ta.getString(R.styleable.SwitchView_sv_option_three);
        String item_four = ta.getString(R.styleable.SwitchView_sv_option_four);
        String item_five = ta.getString(R.styleable.SwitchView_sv_option_five);

        if (StringUtil.isNotEmpty(item_one)) {
            itemNames.add(item_one);
        }

        if (StringUtil.isNotEmpty(item_two)) {
            itemNames.add(item_two);
        }

        if (StringUtil.isNotEmpty(item_three)) {
            itemNames.add(item_three);
        }

        if (StringUtil.isNotEmpty(item_four)) {
            itemNames.add(item_four);
        }

        if (StringUtil.isNotEmpty(item_five)) {
            itemNames.add(item_five);
        }

        //获取所有条目最长文字长度
        int maxTextLength = 0;
        for (String s : itemNames) {
            if (s.length() > maxTextLength) {
                maxTextLength = s.length();
                maxText = s;
            }
        }

        GridLayoutManager mgr = new GridLayoutManager(context, itemNames.size());

        //设置布局管理器
        mRecyclerView.setLayoutManager(mgr);

        //给列表设定数据适配器
        mAdapter = new SwitchViewAdapter(getContext(), itemNames, inter);
        mAdapter.setSelectedPosition(defaultSelect);

        mRecyclerView.setAdapter(mAdapter);

        //设定grid间隙
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(itemNames.size(), getCount(R.dimen.dp1), false));

        //设置继承BaseQuickAdapter的数据适配器的条目点击监听
        //mAdapter.setOnItemClickListener(this);
    }

    /**
     * 设定默认选中项
     *
     * @param defaultSelect
     */
    public void setDefaultSelect(int defaultSelect) {
        mAdapter.setSelectedPosition(defaultSelect);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 设定选择监听
     *
     * @param inter
     */
    public void setSwitchListener(SwitchButtonInter inter) {
        this.inter = inter;
    }

    /**
     * 设定外部数据、选择监听
     * 默认选择第一个
     *
     * @param list
     * @param inter
     */
    public void setSwitchListener(List<String> list, SwitchButtonInter inter) {
        this.itemNames = list;
        this.inter = inter;

        mAdapter.notifyDataSetChanged();
    }

    /**
     * 设定外部数据、默认选中项、选择监听
     *
     * @param list
     * @param defaultSelect
     * @param inter
     */
    public void setSwitchListener(List<String> list, int defaultSelect, SwitchButtonInter inter) {
        this.itemNames = list;
        this.inter = inter;

        mAdapter.setSelectedPosition(defaultSelect);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 获取选中项角标
     *
     * @return
     */
    public int getSelected() {
        return mAdapter.getSelectedPosition();
    }

    /**
     * 根据文本的
     *
     * @param textSize
     * @return
     */
    public int getTextWidth(float textSize) {

        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(sp2px(textSize));
        return (int) textPaint.measureText(maxText);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据dimen值计算返回对应屏幕的px值，
     *
     * @param id R.dimen.id
     * @return
     */
    private int getCount(int id) {
        return getResources().getDimensionPixelSize(id);
    }

}
