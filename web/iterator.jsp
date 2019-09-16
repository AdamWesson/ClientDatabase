<%--
  Created by IntelliJ IDEA.
  User: WessonAN
  Date: 2019/09/11
  Time: 03:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%--Iterator.jsp --%>
<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<%--Importing all the dependent classes--%>
<%@ page import="com.dao.ClientDetails" %>
<%@ page import="java.util.*" %>
<%@ page buffer="32768kb" %>

<% ArrayList<ClientDetails> clientList = (ArrayList) request.getAttribute("clients"); %> <%--Assigning ArrayList object containing Client data to the local object --%>
<% ArrayList<ClientDetails> surnameList = (ArrayList) request.getAttribute("SurnameList"); %> <%--Assigning ArrayList object containing Client data to the local object --%>
<% ArrayList<ClientDetails> yearList = (ArrayList) request.getAttribute("YearList"); %>
<% ArrayList<ClientDetails> bdayList = (ArrayList) request.getAttribute("BdayList"); %>
<% ArrayList<ClientDetails> surnameList2 = (ArrayList) request.getAttribute("SurnameList2"); %>

<strong><a href="<%=request.getContextPath()%>/IteratorExample?type=getDetails">Show Client Details | </a></strong>
<strong><a href="<%=request.getContextPath()%>/IteratorExample?type=sortSurname">Sorted by Surname | </a></strong>
<strong><a href="<%=request.getContextPath()%>/IteratorExample?type=sortYear">Sorted by Birth Year | </a></strong>
<strong><a href="<%=request.getContextPath()%>/IteratorExample?type=sortBday">Sorted by Same Birthday</a></strong>

<br>
<table cellspacing="2" cellpadding="2">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Date Of Birth</th>
        <th>Gender</th>
        <th>Post Code</th>
    </tr>
    <%
        // Iterating through clientList
        if (request.getAttribute("clients") != null)  // Null check for the object
        {
            Iterator<ClientDetails> iterator = clientList.iterator();  // Iterator interface
            while (iterator.hasNext())  // iterate through all the data until the last record
            {
                ClientDetails clientDetails = iterator.next(); //assign individual employee record to the employee class object
    %>
    <tr>
        <td><%=clientDetails.getFirstName()%>
        </td>
        <td><%=clientDetails.getLastName()%>
        </td>
        <td><%=clientDetails.getDateOfBirth()%>
        </td>
        <td><%=clientDetails.getGender()%>
        </td>
        <td><%=clientDetails.getPostCode()%>
        </td>
    </tr>
    <%
            }
        } else if        // Iterating through clientList
        (request.getAttribute("SurnameList") != null)  // Null check for the object
        {
            Map<String, Integer> map = new HashMap<String, Integer>();

            int x = 0;
            if(request.getAttribute("SurnameList2") != null){
            for (ClientDetails cli : surnameList2) {
                String key = cli.getLastName();
                if (!map.containsKey(key)) {
                    map.put(key, x);
                    x++;
                }

            }
            } else {
            for (ClientDetails cli : surnameList) {
                String key = cli.getLastName();
                if (!map.containsKey(key)) {
                    map.put(key, x);
                    x++;
                }

            }
            }
%>
    <form action="<%=request.getContextPath()%>/IteratorExample" method="get" name="surN" enctype="multipart/form-data">
        <input name="type" type="hidden" value="sortSurname" />
        <select name="surname">
    <%
            for(Map.Entry m : map.entrySet()){
                out.println("<option name=\""+m.getKey()+"\">"+m.getKey()+"</option>");
            }
    %>
        </select>
        <input type="submit" value="Submit" />
    </form>
    <%



            Iterator<ClientDetails> iterator = surnameList.iterator();  // Iterator interface
            while (iterator.hasNext())  // iterate through all the data until the last record
            {
                ClientDetails surnameDetails = iterator.next(); //assign individual employee record to the employee class object
    %>
    <tr>
        <td><%=surnameDetails.getFirstName()%>
        </td>
        <td><%=surnameDetails.getLastName()%>
        </td>
        <td><%=surnameDetails.getDateOfBirth()%>
        </td>
        <td><%=surnameDetails.getGender()%>
        </td>
        <td><%=surnameDetails.getPostCode()%>
        </td>
    </tr>
    <%
            }
        } else if
            (request.getAttribute("YearList") != null)  // Null check for the object
            {

                %>
    <form action="/ClientDatabase_war/IteratorExample" method="get" enctype="multipart/form-data">
        <input name="type" type="hidden" value="sortYear" />
        <label>Enter Birth Year: <input name="bYear" type="text" value="" /> (Format: yyyy)</label>
        <input name="submit" type="submit" value="Submit" />
    </form>

    <%

                Iterator<ClientDetails> iterator = yearList.iterator();  // Iterator interface
                while (iterator.hasNext())  // iterate through all the data until the last record
                {
                    ClientDetails yearDetails = iterator.next(); //assign individual employee record to the employee class object
    %>
    <tr>
        <td><%=yearDetails.getFirstName()%>
        </td>
        <td><%=yearDetails.getLastName()%>
        </td>
        <td><%=yearDetails.getDateOfBirth()%>
        </td>
        <td><%=yearDetails.getGender()%>
        </td>
        <td><%=yearDetails.getPostCode()%>
        </td>
    </tr>
    <%
            }
        } else if
    (request.getAttribute("BdayList") != null)  // Null check for the object
    {

    %>
    <form action="/ClientDatabase_war/IteratorExample" method="get" enctype="multipart/form-data">
        <input name="type" type="hidden" value="sortBday" />
        <select name="dobMonth">
            <option>- Month -</option>
            <option value="1">January</option>
            <option value="2">Febuary</option>
            <option value="3">March</option>
            <option value="4">April</option>
            <option value="5">May</option>
            <option value="6">June</option>
            <option value="7">July</option>
            <option value="8">August</option>
            <option value="9">September</option>
            <option value="10">October</option>
            <option value="11">November</option>
            <option value="12">December</option>
        </select>
        <select name="dobDay">
            <option>- Day -</option>
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
            <option value="11">11</option>
            <option value="12">12</option>
            <option value="13">13</option>
            <option value="14">14</option>
            <option value="15">15</option>
            <option value="16">16</option>
            <option value="17">17</option>
            <option value="18">18</option>
            <option value="19">19</option>
            <option value="20">20</option>
            <option value="21">21</option>
            <option value="22">22</option>
            <option value="23">23</option>
            <option value="24">24</option>
            <option value="25">25</option>
            <option value="26">26</option>
            <option value="27">27</option>
            <option value="28">28</option>
            <option value="29">29</option>
            <option value="30">30</option>
            <option value="31">31</option>
        </select>
        <input name="submit" type="submit" value="Submit" />
    </form>
    <p>OR</p>
    <form action="/ClientDatabase_war/IteratorExample" method="get" enctype="multipart/form-data">
        <input name="type" type="hidden" value="sortBday" />
        <label>Enter start age:
            <input name="startAge" type="text" value="" />
        </label>
        <label>Enter end age:
            <input name="endAge" type="text" value="" />
        </label>
        <input name="submit" type="submit" value="Submit" />
    </form>
    <%//



        Iterator<ClientDetails> iterator = bdayList.iterator();  // Iterator interface
        while (iterator.hasNext())  // iterate through all the data until the last record
        {
            ClientDetails dobDetails = iterator.next(); //assign individual employee record to the employee class object
    %>
    <tr>
        <td><%=dobDetails.getFirstName()%>
        </td>
        <td><%=dobDetails.getLastName()%>
        </td>
        <td><%=dobDetails.getDateOfBirth()%>
        </td>
        <td><%=dobDetails.getGender()%>
        </td>
        <td><%=dobDetails.getPostCode()%>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>
