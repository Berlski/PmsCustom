package com.berlski.tool.test;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.inter.NumberRangeInter;
import com.berlski.tool.custom.util.LogUtil;
import com.berlski.tool.custom.widget.NumberRangeSliderView;

public class PriceSelectDialog extends Dialog implements DialogInterface.OnDismissListener, NumberRangeSliderView.RangeSliderListener, View.OnClickListener {

    private NumberRangeInter inter;

    private TextView minPriceTxt;
    private TextView maxPriceTxt;
    private NumberRangeSliderView priceSlider;

    public PriceSelectDialog(Context context) {
        super(context, R.style.remind_dialog);
    }

    public static PriceSelectDialog builder(Context context) {
        return new PriceSelectDialog(context);
    }

    public Dialog onCreateDialog(int minPrice, int maxPrice, Integer selectedMinPrice, Integer selectedMaxPrice, final NumberRangeInter inter) {
        this.inter = inter;

        show();
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        setContentView(R.layout.dialog_price_range_picker);
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        //对齐方式：显示在屏幕下方
        window.setGravity(Gravity.BOTTOM);

        minPriceTxt = findViewById(R.id.min_price_txt);
        maxPriceTxt = findViewById(R.id.max_price_txt);
        priceSlider = findViewById(R.id.price_slider);

        TextView mSureView = findViewById(R.id.tvSure);
        TextView mCancleView = findViewById(R.id.tvCancle);

        mSureView.setOnClickListener(this);
        mCancleView.setOnClickListener(this);

        setOnDismissListener(this);
        priceSlider.setRangeSliderListener(this);

        priceSlider.setMin(minPrice);
        priceSlider.setMax(maxPrice);
        priceSlider.setStartingMinMax(selectedMinPrice, selectedMaxPrice);

        return this;
    }

    @Override
    public void onMinChanged(int newValue) {
        try {
            String minPrice = String.valueOf(newValue);
            String suMinPrice = minPrice.replace(minPrice.substring(minPrice.length() - 2), "00");
            minPriceTxt.setText(suMinPrice);
        }catch (Exception e){
            LogUtil.e(e);
        }
    }

    @Override
    public void onMaxChanged(int newValue) {
        try {
            String minPrice = String.valueOf(newValue);
            String suMaxPrice = minPrice.replace(minPrice.substring(minPrice.length() - 2), "00");
            maxPriceTxt.setText(suMaxPrice);
        }catch (Exception e){
            LogUtil.e(e);
        }
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.tvSure) {
            if (inter != null) {
                inter.onSelect(minPriceTxt.getText().toString(), maxPriceTxt.getText().toString());
            }
            cancel();

        } else if (v.getId() == R.id.tvCancle) {
            cancel();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        if (inter != null) {
            inter.onCancel();
        }
    }
}
