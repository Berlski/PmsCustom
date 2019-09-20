package com.berlski.tool;

import android.support.annotation.Nullable;

import com.berlski.tool.test.ItemBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Copyright (SaaS 技术团队)
 * FileName: TenantLoanProgressAdapter
 * Author: Berlski
 * Date: 2019/9/19 10:20
 * Description: ${  }
 */
public class ItemViewAdapter extends BaseQuickAdapter<ItemBean, BaseViewHolder> {


    public ItemViewAdapter(@Nullable List<ItemBean> data) {
        super(R.layout.item_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemBean item) {
        helper.setText(R.id.iv_name, item.getName());
    }
}
