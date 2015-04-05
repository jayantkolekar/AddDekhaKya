<%-- 
    Document   : Login
    Created on : 18 Jul, 2013, 2:17:39 PM
    Author     : Jayant.Kolekar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Test Solutions</title>
    </head>
    <body>
        <center>
             <img  src="images/fundtechLogo.gif" />
        </center>
        <c:if test="${ErrorMessage != null}" >
            <div class="informationMessage">
                ${ErrorMessage}
            </div>

        </c:if>
            <center>
                <table cellpadding='2' cellspacing='0' border='0' id='ap_table'>
                    <tr>
                        <td bgcolor="blue">
                            <table cellpadding='0' cellspacing='0' border='0' width='100%'>
                                <tr>
                                    <td bgcolor="blue" align=center style="padding:2;padding-bottom:4">
                                            <font size=-1  color="white" face="verdana,arial">
                                                <b>
                                                    Enter your login and password
                                                </b>
                                            </font>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td bgcolor="white" style="padding:5">
                                        <br>
                                            <form id="loginFrm" method="POST" action="j_spring_security_check" name="loginFrm">
                            <p>
                                <label  for="userName">Username</label>
                                <input type="text" name="j_username" id="userName" tabindex="10">
                            </p>
                            
                            <p>
                                <label for="password">Password</label>
                                <input type="password" name="j_password" id="password" tabindex="20">
                            </p>
      
                            <a href="" class="fgtPass">Forgot your password?</a>
                              
                                <p>
                                    <input type="submit" name="loginBtn" id="loginBtn" value="Login" tabindex="30">
                                </p>
                        </form>
                                        </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </center>
    </body>
</html>
