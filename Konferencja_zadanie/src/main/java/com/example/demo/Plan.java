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

    public Plan(){
    }

    public Plan(String user){
        this.user = user;
    }

    public Plan(String user, String lecture){
        this.lecture = lecture;
        this.user = user;
    }

    public String getUser(){
        return  user;
    }

    public void setUser(String user){
        this.user = user;
    }

    public String getLecture(){return user;}

    public void setLecture(String lecture){
        this.lecture = lecture;
    }
   // public boolean isDone(){
     //   return done;
    //}

  /*  public void setDone(boolean done){
        this.done = done;
    }*/
}