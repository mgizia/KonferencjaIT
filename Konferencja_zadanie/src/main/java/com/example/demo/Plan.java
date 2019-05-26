package com.example.demo;

import javax.persistence.Entity;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String user;

    private String lecture;

    private boolean deleted;

    public Plan(){
    }

    public Plan(String user){
        this.user = user;
    }

    public Plan(String user, String lecture){
        this.lecture = lecture;
        this.user = user;
    }

    public Plan(String user, String lecture, boolean deleted){
        this.user = user;
        this.lecture=lecture;
        this.deleted = deleted;
    }

    public String getUser(){
        return  user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getLecture(){return lecture;}

    public void setLecture(String lecture){
        this.lecture = lecture;
    }
    public boolean isDeleted(){
      return deleted;
    }

   public void setDeleted(boolean done){
      this.deleted = deleted;
    }
}