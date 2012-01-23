<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INVESTIR</title>
</head>
<body>
	<h1>Investir dans une startup en tant que membre fondateur</h1>
	<html:form action="/InvestirFondateur">
		<label>Entrer le nom de la startup de votre copain (case sensitive)</label>
		<a href="RechercherStartup.jsp">Si vous ne le connaissez pas, vous pouvez faire une petite recherche par là</a><br/>
		<html:text name="InvestirFondateurForm" property="startup"></html:text><br/>
		<label>Entrer le montant</label><br/>
		<html:text name="InvestirFondateurForm" property="montant"></html:text><br/>
		<br/><html:submit value="Investir"></html:submit>
	</html:form>
</body>
</html:html>