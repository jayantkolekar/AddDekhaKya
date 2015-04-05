var OnlineExam = new function(){
    this.examSet = new TAFFY();
    /**
     * This function is used for validating whether the answers for all the questions is submitted. 
     */
    this.calculateResult = function(varObject){
        var userId = $('#userId').val();
        var pageNo = $('#pageNo').val();
        if(varObject != null && $(varObject).attr('id') == 'nextPage'){
            if(OnlineExam.validatePage('nextPage')){
                var newPageNo = parseInt(pageNo) + 1;

                var pageEntriesIfExist = OnlineExam.examSet({"pageNo":"" + newPageNo + ""});
                if(pageEntriesIfExist.count() == 0 ){
                   OnlineExam.examSet({"pageNo":"" + pageNo + ""}).remove();
                   OnlineExam.storeCurrentPageDetails(pageNo);
                    var questionIdList = '';
                    OnlineExam.examSet().each(function (record,recordnumber) {
                        questionIdList += record["questionId"] + ',';
                    });
                    if(questionIdList != ''){
                        questionIdList = questionIdList.substring(0,questionIdList.length - 1);
                    }
                    var currentPageEntriesIfExist = OnlineExam.examSet({"pageNo":"" + pageNo + ""});
                    var currentPageEntries = '{"questionList":'+currentPageEntriesIfExist.stringify()+'}'; 
                    OnlineExam.startTest(userId,newPageNo,null,questionIdList,currentPageEntries);
                }
                else{
                    OnlineExam.examSet({"pageNo":"" + pageNo + ""}).remove();
                    OnlineExam.storeCurrentPageDetails(pageNo);
                    var pageEntryString = '{"questionList":'+pageEntriesIfExist.stringify()+'}'; 
                    OnlineExam.startTest(userId,newPageNo,pageEntryString,null,null);
                }
            }
            else{
                return false;
            }
        }
        else if((varObject != null && $(varObject).attr('id') == 'submitTest') || 
            (varObject != null && varObject == 'submitTest') || (varObject != null && varObject == 'noQuestions') ){
            if(varObject == 'submitTest' || OnlineExam.validatePage('submitTest')){
                var inputString = '';
                var finalProficiencyLevel = $("#proficiencyLevel").val();
                if(varObject != 'noQuestions'){
                    OnlineExam.storeCurrentPageDetails(pageNo);
                }
                inputString = '';
                OnlineExam.examSet().each(function (record,recordnumber) {
                    inputString += record["questionId"] + '##' + record["correctOption"] + '##' + record["userAnswer"] + '##' + record["marks"];
                    inputString += '~~';
                });
                if(inputString != ''){
                    $('#inputString').val(inputString);
                    $('#finalProficiencyLevel').val(finalProficiencyLevel);
                    $('form').submit();
                    return false;
                }
            }
        }
       return false;
    };
    
     /**
     * This function is used for displaying previous page data.
     * @param varObject: Click Event Object.
     */
    this.displayPreviousPageData = function(varObject){
        var pageNo = $('#pageNo').val();
        OnlineExam.examSet({"pageNo":"" + pageNo + ""}).remove();
        OnlineExam.storeCurrentPageDetails(pageNo,true);
        var userId = $('#userId').val();
        var prevPageNo = parseInt(pageNo) - 1;
        var previousPageEntries = OnlineExam.examSet({"pageNo":"" + prevPageNo + ""});
        var pageEntryString = '{"questionList":'+previousPageEntries.stringify()+'}'; 
        OnlineExam.startTest(userId,prevPageNo,pageEntryString,null,null);
        return false;
    };
    
    /**
     * This function is used for current page details in the Taffy DB.
     * @param currentPageNumber: Current Page Number.
     */
    this.storeCurrentPageDetails = function(currentPageNumber){
        var totalQuestionsCount = 0;
        var pageNo = currentPageNumber;
        OnlineExam.examSet({"pageNo":pageNo}).remove();
        var pattern = new RegExp("<br>", 'g');
        $("table.entireQuestion").each(function(){
            var userAnswerString = '';
            var questionId = $(this).find("#questionId").val();
            var questionStatement = $(this).find("#questionStatement").html();
            questionStatement = $.trim(questionStatement);
            questionStatement = questionStatement.replace(pattern, "~~N~~");
            var optionList = $(this).find(".optionTable");
            var checkedOption = $(this).find(".radioOption:checked");
            var correctOption = $(this).find("#correctOption").val();
            var questionCount = $(this).find("#questionNumber").html();
            var marks = $(this).find("#marks").val();
            var proficiencyLevel = $(this).find("#proficiencyLevel").val();
            var checkBoxIsPresent = false;
            questionCount = $.trim(questionCount);
            marks = $.trim(marks);
            if(correctOption.length > 1){
                var correctOptionArray = correctOption.split(',');
                checkBoxIsPresent = true;
                $(checkedOption).each(function(){
                    var value = $(this).val();
                    userAnswerString += $.trim(value) + ',';
                });
            }
            if(!checkBoxIsPresent){
                if(checkedOption.length == 0){
                    userAnswerString += '';
                }
                else{
                    var selectedOptionNumber = $(checkedOption).val();
                    userAnswerString += selectedOptionNumber;
                }
            }
            else{
                if(userAnswerString.indexOf(',') != -1){
                    userAnswerString = userAnswerString.substring(0,userAnswerString.length - 1);
                }
            }
            totalQuestionsCount++;
            var optionArray = new Array();
            $(optionList).find('.optionRow').each(function(){
                var optionStatement = $(this).find('.optionData').val();
                optionStatement = $.trim(optionStatement);
                optionStatement = optionStatement.replace(pattern, "~~N~~");
                var option = {"optionId":$(this).find('.optionData').attr('id'), "option":optionStatement};
                optionArray.push(option);
            });
            if(OnlineExam.examSet == null){
                OnlineExam.examSet = TAFFY([{"questionId":questionId,"pageNo":pageNo,"question":questionStatement,"optionList":optionArray,
                        "correctOption":correctOption,"userAnswer":userAnswerString,"questionCount":questionCount,
                        "marks":marks, "proficiencyLevel":proficiencyLevel}]);
            }
            else{
                
                OnlineExam.examSet.insert({"questionId":questionId,"pageNo":pageNo,"question":questionStatement,"optionList":optionArray,
                    "correctOption":correctOption,"userAnswer":userAnswerString,"questionCount":questionCount,
                    "marks":marks, "proficiencyLevel":proficiencyLevel});
            }
        });
    };
    
    
    
    /**
     * This functin is used for initializing the timer displayed on the secreen.
     */
    this.initializeTimer = function(hours,minutes, seconds){
//      Set 45 Mins Timer.  
        timer.init(hours,minutes,seconds);
    }

    /**
     *  Timer Plugin
     */
    var timer = {
        minutes :0,
        seconds : 0,
        elm :null,
        samay : null,
        sep : ':',
        init : function(h,m,s){
            h = parseInt(h,10);
            m = parseInt(m,10);
            s = parseInt(s,10);
            if(h < 0 || m < 0 || s <0 || isNaN(h) || isNaN(m) || isNaN(s)) { 
                alert('Invalid Values'); return; 
            }
            this.hours = h;
            this.minutes = m;
            this.seconds = s;
            timer.start();
        },
        start : function(){
            this.samay = setInterval((this.doCountDown),1000);
        },
        doCountDown : function(){
            if(timer.seconds == 0){
                if(timer.minutes == 0){
                    if(timer.hours == 0){
                        clearInterval(timer.samay);
                        OnlineExam.timerComplete();
                        return;
                    }
                    else{
                         timer.seconds=60;
                         timer.minutes=59;
                         timer.hours--;
                    }
                }
                else{
                    timer.seconds=60;
                    timer.minutes--;
                }
            }
            timer.seconds--;
            timer.updateTimer(timer.hours,timer.minutes,timer.seconds);
        },
        updateTimer :  function(hr,min,secs){
            hr = (hr < 10 ? '0'+hr : hr);
            min = (min < 10 ? '0'+min : min);
            secs = (secs < 10 ? '0'+secs : secs);
            if(hr<=0&&min<=0&&secs<=0){
                 $('#hours').html("00");
                $('#minutes').html("00");
                $('#seconds').html("00");
                return;
            }
            else {
                $('#hours').html(hr);
                $('#minutes').html(min);
                $('#seconds').html(secs);
            }
        }
   }


    /**
     * This function is called only when the total time is complete.
     */
    this.timerComplete = function (){
        OnlineExam.calculateResult('submitTest');
    };
    
    /**
     * This function is used for retrieving exam page for the corresponding user.
     * @param userId: User ID.
     * @param pageNo: Page Number. 
     * @param pageEntries: Existing Page List.
     * @param questionIdList: Question ID String List.
     * @param currentPageEntries: Current Page Details.
     */
    this.startTest = function(userId,pageNo,pageEntries,questionIdList,currentPageEntries){
        $.blockUI({ css: { 
            border: 'none', 
            padding: '15px', 
            backgroundColor: '#000', 
            '-webkit-border-radius': '10px', 
            '-moz-border-radius': '10px', 
            opacity: .5, 
            color: '#fff' 
        } }); 
        var questionCount = $('#questionCount').val();
        var request = $.ajax({
            url: "startTest.htm",
            type: "POST",
            data: {'userId' : userId, 'pageNo' : pageNo, 'questionCount' : questionCount, 'pageEntriesToShow' : pageEntries, 
                'questionIdList' : questionIdList,'pageEntriesToEvaluate' : currentPageEntries },
            dataType: "html"
        });
        
        request.done(function(varData) {
            $.unblockUI();
            if($('#examInstructions').css('display') == 'none'){
                $('#examInstructions').show();
            }
            if(varData.indexOf('No Questions Available.....') != -1){
                OnlineExam.calculateResult('noQuestions');
            }
            else{
                var pattern = new RegExp("~~N~~", 'g');
                $("form").html('');
                
                $("form").html(varData.replace(pattern,"<br>"));
                if($('#timerClock').is(":hidden") ){
                    var timerCount = $('#timerCount').val();
                    OnlineExam.initializeTimer(0,parseInt(timerCount),0);
                    $('#timerClock').show();
                }
                window.scroll(0, 0);
            }
        });

        request.fail(function(jqXHR, textStatus) {
            $.unblockUI();
            alert( "Request failed: " + textStatus );
        });
        return false;
    };

    /**
     * This function is used for validating the current page.
     */
    this.validatePage = function(varButtonType){
        var isError = false;
        $("table.entireQuestion").each(function(){
            var questionId = $(this).find("#questionId").val();
            var checkedOption = $(this).find(".radioOption:checked");
            
            if(checkedOption.length == 0){
                isError = true;
            }
        });
        
        /*if(isError){
            if(varButtonType == 'nextPage'){
                alert('Please answer all the questions before proceding to the next page');
            }
            else{
                alert('Please answer all the questions before submitting the test.');
            }
            return false;
        }*/
        return true;
    };
    
}();