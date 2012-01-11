<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP</title>
</head>
<body>
	<div style="color: red">
		<html:errors />
	</div>
	<html:form action="/Login">
            User name : <html:text name="LoginForm" property="userName" />
		<br>
            Password  : <html:password name="LoginForm"
			property="password" />
		<br>
		<html:submit value="Login" />
	</html:form>	
</body>
</html>