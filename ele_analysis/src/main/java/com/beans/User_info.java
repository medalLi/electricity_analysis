package com.beans;

/**
 * @author medal
 * @create 2019-11-04 21:45
 **/
public class User_info {

    private long user_id;
    private String userName;
    private String name;
    private int age;
    private String professional;
    private String city;
    private String sex;

    public User_info(long user_id, String userName, String name, int age, String professional, String city, String sex) {
        this.user_id = user_id;
        this.userName = userName;
        this.name = name;
        this.age = age;
        this.professional = professional;
        this.city = city;
        this.sex = sex;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User_info{" +
                "user_id=" + user_id +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", professional='" + professional + '\'' +
                ", city='" + city + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
