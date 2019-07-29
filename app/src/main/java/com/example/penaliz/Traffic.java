package com.example.penaliz;

public class Traffic {
    public String regno,date,amt;
    public Traffic(){

    }
    public Traffic(String regno,String date,String amt){
        this.regno=regno;
        this.date=date;
        this.amt=amt;

    }

    public String getAmt() {
        return amt;
    }

    public String getDate() {
        return date;
    }

    public String getRegno() {
        return regno;
    }
}
