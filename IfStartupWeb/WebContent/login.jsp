<%--
    Document   : Login
    Created on : 11 janv. 2012, 15:57:25
    Author     : UTILISATEUR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean
           " %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:html lang="true">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="myCss.css">
        <title>CONNECTION</title>
    </head>
    <body>
        <h1>Authentification</h1>
        <html:form action="/Login">
    <table border="0">
        <tbody>
            <tr>
                <td>Entrer votre email:</td>
                <td><html:text name="LoginForm" property="email" /></td>
            </tr>
            <tr>
                <td>Entrer votre mot de passe:</td>
                <td><html:password name="LoginForm" property="password" /></td>
            </tr>
            <tr>
                <td></td>
                <td><html:submit value="Connection" /></td>
            </tr>
        </tbody>
    </table>
</html:form>
    </body>
</html:html>