<%-- 
    Document   : Investir
    Created on : 16 janv. 2012, 00:03:15
    Author     : UTILISATEUR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INVESTIR</title>
    </head>
    <body>
        <h3>Les levées de fonds auxquelles vous participez</h3>
        <html:form action="/Investir">
            <table border="1" width="1" cellspacing="1" cellpadding="1">
                <thead>
                    <tr>
                        <th>DATE</th>
                        <th>ORGANISATEUR</th>
                        <th>ETAPE</th>
                        <th>MONTANT CIBLE</th>
                        <th>MONTANT DES PARTICIPATIONS</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${requestScope['levees_inscr'] }" var="levee">
	                    <tr>
	                        <td><c:out value="${levee.date}"/></td>
	                        <td><c:out value="${levee.startup}"/></td>
	                        <td><c:out value="${levee.etape}"/></td>
	                        <td><c:out value="${levee.cible}"/></td>
	                        <td><c:out value="${levee.total}"/></td>
	                        <td><html:radio name="InvestirForm" property="idLevee" value="${levee.idLevee}" /></td>
	                        <html:hidden name="InvestirForm" property="startup" value="${levee.startup }"/>
	                    </tr>
                    </c:forEach>
                </tbody>
            </table><br/>
           <html:submit property="method" value="Forward"/>
        </html:form>
    </body>
</html:html>
