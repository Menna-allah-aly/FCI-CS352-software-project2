<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
body {
    margin-left: 200px;
    background: #5d9ab2 url("img_tree.png") no-repeat top left;
}

.container {
    text-align: center;
}

.center_div {
    border: 1px solid gray;
    margin-left: auto;
    margin-right: auto;
    width: 90%;
    background-color: #d0f0f6;
    text-align: left;
    padding: 8px;}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<div class="container">
  <div class="center_div">
    <h1>Home page</h1>
    <p>Helloo guys into home page....</p>
  </div>
</div>
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
 Acceptemail : <input type="text" name="acceptfriend" /> <br>
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

<form action="/social/sendmsg" method="post"> 
 Enter your msg: <input type="text" name="sendmsg" /> <br>
 
 Enter email of your friend: <input type="text" name="friendemail" /> <br>
<input type="submit" value="send">
</form> 



<form action="/social/group_message" method="post"> 
   <br>
<input type="submit" value="Group_message">
</form>




</body>
</html>