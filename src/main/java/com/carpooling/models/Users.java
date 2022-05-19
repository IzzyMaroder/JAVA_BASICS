package com.carpooling.models;

public class Users {
    private int userID;
    private String name;
    private String lastname;
    private String date;
    private String address;
    private String documentID;

    public Users(int userID, String name, String lastname, String date, String address, String documentID){
        this.userID = userID;
        this.name = name;
        this.lastname =lastname;
        this.date = date;
        this.address = address;
        this.documentID = documentID;
    }
    public int getUser() {
        return this.userID;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.lastname;
    }

    public String getDate() {
        return this.date;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDocumentID() {
        return this.documentID;
    }

    public String toString() {
        return  userID + " " +
                name + " " +
                lastname + " " +
                date + " " +
                address + " " +
                documentID;
    }
}
