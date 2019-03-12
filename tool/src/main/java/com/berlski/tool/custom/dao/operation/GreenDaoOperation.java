package com.berlski.tool.custom.dao.operation;

import com.berlski.tool.custom.dao.DictionaryDao;
import com.berlski.tool.custom.dao.bean.Dictionary;
import com.berlski.tool.custom.enums.DictionaryEnum;
import com.berlski.tool.custom.manager.GreenDaoManager;
import com.berlski.tool.custom.util.LogUtil;

import java.util.List;

/**
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 数据库工具（Util）
 * Created by Berlski on 2019/1/22.
 */
public class GreenDaoOperation {

    private static GreenDaoOperation instance;

    public static GreenDaoOperation builder() {
        if (instance == null) {
            instance = new GreenDaoOperation();
        }
        return instance;
    }


    /**
     * 获取表
     * get the note DAO
     *
     * @return
     */
    private DictionaryDao getDictionaryDao() {
        return GreenDaoManager.getInstance().getDaoSession(false).getDictionaryDao();
    }

    /**
     * 删除所有字典
     */
    public void deleteAllDictionary() {

        //清空所有的数据
        getDictionaryDao().deleteAll();
    }

    /**
     * 根据字典枚举mark值，新增或修改覆盖字典列表
     *
     * @param dictionaryBeanList
     * @param dictionaryEnum
     */
    public void addDictionary(List<Dictionary> dictionaryBeanList, DictionaryEnum dictionaryEnum) {

        //先查找，数据表中有没有
        for (Dictionary bean : dictionaryBeanList) {
            bean.setDictionaryType(dictionaryEnum.getMark());
            try {
                getDictionaryDao().insert(bean);
            } catch (Exception e) {
                LogUtil.e("存储位置失败：", e);
                getDictionaryDao().update(bean);
            }
        }
    }

    /**
     * 根据字典枚举mark值，查找字典列表
     *
     * @param dictionaryEnum
     * @return
     */
    public List<Dictionary> getDictionary(DictionaryEnum dictionaryEnum) {

        LogUtil.e("查找字典包");

        //通过DictionaryDao的queryBuilder()方法，生成一个查找构造器
        List<Dictionary> dictionaryList = getDictionaryDao().queryBuilder()

                .where(DictionaryDao.Properties.DictionaryType.eq(dictionaryEnum.getMark()))    //给构造器添加where条件判断

                //.orderAsc(DictionaryDao.Properties.DictionaryType)      //按照某某字段排序

                //.limit(1)     //查询的条数

                .build().list();//list()方法表示查询的结果为一个集合，unique()方法表示查询的结果为一个对象

        return dictionaryList;
    }
}
