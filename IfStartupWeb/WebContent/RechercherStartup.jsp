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
<title>Insert title here</title>
</head>
<body>
	<html:form action="/RechercherStartup">
	    <table border="0">
	        <tbody>
	            <tr>
	                <td>Entrer le nom du Startup:</td>
	                <td><html:text name="RechercherStartupForm" property="nom" /></td>
	            </tr>
	            <tr>
	                <td>Entrer les activit�s:</td>
	                <td><html:text name="RechercherStartupForm" property="activite" /></td>
	            </tr>
	            <tr>
	                <td></td>
	                <td><html:submit value="Rechercher" /></td>
	            </tr>
	        </tbody>
	    </table>
		<TABLE border="1">
		    <tr>
		      <td>
		        Nom du Startup
		      </td>
		      <td>
		        Activit�
		      </td>
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
		</TABLE>  	    
	</html:form>
</body>
</html>