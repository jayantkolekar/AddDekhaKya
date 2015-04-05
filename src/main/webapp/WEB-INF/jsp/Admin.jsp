<%-- 
    Document   : Admin
    Created on : 14 Nov, 2012, 11:55:34 AM
    Author     : Jayant.Kolekar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>India Limited</title>
        
        <link rel="stylesheet" type="text/css" href="style/style.css"/>
        <link rel="stylesheet" href="style/amchart.css" type="text/css">
        <link rel="stylesheet" href="style/jquery-ui-1.10.4.css" type="text/css">
        
         <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
         <script type="text/javascript" src="js/jquery-ui-1.10.4.js"></script>
         <script type="text/javascript" src="js/json2.js" ></script>
        <script type="text/javascript" src="js/jquery.blockUI.js" ></script>
        <script src="js/amcharts.js" type="text/javascript"></script>
        <script src="js/pie.js" type="text/javascript"></script>
         <script type="text/javascript" src="js/Admin.js" ></script>
        
         
         <script type="text/javascript" >
            $(document).ready(function(){
                $('#fetch').click(function(){
                    Admin.fetchUserList('fetch');
                }); 
               
                $('#qualifyingRange').change(function() {
                    Admin.changeQualifyingMarks();
                });
                
                $('#tableToExcel').click(function(){
                    Admin.fetchUserList('export');
                    return false;
                });
                
                $('#customizeTest').click(function(){
                    Admin.customizeTest('export');
                    return false;
                });
                
                $('img#deleteUser').live('click', function(){
                    var retVal = confirm("Do you really want to delete the user?");
                    if(retVal == true){
                        var userId = $(this).parent('td.deleteRow').find('#userId').val();
                        Admin.deleteUser(userId);
                    }
                    else{
//                      Do Nothing....  
                    }
                });
                
                $('#pieChart').live('click', function(){
                     var userId = $(this).parent('td.pieChart').find('#userId').val();
                     Admin.showPieChart(userId);
                });
            });
            
            $(function() {
                $( "#fromDate" ).datepicker({
                  changeMonth: true,
                  changeYear: true,
                  dateFormat: "dd-mm-yy"
                });
                $("#fromDate").datepicker("setDate", new Date());
           });
           
           $(function() {
                $( "#toDate" ).datepicker({
                  changeMonth: true,
                  changeYear: true,
                  dateFormat: "dd-mm-yy"
                });
                $("#toDate").datepicker("setDate", new Date());
           });
            
         </script>
         
    </head>
    
    <body>
         <center>
             <img  src="images/fundtechLogo.gif" />
        </center>
        <c:if test="${InformationalMessage != null}" >
            <div class="informationMessage">
                ${InformationalMessage}
            </div>

        </c:if>
        <c:if test="${UserDetailsList eq null or fn:length(UserDetailsList) eq 0}">
            <div class="errorMessage">
                No user has appeared for the test on the given date.
            </div>
        </c:if>
        <table>
            <tr>
                <td>
                    From Date: <input type="text" value="" id="fromDate" />
                </td>
            </tr>
            <tr>
                <td>
                    To Date: <input type="text" value="" class="ToDate" id="toDate" />
                </td>
            </tr>
            <tr>
                <td style="text-align: right;padding-right: 50px;">
                    <input type="button" value="Fetch" id="fetch" class="buttonClass"/>
                </td>
            </tr>
            <tr>
                <td>
                    Select the qualifying marks range: 
                </td>
                <td>
                    <select id="qualifyingRange" name="qualifyingRange" >
                        <option value="20"> 20 </option>
                        <option value="21"> 21 </option>
                        <option value="22"> 22 </option>
                        <option value="23" selected="selected"> 23 </option>
                        <option value="24"> 24 </option>
                        <option value="25"> 25 </option>
                        <option value="26"> 26 </option>
                        <option value="27"> 27 </option>
                        <option value="28"> 28 </option>
                        <option value="29"> 29 </option>
                        <option value="30"> 30 </option>
                    </select>
                    
                </td>
            </tr>
        </table>
    <div style="text-align: right;">
        <a href="displayAddQuestion.htm" style="padding-top: 5px; padding-right: 245px;">
             Add New Question
        </a>
    </div>
    <c:if test="${UserDetailsList != null && fn:length(UserDetailsList) > 0}">
        <h2 class="fontColor">User List With Marks Obtained</h2> 
        <div>
            <font color="red">
                *The proficiency level in the table is the proficiency level of the last question answered by the candidate.
            </font>
            <table cellpadding="4" class="ratingTable" id="ratingTable">
                <tr class="headerClass">
                    <th class="srNo ">
                        Sr. No.
                    </th>
                    <th class="userName ">
                        Rating
                    </th>  
                    <th class="emailAddress ">
                        Percentage Range
                    </th>   
                </tr>
                <tr class="oddClass">
                    <td class="srNo ">
                        1
                    </td>
                    <td class="Rating ">
                        A+
                    </td>  
                    <td class="marksRange ">
                        70+
                    </td>   
                </tr>
                <tr class="evenClass">
                    <td class="srNo ">
                        2
                    </td>
                    <td class="Rating ">
                        A
                    </td>  
                    <td class="marksRange ">
                        60-70
                    </td>   
                </tr>
                <tr class="oddClass">
                    <td class="srNo ">
                        3
                    </td>
                    <td class="Rating ">
                        B+
                    </td>  
                    <td class="marksRange ">
                        50-60
                    </td>   
                </tr>
                <tr class="evenClass">
                    <td class="srNo ">
                        4
                    </td>
                    <td class="Rating ">
                        B
                    </td>  
                    <td class="marksRange ">
                        40-50
                    </td>   
                </tr>
                <tr class="oddClass">
                    <td class="srNo ">
                        5
                    </td>
                    <td class="Rating ">
                        C+
                    </td>  
                    <td class="marksRange ">
                        30-40
                    </td>   
                </tr>
                <tr class="evenClass">
                    <td class="srNo ">
                        6
                    </td>
                    <td class="Rating ">
                        C
                    </td>  
                    <td class="marksRange ">
                        20-30
                    </td>   
                </tr>
                <tr class="oddClass">
                    <td class="srNo ">
                        7
                    </td>
                    <td class="Rating ">
                        F
                    </td>  
                    <td class="marksRange ">
                        Below 20
                    </td>   
                </tr>
            </table>
            
        </div>
            <table cellpadding="4" class="adminTable" id="adminTable">
                <tr class="headerClass">
                    <th class="srNo ">
                        Sr. No.
                    </th>
                    <th class="userName">
                        User Name
                    </th>  
                    <th class="emailAddress">
                        Email Address
                    </th>   
                    <th class="phoneNumber">
                        Phone Number
                    </th>  
                     <th class="modifiedDate">
                        Date
                    </th>  
                    <th class="marksObtained">
                        Marks Obtained
                    </th>
                    <th class="totalMarks">
                        Total Marks
                    </th>
                    <th class="primarySkilSet">
                        Primary Skill Set
                    </th>
                    <th class="proficiencyLevel">
                        Proficiency Level
                    </th>
                    <th class="candidateRating">
                        Candidate Rating
                    </th>
                    <th class="passResult">
                        Pass Result
                    </th>
                    <th class="pieChart">
                        Pie Chart
                    </th>
                    <th class="deleteRow">
                        Delete
                    </th>
                </tr>
                <c:set var="rowCount" value="1" />
                <c:forEach items="${UserDetailsList}" var="user" >
                    <c:if test="${rowCount%2 eq 0}" >
                        <tr class="evenClass">
                    </c:if>
                    
                    <c:if test="${rowCount%2 eq 1}" >
                        <tr class="oddClass">
                    </c:if>
                        <td class="srNo">
                            ${rowCount}.
                        </td>
                        
                        <td class="userName">
                            ${user.firstName} ${user.lastName}
                        </td>
                        
                        <td class="emailAddress">
                            ${user.emailId}
                        </td>
                        
                        <td class="phoneNumber">
                            ${user.phoneNumber}
                        </td>
                         <td class="modifiedDate">
                            ${user.modifiedDate}
                        </td>
                        <td class="marksObtained">
                            ${user.marksObtained}
                        </td>
                        
                        
                        <td class="totalMarks">
                            <c:choose>
                                <c:when test="${user.totalMarks ne null}">
                                    ${user.totalMarks}
                                </c:when>
                                <c:otherwise>
                                    45
                                </c:otherwise>
                                    
                            </c:choose>
                            
                        </td>
                        
                        <td class="primarySkilSet">
                            <c:choose>
                                <c:when test="${user.primarySkill eq 1}">
                                    Java
                                </c:when>
                                <c:when test="${user.primarySkill eq 2}">
                                    Oracle
                                </c:when>
                                <c:when test="${user.primarySkill eq 3}">
                                    QA
                                </c:when>
                                <c:otherwise>
                                    General
                                </c:otherwise>
                            </c:choose>
                        </td>
                        
                        <td class="proficiencyLevel">
                            <c:if test="${user.proficiencyLevel eq 1}">
                                Simple
                            </c:if>
                                
                            <c:if test="${user.proficiencyLevel eq 2}">
                                Medium
                            </c:if>
                                
                            <c:if test="${user.proficiencyLevel eq 3}">
                                Complex
                            </c:if>
                        </td>
                        
                        <td class="candidateRating">
                            ${user.candidateRating}
                        </td>
                        
                        <td class="passResult">
                            <c:if test="${user.marksObtained >= 23}">
                                Qualified.
                            </c:if>
                            <c:if test="${user.marksObtained < 23}">
                                Not Qualified.
                            </c:if>    
                        </td>
                        
                        <td class="pieChart">
                            <c:if test="${user.marksObtained ne null and user.marksObtained > 0}">
                                <a href="#" id="pieChart">
                                    Pie Chart
                                </a>
                            </c:if>
                            <input type="hidden" id="userId" value="${user.userDetailsPK.userId}"/>
                        </td>
                        
                        <td class="deleteRow">
                            <input type="hidden" value="${user.userDetailsPK.userId}" id="userId" name="userId" />
                            <img  src="images/close.png" id="deleteUser" />
                            
                        </td>
                        
                    </tr>
                    <c:set var="rowCount" value="${rowCount + 1}" />
                </c:forEach>

            </table>
           
          
           
       </c:if>
            <center>
                <a href="index.htm" style="padding-top: 5px; padding-right: 5px;">
                    Home
                </a>
                <a href="#" onClick="window.print();" style="padding-top: 5px; padding-right: 5px;">
                    Print
                </a>
                <a href="#" id="tableToExcel" style="padding-top: 5px;">
                    Export
                </a>
                <a href="customizeSystemConfiguration.htm" id="customizeTest" style="padding-top: 5px;">
                    Customize Test
                </a>
                <input type="hidden" id="fromDate" value="${FromDate}"/>
                <input type="hidden" id="toDate" value="${ToDate}"/>
            </center>
            <div id="dialog-modal" title="Online Test Solutions">
                <div id="chartdiv" style="width: 100%; height: 400px;"></div>
            </div>
    </body>
</html>
