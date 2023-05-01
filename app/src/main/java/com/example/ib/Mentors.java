package com.example.ib;

public class Mentors {
    String user,mail,img, mentSubj;
    Integer std;

    public Mentors() {
    }
    public Mentors(String user, String mail, Integer std, String img, String mentSubj) {
        this.user = user;
        this.mail = mail;
        this.std = std;
        this.img = img;
        this.mentSubj = mentSubj;
    }

    //getter and setter
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getStd() {
        return std;
    }

    public void setStd(Integer std) {
        this.std = std;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMentSubj() {
        return mentSubj;
    }

    public void setMentSubj(String mentSubj) {
        this.mentSubj = mentSubj;
    }

}
