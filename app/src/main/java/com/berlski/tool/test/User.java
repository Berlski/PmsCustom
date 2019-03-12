package com.berlski.tool.test;


import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/15.
 */
public class User implements Serializable {

    //----------------------------------------------------------------------------------------------------------------------------
    private String companyCityName;                     //公司所在城市名称
    private String companyName;                         //公司名称

    private String companyCityId;                         //公司所在城市id

    private String pic;                                 //true string 用户头像

    private String lastDepartment;                      //true string 培训部门

    private String id;                                  //true string 业务员ID

    private String lat;                                 //true string 纬度

    private String nickName;                            //true string 真实姓名

    private Department dptm;                            //true object 部门对象

    private Department department;                            //true object 部门对象

    private String phone;                               //true string 联系电话

    private String gender;                              //true number 用户性别-0:女 1:男

    private String gcid;                                //true string 公司ID

    private String lon;                                 //true string 经度

    private String token;                               //true string 登录成功后返回令牌


    public String getCompanyCityName() {
        return companyCityName;
    }

    public void setCompanyCityName(String companyCityName) {
        this.companyCityName = companyCityName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCityId() {
        return companyCityId;
    }

    public void setCompanyCityId(String companyCityId) {
        this.companyCityId = companyCityId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLastDepartment() {
        return lastDepartment;
    }

    public void setLastDepartment(String lastDepartment) {
        this.lastDepartment = lastDepartment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Department getDptm() {
        return dptm;
    }

    public void setDptm(Department dptm) {
        this.dptm = dptm;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGcid() {
        return gcid;
    }

    public void setGcid(String gcid) {
        this.gcid = gcid;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
