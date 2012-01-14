<%-- 
    Document   : AllInvestisseurs
    Created on : 14 janv. 2012, 13:33:27
    Author     : UTILISATEUR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="myCss.css">
        <title>GESTION INVESTISSEURS</title>
    </head>
    <body>
        <h1>GERER LES INVESTISSEURS</h1>
        <html:form action="/AllInvestisseurs">
            <table border="0" cellspacing="1" cellpadding="1">
                <tbody>
                    <tr>
                        <td><a href="CreerInvestisseur.jsp">Nouvel Investisseur</a></td>
                    </tr>
                    <tr>
                        <td>
                            <table border="0" cellspacing="1" cellpadding="1">
                                <thead>
                                    <tr>
                                        <th>Nom</th>
                                        <th>Email</th>
                                        <th>Password</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <logic:iterate id="list" name="CreerInvestisseurForm" property="list">
                                    <tr>
                                        <td><html:text name="list" property="nom"></td>
                                        <td><html:text name="list" property="email"></td>
                                        <td><html:text name="list" property="password"></td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
        </html:form>
    </body>
</html:html>
