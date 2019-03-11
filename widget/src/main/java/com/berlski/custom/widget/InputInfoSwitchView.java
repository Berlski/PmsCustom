package com.berlski.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.berlski.custom.util.GridSpacingItemDecoration;
import com.berlski.custom.util.StringUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 信息编辑页，多选一自定义view（view）
 * Created by Berlski on 2019/3/8.
 */
public class InputInfoSwitchView extends LinearLayout implements BaseQuickAdapter.OnItemClickListener {

    private List<String> itemNames = new ArrayList<>();

    private SwitchButtonInter inter;
    private RecyclerView mRecyclerView;
    private String maxText = "";
    private TextAdapter mAdapter;

    public InputInfoSwitchView(Context context) {
        super(context);
    }

    public InputInfoSwitchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputInfoSwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_input_info_switch, this);

        TextView nameText = findViewById(R.id.tv_iis_name);
        ImageView requiredMarker = findViewById(R.id.iv_iis_required_marker);
        mRecyclerView = findViewById(R.id.rv_iis_recycler_view);

        //对属性进行解析
        // 由attrs 获得 TypeArray
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.InputInfoSwitchView);

        //设定必选标识
        boolean requiredBlean = ta.getBoolean(R.styleable.InputInfoSwitchView_iisv_is_required, false);
        if (requiredBlean) {
            requiredMarker.setVisibility(VISIBLE);

        } else {
            requiredMarker.setVisibility(GONE);
        }

        //默认选中项
        int defaultSelect = ta.getInteger(R.styleable.InputInfoSwitchView_iisv_default_select, 0);

        //设定选项名称
        String name = ta.getString(R.styleable.InputInfoSwitchView_iisv_name);
        nameText.setText(name);


        String item_one = ta.getString(R.styleable.InputInfoSwitchView_iisv_item_one);
        String item_two = ta.getString(R.styleable.InputInfoSwitchView_iisv_item_two);
        String item_three = ta.getString(R.styleable.InputInfoSwitchView_iisv_item_three);
        String item_four = ta.getString(R.styleable.InputInfoSwitchView_iisv_item_four);
        String item_five = ta.getString(R.styleable.InputInfoSwitchView_iisv_item_five);

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
        mAdapter = new TextAdapter(itemNames);
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

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        TextAdapter textAdapter = (TextAdapter) adapter;

        textAdapter.setSelectedPosition(position);
        textAdapter.notifyDataSetChanged();

        if (inter != null) {
            inter.switchIndex(position);
        }
    }


    /**
     * 公客私客适配器
     * Created by Administrator on 2017/1/21.
     */
    public class TextAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        //默认选中项
        private int selectedPosition = 0;

        public int getSelectedPosition() {
            return selectedPosition;
        }

        public void setSelectedPosition(int selectedPosition) {
            this.selectedPosition = selectedPosition;
        }

        public TextAdapter(@Nullable List<String> data) {
            super(R.layout.item_input_info_switch, data);
        }

        /**
         * @param holder viewHolder
         * @param item   数据实体类
         */
        @Override
        protected void convert(final BaseViewHolder holder, final String item) {

            TextView textView = holder.getView(R.id.tv_iis_text);

            if (selectedPosition == holder.getAdapterPosition()) {

                textView.setBackgroundResource(0);

                textView.setTextColor(Color.parseColor("#FFFFFF"));
            } else {
                textView.setTextColor(Color.parseColor("#31C27C"));

                if (holder.getAdapterPosition() == 0) {
                    textView.setBackgroundResource(R.drawable.background_switch_button_left);

                } else if (holder.getAdapterPosition() == getData().size() - 1) {
                    textView.setBackgroundResource(R.drawable.background_switch_button_right);
                } else {
                    textView.setBackgroundColor(Color.WHITE);
                }
            }

            textView.setText(item.trim());
            textView.setWidth(getTextWidth(13) + getCount(R.dimen.dp20));
        }
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

    public interface SwitchButtonInter {
        void switchIndex(int index);
    }
}
