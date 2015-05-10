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
    <h1>Not founded page!</h1>
    <p>ooooooooooooooooops not founded sorry .</p>
  </div>
</div>
<p>  ${it.name} </p>



<form action="/social/accept" method="post"> 
   <br>
<input type="submit" value="Back">
</form> 

</body>

</html>