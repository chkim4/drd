function onDOMContentLoaded() {
	    var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {  
        	initialView: 'dayGridMonth', 
	        events: 
	        	[{ 
	             title: 'Test',
		          start: '2023-01-12'
		    	}] 
	   		}).on('click', '.fc-basicDay-button', function(event) {
	         $("th.fc-today").text(moment().format('D MMM, YYYY'));  
        }); 
        calendar.render(); 
} 


