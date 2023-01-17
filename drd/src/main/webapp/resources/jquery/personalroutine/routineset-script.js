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

 
function selectPersonalRoutine_onclick(){
	$.ajax({
	type:"post",
	url:"/personalroutine/ajax/setfitnesslist",
	data:{
		"personalRoutineSEQ":personalRoutineSEQ
	},
	async : false,
	success: successRun1,
	error:function(a,b,c){
		alert(c);
	}
	})//end ajax1
	function successRun1(){
			mydata = "<div class='w-100' style='float:left; width:100%'>"
			+"<div style='float:left; width:70%'>name</div>"
			+"<div style='float:left; width:10%'>set</div>"
			+"<div style='float:left; width:10%'>count</div>"
			+"<div style='float:left; width:10%'>weight</div>"
			+"</div><br/>";
			for (var i = 0; i < data.length; i++) {
				mydata = mydata+
				"<div class='fitness' style='float:left; width:100%'>"
				+"<div class='seq' style='display: none'>"+data[i].fitnessSEQ+"</div>"
				+"<div style='float:left; width:70%'>"+data[i].name+"</div>"
				+"<div style='float:left; width:10%'>"+data[i].set+"</div>"
				+"<div style='float:left; width:10%'>"+data[i].count+"</div>"
				+"<div style='float:left; width:10%'>"+data[i].weight+"kg</div>"
				+"</div><br/>";
			}
			$("#myroutine_fitnessList").empty();
			$("#myroutine_fitnessList").append(mydata);
	}
	$.ajax({
		type:"post",
		url:"/personalroutine/ajax/setcardiolist",
		data:{
			"personalRoutineSEQ":personalRoutineSEQ
		},
		async : false,
		success:successRun2,
		error:function(a,b,c){
			alert(c);
		}
					
	})//end ajax2
	function successRun2(){
		mydata = "<div style='float:left; width:100%'>"
		+"<div class='w-75' style='float:left'>name</div>"
		+"<div class='w-auto' style='float:left'>time</div>"
		+"</div><br/>";
		for (var i = 0; i < data.length; i++) {
			mydata = mydata+
			"<div class='cardio' style='float:left; width:100%'>"
			+"<div class='seq' style='display: none'>"+data[i].cardioSEQ+"</div>"
			+"<div class='w-75' style='float:left'>"+data[i].name+"</div>"
			+"<div class='w-auto' style='float:left'>"+data[i].time+"분</div>"
			+"</div><br/>"
		}
		$("#myroutine_cardioList").empty();
		$("#myroutine_cardioList").append(mydata);
	}
}
			
			
function selectFitness_onclick(){
	var fitnessSEQ = $(this).children(".seq").text();
	$.ajax({
	type:"post",
	url:"/personalroutine/ajax/setfitnesschange",
	data:{
		"personalRoutineSEQ":personalRoutineSEQ,
		"fitnessSEQ":fitnessSEQ
	},
	async : false,
	success:successRun1,
	error:function(a,b,c){
		alert(c);
	}
	})
	fuction successRun1(data){
		mydata = "<form action=''>"
				 + "<div class='w-100'>set : <div style='float:right; text-align: right;'><input type='number' min='1' value='"+data.set+"' name='set'></div></div><br/>"
				 + "<div class='w-100'>count : <div style='float:right; text-align: right;'><input type='number' min='1' value='"+data.count+"' name='count'></div></div><br/>"
				 + "<div class='w-100'>weight : <div style='float:right; text-align: right;'><input type='number' min='1' value='"+data.weight+"' name='weight'></div></div><br/>"
				 + "<div style='text-align: right; '>"
		         + "<a href='#' class='btn btn-success btn-icon-split'>"
					+ "<span class='icon text-white-50'>"
					+ "<i class='fas fa-check'></i>"
					+ "</span>"
					+ "<span class='text'>수정하기</span>"
				+ "</a>"
				 +"<a href='#' class='btn btn-danger btn-icon-split' >"
					+ "<span class='icon text-white-50'>"
						+ "<i class='fas fa-trash'></i>"
					+ "</span>"
					+ "<span class='text'>삭제하기</span>"
				+ "</a>";
		$("#ajaxchangename").empty();
		$("#ajaxchangename").append("운동 수정");					
		$("#ajaxchangecontents").empty();
		$("#ajaxchangecontents").append(mydata);
	}
	$.ajax({
	type:"post",
	url:"/personalroutine/ajax/setfitness",
	data:{
		"fitnessSEQ":fitnessSEQ
	},
	async : false,
	success:successRun2,
	error:function(a,b,c){
		alert(c);
	}
	})
	fuction successRun2(data){
		mydata = "<div style='text-align: center;'>"+data.name+"</div><br/>"
		+ "<div style='text-align: right; float:right;'>"+data.muscleGroup+"</div><br/>"
		+ "<div style='text-align: right; float:right;'>"+data.equipment+"</div>"
		$("#check").empty();
		$("#check").append(mydata);
	}
}


function selectCardio_onclick(){
	var cardioSEQ = $(this).children(".seq").text();
	$.ajax({
		type:"post",
		url:"/personalroutine/ajax/setcardiochange",
		data:{
			"personalRoutineSEQ":personalRoutineSEQ,
			"cardioSEQ":cardioSEQ
		},
		async : false,
		success: successRun1,
		error:function(a,b,c){
			alert(c);
		}
		})//end ajax1cardiochange
		function successRun1(data){
			mydata = "<form action=''>"
				+ "<div class='w-100'>time : <div style='float:right; text-align: right;'>"
					+ "<input type='number' min='1' value='"+data.time+"' name='time'>"
				+ "</div></div><br/>"
				+ "<div style='text-align: right; '>"
			        + "<a href='#' class='btn btn-success btn-icon-split'>"
						+ "<span class='icon text-white-50'>"
						+ "<i class='fas fa-check'></i>"
						+ "</span>"
						+ "<span class='text'>수정하기</span>"
					+ "</a>"
					+"<a href='#' class='btn btn-danger btn-icon-split' >"
						+ "<span class='icon text-white-50'>"
							+ "<i class='fas fa-trash'></i>"
						+ "</span>"
						+ "<span class='text'>삭제하기</span>"
					+ "</a>"
				+ "</div>";
		$("#ajaxchangename").empty();
		$("#ajaxchangename").append("운동 수정");						
		$("#ajaxchangecontents").empty();
		$("#ajaxchangecontents").append(mydata);
		}
		$.ajax({
			type:"post",
			url:"/personalroutine/ajax/setcardio",
			data:{
				"cardioSEQ":cardioSEQ
			},
			async : false,
			success:successRun2,
			error:function(a,b,c){
				alert(c);
		}
		})
		function successRun2(data){
			intensity = "";
			if(data.intensity==0){
				intensity = "저강도";
			}else{
				intensity = "고강도";
			}
			mydata = "<div style='text-align: center;'>"+data.name+"</div><br/>"
			+ "<div style='text-align: right; float:right;'>"+intensity+"</div><br/>"
			+ "<div style='text-align: right; float:right;'>시간당 칼로리: "+data.calory+"</div>"
			$("#check").empty();
			$("#check").append(mydata);
		}
}