<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<p> Welcome b2a ya ${it.name} </p>
<p> This is should be user home page </p>
<p> Current implemented services "http://fci-swe-apps.appspot.com/rest/RegistrationService --- {requires: uname, email, password}" </p>
<p> and "http://fci-swe-apps.appspot.com/rest/LoginService --- {requires: uname,  password}" </p>
<p> you should implement sendFriendRequest service and addFriend service


<form action="/social/sendrequest" method="post"> 
 Enter the email of your friend: <input type="text" name="friendemail" /> <br>
<input type="submit" value="sendrequest">
</form> 

<br><form action="/social/acceptrequest" method="post"> 
 AcceptName : <input type="text" name="acceptfriend" /> <br>
<input type="submit" value="acceptrequest">
</form> </br>

<form action="/social/signout" method="post">  <br>
<br> <input type="submit" value="Signout"> 
</form> 


<form action="/social/search" method="post"> 
 Enter the email of your friend to search on it: <input type="text" name="searchemail" /> <br>
<input type="submit" value="search">
</form> 

<form action="/social/acceptsearch" method="post"> 
 
<input type="submit" value="searchsendedrequest">
</form> 









</body>
</html>