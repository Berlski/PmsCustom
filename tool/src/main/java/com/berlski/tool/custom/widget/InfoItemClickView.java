package com.berlski.tool.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.style.InfoItemDefaultStyle;
import com.berlski.tool.custom.style.InfoItemStyle;
import com.berlski.tool.custom.util.ConstraintUtil;
import com.berlski.tool.custom.util.StringUtil;
import com.berlski.tool.custom.util.UiUtil;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * 信息编辑、信息展示 - 条目，用来表示可点击的，自定义view
 */
public class InfoItemClickView extends ConstraintLayout {

    private boolean tipsIsShow;
    private float contentMarginEnd;
    private String mContentId;
    private String mContentText;
    private String mItemName;

    private AVLoadingIndicatorView mLoadView;
    private TextView mContentView;
    private TextView mItemNameView;
    private ImageView mRightIcon;


    private static InfoItemStyle mInfoItemStyle;

    public InfoItemClickView(Context context) {
        super(context);
    }

    public InfoItemClickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    public InfoItemClickView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }

    private void initView(Context context, @Nullable AttributeSet attrs) {
        //设定默认样式
        if (mInfoItemStyle == null) {
            mInfoItemStyle = new InfoItemDefaultStyle(context);
        }

        InfoItemStyle style = mInfoItemStyle;

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_info_item_click_view, this);

        ConstraintLayout mParent = findViewById(R.id.viicv_parent);
        mItemNameView = findViewById(R.id.tv_bsi_name);
        ImageView tipsIconView = findViewById(R.id.iv_bsi_required_marker);
        mContentView = findViewById(R.id.tv_bsi_content);
        mRightIcon = findViewById(R.id.iv_bsi_right_icon);
        mLoadView = findViewById(R.id.avi_bsi_load);
        View bottomLine = findViewById(R.id.viicv_bottom_line);
        mLoadView.setVisibility(GONE);

        //对属性进行解析
        // 由attrs 获得 TypeArray
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.InfoItemClickView);

        //设定提示
        String hint = ta.getString(R.styleable.InfoItemClickView_iicv_content_hint);
        if (StringUtil.isEmpty(hint)) {
            hint = getContext().getString(R.string.please_choose);
        }

        if (context.getString(R.string.null_string).equals(hint)) {
            hint = "";
        }

        mContentView.setHint(hint);

        //设定选项名称
        mItemName = ta.getString(R.styleable.InfoItemClickView_iicv_item_name);
        mItemNameView.setText(mItemName);

        //条目名称颜色
        int itemNameColor = ta.getColor(R.styleable.InfoItemClickView_iicv_item_name_color, style.getItemNameColor());
        mItemNameView.setTextColor(itemNameColor);

        //条目名称字体大小
        float itemNameSize = ta.getDimension(R.styleable.InfoItemClickView_iicv_item_name_size, style.getItemNameSize());
        mItemNameView.setTextSize(TypedValue.COMPLEX_UNIT_PX, itemNameSize);

        //右侧图标
        if (ta.hasValue(R.styleable.InfoItemClickView_iicv_right_icon)) {
            mRightIcon.setImageResource(ta.getResourceId(R.styleable.InfoItemClickView_iicv_right_icon, 0));
        } else {
            mRightIcon.setImageDrawable(style.getRightIcon());
        }

        //右侧图标颜色
        int rightIconColor = ta.getColor(R.styleable.InfoItemClickView_iicv_right_icon_color, style.getRightIconColor());
        if (rightIconColor != 0) {
            UiUtil.drawableSetColor(mRightIcon.getDrawable(), rightIconColor);
        }


        //设定右侧图片大小
        float rightIconSize = ta.getDimension(R.styleable.InfoItemClickView_iicv_right_icon_size, style.getRightIconSize());
        if (rightIconSize != 0) {
            ViewGroup.LayoutParams rightIconLp = mRightIcon.getLayoutParams();
            rightIconLp.height = (int) rightIconSize;
            rightIconLp.width = (int) rightIconSize;
        }

        //设定提示标记图片大小
        float tipsIconSize = ta.getDimension(R.styleable.InfoItemClickView_iicv_tips_icon_size, style.getTipsIconSize());
        if (tipsIconSize != 0) {
            ViewGroup.LayoutParams rightIconLp = tipsIconView.getLayoutParams();
            rightIconLp.height = (int) tipsIconSize;
            rightIconLp.width = (int) tipsIconSize;
        }

        //设定内容后外间距
        contentMarginEnd = ta.getDimension(R.styleable.InfoItemClickView_iicv_content_margin_end, 0);

        //设定是否显示右侧icon
        boolean rightIconIsShow = ta.getBoolean(R.styleable.InfoItemClickView_iicv_right_icon_is_show, style.getRightIconIsShow());
        setRightIconIsShow(rightIconIsShow);

        //内容提示字体颜色
        int hintColor = ta.getColor(R.styleable.InfoItemClickView_iicv_content_hint_color, style.getContentHintColor());
        mContentView.setHintTextColor(hintColor);

        //设定内容后外间距
        int contentColor = ta.getColor(R.styleable.InfoItemClickView_iicv_content_color, style.getContentColor());
        mContentView.setTextColor(contentColor);

        //内容文字字体大小
        float contentSize = ta.getDimension(R.styleable.InfoItemClickView_iicv_content_size, style.getContentSize());
        mContentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentSize);


        //设定必选标识
        tipsIsShow = ta.getBoolean(R.styleable.InfoItemClickView_iicv_tips_is_show, style.getTipsIsShow());
        if (tipsIsShow) {
            tipsIconView.setVisibility(VISIBLE);

            //提示标识 icon
            if (ta.hasValue(R.styleable.InfoItemClickView_iicv_tips_icon)) {
                tipsIconView.setImageResource(ta.getResourceId(R.styleable.InfoItemClickView_iicv_tips_icon, 0));
            } else {
                tipsIconView.setImageDrawable(style.getTipsIcon());
            }

            int tipsIconColor = ta.getColor(R.styleable.InfoItemClickView_iicv_tips_icon_color, style.getTipsIconColor());
            UiUtil.drawableSetColor(tipsIconView.getDrawable(), tipsIconColor);

            boolean tipsIsLeft = ta.getBoolean(R.styleable.InfoItemClickView_iicv_tips_is_left, style.getTipsIsLeft());
            if (tipsIsLeft) {
                ConstraintUtil constraintUtil = new ConstraintUtil(mParent);
                ConstraintUtil.ConstraintBegin begin = constraintUtil.beginWithAnim();
                begin.Start_toStartOf(R.id.tv_bsi_name, R.id.viicv_parent);
                begin.Top_toTopOf(R.id.iv_bsi_required_marker, R.id.tv_bsi_name);
                begin.Bottom_toBottomOf(R.id.iv_bsi_required_marker, R.id.tv_bsi_name);
                begin.commit();
            } else {
                ConstraintUtil constraintUtil = new ConstraintUtil(mParent);
                ConstraintUtil.ConstraintBegin begin = constraintUtil.beginWithAnim();
                begin.Start_toEndOf(R.id.iv_bsi_required_marker, R.id.tv_bsi_name);
                begin.Top_toTopOf(R.id.iv_bsi_required_marker, R.id.tv_bsi_name);
                begin.Bottom_toBottomOf(R.id.iv_bsi_required_marker, R.id.tv_bsi_name);
                begin.commit();
            }

        } else {
            tipsIconView.setVisibility(GONE);
        }


        //设定底部线的显示隐藏
        boolean bottomLineIsShow = ta.getBoolean(R.styleable.InfoItemClickView_iicv_bottom_line_is_show, style.getBottomLineIsShow());
        if (bottomLineIsShow) {
            bottomLine.setVisibility(VISIBLE);

            int tipsIconColor = ta.getColor(R.styleable.InfoItemClickView_iicv_bottom_line_color, style.getBottomLineColor());
            bottomLine.setBackgroundColor(tipsIconColor);

            float bottomLineHeight = ta.getDimension(R.styleable.InfoItemClickView_iicv_bottom_line_height, style.getBottomLineHeight());
            ConstraintLayout.LayoutParams layoutParams = (LayoutParams) bottomLine.getLayoutParams();
            layoutParams.height = (int) bottomLineHeight;
            bottomLine.setLayoutParams(layoutParams);

        } else {
            bottomLine.setVisibility(GONE);
        }

        //设定最大宽度，保证其他view不被挤出屏幕
        setContentTextMaxWidth();

        /*if (getBackground() == null) {
            setBackgroundColor(ColorUtil.getColor(getContext(), R.color.white));
        }*/
    }

    private void setContentMarginEnd(float contentMarginEnd) {
        if (contentMarginEnd != 0) {
            LayoutParams layoutParams = (LayoutParams) mContentView.getLayoutParams();
            layoutParams.setMargins(0, 0, (int) contentMarginEnd, 0);//4个参数按顺序分别是左上右下
            mContentView.setLayoutParams(layoutParams);
        }
    }

    /**
     * 设定最大宽度，保证其他view不被挤出屏幕
     */
    private void setContentTextMaxWidth() {
        int maxWidth = getPhoneWidth();        //屏幕宽度

        maxWidth -= getCount(R.dimen.dp15);   //条目标题距左的宽度

        maxWidth -= getTextWidth();    //条目标题宽度

        maxWidth -= tipsIsShow ? getCount(R.dimen.dp30) : 0;      //“是否必选标记”的宽度

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
        paint = mItemNameView.getPaint();
        paint.getTextBounds(mItemName, 0, mItemName.length(), bounds);
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

    public void setItemName(String itemName) {
        mItemNameView.setText(itemName);
    }

    public void setItemNameColor(int color) {
        mItemNameView.setTextColor(color);
    }

    public TextView getContentTextView() {
        return mContentView;
    }

    public TextView getItemNameTextView() {
        return mItemNameView;
    }

    public void setRightIconIsShow(boolean isShow) {
        if (isShow) {
            mRightIcon.setVisibility(VISIBLE);

            setContentMarginEnd(contentMarginEnd);

        } else {
            mRightIcon.setVisibility(GONE);
            setContentMarginEnd(getCount(R.dimen.dp0));
        }
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

    public void setContentTextColor(int color) {
        mContentView.setTextColor(color);
    }

    public String getContentId() {
        return mContentId;
    }

    public void setContentId(String mContentId) {
        this.mContentId = mContentId;
    }

    /**
     * 统一全局的MenuButton样式，建议在{@link android.app.Application#onCreate()}中初始化
     */
    public static void initStyle(InfoItemStyle style) {
        InfoItemClickView.mInfoItemStyle = style;
    }
}
