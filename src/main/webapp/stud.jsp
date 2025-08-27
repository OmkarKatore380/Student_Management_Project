<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="in.sp.db.ConnectionDb" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Management</title>
<style>
    table, th, td { border: 1px solid black; border-collapse: collapse; padding: 8px; }
    table { width: 100%; margin-top: 20px; }
    input[type=text], input[type=email], input[type=tel], input[type=date] { padding: 5px; }
    input[type=submit] { padding: 5px 10px; margin-top: 10px; }
</style>
</head>
<body>

<!-- Insert Form -->
<form action="enter_stud_data" method="post">
<h3>Enter Student Data</h3>
Name : <input type="text" name="name" placeholder="Enter Student Name" ><br><br>
Roll no. : <input type="text" name="roll" placeholder="Enter Student Roll no."><br><br>
Email id : <input type="email" name="email" placeholder="Enter Student Email id"><br><br>
Contact no. : <input type="tel" name="con" placeholder="Enter Student Contact no."><br><br>
City : <select name="city">
<option>Nagpur</option>
<option>Bhandara</option>
<option>Pune</option>
<option>Nashik</option>
<option>Kolahapur</option>
<option>Aurangabad</option>
</select><br><br>
Date of Birth :
<input type="date" name="dob"><br><br>
<input type="submit" value="Enter Data ">
</form>

<hr>

<!-- Search Form -->
<h3>Search Student</h3>
<form method="get" action="stud.jsp">
    Roll No: <input type="text" name="roll">
    Name: <input type="text" name="name">
    <input type="submit" value="Search">
</form>

<%
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = ConnectionDb.getConnection();

        // --- Search Logic ---
        String searchRoll = request.getParameter("roll");
        String searchName = request.getParameter("name");

        if ((searchRoll != null && !searchRoll.trim().equals("")) || 
            (searchName != null && !searchName.trim().equals(""))) {
            
            ps = con.prepareStatement("SELECT * FROM student WHERE rollno=? OR name=?");
            ps.setString(1, (searchRoll != null && !searchRoll.trim().equals("")) ? searchRoll : "___");
            ps.setString(2, (searchName != null && !searchName.trim().equals("")) ? searchName : "___");

            rs = ps.executeQuery();
%>
            <h3>Search Result</h3>
            <table>
                <tr>
                    <th>Roll No</th><th>Name</th><th>Email</th><th>Contact</th><th>City</th><th>DOB</th><th>Action</th>
                </tr>
<%
            while(rs.next()) {
%>
                <tr>
                    <td><%= rs.getString("rollno") %></td>
                    <td><%= rs.getString("name") %></td>
                    <td><%= rs.getString("email") %></td>
                    <td><%= rs.getString("contact") %></td>
                    <td><%= rs.getString("city") %></td>
                    <td><%= rs.getString("dob") %></td>
                    <td>
                        <a href="UpdateStudent.jsp?rollno=<%= rs.getString("rollno") %>">Update</a> | 
                        <a href="DeleteStudent.jsp?rollno=<%= rs.getString("rollno") %>">Delete</a>
                    </td>
                </tr>
<%
            }
%>
            </table>
<%
        }

        // --- Always show all students ---
        ps = con.prepareStatement("SELECT * FROM student");
        rs = ps.executeQuery();
%>
        <h3>All Students</h3>
        <table>
            <tr>
                <th>Roll No</th><th>Name</th><th>Email</th><th>Contact</th><th>City</th><th>DOB</th><th>Action</th>
            </tr>
<%
        while(rs.next()) {
%>
            <tr>
                <td><%= rs.getString("rollno") %></td>
                <td><%= rs.getString("name") %></td>
                <td><%= rs.getString("email") %></td>
                <td><%= rs.getString("contact") %></td>
                <td><%= rs.getString("city") %></td>
                <td><%= rs.getString("dob") %></td>
                <td>
                    <a href="UpdateStudent.jsp?rollno=<%= rs.getString("rollno") %>">Update</a> | 
                    <a href="DeleteStudent.jsp?rollno=<%= rs.getString("rollno") %>">Delete</a>
                </td>
            </tr>
<%
        }
%>
        </table>
<%
    } catch(Exception e) {
        out.println("<p style='color:red'>" + e.getMessage() + "</p>");
    } finally {
        if(rs!=null) try{rs.close();}catch(Exception ex){}
        if(ps!=null) try{ps.close();}catch(Exception ex){}
        if(con!=null) try{con.close();}catch(Exception ex){}
    }
%>

</body>
</html>
