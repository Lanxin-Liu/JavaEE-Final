package com.healthykitchen.springboot.pojo;

import org.springframework.stereotype.Component;

@Component
public class DailyPlan {

    private int DPId;

    private String DPContent;

    private String DPTime;

    private String DPTag;

    private String DPDate;

    private int DPCalorie;

    private byte[] DPImage;

    private int DPUserId;

    public DailyPlan(){

    }

    public int getDPId() {
        return DPId;
    }

    public void setDPId(int DPId) {
        this.DPId = DPId;
    }

    public String getDPContent() {
        return DPContent;
    }

    public void setDPContent(String DPContent) {
        this.DPContent = DPContent;
    }

    public String getDPTime() {
        return DPTime;
    }

    public void setDPTime(String DPTime) {
        this.DPTime = DPTime;
    }

    public String getDPTag() {
        return DPTag;
    }

    public void setDPTag(String DPTag) {
        this.DPTag = DPTag;
    }

    public String getDPDate() {
        return DPDate;
    }

    public void setDPDate(String DPDate) {
        this.DPDate = DPDate;
    }

    public int getDPCalorie() {
        return DPCalorie;
    }

    public void setDPCalorie(int DPCalorie) {
        this.DPCalorie = DPCalorie;
    }

    public byte[] getDPImage() {
        return DPImage;
    }

    public void setDPImage(byte[] DPImage) {
        this.DPImage = DPImage;
    }

    public int getDPUserId() {
        return DPUserId;
    }

    public void setDPUserId(int DPUserId) {
        this.DPUserId = DPUserId;
    }



}
