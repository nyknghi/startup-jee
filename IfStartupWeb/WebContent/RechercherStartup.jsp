<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="myCss.css">
	<title>Rechercher startup</title>
</head>
<body>
	<div class="entete"><h1>STARTUP - Business Master</h1></div>
	<h1>Recherche d'une startup</h1>
	<html:form action="/RechercherStartup">
	    <table border="0">
	        <tbody>
		        <thead>
	                <tr>
	                    <th><h2>Recherche par critères</h2></th>
	                </tr>
	            </thead>
	            <tr>
	                <td>Entrer le nom de la startup:</td>
	                <td><html:text name="RechercherStartupForm" property="nom" /></td>
	            </tr>
	            <tr>
	                <td>Entrer son domaine d'activit&eacute;:</td>
	                <td><html:text name="RechercherStartupForm" property="activite" /></td>
	            </tr>
	            <tr>
	                <td></td>
	                <td><html:submit value="Rechercher" /></td>
	            </tr>
	        </tbody>
	    </table>
	    <br/>
	    
		<TABLE border="1">
			<thead>
                <tr>
                    <th><h2>R&eacute;sultat de la recherche</h2></th>
                </tr>
            </thead>
            <tbody>
			    <tr>
			      <th>
			        Nom du Startup
			      </th>
			      <th>
			        Activité
			      </th>
			    </tr>
			    <c:forEach items="${requestScope.startups}" var="startup">
			    <tr>
			      <td>
			        ${startup.nomStartup}
			      </td>
			      <td>
			        ${startup.activite}
			      </td>		        
			    </tr>
			    </c:forEach> 
			</tbody>
		</TABLE>  	    
	</html:form>
</body>
</html>