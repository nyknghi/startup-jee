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
	<link rel="stylesheet" type="text/css" href="myCss.css">
	<title>Fondateur</title>
</head>
<body>
		<div class="entete"><h1>STARTUP - Business Master</h1></div>
		<h1>Bienvenue FONDATEUR</h1>
         <br/>
         <table>
            <thead>
                <tr>
                    <th><h2>Actions</h2></th>
                    <th><a href="Deconnexion.do">D�connection</a></th>
                </tr>
            </thead>
            <tbody>
	            <tr>
	                <td>
	                    <a href="creerstartup.jsp">Monter une startup</a>
	                </td>              
	            </tr>
	            <tr>
	                <td>
	                    <a href="RechercherStartup.jsp">Rechercher des startups</a>
	                </td>                
	            </tr>
	            <tr>
	                <td>
	                    <a href="OrganiserLevee.do?method=load">Organiser une lev�e de fonds</a>
	                </td>                
	            </tr>
	            <tr>
	                <td>
	                    <a href="EtapeLevee.do">G�rer vos lev�es de fonds</a>
	                </td>                
	            </tr>
	            <tr>
	                <td>
	                    <a href="InvestirFondateur.jsp">Investir dans une startup</a>
	                </td>                
	            </tr>
	            <tr>
	                <td>
	                    <a href="RechercherClubAmi.jsp">Rechercher clubs amis</a>
	                </td>                
	            </tr>
	         </tbody>            
        </table>
</body>
</html>