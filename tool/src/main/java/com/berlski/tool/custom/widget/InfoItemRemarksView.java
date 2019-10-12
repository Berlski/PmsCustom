package com.berlski.tool.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.style.InfoItemDefaultStyle;
import com.berlski.tool.custom.style.InfoItemStyle;
import com.berlski.tool.custom.util.AnimUtil;
import com.berlski.tool.custom.util.ConstraintUtil;
import com.berlski.tool.custom.util.StringUtil;
import com.berlski.tool.custom.util.ToastUtil;
import com.berlski.tool.custom.util.UiUtil;

/**
 * 信息编辑页-输入框，自定义view
 * 功能包含输入类型选择、输入不符提醒、输入内容清除、输入结果监听
 * 输入类型：文字、姓名、手机号、证件号，
 * 证件号包括：身份证、护照、港澳通行证、台湾同胞证
 * <p>
 * Created by Berlski on 2019/9/29.
 */
public class InfoItemRemarksView extends ConstraintLayout implements TextWatcher {

    private static InfoItemStyle mInfoItemStyle;
    private TypedArray ta;
    private EditText mContentView;
    private TextView mItemNameView;

    private static final int TOP_TO_TOP_OF_TITLE = 0x0202;          //与条目名称顶部对齐
    private static final int START_TO_START_OF_TITLE = 0x0203;      //与条目名称左侧对齐
    private static final int START_TOP_TO_END_BOTTOM_OF_TITLE = 0x0204;      //左上与条目名称右下角对齐

    /**
     * 限制文字长度
     */
    private int maxLength = 0;

    /**
     * 默认选择文本文字
     */
    private ConstraintLayout mParent;


    public InfoItemRemarksView(Context context) {
        super(context);
    }

    public InfoItemRemarksView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    public InfoItemRemarksView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        //设定默认样式
        if (mInfoItemStyle == null) {
            mInfoItemStyle = new InfoItemDefaultStyle(context);
        }

        InfoItemStyle style = mInfoItemStyle;

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_info_item_remarks, this);

        mItemNameView = findViewById(R.id.viir_name);
        mParent = findViewById(R.id.viir_parent);
        ImageView tipsIconView = findViewById(R.id.viir_required_marker);
        mContentView = findViewById(R.id.viir_content);
        View bottomLine = findViewById(R.id.viir_bottom_line);

        //对属性进行解析
        // 由attrs 获得 TypeArray
        ta = context.obtainStyledAttributes(attrs, R.styleable.InfoItemRemarksView);

        //设定提示文字
        String hint = ta.getString(R.styleable.InfoItemRemarksView_iirv_content_hint);
        if (StringUtil.isEmpty(hint)) {
            hint = getContext().getString(R.string.please_input);
        }
        mContentView.setHint(hint);

        //设定条目名称
        String itemName = ta.getString(R.styleable.InfoItemRemarksView_iirv_item_name);
        mItemNameView.setText(itemName);

        //条目名称颜色
        int itemNameColor = ta.getColor(R.styleable.InfoItemRemarksView_iirv_item_name_color, style.getItemNameColor());//" format="color" /><!--条目名字体颜色-->
        mItemNameView.setTextColor(itemNameColor);

        //条目名称字体大小
        float itemNameSize = ta.getDimension(R.styleable.InfoItemRemarksView_iirv_item_name_size, style.getItemNameSize());//" format="dimension" /><!--条目名字体大小-->
        mItemNameView.setTextSize(TypedValue.COMPLEX_UNIT_PX, itemNameSize);


        //提示文字颜色
        int contentHintColor = ta.getColor(R.styleable.InfoItemRemarksView_iirv_content_hint_color, style.getContentHintColor());//" format="color" /><!--提示文字颜色-->
        mContentView.setHintTextColor(contentHintColor);

        //内容字体颜色
        int contentColor = ta.getColor(R.styleable.InfoItemRemarksView_iirv_content_color, style.getContentColor());//" format="color" /><!--内容字体颜色-->
        mContentView.setTextColor(contentColor);

        //内容文字大小
        float contentSize = ta.getDimension(R.styleable.InfoItemRemarksView_iirv_content_size, style.getContentSize());//" format="dimension" /><!--内容文字大小-->
        mContentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentSize);

        //设定必选标识
        boolean tipsIsShow = ta.getBoolean(R.styleable.InfoItemRemarksView_iirv_tips_is_show, style.getTipsIsShow());
        boolean tipsIsLeft = ta.getBoolean(R.styleable.InfoItemRemarksView_iirv_tips_is_left, style.getTipsIsLeft());
        if (tipsIsShow) {
            tipsIconView.setVisibility(VISIBLE);

            UiUtil.drawableSetStyleColor(getContext(), tipsIconView.getDrawable());


            //提示标识 icon
            if (ta.hasValue(R.styleable.InfoItemRemarksView_iirv_tips_icon)) {
                tipsIconView.setImageResource(ta.getResourceId(R.styleable.InfoItemRemarksView_iirv_tips_icon, 0));
            } else {
                tipsIconView.setImageDrawable(style.getTipsIcon());
            }

            int tipsIconColor = ta.getColor(R.styleable.InfoItemRemarksView_iirv_tips_icon_color, style.getTipsIconColor());
            UiUtil.drawableSetColor(tipsIconView.getDrawable(), tipsIconColor);


            if (tipsIsLeft) {
                ConstraintUtil constraintUtil = new ConstraintUtil(mParent);
                ConstraintUtil.ConstraintBegin begin = constraintUtil.beginWithAnim();

                begin.Start_toStartOf(R.id.viir_name, R.id.viir_parent);
                begin.Top_toTopOf(R.id.viir_required_marker, R.id.viir_name);
                begin.Bottom_toBottomOf(R.id.viir_required_marker, R.id.viir_name);

                begin.commit();
            } else {
                ConstraintUtil constraintUtil = new ConstraintUtil(mParent);
                ConstraintUtil.ConstraintBegin begin = constraintUtil.beginWithAnim();

                begin.Start_toEndOf(R.id.viir_required_marker, R.id.viir_name);
                begin.Start_toEndOf(R.id.viir_content, R.id.viir_required_marker);
                begin.Top_toTopOf(R.id.viir_required_marker, R.id.viir_name);
                begin.Bottom_toBottomOf(R.id.viir_required_marker, R.id.viir_name);

                begin.commit();
            }

        } else {
            tipsIconView.setVisibility(GONE);
        }

        //设定内容对齐方式
        int contentAlign = ta.getInteger(R.styleable.InfoItemRemarksView_iirv_content_align, TOP_TO_TOP_OF_TITLE);
        setContentAlign(contentAlign, tipsIsShow, tipsIsLeft);

        //设定底部线的显示隐藏
        boolean isShowbottomLine = ta.getBoolean(R.styleable.InfoItemRemarksView_iirv_bottom_line_is_show, style.getBottomLineIsShow());
        if (isShowbottomLine) {
            bottomLine.setVisibility(VISIBLE);

        } else {
            bottomLine.setVisibility(GONE);
        }

        //如果有设定文字长度则使用外设长度，否则就使用默认长度
        maxLength = ta.getInteger(R.styleable.InfoItemRemarksView_iirv_content_length, maxLength);


        mParent.setMinHeight(getMinHeight());
    }

    private void setContentAlign(int contentAlign, boolean tipsIsShow, boolean tipsIsLeft) {

        ConstraintUtil constraintUtil = new ConstraintUtil(mParent);
        ConstraintUtil.ConstraintBegin begin = constraintUtil.beginWithAnim();

        switch (contentAlign) {

            //与条目名称顶部对齐
            case TOP_TO_TOP_OF_TITLE:
                begin.setMarginTop(R.id.viir_name, getCount(R.dimen.dp15));
                begin.setMarginLeft(R.id.viir_content, getCount(R.dimen.dp10));

                begin.Top_toTopOf(R.id.viir_name, R.id.viir_parent);
                begin.Start_toStartOf(R.id.viir_name, R.id.viir_parent);
                begin.clear(R.id.viir_name, ConstraintSet.BOTTOM);


                begin.Start_toEndOf(R.id.viir_content, R.id.viir_name);
                begin.Top_toTopOf(R.id.viir_content, R.id.viir_name);
                begin.Bottom_toBottomOf(R.id.viir_content, R.id.viir_parent);
                begin.End_toEndOf(R.id.viir_content, R.id.viir_parent);


                //begin.setMarginRight(R.id.viir_name, getCount(R.dimen.dp10));
                begin.commit();

                mContentView.setSingleLine(false);
                mContentView.setGravity(Gravity.LEFT);
                break;

            //与条目名称左侧对齐
            case START_TO_START_OF_TITLE:
                begin.Start_toStartOf(R.id.viir_content, R.id.viir_name);
                begin.Top_toBottomOf(R.id.viir_content, R.id.viir_name);
                begin.Bottom_toBottomOf(R.id.viir_content, R.id.viir_parent);
                begin.End_toEndOf(R.id.viir_content, R.id.viir_parent);
                begin.commit();

                mContentView.setSingleLine(false);

                mContentView.setGravity(Gravity.LEFT);
                break;

            //左上与条目名称右下角对齐
            case START_TOP_TO_END_BOTTOM_OF_TITLE:
                begin.Start_toEndOf(R.id.viir_content, R.id.viir_name);
                begin.Top_toBottomOf(R.id.viir_content, R.id.viir_name);
                begin.Bottom_toBottomOf(R.id.viir_content, R.id.viir_parent);
                begin.End_toEndOf(R.id.viir_content, R.id.viir_parent);
                begin.commit();

                mContentView.setSingleLine(false);

                mContentView.setGravity(Gravity.LEFT);
                break;
        }
    }

    /**
     * 获取输入框内容是否为空的boolean值
     *
     * @return
     */
    public boolean isNotEmpty() {
        return StringUtil.isNotEmpty(getText());
    }

    /**
     * 获取输入框内容是否为空的boolean值
     *
     * @return
     */
    public boolean isEmpty() {
        return StringUtil.isEmpty(getText());
    }

    /**
     * 获取输入框内容的长度
     *
     * @return
     */
    public int length() {
        return getText().length();
    }

    /**
     * 获取输入框内容
     *
     * @return
     */
    public String getText() {
        return mContentView.getText().toString().trim();
    }

    public void setItemName(String itemName) {
        mItemNameView.setText(itemName);
    }

    public void setItemNameColor(int color) {
        mItemNameView.setTextColor(color);
    }

    /**
     * 从外部设定的输入框内容
     *
     * @param content
     */
    public void setText(String content) {
        mContentView.setText(content);
    }

    /**
     * 当输入框里面内容发生变化的时候回调的方法，这个方法是在Text改变之前被调用，
     * 它的意思就是说在原有的文本s中，从start开始的count个字符将会被一个新的长度为after的文本替换，注意这里是将被替换，还没有被替换。
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {

        // e("s:" + s + "  start:" + start + " before:" + before + " after:" + after);
        //输入的类容
        CharSequence input = s.subSequence(start, start + after);
        //e("输入信息:" + input);
        // 退格
        if (after == 0) return;

        //如果 输入的类容包含有Emoji
        if (StringUtil.isEmojiCharacter(input)) {
            //那么就去掉
            mContentView.setText(StringUtil.removeEmoji(s));
            AnimUtil.setShakeAnim(mContentView, 5);

            //光标移动到修改的地方(使光标位置不变) TODO 这里可能会有更好的解决方案
            mContentView.setSelection(start);

            ToastUtil.showToast("不支持表情输入!");
        }


        //如果输入框的内容长度超过限定长度时,超出的部分 砍掉~
        if (s.length() > maxLength) {

            try {
                String front = s.subSequence(0, start).toString();
                String middle = input.subSequence(0, input.length() - (s.length() - maxLength)).toString();
                String hinder = s.subSequence(start + after, s.length()).toString();

                mContentView.setText(front + middle + hinder);
            } catch (Exception e) {

            }

            AnimUtil.setShakeAnim(mContentView, 5);

            //光标移动到修改的地方(使光标位置不变) TODO 这里可能会有更好的解决方案
            mContentView.setSelection(start);
        }

        // 只允许字母、数字和汉字
        //stringFilter()
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        //对输入结果的监听
        if (mAfterChangedListener != null) {
            mAfterChangedListener.onAfter(s.toString().trim());
        }
    }

    private AfterChangedListener mAfterChangedListener;

    /**
     * 设定对输入结果的监听接口
     *
     * @param mAfterChangedListener
     */
    public void setAfterChangedListener(AfterChangedListener mAfterChangedListener) {
        this.mAfterChangedListener = mAfterChangedListener;
    }

    /**
     * 对输入结果的监听接口
     */
    public interface AfterChangedListener {
        void onAfter(String s);
    }

    public void setName(String name) {
        mItemNameView.setText(name);
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
     * 统一全局的MenuButton样式，建议在{@link android.app.Application#onCreate()}中初始化
     */
    public static void initStyle(InfoItemStyle style) {
        InfoItemRemarksView.mInfoItemStyle = style;
    }
}
