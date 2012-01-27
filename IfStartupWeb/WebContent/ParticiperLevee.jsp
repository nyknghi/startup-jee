<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="myCss.css">
<title>Participer à une levée de fonds</title>
</head>
<body>
 		<div class="entete"><h1>STARTUP - Business Master</h1></div>
		<h1>Inscription levée de fonds</h1>
        
             <html:form action="/ParticiperLevee">
            <table border="1" width="1" cellspacing="1" cellpadding="1">
                <thead>
                    <tr>
                        <th>DATE</th>
                        <th>ORGANISATEUR</th>
                        <th>ETAPE</th>
                        <th>CIBLE</th>
                        <th nowrap="nowrap">MONTANT DES PARTICIPATIONS</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope['list_levees']}" var="levee">
                    <tr>
                        <td nowrap="nowrap"><c:out value="${levee.date}"/></td>
                        <td nowrap="nowrap"><c:out value="${levee.startup}"/></td>
                        <td><c:out value="${levee.etape}"/></td>
                        <td><c:out value="${levee.cible}"/></td>
                        <td><c:out value="${levee.total}"/></td>
                        <td><html:radio name="InscriptionForm" property="idLevee" value="${levee.idLevee}" /></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table><br/>
           <html:submit property="method" value="Participer"/>
        </html:form>
</body>
</html:html>