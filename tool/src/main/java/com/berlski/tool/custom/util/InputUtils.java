package com.berlski.tool.custom.util;

import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * com.android.review.ui
 * Created on 2019/6/29.
 * by - #.SilenceGo
 */
public class InputUtils {

    /**
     * @param editText
     * @param inputListener
     */
    public static void limitMoneyInput(final EditText editText, final IProxyInputListener inputListener) {
        if (null == editText || null == inputListener) {
            return;
        }
        editText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(".")) {
                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                        s = s.toString().subSequence(0,
                                s.toString().indexOf(".") + 3);
                        inputListener.onMoneyCallback(s.toString(), s.length());
                    }
                }
                if (s.toString().trim().substring(0).equals(".")) {
                    s = "0" + s;
                    inputListener.onMoneyCallback(s.toString(), 2);
                }

                if (s.toString().startsWith("0")
                        && s.toString().trim().length() > 1) {
                    if (!s.toString().substring(1, 2).equals(".")) {
                        inputListener.onMoneyCallback(s.subSequence(0, 1).toString(), 1);
                        return;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    public interface IProxyInputListener {
        /**
         * @param moneyNumber
         * @param selection
         */
        void onMoneyCallback(String moneyNumber, int selection);
    }

}
