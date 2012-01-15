<%-- 
    Document   : AllInvestisseurs
    Created on : 14 janv. 2012, 13:33:27
    Author     : UTILISATEUR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<!DOCTYPE html>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="myCss.css">
        <title>GESTION INVESTISSEURS</title>
    </head>
    <body>
	    <div class="entete"><h1>STARTUP - Business Master</h1></div>
	    <h1>Liste des Investisseurs</h1>
	    <br/>
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
                                <c:forEach items="${requestScope.investisseurs}" var="investisseurs">
								   <tr>
								     <td>
								       ${investisseurs.nom}
								     </td>
								     <td>
								       ${investisseurs.mail}
								     </td>
								     <td>
								       ${investisseurs.mdp}
								     </td>		        
								   </tr>
								  </c:forEach> 
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
    </body>
</html:html>
