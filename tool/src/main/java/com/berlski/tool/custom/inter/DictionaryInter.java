package com.berlski.tool.custom.inter;


import com.berlski.tool.custom.dao.bean.Dictionary;

import java.util.List;

/**
 * copywrite 2015-2020 金地物业
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 字典列表请求回调接口类
 * Created by Berlski on 2018/1/15.
 */
public interface DictionaryInter {

    /**
     * 接口回调时，表示获取数据成功
     *
     * @param list 回传字典列表
     */
    void onSuccess(List<Dictionary> list);

    /**
     * 接口回调时，表示获取数据失败
     */
    //void onFailure();
}
