<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="myCss.css">
	<title>Business Angel</title>
</head>
<body>
	<div class="entete"><h1>STARTUP - Business Master</h1></div>
	<h1>Bienvenue Business Angel</h1>
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
	                    <a href="ParticiperLevee.do?method=load">Participer � une lev�e de fonds</a>
	                </td>                
	            </tr>
                    <tr>
	                <td>
	                    <a href="ChoisirLevee.do">Claquer vos sous</a>
	                </td>                
	            </tr>
	            <tr>
	                <td>
	                    <a href="MonterClub.jsp">Cr�er un club d'amis</a>
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