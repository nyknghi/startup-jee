<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="myCss.css">
<title>Monter Club Ami</title>
</head>
<body>
	<div class="entete"><h1>STARTUP - Business Master</h1></div>
	<h1>Monter un club ami</h1>
	<html:form action="/MonterClub">
	    <table>
	        <tr>
	            <td>
	                Nom du Club Ami :
	            </td>
	            <td>
	                <html:text name="MonterClubForm" property="nom" />
	            </td>
	        </tr>
	        <tr>
	            <td>
	                <html:submit value="Monter"/>
	            </td>
	        </tr>
	    </table>
	</html:form>
</body>
</html>