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
import com.berlski.tool.custom.inter.VoiceToStringInter;
import com.berlski.tool.custom.util.UiUtil;

/**
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 信息编辑页，多选一自定义view（view）
 * Created by Berlski on 2019/3/8.
 */
public class SpeechInputView extends LinearLayout implements View.OnClickListener {

    private VoiceToStringInter inter;
    private EditText mContentView;

    public SpeechInputView(Context context) {
        super(context);
    }

    public SpeechInputView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpeechInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_speech_input, this);

        TextView nameText = findViewById(R.id.tv_vsi_name);
        ImageView requiredMarker = findViewById(R.id.iv_vsi_required_marker);
        ImageView mVoiceView = findViewById(R.id.iv_vsi_voice);
        mContentView = findViewById(R.id.et_vsi_content);

        //对属性进行解析
        // 由attrs 获得 TypeArray
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SpeechInputView);

        //设定必选标识
        boolean requiredBlean = ta.getBoolean(R.styleable.SpeechInputView_siv_is_required, false);
        if (requiredBlean) {
            requiredMarker.setVisibility(VISIBLE);

            UiUtil.drawableSetStyleColor(getContext(), requiredMarker.getDrawable());

        } else {
            requiredMarker.setVisibility(GONE);
        }


        //设定选项名称
        String name = ta.getString(R.styleable.SpeechInputView_siv_name);
        nameText.setText(name);

        String hint = ta.getString(R.styleable.SpeechInputView_siv_hint);
        mContentView.setHint(hint);

        //是否有语音
        boolean voiceBlean = ta.getBoolean(R.styleable.SpeechInputView_siv_is_voice, true);

        if (voiceBlean) {
            mVoiceView.setVisibility(VISIBLE);
            UiUtil.drawableSetStyleColor(getContext(), mVoiceView.getDrawable());
            mVoiceView.setOnClickListener(this);

        } else {
            mVoiceView.setVisibility(GONE);
        }
    }

    /**
     * 设定点击语音按钮监听
     *
     * @param inter
     */
    public void setSwitchListener(VoiceToStringInter inter) {
        this.inter = inter;
    }

    @Override
    public void onClick(View v) {
        if (inter != null) {
            mContentView.setText(inter.voiceToString());
        }
    }
}
