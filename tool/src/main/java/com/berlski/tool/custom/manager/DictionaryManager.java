package com.berlski.tool.custom.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.berlski.tool.custom.bean.ResultArray;
import com.berlski.tool.custom.dao.bean.Dictionary;
import com.berlski.tool.custom.dao.operation.GreenDaoOperation;
import com.berlski.tool.custom.enums.DictionaryEnum;
import com.berlski.tool.custom.enums.NetUrlEnum;
import com.berlski.tool.custom.inter.DictionaryInter;
import com.berlski.tool.custom.util.LogUtil;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 字典数据请求管理、数据库管理（Util）
 * Created by Berlski on 2019/1/22.
 */
public class DictionaryManager {

    private static DictionaryManager instance;

    /**
     * 单一实例
     */
    public static DictionaryManager instance() {
        if (instance == null) {
            instance = new DictionaryManager();
        }
        return instance;
    }

    /**
     * 数据初始化，强制更新所有字典包
     */
    public void init() {

        for (DictionaryEnum dictionaryEnum : DictionaryEnum.values()) {
            //请求数据
            request(dictionaryEnum, null);
        }
    }

    /**
     * 获取字典列表
     *
     * @param dictionaryEnum  字典枚举
     * @param dictionaryInter 接口
     */
    public void getDictionary(DictionaryEnum dictionaryEnum, DictionaryInter dictionaryInter) {

        if (getList(dictionaryEnum) != null) {

            dictionaryInter.onSuccess(getList(dictionaryEnum));

        } else {

            //请求数据
            request(dictionaryEnum, dictionaryInter);
        }
    }


    /**
     * 根据字典枚举，获取Map中存储的字典列表
     *
     * @param dictionaryEnum 字典枚举
     * @return 如果获取不到字典列表，或者字典列表长度为0，则返回 null
     */
    private List<Dictionary> getList(DictionaryEnum dictionaryEnum) {

        List<Dictionary> dictionaryList = GreenDaoOperation.builder().getDictionary(dictionaryEnum);

        if (dictionaryList != null && dictionaryList.size() != 0) {

            //新建一个list，返回出去后，list 跟字典 Map 再无关系
            return dictionaryList;

        } else {
            return null;
        }
    }

    /**
     * 请求字典list数据
     *
     * @param dictionaryEnum 字典枚举
     */
    private void request(final DictionaryEnum dictionaryEnum, final DictionaryInter dictionaryInter) {
        LogUtil.e("开始请求字典");

        JSONObject paramsJSON = new JSONObject();
        paramsJSON.put("mark", dictionaryEnum.getMark());                     //

        HttpManager.getInstance().post(NetUrlEnum.GET_ZI_DIAN, paramsJSON, new HttpManager.AbHttpResponseListenerInterface() {
            @Override
            public void onSuccess(String s) {
                LogUtil.e("请求字典完成：" + s);

                ResultArray<Dictionary> tResultArray = JSON.parseObject(s.toString(), new TypeToken<ResultArray<Dictionary>>() {
                }.getType());
                //判断结果集下的结果子集是否为非空
                if (tResultArray.getResult() != null) {

                    List<Dictionary> list = tResultArray.getResult().getList();

                    if (list == null) {
                        list = new ArrayList<>();
                    }

                    //通过mark值，新增 或更新字典列表
                    GreenDaoOperation.builder().addDictionary(list, dictionaryEnum);

                    if (dictionaryInter != null) {

                        //List<Dictionary> dictionaryBeanList = new ArrayList<>();
                        //dictionaryBeanList.addAll(list);
                        dictionaryInter.onSuccess(list);
                    }

                } else {
                    /*if (dictionaryInter != null) {
                        dictionaryInter.onFailure();
                    }*/
                }
            }

            @Override
            public void onFailure() {
                /*if (dictionaryInter != null) {
                    dictionaryInter.onFailure();
                }*/
            }
        });
    }
}
