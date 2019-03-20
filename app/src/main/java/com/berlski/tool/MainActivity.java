package com.berlski.tool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.berlski.tool.custom.enums.DictionaryEnum;
import com.berlski.tool.custom.enums.NetUrlEnum;
import com.berlski.tool.custom.inter.NumberRangeInter;
import com.berlski.tool.custom.inter.SwitchButtonInter;
import com.berlski.tool.custom.util.ToastUtil;
import com.berlski.tool.custom.widget.InfoEntryItemView;
import com.berlski.tool.custom.widget.SwitchOptionItemView;
import com.berlski.tool.custom.widget.MenuButton;
import com.berlski.tool.custom.widget.SelectBeanItemView;
import com.berlski.tool.custom.widget.ShadowButton;
import com.berlski.tool.custom.widget.SpeechInputItemView;
import com.berlski.tool.test.Dictionary;
import com.berlski.tool.test.PriceSelectDialog;
import com.berlski.tool.test.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInputInfoSwitchView();

        setInfoEntryView();

        setShadowButton();

        setSelectBeanView();

        //setSelectDictionaryView();

        setMenuButton();

        setSpeechInputView();
    }

    private void setSpeechInputView() {
        final SpeechInputItemView mSpeechInputView = findViewById(R.id.speechInputView);

        mSpeechInputView.setVoiceToStringListener(new SpeechInputItemView.VoiceToStringInter() {
            @Override
            public void voiceToString(final SpeechInputItemView v) {

                //v.setText(v.getText() + "加加 -- ");

                PriceSelectDialog.builder(MainActivity.this).onCreateDialog(500, 5000, 500, 5000, new NumberRangeInter() {
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

                setSelectDictionaryView();
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
        InfoEntryItemView infoEntryItemView = findViewById(R.id.infoEntryView);
        final TextView infoEntryViewText = findViewById(R.id.infoEntryViewText);

        infoEntryItemView.setAfterChangedListener(new InfoEntryItemView.AfterChangedListener() {
            @Override
            public void onAfter(String s) {
                infoEntryViewText.setText(s);
            }
        });
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
