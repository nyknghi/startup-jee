<%-- 
    Document   : Participer
    Created on : 16 janv. 2012, 00:29:14
    Author     : UTILISATEUR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Participer</title>
    </head>
    <body>
        <html:form action="/Participer">
        <table border="0" width="1" cellspacing="1" cellpadding="1">
            <thead>
                <tr>
                    <th>Entrer le montant de la participation</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><html:hidden name="ParticipationForm" property="idLevee" value="${request.investir_levee}"/>
                        <html:text name="ParticipationForm" property="montant"/></td>
                </tr>
                <tr>
                    <td><html:submit value="Participer"/></td>
                </tr>
            </tbody>
        </table>
        </html:form>>
    </body>
</html:html>
