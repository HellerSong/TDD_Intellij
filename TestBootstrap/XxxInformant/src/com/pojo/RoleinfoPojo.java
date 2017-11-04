package com.pojo;

public class RoleinfoPojo {
    private int Id;
    private String roleId;
    private String RoleName;
    private String RGroup;
    private String RMemo;
    private int newType;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String RoleName) {
        this.RoleName = RoleName;
    }

    public String getRGroup() {
        return RGroup;
    }

    public void setRGroup(String RGroup) {
        this.RGroup = RGroup;
    }

    public String getRMemo() {
        return RMemo;
    }

    public void setRMemo(String RMemo) {
        this.RMemo = RMemo;
    }

    public void setNewType(int newType) {
        this.newType = newType;
    }

    public int getNewType() {
        return newType;
    }

}
