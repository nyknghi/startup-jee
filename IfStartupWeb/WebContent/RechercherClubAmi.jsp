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
	<title>Rechercher Club Ami</title>
</head>
<body>
	<div class="entete"><h1>STARTUP - Business Master</h1></div>
	<h1>Recherche des Clubs amis</h1>
	<html:form action="/RechercherClubAmi">
	    <table border="0">
	       <thead>
	                <tr>
	                    <th><h2>Recherche par crit�res</h2></th>
	                </tr>
	        </thead>
	        <tbody>
	            <tr>
	                <td>Entrer le nom du Club Ami</td>
	                <td><html:text name="RechercherClubAmiForm" property="nomclub" /></td>
	            </tr>
	            <tr>
	                <td></td>
	                <td><html:submit value="Rechercher" /></td>
	            </tr>
	        </tbody>
	    </table>
	    <br/>	    
		<TABLE border="0">
		   <thead>
	                <tr>
	                    <th><h2>R&eacute;sultat de recherche</h2></th>
	                </tr>
			</thead>
			<tbody>
		    <tr>
		      <td>
		        Nom du Club Ami
		      </td>
		    </tr>
		    <c:forEach items="${requestScope.clubamis}" var="clubami">
		    <tr>
		      <td>
		        ${clubami.nomClub}
		      </td>
	          <td>
		          <c:url value="/MettrePartenaire.do" var="MettrePartenaireUrl">
		          <c:param name="id">${clubami.idClub}</c:param>
		          </c:url>
		          <A href="${MettrePartenaireUrl}">Mettre en partenaire</A>
	          </td>              	        
		    </tr>
		    </c:forEach> 
		    </tbody>
		</TABLE>  	    
	</html:form>
	<br/>
	<div class="text">
	<c:choose>
		<c:when test="${sessionScope.User=='BA' }"><a href="BA.jsp">Retour au menu principal</a></c:when>
		<c:when test="${sessionScope.User=='fondateur' }"><a href="fondateur.jsp">Retour au menu principal</a></c:when>
	</c:choose>
	</div>
</body>
</html>