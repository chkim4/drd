var cardioList = [];
var fitnessList = [];
var foodList = [];
var result = "";
var d = new Date();
var year = d.getFullYear(); 
var month = d.getMonth(); 
var date = d.getDate(); 
var deleteTag = []; 
var fetchedEvent = [];

//sweetalert 전역 설정
var mySwal = Swal.mixin({ 
				confirmButtonColor: '#4E73DF',		
				confirmButtonText: '확인'
			  })

function onDOMContentLoaded() {   
    $.ajax({
		url: "/record/findMonthlyRecord.do", 
		type: "GET",
		data: ({"year": year, "month": month}),
		success: successRun,
		error: errorRun 
		}) 
		function successRun(data){   
            data.map(function(record, recordIndex, recordArray){
            	var date = record.date; 
            	
            	// 유산소 운동 기록이 있을 때만 화면에 출력
            	if(record.cardioObj){
	            	fetchedEvent.push({ 
		    			"groupId": "cardio",
		   				"title": "유산소 운동 기록 내역", 
		       			"start": date, 
		       			"backgroundColor": "#2E59D9",
		       			"extendedProps": { "totalTime": record.cardioObj.totalTime, 
		       				 			   "cardioList": record.cardioObj.cardioList, 
		       				 			   "date": record.date, 
		       				 			   "order": 1}	
		   			}); 
		   		} 
            	
            	if(record.fitnessObj){
	            	fetchedEvent.push({ 
	            			"groupId": "fitness",
	           				"title": "무산소 운동 기록 내역", 
		           			"start": date, 
		           			"backgroundColor": "#2C9FAF",
		           			"extendedProps": { "totalTime": record.fitnessObj.totalTime, 
		           							   "fitnessList": record.fitnessObj.fitnessList, 
		       				 			   		"date": record.date,
		       				 			   		"order": 2}	
	           		});  
	           	}
            	
            	if(record.foodObj){
	        		fetchedEvent.push({
	       				"groupId": "food",
	       				"title": "식사 기록 내역", 
	           			"start": date, 
	           			"backgroundColor": "#17A673",
	           			"extendedProps": {"foodObj": record.foodObj, 
		       				 			   "date": record.date, 
		       				 			   "order": 3}	
	           		});   
	           	}
				
				if(record.status){
	        		fetchedEvent.push({
	       				"groupId": "status",
	       				"title": "상태 점수", 
	           			"start": date, 
	           			"backgroundColor": "#A6174A",
	           			"extendedProps": {"status": record.status, 
		       				 			   "date": record.date, 
		       				 			   "order": 4}	
	           		});   
	           	}
            }); // data map 닫기  
           		
			 var calendarEl = document.getElementById('calendar');
	         var calendar = new FullCalendar.Calendar(calendarEl, {
	             initialDate:  getInitialDate(),
	             initialView: 'dayGridMonth',
	             customButtons: {
				    customPrev: {
				      text: '<',
				      click: () => {   
					      customPrev_onClick(); 
					      onDOMContentLoaded();
					   }
				    }, 
				    customNext: {
				      text: '>',
				      click: () => {   
					     customNext_onClick(); 
					     onDOMContentLoaded();		     
					   }
				    }
				  }, 
	             titleFormat: { month: '2-digit', year: 'numeric' },
	             headerToolbar: {
	                left: 'customPrev',
	                center: 'title', 
	                right: 'customNext'
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
	            dateClick: function(info) {clickDateInfo(info);},
	            events: fetchedEvent, 
	            eventClick: function(info, fetched){
	            	showEventInfo(info)
				},   
				eventOrder: 'order', 
				eventOrderStrict: true,
				displayEventTime: false
	          }); //calendar 선언 닫기
	              
	           calendar.render();
   
		}
		function errorRun(obj, msg,statusMsg){  
			console.log(obj);
			console.log(msg);
			console.log(statusMsg);
		} 
} // onDOMContentLoaded() 닫기	 	 

// 달력의 날짜 클릭 이벤트 시작
function clickDateInfo(info){  

	var eventDate = new Date(info.dateStr);
	var today = new Date();
	var readonly = checkReadOnly(info.dateStr); 
	var result = [];
	
	// 오늘과 비교해 클릭한 날짜가 과거(past),현재(current),미래(post) 중 어디에 해당하는 지의 정보 저장
	var timeInfo = ""; 
	
	if(readonly != "readonly"){
		timeInfo = "current";
	}
	else if(eventDate < today){
		timeInfo = "past"; 
	}
	else {
		timeInfo = "post";
	} 
		
	switch(timeInfo){
		
		case "past": 
			mySwal.fire({
				icon: 'warning',
				title: '기록 내역은 각 항목 별로 조회해주세요!' 
			})
			break;		
		
		case "current":   
			result = getDailyEvent(fetchedEvent, eventDate);
			showDateAddList(result); 
			break;	
		
		case "post":
			mySwal.fire({
				icon: 'warning',
				title: '아직 기록할 수 없습니다!' 
			})
			break;	
		
		default: 
			break;
	
	}// switch 닫기 	
}
// 달력의 날짜 클릭 이벤트 끝  

// 현재 날짜 클릭 시 없는 이벤트 (유산소, 무산소, 식단, 상태) 추가하는 모달 띄우기
function showDateAddList(eventList){
	if(eventList.length == 4){
		mySwal.fire({
			icon: 'info', 
			title: '모든 항목을 작성하였습니다. 각 항목 별로 조회 바랍니다.'
		})
		return; 
	}
	else{
		var notWrittenGroupId = ['cardio', 'fitness', 'food', 'status']; 
		var content =  {icon: 'info',  
				   		customClass: 'event-read',
				   		html: ''};
		
		// 전체 groupid 들 중 작성된 eventㅇ의 group id 제거
		eventList.map(function(event){ 
			notWrittenGroupId.splice(notWrittenGroupId.indexOf(event.groupId),1);
		}); // eventList.map 닫기 
		
		notWrittenGroupId.map(function(event){
			switch(event){
				case "cardio": 
					content['html']+= '<div style="font-size: 200%" onclick="searchEvent(\''+"cardio"+ '\')"> 유산소 운동 내역 기록하기 </div>';
					break;
				
				case "fitness": 
					content['html']+= '<div style="font-size: 200%" onclick="searchEvent(\''+"fitness"+ '\')"> 무산소 운동 내역 기록하기 </div>';
					break;
				
				case "food": 
					content['html']+= '<div style="font-size: 200%" onclick="searchEvent(\''+"food"+ '\')"> 식단 기록하기 </div>';
					break;
				
				case "status": 
					content['html']+= '<div style="font-size: 200%" onclick="searchEvent(\''+"status"+ '\')"> 상태 점수 기록하기 </div>';
					break;
				
			} //switch 닫기   
		}) //notWrittenGroupId.map 닫기 
		
		mySwal.fire(content);
	} //else 닫기
} 

// 클릭한 날짜의 이벤트 추출  
function getDailyEvent(events, d){
	var date = new Date(d); 
	var result = [];
	
	events.map(function(event){
		if(isSameDate(event.start, date)){
			result.push(event);
		} 
	}) //events.map 닫기 
	
	return result;
}

// 이벤트 클릭 시작
function showEventInfo(info){
 	var readOnly = checkReadOnly(info.event.extendedProps.date); // 오늘 이벤트일 경우에만 수정 가능토록 하며 버튼 문구 다르게.
	var title = "";  
	var content = {icon: 'info',  
				   customClass: 'event-read',
				  }; 
	// 편집 가능한 이벤트일 경우  
	if(readOnly != "readonly"){
		content["showDenyButton"] = true; 
		content["showCancelButton"] = true;
		content["denyButtonText"] = "전체 삭제";
		content["cancelButtonText"] = "취소";
		content["confirmButtonText"] = "저장";  
		content["showLoaderOnConfirm"] = true; 
		content["reverseButtons"] = true;  
		
	}
	// 편집 가능한 이벤트일 경우 삭제 기능 추가 
	createDeleteTag(readOnly)
	 
	switch(info.event.groupId){
		case 'cardio': 
			title = '유산소 운동 목록입니다.'; 
			content["title"] = title; 
			content["html"] = readCardioEvent(info, readOnly); 
			content["preConfirm"] = () => {updateCardioEvent(info.event.extendedProps.date);}  // 저장 버튼 핸들러
			content["preDeny"] = () => {deleteEvent(info.event.extendedProps.date, 'cardioObj');}	// 전체 삭제 버튼 핸들러	
			break;
		
		case 'fitness': 
			title = '무산소 운동 목록입니다.'; 
			content["title"] = title; 
			content["html"] = readFitnessEvent(info, readOnly);  
			content["preConfirm"] = () => {updateFitnessEvent(info.event.extendedProps.date);} 
			content["preDeny"] = () => {deleteEvent(info.event.extendedProps.date, 'fitnessObj');}	
			break;
		
		case 'food': 
			title = '식사 목록입니다.';
			content["title"] = title; 
			content["html"] = readFoodEvent(info, readOnly);
			content["preConfirm"] = () => {updateFoodEvent(info.event.extendedProps.date);} 
			content["preDeny"] = () => {deleteEvent(info.event.extendedProps.date, 'foodObj');}		
			break;

		case 'status': 
			title = '상태 점수 입니다.';
			content["title"] = title; 
			content["html"] = readStatusEvent(info, readOnly); 	
			content["preConfirm"] = () => {updateStatusEvent(info.event.extendedProps.date);} 
			content["preDeny"] = () => {deleteEvent(info.event.extendedProps.date, 'status');}	
			content["denyButtonText"] = "삭제";
			break;
	}   
	
	// 편집 불가능한 이벤트일 경우 업데이트 이벤트 핸들러 제거
	if(readOnly == "readonly"){
		content["preConfirm"] = () => {}	
	}
	
	mySwal.fire(content);
	
} //showEventInfo 닫기

// 필드에 해당하는 목록 전체 조회 
function searchEvent(field){
	var title = "유산소 운동 목록입니다."; 
	var requestURL = "";
	
	var content = {icon: 'info',  
				   title: '',
				   customClass: 'event-read', 
				   showCancelButton: true, 
				   cancelButtonText: '취소', 
				   confirmButtonText: '저장',
				   reverseButtons: true,
				   html: ''
				  };  
	
	switch(field){	
		case "cardio": 
			content["title"] = "유산소 운동 목록입니다."
			content["preConfirm"] = () => {createCardioEvent();}
			requestURL = "/record/findAllCardio.do";
			break;
		
		case "fitness": 
			content["title"] = "무산소 운동 목록입니다." 
			content["preConfirm"] = () => {createFitnessEvent();}
			requestURL = "/record/findAllFitness.do";
			break;

		case "food": 
			content["title"] = "식단 목록입니다." 
			content["preConfirm"] = () => {createFoodEvent();}
			requestURL = "/food/findAll.do";
			break;

	}//switch 닫기	
	
	// 상태 점수 생성 시에는 ajax 불필요)
	if(field == "status"){
		content["title"] = "상태 점수를 입력해주세요."; 
		content["preConfirm"] = () => {createStatusEvent();}
		content["html"] = getAllStatusHTML();
		mySwal.fire(content); 
		return;
	}
			
	$.ajax({
		url: requestURL, 
		type: "GET",
		success: successRun,
		error: errorRun 
	}) 
	function successRun(data){  
		switch(field){	
			case "cardio": 
				content["html"] = getAllCardioHTML(data);
				break;
			
			case "fitness": 
				content["html"] = getAllFitnessHTML(data);
				break;
	
			case "food": 
				content["html"] = getAllFoodHTML(data);
				break;
		}//switch 닫기	 
		
		mySwal.fire(content);
		
		
	}  
	function errorRun(obj, msg,statusMsg){  
		console.log(obj);
		console.log(msg);
		console.log(statusMsg);
	} 	 			
}

// 유산소 운동 관련 시작

// 유산소 전체 목록 조회 
function getAllCardioHTML(cardioList){
	var result = '<div class = "swal-text">' +     
				      '<table class="table table-bordered" id="cardioEventListTable" width="100%" cellspacing="0">' +  
                    	'<thead><tr>' + 
	                    	'<th>' + '유산소 운동 이름' + '</th>' + 
	                    	'<th>' + '소모 칼로리(시간)' + '</th>' + 
	                    	'<th>' + '운동 강도' + '</th>' +   
	                    	'<th>' + '운동 시간(분)' + '</th>' +   
	                    	'<th>' + '선택' + '</th>' +   
	                    	'<th style="display: none">' + 'cardioSEQ' + '</th>' + 
				    	'</tr></thead>' +  
				    	'<tbody>';	    	
						cardioList.map(function(cardio){								      	
					  	result += '<tr>' +
									'<td id="name">' + cardio.name + '</td>' + 
									'<td id="calory">' + cardio.calory + '</td>' +    
									'<td>' + getIntensityComment(cardio.intensity) + '</td>' +  
									'<td>' + 
										'<input type="number" id="time" class= "inputs" min="1">' +
									'</td>' +  
									'<td>' + 
										'<input type="checkbox" id="selectedCardio">' + 
					  				'</td>' + 
									'<td style="display: none">"' + 
										'<input type="number" id="cardioSEQ" class= "inputs" value=' + cardio.cardioSEQ +'>' +  
									'</td>' +     
									'<td style="display: none">"' + 
										'<input type="text" id="nameValue" class= "inputs" value=' + cardio.name +'>' +  
									'</td>' +     
									'<td style="display: none">"' + 
										'<input type="number" id="caloryValue" class= "inputs" value=' + cardio.calory +'>' +  
									'</td>' +     
									'<td style="display: none">"' + 
										'<input type="number" id="intensityValue" class= "inputs" value=' + cardio.intensity +'>' +  
									'</td>' +     
								   '</tr>';
						})
	result +=	  '</tbody>' + 
					'</table>' +            
				'</div>' 
	
	return result;
} 

// 유산소 운동 생성
function createCardioEvent(){
	var cardioObjList = [];
	
	$('#cardioEventListTable tr').each(function() {
    	if($(this).find("td input[id=selectedCardio]").is(':checked')){
    		var cardioObj = {};    	
	    	var parsedCardioSEQ = parseInt($(this).find("td input[id=cardioSEQ]").val(), 10); 
	    	var name = $(this).find("td input[id=nameValue]").val();
	    	var parsedTime = parseInt($(this).find("td input[id=time]").val(), 10);
	    	var parsedCalory = parseInt($(this).find("td input[id=caloryValue]").val(), 10)*(parsedTime/60); 
	    	var parsedIntensity = parseInt($(this).find("td input[id=intensityValue]").val(), 10); 
	    	
	    	// parsedCalory: 조회된 시간 당 칼로리를 운동한 시간에 맞게끔 변경
	    	cardioObj["cardioSEQ"] = parsedCardioSEQ;
	    	cardioObj["name"] = name;
	    	cardioObj["time"] = parsedTime;
	    	cardioObj["calory"] = parsedCalory;
	    	cardioObj["intensity"] = parsedIntensity; 
	    	cardioObjList.push(cardioObj);    	
    	} //if...checked 닫기     	
	}) 
	
	$.ajax({
		url: "/record/createCardio.do", 
		type: "POST",
		data: {"cardioList": convertString(cardioObjList), "date": new Date()},
		success: successRun,
		error: errorRun 
		}) 
		function successRun(result){  
			
			if(result > 0){ 
				mySwal.fire({
				  icon: 'success',
				  title: '업데이트가 반영되었습니다!',
				})
				
			}
			else{
				mySwal.fire({
				  icon: 'error',
				  title: '업데이트 중 에러가 생겼습니다... 관리자에게 문의 바랍니다.',
				})
			}
		}  
		function errorRun(obj, msg,statusMsg){  
			console.log(obj);
			console.log(msg);
			console.log(statusMsg);
		} 	 			
}

// 유산소 운동 추가 이후의 읽기
function readCardioEvent(info, readOnly){ 
	cardioList = info.event.extendedProps.cardioList;
	
	result = '<div class = "swal-text">' +     
				      '<table class="table table-bordered" id="cardioEventListTable" width="100%" cellspacing="0">' +  
                    	'<thead><tr>' + 
	                    	'<th>' + '유산소 운동 이름' + '</th>' + 
	                    	'<th>' + '운동 시간 (분)' + '</th>' + 
	                    	'<th>' + '소모 칼로리' + '</th>' + 
	                    	'<th>' + '운동 강도' + '</th>' + 
	                    	deleteTag[0] + 
	                    	'<th style="display: none">' + 'cardioSEQ' + '</th>' + 
				    	'</tr></thead>' +
	      				'<tbody>';  
		      				 cardioList.map(function(cardio){								      	
						      	result += '<tr>' +
											'<td>' + cardio.name + '</td>' +  
											'<td>' + 
												'<input type="number" id="time" class= "inputs" ' + readOnly + ' value=' + cardio.time + ' min="1">' +  												 
											'</td>' +  
											'<td>' + cardio.calory + '</td>' +    
											'<td>' + getIntensityComment(cardio.intensity) + '</td>' +  
											deleteTag[1] + 
											'<td style="display: none">"' + 
												'<input type="number" id="cardioSEQ" class= "inputs" value=' + cardio.cardioSEQ +'>' +  
											'</td>' +     
											'<td style="display: none">"' + 
												'<input type="text" id="nameValue" class= "inputs" value=' + cardio.name +'>' +  
											'</td>' +     
											'<td style="display: none">"' + 
												'<input type="number" id="caloryValue" class= "inputs" value=' + cardio.calory +'>' +  
											'</td>' +     
											'<td style="display: none">"' + 
												'<input type="number" id="intensityValue" class= "inputs" value=' + cardio.intensity +'>' +  
											'</td>' +     
										   '</tr>';
							 })
	result += 			'</tbody>' + 
	    			'</table>' +            
				'</div>' 

	
	return result; 
} 

function getIntensityComment(intensity){
	return intensity == 1 ? '고강도' : '저강도';
}

function updateCardioEvent(date){ 
	var cardioObjList = [];
	
	// 화면에 띄운 모든 유산소 운동 목록을 [{"cardioSEQ", 숫자}, {"time", 숫자}] 형태로 가져오기
	$('#cardioEventListTable tr').each(function() {
    		var cardioObj = {};    	
	    	var parsedCardioSEQ = parseInt($(this).find("td input[id=cardioSEQ]").val(), 10); 
	    	var name = $(this).find("td input[id=nameValue]").val();
	    	var parsedTime = parseInt($(this).find("td input[id=time]").val(), 10);
	    	var parsedCalory = parseInt($(this).find("td input[id=caloryValue]").val(), 10)*(parsedTime/60); 
	    	var parsedIntensity = parseInt($(this).find("td input[id=intensityValue]").val(), 10); 
	    	
	    	// parsedCalory: 조회된 시간 당 칼로리를 운동한 시간에 맞게끔 변경
	    	cardioObj["cardioSEQ"] = parsedCardioSEQ;
	    	cardioObj["name"] = name;
	    	cardioObj["time"] = parsedTime;
	    	cardioObj["calory"] = parsedCalory;
	    	cardioObj["intensity"] = parsedIntensity; 
	    	cardioObjList.push(cardioObj);
	}) 
	cardioObjList.shift(); // [0] 에 저장된 undefined 없애기
	
	$.ajax({
		url: "/record/updateCardio.do", 
		type: "POST",
		data: {"cardioList": convertString(cardioObjList), "date": date},
		success: successRun,
		error: errorRun 
		}) 
		function successRun(result){  
			
			if(result > 0){ 
				mySwal.fire({
				  icon: 'success',
				  title: '업데이트가 반영되었습니다!',
				})
				
			}
			else{
				mySwal.fire({
				  icon: 'error',
				  title: '업데이트 중 에러가 생겼습니다... 관리자에게 문의 바랍니다.',
				})
			}
		}  
		function errorRun(obj, msg,statusMsg){  
			console.log(obj);
			console.log(msg);
			console.log(statusMsg);
		} 	 			
}
// 유산소 운동 관련 끝

// 무산소 운동 관련 시작
// 무산소 전체 목록 조회 
function getAllFitnessHTML(fitnessList){
	var result = '<div class = "swal-text">' +     
				      '<table class="table table-bordered" id="fitnessEventListTable" width="100%" cellspacing="0">' +  
                    	'<thead><tr>' + 
	                    	'<th>' + '무산소 운동 이름' + '</th>' +  
	                    	'<th>' + '근육 부위' + '</th>' + 
	                    	'<th>' + '기구' + '</th>' +   
	                    	'<th>' + '세트 수' + '</th>' + 
	                    	'<th>' + '세트 당 횟수' + '</th>' + 
	                    	'<th>' + '중량' + '</th>' + 
	                    	'<th>' + '선택' + '</th>' +   
	                    	'<th style="display: none">' + 'fitnessSEQ' + '</th>' + 
				    	'</tr></thead>' +  
				    	'<tbody>';	    	
						fitnessList.map(function(fitness){								      	
					  	result += '<tr>' +
									'<td>' + fitness.name + '</td>' + 
									'<td>' + fitness.muscleGroup + '</td>' + 
									'<td>' + fitness.equipment + '</td>' + 
									'<td>' +  
										'<input type="number" id="set" class= "inputs" min="1">' +
									'</td>' +    
									'<td>' + 
										'<input type="number" id="count" class= "inputs" min="1">' +
									'</td>' +  
									'<td>' + 
										'<input type="number" id="weight" class= "inputs" min="1">' +
									'</td>' +  
									'<td>' + 
										'<input type="checkbox" id="selectedFitness">' + 
					  				'</td>' + 
									'<td style="display: none">"' + 
										'<input type="number" id="fitnessSEQ" class= "inputs" value=' + fitness.fitnessSEQ +'>' +  
									'</td>' +     
									'<td style="display: none">"' + 
										'<input type="text" id="nameValue" class= "inputs" value=' + fitness.name +'>' +  
									'</td>' +     
									'<td style="display: none">"' + 
										'<input type="text" id="muscleGroupValue" class= "inputs" value=' + fitness.muscleGroup +'>' +  
									'</td>' +     
									'<td style="display: none">"' + 
										'<input type="text" id="equipmentValue" class= "inputs" value=' + fitness.equipment +'>' +  
									'</td>' +     
								   '</tr>';
						})
	result +=	  '</tbody>' + 
				'</table>' +    
				'<span>총 운동 시간: ' +  
	    				'<input type="number" id="totalTime" class= "inputs" style="width: 10%;" ' +'min="1"> 분' +  
	    		'</span>' +         
			 '</div>' 
	
	return result;
} 

// 무산소 운동 생성
function createFitnessEvent(){
	var fitnessObjList = [];
	
	$('#fitnessEventListTable tr').each(function() {
    	if($(this).find("td input[id=selectedFitness]").is(':checked')){
    		var fitnessObj = {};    	
	    	var parsedFitnessSEQ = parseInt($(this).find("td input[id=fitnessSEQ]").val(), 10); 
	    	var name = $(this).find("td input[id=nameValue]").val();
	    	var muscleGroup = $(this).find("td input[id=muscleGroupValue]").val();
	    	var equipment = $(this).find("td input[id=equipmentValue]").val();
	    	var parsedSet = parseInt($(this).find("td input[id=set]").val(), 10);
	    	var parsedCount = parseInt($(this).find("td input[id=count]").val(), 10); 
	    	var parsedWeight = parseInt($(this).find("td input[id=weight]").val(), 10); 
	    	
	    	// parsedCalory: 조회된 시간 당 칼로리를 운동한 시간에 맞게끔 변경
	    	fitnessObj["fitnessSEQ"] = parsedFitnessSEQ;
	    	fitnessObj["name"] = name;
	    	fitnessObj["muscleGroup"] = muscleGroup;
	    	fitnessObj["equipment"] = equipment;
	    	fitnessObj["set"] = parsedSet; 
	    	fitnessObj["count"] = parsedCount; 
	    	fitnessObj["weight"] = parsedWeight; 
	    	fitnessObjList.push(fitnessObj);    	
    	} //if...checked 닫기     	
	}) 
	
	$.ajax({
		url: "/record/createFitness.do", 
		type: "POST",
		data: {"fitnessList": convertString(fitnessObjList), "date": new Date(), 
			   "totalTime": parseInt($("input[id='totalTime']").val())},
		success: successRun,
		error: errorRun 
		}) 
		function successRun(result){  
			
			if(result > 0){ 
				mySwal.fire({
				  icon: 'success',
				  title: '업데이트가 반영되었습니다!',
				})
				
			}
			else{
				mySwal.fire({
				  icon: 'error',
				  title: '업데이트 중 에러가 생겼습니다... 관리자에게 문의 바랍니다.',
				})
			}
		}  
		function errorRun(obj, msg,statusMsg){  
			console.log(obj);
			console.log(msg);
			console.log(statusMsg);
		} 	 			
}

// 무산소 운동 생성 후의 읽기
function readFitnessEvent(info, readOnly){ 
	fitnessList = info.event.extendedProps.fitnessList;
	totalTime = info.event.extendedProps.totalTime;
	
	result = '<div class = "swal-text">' +     
				      '<table class="table table-bordered" id="fitnessEventListTable" width="100%" cellspacing="0">' +  
                    	'<thead><tr>' + 
	                    	'<th>' + '무산소 운동 이름' + '</th>' + 
	                    	'<th>' + '세트 수' + '</th>' + 
	                    	'<th>' + '세트 당 횟수' + '</th>' + 
	                    	'<th>' + '중량' + '</th>' + 
	                    	'<th>' + '근육 부위' + '</th>' + 
	                    	'<th>' + '기구' + '</th>' +   
	                    	deleteTag[0] + 
	                    	'<th style="display: none">' + 'fitnessSEQ' + '</th>' +
				    	'</tr></thead>' +
	      				'<tbody>';  
		      				 fitnessList.map(function(fitness){								      	
						      	result += '<tr>' + 
											'<td>' + fitness.name + '</td>' +  
											'<td>' + 
												'<input type="number" id="set" class= "inputs" ' + readOnly + ' value=' + fitness.set + ' min="1">' +  
											'</td>' +  
											'<td>' + 
												'<input type="number" id="count" class= "inputs" ' + readOnly + ' value=' + fitness.count + ' ' + readOnly +'min="1">' +  
											'</td>' +  
											'<td>' + 
												'<input type="number" id="weight" class= "inputs" ' + readOnly + ' value=' + fitness.weight + ' ' + readOnly +'min="1">' +  
											'</td>' +    
											'<td>' + fitness.muscleGroup + '</td>' +    
											'<td>' + fitness.equipment + '</td>' +   
											deleteTag[1] +   
											'<td style="display: none">"' + 
												'<input type="number" id="fitnessSEQ" class= "inputs" ' + readOnly + ' value=' + fitness.fitnessSEQ +'>' +  
											'"</td>' +  
											'<td style="display: none">"' + 
												'<input type="text" id="nameValue" class= "inputs" value=' + fitness.name +'>' +  
											'</td>' +     
											'<td style="display: none">"' + 
												'<input type="text" id="muscleGroupValue" class= "inputs" value=' + fitness.muscleGroup +'>' +  
											'</td>' +     
											'<td style="display: none">"' + 
												'<input type="text" id="equipmentValue" class= "inputs" value=' + fitness.equipment +'>' +  
											'</td>' +     
										   '</tr>';
										  '</tr>';
							 })
	result += 			'</tbody>' + 
	    			'</table>' +      
	    			'<span>총 운동 시간: ' +  
	    				'<input type="number" id="totalTime" class= "inputs" style="width: 10%;" ' + readOnly + ' value=' + totalTime + ' ' + readOnly +'min="1"> 분' +  
	    			'</span>' +    
				'</div>' 
	
	return result; 
}  

function updateFitnessEvent(date){
	var fitnessObjList = [];
	var totalTime =  parseInt($("input[id='totalTime']").val()); 
	
	$('#fitnessEventListTable tr').each(function() {
    	var fitnessObj = {};    	
    	var parsedFitnessSEQ = parseInt($(this).find("td input[id=fitnessSEQ]").val(), 10);
    	var name = $(this).find("td input[id=nameValue]").val();
    	var parsedSet = parseInt($(this).find("td input[id=set]").val(), 10);
    	var parsedCount = parseInt($(this).find("td input[id=count]").val(), 10);
    	var parsedWeight = parseInt($(this).find("td input[id=weight]").val(), 10); 
    	var muscleGroup = $(this).find("td input[id=muscleGroupValue]").val();
    	var equipment = $(this).find("td input[id=equipmentValue]").val();
    	
    	fitnessObj["fitnessSEQ"] = parsedFitnessSEQ;
    	fitnessObj["name"] = name;
    	fitnessObj["set"] = parsedSet;
    	fitnessObj["count"] = parsedCount;
    	fitnessObj["weight"] = parsedWeight;
    	fitnessObj["muscleGroup"] = muscleGroup;
    	fitnessObj["equipment"] = equipment;
    	
    	fitnessObjList.push(fitnessObj);
	}) 
	fitnessObjList.shift(); // [0] 에 저장된 undefined 없애기
	
	$.ajax({
		url: "/record/updateFitness.do", 
		type: "POST",
		data: {"fitnessList": convertString(fitnessObjList), "date": date, "totalTime": totalTime},
		success: successRun,
		error: errorRun 
		}) 
		function successRun(result){  
			
			if(result > 0){ 
				mySwal.fire({
				  icon: 'success',
				  title: '업데이트가 반영되었습니다!',
				})
				
			}
			else{
				mySwal.fire({
				  icon: 'error',
				  title: '업데이트 중 에러가 생겼습니다... 관리자에게 문의 바랍니다.',
				})
			}
		}  
		function errorRun(obj, msg,statusMsg){  
			console.log(obj);
			console.log(msg);
			console.log(statusMsg);
		} 	 			
}

// 무산소 운동 관련 끝

// 음식 관련 시작 

function getAllFoodHTML(foodList){
	var result = '<div class = "swal-text">' +     
				      '<table class="table table-bordered" id="foodEventListTable" width="100%" cellspacing="0">' +  
                    	'<thead><tr>' + 
	                    	'<th>' + '음식 이름' + '</th>' +  
	                    	'<th>' + '1회 섭취량' + '</th>' + 
	                    	'<th>' + '칼로리' + '</th>' +   
	                    	'<th>' + '탄수화물 함량' + '</th>' + 
	                    	'<th>' + '단백질 함량' + '</th>' + 
	                    	'<th>' + '지방 함량' + '</th>' + 
	                    	'<th>' + '콜레스트롤 함량' + '</th>' +   
	                    	'<th>' + '섭취량 (g)' + '</th>' +   
	                    	'<th>' + '선택' + '</th>' +   
	                    	'<th style="display: none">' + 'foodSEQ' + '</th>' + 
				    	'</tr></thead>' +  
				    	'<tbody>';	    	
						foodList.map(function(food){								      	
					  	result += '<tr>' +
									'<td>' + food.name + '</td>' + 
									'<td>' + food.quantity + '</td>' + 
									'<td>' + food.calory + '</td>' + 
									'<td>' + food.carb + '</td>' + 
									'<td>' + food.protein + '</td>' + 
									'<td>' + food.fat + '</td>' + 
									'<td>' + food.cholesterol + '</td>' +  
									'<td>' +  
										'<input type="number" id="quantityValue" class= "inputs" min="1">' +
									'</td>' +      
									'<td>' + 
										'<input type="checkbox" id="selectedFood">' + 
					  				'</td>' + 
									'<td style="display: none">"' + 
										'<input type="number" id="foodSEQ" class= "inputs" value=' + food.foodSEQ +'>' +  
									'</td>' +   
									'<td style="display: none">"' + 
												'<input type="text" id="nameValue" class= "inputs" value=' + food.name +'>' +  
									'</td>' +   
								   '</tr>';
						})
	result +=	  '</tbody>' + 
				'</table>' +    
			 '</div>' 
	
	return result;
} 


function createFoodEvent(){
	var foodObjList = [];
	// food는 food 테이블 참조를 통해 1회 섭취량 대비 칼로리등을 구해야 하므로 서버에 보낼 때 최소한의 정보만 보낸다.
	$('#foodEventListTable tr').each(function() {
    	if($(this).find("td input[id=selectedFood]").is(':checked')){
    		var foodObj = {};    	
	    	var parsedFoodSEQ = parseInt($(this).find("td input[id=foodSEQ]").val(), 10);  
	    	var name = $(this).find("td input[id=nameValue]").val();
	    	var parsedQuantity = parseInt($(this).find("td input[id=quantityValue]").val(), 10); 
	    	
	    	foodObj["foodSEQ"] = parsedFoodSEQ;
	    	foodObj["name"] = name;	    	
	    	foodObj["quantity"] = parsedQuantity;
	    	
	    	foodObjList.push(foodObj);    	
    	} //if...checked 닫기     	
	}) 
	console.log("foodObjList: ", foodObjList);
	$.ajax({
		url: "/record/createFood.do", 
		type: "POST",
		data: {"foodList": convertString(foodObjList), "date": new Date()},
		success: successRun,
		error: errorRun 
		}) 
		function successRun(result){  
			
			if(result > 0){ 
				mySwal.fire({
				  icon: 'success',
				  title: '업데이트가 반영되었습니다!',
				})
				
			}
			else{
				mySwal.fire({
				  icon: 'error',
				  title: '업데이트 중 에러가 생겼습니다... 관리자에게 문의 바랍니다.',
				})
			}
		}  
		function errorRun(obj, msg,statusMsg){  
			console.log(obj);
			console.log(msg);
			console.log(statusMsg);
		} 	 			
}

//foodObj 생성 후의 읽기
function readFoodEvent(info, readOnly){ 
	foodObj = info.event.extendedProps.foodObj;
	
	result = '<div class = "swal-text">' +     
				      '<table class="table table-bordered" id="foodEventListTable" width="100%" cellspacing="0">' +  
                    	'<thead><tr>' + 
	                    	'<th>' + '음식 이름' + '</th>' + 
	                    	'<th>' + '섭취량' + '</th>' + 
	                    	'<th>' + '총 칼로리' + '</th>' + 
	                    	'<th>' + '단백질량' + '</th>' + 
	                    	'<th>' + '지방량' + '</th>' + 
	                    	'<th>' + '콜레스트롤' + '</th>' +   
	                    	deleteTag[0] + 
	                    	'<th style="display: none">' + 'foodSEQ' + '</th>' +
				    	'</tr></thead>' +
	      				'<tbody>';  
		      				 foodObj.map(function(food){								      	
						      	result += '<tr>' +
											'<td>' + food.name + '</td>' +  
											'<td>' + 
												'<input type="number" id="quantity" class= "inputs" ' + readOnly + ' value=' + food.quantity + ' ' + readOnly +'min="1">' + 
											'</td>' +  
											'<td>' + food.calory + '</td>' +    
											'<td>' + food.protein + '</td>' +    
											'<td>' + food.fat + '</td>' +    
											'<td>' + food.cholesterol + '</td>' +  
											deleteTag[1] +     
											'<td style="display: none">"' + 
												'<input type="number" id="foodSEQ" class= "inputs" value=' + food.foodSEQ +'>' +  
											'"</td>' + 
											'<td style="display: none">"' + 
												'<input type="text" id="nameValue" class= "inputs" value=' + food.name +'>' +  
											'</td>' + 
										  '</tr>';
							 })
	result+=  			'</tbody>' + 
	               '</table>' +            
	   			'</div>' 
	
	return result; 
}  

function updateFoodEvent(date){ 
	var foodObjList = [];
	
	// 화면에 띄운 모든 음식 목록을 [{"foodSEQ": 숫자}, {"quantity": 숫자}] 형태로 가져오기
	$('#foodEventListTable tr').each(function() {
    	var foodObj = {};    	
    	var name = $(this).find("td input[id=nameValue]").val();   	
    	var parsedFoodSEQ = parseInt($(this).find("td input[id=foodSEQ]").val(), 10);
    	var parsedQuantity = parseInt($(this).find("td input[id=quantity]").val(), 10);
    	
    	foodObj["foodSEQ"] = parsedFoodSEQ;
    	foodObj["name"] = name;
    	foodObj["quantity"] = parsedQuantity;
    	foodObjList.push(foodObj);
	}) 
	foodObjList.shift(); // [0] 에 저장된 undefined 없애기
	
	$.ajax({
		url: "/record/updateFood.do", 
		type: "POST",
		data: {"foodList": convertString(foodObjList), "date": date},
		success: successRun,
		error: errorRun 
		}) 
		function successRun(result){  
			
			if(result > 0){ 
				mySwal.fire({
				  icon: 'success',
				  title: '업데이트가 반영되었습니다!',
				})
				
			}
			else{
				mySwal.fire({
				  icon: 'error',
				  title: '업데이트 중 에러가 생겼습니다... 관리자에게 문의 바랍니다.',
				})
			}
		}  
		function errorRun(obj, msg,statusMsg){  
			console.log(obj);
			console.log(msg);
			console.log(statusMsg);
		} 	 			
} 

// 음식 관련 끝

// status 관련 시작

// 상태점수 생성 시 띄어질 HTML (searchEvent 참고)
function getAllStatusHTML(){ 
		
	result = '<div class = "swal-text" style="font-size: 250%">' +     
				'<div> 상태점수: ' + 
					'<select id="status">' +   
						'<option value="0">선택</option>';
						
						for(var i=1; i<=5; i++){
							result+= '<option value=' + i +'>' + i + '</option>'; 
						}   
	result += 		'</select>' + 
				'</div>' + 	                  
		   	'</div>' 

	return result; 
}   

// 상태 점수 생성 
function createStatusEvent(date){ 
	
	var status = $('#status').val(); 
	
	if(status < 1) {
		mySwal.fire({
		  icon: 'error',
		  title: '상태 점수를 골라주세요!',
		}) 
		return;
	}
	
	$.ajax({
		url: "/record/createStatus.do", 
		type: "POST",
		data: {"status": status, "date": date},
		success: successRun,
		error: errorRun 
		}) 
		function successRun(result){  
			
			if(result > 0){ 
				mySwal.fire({
				  icon: 'success',
				  title: '업데이트가 반영되었습니다!',
				})
				
			}
			else{
				mySwal.fire({
				  icon: 'error',
				  title: '업데이트 중 에러가 생겼습니다... 관리자에게 문의 바랍니다.',
				})
			}
		}  
		function errorRun(obj, msg,statusMsg){  
			console.log(obj);
			console.log(msg);
			console.log(statusMsg);
		} 	 			
}

// status 생성 후 읽기
function readStatusEvent(info, readOnly){ 
	var userStatus = info.event.extendedProps.status;  
	//select tag에는 readonly 속성이 없어서 변환이 필요함.
	var disabled = readOnly == "readonly" ? "disabled": ""; 
	
	result = '<div class = "swal-text" style="font-size: 250%">' +     
				'<div> 상태점수: ' + 
					'<select id="status" ' + disabled + '>' +   
						'<option value="0">선택</option>';
						
						for(var i=1; i<=5; i++){
							var selected = i == userStatus ? "selected":" "; 
							result+= '<option value=' + i +' ' + selected +' >' + i + '</option>'; 
						}   
	result += 		'</select>' + 
				'</div>' + 	                  
		   	'</div>' 

	return result; 
}  

function updateStatusEvent(date){ 
	
	var status = $('#status').val(); 
	
	if(status < 1) {
		mySwal.fire({
		  icon: 'error',
		  title: '상태 점수를 골라주세요!',
		}) 
		return;
	}
	
	$.ajax({
		url: "/record/updateStatus.do", 
		type: "POST",
		data: {"status": status, "date": date},
		success: successRun,
		error: errorRun 
		}) 
		function successRun(result){  
			
			if(result > 0){ 
				mySwal.fire({
				  icon: 'success',
				  title: '업데이트가 반영되었습니다!',
				})
				
			}
			else{
				mySwal.fire({
				  icon: 'error',
				  title: '업데이트 중 에러가 생겼습니다... 관리자에게 문의 바랍니다.',
				})
			}
		}  
		function errorRun(obj, msg,statusMsg){  
			console.log(obj);
			console.log(msg);
			console.log(statusMsg);
		} 	 			
} 

// status 관련 끝

// 처음 보여지는 화면의 날짜 
function getInitialDate(){
	return new Date(year, month, date);
}

// 이전 월 조회 버튼 시작
function customPrev_onClick() { 
     
     month -= 1;  
     var date = new Date(year, month); 
     year = date.getFullYear();
     month = date.getMonth();  
      
	 return date;
};

// 이전 월 조회 버튼 끝  

// 다음 월 조회 버튼 시작
function customNext_onClick() {
 	 month += 1;  
     var date = new Date(year, month); 
     year = date.getFullYear();
     month = date.getMonth();  
      
	 return date;
};
// 다음 월 조회 버튼 끝 


// 기타

// 두 날짜 비교 후 같은 날짜인지의 여부 확인 
function isSameDate(d1, d2){
	var date1 = new Date(d1);
	var date2 = new Date(d2); 
	
	var isSameYear = date1.getFullYear() == date2.getFullYear();
	var isSameMonth = date1.getMonth() == date2.getMonth();
	var isSameDate = date1.getDate() == date2.getDate(); 	
	
	return (isSameYear && isSameMonth && isSameDate);
}


// 오늘 이벤트가 아니면 편집 불가능하도록 처리.
function checkReadOnly(date){
	var convertedDate = new Date(date);
	var today = new Date();
	
	var readOnly = "readonly"; 
	
	// 오늘 이벤트는 readonly가 안 되도록 설정
	if(isSameDate(date, today)){
		readOnly = "";
	}
	return readOnly;
}	

function convertString(obj){
	return JSON.stringify(obj);
} 

// 오늘 이벤트일 경우 삭제 버튼 추가
function createDeleteTag(readOnly){
	var isTodayEvent = readOnly != "readonly";
	
	if(isTodayEvent){
		deleteTag[0] = '<th>삭제</th>';
		deleteTag[1] = '<td>' + 
					   		'<button type="button" id="deleteOneBTN" class="btn btn-danger" style="text-align: center;" onclick="deleteCurrentRow(this)">삭제</button>' + 
						'</td>';
	} 
	else{
		deleteTag[0] = '';
		deleteTag[1] = '';
	}
}

function deleteCurrentRow(thisObj){  
	$(thisObj).closest('tr').remove();	
}

// 하나의 이벤트 (유산소, 무산소, 식단, 상태 중 1) 삭제. Controller의 deleteField에 대응.
function deleteEvent(date, field){
	mySwal.fire({
		icon: 'warning',
		title: '삭제한 기록은 복구할 수 없습니다. 정말 전체 삭제하겠습니까?',
		showCancelButton: true,
		cancelButtonText: "취소",
		confirmButtonText: "삭제",  
		showLoaderOnConfirm: true, 
		reverseButtons: true 
		
	}).then(function(result){
		if(result.isConfirmed){
			$.ajax({
				url: "/record/deleteField.do", 
				type: "POST",
				data: {"field": field, "date": date},
				success: successRun,
				error: errorRun 
				}) //ajax 닫기 
				
				function successRun(result){  
					
					if(result > 0){ 
						mySwal.fire({
						  icon: 'success',
						  title: '성공적으로 삭제 되었습니다!',
						})	
					}
					else{
						mySwal.fire({
						  icon: 'error',
						  title: '업데이트 중 에러가 생겼습니다... 관리자에게 문의 바랍니다.',
						})
					}
				}  
				function errorRun(obj, msg,statusMsg){  
					console.log(obj);
					console.log(msg);
					console.log(statusMsg);
				} 	 
		} //if(isConfirmed == "true") 닫기 
	}) //then 닫기 
} // deleteEvent 함수 닫기

