var isNickNameChecked = true; 
const NICKNAME_MIN_LENGTH = 5; 

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
					  title: '',
					  text: '입력하신 닉네임을 사용할 수 있습니다',
					})
				}
				else{
					Swal.fire({
					  icon: 'error',
					  title: '',
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


function updateInfoBTN_onClick(){
	if(isNickNameChecked){
	$.ajax({
				url: "/mypage/updateInfo", 
				type: "POST",
				data: {nickName: $("#nickName").val(),memberSEQ: $("#memberSEQ").val()},
				success: successRun,
				}) 
				function successRun(data){
					$("#nickName").val(data.nickName);
					Swal.fire({
					  icon: 'success',
					  title: '',
					  text: '변경 되었습니다',
					})
				}
	}
	else{
				Swal.fire({
					  icon: 'error',
					  title: '',
					  text: '닉네임을 체크해주세요',
					})
	}
}