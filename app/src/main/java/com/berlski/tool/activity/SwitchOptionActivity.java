package com.berlski.tool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.berlski.tool.R;
import com.berlski.tool.custom.inter.SwitchButtonInter;
import com.berlski.tool.custom.util.ToastUtil;
import com.berlski.tool.custom.widget.SwitchOptionItemView;

/**
 * Copyright (SaaS 技术团队)
 * FileName: SwitchOptionActivity
 * Author: Berlski
 * Date: 2019/9/20 15:57
 * Description: ${DESCRIPTION}
 */
public class SwitchOptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_option);

        setInputInfoSwitchView();


    }

    private void setInputInfoSwitchView() {
        SwitchOptionItemView switchOptionItemView = findViewById(R.id.inputInfoSwitchView);
        final TextView inputInfoSwitchViewText = findViewById(R.id.switchViewText);

        switchOptionItemView.setSwitchListener(new SwitchButtonInter() {
            @Override
            public void switchIndex(int index) {

                ToastUtil.showToast(index + "");
                inputInfoSwitchViewText.setText(index + "");
            }
        });
    }
}