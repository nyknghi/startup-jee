<%-- 
    Document   : success
    Created on : 11 janv. 2012, 19:46:42
    Author     : UTILISATEUR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean
           " %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="myCss.css">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h3><bean:write name="LoginForm" property="email" filter="false"/></h3>
    </body>
</html:html>
