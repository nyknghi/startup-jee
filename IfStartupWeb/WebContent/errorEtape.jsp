<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ERROR</title>
</head>
<body>
	<h1>La levée de fonds ne prend pas encore de participations!</h1>
	<c:choose>
		<c:when test="${sessionScope.User=='BA' }"><a href="BA.jsp">Retour au menu</a></c:when>
		<c:when test="${sessionScope.User=='investisseur' }"><a href="Investisseur.jsp">Retour au menu</a></c:when>
	</c:choose>
</body>
</html>