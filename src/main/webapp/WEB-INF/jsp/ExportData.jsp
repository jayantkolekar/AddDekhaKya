<%-- 
    Document   : ExportData
    Created on : 4 Dec, 2012, 2:41:48 PM
    Author     : Jayant.Kolekar
--%>

<%@page contentType="application/vnd.ms-excel"  pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<table cellpadding="4" cellspacing="5" class="adminTable" border="5" id="adminTable">
                <tr>
                    <th class="srNo fontColor">
                        Sr. No.
                    </th>
                    <th class="userName fontColor">
                        User Name
                    </th>  
                    <th class="emailAddress fontColor">
                        Email Address
                    </th>   
                    <th class="phoneNumber fontColor">
                        Phone Number
                    </th>  
                     <th class="modifiedDate fontColor">
                        Date
                    </th>  
                    <th class="marksObtained fontColor">
                        Marks Obtained
                    </th>
                    <th class="totalMarks fontColor">
                        Total Marks
                    </th>
                    <th class="proficiencyLevel fontColor">
                        Proficiency Level
                    </th>
                    <th class="candidateRating fontColor">
                        Candidate Rating
                    </th>
                    <th class="passResult fontColor">
                        Pass Result
                    </th>
                </tr>
                <c:set var="rowCount" value="1" />
                <c:forEach items="${UserDetailsList}" var="user" >
                    <tr>
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
                            ${user.totalMarks}
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
                        
                    </tr>
                    <c:set var="rowCount" value="${rowCount + 1}" />
                </c:forEach>

            </table>