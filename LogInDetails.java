package com.example.knight.b;

/**
 * Created by Knight on 19/10/2017.
 */

public class LogInDetails {

    String name = "";
    String number = "";

    public LogInDetails(){

    }

    public LogInDetails(String name, String number){
      this.name = name;
      this.number = number;
    }

    public String getName(){
        return  this.name;
    }
    public String getNumber(){
        return this.number;
    }
}
