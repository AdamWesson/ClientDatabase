package com.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.ClientDetails;

public class IteratorExample extends HttpServlet {
    private String filename = "c:\\data\\Client_List.txt";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type"); // get instruction type from the frontend based on which filter link is clicked.

        // Read data from Client_List.txt using readClientFromFile method in ClientDetails
        // Data parsed to view to display full unsorted client list
        if (type.equals("getDetails")) {
            ClientDetails.readClientFromFile(filename);
            request.setAttribute("clients", ClientDetails.readClientFromFile(filename));
            request.getRequestDispatcher("iterator.jsp").forward(request, response);

        // Data sorted by Surname and passed to view
        } else if (type.equals("sortSurname")) {
            ClientDetails.readClientFromFile(filename);
            ArrayList<ClientDetails> sur = ClientDetails.readClientFromFile(filename);

            // Loop through ClientDetails array and select unique surnames to build up Select List in view
            if (request.getParameter("surname") != null){
                ArrayList<ClientDetails> result = new ArrayList<ClientDetails>();  // Array to store unique surnames
                for (ClientDetails person : sur) {
                    if (person.getLastName().equals(request.getParameter("surname"))) {
                        result.add(person); // add unique surname to array
                    }
                }
                request.setAttribute("SurnameList", result); // send array filtered by unique surname to view
                Collections.sort(sur, ClientDetails.compareLastName); // sort array alphabetically
                request.setAttribute("SurnameList2", sur); // send full surname list to view to re-populate the select list after a search
                request.getRequestDispatcher("iterator.jsp").forward(request, response);
            }
                // sort surnames alphabetically and send to view.
                // On first page load before filtering on a surname
                Collections.sort(sur, ClientDetails.compareLastName);
                request.setAttribute("SurnameList", sur);
                request.getRequestDispatcher("iterator.jsp").forward(request, response);

        }
        // Sort ClientList by the year of birth
        else if(type.equals("sortYear"))
        {
            ClientDetails.readClientFromFile(filename); // read txt file
            ArrayList<ClientDetails> y = ClientDetails.readClientFromFile(filename); // array to hold clients

            // if you filter by a year
            if (request.getParameter("bYear") != null  ){
                ArrayList<ClientDetails> result1 = new ArrayList<ClientDetails>();  // array to hold year
                for (ClientDetails birYear : y) { // loop through ClientDetails and add like years to array
                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy"); // standardize date format
                    String yx = birYear.getDateOfBirth();
                    String[] arrOfStr = yx.split("/", 3);  // split date into 3 strings on /

                    String y1 = arrOfStr[2];
                    String y2 = request.getParameter("bYear"); // get year from view form

                    int xyz = y1.compareTo(y2); // compare client birth year to the filter year and add to array if the same
                    if(xyz == 0){
                        result1.add(birYear);
                    }
                }
                Collections.sort(result1, ClientDetails.compareDOB);
                request.setAttribute("YearList", result1); // send filtered array to view for display
                request.getRequestDispatcher("iterator.jsp").forward(request, response);
            }
            // send sorted array on birth year to view for display without any filtering
            Collections.sort(y, ClientDetails.compareDOB);
            request.setAttribute("YearList", y);
            request.getRequestDispatcher("iterator.jsp").forward(request, response);
        }
        // Sort ClientDetails on same birthday
        else if(type.equals("sortBday"))
        {
            ClientDetails.readClientFromFile(filename);
            ArrayList<ClientDetails> b = ClientDetails.readClientFromFile(filename);

            // check if form values are not null
            if (request.getParameter("dobDay") != null || request.getParameter("dobMonth") != null ){
                ArrayList<ClientDetails> result = new ArrayList<ClientDetails>();
                for (ClientDetails dateOB : b) { // loop through list
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd"); // set date format
                    try {
                        Date birthDay = sdf.parse(dateOB.getDateOfBirth()); // cast to date
                        Date parsedDate = sdf.parse(request.getParameter("dobMonth") + "/" + request.getParameter("dobDay"));
                        if (birthDay.equals(parsedDate)) { // compare dates and add to array if alike
                            result.add(dateOB);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                Collections.sort(result, ClientDetails.compareLastName); // sort on Surname
                request.setAttribute("BdayList", result); // send filtered list to view for display
                request.getRequestDispatcher("iterator.jsp").forward(request, response);

            } else if
            // Filter based on a year range
            (request.getParameter("startAge") != null || request.getParameter("endAge") != null ){
                ArrayList<ClientDetails> result = new ArrayList<ClientDetails>();
                for (ClientDetails dateOB : b) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

                    Date dateOfBirth = null;
                    try {
                        dateOfBirth = sdf.parse(dateOB.getDateOfBirth());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    int age = 0;
                    Calendar born = Calendar.getInstance();
                    Calendar now = Calendar.getInstance();
                    if(dateOfBirth!= null) {
                        now.setTime(new Date());
                        born.setTime(dateOfBirth); // set calendar instance to birth date
                        if(born.after(now)) {
                            throw new IllegalArgumentException("Can't be born in the future");
                        }
                        age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR); // get client age in years by comparing current date to birth date
                        if(now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR))  {
                            age-=1;
                        }
                    }
                    // check if age of client lies between the start and end date range
                    if(age >= Integer.parseInt(request.getParameter("startAge")) && age <= Integer.parseInt(request.getParameter("endAge"))){
                        result.add(dateOB);
                    }
                }
                Collections.sort(result, ClientDetails.compareBday); // sort on birthday
                request.setAttribute("BdayList", result);
                request.getRequestDispatcher("iterator.jsp").forward(request, response);
            }

            Collections.sort(b, ClientDetails.compareBday);
            request.setAttribute("BdayList", b);
            request.getRequestDispatcher("iterator.jsp").forward(request, response);
        }
    }
}