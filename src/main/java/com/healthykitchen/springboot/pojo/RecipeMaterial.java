package com.healthykitchen.springboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 20:26 2019/11/19
 * @version: v1.0
 */

@Entity
@Table(name = "Recipe_has_Material")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class RecipeMaterial implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "recipe_id")
    private int rId;

    @Id
    @Column(name = "material_id")
    private int mId;

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
}
