<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>회원 가입 페이지</title> 
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

    <!-- Custom fonts for this template-->
    <link href="/sbadmin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/sbadmin/css/sb-admin-2.min.css" rel="stylesheet"> 
    
    <script src="/sweetalert/sweetalert2.min.js"></script>
	<link rel="stylesheet" href="/sweetalert/sweetalert2.min.css">
        
    <script src="/resources/jquery/member/register-script.js"></script> 
    
    <!-- jsp 내 태그와 register-script.js 내 함수들 연결 -->
    <script type="text/javascript"> 
    	$(document).ready(function() {    
    		
   			$("#nickName").keyup(nickName_onKeyUp);
    		$("#checkNickNameBTN").click(checkNickNameBTN_onClick); 
    		
    		$("#email").keyup(email_onKeyUp);
    		$("#checkEmailBTN").click(checkEmailBTN_onClick); 
    		
    		$("#pass").keyup(pass_onKeyUp); 
    		$("#pass").focusout(pass_onFocusout); 
    		
    		$("#birth").focusout(get_age);
    		
   			// 페이지가 로드될 때 추천 루틴 리스트는 보여서는 안 된다.
    		$("#routineList").css("display", "none");
    		
    		$("#routineInfo").change(routineInfo_change); //나이, 성별, 키, 몸무게 변경 시 추천 루틴 변경 
    		
    		$("#findAllRoutineBTN").click(findAllRoutineBTN_click); //루틴 전체 조회 기능
    		
    		$("#submitBTN").click(submitBTN_onClick);   
    		
    	})//$(document).ready 닫기
    
    </script> 
    
    <style type="text/css">
    	th, td {
		  text-align: center;
		}
    </style>
    
</head>

<body class="bg-gradient-primary">

    <div class="container">

        <!-- Outer Row -->
        <div class="row justify-content-center">

            <div class="col-xl-10 col-lg-12 col-md-9">

                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-8 mx-auto">
                                <div class="p-5">
                                    <div class="text-center">
                                        <h1 class="h4 text-gray-900 mb-4">Welcome to DRD!</h1>
                                    </div>
                                    <form class="user" action="/member/register.do" method = "post" id="registerForm">
                                   		<div class="form-group">
                                            <div class="input-group">  
					                            <input type="email" class="form-control form-control-user"
                                                	id="email" name="email" aria-describedby="emailHelp" placeholder="이메일을 입력하세요...">
					                      
												<div class="input-group-append">
					                                <button type="button" class="btn btn-primary btn" id="checkEmailBTN">
					                                	체크
					                                </button>
					                            </div> 
					                       	</div>
					                    </div>    
					                     <div class="form-group">  
                                            <div class="input-group">
					                            <input type="text" class="form-control form-control-user" name="nickName"  id="nickName" placeholder="닉네임을 입력하세요...">
					                            <div class="input-group-append">
					                                <button type="button" class="btn btn-primary btn" id="checkNickNameBTN">
					                                	체크
					                                </button>
					                            </div>
					                        </div>    
                                        </div>                                                        
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-user"
                                                id="pass" name="pass" placeholder="비밀번호를 입력하세요...">
                                        </div> 
                                      <div id = "routineInfo"> <!-- 루틴 추천에 필요한 정보 모음. -->
         								<div class="form-group"> 
         									<div class="form-group row">
			                                    <div class="col-sm-4 mb-3 mb-sm-0">
			                                        <input type="text" value = "생년월일" class="form-control form-control-user" readonly>
			                                    </div>
			                                    <div class="col-sm-8">
			                                        <input type="date" class="form-control form-control-user"
                                                		id="birth" name="birth">
			                                    </div> 
			                                    <input type="hidden" id="age" name="age"/>
                                			</div>                                           
                                        </div> 
                                      
                                        <div class="form-group"> 
         									<div class="form-group row">
			                                    <div class="col-sm-4 mb-3 mb-sm-0">
			                                        <input type="text" value = "성별" class="form-control form-control-user" readonly>
			                                    </div> 
			                               
			                                    <div class="col-sm-8">
			                                    	<select class="form-control" id="gender" name="gender">
												      <option value = "" selected>선택</option>
												      <option value = "1">남성</option>
												      <option value = "0">여성</option>
												    </select>
			                                    </div>
                                			</div>                                           
                                        </div>
                                        <div class="form-group"> 
         									<div class="form-group row">
			                                    <div class="col-sm-4 mb-3 mb-sm-0">
			                                        <input type="text" value = "키" class="form-control form-control-user" readonly>
			                                    </div>
			                                    <div class="col-sm-8">
			                                    	<input type = "number" class="form-control" id="height" name="height" min="0">
			                                    </div>
                                			</div>                                           
                                        </div>
                                        <div class="form-group"> 
         									<div class="form-group row">
			                                    <div class="col-sm-4 mb-3 mb-sm-0">
			                                        <input type="text" value = "몸무게" class="form-control form-control-user" readonly>
			                                    </div>
			                                    <div class="col-sm-8">
			                                    	<input type = "number" class="form-control" id="weight" name="weight" min="0">
			                                    </div>
                                			</div>                                           
                                        </div> 
                                        <div class="form-group"> 
         									<div class="form-group row">
			                                    <div class="col-sm-4 mb-3 mb-sm-0">
			                                        <input type="text" value = "질병 유무" class="form-control form-control-user" readonly>
			                                    </div> 
			                               
			                                    <div class="col-sm-8">
			                                    	<select class="form-control" id="disease" name="disease">
												      <option value = "" selected>선택</option>
												      <option value = "0">정상</option>
												      <option value = "1">당뇨</option>
												      <option value = "2">고혈압</option>
												    </select>
			                                    </div>
                                			</div>                                           
                                        </div> 
                                        </div>
                                                                                        
                                        <div class="form-group" id="routineList"> 
                                        	<span> 
                                        		당신만을 위한 추천 루틴
                                        		<button type="button" class="btn btn-primary btn" id = "findAllRoutineBTN" style="float: right;">전체 조회</button>
                                        	</span> 
			                                <div class="card-body">
					                            <div class="table-responsive">
					                                <table class="table table-bordered" id="routineListTable" width="100%" cellspacing="0">
					                                    <thead>
					                                        <tr>
					                                        	<th>루틴이름</th>				                             
					                                            <th>설명</th> 
					                                            <th>선택하기</th>
					                                        </tr>
					                                    </thead>
					                                    <tbody>
					                      				</tbody>
								                        </table> 
								                      </div>
				                  					</div> 
				                  				</div>
			                                                                       
                                        <input type="button" class="btn btn-primary btn-user btn-block" id = "submitBTN" value="가입하기">
                                        <hr>
                                    </form>
                                    <hr>
                                    <div class="text-center">
                                        <a class="small" href="/member/login.do">이미 계정이 있으신가요? 로그인하기</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/sbadmin/vendor/jquery/jquery.min.js"></script>
    <script src="/sbadmin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/sbadmin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/sbadmin/js/sb-admin-2.min.js"></script>

</body>

</html>