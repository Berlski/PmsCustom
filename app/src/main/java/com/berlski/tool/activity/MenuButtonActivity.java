package com.berlski.tool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.berlski.tool.R;
import com.berlski.tool.custom.widget.MenuButton;

/**
 * Copyright (SaaS 技术团队)
 * FileName: MenuButtonActivity
 * Author: Berlski
 * Date: 2019/9/20 16:10
 * Description: ${DESCRIPTION}
 */
public class MenuButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_button);

        setMenuButton();


    }

    private void setMenuButton() {
        final MenuButton mMenuButton = findViewById(R.id.menuButton);
        mMenuButton.setTag(true);

        mMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpen = (boolean) mMenuButton.getTag();

                if (isOpen){
                    mMenuButton.setText("开启");
                    mMenuButton.open();
                }else {
                    mMenuButton.setText("关闭");
                    mMenuButton.close();
                }

                mMenuButton.setTag(!isOpen);
            }
        });
    }
}
