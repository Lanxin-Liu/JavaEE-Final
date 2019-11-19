package com.healthykitchen.springboot.pojo;

import org.hibernate.engine.jdbc.BinaryStream;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * @className: User
 * @description:
 * @author: anonym_co
 * @date: 13:14 2019/11/13
 * @version: v1.0
 */

@Entity
@Table(name = "user_info")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "intro")
    private String intro;

    @Column(name = "gender")
    private String gender;

    @Column(name = "followed_num")
    private int followed_num;

    @Column(name = "following_num")
    private int following_num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFollowed_num() {
        return followed_num;
    }

    public void setFollowed_num(int followed_num) {
        this.followed_num = followed_num;
    }

    public int getFollowing_num() {
        return following_num;
    }

    public void setFollowing_num(int following_num) {
        this.following_num = following_num;
    }
}
