package com.berlski.tool.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.style.MenuButtonDefaultStyle;
import com.berlski.tool.custom.style.MenuButtonStyle;
import com.berlski.tool.custom.util.ConstraintUtil;
import com.berlski.tool.custom.util.UiUtil;

/**
 * Created by ccp on 2018/11/26.
 */

public class MenuButton extends ConstraintLayout {

    private static MenuButtonStyle mMenuButtonStyle;

    private int textOpenColor;
    private int textCloseColor;
    private int drawableOpenColor;
    private int drawableCloseColor;
    private TextView textView;
    private Context mContext;
    private Drawable openIcon;
    private Drawable closeIcon;
    private float iconSize;

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

        //设定默认样式
        if (mMenuButtonStyle == null) {
            mMenuButtonStyle = new MenuButtonDefaultStyle(context);
        }

        MenuButtonStyle style = MenuButton.mMenuButtonStyle;

        //对属性进行解析
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MenuButton);// 由attrs 获得 TypeArray

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_menu_button, this);

        textView = findViewById(R.id.vmb_text);

        //字体文本
        String text = ta.getString(R.styleable.MenuButton_mb_text);

        //字体开启时颜色
        textOpenColor = ta.getColor(R.styleable.MenuButton_mb_text_open_color, style.getTextOpenColor());
        //字体关闭时颜色
        textCloseColor = ta.getColor(R.styleable.MenuButton_mb_text_close_color, style.getTextCloseColor());

        //倒三角开启时颜色
        drawableOpenColor = ta.getColor(R.styleable.MenuButton_mb_icon_open_color, style.getIconOpenColor());
        //倒三角关闭时颜色
        drawableCloseColor = ta.getColor(R.styleable.MenuButton_mb_icon_close_color, style.getIconCloseColor());

        //开启时的 icon
        if (ta.hasValue(R.styleable.MenuButton_mb_icon_open_icon)) {
            openIcon = getContext().getResources().getDrawable(ta.getResourceId(R.styleable.MenuButton_mb_icon_open_icon, 0));
        } else {
            openIcon = style.getOpenIcon();
        }

        //关闭时的 icon
        if (ta.hasValue(R.styleable.MenuButton_mb_icon_close_icon)) {
            closeIcon = getContext().getResources().getDrawable(ta.getResourceId(R.styleable.MenuButton_mb_icon_close_icon, 0));
        } else {
            closeIcon = style.getCloseIcon();
        }

        //icon大小
        iconSize = ta.getDimensionPixelSize(R.styleable.MenuButton_mb_icon_size, style.getIconSize());


        //字体大小
        float textSize = ta.getDimensionPixelSize(R.styleable.MenuButton_mb_text_size, style.getTextSize());

        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

        //是否显示底线
        boolean isShowBottomLine = ta.getBoolean(R.styleable.MenuButton_mb_is_show_bottom_line, style.isShowBottomLine());
        if (isShowBottomLine) {

            int bottomLineHeight = ta.getDimensionPixelSize(R.styleable.MenuButton_mb_bottom_line_height, style.getBottomLineHeight());

            View bottomLine = new View(context);

            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(LayoutParams.MATCH_PARENT, bottomLineHeight);
            layoutParams.bottomToBottom = this.getId();
            //layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);//与父容器的底部对齐
            bottomLine.setLayoutParams(layoutParams);

            //底线颜色
            int bottomLineColor = ta.getColor(R.styleable.MenuButton_mb_bottom_line_color, style.getBottomLineColor());
            bottomLine.setBackgroundColor(bottomLineColor);

            bottomLine.setId(R.id.vbl_bottom_line);

            addView(bottomLine);
        }

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
        Drawable right = openIcon;

        //设置图片的颜色
        UiUtil.drawableSetColor(right, drawableOpenColor);

        //设置图片的大小
        right.setBounds(0, 0, (int) iconSize, (int) iconSize);//设置图片的大小

        //设定图片居右
        //textView.setCompoundDrawables(null, null, right, isShowBottomLine ? bottom: null);
        textView.setCompoundDrawables(null, null, right, null);
    }

    public void close() {
        textView.setTextColor(textCloseColor);

        //设定图片
        Drawable right = closeIcon;

        //设置图片的颜色
        UiUtil.drawableSetColor(right, drawableCloseColor);

        //设置图片的大小
        right.setBounds(0, 0, (int) iconSize, (int) iconSize);

        //设定图片居右
        textView.setCompoundDrawables(null, null, right, null);
    }

    /**
     * 统一全局的MenuButton样式，建议在{@link android.app.Application#onCreate()}中初始化
     */
    public static void initStyle(MenuButtonStyle style) {
        MenuButton.mMenuButtonStyle = style;
    }
}
