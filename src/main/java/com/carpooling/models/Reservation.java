package com.carpooling.models;

public class Reservation {
    private int reservationID;
    private Users user;
    private Trip trip;

    public Reservation(int reservationID, Users user, Trip trip) {
        this.reservationID = reservationID;
        this.user = user;
        this.trip = trip;
    }

    public int getReservationID(){
        return this.reservationID;
    }
    public Users getUser() {
        return this.user;
    }
    public Trip getTrip(){
        return this.trip;
    }
}
