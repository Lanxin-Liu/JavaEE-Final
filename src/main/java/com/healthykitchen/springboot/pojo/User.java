package com.healthykitchen.springboot.pojo;



/**
 * @className: User
 * @description:
 * @author: anonym_co
 * @date: 13:14 2019/11/13
 * @version: v1.0
 */

public class User {

    private int id;

    private String username;

    private String password;

    private byte[] image;

    private String intro;

    private String gender;

    private int followed_num;

    private int following_num;

    public User(){}


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.gender = "";
        this.intro = "";
        // 默认图像路径
//        this.image = default;
    }

//    public User(int id, String username, String password, byte[] image, String intro, String gender, int followed_num, int following_num)
//    {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.image = image;
//        this.intro = intro;
//        this.gender = gender;
//        this.followed_num = followed_num;
//        this.following_num = following_num;
//    }

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
