package com.carpooling.functions;

import java.io.*;

import java.text.SimpleDateFormat;
import java.util.*;

import java.util.stream.Collectors;

import com.carpooling.models.Reservation;
import com.carpooling.models.Users;

public class App {
    private static Manager fileData;

    public App() {
        fileData = new Manager();
        fileData.importFile();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int input = 0;

        do {
            System.out.println("1): Show all trips");
            System.out.println("2): Book a trip");
            System.out.println("3): Delete a reservation");
            System.out.println("4): Add a new user");
            System.out.println("5): Export a file with available trips");
            System.out.println("0): Quit");

            input = Integer.parseInt(sc.nextLine());

            if((input)<0 || (input)>5) {
                System.out.println("Enter a valid number!");
                continue;
            }
            switch(input) {
                case 1:
                    printAllTrips();
                    break;
                case 2:
                    bookAtrip(sc);
                    break;
                case 3:
                    deleteReservation(sc);
                    break;
                case 4:
                    addNewUsr(sc);
                    break;
                case 5:
                    writeCSV();
                    break;
                default:
                    break;

            }

        }while(input != 0);

        sc.close();
    }

    private static void printAllTrips() {
        System.out.println("ID | Date | Duration | Departure | Arrival | Available");

        fileData.getT().stream().forEach(tr-> System.out.println(tr.toString()));
    }

    private void bookAtrip(Scanner sc) {
        System.out.println("Enter trip ID");
        int tId = Integer.parseInt(sc.nextLine());
        System.out.println("Enter your ID");
        int uId = Integer.parseInt(sc.nextLine());
        if(fileData.getTripsMap().containsKey(tId) && fileData.getTripsMap().get(tId).getAvailable()
            && fileData.containsUs(uId)) {
                   Reservation res = new Reservation(fileData.getR().size()+1, fileData.getUsersMap().get(uId), fileData.getTripsMap().get(tId)) ;
                   fileData.addR(res);
                   fileData.getTripsMap().get(tId).setAvailable(false);

                   System.out.println("New reservation added!");
        }else {
            System.out.println("Cannot book!!");
            
        }
    }

    private void deleteReservation(Scanner sc) {
        System.out.println("Enter the reservation ID");
        int rId = Integer.parseInt(sc.nextLine());

        if(fileData.getReservationsMap().containsKey(rId)) {
            fileData.removeR(rId);
            System.out.println("Reservation with ID:"+ rId +" removed");
        }else {
            System.out.println("Id not found");
        }
    }

    private void addNewUsr(Scanner sc) {
       String[] newUsr = new String[6];
       boolean ok = false;

       do {
           System.out.println("Enter the user Id");
           newUsr[0] = sc.nextLine();

           if(fileData.containsUs(Integer.parseInt(newUsr[0]))){
               System.out.println("This user ID alredy exists");
               ok = false;
           }else {
               ok = true;
           }
       }while(ok == false);

       System.out.println("Enter your name");
       newUsr[1] = sc.nextLine();
       System.out.println("Enter your last name");
       newUsr[2] = sc.nextLine();
       System.out.println("Enter your birth date");
       newUsr[3] = sc.nextLine();
       System.out.println("Enter your address");
       newUsr[4] = sc.nextLine();
       System.out.println("Enter your document Id");
       newUsr[5] = sc.nextLine();

       fileData.addU(new Users(Integer.parseInt(newUsr[0]), newUsr[1], newUsr[2], newUsr[3], newUsr[4], newUsr[5]));
       System.out.println("New user added");
    }

    public void writeCSV() {

        List<String> tripsFiltered = fileData.getT().stream().filter(t -> t.getAvailable())
                .map(t -> t.toStringComplete()).collect(Collectors.toList());

        FileWriter csvW;
        try {

            String fileName = new SimpleDateFormat("'trips_'dd_MM_yyyy'.csv'").format(new Date());
            csvW = new FileWriter(fileName);

            csvW.append("ID");
            csvW.append(";");
            csvW.append("Date");
            csvW.append(";");
            csvW.append("Duration");
            csvW.append(";");
            csvW.append("Departure city");
            csvW.append(";");
            csvW.append("Arrival city");
            csvW.append("\n");
            

            for (String row : tripsFiltered) {

                csvW.append(row);
                csvW.append("\n");
            }

            csvW.flush();
            csvW.close();

            System.out.println(" New file: " + fileName + " created ");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
