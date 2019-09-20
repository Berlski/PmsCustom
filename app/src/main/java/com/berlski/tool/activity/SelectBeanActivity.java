package com.berlski.tool.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.berlski.tool.R;
import com.berlski.tool.custom.enums.DictionaryEnum;
import com.berlski.tool.custom.enums.NetUrlEnum;
import com.berlski.tool.custom.widget.SelectBeanItemView;
import com.berlski.tool.test.Dictionary;
import com.berlski.tool.test.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (SaaS 技术团队)
 * FileName: SelectBeanActivity
 * Author: Berlski
 * Date: 2019/9/20 16:08
 * Description: ${DESCRIPTION}
 */
public class SelectBeanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_bean);

        setSelectBeanView();


    }

    private void setSelectBeanView() {
        SelectBeanItemView selectBeanView = findViewById(R.id.select_bean_view);
        final TextView selectBeanViewText = findViewById(R.id.selectBeanViewText);


        List<Role> stringList = new ArrayList<>();

        stringList.add(new Role(getString(R.string.option_one), "1"));
        stringList.add(new Role(getString(R.string.option_two), "2"));
        stringList.add(new Role(getString(R.string.option_three), "3"));
        stringList.add(new Role(getString(R.string.option_four), "4"));
        stringList.add(new Role(getString(R.string.option_five), "5"));

        selectBeanView.setListAndInter(stringList, new SelectBeanItemView.SelectInter<Role>() {
            @Override
            public void onSelect(Role bean, String key, String id, int index) {
                selectBeanViewText.setText(key);

                //setSelectDictionaryView();
            }

            @Override
            public void toRequestCompletedSetSelect(List list, SelectBeanItemView view) {

            }

            @Override
            public SelectBeanItemView.SelectBean setSelectBean(Role bean) {
                return new SelectBeanItemView.SelectBean(bean.getName(), bean.getId());
            }
        });
    }

    private void setSelectDictionaryView() {
        SelectBeanItemView selectBeanView = findViewById(R.id.selectDictionaryView);
        final TextView selectBeanViewText = findViewById(R.id.selectDictionaryViewText);

        selectBeanView.setRequestDataSelect(new SelectBeanItemView.RequestDataSelectInter<Dictionary>() {

            @Override
            public void onSelect(Dictionary bean, String key, String id, int index) {
                selectBeanViewText.setText(key);
            }

            @Override
            public void toRequestCompletedSetSelect(List<Dictionary> list, SelectBeanItemView view) {

            }

            @Override
            public NetUrlEnum setNetUrlEnum() {
                return NetUrlEnum.NET_URL_ENUM;
            }

            @Override
            public Map<String, Object> setParams(HashMap<String, Object> params) {

                //Map<String, Object> params = new HashMap<>();

                params.put("gcid", "0100099");
                params.put("token", "901b9bc1-165d-4de0-8c49-7da98b893c90");
                params.put("userid", "b0585adccc16473082b63915901842af");

                params.put("mark", DictionaryEnum.ACCOUNT_TYPE.getMark());                     //
                params.put("params", params);

                return null;
            }

            @Override
            public SelectBeanItemView.SelectBean setSelectBean(Dictionary bean) {
                return new SelectBeanItemView.SelectBean(bean.getKey(), bean.getId());
            }
        });
    }
}