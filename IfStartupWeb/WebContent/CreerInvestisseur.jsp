<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="myCss.css">
	<title>Nouveau profil</title>
</head>
<body>
        <div class="entete"><h1>STARTUP - Business Master</h1></div>
    	<h1>Cr&eacute;ation d'un nouveau profil d'investisseur</h1>
        <hr>
        <html:form action="/CreerInvestisseur">
            <table>
                <tr>
                    <td>
                        Nom d'investisseur :
                    </td>
                    <td>
                        <html:text name="CreerInvestisseurForm" property="nom" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Type d'investisseur :
                    </td>
                    <td>
                        <html:radio name="CreerInvestisseurForm" property="type" value="Fondateur" >
                            Fondateur
                        </html:radio>
                        <html:radio name="CreerInvestisseurForm" property="type" value="BA" >
                            Business Angel
                        </html:radio>
                        <html:radio name="CreerInvestisseurForm" property="type" value="Investisseur" >
                            Investisseur
                        </html:radio>                        
                    </td>
                </tr>               
                <tr>
                    <td>
                        Email :
                    </td>
                    <td>
                        <html:text name="CreerInvestisseurForm" property="email" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Mot de passe :
                    </td>
                    <td>
                        <html:password name="CreerInvestisseurForm" property="password" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <html:submit value="Créer"/>
                    </td>
                </tr>
            </table>
        </html:form>
</body>
</html:html>