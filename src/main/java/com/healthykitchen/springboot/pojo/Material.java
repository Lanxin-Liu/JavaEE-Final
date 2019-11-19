package com.healthykitchen.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 20:23 2019/11/19
 * @version: v1.0
 */
@Entity
@Table(name = "Material")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private int mId;

    @Column(name = "calorie")
    private int calorie;

    @Column(name = "material_name")
    private String mName;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
