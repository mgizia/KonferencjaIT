package com.example.demo;

public class Lecture{

    private int id;
    private int path1;
    private int path2;
    private int path3;

    public Lecture(int id, int path1, int path2, int path3){

        this.id = id;
        this.path1 = path1;
        this.path2 = path2;
        this.path3 = path3;
    }

    public String getPath1(){
        if(path1 == 111)
            return "1.06.2019r., 10:00-11:45";
        else if(path1 == 112)
            return "1.06.2019r., 12:00-13:45";
        else if(path1 == 121)
            return "2.06.2019r., 10:00-11:45";
        else if(path1 == 122)
            return "2.06.2019r., 12:00-13:45";
        else
            return "Nie istnieje taka ścieżka";
    }

    public String getPath2(){
        if(path2 == 211)
            return "1.06.2019r., 10:00-11:45";
        else if(path2 == 212)
            return "1.06.2019r., 12:00-13:45";
        else if(path2 == 221)
            return "2.06.2019r., 10:00-11:45";
        else if(path2 == 222)
            return "2.06.2019r., 12:00-13:45";
        else
            return "Nie istnieje taka ścieżka";
    }

    public String getPath3(){
        if(path3 == 311)
            return "1.06.2019r., 10:00-11:45";
        else if(path3 == 312)
            return "1.06.2019r., 12:00-13:45";
        else if(path3 == 321)
            return "2.06.2019r., 10:00-11:45";
        else if(path3 == 322)
            return "2.06.2019r., 12:00-13:45";
        else
            return "Nie istnieje taka ścieżka";
    }

}
