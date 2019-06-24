package com.berlski.tool.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.util.ColorUtil;
import com.berlski.tool.custom.util.UiUtil;

/**
 * Created by ccp on 2018/11/26.
 */

public class MenuButton extends RelativeLayout {

    private int textOpenColor;
    private int textCloseColor;
    private int drawableOpenColor;
    private int drawableCloseColor;
    private TextView textView;
    private Context mContext;
    private int openDrawable;
    private int closeDrawable;
    private float drawableSize;

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

        textView = findViewById(R.id.atv_vmb_text);

        setGravity(Gravity.CENTER);

        //字体文本
        String text = ta.getString(R.styleable.MenuButton_mb_text);

        //字体颜色
        textOpenColor = ta.getColor(R.styleable.MenuButton_mb_text_open_color, ColorUtil.getColor(mContext,R.color.color_styles));
        //字体颜色
        textCloseColor = ta.getColor(R.styleable.MenuButton_mb_text_close_color, Color.BLACK);

        //字体颜色
        drawableOpenColor = ta.getColor(R.styleable.MenuButton_mb_drawable_open_color, ColorUtil.getColor(mContext,R.color.color_styles));
        drawableCloseColor = ta.getColor(R.styleable.MenuButton_mb_drawable_close_color, Color.BLACK);

        //开启关闭时的 icon
        openDrawable = ta.getResourceId(R.styleable.MenuButton_mb_open_drawable, R.drawable.menu_up);
        closeDrawable = ta.getResourceId(R.styleable.MenuButton_mb_close_drawable, R.drawable.menu_down);

        //icon大小
        drawableSize = ta.getDimensionPixelSize(R.styleable.MenuButton_mb_drawable_size, getCount(R.dimen.dp15));


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
        textView.setTextColor(textOpenColor);

        //设定图片
        Drawable right = getResources().getDrawable(openDrawable);

        //设置图片的颜色
        UiUtil.drawableSetColor(right, drawableOpenColor);

        //设置图片的大小
        right.setBounds(0, 0, (int) drawableSize, (int) drawableSize);//设置图片的大小

        //设定图片居右
        textView.setCompoundDrawables(null, null, right, null);
    }

    public void close() {
        textView.setTextColor(textCloseColor);

        //设定图片
        Drawable right = getResources().getDrawable(closeDrawable);

        //设置图片的颜色
        UiUtil.drawableSetColor(right, drawableCloseColor);

        //设置图片的大小
        right.setBounds(0, 0, (int) drawableSize, (int) drawableSize);

        //设定图片居右
        textView.setCompoundDrawables(null, null, right, null);
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
