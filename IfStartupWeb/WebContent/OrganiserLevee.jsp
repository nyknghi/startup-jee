<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Organiser une lev�e de fonds</title>
</head>
<body>
        <b>Organiser une lev�e de fonds</b>
        <hr>
        <html:form action="/OrganiserLevee">
            <table>
                <tr>
                    <td>
                        Nom du Startup :
                    </td>
                    <td>
                        <html:text name="OrganiserLeveeForm" property="nom" />
                    </td>
                </tr>            
                <tr>
                    <td>
                        Date :
                    </td>
                    <td>
                        <html:text name="OrganiserLeveeForm" property="date" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Le fonds cibl� :
                    </td>
                    <td>
                        <html:text name="OrganiserLeveeForm" property="cible" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <html:submit value="Organiser"/>
                    </td>
                </tr>
            </table>
        </html:form>
</body>
</html>