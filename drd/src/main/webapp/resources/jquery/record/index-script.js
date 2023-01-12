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
	          });
	              
	           calendar.render();
			  }   
		}
		function errorRun(obj, msg,statusMsg){  
			console.log(obj);
			console.log(msg);
			console.log(statusMsg);
		} 			 

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
		  title: title
	})	
}

