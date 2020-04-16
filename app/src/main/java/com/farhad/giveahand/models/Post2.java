package com.farhad.giveahand.models;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post2 {
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("area")
    @Expose
    private String area;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("other_contact")
    @Expose
    private String otherContact;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("type")
    @Expose
    private String type;

    public Post2(String area, String phone, String message, String address) {
        this.title = "Need Help";
        this.area = area;
        this.phone = phone;
        this.message = message;
        this.address = address;
        this.type = "seeking_help";
    }

    public Post2(String area, String phone, String otherContact, String message, String address) {
        this.title = "Need Help";
        this.area = area;
        this.phone = phone;
        this.otherContact = otherContact;
        this.message = message;
        this.address = address;
        this.type = "seeking_help";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOtherContact() {
        return otherContact;
    }

    public void setOtherContact(String otherContact) {
        this.otherContact = otherContact;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}