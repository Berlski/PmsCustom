package com.berlski.tool.test;

import com.berlski.tool.custom.util.StringUtil;
import com.berlski.tool.custom.util.UserUtil;

public class UserService implements UserUtil.UserDateInter<User> {


    @Override
    public String getCompanyId(User user) {
        return user.getGcid();
    }

    @Override
    public String getPhoneNo(User user) {
        return user.getPhone();
    }

    @Override
    public String getUserName(User user) {
        return user.getNickName();
    }

    @Override
    public String getUserId(User user) {
        return user.getId();
    }

    @Override
    public String getUserToken(User user) {
        return user.getToken();
    }

    @Override
    public String getCompanyCityId(User user) {
        return user.getCompanyCityId();
    }

    @Override
    public String getCompanyCityName(User user) {
        return user.getCompanyCityName();
    }

    @Override
    public String getCompanyName(User user) {
        return user.getCompanyName();
    }

    @Override
    public String getUserHead(User user) {
        return user.getPic();
    }

    @Override
    public String getLat(User user) {
        return user.getLat();
    }

    @Override
    public String getLon(User user) {
        return user.getLon();
    }

    @Override
    public String getLastDepartment(User user) {
        return user.getLastDepartment();
    }

    @Override
    public String getGender(User user) {
        return user.getGender();
    }

    @Override
    public String getDptmId(User user) {

        if (user.getDptm() == null || StringUtil.isEmpty(user.getDptm().getId())) {
            return "";
        }

        return user.getDptm().getId();
    }

    @Override
    public String getDptmName(User user) {

        if (user.getDptm() == null || StringUtil.isEmpty(user.getDptm().getName())) {
            return "";
        }

        return user.getDptm().getName();
    }
}
