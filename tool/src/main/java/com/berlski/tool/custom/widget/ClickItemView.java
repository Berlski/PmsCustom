package com.berlski.tool.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.util.ColorUtil;
import com.berlski.tool.custom.util.StringUtil;
import com.berlski.tool.custom.util.UiUtil;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * 信息编辑页，下弹菜单自定义view
 */
public class ClickItemView extends LinearLayout {

    private String mContentId;
    private String mContentText;

    private AVLoadingIndicatorView mLoadView;
    private TextView mContentView;
    private TextView mNameView;
    private boolean isRequired;

    private String mNameText;

    public ClickItemView(Context context) {
        super(context);
    }

    public ClickItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    public ClickItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }

    private void initView(Context context, @Nullable AttributeSet attrs) {

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_click_item, this);

        setOrientation(VERTICAL);

        mNameView = findViewById(R.id.tv_bsi_name);
        ImageView requiredMarker = findViewById(R.id.iv_bsi_required_marker);
        mContentView = findViewById(R.id.tv_bsi_content);
        ImageView rightIcon = findViewById(R.id.iv_bsi_right_icon);
        mLoadView = findViewById(R.id.avi_bsi_load);
        mLoadView.setVisibility(GONE);

        //对属性进行解析
        // 由attrs 获得 TypeArray
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ClickItemView);

        //设定提示
        String hint = ta.getString(R.styleable.ClickItemView_civ_hint);
        if (StringUtil.isEmpty(hint)) {
            hint = getContext().getString(R.string.please_choose);
        }

        mContentView.setHint(hint);

        int rightIconRes = ta.getResourceId(R.styleable.ClickItemView_civ_right_icon, R.drawable.ic_keyboard_arrow_right);
        rightIcon.setImageResource(rightIconRes);

        //设定右侧图片大小
        float rightIconSize = ta.getDimension(R.styleable.ClickItemView_civ_right_icon_size, 0);
        if (rightIconSize != 0) {
            ViewGroup.LayoutParams rightIconLp = rightIcon.getLayoutParams();
            rightIconLp.height = (int) rightIconSize;
            rightIconLp.width = (int) rightIconSize;
        }

        //设定必选标记图片大小
        float requiredIconSize = ta.getDimension(R.styleable.ClickItemView_civ_required_icon_size, 0);
        if (requiredIconSize != 0) {
            ViewGroup.LayoutParams rightIconLp = requiredMarker.getLayoutParams();
            rightIconLp.height = (int) requiredIconSize;
            rightIconLp.width = (int) requiredIconSize;
        }

        //设定必选标记居左
        float requiredMarginStart = ta.getDimension(R.styleable.ClickItemView_civ_required_margin_start, getCount(R.dimen.dp10));
        if (requiredMarginStart != 0) {

            if (requiredIconSize == 0) {
                requiredIconSize = getCount(R.dimen.dp10);
            }

            LayoutParams params = new LayoutParams((int) requiredIconSize, (int) requiredIconSize);
            params.setMargins((int) requiredMarginStart, 0, 0, 0);
            requiredMarker.setLayoutParams(params);
        }

        //设定内容居右
        float contentMarginEnd = ta.getDimension(R.styleable.ClickItemView_civ_content_margin_end, 0);
        if (contentMarginEnd != 0) {
            LayoutParams layoutParams = (LayoutParams) mContentView.getLayoutParams();
            layoutParams.setMargins(0, 0, (int) contentMarginEnd, 0);//4个参数按顺序分别是左上右下
            mContentView.setLayoutParams(layoutParams);
        }

        //内容文字底色颜色
        int hintColor = ta.getColor(R.styleable.ClickItemView_civ_hint_color, 0);
        if (hintColor != 0) {
            mContentView.setHintTextColor(hintColor);
        }

        //内容文字字体大小
        float hintSize = ta.getDimension(R.styleable.ClickItemView_civ_hint_size, 0);
        if (hintSize != 0) {
            mContentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, hintSize);
        }

        //内容文字颜色
        int contentColor = ta.getColor(R.styleable.ClickItemView_civ_content_color, 0);
        if (contentColor != 0) {
            mContentView.setTextColor(contentColor);
        }

        //内容文字字体大小
        float contentSize = ta.getDimension(R.styleable.ClickItemView_civ_content_size, 0);
        if (contentSize != 0) {
            mContentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentSize);
        }

        //条目名称颜色
        int nameColor = ta.getColor(R.styleable.ClickItemView_civ_name_color, 0);
        if (nameColor != 0) {
            mNameView.setTextColor(nameColor);
        }

        //条目名称颜色
        float nameSize = ta.getDimension(R.styleable.ClickItemView_civ_name_size, 0);
        if (nameSize != 0) {
            mNameView.setTextSize(TypedValue.COMPLEX_UNIT_PX, nameSize);
        }

        //设定必选标识
        isRequired = ta.getBoolean(R.styleable.ClickItemView_civ_is_required, false);
        if (isRequired) {
            requiredMarker.setVisibility(VISIBLE);

            UiUtil.drawableSetStyleColor(getContext(), requiredMarker.getDrawable());

        } else {
            requiredMarker.setVisibility(GONE);
        }

        //设定选项名称
        mNameText = ta.getString(R.styleable.ClickItemView_civ_name);
        mNameView.setText(mNameText);

        //设定最大宽度，保证其他view不被挤出屏幕
        setContentTextMaxWidth();

        //setGravity(Gravity.CENTER_VERTICAL);

        if (getBackground() == null) {
            setBackgroundColor(ColorUtil.getColor(getContext(), R.color.white));
        }

        //getPaddingStart()

        //设定view点击事件
        //this.setOnClickListener(this);
    }

    /**
     * 设定最大宽度，保证其他view不被挤出屏幕
     */
    private void setContentTextMaxWidth() {
        int maxWidth = getPhoneWidth();        //屏幕宽度

        maxWidth -= getCount(R.dimen.dp15);   //条目标题距左的宽度

        maxWidth -= getTextWidth();    //条目标题宽度

        maxWidth -= isRequired ? getCount(R.dimen.dp30) : 0;      //“是否必选标记”的宽度

        maxWidth -= mLoadView.getVisibility() == View.VISIBLE ? getCount(R.dimen.dp25) : 0;   //加载view 的宽度

        maxWidth -= getCount(R.dimen.dp30);   //右角标 的宽度

        mContentView.setMaxWidth(maxWidth);
    }

    /**
     * 获得屏幕的宽
     *
     * @return
     */
    private int getPhoneWidth() {
        //Android 获得屏幕的宽和高
        DisplayMetrics dm = getResources().getDisplayMetrics();

        return dm.widthPixels;
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

    /**
     * 计算文字宽度
     *
     * @return
     */
    private int getTextWidth() {
        Rect bounds = new Rect();
        TextPaint paint;
        paint = mNameView.getPaint();
        paint.getTextBounds(mNameText, 0, mNameText.length(), bounds);
        return bounds.width();
    }

    public void showLoad() {
        mLoadView.setVisibility(VISIBLE);
        mLoadView.show();
    }

    public void hideLoad() {
        mLoadView.hide();
        mLoadView.setVisibility(GONE);
    }

    /**
     * 获取选中内容是否为空的boolean值
     *
     * @return
     */
    public boolean isNotEmpty() {
        return StringUtil.isNotEmpty(mContentText.trim());
    }

    /**
     * 获取选中内容是否为空的boolean值
     *
     * @return
     */
    public boolean isEmpty() {
        return StringUtil.isEmpty(mContentText.trim());
    }

    public String getContentText() {
        return mContentText;
    }

    public void setContentText(String content) {
        this.mContentText = content;
        mContentView.setText(content);
    }

    public String getContentId() {
        return mContentId;
    }

    public void setContentId(String mContentId) {
        this.mContentId = mContentId;
    }
}
