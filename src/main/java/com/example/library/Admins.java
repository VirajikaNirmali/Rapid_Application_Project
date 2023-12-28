package com.example.library;

public class Admins {

    int AdminID;
    String AdminName;
    String AdminEmail;
    String AdminUserRole;

    public Admins(int id, String name, String email, String adminUserRole){
        this.AdminID = id;
        this.AdminName = name;
        this.AdminEmail = email;
        this.AdminUserRole = adminUserRole;
    }

    public int getAdminID() {
        return AdminID;
    }

    public void setAdminID(int adminID) {
        AdminID = adminID;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }

    public String getAdminEmail() {
        return AdminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        AdminEmail = adminEmail;
    }

    public String getAdminUserRole() {
        return AdminUserRole;
    }

    public void setAdminUserRole(String adminUserRole){
        AdminUserRole = adminUserRole;
    }

}
