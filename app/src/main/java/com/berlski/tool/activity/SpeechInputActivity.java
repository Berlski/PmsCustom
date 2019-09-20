package com.berlski.tool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berlski.tool.R;
import com.berlski.tool.custom.inter.NumberRangeInter;
import com.berlski.tool.custom.widget.SpeechInputItemView;
import com.berlski.tool.test.PriceSelectDialog;

/**
 * Copyright (SaaS 技术团队)
 * FileName: SpeechInputActivity
 * Author: Berlski
 * Date: 2019/9/20 16:15
 * Description: ${DESCRIPTION}
 */
public class SpeechInputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_input);

        setSpeechInputView();

    }

    private void setSpeechInputView() {
        final SpeechInputItemView mSpeechInputView = findViewById(R.id.speechInputView);

        mSpeechInputView.setVoiceToStringListener(new SpeechInputItemView.VoiceToStringInter() {
            @Override
            public void voiceToString(final SpeechInputItemView v) {

                //v.setText(v.getText() + "加加 -- ");

                PriceSelectDialog.builder(SpeechInputActivity.this).onCreateDialog(500, 5000, 500, 5000, new NumberRangeInter() {
                    @Override
                    public void onSelect(String minPrice, String maxPrice) {
                        v.setText(minPrice + " -- " + maxPrice);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
            }
        });
    }
}
