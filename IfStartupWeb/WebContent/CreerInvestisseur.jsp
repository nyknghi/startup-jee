<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accr�diter investisseur</title>
</head>
<body>
        <b>Accr�diter investisseur</b>
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
                        <html:radio name="CreerInvestisseurForm" property="type" value="F" >
                            Fondateur
                        </html:radio>
                        <html:radio name="CreerInvestisseurForm" property="type" value="B" >
                            Business Angel
                        </html:radio>
                        <html:radio name="CreerInvestisseurForm" property="type" value="I" >
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
                        <html:submit value="Cr�er"/>
                    </td>
                </tr>
            </table>
        </html:form>
</body>
</html:html>