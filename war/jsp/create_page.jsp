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

<form action="/social/create_page_data" method="post">

  Name : <input type="text" name="name" /> <br>
  Category :<input type="text" name="category" /> <br>
  Type : <input type="text" name="type" /> <br>
  
  <input type="submit" value="create">
  
 </form>
 
 
 
 
 
 
</body>
</html>