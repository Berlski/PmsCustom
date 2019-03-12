package com.berlski.tool.custom.enums;

/**
 * copywrite 2015-2020 金地物业
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 字典枚举（Enum）
 * Created by Berlski on 2018/10/21.
 */
public enum DictionaryEnum {


    /**
     * 获取“成交方式”字典列表，所需mark 值
     * mark 值对应名称
     */
    TRANSACTION_MODE("6ae4d789-4d0b-476d-ab0b-7411614f269d", "成交方式"),

    /**
     * 获取“付款方式”字典列表，所需mark 值
     * mark 值对应名称
     */
    PAYMENT_METHOD("31841886-28ec-45dc-aaec-67c40f7a73fe", "付款方式"),

    /**
     * 获取“其他费用”字典列表，所需mark 值
     * mark 值对应名称
     */
    OTHER_CHARGES("93e9ae54-12b6-47ad-9419-11514d8c1712", "其他费用"),

    /**
     * 获取“房源配置”字典列表，所需mark 值
     * mark 值对应名称
     */
    HOUSE_CONFIGURE("8c37aabd-7185-4467-967b-5b3cab887505", "房源配置"),

    /**
     * 获取“资源类型”字典列表，所需mark 值
     * mark 值对应名称
     */
    RESOURC_TYPE("3d339000-5b37-4689-9bb0-a34f8d463b70", "资源类型"),

    /**
     * 获取“最短租期”字典列表，所需mark 值
     * mark 值对应名称
     */
    SHORTEST_LEASE("0ae4baac-a453-4130-97f0-e9a3bb2a8eec", "最短租期"),

    /**
     * 获取“出租类型”字典列表，所需mark 值
     * mark 值对应名称
     */
    RENTING_TYPE("f3556b81-2f90-4e3b-8138-75070410fc9f", "出租类型"),

    /**
     * 获取“客户来源”字典列表，所需mark 值
     * mark 值对应名称
     */
    CUSTOMER_SOURCE("44d8d93e-73f2-475e-a854-ec0a0cf513ad", "客户来源"),

    /**
     * 获取“装修程度”字典列表，所需mark 值
     * mark 值对应名称
     */
    DEGREE_OF_DECORATION("504884c6-5bef-4693-b1dc-e096f7a9b1e0", "装修程度"),

    /**
     * 获取“租期类型”字典列表，所需mark 值
     * mark 值对应名称
     */
    LEASE_TERM_TYPE("df5ac7e8-e0d8-4345-83cd-ed30317cca3f", "租期类型"),

    /**
     * 获取“成交方式”字典列表，所需mark 值
     * mark 值对应名称
     */
    TRANSACTION_MODE_("18a66e3a-82f8-460d-b7f2-eaf671f48b13", "成交方式"),

    /**
     * 获取“重视程度”字典列表，所需mark 值
     * mark 值对应名称
     */
    DEGREE_OF_ATTENTION("97efd4f6-c163-4b6d-8bf1-7b4887f45930", "重视程度"),

    /**
     * 获取“房屋朝向”字典列表，所需mark 值
     * mark 值对应名称
     */
    HOUSE_ORIENTATION("5045ea9c-c341-446a-ab7b-a635e57f778d", "房屋朝向"),

    /**
     * 获取“房屋性质”字典列表，所需mark 值
     * mark 值对应名称
     */
    PROPERTY_OF_THE_HOUSE("dd740964-9d7c-4c98-adc6-54436742e3e0", "房屋性质"),

    /**
     * 获取“房屋特色”字典列表，所需mark 值
     * mark 值对应名称
     */
    HOUSE_CHARACTERISTICS("04b91d6b-44ca-43ee-870a-e078ac2c6771", "房屋特色"),

    /**
     * 获取“物品类型”字典列表，所需mark 值
     * mark 值对应名称
     */
    GOODS_TYPE("a93440ec-dd8a-481c-81e5-adddbefaaeb0", "物品类型"),

    /**
     * 获取“物品品牌”字典列表，所需mark 值
     * mark 值对应名称
     */
    GOODS_BRAND("b3e3d6cc-59cf-4d16-9f07-9114f5053842", "物品品牌"),

    /**
     * 获取“支付类型”字典列表，所需mark 值
     * mark 值对应名称
     */
    PAYMENT_TYPE("eec5e676-c364-4dd1-a0a1-716ccfdb00f8", "支付类型"),

    /**
     * 获取“提前付款天数”字典列表，所需mark 值
     * mark 值对应名称
     */
    DAYS_OF_ADVANCE_PAYMENT("4f405d10-8a62-11e7-bb31-be2e44b06b34", "提前付款天数"),

    /**
     * 获取“跟进方式”字典列表，所需mark 值
     * mark 值对应名称
     */
    FOLLOW_TYPE("4970ee2f-1895-4312-bcda-58e198806bd2", "跟进方式"),

    /**
     * 获取“维修类型”字典列表，所需mark 值
     * mark 值对应名称
     */
    MAINTENANCE_TYPE("0dc1fd1a-2991-4f43-8497-a144b644b3f0", "维修类型"),

    /**
     * 获取“账户类型”字典列表，所需mark 值
     * mark 值对应名称
     */
    ACCOUNT_TYPE("550fc90b-d491-4a79-9040-dead2e915eb9", "账户类型"),

    /**
     * 获取“个人爱好”字典列表，所需mark 值
     * mark 值对应名称
     */
    PERSONAL_HOBBY("039ebf4015054fd8befef29883418333", "个人爱好"),

    /**
     * 获取“职业类型”字典列表，所需mark 值
     * mark 值对应名称
     */
    OCCUPATION_TYPE("44c2b9a627e44422810df13ef58a9d24", "职业类型");


    private String mark;
    private String markName;

    DictionaryEnum(String mark) {
        this.mark = mark;
    }

    DictionaryEnum(String mark, String markName) {
        this.mark = mark;
        this.markName = markName;
    }

    public String getMark() {
        return mark;
    }

    public String getMarkName() {
        return markName;
    }

    public static DictionaryEnum getDictionaryEnum(int type) {
        DictionaryEnum dictionaryEnum = null;

        switch (type) {

            //成交方式
            case 0x0001:
                dictionaryEnum = DictionaryEnum.TRANSACTION_MODE;
                break;


            //付款方式
            case 0x0002:
                dictionaryEnum = DictionaryEnum.PAYMENT_METHOD;
                break;

            //其他费用
            case 0x0003:
                dictionaryEnum = DictionaryEnum.OTHER_CHARGES;
                break;

            //房源配置
            case 0x0004:
                dictionaryEnum = DictionaryEnum.HOUSE_CONFIGURE;
                break;

            //资源类型
            case 0x0005:
                dictionaryEnum = DictionaryEnum.RESOURC_TYPE;
                break;

            //最短租期
            case 0x0006:
                dictionaryEnum = DictionaryEnum.SHORTEST_LEASE;
                break;

            //出租类型
            case 0x0007:
                dictionaryEnum = DictionaryEnum.RENTING_TYPE;
                break;

            //客户来源
            case 0x0008:
                dictionaryEnum = DictionaryEnum.CUSTOMER_SOURCE;
                break;

            //装修程度
            case 0x0009:
                dictionaryEnum = DictionaryEnum.DEGREE_OF_DECORATION;
                break;

            //租期类型
            case 0x0010:
                dictionaryEnum = DictionaryEnum.LEASE_TERM_TYPE;
                break;

            //成交方式
            case 0x0011:
                dictionaryEnum = DictionaryEnum.TRANSACTION_MODE_;
                break;

            //重视程度
            case 0x0012:
                dictionaryEnum = DictionaryEnum.DEGREE_OF_ATTENTION;
                break;

            //房屋朝向
            case 0x0013:
                dictionaryEnum = DictionaryEnum.HOUSE_ORIENTATION;
                break;

            //房屋性质
            case 0x0014:
                dictionaryEnum = DictionaryEnum.PROPERTY_OF_THE_HOUSE;
                break;

            //房屋特色
            case 0x0015:
                dictionaryEnum = DictionaryEnum.HOUSE_CHARACTERISTICS;
                break;

            //物品类型
            case 0x0016:
                dictionaryEnum = DictionaryEnum.GOODS_TYPE;
                break;

            //物品品牌
            case 0x0017:
                dictionaryEnum = DictionaryEnum.GOODS_BRAND;
                break;

            //支付类型
            case 0x0018:
                dictionaryEnum = DictionaryEnum.PAYMENT_TYPE;
                break;

            //提前付款天数
            case 0x0019:
                dictionaryEnum = DictionaryEnum.DAYS_OF_ADVANCE_PAYMENT;
                break;

            //跟进方式
            case 0x0020:
                dictionaryEnum = DictionaryEnum.FOLLOW_TYPE;
                break;

            //维修类型
            case 0x0021:
                dictionaryEnum = DictionaryEnum.MAINTENANCE_TYPE;
                break;

            //账户类型
            case 0x0022:
                dictionaryEnum = DictionaryEnum.ACCOUNT_TYPE;
                break;

            //个人爱好
            case 0x0023:
                dictionaryEnum = DictionaryEnum.PERSONAL_HOBBY;
                break;

            //职业类型
            case 0x0024:
                dictionaryEnum = DictionaryEnum.OCCUPATION_TYPE;
                break;
        }

        return dictionaryEnum;
    }
}
