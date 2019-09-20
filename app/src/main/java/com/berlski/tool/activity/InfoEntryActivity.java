package com.berlski.tool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.berlski.tool.R;
import com.berlski.tool.custom.widget.InfoEntryItemView;

/**
 * Copyright (SaaS 技术团队)
 * FileName: InfoEntryActivity
 * Author: Berlski
 * Date: 2019/9/20 16:00
 * Description: ${DESCRIPTION}
 */
public class InfoEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_entry);

        setInfoEntryView();

    }

    private void setInfoEntryView() {
        InfoEntryItemView infoEntryItemView = findViewById(R.id.infoEntryView);
        final TextView infoEntryViewText = findViewById(R.id.infoEntryViewText);

        infoEntryItemView.setAfterChangedListener(new InfoEntryItemView.AfterChangedListener() {
            @Override
            public void onAfter(String s) {
                infoEntryViewText.setText(s);
            }
        });
    }
}
