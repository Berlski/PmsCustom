package com.berlski.tool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.berlski.tool.R;
import com.berlski.tool.custom.util.ColorUtil;
import com.berlski.tool.custom.widget.InfoItemClickView;
import com.berlski.tool.custom.widget.InfoItemEditView;

/**
 * Copyright (SaaS 技术团队)
 * FileName: InfoItemEditActivity
 * Author: Berlski
 * Date: 2019/9/29 18:39
 * Description: ${DESCRIPTION}
 */
public class InfoItemEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_item_edit);

        InfoItemEditView infoItemClickView = findViewById(R.id.ac_InfoItemClickView);

        infoItemClickView.setItemName("我的条目");
        infoItemClickView.setItemNameColor(ColorUtil.getColor(this,R.color.orange));
        /*infoItemClickView.setContentTextColor(ColorUtil.getColor(this,R.color.red));
        infoItemClickView.setContentText("不可点击");
        infoItemClickView.showLoad();
        infoItemClickView.setRightIconIsShow(true);*/
    }
}
