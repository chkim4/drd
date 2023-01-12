function onDOMContentLoaded() {   
    $.ajax({
		url: "/record/findMonthlyRecord.do", 
		type: "GET",
		success: successRun,
		error: errorRun 
		}) 
		function successRun(data){  
			var fetchedEvent = []; 
                
            for(var i=0; i<data.length; i++){
           		fetchedEvent.push({
           			"title": "운동 기록 내역", 
           			"start": data[i].date,
           			"extendedProps": {"content": data[i].cardioObj}
           		});
            }
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
                eventClick: function(info) { 
                	var displayContent = info.event.extendedProps.content;
                	console.log("displayContent: ", displayContent.cardioList[0].name);
                	
                	Swal.fire({
					  icon: 'info',
					  title: '유산소 운동 목록 중 첫 번째 운동의 이름입니다.', 
					  text: displayContent.cardioList[0].name
					})	
    			}
              });
                  
               calendar.render();
		}  
		function errorRun(obj, msg,statusMsg){  
			console.log(obj);
			console.log(msg);
			console.log(statusMsg);
		} 			
         
} 


