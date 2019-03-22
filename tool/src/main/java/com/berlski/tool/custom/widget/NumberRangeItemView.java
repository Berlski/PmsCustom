package com.berlski.tool.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.dialog.NumberSectionDialog;
import com.berlski.tool.custom.inter.NumberRangeInter;
import com.berlski.tool.custom.util.StringUtil;
import com.berlski.tool.custom.util.UiUtil;

public class NumberRangeItemView extends LinearLayout implements View.OnClickListener {

    private TextView mMinNoView;
    private TextView mMaxNoView;
    private int minLimit;
    private int maxLimit;

    public NumberRangeItemView(Context context) {
        super(context);
    }

    public NumberRangeItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberRangeItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_number_range_item, this);

        TextView nameText = findViewById(R.id.tv_nri_name);

        mMinNoView = findViewById(R.id.tv_nri_min_no);
        mMaxNoView = findViewById(R.id.tv_nri_max_no);

        //对属性进行解析
        // 由attrs 获得 TypeArray
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NumberRangeItemView);

        //设定必选标识
        boolean requiredBlean = ta.getBoolean(R.styleable.NumberRangeItemView_nriv_is_required, false);
        if (requiredBlean) {
            UiUtil.setRequiredMarkerLabel(nameText);
        }

        //设定选项名称
        String name = ta.getString(R.styleable.NumberRangeItemView_nriv_name);
        nameText.setText(name);

        //设定最小数提示文字
        minLimit = ta.getInteger(R.styleable.NumberRangeItemView_nriv_min_limit, 500);

        //如果文字不为空，则不设定提示
        String minText = ta.getString(R.styleable.NumberRangeItemView_nriv_min_text);
        if (StringUtil.isNotEmpty(minText)) {
            mMinNoView.setText(minText);
        } else {
            String minHint = ta.getString(R.styleable.NumberRangeItemView_nriv_min_hint);
            mMinNoView.setHint(minHint);
        }


        //设定最小数提示文字
        maxLimit = ta.getInteger(R.styleable.NumberRangeItemView_nriv_max_limit, 10000);

        //如果文字不为空，则不设定提示
        String maxText = ta.getString(R.styleable.NumberRangeItemView_nriv_max_text);
        if (StringUtil.isNotEmpty(maxText)) {
            mMaxNoView.setText(maxText);
        } else {
            String maxHint = ta.getString(R.styleable.NumberRangeItemView_nriv_max_hint);
            mMaxNoView.setHint(maxHint);
        }

        setOnClickListener(this);
    }

    /**
     * 获取最小数、最大数内容是否为空的boolean值
     *
     * @return
     */
    public boolean isNotEmpty() {
        if (StringUtil.isNotEmpty(getMinText()) && StringUtil.isNotEmpty(getMaxText())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取最小数、最大数内容是否为空的boolean值
     *
     * @return
     */
    public boolean isEmpty() {
        if (StringUtil.isEmpty(getMinText()) || StringUtil.isEmpty(getMaxText())) {
            return true;
        } else {
            return false;
        }
    }

    public void setMinText(String s) {
        mMinNoView.setText(s);
    }

    public void setMaxText(String s) {
        mMaxNoView.setText(s);
    }

    public String getMinText() {
        return mMinNoView.getText().toString().trim();
    }

    public String getMaxText() {
        return mMaxNoView.getText().toString().trim();
    }

    @Override
    public void onClick(View v) {
        screeningPrice();
    }

    private void screeningPrice() {
        int minPrice = 0;
        int maxPrice = 0;

        try {
            //获取view中最低数字
            minPrice = Integer.parseInt(getMinText());
        } catch (Exception e) {
        }

        //如果数字比最低限定还低，则以最低限定为准
        if (minLimit > minPrice) {
            minPrice = minLimit;
        }

        try {
            //获取view中最高数字
            maxPrice = Integer.parseInt(getMaxText());
        } catch (Exception e) {
        }

        //如果view数字比最高限定还高，或者view数字
        if (maxLimit < maxPrice || minLimit > maxPrice) {
            maxPrice = maxLimit;
        }

        NumberSectionDialog.builder(getContext()).onCreateDialog(minLimit, maxLimit, minPrice, maxPrice, new NumberRangeInter() {
            @Override
            public void onSelect(String minPrice, String maxPrice) {
                /*bean.setMinPrice(minPrice);
                bean.setMaxPrice(maxPrice);*/

                mMinNoView.setText(minPrice);
                mMaxNoView.setText(maxPrice);

                if (StringUtil.isNotEmpty(minPrice)) {
                    mMinNoView.setHint("");
                }

                if (StringUtil.isNotEmpty(maxPrice)) {
                    mMaxNoView.setHint("");
                }
            }

            @Override
            public void onCancel() {

            }
        });
    }
}
