package com.example.library;

public class Members {
    int MemberID;
    String MemberName;
    String MemberEmail;
    String MemberUserRole;

    public Members(int id, String name, String email, String userRple){
        this.MemberID = id;
        this.MemberName = name;
        this.MemberEmail = email;
        this.MemberUserRole = userRple;
    }

    public int getMemberID() {
        return MemberID;
    }

    public void setMemberID(int memberID) {
        MemberID = memberID;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setAdminName(String memberName) {
        MemberName = memberName;
    }

    public String getMemberEmail() {
        return MemberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        MemberEmail = memberEmail;
    }

    public  String getMemberUserRole(){ return MemberUserRole; }

    public void setMemberUserRole(String memberUserRole){ MemberUserRole = memberUserRole; }
}
