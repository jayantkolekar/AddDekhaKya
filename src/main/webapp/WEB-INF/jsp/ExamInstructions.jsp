<%-- 
    Document   : ExamInstructions
    Created on : 22 Nov, 2012, 4:10:42 PM
    Author     : Jayant.Kolekar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Test Solutions</title>
        <link rel="stylesheet" type="text/css" href="style/style.css"/>
        <script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
        <script type="text/javascript" src="js/json2.js" ></script>
        <script type="text/javascript" src="js/jquery.blockUI.js" ></script>
        <script type="text/javascript" src="js/taffy.js" ></script>
        <script type="text/javascript" src="js/OnlineExam.js" ></script>
        
        <script type="text/javascript" >
            $(document).ready(function(){
                $('#timerClock').hide();
                $('#startTest').click(function(){
                    OnlineExam.startTest('${UserId}',1);
                    return false;
                });
            });
            
            /**
            * Bind Click even to both submit test and next button.
            */
           $('#submitTest').live('click',function(){
               var retVal = confirm("Do you really want to submit the test?");
               if(retVal == true){
                    OnlineExam.calculateResult(this);
               }
               return false;
           });
           $('#nextPage').live('click',function(){
               OnlineExam.calculateResult(this);
               return false;
           });
           $('#previousPage').live('click',function(){
               OnlineExam.displayPreviousPageData(this);
               return false;
           });
           
        </script>
    </head>
    <body>
        <center>
             <img  src="images/fundtechLogo.gif" />
        </center>
        <div id="examInstructions" class="examInstructions">
            1. Click the 'Submit' button given in the bottom page to Submit your answers.
            2. Test will be submitted automatically if the time expired.
            3. Do not refresh or close the page.
            4. Do not press back button of keyboard or browser.
        </div>
        <div id="timerClock"  class="timerClock" >
                <input id="timerCount" name="timerCount" type="hidden" value="${TimerCount}" />
                 Time Left <span id="hours"></span>:<span id="minutes"></span>:<span id="seconds"></span>
        </div>
         <form action="submitTest.htm" method="post" >
            <H3 class="fontColor"> Exam Instructions </H3>
            <table cellpadding="4" cellspacing="5">
                <tr>
                    <td>
                        1. All the questions have equal weightage.
                    </td>
                </tr>
                <tr>
                    <td>
                        2. Time alloted : 45 minutes.
                    </td>
                </tr>
                <tr>
                    <td>
                        3. Some questions could have multiple correct answers.
                    </td>
                </tr>
            </table>
            <H3 class="fontColor"> Note </H3>
            <table cellpadding="4" cellspacing="5">
                <tr>
                    <td>
                        1. Click the 'Submit' button given in the bottom page to Submit your answers.
                    </td>
                </tr>
                
                <tr>
                    <td>
                        2. Test will be submitted automatically if the time expired.
                    </td>
                </tr>
                
                <tr>
                    <td>
                        3. Do not refresh or close the page.
                    </td>
                </tr>
                
                <tr>
                    <td>
                        4. Do not press back button of keyboard or browser.
                    </td>
                </tr>
                
            </table>
            <center>
                <input type="button" id="startTest" value="Start Test" class="buttonClass">
            </center>
        </form>
    </body>
</html>