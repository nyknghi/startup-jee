<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %> 
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="myCss.css">
<title>Organiser une levée de fonds</title>
</head>
<body>
	<div class="entete"><h1>STARTUP - Business Master</h1></div>
    <h1>Organiser une levée de fonds</h1>
        <html:form action="/OrganiserLevee">
            <table>
                <tr>
                    <td>
                        Organisateur :
                    </td>
                    <td>
                        <html:select name="LeveeForm" property="startup">
                            <html:option value="0">Selectionner la startup</html:option>
                            <html:optionsCollection name="LeveeForm" property="startupList" label="nom" value="idStartup"/>
                        </html:select>                     
                    </td>
                </tr>            
                <tr>
                    <td>
                        Le fonds ciblé :
                    </td>
                    <td>
                        <html:text name="LeveeForm" property="cible" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <html:submit property="method" value="Creer"/>
                    </td>
                </tr>
            </table>
        </html:form>
</body>
</html:html>