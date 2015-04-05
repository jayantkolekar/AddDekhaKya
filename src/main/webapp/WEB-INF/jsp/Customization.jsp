<%-- 
    Document   : Customization
    Created on : Apr 5, 2014, 4:26:16 PM
    Author     : Jayant Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Test Solutions</title>
        <link rel="stylesheet" type="text/css" href="style/style.css"/>
        <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="js/CalenderDateInput.js" ></script>
        <script type="text/javascript" src="js/Common.js" ></script>
        <script type="text/javascript" src="js/json2.js" ></script>
        <script type="text/javascript">
            $('#backButton').live('click',function(){
                location.href = 'admin.htm';
                return false;
            });
        </script>
    </head>
    <body>
        <form method="POST" action="saveSystemConfiguration.htm">
        <table class="mainRegistrationTable">
            <c:forEach var="systemConfiguration" items="${SystemConfigurationList}">
            <tr>
                <td class="labelClass">${systemConfiguration.systemConfiguration}<font color="red">*</font>:</td>
                <td class="valueClass">
                    <select id="systemConfiguration_${systemConfiguration.systemConfigurationId}" name="systemConfiguration_${systemConfiguration.systemConfigurationId}">
                        <c:choose>
                            <c:when test="${systemConfiguration.systemConfigurationId eq '1000'}">
                                <c:forEach var="noOfQuestionsPerpage" items="${NoOfQuestionsPerpage}" >
                                    <c:choose>
                                        <c:when test="${systemConfiguration.systemConfigurationValue eq noOfQuestionsPerpage}">
                                            <option value="${noOfQuestionsPerpage}" selected="selected"> 
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${noOfQuestionsPerpage}">
                                        </c:otherwise>
                                     </c:choose>
                                        ${noOfQuestionsPerpage}
                                    </option>
                                </c:forEach>
                            </c:when>
                            <c:when test="${systemConfiguration.systemConfigurationId eq '1001' or systemConfiguration.systemConfigurationId eq '1002'}">
                                <c:forEach var="totalQuestionsAndTime" items="${TotalQuestionsAndTime}" >
                                    <c:choose>
                                        <c:when test="${systemConfiguration.systemConfigurationValue eq totalQuestionsAndTime}">
                                            <option value="${totalQuestionsAndTime}" selected="selected"> 
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${totalQuestionsAndTime}">
                                        </c:otherwise>
                                     </c:choose>
                                        ${totalQuestionsAndTime}
                                    </option>
                                </c:forEach>
                                </c:when>
                            </c:choose>
                        </select> 
                    </td>
                </tr>
            </c:forEach>
        </table>
                    
            <center>
                <input type="submit" id="saveSystemConfiguration" value="Save" class="buttonClass">
                <input type="button" id="backButton" value="Back" class="buttonClass">
            </center>
        </form>
    </body>
</html>
