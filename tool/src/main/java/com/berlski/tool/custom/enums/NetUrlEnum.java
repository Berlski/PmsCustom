package com.berlski.tool.custom.enums;

/**
 * Created by Administrator on 2015/12/17.
 */
public enum NetUrlEnum {

    //新增支出清单
    XZ_ZC_QD("/v2/rentservice/table_clean/add_pay_money", "------------------------"),

    //获取所有经纪人
    GET_DEPARTMENT("/v2/sys/table_jjr_user/get_list", "获取所有经纪人"),

    //新系统登陆接口
    NEW_LOGIN_STSYE("/v2/jjr_user_login/app_login", "新系统登陆接口"),

    //智能门锁
    //云柚一键开门
    SUOKONGZHI("/v2/lockmanage/lock_util/suokongzhi", "------------------------"),

    //判断是否绑定门锁
    SHIFOU_BANGDINGLOCK("/v2/lockmanage/lock_util/selislock", "------------------------"),

    //果加/云柚动态密码开门
    GUOJIAYUNYOU_DONGTAIPWD("/v2/lockmanage/lock_util/sel_DynamicPwd", "------------------------"),

    //果加蓝牙锁,蓝牙动态码
    GUOJIA_LANYAPWD("/v2/lockmanage/lock_util/getLanyaTimePwd", "------------------------"),

    //果加蓝牙一键开门
    GUOJIA_LANYAKM("/v2/lockmanage/lock_util/appopenlock", "------------------------"),

    //丁丁门锁获取动态码
    DD_DONGTAIPWD("/v2/lockmanage/lock_util/dynamicpwd", "------------------------"),

    //判断果加的锁是否绑定公司
    GUOJIA_ISBANGDINGGONGSI("/v2/lockmanage/lock_util/isgc", "------------------------"),

    //丁丁锁是否绑定公司
    DD_ISBANGDINGGONGS("/v2/lockmanage/lock_util/isgcfording", "------------------------"),

    //榉树锁是否绑定公司
    JS_ISBANGDINGGONGS("/v2/jushulock/selUsertologin", "------------------------"),

    //榉树的蓝牙开锁
    JS_LANYAKAIMEN("/v2/jushulock/lanyaKey", "------------------------"),

    //榉树的一次性密码
    JS_YICIXINGPWD("/v2/jushulock/pGetZotpPwd", "------------------------"),

    //判断保洁是否有供应商
    GYS("/v2/rentservice/table_clean/select_supplier", "------------------------"),

    //判断维修是否有供应商
    WX_GYS("/v2/rentservice/table_web_repair/select_supplier", "------------------------"),

    //维修保洁
    //维修列表
    WX_LIST("/v2/rentservice/table_web_repair/get_list", "------------------------"),

    //保洁列表
    BJ_LIST("/v2/rentservice/table_clean/get_list", "------------------------"),

    //投诉列表
    TS_LIST("/v2/rentservice/complaint_letter/get_list", "------------------------"),

    //提交维修申请
    WX_SHENQING_TIJIAO("/v2/rentservice/table_web_repair/insert", "------------------------"),

    //提交保洁申请
    BJ_SHENQING_TIJIAO("/v2/rentservice/table_clean/insert", "------------------------"),

    //维修取消订单
    WX_QUXIAODINGDAN("/v2/rentservice/table_web_repair/abolish_orders", "------------------------"),

    //保洁取消订单
    BJ_QUXIAODINGDAN("/v2/rentservice/table_clean/abolish_orders", "------------------------"),

    //维修提交派单
    WX_TIJIAOPD("/v2/rentservice/table_web_repair/send_orders", "------------------------"),

    //保洁提交派单
    BJ_TIJIAOPD("/v2/rentservice/table_clean/send_orders", "------------------------"),

    //维修自己处理
    WX_ZJCL("/v2/rentservice/table_web_repair/my_handle_orders", "------------------------"),

    //保洁自己处理
    BJ_ZJCL("/v2/rentservice/table_clean/my_handle_orders", "------------------------"),

    //维修验收的网络请求
    WX_OKYS("/v2/rentservice/table_web_repair/check_orders", "------------------------"),

    //保洁验收
    BJ_OKYS("/v2/rentservice/table_clean/check_orders", "------------------------"),

    //维修提交评价
    WX_TIJIAOPINGJIA("/v2/rentservice/table_web_repair/grade", "------------------------"),

    //保洁提交评价
    BJ_TIJIAOPINGJIA("/v2/rentservice/table_clean/grade", "------------------------"),

    //维修代替工人提交维修完成
    WX_DATGRSCWX("/v2/rentservice/table_web_repair/finish_orders", "------------------------"),

    //保洁代替工人提交维修完成
    BJ_DATGRSCWX("/v2/rentservice/table_clean/finish_orders", "------------------------"),

    //获取维修保洁支出清单
    WX_BJ_ZC("/v2/balance/table_balance_sheet/get_list", "------------------------"),

    //获取房源搜索列表
    GET_HOUSE_SEARCH_LIST("/v2/house/house/search_list", "获取房源搜索列表"),

    //获取人员   租客list  业主
    ZUKE_USER_LIST("/v2/compact/chengzu/search_list", "------------------------"),

    YEZHU_USER_LIST("/v2/compact/qian_yue/search_list", "------------------------"),

    /**
     * 扫码登录
     */
    SAOMIAO_LOGIN("/v2/jjr_user_login/app_scan_code_login", "扫码登录"),

    //投诉处理完成
    TS_CLWC("/v2/rentservice/complaint_letter/finish_orders", "------------------------"),

    //投诉待处理指派
    TS_ZPWC("/v2/rentservice/complaint_letter/send_orders", "------------------------"),

    //投诉待处理自己处理
    TS_ZJCLWC("/v2/rentservice/complaint_letter/my_handle_orders", "------------------------"),

    //投诉验收通过
    TS_YSTG("/v2/rentservice/complaint_letter/check_orders", "------------------------"),

    //房态模块
    //集中房态获取项目
    HOUSE_PROJECT("/v2/item/house_item/get_list", "------------------------"),

    //房态获取座栋的网路请求
    HOUSE_LOUDONG("/v2/item/house_lou_dong/get_list", "------------------------"),

    //集中房态获取房源列表
    HOUSE_TYPELIST("/v2/house/focus_house/get_list", "------------------------"),

    //合租房态获取主房源列表
    HEZU_HOUSETYPE("/v2/house/part_house/get_list", "------------------------"),

    //合租根据主房源获取子间
    HEZU_HOUSEZIJIAN("/v2/house/part_house/get_list_by_parent_ids", "------------------------"),

    //整租房态获取房源列表
    HEZU_HOUSEZHENGZU("/v2/house/full_house/get_list", "------------------------"),

    //房态位置获取城市
    HOUSE_CITY("/v2/sys/resources_adjust/get", "------------------------"),

    //房态位置获取商圈
    HOUSE_SHANGQUAN("/v2/location/district/get_list", "房态位置获取商圈"),

    //房态位置获取部门
    HOUSE_BUMEN("/v2/sys/department/get_list", "房态位置获取部门"),

    //合租房态获取房源详情
    HEZUHONGHOUSE_INFO("/v2/house/part_house/get_by_id", "合租房态获取房源详情"),

    //整租房态获取房源详情
    ZHENGZUHONGHOUSE_INFO("/v2/house/full_house/get_by_id", "整租房态获取房源详情"),

    //房态获取负责人信息
    HOUSEINFO_FUZEREN("/v2/house/house_fuzeren/get_list", "------------------------"),

    //房态获取房源图片
    HOUSE_TUPIAN("/v2/accessory/pic/get_list", "房态获取房源图片"),

    //房态整租根据城市获取区县
    ZZ_HOUSE_GETREGION("/v2/web/house/getjjrfullhousenumbycityid", "------------------------"),

    //房态整租根据区县获取商圈
    ZZ_HOUSE_GETAREA("/v2/web/house/getjjrfullhousenumbytownid", "------------------------"),

    //房态整租根据商圈获取小区
    ZZ_HOUSE_COMMUNTIY("/v2/web/house/getjjrfullhousebydistrictid", "------------------------"),

    //房态整租根据小区名称获取小区列表
    ZZ_HOUSE_COMMUNTIYLIST("/v2/web/house/getjjrfullhousebycommunityname", "------------------------"),

    //房态合租根据城市获取区县
    HZ_HOUSE_GETREGION("/v2/web/house/getjjrparthousenumbycityid", "------------------------"),

    //房态合租根据区县获取商圈
    HZ_HOUSE_GETAREA("/v2/web/house/getjjrparthousenumbytownid", "------------------------"),

    //房态合租根据商圈获取小区
    HZ_HOUSE_COMMUNTIY("/v2/web/house/getjjrparthousebydistrictid", "------------------------"),

    //房态合租根据小区名获取小区列表
    HZ_HOUSE_COMMUNTIYLIST("/v2/web/house/getjjrparthousebycommunityname", "------------------------"),


    //预约客户--预约列表
    GET_ORDER_RENTER("/v2/meet/renter_info/get_list", "------------------------"),

    //预约客户--新增跟进
    ADD_FOLLOW_UP("/v2/meet/follow_info/save", "------------------------"),


    //预约客户--跟进记录列表
    GET_FOLLOW_UP_LIST("/v2/meet/follow_info/get_list", "------------------------"),

    //预约客户--获取编辑信息
    GET_CLIENT_DATA("/v2/meet/renter_info/get", "------------------------"),

    //预约客户--保存编辑信息
    SET_CLIENT_DATA("/v2/meet/renter_info/save", "预约客户--保存编辑信息"),


    //预约客户--指派
    CLIENT_ASSIGN("/v2/meet/renter_info/update", "------------------------"),

    //预约客户--无效
    CLIENT_INVALID("/v2/meet/renter_info/update", "------------------------"),

    //预约客户--转为公客/私客
    ORDER_GUEST_CONVERSION("/v2/meet/renter_info/updatePublic", "------------------------"),

    //公客、私客跟进记录列表
    GUEST_FOLLOW_UP_LIST("/v2/meet/guest_follow_log/get_list", "------------------------"),

    //公盘、私盘跟进记录列表
    OWNER_FOLLOW_UP_LIST("/v2/owner/owner_floow/get_list", "------------------------"),

    //公客、私客录入客源
    SAVE_GUEST_INFO("/v2/meet/guest_source_pools/save", "------------------------"),

    //公盘、私盘、线上委托录入客源、编辑资料
    SAVE_OWNER_INFO("/v2/owner/owner_resource_pool/save", "公盘、私盘、线上委托录入客源、编辑资料"),

    //公客、私客编辑资料
    EDIT_GUEST_INFO("/v2/meet/guest_source_pools/edit", "公客、私客编辑资料"),

    //公客、私客获取客户信息
    GET_CLIENT_INFO("/v2/meet/guest_source_pools/get", "------------------------"),

    //公盘、私盘获取客户信息
    GET_OWNER_INFO("/v2/owner/owner_resource_pool/get", "公盘、私盘获取客户信息"),

    //获取公客私客列表
    GET_GUEST_DATA("/v2/meet/guest_source_pools/get_list", "获取公客私客列表"),

    //获取公盘私盘列表
    GET_OWNER_DATA("/v2/owner/owner_resource_pool/get_list", "获取公盘私盘列表"),

    //公客、私客--指派
    GUEST_ASSIGN("/v2/meet/guest_source_pools/assign", "------------------------"),

    //公盘、私盘--指派
    OWNER_ASSIGN("/v2/owner/owner_resource_pool/save_assign", "公盘、私盘--指派"),

    //公客、私客--无效
    GUEST_INVALID("/v2/meet/guest_source_pools/set_status", "------------------------"),

    //公盘、私盘--无效
    OWNER_INVALID("/v2/owner/owner_resource_pool/update", "公盘、私盘--无效"),

    //私客--转为公客
    GUEST_TO_PUBLIC("/v2/meet/guest_source_pools/update_renter_type", "------------------------"),

    //业主资源--转为公盘
    OWNER_TO_PUBLIC("/v2/owner/owner_resource_pool/turn_to_public", "业主资源--转为公盘"),

    //公客、私客--转为私客
    GUEST_TO_PRIVATE("/v2/meet/guest_source_pools/change_renter_type", "------------------------"),

    //业主资源--转为私盘
    OWNER_TO_PRIVATE("/v2/owner/owner_resource_pool/turn_to_private", "业主资源--转为私盘"),

    //公客、私客--新增跟进
    GUEST_ADD_FOLLOW_UP("/v2/meet/guest_follow_log/insert", "------------------------"),

    //公盘、私盘--新增跟进
    OWNER_ADD_FOLLOW_UP("/v2/owner/owner_floow/insert", "公盘、私盘--新增跟进"),

    //获取字典
    GET_ZI_DIAN("/v2/sys/zi_dian/get_list_by_mark", "获取字典"),

    //获取城市
    GET_CITY("/v2/sys/resources_adjust/get", "获取城市"),

    //获取付款天数
    GET_EARYLY("/v2/sys/decision/get", "获取付款天数"),

    //获取区域
    GET_AREA_LIST("/v2/location/town/get_list", "获取区域"),

    //签约提交的网络请求
    GET_QIANYUE_TIJIAO("/v2/compact/chengzu/sign_contract", "------------------------"),

    //租客续签的网络请求
    GET_ZKXQ("/v2/compact/chengzu/renew", "------------------------"),

    //收支列表
    GET_SHOUZHI_LIST("/v2/compact/chengzu/anticipated_revenue", "------------------------"),

    //获取合同列表
    GET_CONTRACT_LIST("/v2/compact/chengzu/get_list", "------------------------"),

    //获取我的合同列表
    GET_MY_CONTRACT_LIST("/v2/compact/chengzu/get_my_list", "------------------------"),

    //获取合同详情
    GET_CONTRACT_INFO("/v2/compact/chengzu/get_by_id", "------------------------"),

    //给租客发送电子合同
    GET_ZUKE_FSHT("/v2/contract/electronic_contract/contract_template_print", "------------------------"),//棉花助手专用

    GET_LOCK_DIANZIHT("/v2/contract/electronic_contract/selcontract", "------------------------"),

    //获取物业交割
    GET_WUYE_DATA("/v2/house/house_delivery/get", "------------------------"),

    //获取收支列表
    GET_SHOU_ZHI_LIST("/v2/balance/table_balance_sheet/get_compact_list", "------------------------"),

    //生成合同
    CREATE_PACT("/v2/compact/chengzu/update_status", "------------------------"),

    //合同审核
    CHECK_PACT("/v2/compact/chengzu/auditing", "------------------------"),

    //合同作废
    CANCEL_PACT("/v2/compact/chengzu/update_status", "------------------------"),

    //添加合同备注
    ADD_HOUSE_BEIZU("/v2/house/part_house/update", "------------------------"),

    //问题反馈
    QUESTION_UP("/v2/interlocution/online_opinion/insert", "问题反馈"),

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //获取代办已完成列表
    GET_COMPLETE_REMIND_LIST("/v2/employee/time_manager/get_complete_list", "获取代办已完成列表"),

    //拆分 获取代办未完成列表
    GET_NOT_COMPLETE_LIST("/v2/employee/time_manager/get_un_complete_list", "拆分 获取代办未完成列表"),

    //代办置顶
    TO_TOP_REMIND_LIST("/v2/employee/time_manager/update_by_id", "代办置顶"),

    //代办转为已经完成
    TO_COMPLETE_REMIND_LIST("/v2/employee/time_manager/update_complete", "代办转为已经完成"),

    //代办转为未完成
    TO_NOT_COMPLETE_REMIND_LIST("/v2/employee/time_manager/update", "代办转为未完成"),

    //分享待办
    SHARE_REMIND("/v2/employee/time_manager/insert_list", "分享待办"),

    //添加待办
    ADD_TO_DO("/v2/employee/time_manager/insert", "添加待办"),

    //删除待办
    DELETE_TO_DO("/v2/employee/time_manager/delete_by_id", "删除待办"),

    //修改日程
    MODIFY_REMIND("/v2/employee/time_manager/update", "修改日程"),

    //合租房源状态统计
    GET_HZ_HOUSE_COUNT("/v2/house/part_house/get_count_by_status", "------------------------"),

    //整租房源状态统计
    GET_ZZ_HOUSE_COUNT("/v2/house/full_house/get_count_by_status", "------------------------"),

    //集中房源状态统计
    GET_JZ_HOUSE_COUNT("/v2/house/focus_house/get_count_by_status", "------------------------"),

    //房源自动计算退租或违约后应退金额
    FY_AUTO_COUNT("/v2/compact/chengzu/violation_calculate", "------------------------"),

    //获取仪表列表
    WYJG_GETMETER("/v2/device/meter/get_list", "获取仪表列表"),

    //房源租客退租
    FY_ZK_TUIZU("/v2/compact/chengzu/quit_rent", "------------------------"),

    //查看费用明细
    FY_ZK_TUIZU_MX("/v2/balance/table_balance_sheet/get_zuke_tbs_by_chengzu_id", "------------------------"),

    //租客预定
    FY_ZK_YUDING("/v2/house/house_shouding/insert", "------------------------"),

    //租客退定
    FY_ZK_TUIDING("/v2/house/house_shouding/return_reservation", "------------------------"),

    //根据ID获取房源预定信息
    Fy_GET_YDMSG("/v2/house/house_shouding/get", "------------------------"),

    //删除房源
    Fy_DELETE_HOUSE("/v2/house/part_house/delete_children_house_by_id", "------------------------"),


    //添加租客收支
    ADD_RENTER_SZ("/v2/balance/table_balance_sheet/save_chengzu_balance_sheet", "------------------------"),

    //添加业主收支
    ADD_OWNER_SZ("/v2/balance/table_balance_sheet/save_yezhu_balance_sheet", "------------------------"),

    //添加房源收支
    ADD_HOUSE_SZ("/v2/balance/table_balance_sheet/save_house_balance_sheet", "------------------------"),

    //添加运营收支
    ADD_YUNYING_SZ("/v2/balance/table_balance_sheet/save_borrow_balance_sheet", "------------------------"),


    //添加借还收支
    ADD_DEBIT_SZ("/v2/balance/table_balance_sheet/save_debit_balance_sheet", "------------------------"),

    //添加 未知 收支
    ADD_UNKNOW_SZ("/v2/balance/table_balance_sheet/save_unknown_balance_sheet", "------------------------"),


    //获取工人列表
    GET_WORKERS_LIST("/v2/decoration/table_worker/get_list", "获取工人列表"),

    //获取是否有表，及表品牌信息
    GET_METER_INFO("/v2/device/meter/get", "------------------------"),

    //绑定丁盯电表
    BOUND_DINGDING_ELECTRIC("/v2/device/bangElemeter", "------------------------"),

    //绑定丁盯水表
    BOUND_DINGDING_WATER("/v2/device/bangWatermeter", "------------------------"),

    //绑定超义水电表
    BOUND_CHAOYI_WATER_ELECTRIC("/v2/device/chaoyiBang", "------------------------"),

    //绑定非智能表
    BOUND_NON_INTELLIGENT_METER("/v2/device/meter/save", "------------------------"),

    //查看是否绑定公司
    GET_BIND_COMPANY("/v2/lockmanage/lock_util/isgcforpc", "------------------------"),

    //登录丁盯
    LOGIN_DING_DING("/v2/lockmanage/lock_util/getToken", "------------------------"),

    //丁盯同步房源后，才可绑定电表
    DING_DING_TONG_BU("/v2/lockmanage/lock_util/tongbuFangyuan", "------------------------"),

    //丁盯水表抄表记录
    GET_DING_DING_WATER_LIST("/v2/device/readMeter", "------------------------"),

    //丁盯电表抄表记录
    GET_DING_DING_ELECTRIC_LIST("/v2/device/selrecord", "------------------------"),

    //丁盯电表抄表
    DING_DING_ELECTRIC_READ("/v2/device/read", "------------------------"),

    //超义抄表记录
    GET_CHAOYI_LIST("/v2/device/selrecordchaoyi", "------------------------"),

    //非智能抄表记录
    GET_NON_INTELLIGENT_LIST("/v2/device/record/get_list", "非智能抄表记录"),

    //非智能表抄表
    SAVE_NON_INTELLIGENT("/v2/device/record/save", "------------------------"),

    //超义表抄表
    SAVE_CHAOYI_METER("/v2/device/readByMeterNo", "------------------------"),

    //丁盯表抄表
    SAVE_DING_DING_METER("/v2/device/chaobiao", "------------------------"),

    //解绑表
    UNBUNDLE_METER("/v2/device/delbang", "------------------------"),

    //设置丁盯电表单价
    SET_DING_DING_ELECTRIC_PRICE("/v2/device/updateprice", "------------------------"),

    //设置丁盯水表单价
    SET_DING_DING_WATER_PRICE("/v2/device/updatepriceMamter", "------------------------"),

    //设置超义表单价
    SET_CHAO_YI_METER_PRICE("/v2/device/updatepricechaoyi", "------------------------"),

    //设置蜂电表单价
    SET_FENG_DIAN_METER_PRICE("/v2/device/upPrice", "------------------------"),

    //标为脏房
    FLAG_DIRTY_HOUSE("/v2/house/house/apply_dirty", "------------------------"),

    //取消脏房
    CANCEL_DIRTY_HOUSE("/v2/house/house/cancel_dirty", "------------------------"),

    //标为转租
    FLAG_SUBLET_HOUSE("/v2/house/house/apply_trun_rent", "------------------------"),

    //取消转租
    CANCEL_SUBLET_HOUSE("/v2/house/house/cancel_trun_rent", "------------------------"),

    //标为申退
    FLAG_TO_QUIT("/v2/house/house/apply_back", "------------------------"),

    //取消申退
    CANCEL_TO_QUIT("/v2/house/house/cancel_back", "------------------------"),

    // 编辑房源集中
    SUBIT_JIZHONG_SAVE("/v2/house/focus_house/update", "------------------------"),

    //编辑房源整租
    SUBMIT_ZHENGZU_SAVE("/v2/house/full_house/update", "------------------------"),

    //编辑房源合租
    SUBMIT_HEZU_SAVE("/v2/house/part_house/update", "------------------------"),

    //获取业主合同
    GET_CONTRACT_OWNER("/v2/compact/qian_yue/get_by_id", "------------------------"),

    //获取房源配置
    GET_HOUSE_PEIZHI("/v2/storage/house_pei_zhi/get_list", "获取房源配置"),

    //获取集中户型
    GET_JZ_DOOR_MODEL("/v2/item/room_type/get_list", "获取集中户型"),

    //合同基计数
    GET_HETONG_COUNT("/v2/compact/chengzu/app_status_count", "合同基计数"),

    //装修及租后计数
    GET_ZHUANGXIU_ZUHOU_COUNT("/v2/rentservice/complaint_letter/app_status_count", "装修及租后计数"),

    //我的预定
    GET_MINE_ADDMOINT("/v2/house/house/get_boot_list", "------------------------"),

    //我的经营状况
    GET_MINE_JINGYING("/v2/house/house/house_status_count", "我的经营状况"),

    //获取装修列表
    GET_HOUSE_FIT_LIST("/v2/decoration/house_decoration/house_decoations", "------------------------"),

    //房源详情添加装修
    GET_HOUSE_INFO_ZHUANGXIU("/v2/decoration/house_decoration/get_list", "------------------------"),

    //修改装修状态
    CHANGE_FITUP_STATE("/v2/decoration/house_decoration/batch_save", "------------------------"),

    //获取业主合同列表
    GET_HETONG_OWNER_LIST("/v2/compact/qian_yue/get_list", "------------------------"),

    //删除工人
    DEL_GONGREN_LIST("/v2/decoration/table_worker/delete_by_id", "------------------------"),

    //编辑工人
    EDIT_GONGREN_LIST("/v2/decoration/table_worker/save", "------------------------"),

    //获取租客合同列表
    GET_HETONG_TENANT_LIST("/v2/compact/chengzu/get_audit_list", "------------------------"),

    //业主审批
    GET_OWNER_DAI_SP("/v2/compact/qian_yue/get_audit_list", "------------------------"),

    //租客合同审批记录
    TENANT_ACCRADITATION_RECORD("/v2/compact/chengzu/get_audit_operations_record", "------------------------"),

    //业主合同审批记录
    OWNER_ACCRADITATION_RECORD("/v2/compact/qian_yue/get_audit_operations_record", "------------------------"),

    //合同审批权限
    ACCRADITATION_AUTHORITY("/v2/workflow/work_flow_def/get_task_operations", "------------------------"),

    //租客业主通过合同审批
    ZK_YZ_HETONGSHENPI("/v2/workflow/work_flow_def/complete_task", "------------------------"),

    //业主签约提交的网络请求
    GET_QIANYUE_TIJIAO_YEZHU("/v2/compact/qian_yue/insert", "------------------------"),

    GET_HOUSE_YEZHU_QIANYUE("/v2/compact/qian_yue/renew", "------------------------"),

    //获取业主合同列表
    GET_YEZHU_CONTRACT_LIST("/v2/compact/qian_yue/get_list", "------------------------"),

    //获取部门人员
    GET_DEPARTMENT_USER("/v2/sys/table_jjr_user/search_list_by_nick_name", "获取部门人员"),

    //生成费用收支
    CREATE_PAYMENTS_LIST("/v2/compact/qian_yue/anticipated_revenue", "------------------------"),

    //生成电子合同链接
    GET_PACT_QR_CODE("/v2/contract/electronic_contract/generate_econtract_link", "------------------------"),

    //发送电子合同的邮件
    SEND_EMAIL("/v2/contract/electronic_contract/send_contract_email", "------------------------"),


    /*获取城市和地区*/
    GET_PROVINCE_AREA("/v2/location/city/get_list", "------------------------"),

    /*获取所有经纪人*/
    GET_ALL_AGENT("/v2/sys/table_jjr_user/get_simple_user_list", "获取所有经纪人"),

    /*查看本套房源所有信息*/
    GET_ALL_HOUSE_INFO("/v2/lockmanage/lock_util/appSelAllHouseid", "------------------------"),

    /*首页--工单模块数据*/
    WORK_SHEET_MODULE("/v2/web/customerform/work_config/get_ListForApp", "首页--工单模块数据"),

    /*工单模块状态列表*/
    WORK_SHEET_LIST("/v2/web/customerform/work_form/get_page_list", "------------------------"),

    /*工单模块工单详情*/
    WORK_SHEET_DETAIL("/v2/web/customerform/work_form/getWorkFormReturnDTO", "------------------------"),

    /*工单模块取消工单*/
    WORK_SHEET_CANCEL("/v2/web/customerform/work_form_manage/cancel_order", "------------------------"),

    /*工单模块完成工单*/
    WORK_SHEET_COMPLETE("/v2/web/customerform/work_form_manage/finish_orders", "------------------------"),

    /*工单模块改派 指派员工*/
    WORK_SHEET_ASSIGN_EMPLOYEE("/v2/web/customerform/work_form_manage/assign_employee", "------------------------"),

    /*工单模块改派 指派工人*/
    WORK_SHEET_ASSIGN_WORKER("/v2/web/customerform/work_form_manage/send_orders", "------------------------"),

    /*工单模块 申请工单获取配置*/
    WORK_SHEET_APPLY_CONFIG("/v2/web/customerform/work_config/get_by_id", "------------------------"),

    /*工单模块 申请工单*/
    WORK_SHEET_APPLY("/v2/web/customerform/work_form/insert_work_form", "------------------------"),

    /*获取城市及公司信息*/
    GET_CITY_AND_COMPANY("/v2/sys/resources_adjust/get", "获取城市及公司信息"),

    /**
     * 获取绑锁房源列表
     */
    GET_LOCK_HOUSE_LIST("/v2/house/house/get_bind_science_lock_house_list", "获取绑锁房源列表"),

    /**
     * 获取绑锁详情
     */
    GET_LOCK_HOUSE_DATA("/v2/lockmanage/lock_util/selislock", "------------------------"),

    /**
     * 获取多个绑锁密码
     */
    GET_DOOR_PASSWORD_LIST("/v2/lockmanage/lock_util/app_get_keys_list", "------------------------"),

    /*获取我的租客合同*/
    GET_MY_TENANT_PACT_LIST("/v2/compact/chengzu/get_my_list", "------------------------"),

    /*获取我的业主合同*/
    GET_MY_OWNER_PACT_LIST("/v2/compact/qian_yue/get_my_list", "获取我的业主合同"),

    /* 获取最新一条公告*/
    GET_ONE_NOTICE("/v2/company/company_notice/get_new_info", "获取最新一条公告"),

    /**
     * 获取公告列表
     */
    GET_NOTICES("/v2/company/company_notice/get_list", "获取公告列表"),

    /* 删除公告*/
    DELETE_NOTICE("/v2/company/company_notice/delete_by_id", "删除公告"),

    /* 修改公告*/
    UPDATE_NOTICE("/v2/company/company_notice/update", "修改公告"),

    /* 获取公告人员*/
    GET_NOTICES_GROUP("/v2/company/company_notice/get_by_id", "获取公告人员"),

    /**
     * 获取房源图片
     */
    GET_PHOTO("/v2/accessory/pic/get_list", "获取房源图片"),

    /* 删除房源图片*/
    DELETE_PHOTO("/v2/accessory/pic/delete_by_id", "删除房源图片"),

    /**
     * 绑定图片
     */
    BIND_PHOTO("/v2/accessory/pic/save_list", "绑定图片"),

    //通知后台消息已读
    INFOISRED("/v2/interlocution/users_red_spot/save", "通知后台消息已读"),

    /**
     * 昨日业务报表
     */
    YESTERDAY_BUSINESS_REPORT("/v2/reportforms/over_all_situation/last_month_Data", "------------------------"),

    /**
     * 昨日空置
     */
    YESTERDAY_IS_EMPTY("/v2/reportforms/over_all_situation/this_month_vacant", "昨日空置"),

    /**
     * 昨日租客续租、应退
     */
    YESTERDAY_CONTINUE_RENT_SHOULD_BACK("/v2/reportforms/over_all_situation/last_month_rent", "------------------------"),

    //累计空置比率(圆形图)
    OVER_ALL_SITUATION_PIE("/v2/reportforms/over_all_situation/this_vacant", "累计空置比率(圆形图)"),

    //空置统计
    OVER_ALL_SITUATION_COUNT("/v2/reportforms/over_all_situation/house_status", "空置统计"),

    //业务统计(柱状图)
    OVER_YEWU_SITUATION_PIE("/v2/reportforms/over_all_situation/six_chart", "------------------------"),

    //业务
    OVER_YEWU__SITUATION_COUNT("/v2/reportforms/over_all_situation/this_month_type_Data", "------------------------"),

    //获取推送列表
    GET_PUSH_LIST("/v2/message/table_push_message/getAllList", "------------------------"),

    /**
     * 昨日租客缴租逾期
     */
    YESTERDAY_PAY_RENT("/v2/reportforms/over_all_situation/pay_rent", "------------------------"),

    /**
     * 获取承租信息
     */
    GET_CHENGZU_INFO("/v2/compact/chengzu/get_chengzu_info", "get_chengzu_info -- 获取承租信息"),

    /**
     * 获取最新战报
     */
    WAR_MSG_GET_NEWEST("/v2/war/war_msg/get_newest", "获取最新战报"),

    /**
     * 获取战报列表
     */
    WAR_MSG_GET_LIST("/v2/war/war_msg/get_list", "------------------------"),

    /**
     * 战报点赞
     */
    WAR_MSG_MAKE_GOOD("/v2/war/war_msg_good/make_good", "战报点赞"),

    /**
     * 取消点赞
     */
    WAR_MSG_CANCEL_GOOD("/v2/war/war_msg_good/cancel_good", "取消点赞"),

    /**
     * 擂台pk列表
     */
    GET_LEI_TAI_LIE_BIAO("/v2/arena/arena_pk/get_detail_list", "擂台pk列表"),

    /**
     * 投票列表
     */
    GET_TOU_PIAO_XIANG_QING("/v2/arena/arena_pk_vote/get_list_by_pkid", "------------------------"),

    /**
     * 投票
     */
    TOU_PIAO("/v2/arena/arena_pk_vote/vote", "------------------------"),

    /**
     * 获取打卡策略
     */
    GET_CLOCK_IN_STYLE("/v2/oa/oa_configure/getcurrentuserconfigure", "获取打卡策略"),

    /**
     * 打卡
     */
    TO_CLOCK_IN("/v2/oa/oa_work_attendance/insert", "------------------------"),

    /**
     * 获取当天打卡记录
     */
    GET_CLOCK_IN_LIST("/v2/oa/oa_work_attendance/getcurrentdayanduserworked", "------------------------"),

    /**
     * 获取各状态的请假列表
     */
    GET_APPLY_FOR_LIST("/v2/oa/oa_leave_apply/get_currentuserleave", "------------------------"),

    /**
     * 获取请假计算明细列表
     */
    GET_LEAVE_DETAIL_LIST("/v2/oa/oa_leave_apply/getleavedatedetail", "------------------------"),

    /**
     * 请假申请
     */
    ASK_LEAVE_REQUEST("/v2/oa/oa_leave_apply/insert", "------------------------"),

    /**
     * 请假申请修改
     */
    ASK_LEAVE_REQUEST_MODIFY("/v2/oa/oa_leave_apply/update", "------------------------"),

    /**
     * 获取请假审批记录
     */
    GET_LEAVE_APPROVE_RECORD("/v2/approvalflow/core_approvalflow_bizrecord/get_list", "获取请假审批记录"),

    /**
     * 获取请假申请详情
     */
    GET_LEAVE_APPROVE_INFO("/v2/oa/oa_leave_apply/get_userleavedetail", "------------------------"),

    /**
     * 请假撤销
     */
    TO_LEAVE_REVOCATION("/v2/oa/oa_leave_apply/apply_cancel", "------------------------"),

    /**
     * 请假删除
     */
    TO_LEAVE_DELETE("/v2/oa/oa_leave_apply/delete", "------------------------"),

    /**
     * 获取请假审批列表
     */
    GET_ASK_LEAVE_LIST("/v2/oa/oa_leave_apply/get_leave_check", "------------------------"),

    /**
     * 去审批请假申请
     */
    TO_OPERATION_ASK_LEAVE("/v2/approvalflow/core_approvalflow_task/complete_task", "------------------------"),

    /**
     * 获取考勤统计列表
     */
    GET_LEAVE_COUNTING_LIST("/v2/oa/oa_work_attendance/get_currentuser_workedcount", "------------------------"),

    /**
     * 考勤日历
     */
    GET_CALENDAR_LIST("/v2/oa/oa_work_attendance/get_currentuser_workedcalendar", "------------------------"),

    /**
     * 请假申请催办
     */
    URGED_LEAVE_APPROVE("/v2/oa/oa_leave_apply/leave_apply_remind", "------------------------"),

    /**
     * 获取催缴列表
     */
    GET_CUIJIAO_LIST("/v2/balance/table_balance_sheet/get_collection_list", "------------------------"),

    /**
     * 根据收支id获取催缴详情
     */
    GETSHOUZHIINFO("/v2/balance/table_balance_sheet/get_app_by_id", "------------------------"),

    /**
     * 催缴-->开启/关闭提醒
     */
    SETGUANBITIXING("/v2/balance/table_balance_sheet/update_remind", "------------------------"),

    /**
     * 催缴-->短信催收
     */
    SETSMSINFO("/v2/balance/table_balance_sheet/hand_app_sms_notice", "------------------------"),

    /**
     * 验证身份证真伪
     */
    VALIDATE_IDCARD("/v2/id/id_card/validate", "验证身份证真伪"),

    /**
     * 集中整租房源添加
     */
    ADDZHENGZUJIZHONGHOUSE("/v2/house/full_house/insert", "------------------------"),

    /**
     * 合租房源添加
     */
    ADDHEZUHOUSE("/v2/house/part_house/insert", "------------------------"),

    /**
     * 获取合租房间列表
     */
    GETZIJIANHOUSELIST("/v2/house/house/get_list_by_parent_id", "------------------------"),

    /**
     * 合租保存基本信息
     */
    HEZUSAVEINFO("/v2/house/house/save", "------------------------"),

    /**
     * 整租保存基本信息
     */
    ZHENGZUSAVEINFO("/v2/house/full_house/update", "------------------------"),

    /**
     * 添加房源添加子间
     */
    ADDHOUSEADDZIJIAN("/v2/house/part_house/insert_children_house", "------------------------"),

    /***
     * 修改子间信息
     */
    MODIFYHOUSEADDZIJIAN("/v2/house/part_house/update_children_house", "------------------------"),

    /**
     * 删除子间房源
     */
    DELETEHOUSEADDZIJIAN("/v2/house/house/delete_by_id", "------------------------"),

    /**
     * 根据模板id获取配置列表
     */
    GENJUMOBANHUOQUPEIZHILISE("/v2/item/room_type_peizhi/get_list", "------------------------"),

    /**
     * 添加房源合租整租房屋配置保存信息
     */
    ADDHOUSESAVECONFIGDATA("/v2/storage/house_pei_zhi/insert_house_pic", "添加房源合租整租房屋配置保存信息"),

    /**
     * 根据id删除房源配置
     */
    DELETEHOUSECONFIG("/v2/storage/house_pei_zhi/logic_delete", "根据id删除房源配置"),

    /**
     * 根据维修id获取维修详情
     */
    GETIDGEWXINFO("/v2/rentservice/table_web_repair/getInformation", "根据维修id获取维修详情"),

    /**
     * 根据保洁id获取保洁详情
     */
    GETIDGETBJXNFO("/v2/rentservice/table_clean/getInformation", "根据保洁id获取保洁详情"),

    /**
     * 根据投诉id获取投诉详情
     */
    GETIDGETTSXNFO("/v2/web/complain_letter/getInformation", "根据投诉id获取投诉详情"),

    /**
     * 公盘、私盘--跟进提醒（代办）
     */
    OWNER_FOLLOW_UP_REMIND("/v2/owner/owner_floow/remind", "公盘、私盘--跟进提醒（代办）"),

    /**
     * 租后保洁指派
     */
    SETZUHOUBJZHIPAI("/v2/rentservice/table_clean/set_person_in_charge", "租后保洁指派"),

    /**
     * 租后维修指派
     */
    SETZUHOUWXZHIPAI("/v2/rentservice/table_web_repair/set_person_in_charge", "租后维修指派"),

    /**
     * 预览合同
     */
    YULANHETONG("/v2/compact/chengzu/preview_contract", "预览合同"),

    /**
     * 签约优惠活动
     */
    DISCOUNTPAY("/v2/compact/chengzu/getDiscount_kangqiaoExpend", "签约优惠活动"),

    /**
     * 忘记手势密码
     */
    FORGETPASSWORD("/v2/jjr_user_login/app_login_simple", "忘记手势密码"),

    /**
     * 获取电子合同模板列表
     * 棉花专用电子合同模板
     */
    GET_CONTRACT_TEMPLATE_LIST("/v2/template/bind_contract_template/get_template_list", "获取电子合同模板列表"),

    /**
     * 设置电子合同已被预览
     */
    SETELECTROICREAD("/v2/compact/chengzu/is_read_electronic", "设置电子合同已被预览"),

    /**
     * 平安更新弹窗
     */
    PINGANUPDATE("/v2/sys/sys_control/isPAUser", "平安更新弹窗"),

    /**
     * 获取部门列表  --  部门汇总表格(销售漏斗)
     */
    LOU_DOU_GET_BY_DEPT("/v2/meet/loudou_info/get_by_dept", "获取部门列表  --  部门汇总表格(销售漏斗)"),

    /**
     * 获取个人列表  --  部门汇总表格(销售漏斗)
     */
    LOU_DOU_GET_BY_GEREN("/v2/meet/loudou_info/get_by_geren", "获取个人列表  --  部门汇总表格(销售漏斗)"),

    /**
     * 获得所在部门的漏斗
     */
    LOU_DOU_GET_SZ_DEPT("/v2/meet/loudou_info/get_sz_dept", "获得所在部门的漏斗"),

    /**
     * 获得个人的漏斗
     */
    LOU_DOU_GET_BY_USERID("/v2/meet/loudou_info/get_by_userid", "获得个人的漏斗"),


    /**
     * 获取多城市列表
     */
    GETMORECTIYLIST("/v2/location/city/get_ctiy_by_gcid", "获取多城市列表"),

    /**
     * app修改密码
     */
    APP_CHANGE_PASSWORD("/v2/jjr_user_login/app_change_password", "app修改密码"),

    /**
     * 获取渠道来源
     */
    GET_CHANNEL_SOURCE_BY_PHONE("/v2/compact/chengzu/get_channel_source_by_phone", "获取渠道来源"),

    /**
     * 修改房源门锁密码
     */
    MODIFY_HOUSE_LOCK_PASSWORD("/v2/lockmanage/lock_util/app_update_key_", "修改房源门锁密码");


    public String getUrl() {
        return url;
    }

    public String getUrlName() {
        return urlName;
    }

    private String url;
    private String urlName;

    NetUrlEnum(String url, String urlName) {
        this.url = url;
        this.urlName = urlName;
    }
}
