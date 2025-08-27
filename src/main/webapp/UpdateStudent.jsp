<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Update Student data</h3>
<form action="update_Student_data" method="post">
Name : <input type="text" name="name" placeholder="Enter Student Name" ><br><br>
Roll no. : <input type="text" name="roll" placeholder="Enter Student Roll no."><br><br>
Email id : <input type="email" name="email" placeholder="Enetr Student Email id"><br><br>
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
<input type="submit" value="Update">
</form>
</body>
</html>