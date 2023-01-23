var fitnessSEQ = -1;
var cardioSEQ = -1;
var personalRoutineSEQ = -1; 
var fitnessarray= [];
var cardioarray = [];
function setPersonalRoutineSEQ(key){
	personalRoutineSEQ = key;
}
function setFitnessArray(array){
	fitnessarray = array;
}
function setCardioArray(array){
	cardioarray = array;
}
function selectPersonalRoutine_onclick(){
	$.ajax({
	type:"post",
	url:"/personalroutine/ajax/setfitnesslist",
	data:{
		"personalRoutineSEQ": personalRoutineSEQ
	},
	async : false,
	success: successRun1,
	error:function(a,b,c){
		alert(c);
	}
	})//end ajax1
	function successRun1(data){
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
	function successRun2(data){
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
	fitnessSEQ = $(this).children(".seq").text();
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
	function successRun1(data){
		mydata = "<form id='updatefitness' action='/personalroutine/updatefitness' method='post'>"
				+ "<input type='hidden' value='"+personalRoutineSEQ+"' name='personalRoutineSEQ' id='personalRoutineSEQ'>"
				+ "<input type='hidden' value='"+data.fitnessSEQ+"' name='fitnessSEQ' id= 'fitnessSEQ'>"
				+ "<div class='w-100'>set : <div style='float:right; text-align: right;'><input type='number' min='1' value='"+data.set+"' name='set' id='set'></div></div><br/>"
				+ "<div class='w-100'>count : <div style='float:right; text-align: right;'><input type='number' min='1' value='"+data.count+"' name='count' id='count'></div></div><br/>"
				+ "<div class='w-100'>weight : <div style='float:right; text-align: right;'><input type='number' min='1' value='"+data.weight+"' name='weight' id='weight'></div></div><br/>"
				+ "<div style='text-align: right; '>"
		        + "<a href='#' onclick='return updateFitness_onclick()' class='btn btn-success btn-icon-split'>"
					+ "<span class='icon text-white-50'>"
					+ "<i class='fas fa-check'></i>"
					+ "</span>"
					+ "<span class='text'>수정하기</span>"
				+ "</a>"
				 +"<a href='#' onclick='deleteandupdateFitness_onclick()' class='btn btn-danger btn-icon-split' data-toggle='modal' data-target='#deleteandupdatemodal'>"
					+ "<span class='icon text-white-50'>"
						+ "<i class='fas fa-trash'></i>"
					+ "</span>"
					+ "<span class='text'>삭제하기</span>"
				+ "</a>";
		var array = [data.set, data.count, data.weight];
		setFitnessArray(array);
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
	function successRun2(data){
		mydata = "<div style='text-align: center;'>"+data.name+"</div><br/>"
		+ "<div style='text-align: right; float:right;'id ='musclegroup'>"+data.muscleGroup+"</div><br/>"
		+ "<div style='text-align: right; float:right;'>"+data.equipment+"</div>"
		$("#check").empty();
		$("#check").append(mydata);
	}
}


function selectCardio_onclick(){
	cardioSEQ = $(this).children(".seq").text();
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
			mydata = "<form id='updatecardio' action='/personalroutine/updatecardio' method='post'>"
				+ "<input type='hidden' value='"+personalRoutineSEQ+"' name='personalRoutineSEQ' id='personalRoutineSEQ'>"
				+ "<input type='hidden' value='"+data.cardioSEQ+"' name='cardioSEQ' id='cardioSEQ'>"
				+ "<div class='w-100'>time : <div style='float:right; text-align: right;'>"
					+ "<input type='number' min='1' value='"+data.time+"' name='time' id='time'>"
				+ "</div></div><br/>"
				+ "<div style='text-align: right; '>"
			        + "<a href='#' onclick='return updateCardio_onclick()' class='btn btn-success btn-icon-split'>"
						+ "<span class='icon text-white-50'>"
						+ "<i class='fas fa-check'></i>"
						+ "</span>"
						+ "<span class='text'>수정하기</span>"
					+ "</a>"
					+"<a href='#' onclick='deleteandupdateCardio_onclick()' class='btn btn-danger btn-icon-split' data-toggle='modal' data-target='#deleteandupdatemodal'>"
						+ "<span class='icon text-white-50'>"
							+ "<i class='fas fa-trash'></i>"
						+ "</span>"
						+ "<span class='text'>삭제하기</span>"
					+ "</a>"
				+ "</div>";
		var array = [data.time];
		setCardioArray(array);
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
		+ "<div style='text-align: right; float:right; display:none;' id ='intensity'>"+data.intensity+"</div><br/>"
		+ "<div style='text-align: right; float:right;'>시간당 칼로리: "+data.calory+"</div>"
		$("#check").empty();
		$("#check").append(mydata);
	}
}
function updateFitness_onclick(){
	if(document.getElementById("set").value==fitnessarray[0] && document.getElementById("count").value==fitnessarray[1] && document.getElementById("weight").value==fitnessarray[2]){
	alert("변화가 없습니다.");
	return false;
	}
	document.getElementById("updatefitness").submit();
	
}

function updateCardio_onclick(){
	if(document.getElementById("time").value==cardioarray[0]){
	alert("변화가 없습니다.");
	return false;
	}
	document.getElementById("updatecardio").submit();
	
}

function deleteandupdateFitness_onclick(){
	fitnessSEQ = $("#fitnessSEQ").val();
	muscleGroup = $("#musclegroup").text();
	$.ajax({
		type:"post",
		url:"/personalroutine/findfitnessbymusclegroup",
		data:{
			"personalRoutineSEQ":personalRoutineSEQ,
			"muscleGroup":muscleGroup,
			"fitnessSEQ":fitnessSEQ
			
		},
		async : false,
		success: successRun1,
		error:function(a,b,c){
			alert(c);
		}
	})//end ajax1cardiochange
	function successRun1(data){
		head = "";
		body = "";
		foot = "";
		console.log(data.length);
		if(data.lengh=1){
			if(data[0].fitnessSEQ==0){
				head = "운동 삭제";
				body = "직접 추가한 운동입니다.";
				foot = "<button type='button' class='btn btn-primary' id='changesubmit' onclick=''>삭제</button>"
				+ "<button type='button' class='btn btn-secondary' data-dismiss='modal'>닫기</button>";
				console.log("case1")
			}else if(data[0].fitnessSEQ==fitnessSEQ){
				head = "운동수정";
				body = "대체 가능한 운동이 없습니다.";
				foot = "<button type='button' class='btn btn-secondary' data-dismiss='modal'>닫기</button>";
				console.log("case2")
			}else{
				head = "운동수정";
				body = "<h4><a href='#' onclick='selectTPOFitness_onclick()'>"+data[0].name+"<div style='display:none;' class ='afterfitnessSEQ'>"+data[0].fitnessSEQ+"</div>"
					+ "<div style='display:none;' class ='beforefitnessSEQ'>"+fitnessSEQ+"</div></a></h4>";
				foot = "<button type='button' class='btn btn-secondary' data-dismiss='modal'>닫기</button>";
			}
		}else{
			head = "운동수정";
			for (var i = 0; i < data.length; i++) {
				body = body + "<h4><a href='#' onclick='selectTPOFitness_onclick()'>"+data[i].name+"<div style='display:none;' class ='afterfitnessSEQ'>"+data[i].fitnessSEQ+"</div>"
					+ "<div style='display:none;' class ='beforefitnessSEQ'>"+fitnessSEQ+"</div></a></h4>";
			} 
			foot = "<button type='button' class='btn btn-secondary' data-dismiss='modal'>닫기</button>";
			console.log("case3")
		}
		$("#changetitle").empty();
		$("#changetitle").append(head);						
		$("#changethings").empty();
		$("#changethings").append(body);
		$("#changefoot").empty();
		$("#changefoot").append(foot);
	}
}

function deleteandupdateCardio_onclick(){
	cardioSEQ = $("#cardioSEQ").val();
	intensity = $("#intensity").text()
	$.ajax({
		type:"post",
		url:"/personalroutine/findcardiobyintensity",
		data:{
			"personalRoutineSEQ":personalRoutineSEQ,
			"cardioSEQ":cardioSEQ,
			"intensity":intensity
			
		},
		async : false,
		success: successRun1,
		error:function(a,b,c){
			alert(c);
		}
	})//end ajax1cardiochange
	function successRun1(data){
		head = "";
		body = "";
		foot = "";
		if(data.lengh=1){
			if(data[0].cardioSEQ==0){
				head = "운동 삭제";
				body = "직접 추가한 운동입니다.";
				foot = "<button type='button' class='btn btn-danger btn-icon-split' data-dismiss='modal' onclick=''>"
					+ "<span class='icon text-white-50'>"
						+ "<i class='fas fa-trash'></i>"
					+ "</span>"
					+ "<span class='text'>삭제하기</span>"
				+ "</button>"
				+ "<button type='button' class='btn btn-secondary' data-dismiss='modal'>닫기</button>";
			}else if(data[0].cardioSEQ==cardioSEQ){
				head = "운동수정";
				body = "대체 가능한 운동이 없습니다.";
				foot = "<button type='button' class='btn btn-secondary' data-dismiss='modal'>닫기</button>";
			}else{
				head = "운동수정";
				body = "<h4><a href='#' onclick='selectTPOCardio_onclick()'>"+data[0].name+"<div style='display:none;' class ='aftercardioSEQ'>"+data[0].cardioSEQ+"</div>"
					+ "<div style='display:none;' class ='beforecardioSEQ'>"+cardioSEQ+"</div></a></h4>";
				foot = "<button type='button' class='btn btn-secondary' data-dismiss='modal'>닫기</button>";
			}
		}else{
			head = "운동수정";
			for (var i = 0; i < data.length; i++) {
				body = body + "<h4><a href='#' onclick='selectTPOCardio_onclick()'>"+data[i].name+"<div style='display:none;' class ='aftercardioSEQ'>"+data[i].cardioSEQ+"</div>"
					+ "<div style='display:none;' class ='beforecardioSEQ'>"+cardioSEQ+"</div></a></h4>";
				
			} 
			foot = "<button type='button' class='btn btn-secondary' data-dismiss='modal'>닫기</button>";
		}
		$("#changetitle").empty();
		$("#changetitle").append(head);						
		$("#changethings").empty();
		$("#changethings").append(body);
		$("#changefoot").empty();
		$("#changefoot").append(foot);
	}
}

function selectTPOFitness_onclick(){
	afterfitnessSEQ = $(this).children(".afterfitnessSEQ").text();
	beforefitnessSEQ = $(this).children(".beforefitnessSEQ").text();
	$.ajax({
		type:"post",
		url:"/personalroutine/ajax/setfitness",
		data:{
			"fitnessSEQ":afterfitnessSEQ
		},
		async : false,
		success:successRun1,
		error:function(a,b,c){
			alert(c);
	}
	})
	function successRun1(data){
		mydata = "<div style='text-align: center;'>"+data.name+"</div><br/>"
		+ "<div style='text-align: right; float:right;'id ='musclegroup'>"+data.muscleGroup+"</div><br/>"
		+ "<div style='text-align: right; float:right;'>"+data.equipment+"</div><hr/>"
		body = mydata + "<form id='deleteandupdatefitness' action='' method='post'>"
				+ "<input type='hidden' value='"+personalRoutineSEQ+"' name='personalRoutineSEQ' id='personalRoutineSEQ'>"
				+ "<input type='hidden' value='"+data.fitnessSEQ+"' name='afterfitnessSEQ' id= 'afterfitnessSEQ'>"
				+ "<input type='hidden' value='"+beforefitnessSEQ+"' name='beforefitnessSEQ' id= 'beforefitnessSEQ'>"
				+ "<div class='w-100'>set : <div style='float:right; text-align: right;'><input type='number' min='1' value='20' name='set' id='set'></div></div><br/>"
				+ "<div class='w-100'>count : <div style='float:right; text-align: right;'><input type='number' min='1' value='5' name='count' id='count'></div></div><br/>"
				+ "<div class='w-100'>weight : <div style='float:right; text-align: right;'><input type='number' min='1' value='4' name='weight' id='weight'></div></div><br/>"
				+ "</form>"
		foot = "<button type='button' class='btn btn btn-success btn-icon-split' data-dismiss='modal' onclick='deleteandupdatefitness_do()'>"
					+ "<span class='icon text-white-50'>"
						+ "<i class='fas fa-check'></i>"
					+ "</span>"
					+ "<span class='text'>대체하기</span>"
				+ "</button>"
				+ "<button type='button' class='btn btn-secondary' data-dismiss='modal'>닫기</button>";	
		$("#changethings").empty();
		$("#changethings").append(body);
		$("#changefoot").empty();
		$("#changefoot").append(foot);
	}
}
function selectTPOCardio_onclick(){
	aftercardioSEQ = $(this).children(".aftercardioSEQ").text();
	beforecardioSEQ = $(this).children(".beforecardioSEQ").text();
	$.ajax({
	type:"post",
	url:"/personalroutine/ajax/setcardio",
	data:{
		"cardioSEQ":aftercardioSEQ
	},
	async : false,
	success:successRun1,
	error:function(a,b,c){
		alert(c);
	}
	})
	function successRun1(data){
		intensity = "";
		if(data.intensity==0){
			intensity = "저강도";
		}else{
			intensity = "고강도";
		}
		mydata = "<div style='text-align: center;'>"+data.name+"</div><br/>"
		+ "<div style='text-align: right; float:right;'>"+intensity+"</div><br/>"
		+ "<div style='text-align: right; float:right; display:none;' id ='intensity'>"+data.intensity+"</div><br/>"
		+ "<div style='text-align: right; float:right;'>시간당 칼로리: "+data.calory+"</div><hr/>"
		body = mydata + "<form id='deleteandupdatecardio' action='' method='post'>"
				+ "<input type='hidden' value='"+personalRoutineSEQ+"' name='personalRoutineSEQ' id='personalRoutineSEQ'>"
				+ "<input type='hidden' value='"+data.cardioSEQ+"' name='aftercardioSEQ' id= 'aftercardioSEQ'>"
				+ "<input type='hidden' value='"+beforecardioSEQ+"' name='beforecardioSEQ' id= 'beforecardioSEQ'>"
				+ "<input type='hidden' value='"+data.calory+"' name='calory' id= 'calory'>"
				+ "<div class='w-100'>time : <div style='float:right; text-align: right;'>"
					+ "<input type='number' min='1' value='30' name='time' id='time'>"
				+ "</div></div><br/>"
				+ "</form>"
		foot = "<button type='button' class='btn btn btn-success btn-icon-split' data-dismiss='modal' onclick='deleteandupdatecardio_do()'>"
					+ "<span class='icon text-white-50'>"
						+ "<i class='fas fa-check'></i>"
					+ "</span>"
					+ "<span class='text'>대체하기</span>"
				+ "</button>"
				+ "<button type='button' class='btn btn-secondary' data-dismiss='modal'>닫기</button>";	
		$("#changethings").empty();
		$("#changethings").append(body);
		$("#changefoot").empty();
		$("#changefoot").append(foot);
	}
}
function deleteandupdatefitness_do(){
	if(!confirm("수정(대체)하시겠습니까?")){
	return false;
	}
	document.getElementById("deleteandupdatefitness").submit();
	
}

function deleteandupdatecardio_do(){
	if(!confirm("수정(대체)하시겠습니까?")){
	return false;
	}
	document.getElementById("deleteandupdatecardio").submit();
	
}