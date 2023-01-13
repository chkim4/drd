$(document).on("change","#cardioEventListTable", function(){cardioEventSelector_change(); })

var cardioList = [];
var result = "";

function onDOMContentLoaded() {   
    $.ajax({
		url: "/record/findMonthlyRecord.do", 
		type: "GET",
		success: successRun,
		error: errorRun 
		}) 
		function successRun(data){   
			var fetchedEvent = [];  
            
            data.map(function(record, recordIndex, recordArray){
            	var date = record.date; 
            	
            	fetchedEvent.push({ 
	    			"groupId": "cardio",
	   				"title": "유산소 운동 기록 내역", 
	       			"start": date, 
	       			"backgroundColor": "#2E59D9",
	       			"extendedProps": { "totalTime": record.cardioObj.totalTime, 
	       				 			   "cardioList": record.cardioObj.cardioList}	
	   			}); 
            	
            	fetchedEvent.push({ 
            			"groupId": "fitness",
           				"title": "무산소 운동 기록 내역", 
	           			"start": date, 
	           			"backgroundColor": "#2C9FAF",
	           			"extendedProps": { "totalTime": record.fitnessObj.totalTime, 
	           							   "fitnessList": record.cardioObj.fitnessList}	
           		}); 
            	 	
        		fetchedEvent.push({
       				"groupId": "food",
       				"title": "식사 기록 내역", 
           			"start": date, 
           			"backgroundColor": "#17A673",
           			"extendedProps": {"foodObj": record.foodObj}	
           			});  
            }); // data map 닫기 
           		
			 var calendarEl = document.getElementById('calendar');
	         var calendar = new FullCalendar.Calendar(calendarEl, {
	             initialDate: new Date(),
	             initialView: 'dayGridMonth',
	             headerToolbar: {
	                left: 'prev,next today',
	                center: 'title',
	                right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
	             },
	             editable: true,
	             droppable: true, // this allows things to be dropped onto the calendar
	             drop: function (arg) {
	                // is the "remove after drop" checkbox checked?
	                if (document.getElementById('drop-remove').checked) {
	                    // if so, remove the element from the "Draggable Events" list
	                    arg.draggedEl.parentNode.removeChild(arg.draggedEl);
	                }
	            },
	            events: fetchedEvent, 
	            eventClick: function(info){
	            	showEventInfo(info)
				}
	          }); //calendar 선언 닫기
	              
	           calendar.render();
   
		}
		function errorRun(obj, msg,statusMsg){  
			console.log(obj);
			console.log(msg);
			console.log(statusMsg);
		} 
} // onDOMContentLoaded() 닫기	 	 

function showEventInfo(info){
	
	var title = ""; 
	
	switch(info.event.groupId){
		
		case 'cardio': 
			title = '유산소 운동 목록입니다.' 
			break;
		
		case 'fitness': 
			title = '무산소 운동 목록입니다.' 
			break;
		
		case 'food': 
			title = '음식 목록입니다.' 
			break;
	} 
	
	Swal.fire({
		  icon: 'info',
		  title: title, 
		  html: readCardioEvent(info)
		  /*
		  didOpen: () => {
		    var content = Swal.getHtmlContainer()
   			var $ = content.querySelector.bind(content) 
   			var cardioEventSelector= $("#cardioEventSelector"); 
   			
   			cardioEventSelector.addEventListener('change', () => {
   				cardioEventSelector_change();
   			});
   			
	      }  
	      */
	})
} //showEventInfo 닫기

function readCardioEvent(info){ 
	cardioList = info.event.extendedProps.cardioList;
	
	result = '<div class = "swal-text">' +     
				      '<table class="table table-bordered" id="cardioEventListTable" width="100%" cellspacing="0">' +  
                    	'<thead><tr>' + 
	                    	'<th>' + '유산소 운동 이름' + '</th>' + 
	                    	'<th>' + '운동 시간 (분)' + '</th>' + 
	                    	'<th>' + '소모 칼로리' + '</th>' + 
	                    	'<th>' + '운동 강도' + '</th>' + 
				    	'</tr></thead>' +
	      				'<tbody>';  
		      				 cardioList.map(function(cardio){								      	
						      	result += '<tr>' +
											'<td>' + cardio.name + '</td>' +  
											'<td>' + cardio.time + '</td>' +  
											'<td>' + cardio.calory + '</td>' +    
											'<td>' + getIntensityComment(cardio.intensity) + '</td>' +    
										  '</tr>';
							 })
	      				'</tbody>' + 
	                  '</table>' +            
	   				'</div>' 
	
	return result; 
} 

function getIntensityComment(intensity){
	return intensity == 1 ? '고강도' : '저강도';
} 

/* dynamic 버전. 실패함.

function readCardioEvent(info){ 
	cardioList = info.event.extendedProps.cardioList;
	
	result = '<div class = "swal-text">' +     
				      '<table class="table table-bordered" id="cardioEventListTable" width="100%" cellspacing="0">' +  
                    	'<thead><tr>' + 
	                    	'<th>' + '유산소 운동 목록' + '</th>' + 
	                    	'<th>' + 
	                    		'<select class="form-control" id="cardioEventSelector" name="cardioEventSelector">' + 
						    		'<option value = "-1" selected>' + '목록 선택' + '</option>';						      
								      cardioList.map(function(cardio, cardioIndex){								      	
								      	result += '<option value = "' + cardioIndex + '">' + cardio.name + '</option>' 
								      })
					    		'</select>' + 
					    	'</th>' + 
				    	'</tr></thead>' +
	      				'<tbody>' + 
	      				'</tbody>' + 
	                  '</table>'              
	   				'</div>' 
	
	return result; 
} 
*/

