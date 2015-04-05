<%-- 
    Document   : NewQuestionaire
    Created on : 22 Nov, 2012, 10:42:53 AM
    Author     : Jayant.Kolekar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Test Solutions</title>
        <link rel="stylesheet" type="text/css" href="style/style.css"/>
        
        <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="js/Common.js" ></script>
        
        <script type="text/javascript" src="js/ajaxfileupload.js"></script>
	<script type="text/javascript">
	
            $(document).ready(function(){
                    $("#addNewQuestion").click(function(){
                        var displayMessage = false;
                        if($('#questionDetails').html() == null || $('#questionDetails').val() == ''){
                            alert('Question field is a mandatory field. Please enter appropriate question. ');
                        }
                        var optionCount = 0;
                        $('input.optionName').each(function(){
                            if(($(this).val() == null || $(this).val() == '') && $(this).attr('name') == 'option'){
                                optionCount++;
                                displayMessage = true;
                            }
                        })
                        var optionErrorPresent =  false;
                        if(optionCount > 5 && displayMessage){
                            alert('At least two options should be present. Please enter value of the option fields.');
                            optionErrorPresent = true;
                        }
                        if(($('#correctOption').val() == null || $('#correctOption').val() == '') && !optionErrorPresent){
                            alert('Correct Option field is a mandatory field. Please enter appropriate option number. ');
                        }
                        else{
                            if(!optionErrorPresent){
                                var correctOption = $('#correctOption').val();
                                if(Common.isCorrectOptionValid(correctOption)){
                                    $('form#addQuestion').submit();
                                    return false;
                                }
                                else{
                                    alert('Please enter valid correct option numbers. ');
                                }
                            }
                        }
                    });
                    
                    $('#backButton').click(function(){
                       location.href = 'admin.htm';
                       return false;
                    });
                    
                    $('#primarySkill').change(function() {
                        var id = $(this).val();
                        var secondarySkillHtml = $('td#primarySkill_'+id).html();
                        $('#secondarySkill').html(secondarySkillHtml);
                        return false;
                    });
                    
                     $('#importQuestions').click(function(){
                       location.href = 'admin.htm';
                       return false;
                    });
                    
    
            });
            
            function ajaxFileUpload(){
		
		$.ajaxFileUpload({
                    url:'uploadExcelToImport.htm',
                    secureuri:false,
                    fileElementId:'fileToUpload',
                    dataType: 'json',
                    data:{name:'logan', id:'id'},
                    success: function (data, status){
                        alert('in success');
                        if(typeof(data.error) != 'undefined'){
                            if(data.error != ''){
                                alert(data.error);
                            }
                            else{
                                alert(data.msg);
                            }
                        }
                    },
                    error: function (data, status, e){
                        alert(e);
                    }
            });
            return false;
	}
            
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
    
    <c:if test="${ErrorMessage != null}" >
                <div class="errorMessage">
                    ${ErrorMessage}
                </div>

    </c:if>
    
    <form:form action="addQuestion.htm" id="addQuestion" commandName="question" enctype="multipart/form-data" >
        <table cellpadding="4" cellspacing="5" style="width: 100%">
                
            <tr>
                <td>
            File1 to upload: <input type="file" name="file"><br /> 
                Name1: <input type="text" name="name"><br /> <br /> 
                
                </td>
        </tr>
                <tr>
                    <td class="formLabel">Question<font color="red">*</font>: </td>
                    <td><form:textarea path="question" id="questionDetails" rows="6"  /></td>
                </tr>
                <tr>
                    <td class="formLabel">Option A: </td>
                    <td><input type="text" name="option" class="optionName" /></td>
                </tr>
                <tr>
                    <td class="formLabel">Option B: </td>
                    <td><input type="text" name="option" class="optionName" /></td>
                </tr>
                <tr>
                    <td class="formLabel">Option C: </td>
                    <td><input type="text" name="option" class="optionName" /></td>
                </tr>
                <tr>
                    <td class="formLabel">Option D: </td>
                    <td><input type="text" name="option" class="optionName" /></td>
                </tr>
                <tr>
                    <td class="formLabel">Option E: </td>
                    <td><input type="text" name="option" class="optionName" /></td>
                </tr>
                <tr>
                    <td class="formLabel">Correct Option: </td>
                    <td><form:input path="correctOption" id="correctOption"  /> 
                        <br/>
                        <font color="red">Please add comma separated option numbers (without space),if multiple answers are correct.</font>
                    </td>    
                </tr>
            </table>
                    
            <center>
                <input type="button" id="addNewQuestion" value="Add" class="buttonClass">
                <input type="button" id="backButton" value="Back" class="buttonClass">
                
            </center>
            
        
    </form:form>
            
            <form method="post" action="uploadExcelToImport.htm" enctype="multipart/form-data">
                <br>
                <br>
                <center>
                    <table border="0">
                        <tr>
                            <td><input type="file" name="fileUpload" size="50" /></td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Upload" /></td>
                        </tr>
                    </table>
                </center>
        </form>
    </body>
</html>

