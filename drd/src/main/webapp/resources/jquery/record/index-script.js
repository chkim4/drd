var cardioList = [];
var fitnessList = [];
var foodList = [];
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
	           							   "fitnessList": record.fitnessObj.fitnessList}	
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
	var content = {icon: 'info',  
				   customClass: 'event-read'};
	
	switch(info.event.groupId){
		case 'cardio': 
			title = '유산소 운동 목록입니다.'; 
			content["title"] = title; 
			content["html"] = readCardioEvent(info); 
			break;
		
		case 'fitness': 
			title = '무산소 운동 목록입니다.'; 
			content["title"] = title; 
			content["html"] = readFitnessEvent(info); 	
			break;
		
		case 'food': 
			title = '식사 목록입니다.';
			content["title"] = title; 
			content["html"] = readFoodEvent(info); 	
			break;
	}  
	
	Swal.fire(content)
	
	
} //showEventInfo 닫기

// 유산소 운동 관련 시작
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
// 유산소 운동 관련 끝

// 무산소 운동 관련 시작
function readFitnessEvent(info){ 
	fitnessList = info.event.extendedProps.fitnessList;
	
	result = '<div class = "swal-text">' +     
				      '<table class="table table-bordered" id="fitnessEventListTable" width="100%" cellspacing="0">' +  
                    	'<thead><tr>' + 
	                    	'<th>' + '무산소 운동 이름' + '</th>' + 
	                    	'<th>' + '세트 수' + '</th>' + 
	                    	'<th>' + '세트 당 횟수' + '</th>' + 
	                    	'<th>' + '중량' + '</th>' + 
	                    	'<th>' + '근육 부위' + '</th>' + 
	                    	'<th>' + '기구' + '</th>' + 
				    	'</tr></thead>' +
	      				'<tbody>';  
		      				 fitnessList.map(function(fitness){								      	
						      	result += '<tr>' +
											'<td>' + fitness.name + '</td>' +  
											'<td>' + fitness.set + '</td>' +  
											'<td>' + fitness.count + '</td>' +    
											'<td>' + fitness.weight + '</td>' +     
											'<td>' + fitness.muscleGroup + '</td>' +    
											'<td>' + fitness.equipment + '</td>' +    
										  '</tr>';
							 })
	      				'</tbody>' + 
	                  '</table>' +            
	   				'</div>' 
	
	return result; 
} 
// 무산소 운동 관련 끝

// 음식 관련 시작
function readFoodEvent(info){ 
	foodObj = info.event.extendedProps.foodObj;
	
	result = '<div class = "swal-text">' +     
				      '<table class="table table-bordered" id="foofEventListTable" width="100%" cellspacing="0">' +  
                    	'<thead><tr>' + 
	                    	'<th>' + '음식 이름' + '</th>' + 
	                    	'<th>' + '섭취량' + '</th>' + 
	                    	'<th>' + '총 칼로리' + '</th>' + 
	                    	'<th>' + '단백질량' + '</th>' + 
	                    	'<th>' + '지방량' + '</th>' + 
	                    	'<th>' + '콜레스트롤' + '</th>' + 
				    	'</tr></thead>' +
	      				'<tbody>';  
		      				 foodObj.map(function(food){								      	
						      	result += '<tr>' +
											'<td>' + food.name + '</td>' +  
											'<td>' + food.amount + '</td>' +  
											'<td>' + food.calory + '</td>' +    
											'<td>' + food.protein + '</td>' +    
											'<td>' + food.fat + '</td>' +    
											'<td>' + food.cholesterol + '</td>' +    
										  '</tr>';
							 })
	      				'</tbody>' + 
	                  '</table>' +            
	   				'</div>' 
	
	return result; 
} 
// 음식 관련 끝
