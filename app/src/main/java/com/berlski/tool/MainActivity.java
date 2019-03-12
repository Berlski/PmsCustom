package com.berlski.tool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.berlski.tool.custom.util.ToastUtil;
import com.berlski.tool.custom.widget.InfoEntryView;
import com.berlski.tool.custom.widget.InputInfoSwitchView;
import com.berlski.tool.custom.widget.SelectBeanView;
import com.berlski.tool.custom.widget.ShadowButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInputInfoSwitchView();

        setInfoEntryView();

        setShadowButton();

        setSelectBeanView();
    }

    private void setSelectBeanView() {
        SelectBeanView selectBeanView = findViewById(R.id.select_bean_view);
        final TextView selectBeanViewText = findViewById(R.id.selectBeanViewText);


        List<String> stringList = new ArrayList<>();

        stringList.add(getString(R.string.option_one));
        stringList.add(getString(R.string.option_two));
        stringList.add(getString(R.string.option_three));
        stringList.add(getString(R.string.option_four));
        stringList.add(getString(R.string.option_five));

        selectBeanView.setListAndInter(stringList, new SelectBeanView.SelectInter<String>() {
            @Override
            public void onSelect(String bean, String key, String id, int index) {
                selectBeanViewText.setText(key);
            }

            @Override
            public void toSetDefaultSelect(List list, SelectBeanView view) {

            }

            @Override
            public SelectBeanView.SelectBean setSelect(String bean) {
                return new SelectBeanView.SelectBean(bean, "1");
            }
        });
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

    private void setInfoEntryView() {
        InfoEntryView infoEntryView = findViewById(R.id.infoEntryView);
        final TextView infoEntryViewText = findViewById(R.id.infoEntryViewText);

        infoEntryView.setAfterChangedListener(new InfoEntryView.AfterChangedListener() {
            @Override
            public void onAfter(String s) {
                infoEntryViewText.setText(s);
            }
        });
    }

    private void setInputInfoSwitchView() {
        InputInfoSwitchView inputInfoSwitchView = findViewById(R.id.inputInfoSwitchView);
        final TextView inputInfoSwitchViewText = findViewById(R.id.switchViewText);

        inputInfoSwitchView.setSwitchListener(new InputInfoSwitchView.SwitchButtonInter() {
            @Override
            public void switchIndex(int index) {

                ToastUtil.showToast(index + "");
                inputInfoSwitchViewText.setText(index + "");
            }
        });
    }
}
