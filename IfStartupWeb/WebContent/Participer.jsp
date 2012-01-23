<%-- 
    Document   : Participer
    Created on : 16 janv. 2012, 00:29:14
    Author     : UTILISATEUR
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<!DOCTYPE html>
<html:html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Participer</title>
    </head>
    <body>
        <html:form action="/Investir">
        	<label>Entrer le montant de la participation: </label>
        	<html:hidden name="InvestirForm" property="idLevee" value="${requestScope['idlevee']}"/>
        	<html:hidden name="InvestirForm" property="investisseur" value="${requestScope['idinv']}"/>
        	<html:hidden name="InvestirForm" property="startup" value="${requestScope['idstart']}"/>
            <html:text name="InvestirForm" property="montant"/>
			<html:submit property="method" value="Participer"/>
        </html:form>
    </body>
</html:html>
