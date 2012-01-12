<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Participer à une levée de fonds</title>
</head>
<body>
        <b>Participer à une levée de fonds</b>
        <hr>
        <html:form action="/ParticiperLevee">
            <table>
                <tr>
                    <td>
                        Nom du Startup :
                    </td>
                    <td>
                        <html:text name="ParticiperLeveeForm" property="nom" />
                    </td>
                </tr>            
                <tr>
                    <td>
                        Le montant :
                    </td>
                    <td>
                        <html:text name="ParticiperLeveeForm" property="montant" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <html:submit value="Participer"/>
                    </td>
                </tr>
            </table>
        </html:form>
</body>
</html>