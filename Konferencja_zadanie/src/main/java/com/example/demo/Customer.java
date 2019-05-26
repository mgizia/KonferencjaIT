package com.example.demo;

import java.io.*;


public class Customer {
    private int id;
    private String name, email;

    public Customer(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getEmail(){
        return this.email;
    }

    public void message(){
        try{
            Writer output;
            output = new BufferedWriter(new FileWriter("powiadomienia.txt", true));
            output.append( name + "," + email + ";\r\n" );
            output.close();
        } catch (Exception e){
            System.out.println("Błąd wejscia - wyjscia");
        }

    }}
