package com.berlski.tool.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.util.UiUtil;

import java.util.ArrayList;
import java.util.List;

public class MultipleSelectDialog {
    private Context context;
    private Dialog dialog;
    private TextView txt_title;
    private TextView txt_cancel;
    private LinearLayout lLayout_content;
    private ScrollView sLayout_content;
    private boolean showTitle = false;
    private List<SheetItem> sheetItemList;
    private Display display;
    private Drawable mClearDrawable;
    private OnSureClickListener sureClickListener;

    public MultipleSelectDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public MultipleSelectDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_actionsheet, null);

        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(display.getWidth());

        // 获取自定义Dialog布局中的控件
        sLayout_content = (ScrollView) view.findViewById(R.id.sLayout_content);
        lLayout_content = (LinearLayout) view.findViewById(R.id.lLayout_content);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_cancel = (TextView) view.findViewById(R.id.txt_cancel);
        txt_cancel.setText(R.string.yes);
        txt_cancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (sureClickListener != null) {
                    sureClickListener.onClick();
                }
            }
        });

        return this;
    }

    /////////////////////////////////////////////////////////

    public MultipleSelectDialog setTitle(String title) {
        showTitle = true;
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText(title);
        return this;
    }

    public MultipleSelectDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public MultipleSelectDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public MultipleSelectDialog setOnSureClickListener(OnSureClickListener sureClickListener) {
        this.sureClickListener = sureClickListener;
        return this;
    }

    /**
     * @param strItem  条目名称
     * @param color    条目字体颜色，设置null则默认蓝色
     * @param listener
     * @return
     */
    public MultipleSelectDialog addSheetItem(String strItem,boolean isCheck, SheetItemColor color, OnSheetItemClickListener listener) {
        if (sheetItemList == null) {
            sheetItemList = new ArrayList<SheetItem>();
        }
        sheetItemList.add(new SheetItem(strItem,isCheck, color, listener));
        return this;
    }

    /**
     * 设置条目布局
     */
    private void setSheetItems() {
        if (sheetItemList == null || sheetItemList.size() <= 0) {
            return;
        }

        int size = sheetItemList.size();

        // TODO 高度控制，非最佳解决办法
        // 添加条目过多的时候控制高度
        if (size >= 7) {
            LayoutParams params = (LayoutParams) sLayout_content
                    .getLayoutParams();
            params.height = display.getHeight() / 2;
            sLayout_content.setLayoutParams(params);
        }

        // 循环添加条目
        for (int i = 1; i <= size; i++) {
            final int index = i;
            SheetItem sheetItem = sheetItemList.get(i - 1);
            String strItem = sheetItem.name;
            SheetItemColor color = sheetItem.color;
            final OnSheetItemClickListener listener = (OnSheetItemClickListener) sheetItem.itemClickListener;

            RelativeLayout relativeLayout = new RelativeLayout(context);

            // 高度
            float scale = context.getResources().getDisplayMetrics().density;
            int height = (int) (45 * scale + 0.4f);
            relativeLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height));


            final TextView textView = new TextView(context);
            textView.setText(strItem);
            textView.setTextSize(16);
            //textView.setGravity(Gravity.CENTER);

            final ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new LayoutParams(UiUtil.getCount(R.dimen.dp30), UiUtil.getCount(R.dimen.dp30)));
            imageView.setImageResource(R.drawable.ic_check_black);

            //setCheckDrawable(textView);

            // 背景图片
            if (size == 1) {
                if (showTitle) {
                    relativeLayout.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                } else {
                    relativeLayout.setBackgroundResource(R.drawable.actionsheet_single_selector);
                }
            } else {
                if (showTitle) {
                    if (i >= 1 && i < size) {
                        relativeLayout.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                    } else {
                        relativeLayout.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                    }
                } else {
                    if (i == 1) {
                        relativeLayout.setBackgroundResource(R.drawable.actionsheet_top_selector);
                    } else if (i < size) {
                        relativeLayout.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                    } else {
                        relativeLayout.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                    }
                }
            }

            // 字体颜色
            if (color == null) {
                textView.setTextColor(Color.parseColor(SheetItemColor.Blue
                        .getName()));
            } else {
                textView.setTextColor(Color.parseColor(color.getName()));
            }


            // 点击事件
            relativeLayout.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {


                    boolean isCheck = (boolean) imageView.getTag();

                    if (isCheck) {
                        imageView.setVisibility(View.GONE);

                    } else {
                        imageView.setVisibility(View.VISIBLE);
                    }

                    imageView.setTag(!isCheck);

                    listener.onClick(index, !isCheck);
                }
            });


            ///-------------------------------------------------

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            lp.addRule(RelativeLayout.CENTER_VERTICAL);//与父容器纵向居中 //ALIGN_PARENT_RIGHT
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);//与父容器的右侧对齐 //ALIGN_PARENT_RIGHT

            lp.rightMargin = 30;

            imageView.setLayoutParams(lp);//设置布局参数

            relativeLayout.addView(imageView);//RelativeLayout添加子View

            if (sheetItem.isCheck){
                imageView.setVisibility(View.VISIBLE);
                imageView.setTag(true);
            }else {
                imageView.setVisibility(View.GONE);
                imageView.setTag(false);
            }

            ///-------------------------------------------------


            ///-------------------------------------------------

            RelativeLayout.LayoutParams lp111 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

            lp111.addRule(RelativeLayout.CENTER_IN_PARENT);//与父容器居中对齐

            textView.setLayoutParams(lp111);//设置布局参数

            relativeLayout.addView(textView);//RelativeLayout添加子View

            ///-------------------------------------------------


            lLayout_content.addView(relativeLayout);
        }
    }

    private void setCheckDrawable(TextView view) {
        //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        mClearDrawable = view.getCompoundDrawables()[2];
        if (mClearDrawable == null) {
//          throw new NullPointerException("You can add drawableRight attribute in XML");
            mClearDrawable = context.getResources().getDrawable(R.drawable.ic_check_black);
        }

        mClearDrawable.setBounds(-40, 0, 40, 100);//mClearDrawable.getIntrinsicWidth(), mClearDrawable.getIntrinsicHeight());
        //默认设置隐藏图标
        setCheckIconVisible(false, view);
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     * @param visible
     */
    protected void setCheckIconVisible(boolean visible, TextView view) {
        Drawable right = visible ? mClearDrawable : null;
        view.setCompoundDrawables(view.getCompoundDrawables()[0], view.getCompoundDrawables()[1], right, view.getCompoundDrawables()[3]);
    }

    ///////////////////////////////////////////////////////

    public void show() {
        setSheetItems();
        dialog.show();
    }

    public interface OnSheetItemClickListener {
        void onClick(int which, boolean isCheck);
    }

    public interface OnSureClickListener {
        void onClick();
    }

    public class SheetItem {
        String name;
        boolean isCheck;
        OnSheetItemClickListener itemClickListener;
        SheetItemColor color;

        public SheetItem(String name,boolean isCheck, SheetItemColor color, OnSheetItemClickListener itemClickListener) {
            this.name = name;
            this.isCheck = isCheck;
            this.color = color;
            this.itemClickListener = itemClickListener;
        }
    }

    public enum SheetItemColor {
        Blue("#037BFF"), Red("#FD4A2E"), Black("#000000"), Green_up("#31C27C");

        private String name;

        SheetItemColor(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
