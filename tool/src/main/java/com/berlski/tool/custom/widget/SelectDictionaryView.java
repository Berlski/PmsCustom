package com.berlski.tool.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.dao.bean.Dictionary;
import com.berlski.tool.custom.dialog.ActionSheetDialog;
import com.berlski.tool.custom.dialog.MultipleSelectDialog;
import com.berlski.tool.custom.enums.DictionaryEnum;
import com.berlski.tool.custom.inter.DictionaryInter;
import com.berlski.tool.custom.manager.DictionaryManager;
import com.berlski.tool.custom.manager.MyActivityManager;
import com.berlski.tool.custom.util.StringUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;
import java.util.TreeMap;

/**
 * 信息编辑页，下弹字典菜单自定义view
 */
public class SelectDictionaryView extends LinearLayout implements View.OnClickListener {

    private String dictionaryKey;
    private String dictionaryId;

    private AVLoadingIndicatorView loadView;
    private TextView tv_vds_select;
    private boolean isMultipleSelect;
    private TextView nameText;
    private boolean isRequired;
    private String name;
    private int type = 0x0001;

    public SelectDictionaryView(Context context) {
        super(context);
    }

    public SelectDictionaryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    public SelectDictionaryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }

    private void initView(Context context, @Nullable AttributeSet attrs) {
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_dictionary_select, this);

        nameText = findViewById(R.id.tv_vds_name);
        ImageView requiredMarker = findViewById(R.id.iv_vds_required_marker);
        loadView = findViewById(R.id.avi_vds_load);
        tv_vds_select = findViewById(R.id.tv_vds_select);

        //对属性进行解析
        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SelectDictionaryView);// 由attrs 获得 TypeArray

        //设定提示
        String hint = ta.getString(R.styleable.SelectDictionaryView_sv_hint);
        if (StringUtil.isEmpty(hint)) {
            hint = getContext().getString(R.string.please_choose);
        }
        tv_vds_select.setHint(hint);


        //设定必选标识
        isRequired = ta.getBoolean(R.styleable.SelectDictionaryView_sv_is_required, false);
        if (isRequired) {
            requiredMarker.setVisibility(VISIBLE);

        } else {
            requiredMarker.setVisibility(GONE);
        }

        //是否多选
        isMultipleSelect = ta.getBoolean(R.styleable.SelectDictionaryView_sv_is_multiple_select, false);

        //设定选项名称
        name = ta.getString(R.styleable.SelectDictionaryView_sv_name);
        nameText.setText(name);

        //设定最大宽度，保证其他view不被挤出屏幕
        setContentTextMaxWidth();

        //设定view点击事件
        this.setOnClickListener(this);

        //获取字典类型
        type = ta.getInteger(R.styleable.SelectDictionaryView_sv_type, 0x0001);

        showLoad();
        //开启字典请求
        DictionaryManager.instance().getDictionary(DictionaryEnum.getDictionaryEnum(type), new DictionaryInter() {
            @Override
            public void onSuccess(List<Dictionary> list) {
                //关闭加载动画
                hideLoad();

                //设定默认选中项
                int default_select = ta.getInt(R.styleable.SelectDictionaryView_sv_default_select, -1);
                if (default_select > -1 && default_select < list.size()) {
                    Dictionary bean = list.get(default_select);

                    tv_vds_select.setText(bean.getKey());
                    dictionaryKey = bean.getKey();
                    dictionaryId = bean.getId();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        showLoad();

        DictionaryManager.instance().getDictionary(DictionaryEnum.getDictionaryEnum(type), new DictionaryInter() {
            @Override
            public void onSuccess(List<Dictionary> list) {
                hideLoad();

                //是否多选，如果多选，就显示多选，默认单选
                if (isMultipleSelect) {
                    showMultipleSelectDialog(getContext(), list);

                } else {
                    showStateDialog(getContext(), list);
                }
            }
        });
    }

    /**
     * 设定默认选中项
     *
     * @param defaultDictionaryId
     */
    public void setDefaultSelect(final String defaultDictionaryId) {
        if (StringUtil.isNotEmpty(defaultDictionaryId)) {
            DictionaryManager.instance().getDictionary(DictionaryEnum.getDictionaryEnum(type), new DictionaryInter() {
                @Override
                public void onSuccess(List<Dictionary> list) {
                    for (Dictionary dictionary : list) {
                        if (defaultDictionaryId.equals(dictionary.getId())) {
                            dictionaryId = dictionary.getId();
                            dictionaryKey = dictionary.getKey();
                            tv_vds_select.setText(dictionaryKey);
                        }
                    }
                }
            });
        }
    }

    public void showLoad() {
        loadView.setVisibility(VISIBLE);
        loadView.show();
    }

    public void hideLoad() {
        loadView.hide();
        loadView.setVisibility(GONE);
    }

    /**
     * 设定最大宽度，保证其他view不被挤出屏幕
     */
    private void setContentTextMaxWidth() {
        int maxWidth = getPhoneWidth();        //屏幕宽度

        maxWidth -= getCount(R.dimen.dp15);   //条目标题距左的宽度

        maxWidth -= getTextWidth();    //条目标题宽度

        maxWidth -= isRequired ? getCount(R.dimen.dp30) : 0;      //“是否必选标记”的宽度

        maxWidth -= loadView.getVisibility() == View.VISIBLE ? getCount(R.dimen.dp25) : 0;   //加载view 的宽度

        maxWidth -= getCount(R.dimen.dp30);   //右角标 的宽度

        tv_vds_select.setMaxWidth(maxWidth);
    }

    /**
     * 获得屏幕的宽
     *
     * @return
     */
    private int getPhoneWidth() {
        //Android 获得屏幕的宽和高
        DisplayMetrics dm = getResources().getDisplayMetrics();

        return dm.widthPixels;
    }

    /**
     * 根据dimen值计算返回对应屏幕的px值，
     *
     * @param id R.dimen.id
     * @return
     */
    private int getCount(int id) {
        return getResources().getDimensionPixelSize(id);
    }

    /**
     * 计算文字宽度
     *
     * @return
     */
    private int getTextWidth() {
        Rect bounds = new Rect();
        TextPaint paint;
        paint = nameText.getPaint();
        paint.getTextBounds(name, 0, name.length(), bounds);
        return bounds.width();
    }

    /**
     * 列表弹窗
     *
     * @param context
     * @param list
     */
    public void showStateDialog(Context context, List<Dictionary> list) {
        ActionSheetDialog dialog = new ActionSheetDialog(MyActivityManager.getInstance().getCurrentActivity());
        dialog.builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true);

        for (final Dictionary bean : list) {

            dialog.addSheetItem(bean.getKey(), ActionSheetDialog.SheetItemColor.Green,
                    new ActionSheetDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which) {
                            dictionaryKey = bean.getKey();
                            dictionaryId = bean.getId();
                            tv_vds_select.setText(dictionaryKey);
                        }
                    });
        }

        dialog.show();
    }

    /**
     * 列表弹窗
     *
     * @param context
     * @param list
     */
    public void showMultipleSelectDialog(Context context, List<Dictionary> list) {

        MultipleSelectDialog dialog = new MultipleSelectDialog(MyActivityManager.getInstance().getCurrentActivity());
        dialog.builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .setOnSureClickListener(new MultipleSelectDialog.OnSureClickListener() {
                    @Override
                    public void onClick() {

                        String dictionaryIds = "";

                        for (Dictionary dictionary : selectMap.values()) {
                            if (StringUtil.isEmpty(dictionaryIds)) {
                                dictionaryIds = dictionary.getId();
                            } else {
                                dictionaryIds += "," + dictionary.getId();
                            }
                        }

                        dictionaryId = dictionaryIds;
                    }
                });

        for (final Dictionary bean : list) {

            boolean isCheck = selectMap.get(bean.getId()) != null ? selectMap.get(bean.getId()).getIsCheck() : false;

            dialog.addSheetItem(bean.getKey(), isCheck, MultipleSelectDialog.SheetItemColor.Green_up,
                    new MultipleSelectDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which, boolean isCheck) {

                            if (!isCheck && selectMap.get(bean.getId()) != null) {
                                selectMap.remove(bean.getId());

                            } else {
                                bean.setIsCheck(isCheck);
                                selectMap.put(bean.getId(), bean);
                            }

                            String dictionaryNames = "";

                            for (Dictionary dictionary : selectMap.values()) {
                                if (StringUtil.isEmpty(dictionaryNames)) {
                                    dictionaryNames = dictionary.getKey();
                                } else {
                                    dictionaryNames += "、" + dictionary.getKey();
                                }
                            }

                            tv_vds_select.setText(dictionaryNames);
                        }
                    });
        }

        dialog.show();
    }

    public String getDictionaryKey() {
        return dictionaryKey;
    }

    public String getDictionaryId() {
        return dictionaryId;
    }

    public interface MultipleSelectListener {
        void onClick(TreeMap dictionaryMap);
    }

    private TreeMap<String, Dictionary> selectMap = new TreeMap<>();

    private MultipleSelectListener mMultipleSelectListener;

    public void setMultipleSelectListener(MultipleSelectListener mMultipleSelectListener) {
        this.mMultipleSelectListener = mMultipleSelectListener;
    }
}
