var Admin = new function(){
    
    /**
     *  This method is used for retrieving the user list for the corresponding date selected.
     */
    this.fetchUserList = function(varData){
        var fromMonth =  '';
        var fromNumberMonth = '';
        var fromDay =  '';
        var fromYear =  '';
        var toMonth =  '';
        var toNumberMonth =  '';
        var toDay =  '';
        var toYear =''
        var fromDateString = $('#fromDate').val();
        var toDateString = $('#toDate').val();
        
        var fromDateStr = ''; 
        var toDateStr = ''
            
        if(varData != null && varData == 'export' && 
            (fromDateString != null && fromDateString != '') && 
                (toDateString != null && toDateString != '')){
            fromDateStr = fromDateString; 
            toDateStr = toDateString; 
        }
        else{
            fromDateStr = fromDateString; 
            toDateStr = toDateString;
        }
        
        var fromDate = new Date(fromYear,fromNumberMonth,fromDay); 
        var toDate = new Date(toYear,toNumberMonth,toDay); 
        if(toDate < fromDate){
            alert('To Date Should alway be greater than From Date');

        }
        else{
            location.href = 'admin.htm?fromDate='+fromDateStr+'&toDate='+toDateStr+'&fromWhere='+varData;
        }  
        return false;
    };
    
    /**
     * This method is used for displaying pass result for the corresponding qualifying value.
     */
    this.changeQualifyingMarks = function(){
        var qualifyingValue = $('#qualifyingRange option:selected').val();
        qualifyingValue = qualifyingValue.trim();
        $(".adminTable tr").each(function(){
            var marksObtained = $(this).find("td.marksObtained").html();
            var qualifyingStatement = '';
            if(marksObtained != null && marksObtained != ''){
                marksObtained = marksObtained.trim();
            }
            if(marksObtained != null && marksObtained != '' && marksObtained != 'undefined'){
                if(parseInt(marksObtained) >= parseInt(qualifyingValue)){
                      qualifyingStatement = 'Qualified.';
                 }
                 else{
                      qualifyingStatement = 'Not Qualified.';
                 }
            }
            $(this).find('td.passResult').html(qualifyingStatement);

        });
        return false;
    };
    
    /**
     * This method is used for deleting a user.
     * userId: UserId to delete. 
     */
    this.deleteUser = function(userId){
        if(userId != null && userId != ''){
            $.blockUI({ css: { 
                border: 'none', 
                padding: '15px', 
                backgroundColor: '#000', 
                '-webkit-border-radius': '10px', 
                '-moz-border-radius': '10px', 
                opacity: .5, 
                color: '#fff' 
            } }); 
        
            var request = $.ajax({
                url: "deleteUser.htm",
                type: "POST",
                data: {'userId' : userId},
                dataType: "html"
            });
        
            request.done(function(varData) {
                $.unblockUI();
                if(varData.indexOf('Success') != -1){
                    Admin.fetchUserList('deleteUser');
                }
                else{
                    alert('Error while deleting the user. Please contact administrator.');
                }
            });

            request.fail(function(jqXHR, textStatus) {
                $.unblockUI();
                alert( "Request failed: " + textStatus );
            });
        }
        return false;
    };
    
    /**
     * This method is used for dispaying pie chart.
     * userId: UserId to displaay pie chart. 
     */
    this.showPieChart = function(userId){
        if(userId != null && userId != ''){
            $.blockUI({ css: { 
                border: 'none', 
                padding: '15px', 
                backgroundColor: '#000', 
                '-webkit-border-radius': '10px', 
                '-moz-border-radius': '10px', 
                opacity: .5, 
                color: '#fff' 
            } }); 
        
            var request = $.ajax({
                url: "showPieChart.htm",
                type: "POST",
                data: {'userId' : userId},
                dataType: "html"
            });
        
            request.done(function(varData) {
                $.unblockUI();
                
                var obj = $.parseJSON( varData );
                //alert('varData: '+varData);
                if(obj.PieData.length > 0){
                    Admin.displayPieChart(obj.PieData);
                }
                
            });

            request.fail(function(jqXHR, textStatus) {
                $.unblockUI();
                alert( "Request failed: " + textStatus );
            });
        }
        return false;
    };
    
    /**
     * This method is used for displaying pass result for the corresponding qualifying value.
     */
    this.displayPieChart = function(varData){
        
        $( "#dialog-modal" ).dialog({
            height: 500,
            width:800,
            modal: true
        });
              
        var chart;
        //AmCharts.ready(function () {
            // PIE CHART
            chart = new AmCharts.AmPieChart();
            chart.dataProvider = varData;
            chart.titleField = "SKILL_NAME";
            chart.valueField = "VALUE";
            chart.outlineColor = "#FFFFFF";
            chart.outlineAlpha = 0.8;
            chart.outlineThickness = 2;
            chart.balloonText = "[[title]]<br><span style='font-size:14px'><b>[[value]]%</b> </span>";
            // this makes the chart 3D
            chart.depth3D = 15;
            chart.angle = 30;
            chart.labelText = "[[title]]: [[value]]%"
            
            var legend = new AmCharts.AmLegend();
            chart.addLegend(legend);
            // WRITE
            chart.write("chartdiv");
        //});
    };
    
}();
