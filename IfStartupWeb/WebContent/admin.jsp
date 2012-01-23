<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" type="text/css" href="myCss.css">
        <title>ADMINISTRATION</title>
    </head>
    <body>
    	<div class="entete"><h1>STARTUP - Business Master</h1></div>
    	<h1>PAGE D'ADMINISTRATION</h1>
        <table border="0" cellspacing="2">
            <thead>
                <tr>
                    <th><h2>Gestion des profils d'utilisateurs</h2></th>
                    <th><a href="Deconnexion.do">Déconnection</a></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><a href="CreerInvestisseur.jsp">Cr&eacute;er un nouveau profil</a></td>
                </tr>
            </tbody>
        </table>
        <br/>
        <table border="0" cellspacing="2">
            <thead>
                <tr>
                    <th><h2>Gestion des startups</h2></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><a href="creerstartup.jsp">Cr&eacute;er une nouvelle startup</a></td>
                </tr>
            </tbody>
        </table>
    </body>
</html:html>