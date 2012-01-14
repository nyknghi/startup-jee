<%-- 
    Document   : error
    Created on : 14 janv. 2012, 15:19:18
    Author     : UTILISATEUR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERROR PAGE</title>
    </head>
    <body>
        <h1>Erreur!</h1>
        <a <%
              if(request.getSession().getAttribute("User").equals("fondateur")){
                  out.print("href= 'fondateur.jsp'");
              }else if(request.getSession().getAttribute("User").equals("BA")){
                  out.print("href= 'BA.jsp'");
              }else{
                  out.print("href= 'Investisseur.jsp'");
              }
        %>>Retour au menu</a>
    </body>
</html>
