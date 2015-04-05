/**
 * This file consists of common methods which can be used for different 
 * modules of the project.
 */
var Common = new function(){
    
    /**
     * This method is used to check whether phone number is valid.
     */
    this.isPhoneValid = function(phoneNumber){
        var intRegex = /^\d+$/;
        if(phoneNumber.length == 10){
            return intRegex.test(phoneNumber);
        }
        else{
            return false;
        }
    };
    
    /**
     * This method is used to check whether email address is valid.
     */
    this.isEmailIdValid = function(emailAddress){
        var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
        return emailReg.test(emailAddress);
    };
    
    /**
     * This method is used to check whether correct options entered is valid.
     */
    this.isCorrectOptionValid = function(correctOption){
        var correctOptionString = '';
        if(correctOption.length > 1 && correctOption.indexOf(',') != -1){
            var correctOptionArray = correctOption.split(',');
            for(var i=0;i<correctOptionArray.length;i++){
                if(correctOptionArray[i] != null && correctOptionArray[i] != ''){
                    correctOptionString += correctOptionArray[i].trim() + ',';
                }
            }
            if(correctOptionString != ''){
               correctOptionString =  correctOptionString.substring(0,correctOptionString.length - 1);
               $('#correctOption').val(correctOptionString);
            }
            return true;
        }
        else{
            //if(correctOption < 1 || correctOption > 7){
                //return false;
            //}
           // else{
                return true;
           // }
        }
    };
    
}();
