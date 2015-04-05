<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

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
                    Welcome to the online exam
                </H1>    

        </center>
        <br/>
        <div align="right" >
            <a href="register.htm" id="registerUser" style="padding-right:50px;"> Register User </a>
        </div>
    </body>
</html>
