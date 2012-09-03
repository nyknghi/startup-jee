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
<title>Les projets</title>
</head>
<body>
<div class="entete"><h1>Microcredit - Business Master</h1></div>
    <h1>Liste des Projets</h1>
    <br/>
	<html:form action="/AfficherProjet">
		<TABLE border="2">
		    <tr>
		      <th>
		        Projet
		      </th>
		      <th>
		        But Projet 
		      </th>
		    </tr>
		    <c:forEach items="${requestScope.projets}" var="projet">
		    <tr>
		      <td>
		        ${projet.nomProjet}
		      </td>
		      <td>
		        ${projet.butProjet}
		      </td>		        
		    </tr>
		    </c:forEach> 
		</TABLE>  
	</html:form>
	<div class="text">
		<a href="gestionnaire.jsp">Retour à la page gestionnaire</a>
	</div>              	
</body>
</html>