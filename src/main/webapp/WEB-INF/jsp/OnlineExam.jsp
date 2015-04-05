<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

        <c:if test="${QuestionList ne null}" >
            <div class="container" style="width: 100%">
                <c:if test="${questionCount eq null}"  >
                    <c:set value="0" var="questionCount" />
                </c:if>    
                <c:set value="${questionCount}" var="questionCount" />
              <c:forEach var="questionObject" items="${QuestionList}" varStatus="i" >
                  <c:set value="${questionCount + 1}" var="questionCount" />
                  <table class="entireQuestion" >
                      <tr>
                          <td>
                               <table>
                                   <tr class="questionRow">
                                       <td class="questionColumn questionNumber" id="questionNumber">
                                           <c:if test="${questionObject.questionCount ne null}">
                                               ${questionObject.questionCount}. 
                                           </c:if>     
                                           <c:if test="${questionObject.questionCount eq null}">
                                               ${questionCount}. 
                                           </c:if>     
                                       </td>
                                       <td class="questionColumn" id="questionStatement">
                                           ${fn:escapeXml(questionObject.question)}
                                       </td>
                                   </tr>
                                </table>
                          </td>
                      </tr>

                      <tr>
                           <td>
                               <table cellspacing="10" class="optionTable">
                                   <c:set value="0" var="optionCount" />
                                   <c:set value = "" var="optionVar" />
                                   <c:forEach items="${questionObject.optionList}" var="option" >
                                       <c:set value="${optionCount + 1}" var="optionCount" />
                                            <tr class="optionRow">
                                                <td class="optionColumn">
                                                   <c:choose>
                                                       <c:when test="${optionCount == 1}">
                                                           <c:set value="A" var="optionVar" />
                                                           A.
                                                       </c:when>
                                                       <c:when test="${optionCount == 2}">
                                                           <c:set value="B" var="optionVar" />
                                                           B.
                                                       </c:when>  
                                                       <c:when test="${optionCount == 3}">
                                                           <c:set value="C" var="optionVar" />
                                                           C.
                                                       </c:when>
                                                       <c:when test="${optionCount == 4}">
                                                           <c:set value="D" var="optionVar" />
                                                           D.
                                                       </c:when>
                                                       <c:when test="${optionCount == 5}">
                                                           <c:set value="E" var="optionVar" />
                                                           E.
                                                       </c:when>       
                                                       <c:when test="${optionCount == 6}">
                                                           <c:set value="F" var="optionVar" />
                                                           F.
                                                       </c:when>      
                                                       <c:otherwise>
                                                           <c:set value="G" var="optionVar" />
                                                           G.
                                                       </c:otherwise>   
                                                   </c:choose>
                                               </td>
                                               <td class="optionColumn">
                                                   <c:set var="checked" value="" />
                                                   <c:if test="${fn:length(questionObject.correctOption) eq 1}" >
                                                       <c:if test="${questionObject.userAnswer ne null}" >
                                                           <c:if test="${questionObject.userAnswer eq optionVar}" >
                                                                <c:set var="checked" value="checked" />
                                                           </c:if>
                                                       </c:if>
                                                        <input type="radio" class="radioOption" value="${optionVar}" name="selectedOption_${questionCount}" ${checked} />
                                                        ${fn:escapeXml(option.questionOptions)}
                                                   </c:if>
                                                        <c:if test="${fn:length(questionObject.correctOption) > 1}" >
                                                            <c:if test="${questionObject.userAnswer ne null}" >
                                                                <c:if test="${fn:contains(questionObject.userAnswer, optionVar)}" >
                                                                    <c:set var="checked" value="checked" />
                                                                </c:if>
                                                            </c:if>
                                                            <input type="checkbox" class="radioOption" value="${optionVar}" name="selectedOption_${questionCount}" ${checked} />
                                                            ${fn:escapeXml(option.questionOptions)}
                                                        </c:if>     
                                                            <input type="hidden" class="optionData" id="${optionVar}" value="${fn:escapeXml(option.questionOptions)}"/>
                                                            
                                               </td>
                                            </tr>
                                   </c:forEach>
                               </table>
                           </td>
                      </tr>

                      <tr>
                          <td>
                              <input id="correctOption" type="hidden" value="${questionObject.correctOption}" />
                              <input id="questionId" name="questionId" type="hidden" value="${questionObject.questionId}" />
                              <input id="marks" name="marks" type="hidden" value="${questionObject.marks}" />
                              <input id="proficiencyLevel" name="proficiencyLevel" type="hidden" value="${questionObject.proficiencyLevel}" />
                          </td>
                      </tr>

                  </table>
                    <c:if test="${questionObject.questionCount ne null}">
                        <c:set value="${questionObject.questionCount}" var="questionCount" />
                    </c:if>   
              </c:forEach>
               <input id="inputString" name="inputString" type="hidden" value="" />
               <input id="questionCount" name="questionCount" type="hidden" value="${questionCount}" />
               <input type="hidden" value="${PageNo}" id="pageNo" />
               <input id="userId" name="userId" type="hidden" value="${UserId}" />
               <input id="finalProficiencyLevel" name="finalProficiencyLevel" type="hidden" value="" />
               <input id="timerCount" name="timerCount" type="hidden" value="${TimerCount}" />
           </div>
        </c:if>
        <c:if test="${QuestionList eq null or fn:length(QuestionList) eq 0}" >
             No Questions Available.....
         </c:if>    
        <center>
            <div style="padding-top: 10px;">
                <c:if test="${PageNo ne 1}">
                   <%--<a href="#" id="previousPage">
                        <img width="100" height="55" alt="<b>&lt;&lt; Prev</b>" src="images/Previous-Button.png" />
                    </a>--%>
                   <input type="button" id="previousPage" class="buttonClass" value="Previous" />
                </c:if>
                <c:if test="${PageNo ne TotalPages}">
                    <%--<a href="#" id="nextPage">
                        <img width="100" height="55" alt="<b>Next &gt;&gt;</b>" src="images/Next-Button.png" />
                    </a>--%>
                    <input type="button" id="nextPage" class="buttonClass" value="Next" />
                </c:if>
               <c:if test="${PageNo eq TotalPages}">
                    <%--<a href="#" id="submitTest">
                         <img width="100" height="55" alt="Submit" src="images/Submit-Button-Copy.png" />
                     </a>--%>
                    <input type="button" id="submitTest" class="buttonClass" value="Submit" />
               </c:if>
            </div>        
        </center>
   
