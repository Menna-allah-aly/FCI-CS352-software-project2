<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
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
 <title>Welcome!</title>
</head>
<body>
<div class="container">
  <div class="center_div">
    <h1>Hello into register page!</h1>
    <p>we will be happy if you register in our website .</p>
  </div>
</div>

  <form action="/social/response" method="post">
  Name : <input type="text" name="uname" /> <br>
  Email : <input type="text" name="email" /> <br>
  Password : <input type="password" name="password" /> <br>
  <input type="submit" value="Register">
  
  </form>
</body>
</html>
