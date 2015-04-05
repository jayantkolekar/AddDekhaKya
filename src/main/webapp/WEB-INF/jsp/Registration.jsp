<%-- 
    Document   : Registration
    Created on : 6 Nov, 2012, 3:54:51 PM
    Author     : Jayant.Kolekar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Test Solutions</title>
        <link rel="stylesheet" type="text/css" href="style/style.css"/>
        <link rel="stylesheet" href="style/jquery-ui-1.10.4.css" type="text/css">
        
        <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.10.4.js"></script>
        <script type="text/javascript" src="js/CalenderDateInput.js" ></script>
        <script type="text/javascript" src="js/Common.js" ></script>
        <script type="text/javascript" src="js/json2.js" ></script>
        <script type="text/javascript" >
            $(document).ready(function(){
                $("#registerNewUser").click(function(){
                    var displayMessage = false;
                    $('input:text').each(function(){
                        if($(this).val() == null || $(this).val() == ''){
                            displayMessage = true;
                        }
                    })
                    var secondarySkill = $('#secondarySkill').val();
                    if(secondarySkill == null || secondarySkill == '' || secondarySkill.lenght == 0){
                        displayMessage = true;
                    }
                    if(displayMessage){
                        alert('All fields are mandatory. Please enter value of all the fields.');
                    }
                    else{
                        var emailAddress = $('#email').val();
                        var isEmailValid = Common.isEmailIdValid(emailAddress);
                        if(!isEmailValid){
                            alert("Please enter valid Email Address.");
                        }
                        else{
                            var phoneNumber = $('#phone').val();
                            var isPhoneValid = Common.isPhoneValid(phoneNumber);
                            if(!isPhoneValid){
                                alert("Please enter valid 10 digit mobile number.");
                            }
                            else{
                                //var dateValue = $('input[name="orderdate"]').val();
                                //var dateValueArray = dateValue.split('-');
                                //var newDateValue = dateValueArray[0] + '-' + dateValueArray[1] + '-' + $('#orderdate_Year_ID').val();
                                //$('input[name="orderdate"]').val(newDateValue);
                                $('form').submit();
                                return false;
                            }
                        }
                    }
                });
                
                $('#primarySkill').change(function() {
                    var id = $(this).val();
                    var secondarySkillHtml = '';
                    $('td#primarySkill_'+id).find('div').each(function(){
                        secondarySkillHtml += '<option value='+$(this).attr('id')+'>';
                        secondarySkillHtml += $(this).html().trim();
                        secondarySkillHtml += '</option>';
                    });
                    $('#secondarySkill').html(secondarySkillHtml);
                    return false;
                });
            });
            $(function() {
                $( "#dateOfBirth" ).datepicker({
                  changeMonth: true,
                  changeYear: true
                });
                $("#dateOfBirth").datepicker("setDate", new Date());
           });
        </script>
    </head>
    <body>
        <center>
            <img  src="images/fundtechLogo.gif" /> 
        </center>
        
        <%--<form:form method="POST" action="registerNewUser.htm"  commandName="userDetails">
            <table class="mainRegistrationTable">
                <tr>
                    <td>
                        <table cellpadding="4" cellspacing="5" class="subRegistrationFillInformation">
                            <tr>
                                <td class="labelClass">First Name<font color="red">*</font>:</td>
                                <td class="valueClass"><form:input path="firstName" /></td>
                            </tr>
                            <tr>
                                <td class="labelClass">Last Name<font color="red">*</font>:</td>
                                <td class="valueClass"><form:input path="lastName" /></td>
                            </tr>
                            <tr>
                                <td class="labelClass">Birth Date<font color="red">*</font>:</td>
                                <td class="valueClass">
                                    <form:input path="dateOfBirth" id="dateOfBirth" />
                                </td>
                            </tr>
                            <tr>
                                <td class="labelClass">Email<font color="red">*</font>:</td>
                                <td class="valueClass">
                                    <form:input path="emailId" id="email" />
                                </td>
                            </tr>
                            <tr>
                                <td class="labelClass">Mobile Number<font color="red">*</font>:</td>
                                <td class="valueClass">
                                    <form:input path="phoneNumber" id="phone"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="labelClass">Experience Level<font color="red">*</font>:</td>
                                <td class="valueClass">
                                    <form:select id="experienceLevel" path="experienceLevel" >
                                        <c:forEach items="${ExperienceLevelList}" var="experienceLevel" >
                                            <option value="${experienceLevel.experienceLevelPK.experienceLevelCode}" > 
                                                ${experienceLevel.experienceLevelPK.experienceLevel} (${experienceLevel.comments}) 
                                            </option>
                                        </c:forEach>
                                    </form:select> 
                                </td>
                            </tr>
                            
                            <tr>
                                <td class="formLabel">Primary Skill: </td>
                                <td>
                                    <c:set var="SecondarySkillList" value=""/>
                                    <form:select id="primarySkill" path="primarySkill"  >
                                        <c:forEach items="${PrimarySkillList}" var="primarySkill" >
                                            <c:if test="${primarySkill.primarySkillId eq 1}" >
                                                <c:set  var="SecondarySkillList" value="${primarySkill.secondarySkillList}"/>
                                            </c:if>
                                            <option  value="${primarySkill.primarySkillId}"  > 
                                                ${primarySkill.primarySkillName} 
                                            </option>
                                        </c:forEach>
                                    </form:select> 
                                </td>

                            </tr>
                            
                            <tr>
                                <td class="formLabel">Secondary Skill: </td>
                                <td>
                                    <select id="secondarySkill" name="secondarySkill" multiple="multiple" >
                                        <c:forEach items="${SecondarySkillList}" var="secondarySkill" >
                                            <option value="${secondarySkill.secondarySkillsPK.secondarySkillId}" > 
                                                ${secondarySkill.secondarySkillName} 
                                            </option>
                                        </c:forEach>
                                    </select> 
                                    <br/>
                                    <font color="red">Candidate can select multiple options.</font>
                                </td>

                            </tr>
                            
                            <tr>
                                <c:forEach items="${PrimarySkillList}" var="primarySkill" >
                                     <td style="visibility: hidden" id="primarySkill_${primarySkill.primarySkillId}">
                                        <c:forEach items="${primarySkill.secondarySkillList}" var="secondarySkill" >
                                                <div id="${secondarySkill.secondarySkillsPK.secondarySkillId}" > 
                                                    ${secondarySkill.secondarySkillName} 
                                                </div>
                                         </c:forEach>
                                      </td>
                                </c:forEach>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
                    
            <center>
                <input type="button" id="registerNewUser" value="Register" class="buttonClass">
            </center>
        </form:form>--%>
        
        <FORM ENCTYPE="multipart/form-data" method="POST" 
        action="registerNewUser.htm">
        Video name: <INPUT TYPE="text" NAME="name" value="video name"/><br/>
        Description: <INPUT TYPE="text" NAME="desc" value="description"/><br/>
        File:	<INPUT TYPE="file" NAME="file" />
        <INPUT TYPE="submit" VALUE="Upload"/>
      </FORM>
        
        
        
    </body>
</html>
