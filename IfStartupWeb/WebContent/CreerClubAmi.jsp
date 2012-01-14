<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="myCss.css">
	<title>CREATION CLUB AMI</title>
</head>
<body>
		<div class="entete"><h1>STARTUP - Business Master</h1></div>
        <h1>Cr&eacute;ation Club Ami</h1>
        <br/>
        <html:form action="/CreerClubAmi">
            <table>
                <tr>
                    <td>
                        Nom du Club :
                    </td>
                    <td>
                        <html:text name="CreerClubAmiForm" property="nom" />
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