var cardioList = [];
var fitnessList = [];
var foodList = [];
var result = "";
var d = new Date();
var year = d.getFullYear(); 
var month = d.getMonth(); 
var date = d.getDate(); 
  

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
			var fetchedEvent = [];  
            
            data.map(function(record, recordIndex, recordArray){
            	var date = record.date; 
            	
            	fetchedEvent.push({ 
	    			"groupId": "cardio",
	   				"title": "유산소 운동 기록 내역", 
	       			"start": date, 
	       			"backgroundColor": "#2E59D9",
	       			"extendedProps": { "totalTime": record.cardioObj.totalTime, 
	       				 			   "cardioList": record.cardioObj.cardioList, 
	       				 			   "date": record.date}	
	   			}); 
            	
            	fetchedEvent.push({ 
            			"groupId": "fitness",
           				"title": "무산소 운동 기록 내역", 
	           			"start": date, 
	           			"backgroundColor": "#2C9FAF",
	           			"extendedProps": { "totalTime": record.fitnessObj.totalTime, 
	           							   "fitnessList": record.fitnessObj.fitnessList, 
	       				 			   		"date": record.date}	
           		}); 
            	 	
        		fetchedEvent.push({
       				"groupId": "food",
       				"title": "식사 기록 내역", 
           			"start": date, 
           			"backgroundColor": "#17A673",
           			"extendedProps": {"foodObj": record.foodObj, 
	       				 			   "date": record.date}	
           			});  

        		fetchedEvent.push({
       				"groupId": "status",
       				"title": "상태 점수", 
           			"start": date, 
           			"backgroundColor": "#17A673",
           			"extendedProps": {"status": record.status, 
	       				 			   "date": record.date}	
           			});  
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
	            
	             headerToolbar: {
	                left: 'customPrev,customNext today',
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
 	var readOnly = checkReadOnly(info.event.extendedProps.date); // 오늘 이벤트일 경우에만 수정 가능토록 하며 버튼 문구 다르게.
	var title = "";  
	var content = {icon: 'info',  
				   customClass: 'event-read',
				  }; 
	// 편집 가능한 이벤트일 경우  
	if(readOnly != "readonly"){
		content["showDenyButton"] = true; 
		content["denyButtonText"] = "취소"; 
		content["confirmButtonText"] = "저장";  
		content["showLoaderOnConfirm"] = true; 
		content["reverseButtons"] = true; 
	} 
	
	
	switch(info.event.groupId){
		case 'cardio': 
			title = '유산소 운동 목록입니다.'; 
			content["title"] = title; 
			content["html"] = readCardioEvent(info, readOnly); 
			content["preConfirm"] = () => {updateCardioEvent(info.event.extendedProps.date);} 		
			break;
		
		case 'fitness': 
			title = '무산소 운동 목록입니다.'; 
			content["title"] = title; 
			content["html"] = readFitnessEvent(info, readOnly);  
			content["preConfirm"] = () => {updateFitnessEvent(info.event.extendedProps.date);}	
			break;
		
		case 'food': 
			title = '식사 목록입니다.';
			content["title"] = title; 
			content["html"] = readFoodEvent(info);
			content["preConfirm"] = () => {updateFoodEvent(info.event.extendedProps.date);} 	
			break;

		case 'status': 
			title = '상태 점수 입니다.';
			content["title"] = title; 
			content["html"] = readStatusEvent(info); 	
			content["preConfirm"] = () => {updateStatusEvent(info.event.extendedProps.date);}
			break;
	}   
	
	// 편집 불가능한 이벤트일 경우 업데이트 이벤트 핸들러 제거
	if(readOnly == "readonly"){
		content["preConfirm"] = () => {}	
	}
	
	mySwal.fire(content);
	
} //showEventInfo 닫기

// 유산소 운동 관련 시작
function readCardioEvent(info, readOnly){ 
	cardioList = info.event.extendedProps.cardioList;
	
	result = '<div class = "swal-text">' +     
				      '<table class="table table-bordered" id="cardioEventListTable" width="100%" cellspacing="0">' +  
                    	'<thead><tr>' + 
	                    	'<th>' + '유산소 운동 이름' + '</th>' + 
	                    	'<th>' + '운동 시간 (분)' + '</th>' + 
	                    	'<th>' + '소모 칼로리' + '</th>' + 
	                    	'<th>' + '운동 강도' + '</th>' + 
	                    	'<th style="display: none">' + 'cardioSEQ' + '</th>' + 
				    	'</tr></thead>' +
	      				'<tbody>';  
		      				 cardioList.map(function(cardio){								      	
						      	result += '<tr>' +
											'<td>' + cardio.name + '</td>' +  
											'<td>' + 
												'<input type="number" id="time" class= "inputs" value=' + cardio.time + ' ' + readOnly +'min="1">' +  												 
											'</td>' +  
											'<td>' + cardio.calory + '</td>' +    
											'<td>' + getIntensityComment(cardio.intensity) + '</td>' + 
											'<td style="display: none">"' + 
												'<input type="number" id="cardioSEQ" class= "inputs" value=' + cardio.cardioSEQ +'>' +  
											'"</td>' +     
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
    	var parsedTime = parseInt($(this).find("td input[id=time]").val(), 10);
    	
    	cardioObj["cardioSEQ"] = parsedCardioSEQ;
    	cardioObj["time"] = parsedTime;
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
	                    	'<th style="display: none">' + 'fitnessSEQ' + '</th>' +
				    	'</tr></thead>' +
	      				'<tbody>';  
		      				 fitnessList.map(function(fitness){								      	
						      	result += '<tr>' + 
											'<td>' + fitness.name + '</td>' +  
											'<td>' + 
												'<input type="number" id="set" class= "inputs" value=' + fitness.set + ' ' + readOnly + 'min="1">' +  
											'</td>' +  
											'<td>' + 
												'<input type="number" id="count" class= "inputs" value=' + fitness.count + ' ' + readOnly +'min="1">' +  
											'</td>' +  
											'<td>' + 
												'<input type="number" id="weight" class= "inputs" value=' + fitness.weight + ' ' + readOnly +'min="1">' +  
											'</td>' +    
											'<td>' + fitness.muscleGroup + '</td>' +    
											'<td>' + fitness.equipment + '</td>' +    
											'<td style="display: none">"' + 
												'<input type="number" id="fitnessSEQ" class= "inputs" value=' + fitness.fitnessSEQ +'>' +  
											'"</td>' + 
										  '</tr>';
							 })
	result += 			'</tbody>' + 
	    			'</table>' +      
	    			'<span>총 운동 시간: ' +  
	    				'<input type="number" id="totalTime" class= "inputs" style="width: 10%;" value=' + totalTime + ' ' + readOnly +'min="1"> 분' +  
	    				
	    			'</span>' +    
				'</div>' 
	
	return result; 
}  

function updateFitnessEvent(date){
	var fitnessObjList = [];
	var totalTime =  parseInt($("input[id='totalTime']").val()); 
	
	// 화면에 띄운 모든 무산소 운동 목록을 [{"totalTime": 숫자} {"fitnessSEQ", 숫자}, {"set", 숫자}, {"count", 숫자}, {"weight", 숫자}] 형태로 가져오기
	$('#fitnessEventListTable tr').each(function() {
    	var fitnessObj = {};    	
    	var parsedFitnessSEQ = parseInt($(this).find("td input[id=fitnessSEQ]").val(), 10);
    	var parsedSet = parseInt($(this).find("td input[id=set]").val(), 10);
    	var parsedCount = parseInt($(this).find("td input[id=count]").val(), 10);
    	var parsedWeight = parseInt($(this).find("td input[id=weight]").val(), 10);
    	
    	fitnessObj["fitnessSEQ"] = parsedFitnessSEQ;
    	fitnessObj["set"] = parsedSet;
    	fitnessObj["count"] = parsedCount;
    	fitnessObj["weight"] = parsedWeight;
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
function readFoodEvent(info){ 
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
	                    	'<th style="display: none">' + 'foodSEQ' + '</th>' +
				    	'</tr></thead>' +
	      				'<tbody>';  
		      				 foodObj.map(function(food){								      	
						      	result += '<tr>' +
											'<td>' + food.name + '</td>' +  
											'<td>' + 
												'<input type="number" id="amount" class= "inputs" value=' + food.amount + ' ' + readOnly +'min="1">' + 
											'</td>' +  
											'<td>' + food.calory + '</td>' +    
											'<td>' + food.protein + '</td>' +    
											'<td>' + food.fat + '</td>' +    
											'<td>' + food.cholesterol + '</td>' +     
											'<td style="display: none">"' + 
												'<input type="number" id="foodSEQ" class= "inputs" value=' + food.foodSEQ +'>' +  
											'"</td>' +
										  '</tr>';
							 })
	result+=  			'</tbody>' + 
	               '</table>' +            
	   			'</div>' 
	
	return result; 
}  

function updateFoodEvent(date){ 
	var foodObjList = [];
	
	// 화면에 띄운 모든 음식 목록을 [{"foodSEQ": 숫자}, {"amount": 숫자}] 형태로 가져오기
	$('#foodEventListTable tr').each(function() {
    	var foodObj = {};    	
    	var parsedFoodSEQ = parseInt($(this).find("td input[id=foodSEQ]").val(), 10);
    	var parsedAmount = parseInt($(this).find("td input[id=amount]").val(), 10);
    	
    	foodObj["foodSEQ"] = parsedFoodSEQ;
    	foodObj["amount"] = parsedAmount;
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
function readStatusEvent(info){ 
	var status = info.event.extendedProps.status;
	
	result = '<div class = "swal-text">' +     
				'<div> 상태점수: ' + status + '</div>' + 	                  
	   		 '</div>' 
	
	return result; 
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

// 오늘 이벤트가 아니면 편집 불가능하도록 처리.
function checkReadOnly(date){
	var convertedDate = new Date(date);
	var today = new Date();
	var isSameYear = convertedDate.getFullYear() == today.getFullYear();
	var isSameMonth = convertedDate.getMonth() == today.getMonth();
	var isSameDate = convertedDate.getDate() == today.getDate();
	
	var readOnly = "readonly"; 
	
	// 오늘 이벤트는 readonly가 안 되도록 설정
	if(isSameYear && isSameMonth && isSameDate){
		readOnly = "";
	}
	return readOnly;
}	

function convertString(obj){
	return JSON.stringify(obj);
}


