<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
        <b>Creer Startup</b>
        <hr>
        <html:form action="/CreerStartup">
            <table>
                <tr>
                    <td>
                        Nom du Startup :
                    </td>
                    <td>
                        <html:text name="creerStartupForm" property="nom" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Capital du Startup :
                    </td>
                    <td>
                        <html:text name="creerStartupForm" property="capital" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Activités :
                    </td>
                    <td>
                        <html:textarea cols="20" rows="5" name="creerStartupForm" property="activite" />
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