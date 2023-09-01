package com.example.projectmanagementapp;

import com.google.gson.annotations.SerializedName;

public class Task {

    @SerializedName("tid") private int Tid;
    @SerializedName("title") private String Title;
    @SerializedName("description") private String Description;
    @SerializedName("member_email") private String MemberEmail;
    @SerializedName("status") private String Status;
    @SerializedName("priority") private String Priority;
    @SerializedName("deadline") private String Deadline;

    public int getTid() {
        return Tid;
    }
    public String getTitle() {
        return Title;
    }
    public String getDescription() {
        return Description;
    }
    public String getMemberEmail() {
        return MemberEmail;
    }
    public String getStatus() {
        return Status;
    }
    public String getPriority() {
        return Priority;
    }
    public String getDeadline() {
        return Deadline;
    }
}
