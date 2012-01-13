<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Organiser une levée de fonds</title>
</head>
<body>
        <b>Organiser une levée de fonds</b>
        <hr>
        <html:form action="/OrganiserLevee">
            <table>
                <tr>
                    <td>
                        Nom du Startup :
                    </td>
                    <td>
                        <html:select property="startup" >
                            <html:option value="0">Select Startup</html:option>
                            <html:optionsCollection name="OrganiserLeveeForm" property="startupList" label="label" value="value" />
                        </html:select>                        
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
                        Le fonds ciblé :
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
</html:html>