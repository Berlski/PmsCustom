package com.berlski.tool.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.util.StringUtil;
import com.berlski.tool.custom.util.UiUtil;

/**
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 信息编辑页，多选一自定义view（view）
 * Created by Berlski on 2019/3/8.
 */
public class SpeechInputItemView extends LinearLayout implements View.OnClickListener {

    private VoiceToStringInter inter;
    private EditText mContentView;

    public SpeechInputItemView(Context context) {
        super(context);
    }

    public SpeechInputItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpeechInputItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_speech_input_item, this);

        TextView nameText = findViewById(R.id.tv_sii_name);
        ImageView requiredMarker = findViewById(R.id.iv_sii_required_marker);
        ImageView mVoiceView = findViewById(R.id.iv_sii_voice);
        mContentView = findViewById(R.id.et_sii_content);

        //对属性进行解析
        // 由attrs 获得 TypeArray
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SpeechInputItemView);

        //设定必选标识
        boolean requiredBlean = ta.getBoolean(R.styleable.SpeechInputItemView_siiv_is_required, false);
        if (requiredBlean) {
            requiredMarker.setVisibility(VISIBLE);

            UiUtil.drawableSetStyleColor(getContext(), requiredMarker.getDrawable());

        } else {
            requiredMarker.setVisibility(GONE);
        }


        //设定选项名称
        String name = ta.getString(R.styleable.SpeechInputItemView_siiv_name);
        nameText.setText(name);


        //设定输入框内边距
        float editPadding = ta.getDimension(R.styleable.SpeechInputItemView_siiv_edit_padding, 0);
        if (editPadding != 0) {
            mContentView.setPadding((int) editPadding, (int) editPadding, (int) editPadding, (int) editPadding);
        }


        //设定输入框背景
        int editBackground = ta.getResourceId(R.styleable.SpeechInputItemView_siiv_edit_background, 0);
        if (editBackground != 0) {
            mContentView.setBackgroundResource(editBackground);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mContentView.getLayoutParams();
            layoutParams.setMargins(0, getCount(R.dimen.dp10), 0, 0);//4个参数按顺序分别是左上右下
            mContentView.setLayoutParams(layoutParams);
            //mContentView.setMinHeight();

            if (editPadding == 0) {
                mContentView.setPadding(getCount(R.dimen.dp15), getCount(R.dimen.dp15), getCount(R.dimen.dp15), getCount(R.dimen.dp15));
            }
        }

        float minHeight = ta.getDimension(R.styleable.SpeechInputItemView_siiv_min_height, 0);
        float removeHeight = 0;
        //if (StringUtil.isNotEmpty(name)){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) nameText.getLayoutParams();
        removeHeight += layoutParams.height;
        //}
        mContentView.setMinHeight((int) (minHeight - removeHeight));


        String hint = ta.getString(R.styleable.SpeechInputItemView_siiv_hint);
        mContentView.setHint(hint);

        //是否有语音
        boolean voiceBlean = ta.getBoolean(R.styleable.SpeechInputItemView_siiv_is_voice, true);

        if (voiceBlean) {
            mVoiceView.setVisibility(VISIBLE);
            UiUtil.drawableSetStyleColor(getContext(), mVoiceView.getDrawable());
            mVoiceView.setOnClickListener(this);

        } else {
            mVoiceView.setVisibility(GONE);
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

    public void setText(String s) {
        mContentView.setText(s);

        //光标移动到句尾
        mContentView.setSelection(length());
    }

    public String getText() {
        return mContentView.getText().toString().trim();
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
     * 设定点击语音按钮监听
     *
     * @param inter
     */
    public void setVoiceToStringListener(VoiceToStringInter inter) {
        this.inter = inter;
    }

    @Override
    public void onClick(View v) {
        if (inter != null) {
            inter.voiceToString(this);
        }
    }

    public interface VoiceToStringInter {
        void voiceToString(SpeechInputItemView v);
    }
}
