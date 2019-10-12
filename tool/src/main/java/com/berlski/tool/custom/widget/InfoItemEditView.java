package com.berlski.tool.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.enums.DocumentType;
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
 * Created by Berlski on 2019/2/18.
 */
public class InfoItemEditView extends ConstraintLayout implements View.OnFocusChangeListener, TextWatcher, View.OnTouchListener {

    private static InfoItemStyle mInfoItemStyle;
    private TypedArray ta;
    private EditText mContentView;
    private TextView mItemNameView;
    private TextView mPostfixView;

    private static final int TEXT = 0x0001;//文本文字
    private static final int NAME = 0x0002;//姓名
    private static final int EMAIL = 0x0003;//电子邮箱
    private static final int PHONE_NO = 0x0004;//手机号
    private static final int CREDENTIAL_NO = 0x0005;//证件号
    private static final int MONEY = 0x0006;//金额


    private static final int START = 0x0201;    //前对齐
    private static final int END = 0x0202;      //后对齐


    /**
     * 限制文字长度
     */
    private int maxLength = 0;

    /**
     * 删除按钮的引用
     */
    private Drawable mClearDrawable;

    /**
     * 控件是否有焦点
     */
    private boolean hasFoucs;

    /**
     * 默认选择文本文字
     */
    private int type = TEXT;
    private ConstraintLayout mParent;


    public InfoItemEditView(Context context) {
        super(context);
    }

    public InfoItemEditView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    public InfoItemEditView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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
        LayoutInflater.from(context).inflate(R.layout.view_info_item_edit_view, this);

        mItemNameView = findViewById(R.id.viiev_name);
        mParent = findViewById(R.id.viiev_parent);
        ImageView tipsIconView = findViewById(R.id.viiev_required_marker);
        mContentView = findViewById(R.id.viiev_content);
        mPostfixView = findViewById(R.id.viiev_postfix);
        View bottomLine = findViewById(R.id.viiev_bottom_line);

        //对属性进行解析
        // 由attrs 获得 TypeArray
        ta = context.obtainStyledAttributes(attrs, R.styleable.InfoItemEditView);

        //设定提示文字
        String hint = ta.getString(R.styleable.InfoItemEditView_iiev_content_hint);
        if (StringUtil.isEmpty(hint)) {
            hint = getContext().getString(R.string.please_input);
        }
        mContentView.setHint(hint);


        //设定内容文字
        String content = ta.getString(R.styleable.InfoItemEditView_iiev_content);
        if (StringUtil.isNotEmpty(content)) {
            mContentView.setText(content);
        }

        //设定条目名称
        String itemName = ta.getString(R.styleable.InfoItemEditView_iiev_item_name);
        mItemNameView.setText(itemName);

        //条目名称颜色
        int itemNameColor = ta.getColor(R.styleable.InfoItemEditView_iiev_item_name_color, style.getItemNameColor());//" format="color" /><!--条目名字体颜色-->
        mItemNameView.setTextColor(itemNameColor);

        //条目名称字体大小
        float itemNameSize = ta.getDimension(R.styleable.InfoItemEditView_iiev_item_name_size, style.getItemNameSize());//" format="dimension" /><!--条目名字体大小-->
        mItemNameView.setTextSize(TypedValue.COMPLEX_UNIT_PX, itemNameSize);


        //提示文字颜色
        int contentHintColor = ta.getColor(R.styleable.InfoItemEditView_iiev_content_hint_color, style.getContentHintColor());//" format="color" /><!--提示文字颜色-->
        mContentView.setHintTextColor(contentHintColor);

        //内容字体颜色
        int contentColor = ta.getColor(R.styleable.InfoItemEditView_iiev_content_color, style.getContentColor());//" format="color" /><!--内容字体颜色-->
        mContentView.setTextColor(contentColor);

        //内容文字大小
        float contentSize = ta.getDimension(R.styleable.InfoItemEditView_iiev_content_size, style.getContentSize());//" format="dimension" /><!--内容文字大小-->
        mContentView.setTextSize(TypedValue.COMPLEX_UNIT_PX, contentSize);


        //设定后缀文本
        String postfix = ta.getString(R.styleable.InfoItemEditView_iiev_postfix);//" format="string" /><!--后缀，如：单位-->
        if (StringUtil.isNotEmpty(postfix)) {
            mPostfixView.setVisibility(VISIBLE);
            mPostfixView.setText(postfix);

            ConstraintUtil constraintUtil = new ConstraintUtil(mParent);
            ConstraintUtil.ConstraintBegin begin = constraintUtil.beginWithAnim();
            begin.End_toEndOf(bottomLine.getId(), R.id.viiev_postfix);
            begin.commit();

            //后缀文字大小
            float postfixSize = ta.getDimension(R.styleable.InfoItemEditView_iiev_postfix_size, getCount(R.dimen.sp14));//" format="string" /><!--后缀文字大小-->
            mPostfixView.setTextSize(TypedValue.COMPLEX_UNIT_PX, postfixSize);

            //后缀字体颜色
            int postfixColor = ta.getColor(R.styleable.InfoItemEditView_iiev_postfix_color, getContext().getResources().getColor(R.color.black));//" format="string" /><!--后缀文字颜色-->
            mPostfixView.setTextColor(postfixColor);
        } else {
            mPostfixView.setVisibility(GONE);
            ConstraintUtil constraintUtil = new ConstraintUtil(mParent);
            ConstraintUtil.ConstraintBegin begin = constraintUtil.beginWithAnim();
            begin.End_toEndOf(bottomLine.getId(), R.id.viiev_content);
            begin.commit();
        }


        //设定内容对齐方式
        int contentAlign = ta.getInteger(R.styleable.InfoItemEditView_iiev_content_align, END);
        switch (contentAlign) {
            case START:
                mContentView.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
                break;

            case END:
                mContentView.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
                break;
        }

        //设定必选标识
        boolean tipsIsShow = ta.getBoolean(R.styleable.InfoItemEditView_iiev_tips_is_show, style.getTipsIsShow());
        if (tipsIsShow) {
            tipsIconView.setVisibility(VISIBLE);

            UiUtil.drawableSetStyleColor(getContext(), tipsIconView.getDrawable());


            //提示标识 icon
            if (ta.hasValue(R.styleable.InfoItemEditView_iiev_tips_icon)) {
                tipsIconView.setImageResource(ta.getResourceId(R.styleable.InfoItemEditView_iiev_tips_icon, 0));
            } else {
                tipsIconView.setImageDrawable(style.getTipsIcon());
            }

            int tipsIconColor = ta.getColor(R.styleable.InfoItemEditView_iiev_tips_icon_color, style.getTipsIconColor());
            UiUtil.drawableSetColor(tipsIconView.getDrawable(), tipsIconColor);

            boolean tipsIsLeft = ta.getBoolean(R.styleable.InfoItemEditView_iiev_tips_is_left, style.getTipsIsLeft());
            if (tipsIsLeft) {
                ConstraintUtil constraintUtil = new ConstraintUtil(mParent);
                ConstraintUtil.ConstraintBegin begin = constraintUtil.beginWithAnim();
                
                begin.Start_toStartOf(R.id.viiev_name, R.id.viiev_parent);
                begin.Top_toTopOf(R.id.viiev_required_marker, R.id.viiev_name);
                begin.Bottom_toBottomOf(R.id.viiev_required_marker, R.id.viiev_name);

                begin.commit();
            } else {
                ConstraintUtil constraintUtil = new ConstraintUtil(mParent);
                ConstraintUtil.ConstraintBegin begin = constraintUtil.beginWithAnim();

                begin.Start_toEndOf(R.id.viiev_required_marker, R.id.viiev_name);
                begin.Start_toEndOf(R.id.viiev_content, R.id.viiev_required_marker);
                begin.Top_toTopOf(R.id.viiev_required_marker, R.id.viiev_name);
                begin.Bottom_toBottomOf(R.id.viiev_required_marker, R.id.viiev_name);

                begin.commit();
            }
            
        } else {
            tipsIconView.setVisibility(GONE);
        }

        //设定底部线的显示隐藏
        boolean isShowbottomLine = ta.getBoolean(R.styleable.InfoItemEditView_iiev_bottom_line_is_show, style.getBottomLineIsShow());
        if (isShowbottomLine) {

            int tipsIconColor = ta.getColor(R.styleable.InfoItemEditView_iiev_bottom_line_color, style.getBottomLineColor());
            bottomLine.setBackgroundColor(tipsIconColor);

            float bottomLineHeight = ta.getDimension(R.styleable.InfoItemEditView_iiev_bottom_line_height, style.getBottomLineHeight());
            ConstraintLayout.LayoutParams layoutParams = (LayoutParams) bottomLine.getLayoutParams();
            layoutParams.height = (int) bottomLineHeight;
            bottomLine.setLayoutParams(layoutParams);

            bottomLine.setVisibility(VISIBLE);

        } else {
            bottomLine.setVisibility(GONE);
        }

        //获取输入类型
        type = ta.getInteger(R.styleable.InfoItemEditView_iiev_type, 0x0001);

        switch (type) {

            case TEXT:
                maxLength = 20;
                break;

            case NAME:
                maxLength = 20;
                break;

            case EMAIL:
                maxLength = 30;
                mContentView.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                break;

            case PHONE_NO:
                //限定输入类型为电话格式
                mContentView.setInputType(InputType.TYPE_CLASS_PHONE);
                //限定输入字数为11
                maxLength = 11;
                break;

            case CREDENTIAL_NO:
                //初始化证件类型
                int credential_type = ta.getInteger(R.styleable.InfoItemEditView_iiev_credential_type, 0x0021);
                setDocumentType(credential_type);
                break;

            case MONEY:
                //限定输入类型为电话格式
                mContentView.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
                //限定输入字数为11
                maxLength = 10;
                break;
        }

        //如果有设定文字长度则使用外设长度，否则就使用默认长度
        maxLength = ta.getInteger(R.styleable.InfoItemEditView_iiev_content_length, maxLength);

        setClearDrawable();
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
     * 初始化输入框清除按钮
     */
    private void setClearDrawable() {
        //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        mClearDrawable = mContentView.getCompoundDrawables()[2];
        if (mClearDrawable == null) {
//          throw new NullPointerException("You can add drawableRight attribute in XML");
            mClearDrawable = getResources().getDrawable(R.drawable.ic_cancel_black);
        }

        mClearDrawable.setBounds(0, 0, 60, 60);//mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        //默认设置隐藏图标
        setClearIconVisible(false);
        //设置焦点改变的监听
        mContentView.setOnFocusChangeListener(this);
        //设置输入框里面内容发生改变的监听
        mContentView.addTextChangedListener(this);

        mContentView.setOnTouchListener(this);
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
     * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件
     * 当我们按下的位置 在  EditText的宽度 - 图标到控件右边的间距 - 图标的宽度  和
     * EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向就没有考虑
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (mContentView.getCompoundDrawables()[2] != null) {

                boolean touchable = event.getX() > (mContentView.getWidth() - mContentView.getTotalPaddingRight()) && (event.getX() < ((mContentView.getWidth() - mContentView.getPaddingRight())));

                if (touchable) {
                    mContentView.setText("");
                }
            }
        }

        return super.onTouchEvent(event);
    }

    /**
     * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
     */
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        this.hasFoucs = hasFocus;

        if (hasFocus) {
            setClearIconVisible(length() > 0);
        } else {
            setClearIconVisible(false);
        }
    }


    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    protected void setClearIconVisible(boolean visible) {
        Drawable right = visible ? mClearDrawable : null;
        mContentView.setCompoundDrawables(mContentView.getCompoundDrawables()[0], mContentView.getCompoundDrawables()[1], right, mContentView.getCompoundDrawables()[3]);
    }


    /**
     * 当输入框里面内容发生变化的时候回调的方法，这个方法是在Text改变之前被调用，
     * 它的意思就是说在原有的文本s中，从start开始的count个字符将会被一个新的长度为after的文本替换，注意这里是将被替换，还没有被替换。
     */
    @Override
    public void onTextChanged(CharSequence s, int start, int count, int after) {
        if (hasFoucs) {
            setClearIconVisible(s.length() > 0);
        }

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

    /**
     * 除了身份证的证件输入内容限定值
     */
    private String inputKey = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.-";

    private DocumentType documentType;

    /**
     * 获取证件类型值
     *
     * @return
     */
    public String getType() {
        if (documentType != null) {
            return documentType.getType();
        } else {
            return null;
        }
    }

    public void setName(String name) {
        mItemNameView.setText(name);
    }

    /**
     * 初始化证件类型，和各证件的输入限定
     *
     * @param documentType
     */
    public void setDocumentType(int documentType) {

        switch (documentType) {

            //身份证
            case 0x0021:
                this.documentType = DocumentType.ID_CARD;
                maxLength = 18;

                mContentView.setKeyListener(DigitsKeyListener.getInstance("0123456789xX"));
                break;

            //护照
            case 0x0022:
                this.documentType = DocumentType.PASSPORT;
                maxLength = 100;

                mContentView.setKeyListener(DigitsKeyListener.getInstance(inputKey));
                break;

            //港澳通行证
            case 0x0023:
                this.documentType = DocumentType.HONGKONG_AND_MACAO_PASS;
                maxLength = 100;

                mContentView.setKeyListener(DigitsKeyListener.getInstance(inputKey));
                break;

            //台湾同胞证
            case 0x0024:
                this.documentType = DocumentType.TAIWAN_COMPATRIOT_CARD;
                maxLength = 100;

                mContentView.setKeyListener(DigitsKeyListener.getInstance(inputKey));
                break;
        }

        mItemNameView.setText(this.documentType.getTypeName());
    }

    /**
     * 初始化证件类型，和各证件的输入限定
     *
     * @param documentType
     */
    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;

        mItemNameView.setText(documentType.getTypeName());

        switch (documentType) {

            //身份证
            case ID_CARD:
                maxLength = 18;

                mContentView.setKeyListener(DigitsKeyListener.getInstance("0123456789xX"));
                break;

            //护照
            case PASSPORT:
                maxLength = 100;

                mContentView.setKeyListener(DigitsKeyListener.getInstance(inputKey));
                break;

            //港澳通行证
            case HONGKONG_AND_MACAO_PASS:
                maxLength = 100;

                mContentView.setKeyListener(DigitsKeyListener.getInstance(inputKey));
                break;

            //台湾同胞证
            case TAIWAN_COMPATRIOT_CARD:
                maxLength = 100;

                mContentView.setKeyListener(DigitsKeyListener.getInstance(inputKey));
                break;
        }
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
        InfoItemEditView.mInfoItemStyle = style;
    }
}
