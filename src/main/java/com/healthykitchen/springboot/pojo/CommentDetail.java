package com.healthykitchen.springboot.pojo;

/**
 * @className:
 * @description:
 * @author: Liu Lanxin
 * @date: 04:44 2019/12/14
 * @version: v1.0
 */
public class CommentDetail {
    private String content;
    private String time;
    private String userName;
    private String pic;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
