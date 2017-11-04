package com.pojo;

import java.sql.Timestamp;

public class VOrgmemberinfoPojo {
    private int ID;
    private String GUID;
    private String DisplayName;
    private String OrganizeType;
    private String LoginID;
    private String Passwords;
    private String PostName;
    private String DeptName;
    private String OrgName;
    private String ShortName;
    private String IdentityCardID;
    private String JCYDM;
    private int roleId;
    private int status;
    private Timestamp crTime;
    private String RoleName;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getGUID() {
        return GUID;
    }

    public void setGUID(String GUID) {
        this.GUID = GUID;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String DisplayName) {
        this.DisplayName = DisplayName;
    }

    public String getOrganizeType() {
        return OrganizeType;
    }

    public void setOrganizeType(String OrganizeType) {
        this.OrganizeType = OrganizeType;
    }

    public String getLoginID() {
        return LoginID;
    }

    public void setLoginID(String LoginID) {
        this.LoginID = LoginID;
    }

    public String getPasswords() {
        return Passwords;
    }

    public void setPasswords(String Passwords) {
        this.Passwords = Passwords;
    }

    public String getPostName() {
        return PostName;
    }

    public void setPostName(String PostName) {
        this.PostName = PostName;
    }

    public String getDeptName() {
        return DeptName;
    }

    public void setDeptName(String DeptName) {
        this.DeptName = DeptName;
    }

    public String getOrgName() {
        return OrgName;
    }

    public void setOrgName(String OrgName) {
        this.OrgName = OrgName;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String ShortName) {
        this.ShortName = ShortName;
    }

    public String getIdentityCardID() {
        return IdentityCardID;
    }

    public void setIdentityCardID(String IdentityCardID) {
        this.IdentityCardID = IdentityCardID;
    }

    public String getJCYDM() {
        return JCYDM;
    }

    public void setJCYDM(String JCYDM) {
        this.JCYDM = JCYDM;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setCrTime(Timestamp crTime) {
        this.crTime = crTime;
    }

    public Timestamp getCrTime() {
        return crTime;
    }

    public void setRoleName(String roleName) {
        RoleName = roleName;
    }

    public String getRoleName() {
        return RoleName;
    }

}
