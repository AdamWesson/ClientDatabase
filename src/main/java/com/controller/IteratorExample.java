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
    private static final long serialVersionUID = 1L;
    private String filename = "e:\\xampp\\tomcat\\webapps\\data\\Client_List.txt";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Inside Servlet");
        String type = request.getParameter("type");
        if (type.equals("getDetails")) {
            ClientDetails.readClientFromFile(filename);
            request.setAttribute("clients", ClientDetails.readClientFromFile(filename));
            request.getRequestDispatcher("iterator.jsp").forward(request, response);
        } else if (type.equals("sortSurname")) {
            ClientDetails.readClientFromFile(filename);
            ArrayList<ClientDetails> sur = ClientDetails.readClientFromFile(filename);

            if (request.getParameter("surname") != null){
                System.out.println(request.getParameter("surname"));
                ArrayList<ClientDetails> result = new ArrayList<ClientDetails>();
                for (ClientDetails person : sur) {
                    if (person.getLastName().equals(request.getParameter("surname"))) {
                        result.add(person);
                        System.out.println("names equal");
                    } else {
                        System.out.println("names not equal");
                    }
                }
                request.setAttribute("SurnameList", result);
                Collections.sort(sur, ClientDetails.compareLastName);
                request.setAttribute("SurnameList2", sur);
                request.getRequestDispatcher("iterator.jsp").forward(request, response);
            }
                Collections.sort(sur, ClientDetails.compareLastName);
                request.setAttribute("SurnameList", sur);
                request.getRequestDispatcher("iterator.jsp").forward(request, response);

        }
        else if(type.equals("sortYear"))
        {
            ClientDetails.readClientFromFile(filename);
            ArrayList<ClientDetails> y = ClientDetails.readClientFromFile(filename);

            if (request.getParameter("bYear") != null  ){
                ArrayList<ClientDetails> result1 = new ArrayList<ClientDetails>();
                for (ClientDetails birYear : y) {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM/dd/yyyy");
                    String yx = birYear.getDateOfBirth();
                    String[] arrOfStr = yx.split("/", 3);

                    System.out.println("1=" + arrOfStr[2]);

                    String y1 = arrOfStr[2];

                    String y2 = request.getParameter("bYear");
                    System.out.println("2="+y2);

                    int xyz = y1.compareTo(y2);
                    if(xyz == 0){
                        result1.add(birYear);
                        System.out.println("added");
                    } else if (xyz != 0){
                        System.out.println("not added");
                    }

                }
                request.setAttribute("YearList", result1);
                request.getRequestDispatcher("iterator.jsp").forward(request, response);
            }
            Collections.sort(y, ClientDetails.compareDOB);
            request.setAttribute("YearList", y);
            request.getRequestDispatcher("iterator.jsp").forward(request, response);
        }
        else if(type.equals("sortBday"))
        {
            ClientDetails.readClientFromFile(filename);
            ArrayList<ClientDetails> b = ClientDetails.readClientFromFile(filename);

            if (request.getParameter("dobDay") != null || request.getParameter("dobMonth") != null ){
                System.out.println(request.getParameter("dobDay") + "/" + request.getParameter("dobMonth"));
                ArrayList<ClientDetails> result = new ArrayList<ClientDetails>();
                for (ClientDetails dateOB : b) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
                    try {
                        Date birthDay = sdf.parse(dateOB.getDateOfBirth());
                        Date parsedDate = sdf.parse(request.getParameter("dobMonth") + "/" + request.getParameter("dobDay"));
                        System.out.println("birthDay=" + birthDay + " - parsedDate = " + parsedDate );
                        if (birthDay.equals(parsedDate)) {
                            result.add(dateOB);
                            System.out.println("DOB equal");
                        } else {
                            System.out.println("DOB not equal");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                request.setAttribute("BdayList", result);
                request.getRequestDispatcher("iterator.jsp").forward(request, response);
            } else if (request.getParameter("startAge") != null || request.getParameter("endAge") != null ){
                ArrayList<ClientDetails> result = new ArrayList<ClientDetails>();
                for (ClientDetails dateOB : b) {
                    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

                    Date dateOfBirth = null;
                    try {
                        dateOfBirth = sdf.parse(dateOB.getDateOfBirth());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    System.out.println("dateOfBirth = " + dateOfBirth);
                    int age = 0;
                    Calendar born = Calendar.getInstance();
                    Calendar now = Calendar.getInstance();
                    if(dateOfBirth!= null) {
                        now.setTime(new Date());
                        born.setTime(dateOfBirth);
                        if(born.after(now)) {
                            throw new IllegalArgumentException("Can't be born in the future");
                        }
                        age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
                        if(now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR))  {
                            age-=1;
                        }
                    }
                    System.out.println("Age = " + age);
                    if(age >= Integer.parseInt(request.getParameter("startAge")) && age <= Integer.parseInt(request.getParameter("endAge"))){
                        result.add(dateOB);
                        System.out.println("Age is between");
                    } else {
                        System.out.println("Age not between");
                    }

                }
                Collections.sort(result, ClientDetails.compareBday);
                request.setAttribute("BdayList", result);
                request.getRequestDispatcher("iterator.jsp").forward(request, response);
            }

            Collections.sort(b, ClientDetails.compareBday);
            request.setAttribute("BdayList", b);
            request.getRequestDispatcher("iterator.jsp").forward(request, response);
        }
    }
}