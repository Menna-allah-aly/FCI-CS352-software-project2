<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
	<form action="/social/PagePost" method="post">
     	PostType     : <input type="text" name="PostType" /> <br>
		PageName : <input type="text" name="TimelineName" /> <br>
		Post         : <input type="text" name="Post" /> <br>
		Feeling     : <input type="text" name="Feeling" /> <br>
		Privacy   : <input type="text" name="Privacy" /> <br>
		
		 <br> <input type="submit" value="Post"> <br>
		

	</form>
</body>
</html>