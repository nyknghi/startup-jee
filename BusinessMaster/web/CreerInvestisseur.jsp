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
        <b>Accréditer investisseur</b>
        <hr>
        <html:form action="/Login">
            <table>
                <tr>
                    <td>
                        Nom d'investisseur :
                    </td>
                    <td>
                        <html:text name="LoginForm" property="nom" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Type d'investisseur :
                    </td>
                    <td>
                        <html:radio name="LoginForm" property="type" value="F" >
                            Fondateur
                        </html:radio>
                        <html:radio name="LoginForm" property="type" value="B" >
                            Business Angel
                        </html:radio>
                        <html:radio name="LoginForm" property="type" value="I" >
                            Investisseur
                        </html:radio>                        
                    </td>
                </tr>
                <tr>
                    <td>
                        Capital :
                    </td>
                    <td>
                        <html:text name="LoginForm" property="capital" />
                    </td>
                </tr>                
                <tr>
                    <td>
                        Email :
                    </td>
                    <td>
                        <html:text name="LoginForm" property="email" />
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