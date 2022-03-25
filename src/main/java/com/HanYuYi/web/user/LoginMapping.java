package com.HanYuYi.web.user;

/**
 * 跟数据库字段映射
 */
public class LoginMapping {
    private String name;
    private String gender;
    private int score;

    public LoginMapping(String name, String gender, int score) {
        this.name = name;
        this.gender = gender;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "LoginMapping{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", score=" + score +
                '}';
    }
}
