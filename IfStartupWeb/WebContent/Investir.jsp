<%-- 
    Document   : Investir
    Created on : 16 janv. 2012, 00:03:15
    Author     : UTILISATEUR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INVESTIR</title>
    </head>
    <body>
        <h3>Les levées de fonds auxquelles vous participez</h3>
                <html:form action="/Investir">
            <table border="0" width="1" cellspacing="1" cellpadding="1">
                <thead>
                    <tr>
                        <th>DATE</th>
                        <th>ORGANISATEUR</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${request.inscriptions_levees}" var="levee"/>
                    <tr>
                        <td><c:out value="${levee.date}"/></td>
                        <td><c:out value="${levee.startup}"/></td>
                        <td><html:radio name="InscriptionForm" property="idLevee" value="${levee.idLevee}" /></td>
                    </tr>
                </tbody>
            </table>
           <html:submit value="Investir"/>
        </html:form>
    </body>
</html>
