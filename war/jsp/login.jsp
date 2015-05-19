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
    <h1>Hello in Login page!</h1>
    <p>write your name and password to enter our website </p>
  </div>
</div>

<form action="/social/home" method="post">
  Name : <input type="text" name="uname" /> <br>
  Password : <input type="password" name="password" /> <br>
  <input type="submit" value="Login">
  
  </form>
</body>
</html>