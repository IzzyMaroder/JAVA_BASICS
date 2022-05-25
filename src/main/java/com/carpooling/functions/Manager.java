package com.carpooling.functions;


import java.io.*;

import java.util.*;

import com.carpooling.models.*;


public class Manager {
    private TreeMap<Integer, Users> users;
    private TreeMap<Integer, Reservation> reservations;
    private TreeMap<Integer, Trip> trips;

    public Manager() {
        users = new TreeMap<>();
        trips = new TreeMap<>();
        reservations = new TreeMap<>();
    }

    public void importFile() {
        //UTENTI.CSV
       try {
            
            
            BufferedReader reader = new BufferedReader( new FileReader("utenti.csv"));
            
            String line = reader.readLine();
            while ((line = reader.readLine() ) != null) {
                //System.out.println(line);
                String[] elem = line.split(";");

                Users user = new Users(Integer.parseInt(elem[0]), elem[1], elem[2], elem[3],elem[4], elem[5]);
                users.put(Integer.parseInt(elem[0]),user);
            }

            reader.close();


        } catch (IOException e) {
            
            System.out.println(e.getMessage() );

        }

       
        
        //VIAGGI.CSV
        try {

            String line;
            BufferedReader reader = new BufferedReader( new FileReader("viaggi.csv"));
            
            while ((line = reader.readLine()) != null) {
                
                String[] elem = line.split(";");

               Trip trip = new Trip(Integer.parseInt(elem[0]), elem[1], Integer.parseInt(elem[2]), elem[3], elem[4], elem[5]);
               trips.put(Integer.parseInt(elem[0]), trip);
            }

            reader.close();


        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        
         //PRENOTAZIONE.CSV
        try {

            String line;
            BufferedReader reader = new BufferedReader( new FileReader("prenotazioni.csv"));

            
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                String[] elem = line.split(";");
                Reservation reservation = new Reservation(Integer.parseInt(elem[0]), users.get(Integer.parseInt(elem[1])), trips.get(Integer.parseInt(elem[2])));
                reservations.put(Integer.parseInt(elem[0]), reservation);
            }

            reader.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }




    }

    public Collection<Users> getU() {
        return this.users.values();
    }

    public  Collection<Trip> getT() {
        return this.trips.values();
    }

    public Collection<Reservation> getR(){
        return this.reservations.values();
    }

    public boolean containsUs(Integer id) {
        boolean ok = true;
        if(users.containsKey(id)) {
            ok = false;
        }
        return ok;
        
    }

    public void addU(Users id){
        users.put(id.getUser(), id);
    }

    

    public TreeMap<Integer, Users> getUsersMap() {
        return this.users;
    }

    public TreeMap<Integer, Trip> getTripsMap() {
        return this.trips;
    }

    public TreeMap<Integer, Reservation> getReservationsMap() {
        return this.reservations;
    }

    public void addR(Reservation r) {
        reservations.put(reservations.size()+1, r);
    }

    public void removeR(int idR){
        reservations.remove(idR);
    }
}
