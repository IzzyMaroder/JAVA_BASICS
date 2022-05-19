package com.carpooling.models;

public class Trip {
    private int tripID;
    private String date;
    private int duration;
    private String departureCity;
    private String arrivalCity;
    private boolean available; 

    public Trip(int tripID, String date, int duration, String departureCity, String arrivalCity, String available) {
        this.tripID = tripID;
        this.date = date;
        this.duration = duration;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        if(available.compareTo("SI") == 0){
            this.available = true;
        }else {
            this.available = false;
        }
    }

    public int getTripID() {
        return this.tripID;
    }
    public String getTripDate() {
        return this.date;
    }
    public boolean getAvailable() {
        return this.available;
    } 
    public int getDuration() {
        return this.duration;
    }
    public String getDepartureCity(){
        return this.departureCity;
    }

    public void setAvailable(boolean b) {
        this.available = b;
    }
    public String isAvailable() {
        if(available) {
            return "y";
        }else {
            return "n";
        }
    }

    public String toString() {
        
        return tripID + " | " +
                date + " | " +
                duration + " | " +
                departureCity + " | " +
                arrivalCity + " | " +
                isAvailable()+ "\n";
    }

    public String toStringComplete() {
        
        return tripID + ";"+
                date + ";" + 
                duration + ";" + 
                departureCity + ";" + 
                arrivalCity;
    }

}
