package com.berlski.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import com.berlski.custom.util.StringUtil;

/**
 * Created by ccp on 2018/11/24.
 */

public class ShadowButton extends AppCompatTextView {

    public ShadowButton(Context context) {
        super(context);
    }

    public ShadowButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ShadowButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        //对属性进行解析
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ShadowButton);// 由attrs 获得 TypeArray

        String text = ta.getString(R.styleable.ShadowButton_gb_text);
        float textSize = ta.getDimensionPixelSize(R.styleable.ShadowButton_gb_textSize, getCount(R.dimen.dp15));
        int textColor = ta.getColor(R.styleable.ShadowButton_gb_textColor, Color.WHITE);

        if (StringUtil.isEmpty(text)) {
            text = "确定";
        }

        setText(text);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        setTextColor(textColor);

        // left,  top,  right,  bottom
        setPadding(getCount(R.dimen.dp15), 0, getCount(R.dimen.dp15), getCount(R.dimen.dp10));

        //设定最小高度为40dp
        setMinimumHeight(getResources().getDimensionPixelSize(R.dimen.dp40));

        //设定背景
        setBackgroundResource(R.drawable.background_mineinfo_shadow_green);

        //设定文字居中
        setGravity(Gravity.CENTER);
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

    public void setClickable(boolean clickable) {
        super.setClickable(clickable);

        if (clickable) {
            setBackgroundResource(R.drawable.background_mineinfo_shadow_green);
        } else {
            setBackgroundResource(R.drawable.background_mineinfo_shadow_gray);
        }
    }
}
