<%-- 
    Document   : ThankYou
    Created on : 4 Dec, 2012, 4:09:37 PM
    Author     : Jayant.Kolekar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Test Solutions</title>
        
        <link rel="stylesheet" type="text/css" href="style/style.css"/>
    </head>
    <body>
        <center>
            <img  src="images/fundtechLogo.gif" />
            <br/>
            <br/>            
            <br/>

            <c:if test="${ErrorMessage != null}" >
                <div class="errorMessage">
                    ${ErrorMessage}
                </div>

            </c:if>

             <c:if test="${InformationalMessage != null}" >
                <div class="informationMessage">
                    ${InformationalMessage}
                </div>

            </c:if>
            
                <H1 class="fontColor">
                    Thank you for completing the test.
                </H1>
        </center>
        <div align="right" >
            <a href="index.htm" style="padding-right:50px;"> Home </a>
        </div>
    </body>
</html>
