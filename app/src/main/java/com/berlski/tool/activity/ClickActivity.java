package com.berlski.tool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berlski.tool.R;
import com.berlski.tool.custom.util.ColorUtil;
import com.berlski.tool.custom.widget.InfoItemClickView;

/**
 * Copyright (SaaS 技术团队)
 * FileName: ClickActivity
 * Author: Berlski
 * Date: 2019/9/20 16:23
 * Description: ${DESCRIPTION}
 */
public class ClickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);

        InfoItemClickView infoItemClickView = findViewById(R.id.ac_InfoItemClickView);

        infoItemClickView.setContentTextColor(ColorUtil.getColor(this,R.color.red));
        infoItemClickView.setContentText("不可点击");
        infoItemClickView.setItemName("我的条目");
        infoItemClickView.setItemNameColor(ColorUtil.getColor(this,R.color.orange));
        infoItemClickView.showLoad();
        infoItemClickView.setRightIconIsShow(true);
    }
}