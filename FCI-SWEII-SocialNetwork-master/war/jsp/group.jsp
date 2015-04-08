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
    <h1>Hello in group page!</h1>
    <p>write group id and memebr to make group chat  </p>
  </div>
</div>

<form action="/social/group" method="post"> 
enter member name : <input type="text" name="membername" /> <br>
enter Group ID : <input type="text" name="ID" /> <br>
<input type="submit" value="add">
</form>

<form action="/social/sendgroupmsg" method="post"> 

enter the message: <input type="text" name="message_of_group" /> <br>
enter the chat_id: <input type="text" name="chat_id" /> <br>
<input type="submit" value="send">
</form>


<form action="/social/back" method="post"> 
   <br>
<input type="submit" value="Back">
</form> 
