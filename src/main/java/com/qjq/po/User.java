package com.qjq.po;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String id;

    private String name;

    private String institute;

    private String phone;

    private Integer statusId=0;

    private String password;

    private Integer roleId=2;

    private Integer newsnum=0;

    private String headPhoto="/img/head/default.jpg";

    private String signature="";

    private String registertime;

    private static final long serialVersionUID = 1L;

    public User() {
        this.statusId = 0;
        this.roleId = 2;
        this.newsnum = 0;
        this.signature = "";
    }

    public String getHeadPhoto() {
        return headPhoto;
    }

    public void setHeadPhoto(String headPhoto) {
        this.headPhoto = headPhoto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute == null ? null : institute.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getNewsnum() {
        return newsnum;
    }

    public void setNewsnum(Integer newsnum) {
        this.newsnum = newsnum;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getRegistertime() {
        return registertime;
    }

    public void setRegistertime(String registertime) {
        this.registertime = registertime.length()>=21?registertime.substring(0,registertime.length()-2):registertime;

    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getInstitute() == null ? other.getInstitute() == null : this.getInstitute().equals(other.getInstitute()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getStatusId() == null ? other.getStatusId() == null : this.getStatusId().equals(other.getStatusId()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getNewsnum() == null ? other.getNewsnum() == null : this.getNewsnum().equals(other.getNewsnum()))
            && (this.getSignature() == null ? other.getSignature() == null : this.getSignature().equals(other.getSignature()))
            && (this.getRegistertime() == null ? other.getRegistertime() == null : this.getRegistertime().equals(other.getRegistertime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getInstitute() == null) ? 0 : getInstitute().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getStatusId() == null) ? 0 : getStatusId().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getNewsnum() == null) ? 0 : getNewsnum().hashCode());
        result = prime * result + ((getSignature() == null) ? 0 : getSignature().hashCode());
        result = prime * result + ((getRegistertime() == null) ? 0 : getRegistertime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", institute=").append(institute);
        sb.append(", phone=").append(phone);
        sb.append(", statusId=").append(statusId);
        sb.append(", password=").append(password);
        sb.append(", roleId=").append(roleId);
        sb.append(", newsnum=").append(newsnum);
        sb.append(", signature=").append(signature);
        sb.append(", registertime=").append(registertime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}