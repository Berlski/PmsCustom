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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.berlski.tool.custom.R;
import com.berlski.tool.custom.dialog.ActionSheetDialog;
import com.berlski.tool.custom.dialog.MultipleSelectDialog;
import com.berlski.tool.custom.enums.NetUrlEnum;
import com.berlski.tool.custom.manager.HttpManager;
import com.berlski.tool.custom.util.StringUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 信息编辑页，下弹菜单自定义view
 */
public class SelectBeanView<T> extends LinearLayout implements View.OnClickListener {

    private String mKey;
    private String mId;

    private AVLoadingIndicatorView mLoadView;
    private TextView mContentView;
    private TextView mNameView;
    private SelectInter mInter;
    private RequestDataSelectInter mRequestInter;
    private List<T> mList;
    private int mDefaultSelect;
    private boolean isMultipleSelect;
    private boolean isRequired;
    private String mNameText;

    private TreeMap<String, SelectBean> mSelectMap = new TreeMap<>();

    public SelectBeanView(Context context) {
        super(context);
    }

    public SelectBeanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context, attrs);
    }

    public SelectBeanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }

    private void initView(Context context, @Nullable AttributeSet attrs) {

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_bean_select, this);

        mNameView = findViewById(R.id.tv_vds_name);
        ImageView requiredMarker = findViewById(R.id.iv_vds_required_marker);
        mContentView = findViewById(R.id.tv_vds_content);

        mLoadView = findViewById(R.id.avi_vds_load);
        mLoadView.setVisibility(GONE);

        //对属性进行解析
        // 由attrs 获得 TypeArray
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SelectBeanView);

        //设定提示
        String hint = ta.getString(R.styleable.SelectBeanView_sbv_hint);
        if (StringUtil.isEmpty(hint)) {
            hint = getContext().getString(R.string.please_choose);
        }
        mContentView.setHint(hint);

        //设定必选标识
        isRequired = ta.getBoolean(R.styleable.SelectBeanView_sbv_is_required, false);
        if (isRequired) {
            requiredMarker.setVisibility(VISIBLE);

        } else {
            requiredMarker.setVisibility(GONE);
        }

        //是否多选
        isMultipleSelect = ta.getBoolean(R.styleable.SelectBeanView_sbv_is_multiple_select, false);

        //设定选项名称
        mNameText = ta.getString(R.styleable.SelectBeanView_sbv_name);
        mNameView.setText(mNameText);

        //设定最大宽度，保证其他view不被挤出屏幕
        setContentTextMaxWidth();

        //设定view点击事件
        this.setOnClickListener(this);
    }

    /**
     * 设定整个条目的点击响应
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (mInter != null && mList != null) {
            showSelectDialog(mList);

        } else if (mRequestInter != null && mList != null) {
            showSelectDialog(mList);
        }
    }

    /**
     * 设定默认选中项
     *
     * @param defaultSelect
     */
    public void setDefaultSelect(int defaultSelect) {
        this.mDefaultSelect = defaultSelect;

        if (mInter != null && mList != null && defaultSelect > -1 && defaultSelect < mList.size()) {

            SelectBean bean = mInter.setSelectBean(mList.get(defaultSelect));

            mKey = bean.getKey();
            mId = bean.getId();
            mContentView.setText(mKey);

            mInter.onSelect(mList.get(defaultSelect), mKey, mId, defaultSelect);

        } else if (mRequestInter != null && mList != null && defaultSelect > -1 && defaultSelect < mList.size()) {

            SelectBean bean = mRequestInter.setSelectBean(mList.get(defaultSelect));

            mKey = bean.getKey();
            mId = bean.getId();
            mContentView.setText(mKey);

            mRequestInter.onSelect(mList.get(defaultSelect), mKey, mId, defaultSelect);
        }
    }

    /**
     * 设定最大宽度，保证其他view不被挤出屏幕
     */
    private void setContentTextMaxWidth() {
        int maxWidth = getPhoneWidth();        //屏幕宽度

        maxWidth -= getCount(R.dimen.dp15);   //条目标题距左的宽度

        maxWidth -= getTextWidth();    //条目标题宽度

        maxWidth -= isRequired ? getCount(R.dimen.dp30) : 0;      //“是否必选标记”的宽度

        maxWidth -= mLoadView.getVisibility() == View.VISIBLE ? getCount(R.dimen.dp25) : 0;   //加载view 的宽度

        maxWidth -= getCount(R.dimen.dp30);   //右角标 的宽度

        mContentView.setMaxWidth(maxWidth);
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
        paint = mNameView.getPaint();
        paint.getTextBounds(mNameText, 0, mNameText.length(), bounds);
        return bounds.width();
    }

    /**
     * 直接传入数据list、操作监听接口，没有默认选中
     *
     * @param list  数据列表
     * @param inter 回调接口
     */
    public void setListAndInter(List<T> list, SelectInter inter) {
        this.mList = list;
        this.mInter = inter;

        if (inter != null) {
            inter.toRequestCompletedSetSelect(mList, SelectBeanView.this);
        }
    }

    /**
     * 直接传入数据数组、操作监听接口，没有默认选中
     *
     * @param array 数据数组
     * @param inter 回调接口
     */
    public void setListAndInter(T[] array, SelectInter inter) {
        mList = new ArrayList<>();

        for (T t : array) {
            mList.add(t);
        }

        this.mInter = inter;

        if (inter != null) {
            inter.toRequestCompletedSetSelect(mList, SelectBeanView.this);
        }
    }

    /**
     * 直接传入数据list、默认选中项、操作监听接口
     *
     * @param list          数据列表
     * @param defaultSelect 默认选中项
     * @param inter         回调接口
     */
    public void setListAndInter(@Nullable List<T> list, int defaultSelect, SelectInter inter) {
        this.mList = list;
        this.mInter = inter;

        if (inter != null) {
            setDefaultSelect(defaultSelect);
        }
    }

    /**
     * 直接传入数据数组、默认选中项、操作监听接口
     *
     * @param array 数据数组
     * @param defaultSelect 默认选中项
     * @param inter         回调接口
     */
    public void setListAndInter(@Nullable T[] array, int defaultSelect, SelectInter inter) {
        mList = new ArrayList<>();

        for (T t : array) {
            mList.add(t);
        }

        this.mInter = inter;

        if (inter != null) {
            setDefaultSelect(defaultSelect);
        }
    }

    /**
     * 通过请求，获取列表信息
     *
     * @param inter 回调接口
     */
    public void setRequestDataSelect(final RequestDataSelectInter inter) {
        this.mRequestInter = inter;
        showLoad();

        //新建有序map
        HashMap map = new HashMap<String, Object>();

        //将map传回外部
        inter.setParams(map);

        JSONObject paramsJSON = new JSONObject();

        // 获取所有的键
        Set<String> set = map.keySet();

        // 遍历键的集合，获取每一个键
        for (String key : set) {
            // 根据键去找值
            paramsJSON.put(key, map.get(key));
        }

        HttpManager.getInstance().post(inter.setNetUrlEnum(), paramsJSON, new HttpManager.AbHttpResponseListenerInterface() {

            @Override
            public void onSuccess(String s) {

                JSONObject json = JSON.parseObject(s);
                JSONObject result = JSON.parseObject(json.getString("result"));
                String listStr = result.getString("mList");

                List<T> list = jsonToList(listStr, getInterClass());
                setList(list);

                hideLoad();

                if (inter != null) {
                    inter.toRequestCompletedSetSelect(list, SelectBeanView.this);
                }
            }

            @Override
            public void onFailure() {
                hideLoad();
            }
        });
    }

    /**
     * 获取接口和类上的泛型类型
     *
     * @return
     */
    private Class<T> getInterClass() {
        Type[] types = mRequestInter.getClass().getGenericInterfaces();
        ParameterizedType parameterized = (ParameterizedType) types[0];
        final Class<T> tClass = (Class<T>) parameterized.getActualTypeArguments()[0];
        return tClass;
    }

    /**
     * json 转 List<T>
     */
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }

    /**
     * 展示列表弹窗，根据Boolean值判断是否展示多选弹窗
     * @param list
     */
    private void showSelectDialog(List<T> list) {
        //是否多选，如果多选，就显示多选，默认单选
        if (isMultipleSelect) {
            showMultipleSelectDialog(list);

        } else {
            showSingleSelectDialog(list);
        }
    }

    /**
     * 单选列表弹窗
     *
     * @param list
     */
    public void showSingleSelectDialog(List<T> list) {
        ActionSheetDialog dialog = new ActionSheetDialog(getContext());
        dialog.builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true);

        for (final T t : list) {

            SelectBean bean = null;

            if (mInter != null) {
                bean = mInter.setSelectBean(t);

            } else if (mRequestInter != null) {
                bean = mRequestInter.setSelectBean(t);
            }

            final SelectBean finalBean = bean;
            dialog.addSheetItem(bean.getKey(), ActionSheetDialog.SheetItemColor.Green,
                    new ActionSheetDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which) {
                            mKey = finalBean.getKey();
                            mId = finalBean.getId();

                            mContentView.setText(mKey);

                            if (mInter != null) {
                                mInter.onSelect(t, finalBean.getKey(), finalBean.getId(), which - 1);

                            } else if (mRequestInter != null) {
                                mRequestInter.onSelect(t, finalBean.getKey(), finalBean.getId(), which - 1);
                            }
                        }
                    });
        }

        dialog.show();
    }

    /**
     * 多选列表弹窗
     *
     * @param list
     */
    public void showMultipleSelectDialog(List<T> list) {

        MultipleSelectDialog dialog = new MultipleSelectDialog(getContext());
        dialog.builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .setOnSureClickListener(new MultipleSelectDialog.OnSureClickListener() {
                    @Override
                    public void onClick() {

                        String dictionaryIds = "";

                        for (SelectBean bean : mSelectMap.values()) {
                            if (StringUtil.isEmpty(dictionaryIds)) {
                                dictionaryIds = bean.getId();
                            } else {
                                dictionaryIds += "," + bean.getId();
                            }
                        }

                        mId = dictionaryIds;
                    }
                });

        for (final T t : list) {

            SelectBean bean = null;

            if (mInter != null) {
                bean = mInter.setSelectBean(t);

            } else if (mRequestInter != null) {
                bean = mRequestInter.setSelectBean(t);
            }

            boolean isCheck = mSelectMap.get(bean.getId()) != null ? mSelectMap.get(bean.getId()).isCheck() : false;

            final SelectBean finalBean = bean;
            dialog.addSheetItem(bean.getKey(), isCheck, MultipleSelectDialog.SheetItemColor.Green_up,
                    new MultipleSelectDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which, boolean isCheck) {

                            if (!isCheck && mSelectMap.get(finalBean.getId()) != null) {
                                mSelectMap.remove(finalBean.getId());

                            } else {
                                finalBean.setCheck(isCheck);
                                mSelectMap.put(finalBean.getId(), finalBean);
                            }

                            String dictionaryNames = "";

                            for (SelectBean selectBean : mSelectMap.values()) {
                                if (StringUtil.isEmpty(dictionaryNames)) {
                                    dictionaryNames = selectBean.getKey();
                                } else {
                                    dictionaryNames += "、" + selectBean.getKey();
                                }
                            }

                            mContentView.setText(dictionaryNames);
                        }
                    });
        }

        dialog.show();
    }

    public List<T> getList() {
        return mList;
    }

    public void setList(List<T> mList) {
        this.mList = mList;
    }

    private void showLoad() {
        mLoadView.setVisibility(VISIBLE);
        mLoadView.show();
    }

    private void hideLoad() {
        mLoadView.hide();
        mLoadView.setVisibility(GONE);
    }

    /**
     * 获取选中内容是否为空的boolean值
     *
     * @return
     */
    public boolean isNotEmpty() {
        return StringUtil.isNotEmpty(mId);
    }

    /**
     * 获取选中内容是否为空的boolean值
     *
     * @return
     */
    public boolean isEmpty() {
        return StringUtil.isEmpty(mId);
    }

    public String getBeanKey() {
        return mKey;
    }

    public String getBeanId() {
        return mId;
    }

    /**
     * 传入list时使用的接口，限定输入、输出的数据
     *
     * @param <T>
     */
    public interface SelectInter<T> {
        void onSelect(T bean, String key, String id, int index);

        void toRequestCompletedSetSelect(List<T> list, SelectBeanView view);

        SelectBean setSelectBean(T bean);
    }

    /**
     * 请求数据时使用的接口，限定输入、输出的数据
     *
     * @param <T>
     */
    public interface RequestDataSelectInter<T> {
        void onSelect(T bean, String key, String id, int index);

        void toRequestCompletedSetSelect(List<T> list, SelectBeanView view);

        NetUrlEnum setNetUrlEnum();

        Map<String, Object> setParams(HashMap<String, Object> params);

        SelectBean setSelectBean(T bean);
    }


    /**
     * 内部使用的数据对象
     */
    public static class SelectBean {

        private String key;
        private String id;
        private boolean isCheck;        //"",

        public SelectBean(String key, String id) {
            this.key = key;
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }
    }
}
