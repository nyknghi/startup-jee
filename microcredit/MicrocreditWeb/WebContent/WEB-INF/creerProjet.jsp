<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Creation projet</title>
</head>
<body>
<div class="entete"><h1>Projet - Business Master</h1></div>
        <h1>Cr&eacute;ation Projet</h1>
        <br/>
        <div style="color:red">
            <html:errors />
        </div>        
        <html:form action="/CreerProjet">
            <table>
                <tr>
                    <td>
                        Nom du Projet :
                    </td>
                    <td>
                        <html:text name="creerProjetForm" property="nom" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Capital du projet :
                    </td>
                    <td>
                        <html:text name="creerStartupForm" property="capital" />
                    </td>
                </tr>
                <tr>
                    <td>
                        But projet :
                    </td>
                    <td>
                        <html:textarea cols="20" rows="5" name="creerProjetForm" property="but" />
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
</html>