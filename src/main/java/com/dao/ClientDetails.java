package com.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ClientDetails implements Comparable {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private int postCode;


    //Parameterized Constructor
    public ClientDetails(String firstName, String lastName, String dateOfBirth, String gender, int postCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.postCode = postCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public int getPostCode() {
        return postCode;
    }

    //method to read data from txt file and add to ArrayList
    public static ArrayList<ClientDetails> readClientFromFile(String fileName) {
        ArrayList<ClientDetails> clients = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);

        // create an instance of BufferedReader
        try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

            // read first line from txt file
            String line = br.readLine();

            // loop through txt file until all the lines are read
            while (line != null) {

                // use string.split to create array of details that are separated by a Tab
                String[] attributes = line.split("\t");

                ClientDetails client = createClient(attributes);

                // adding Clients into ArrayList
                clients.add(client);

                // read next line before looping
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return clients;
    }

    // Method to create instance of a client
    private static ClientDetails createClient(String[] clientDetail){
        String fn = clientDetail[0];
        String ln = clientDetail[1];
        String dob = clientDetail[2];
        String gen = clientDetail[3];
        int pc = Integer.parseInt(clientDetail[4]);

        return new ClientDetails(fn, ln, dob, gen, pc);
    }


    // Comparator for sorting the list by Client Surname
    public static Comparator<ClientDetails> compareLastName = new Comparator<ClientDetails>() {

        public int compare(ClientDetails s1, ClientDetails s2) {
            String clientName1 = s1.getLastName().toUpperCase();
            String clientName2 = s2.getLastName().toUpperCase();

            //ascending order
            return clientName1.compareTo(clientName2);
        }};


    //Comparator for sorting the list by Client Birth Year
    public static Comparator<ClientDetails> compareDOB = new Comparator<ClientDetails>() {

        public int compare(ClientDetails d1, ClientDetails d2) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

            Date dateOfBirth1 = null;
            try {
                dateOfBirth1 = sdf.parse(d1.getDateOfBirth());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Date dateOfBirth2 = null;
            try {
                dateOfBirth2 = sdf.parse(d2.getDateOfBirth());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //ascending order
            assert dateOfBirth2 != null;
            return dateOfBirth1.compareTo(dateOfBirth2);

        }};

    // Comparator for sorting the list by Client Same birthday
    public static Comparator<ClientDetails> compareBday = new Comparator<ClientDetails>() {

        public int compare(ClientDetails b1, ClientDetails b2) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd");

            Date bDay1 = null;
            try {
                bDay1 = sdf1.parse(b1.getDateOfBirth());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Date bDay2 = null;
            try {
                bDay2 = sdf1.parse(b2.getDateOfBirth());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //ascending order
            assert bDay2 != null;
            return bDay1.compareTo(bDay2);

        }};



    @Override
    public int compareTo(Object o) {
        return 0;
    }
}