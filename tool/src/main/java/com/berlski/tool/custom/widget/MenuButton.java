package com.berlski.tool.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.util.ColorUtil;
import com.berlski.tool.custom.util.UiUtil;

/**
 * Created by ccp on 2018/11/26.
 */

public class MenuButton extends RelativeLayout {

    private int textColor;
    private Button textView;
    //private ImageView imageView;
    private Context mContext;

    public MenuButton(Context context) {
        super(context);
    }

    public MenuButton(Context context, AttributeSet attrs) {
        //这里构造方法也很重要，不加这个很多属性不能再XML里面定义
        super(context, attrs);

        init(context, attrs);
    }

    public MenuButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.mContext = context;

        //对属性进行解析
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MenuButton);// 由attrs 获得 TypeArray

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_menu_button, this);

        /*textView = new Button(mContext);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setMaxLines(1);
        textView.setBackground(null);
        textView.setStateListAnimator();*/

        textView = findViewById(R.id.atv_vmb_text);
//        imageView = findViewById(R.id.iv_vmb_image);

        setGravity(Gravity.CENTER);

        //字体文本
        String text = ta.getString(R.styleable.MenuButton_mb_text);

        //字体颜色
        textColor = ta.getColor(R.styleable.MenuButton_mb_text_color, Color.BLACK);

        //字体大小
        float textSize = ta.getDimensionPixelSize(R.styleable.MenuButton_mb_text_size, getCount(R.dimen.sp15));

        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        close();
        textView.setText(text);
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public void setText(int text) {
        textView.setText(text);
    }

    public void open() {
        textView.setTextColor(ColorUtil.getColor(mContext, R.color.color_styles));

//        imageView.setImageResource(R.drawable.ic_arrow_drop_up);

        Drawable right = getResources().getDrawable(R.drawable.menu_up);
        //right.setBounds(0,0,20,20);


        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,right,null);

//        UiUtil.drawableSetStyleColor(getContext(), imageView.getDrawable());
        UiUtil.drawableSetStyleColor(getContext(), right);

        //设置Drawable
        /*Drawable right = getResources().getDrawable(R.drawable.menu_down);
        right.setBounds(2, 0, dip2px(20), dip2px(20));//必须设置图片的大小否则没有作用
        textView.setCompoundDrawables(null, null, right, null);//设置图片left这里如果是右边就放到第二个参数里面依次对应*/
    }

    public void close() {
        textView.setTextColor(textColor);

        Drawable right = getResources().getDrawable(R.drawable.menu_down);
        //right.setBounds(0,0,50,50);
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,right,null);
        //imageView.setImageResource(R.drawable.menu_down);

        //UiUtil.drawableSetColor(imageView.getDrawable(), textColor);
        UiUtil.drawableSetColor(right, textColor);

        //设置Drawable
        /*Drawable right = getResources().getDrawable(R.drawable.menu_up);
        right.setBounds(2, 0, dip2px(20), dip2px(20));//必须设置图片的大小否则没有作用
        textView.setCompoundDrawables(null, null, right, null);//设置图片left这里如果是右边就放到第二个参数里面依次对应*/
    }

    /**
     * 根据dimen值计算返回对应屏幕的px值，
     *
     * @param id R.dimen.id
     * @return
     */
    private int getCount(int id) {
        return UiUtil.getCount(getContext(), id);
    }
}
