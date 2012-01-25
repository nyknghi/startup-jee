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
<title>Participer à un Club Ami</title> 
</head>
<body>
	<html:form action="/ParticiperClub">
		<TABLE border="1">
		    <tr>
		      <td>
		        Nom du Club Ami
		      </td>
		      <td>
		      </td>
		      <td>
		      </td>	            
		    </tr>
		    <c:forEach items="${requestScope.clubs}" var="club">
		    <tr>
		      <td>
		        ${club.nomClub}
		      </td>   	
	          <td>
		          <div id="div1">
			          <c:url value="/ParticiperClub.do?method=participer" var="ParticiperClubUrl">
			          <c:param name="id">${club.idClub}</c:param>
			          </c:url>
			          <A href="${ParticiperClubUrl}">Participer au Club Ami</A>
			      </div>
	          </td>
	          <td>
		          <div id="div2">
			          <c:url value="/ParticiperClub.do?method=quitter" var="QuitterClubUrl">
			          <c:param name="id">${club.idClub}</c:param>
			          </c:url>
			          <A href="${QuitterClubUrl}">Quitter le Club Ami</A>
		          </div>
	          </td>	          	        
		    </tr>
		    </c:forEach> 
		</TABLE>
	</html:form>
</body>
</html>