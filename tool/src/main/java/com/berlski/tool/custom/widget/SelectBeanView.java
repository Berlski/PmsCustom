package com.berlski.tool.custom.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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
import com.berlski.tool.custom.enums.NetUrlEnum;
import com.berlski.tool.custom.manager.HttpManager;
import com.berlski.tool.custom.util.StringUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 信息编辑页，下弹菜单自定义view
 */
public class SelectBeanView<T> extends LinearLayout implements View.OnClickListener {

    private String dictionaryKey;
    private String dictionaryId;

    private AVLoadingIndicatorView loadView;
    private SelectInter inter;
    private RequestDataSelectInter requestInter;
    private List<T> list;
    private TextView tv_vds_select;
    private int defaultSelect;

    public SelectBeanView(Context context) {
        super(context);
    }

    public SelectBeanView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectBeanView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.view_dictionary_select, this);

        TextView nameText = findViewById(R.id.tv_vds_name);
        ImageView requiredMarker = findViewById(R.id.iv_vds_required_marker);
        tv_vds_select = findViewById(R.id.tv_vds_select);

        loadView = findViewById(R.id.avi_vds_load);
        loadView.setVisibility(GONE);

        //对属性进行解析
        // 由attrs 获得 TypeArray
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SelectDictionaryView);

        //设定提示
        String hint = ta.getString(R.styleable.SelectDictionaryView_sv_hint);
        if (StringUtil.isEmpty(hint)) {
            hint = getContext().getString(R.string.please_choose);
        }
        tv_vds_select.setHint(hint);

        //设定必选标识
        boolean requiredBlean = ta.getBoolean(R.styleable.SelectDictionaryView_sv_is_required, false);
        if (requiredBlean) {
            requiredMarker.setVisibility(VISIBLE);

        } else {
            requiredMarker.setVisibility(GONE);
        }

        //设定选项名称
        String name = ta.getString(R.styleable.SelectDictionaryView_sv_name);
        nameText.setText(name);

        //设定view点击事件
        this.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (inter != null && list != null) {
            showStateDialog(list);

        } else if (requestInter != null && list != null) {
            showStateDialog(list);
        }
    }

    /**
     * 设定默认选中项
     *
     * @param defaultSelect
     */
    public void setDefaultSelect(int defaultSelect) {
        this.defaultSelect = defaultSelect;

        if (inter != null && list != null && defaultSelect > -1 && defaultSelect < list.size()) {

            SelectBean bean = inter.setSelect(list.get(defaultSelect));

            dictionaryKey = bean.getKey();
            dictionaryId = bean.getId();
            tv_vds_select.setText(dictionaryKey);

            inter.onSelect(list.get(defaultSelect), dictionaryKey, dictionaryId, defaultSelect);

        } else if (requestInter != null && list != null && defaultSelect > -1 && defaultSelect < list.size()) {

            SelectBean bean = requestInter.setSelect(list.get(defaultSelect));

            dictionaryKey = bean.getKey();
            dictionaryId = bean.getId();
            tv_vds_select.setText(dictionaryKey);

            requestInter.onSelect(list.get(defaultSelect), dictionaryKey, dictionaryId, defaultSelect);
        }
    }

    /**
     * 直接传入数据list，没有默认选中
     *
     * @param list  数据列表
     * @param inter 回调接口
     */
    public void setListAndInter(List<T> list, SelectInter inter) {
        this.list = list;
        this.inter = inter;
    }

    /**
     * 直接传入数据数组，没有默认选中
     *
     * @param array 数据数组
     * @param inter 回调接口
     */
    public void setListAndInter(T[] array, SelectInter inter) {
        list = new ArrayList<>();

        for (T t : array) {
            list.add(t);
        }

        this.inter = inter;
    }

    /**
     * 直接传入数据list，有默认选中
     *
     * @param list          数据列表
     * @param defaultSelect 默认选中项
     * @param inter         回调接口
     */
    public void setListAndInter(@Nullable List<T> list, int defaultSelect, SelectInter inter) {
        this.list = list;
        this.inter = inter;

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
        this.requestInter = inter;
        showLoad();

        //新建有序map
        TreeMap map = new TreeMap<String, Object>();

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
                String listStr = result.getString("list");

                List<T> list = jsonToList(listStr, getInterClass());
                setList(list);

                hideLoad();

                if (inter != null) {
                    inter.toSetDefaultSelect(list, SelectBeanView.this);
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
        Type[] types = requestInter.getClass().getGenericInterfaces();
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
     * 列表弹窗
     *
     * @param list
     */
    public void showStateDialog(List<T> list) {
        ActionSheetDialog dialog = new ActionSheetDialog(getContext());
        dialog.builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true);

        for (final T t : list) {

            SelectBean bean = null;

            if (inter != null) {
                bean = inter.setSelect(t);

            } else if (requestInter != null) {
                bean = requestInter.setSelect(t);
            }

            final SelectBean finalBean = bean;
            dialog.addSheetItem(bean.getKey(), ActionSheetDialog.SheetItemColor.Green,
                    new ActionSheetDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which) {
                            dictionaryKey = finalBean.getKey();
                            dictionaryId = finalBean.getId();

                            tv_vds_select.setText(dictionaryKey);

                            if (inter != null) {
                                inter.onSelect(t, finalBean.getKey(), finalBean.getId(), which - 1);

                            } else if (requestInter != null) {
                                requestInter.onSelect(t, finalBean.getKey(), finalBean.getId(), which - 1);
                            }
                        }
                    });
        }

        dialog.show();
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void showLoad() {
        loadView.setVisibility(VISIBLE);
        loadView.show();
    }

    public void hideLoad() {
        loadView.hide();
        loadView.setVisibility(GONE);
    }

    public String getBeanKey() {
        return dictionaryKey;
    }

    public String getBeanId() {
        return dictionaryId;
    }

    public interface SelectInter<T> {
        void onSelect(T bean, String key, String id, int index);

        void toSetDefaultSelect(List<T> list, SelectBeanView view);

        SelectBean setSelect(T bean);
    }

    public interface RequestDataSelectInter<T> {
        void onSelect(T bean, String key, String id, int index);

        void toSetDefaultSelect(List<T> list, SelectBeanView view);

        NetUrlEnum setNetUrlEnum();

        Map<String, Object> setParams(TreeMap<String, Object> params);

        SelectBean setSelect(T bean);
    }

    public static class SelectBean {

        private String key;
        private String id;

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
    }
}
