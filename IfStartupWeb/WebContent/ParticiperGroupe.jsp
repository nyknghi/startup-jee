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
<title>Participer à un groupe</title>
</head>
<body>
	<html:form action="/ParticiperGroupe">
		<TABLE border="1">
		    <tr>
		      <td>
		        Nom du groupe
		      </td>
		      <td>
		      </td>
		      <td>
		      </td>		      
		    </tr>
		    <c:forEach items="${requestScope.groupes}" var="groupe">
		    <tr>
		      <td>
		        ${groupe.nom}
		      </td>
		      <td>
		          <c:url value="/ParticiperGroupe.do?method=participer" var="ParticiperGroupeUrl">
		          <c:param name="id">${groupe.idInvestisseur}</c:param>
		          </c:url>
		          <A href="${ParticiperGroupeUrl}">Participer au groupe</A>
	          </td>
	          <td>
		          <c:url value="/ParticiperGroupe.do?method=quitter" var="QuitterGroupeUrl">
		          <c:param name="id">${groupe.idInvestisseur}</c:param>
		          </c:url>
		          <A href="${QuitterGroupeUrl}">Quitter le groupe</A>
	          </td>	          	        
		    </tr>
		    </c:forEach> 
		</TABLE>
	</html:form>
</body>
</html>