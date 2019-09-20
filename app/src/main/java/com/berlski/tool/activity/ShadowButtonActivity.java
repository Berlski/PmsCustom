package com.berlski.tool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.berlski.tool.R;
import com.berlski.tool.custom.widget.ShadowButton;

/**
 * Copyright (SaaS 技术团队)
 * FileName: ShadowButtonActivity
 * Author: Berlski
 * Date: 2019/9/20 16:04
 * Description: ${DESCRIPTION}
 */
public class ShadowButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow_button);

        setShadowButton();

    }

    private void setShadowButton() {
        final ShadowButton shadowButton = findViewById(R.id.shadowButton);
        final String s = shadowButton.getText().toString();
        shadowButton.setTag(0);

        shadowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = (int) shadowButton.getTag();
                shadowButton.setText(s + "(" + index + ")");
                index++;
                shadowButton.setTag(index);
            }
        });
    }
}
