<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="myCss.css">
<title>INVESTIR</title>
</head>
<body>
	<div class="entete"><h1>STARTUP - Business Master</h1></div>
	<h1>Investir dans une startup</h1> 
	<h1>en tant que membre fondateur</h1>
	<html:form action="/InvestirFondateur">
		<table border="0">
			<tbody>
				<tr>
					<td>Nom startup: </td>
					<td><html:text name="InvestirFondateurForm" property="startup"></html:text></td>
					<td><a href="RechercherStartup.jsp">Rechercher startup</a></td>
				</tr>
				<tr>
					<td>Montant: </td>
					<td><html:text name="InvestirFondateurForm" property="montant"></html:text></td>
				</tr>
				<tr>
					<td><html:submit value="Investir"></html:submit></td>		
				</tr>
			</tbody>
		</table>
	</html:form>
</body>
</html:html>