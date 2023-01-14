var isNickNameChecked = null; 
var isEmailChecked = null;  
var isPassChecked = null;
var isRoutineChecked = null;
var height = -1;
var weight = -1; 
var age = -1;   
var disease = -1;
var displayRoutineList = [];  

const ID_MIN_LENGTH = 5; 
const NICKNAME_MIN_LENGTH = 5; 
const EMAIL_REGEX = "^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-z]{2,3}$"; // . 없어도 통과되는 현상 확인 필요
const PASS_MIN_LENGTH = 5;  

 
// 닉네임 변경 시 다시 체크하도록 함
function nickName_onKeyUp(){  
	// 공백 입력 방지
	$("#nickName").val($("#nickName").val().replaceAll(" ", "")); 
	isNickNameChecked = false; 
} 

// 닉네임 사용 가능 여부 체크
function checkNickNameBTN_onClick(){
	var nickName = $("#nickName").val();	
	
	// HTML에서 minlength 가 안 되서 여기서 최소 길이 관련 로직 구현 함.
	if(nickName.length < NICKNAME_MIN_LENGTH){
		Swal.fire({
		  icon: 'error',
		  title: '닉네임은 최소 ' + NICKNAME_MIN_LENGTH + ' 글자 이상이어야 합니다!',
		})
		return;
	}

	$.ajax({
			url: "/member/findByNickName.do", 
			type: "POST",
			data: {"nickName": nickName} ,
			success: successRun,
			error: errorRun 
			}) 
			function successRun(member){  
				// DB에서 조회된 사용자(member)가 없을 때 사용자가 입력한 닉네임 사용 가능
				isNickNameChecked = (member == "");   
				
				if(isNickNameChecked){ 
					Swal.fire({
					  icon: 'success',
					  title: '축하합니다!',
					  text: '입력하신 닉네임을 사용할 수 있습니다',
					})
					
				}
				else{
					Swal.fire({
					  icon: 'error',
					  title: '안타깝습니다...',
					  text: '입력하신 닉네임을 사용할 수 없습니다',
					})
				}
			}  
			function errorRun(obj, msg,statusMsg){  
				console.log(obj);
				console.log(msg);
				console.log(statusMsg);
			} 			
} 

// 이메일 변경 시 다시 체크하도록 함
function email_onKeyUp(){ 
	// 공백 입력 방지
	$("#email").val($("#email").val().replaceAll(" ", ""));
	isEmailChecked = false; 
} 

// 이메일 사용 가능 여부 체크
function checkEmailBTN_onClick(){
	
	var notEmailRegexMatched = ($("#email").val().match(EMAIL_REGEX) == null)
	
	// HTML에서 이메일 체크가 제대로 안 되는 것 같아서 이메일 체크 기능 추가
	if(notEmailRegexMatched){
		Swal.fire({
		  icon: 'error',
		  title: '이메일 형식에 맞게 작성해주세요!',
		})
		return;
	}
	
	$.ajax({
			url: "/member/findByEmail.do", 
			type: "POST",
			data: {"email": $("#email").val()} ,
			success: successRun,
			error: errorRun 
			}) 
			function successRun(member){  
				// DB에서 조회된 사용자(member)가 없을 때 사용자가 입력한 닉네임 사용 가능
				isEmailChecked = (member == "");   
				
				if(isEmailChecked){ 
					Swal.fire({
					  icon: 'success',
					  title: '축하합니다!',
					  text: '입력하신 이메일을 사용할 수 있습니다',
					})
					
				}
				else{
					Swal.fire({
					  icon: 'error',
					  title: '안타깝습니다...',
					  text: '입력하신 이메일을 사용할 수 없습니다',
					})
				}
			}  
			function errorRun(obj, msg, statusMsg){  
				console.log(obj);
				console.log(msg);
				console.log(statusMsg);
			} 			
} 

// 비밀번호 공백 입력 방지 
function pass_onKeyUp(){  
	$("#pass").val($("#pass").val().replaceAll(" ", ""));	
	isPassChecked = false;
} 

// 비밀번호 입력이 끝난 후 길이 조건을 만족하는 지 체크
function pass_onFocusout(){  
	isPassChecked = !($("#pass").val()<PASS_MIN_LENGTH); 
	
} 

function get_age(){
	const today = new Date();
	const birth = new Date($("#birth").val());
	
	var todayMonthDate = today.getMonth()*100+today.getDate();  
	var birthMonthDate = birth.getMonth()*100+birth.getDate();  
	 
	age = today.getFullYear() - birth.getFullYear(); 
	age += todayMonthDate > birthMonthDate ? 0:-1; // 생일이 안 지났을 경우 나이-1 (만 나이 계산법 적용) 
	$("#age").val(age); // 서버에 전달할 나이 정보
}

// 루틴을 조회하기 위한 정보가 입력되었는 지 확인
function getRoutineListDisplayCondition(){ 
	var birth = $("#birth").val();   
	var gender = $("#gender").val(); 
	var height = $("#height").val();
	var weight = $("#weight").val();
	var disease = $("#disease").val();
	
	return birth !=="" && gender !== "" && 
		   height !== "" && weight !== "" && disease !== "";
}
 
function routineInfo_change(){ 
		
	height = $("#height").val();
	weight = $("#weight").val(); 
	age = $("#age").val(); 
	disease = $("#disease").val(); 
	  
	// routineListDisplayCondition 조건 미달성 시 루틴 추천 가리기  
	if(!getRoutineListDisplayCondition()){ 
		
		$("#routineList").css("display","none");
		return;
	}  
	else{
		get_routineList();
		$("#routineList").css("display","block");
	} 
	return;
}

function get_routineList(){ 
	$.ajax({
			url: "/member/findRoutineByRegisterInfo.do", 
			type: "POST",
			data: {disease: $("#disease").val()},
			success: successRun,
			error: errorRun 
			}) 
			function successRun(routines){   
				// 이전에 조회한 내역 지우기 
				$("#routineListTable > tbody").empty();	
				
				for(var i=0;i<routines.length;i++){
				
				$("#routineListTable > tbody:last-child").append(
						"<tr>" +
							"<td>" +routines[i]["name"]+ "</td>" +  
							"<td>" +routines[i]["description"]+ "</td>" +  
							"<td><input type='radio' name='selectedRoutine' id = 'selectedRoutine' value='"+routines[i]["routineSEQ"]+"'></td>" +  
						"</tr>") 
				}  
				
				Swal.fire({
				  icon: 'info',
				  title: "당신만을 위한 최적의 운동 루틴을 찾았습니다!",
				})
			}  
			function errorRun(obj, msg,statusMsg){  
				console.log(obj);
				console.log(msg);
				console.log(statusMsg);
			} 			
} 


//230112 현재 미사용. 추후 사용할 수 있을 것 같아서 남겨둠.
function findAllRoutineBTN_click(){ 
	$.ajax({
			url: "/member/findRoutineByRegisterInfo.do", 
			type: "POST",
			success: successRun,
			error: errorRun 
			}) 
			function successRun(routines){ 
				// 이전에 조회한 내역 지우기 
				$("#routineListTable > tbody").empty();	
				
				for(var i=0;i<routines.length;i++){
					
					$("#routineListTable > tbody:last-child").append(
						"<tr>" +
							"<td>" +routines[i]["name"]+ "</td>" +  
							"<td>" +routines[i]["description"]+ "</td>" +  
							"<td><input type='radio' name='selectedRoutine' id = 'selectedRoutine' value='"+routines[i].routineSEQ+"'></td>" +  
						"</tr>") 
				}  
				Swal.fire({
		  			icon: 'info',
		  			title: "모든 루틴을 조회하였습니다!",
				})
			}  
			function errorRun(obj, msg,statusMsg){  
				console.log(obj);
				console.log(msg);
				console.log(statusMsg);
			} 			
} 
	

// 회원 가입 전 아이디, 닉네임, 이메일, 비밀번호를 체크 했는 지 확인
function submitBTN_onClick(){

	if(!isNickNameChecked){
		Swal.fire({
		  icon: 'error',
		  title: '닉네임을 체크해주시기 바랍니다!',
		}) 
		return;	
	} 
	else if(!isEmailChecked){
		Swal.fire({
		  icon: 'error',
		  title: '이메일을 체크해주시기 바랍니다!',
		})
		return;	
	} 
	else if(!isPassChecked){
		Swal.fire({
		  icon: 'error',
		  title: '비밀번호의 최소' + PASS_MIN_LENGTH + ' 글자 이상이어야 합니다!',
		})
		return;	
	} 
	else if(!getRoutineListDisplayCondition()){
		Swal.fire({
		  icon: 'error',
		  title: '생년월일, 성별, 키, 몸무게 정보를 입력해주시기 바랍니다!',
		})
		return;
	}
	else if($("#activityLevel").val() == ""){
		Swal.fire({
		  icon: 'error',
		  title: '일주일 운동량을 입력해주시기 바랍니다!',
		})
		return;
	}
	else if(!$("input[name='selectedRoutine']:checked").val()){
		Swal.fire({
		  icon: 'info',
		  title: "당신만을 위한 최적의 운동 루틴을 찾았습니다. 아래에서 한 개를 선택해주세요!",
		})
		return;
	}	 
	else{
		console.log($('input[name=radioName]:checked', '#registerForm'))
		$("#registerForm").submit(); 
	}
} 

