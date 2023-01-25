<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>다루다</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<!-- Custom fonts for this template-->
<link href="/sbadmin/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
<!-- Custom styles for this template-->
<link href="/sbadmin/css/sb-admin-2.min.css" rel="stylesheet">
<script src="/sweetalert/sweetalert2.min.js"></script>
<link rel="stylesheet" href="/sweetalert/sweetalert2.min.css">

<script type="text/javascript">
	$(document).ready(function() {
		$("#updateImg").click(function() {
			$("#goalTime").removeAttr("disabled","disabled");
			$("#timeImg").hide();
			$("#updateTimeBTN").css("display","block");
			})
		$("#updateImg2").click(function() {
			$("#goalCalory").removeAttr("disabled","disabled");
			$("#timeImg2").hide();
			$("#updateCaloryBTN").css("display","block");
			})
		$('#myModal').on('shown.bs.modal', function () {
			$("#modWeight").val($('#desiredWeight').val()); 
			$("#modBodyShape").val($('#desiredBodyShape').val()); 
 		})
 		$('[data-toggle="popover"]').popover();
	 		
 	 	$('#modalmodBTN').click(function() {
 	 		$.ajax({
 				url : "/goal/updateBodyShape",
 				type : "POST",
 				data : $("#body").serialize(),
 				dataType: "json",
 				success : function(data) {
					$("#desiredWeight").val(data.desiredWeight);
					$("#desiredBodyShape").val(data.desiredBodyShape);
					$("#myModal").modal('hide');
					location.reload();
				}
 			})
 		})
	});
</script>

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/member/index.do">
                <div>
                    <img src="../resources/static/logo/drd_white.png" style="max-width: 70%;"/>
                </div>
            </a>
            
            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Nav Item - Pages Collapse Menu --> 
            <!-- 마이 페이지 관련. 추 후 작업 후 진행 예정 -->
<!--             <li class="nav-item active"> -->
<!--                 <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" -->
<!--                     aria-controls="collapsePages"> -->
<!--                     <i class="fas fa-user-plus"></i> -->
<!--                     <span>내정보</span> -->
<!--                 </a> -->
<!--                 <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar"> -->
<!--                     <div class="bg-white py-2 collapse-inner rounded"> -->
<!--                         <a class="collapse-item" href="#">정보수정</a> -->
<!--                         <a class="collapse-item" href="#">Forgot Password</a> -->
<!--                         <a class="collapse-item" href="#">404 Page</a> -->
<!--                         <a class="collapse-item active" href="#">Blank Page</a> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </li> -->

			<!-- Nav Item - Dashboard -->
            <li class="nav-item">
                <a class="nav-link" href="/dashboard/read">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>대시보드</span></a>
            </li>
            
            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="/record/index.do">
                    <i class="fas fa-fw fa-table"></i>
                    <span>기록</span></a>
            </li>

            <!-- Nav Item - Tables -->
            <li class="nav-item">
                <a class="nav-link" href="/personalroutine/setpage">
                    <i class="fas fa-running"></i>
                    <span>루틴</span></a>
            </li>
            
            <!-- Nav Item - user-chart -->
            <li class="nav-item">
                <a class="nav-link" href="/goal/readAll">
                	<i class="fas fa-chart-pie"></i>
                    <span>목표</span></a>
            </li> 
            
             <!-- Nav Item - FAQ -->
	         <li class="nav-item">
	         	<a class="nav-link" href="/member/faqPage">
	            	<i class="fas fa-info-circle"></i>
	                	<span>FAQ</span></a>
	         </li>
            
             <!-- Nav Item - 게시판 --> 
             <!-- 운동 후기 및 Q&A 관련. 아직 구현 안해서 제외 -->
<!--             <li class="nav-item active"> -->
<!--                 <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsetwo" aria-expanded="true" -->
<!--                     aria-controls="collapsetwo"> -->
<!--                     <i class="fas fa-comments"></i> -->
<!--                     <span>게시판</span> -->
<!--                 </a> -->
<!--                 <div id="collapsetwo" class="collapse" aria-labelledby="headingtwo" data-parent="#accordionSidebar"> -->
<!--                     <div class="bg-white py-2 collapse-inner rounded"> -->
<!--                         <a class="collapse-item" href="#">운동후기</a> -->
<!--                         <a class="collapse-item" href="#">Q&A</a> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </li> -->
            

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>
                        
                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${member.nickName}</span>
                                <img class="img-profile rounded-circle"
                                    src="/sbadmin/img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown"> 
                                <!-- 상단바 우상단 누를 시 나오는 메뉴. 추 후 작업 시 주석 처리 예정 -->
<!--                                 <a class="dropdown-item" href="#"> -->
<!--                                     <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> -->
<!--                                     프로필 -->
<!--                                 </a> -->
<!--                                 <a class="dropdown-item" href="#"> -->
<!--                                     <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i> -->
<!--                                     정보수정 -->
<!--                                 </a> -->
                                
                                <div class="dropdown-divider"></div>
                                 <a class="dropdown-item" href="/mypage/readAll" >
                                    <i class="fas fa-user-circle text-gray-400"></i>
                                      마이페이지
                                </a>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    로그아웃
                                </a>
                               
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

				
				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h1 mb-2 text-gray-800" style="font-weight: 800;">내
						목표</h1>
					<p class="mb-2">습관을 계획하고 맞춤목표에 도전하세요</p>
						<c:if test="${weeklyExerciseTime == 0 || dailyCalory == 0 || weeklyProtein ==0 }">
							<span class="font-weight-bold text-primary">주간 기록이 없습니다</span><span class="ml-3 font-weight-bold" style="font-size: 12px">기록하기 <a href="/record/index.do"><img src="/sbadmin/img/record.png" class="mb-2" width=25px;></a></span> 
						</c:if>

					<!-- Content Row -->
					<div class="row mt-3">
						<!-- First Column -->
						<div class="col-lg-4">
							<div class="card shadow mb-4">
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">주간 운동 목표</h6>
								</div>
								<!-- Card Body -->
								<div class="card-body">
									<div style="width: 100%;">
										<canvas id="timeChart"></canvas>
									</div>
								</div>
							</div>


							<div class="card shadow mb-4">
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">나의 운동</h6>
								</div>
								<!-- Card Body -->
								<div class="card-body">
									<div id="dId" class="bg-light card px-3 py-3">
										<form class="mb-1 small" method="post"
											action="/goal/updateTime">
											일주일 운동 목표 <input type="number" min="1" max="1000"
												id="goalTime" name="goalExerciseTime"
												value="${goalExerciseTime}" size="10px" disabled="disabled"
												style="text-align: center;"> 분 <a id="updateImg"
												style="cursor: pointer;"><img id="timeImg"
												style="float: right" src="/sbadmin/img/update.png"
												width=20px></a> <input type="submit"
												class="btn btn-sm btn-primary" value="수정" id="updateTimeBTN"
												style="display: none; float: right" /> <input type="hidden"
												value="${member.memberSEQ }" name="memberSEQ" />
										</form>
									</div>
								</div>

								<div class="card-body">
									<div id="dId" class="bg-light card px-3 py-3">
										<div class="mb-1 small">
											나의 운동 시간 <span class="px-5 py-1 ml-2 mt-1"
												style="border: 1px solid #9e9e9e">${weeklyExerciseTime }
												분</span>
										</div>
									</div>
								</div>

								<!-- Progress Small -->
								<div class="card-body">
									<div class="bg-light card px-3 py-3">
										<div id="goalprogress" class="mb-1 small">
											<c:choose>
												<c:when
													test="${goalExerciseTime == weeklyExerciseTime || weeklyExerciseTime > goalExerciseTime}">
														         <div style="color: #ff4d4d; font-size: 30; font-weight: bold;">목표 달성 !</div>
													</c:when>
												<c:when test="${goalExerciseTime > weeklyExerciseTime}">
													          목표달성까지 <fmt:formatNumber type="percent"
														value="${1-(weeklyExerciseTime / goalExerciseTime)}"
														pattern="0%" />
												</c:when>
											</c:choose>
										</div>
										<div class="progress mb-4">
											<div class="progress-bar" role="progressbar"
												style="width:<fmt:formatNumber type="percent" value="${weeklyExerciseTime / goalExerciseTime} "  pattern="0%"/>"
												aria-valuenow="${weeklyExerciseTime / goalExerciseTime}"
												aria-valuemin="0" aria-valuemax="100"></div>
										</div>
									</div>
								</div>
							</div>
						</div>


						<!-- Second Column -->
						<div class="col-lg-4">
							<div class="card shadow mb-4">
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">나의 목표</h6>
								</div>
								<div class="card-body">
									<div class="mb-4 small">
										목표 체중
										<div>
											<c:choose>
												<c:when test="${desiredWeight == 0 }">
													<input type="text" class="form-control" id="desiredWeight"
												name="desiredWeight" value="목표체중을 입력하세요"
												size="10px" readonly="readonly" style="text-align: center;">
												</c:when>
												<c:when test="${desiredWeight != 0 }">
													<input type="text" class="form-control" id="desiredWeight"
												name="desiredWeight" value="${member.desiredWeight}"
												size="10px" readonly="readonly" style="text-align: center;">
												</c:when>
											
											</c:choose>
										
										
										
											
										</div>
									</div>
									<div class="mb-2 small">
										목표 유형 <input type="text" class="form-control"
											id="desiredBodyShape" name="desiredBodyShape"
											value="${member.desiredBodyShape}" size="10px"
											readonly="readonly" style="text-align: center;">
									</div>
									<button type="button" class="btn btn-sm btn-primary"
										data-toggle="modal" data-target="#myModal"
										style="float: right;">수정</button>
								</div>

								<!-- Modal -->
								<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
									aria-labelledby="myModalLabel">
									<div class="modal-dialog" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body">
												<form action="" method="post" id="body">
													<div class="form-group">
														<div>
															<label>목표 체중</label> <input type="number" id="modWeight"
																class="form-control" name="desiredWeight" value="" />
														</div>
														<div>
															<label>목표 유형</label> <select class="form-control"
																id="modBodyShape" name="desiredBodyShape">
																<option value="다이어트">다이어트</option>
																<option value="벌크업">벌크업</option>
															</select>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" id="modalmodBTN"
															class="btn btn-default">수정</button>
														<button type="button" class="btn btn-default"
															data-dismiss="modal">닫기</button>
														<input type="hidden" name="memberSEQ"
															value="${member.memberSEQ }" />
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="card shadow mb-4">
								          <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                    <h6 class="m-0 font-weight-bold text-primary">나의 루틴</h6>
                                    <div class="dropdown no-arrow">
                                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                            aria-labelledby="dropdownMenuLink">
                                            <a class="dropdown-item" href="/personalroutine/setpage">상세보기</a>
                                        </div>
                                    </div>
                                </div>
								
								<div class="card-body">
									<div id="routinelist" class="mb-2 small"></div>
									<div class="card-body pt-1" id="cardiolist">
										<div class="table-responsive" style="text-align: center;">
											<table class="table table-bordered" id="dataTable"
												width="100%" cellspacing="0">
												<div class="mb-2"
													style="text-align: center; color: #9e9e9e; font-size: 15; font-weight: bold;">유산소 운동</div>
												<thead style="background-color: #f4f5f8;">
													<tr>
														<th>이름</th>
														<th>시간</th>
														<th>강도</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="cardio" items="${cardiolist }">
														<tr>
															<td>${cardio.name}</td>
															<td>${cardio.time}분</td>
															<td>${cardio.intensity}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
									<div class="card-body pt-1 mb-1" id="cardiolist">
										<div class="table-responsive" style="text-align: center;">
											<table class="table table-bordered" id="dataTable"
												width="100%" cellspacing="0">
												<div class="mb-2"
													style="text-align: center; color: #9e9e9e; font-size: 15; font-weight: bold;">무산소 운동</div>
												<thead style="background-color: #f4f5f8;">
													<tr>
														<th>이름</th>
														<th>세트</th>
														<th>횟수</th>
														<th>무게</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach var="fitness" items="${fitnesslist }">
														<tr>
															<td>${fitness.name}</td>
															<td>${fitness.set}세트</td>
															<td>${fitness.count}회</td>
															<td>${fitness.weight}kg</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- second column  -->

						<!-- Third Column -->
						<div class="col-lg-4">
							<div class="card shadow mb-4">
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">칼로리 목표</h6>
								</div>
								<div class="card-body">
									<div style="width: 100%;">
										<canvas id="caloryChart" width="400" height="200"></canvas>
										<!--칼로리 차트가 그려질 부분-->
									</div>


									<div class="card mb-4 mt-4">
										<div class="bg-light card-body">
											<form class="mb-1 small" method="post"
												action="/goal/updateCalory">
												칼로리 목표 <input type="number" min="1" max="3000"
													id="goalCalory" class="input" name="goalCalory"
													value="${goalCalory}" size="10px" disabled="disabled"
													style="text-align: center;"> kcal <a
													id="updateImg2" style="cursor: pointer;"><img
													id="timeImg2" style="float: right"
													src="/sbadmin/img/update.png" width=20px></a> <input
													type="submit" class="btn btn-sm btn-primary" value="수정"
													id="updateCaloryBTN" style="display: none; float: right" />
												<input type="hidden" value="${member.memberSEQ }"
													name="memberSEQ" />
											</form>
											<div class="mt-3" id="caloryInfo" style="color: #ff4d4d; font-size: 12px">
												<c:choose>
													<c:when
														test="${member.desiredBodyShape eq '다이어트'}">
												 		권장 칼로리보다 적게 섭취해야 합니다
													</c:when>
													<c:when
														test="${member.desiredBodyShape eq '벌크업'}">
												 		권장 칼로리보다 많이 섭취해야 합니다
												</c:when>
												</c:choose>
											</div>
										</div>
									</div>
									<a href="#" data-toggle="popover" title="참고" style="float: right; font-size: 10px; color:#9e9e9e;" 
									data-content="다이어트 : 권장칼로리보다 적게 섭취 / 벌크업 : 권장칼로리보다 많이 섭취">참고</a>
									<%-- 		<div class="card mb-4 mt-4">
										<div class="bg-light card-body">
											<div class="mb-1 small">
												나의 섭취 칼로리 <span class="px-5 py-2 ml-2 mt-1"
													style="border: 1px solid #9e9e9e">${todayTotalCalory }
													kcal</span>
											</div>
										</div>
									</div> --%>
								</div>
							</div>

							<!-- ㄷ단백질 -->
							<div class="card shadow mb-4">
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">단백질 목표</h6>
								</div>
								<div class="card-body">
									<canvas id="doughnutChart" width="500" , height="300">
									<!-- 단백질 도넛차트 -->
									</canvas>
									<%-- 									<div class="mb-2 small">
										단백질 목표 <input type="text" class="form-control"
											value="${goalProtein }" size="10px"
											readonly="readonly" style="text-align: center;">
									</div>
									<div class="mb-2 small">
										이번주 단백질 섭취 <input type="text" class="form-control"
											value="${weeklyProtein}" size="10px"
											readonly="readonly" style="text-align: center;">
									</div> --%>
								</div>
							</div>
						</div>
						<!-- end -->
						<!-- /.container-fluid -->

					</div>
					<!-- End of Main Content -->
			
					<!-- Footer -->
		            <footer class="sticky-footer bg-white">
		                <div class="container my-auto">
		                    <div class="copyright text-center my-auto">
		                        <span>Copyright &copy; Your Website 2021</span>
		                    </div>
		                </div>
		            </footer>
		            <!-- End of Footer -->
            
				</div>
				<!-- End of Content Wrapper -->

		   </div>
    <!-- End of Page Wrapper -->
    </div>

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">로그아웃 하시겠습니까?</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">아니오</button>
                    <a class="btn btn-primary" href="/member/logout.do">네</a>
                    
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

			<!-- Page level plugins -->
			<script src="/sbadmin/vendor/chart.js/Chart.min.js"></script>

			<script src="/resources/jquery/goal/goal-script.js"></script>
			<script type="text/javascript">

	//운동 그래프
	 new Chart(
            document.getElementById('timeChart'),{
         	   type: "bar",
	          	  data: {
	          		 labels: [
   		              '목표',
   		              '현재'
   		            ],
	          	    datasets: [{
	          	      backgroundColor: ['rgba(255, 99, 132, 0.2)','rgba(54, 162, 235, 0.2)'],
	          	      data: [${goalExerciseTime},${weeklyExerciseTime}]
	          	    }]
	          	  },
	          	  
	          	  options: {
	          		responsive:true,
	          	    title: {
	          	      display: true,
	          	      text: "운동 주간목표달성",
	          	      fontSize : 15,
	          	      fontColor : '#9e9e9e'
	          	    },
	          	    legend: {
	          	    	display:false,
	          	        position: 'bottom',
	          	        labels:{
	          	        	display : false,
	          			    fontSize : 15,
	          			    boxWidth : 10
	          	        }
	          	  	},
	          	 scales: {
	          		 xAxes: [{
	    	        gridLines: {
	    		          display: false,
	    		          drawBorder: false
	    		        },
	    		        ticks: {
	    		          maxTicksLimit: 10
	    		        }
	    		      }],
			            yAxes: [
			                {
			                    gridLines: {
		 		    		          display: false,
		 		    		          drawBorder: false
		 		    		        },
			                    ticks: {
			                    	min: 0,
			        				max: 500,
			        				stepSize : 50,
			        		        maxTicksLimit: 10,
			        	          	padding: 10,
			                        beginAtZero: true
			                    }
			                }
			            ]
			        } // scales
			        
	          	 	} //option 닫기
            }) //document.getElementById 닫기     
	
	//단백질 도넛차트
	var calory = [${goalCalory}, ${dailyCalory}]; // dailyCalory: 1일 섭취 칼로리, goalCalory: 주간 목표 섭취 칼로리
	var protein = [${goalProtein}, ${weeklyProtein}]; // weeklyProtein: 1주일 섭취 단백질, goalProtein: 주간 목표 섭취 단백질
	var goal = []; 
	          
	            new Chart(
                   document.getElementById('doughnutChart'),{
                	   type: "bar",
 		          	  data: {
 		          		 labels: [
          		              '목표 단백질',
          		              '현재 단백질'
          		            ],
 		          	    datasets: [{
 		          	      backgroundColor: [ 'rgba(255, 206, 86, 0.3)', 'rgba(75, 192, 192, 0.3)'],
 		          	      data: protein
 		          	    }]
 		          	  },
 		          	  
 		          	  options: { 
 		          		
 		          		responsive:true,
 		          	    title: {
 		          	      display: true,
 		          	      text: "단백질 주간목표달성",
 		          	      fontSize : 15,
 		          	      fontColor : '#9e9e9e'
 		          	    },
 		          	    legend: {
 		          	    	display:false,
 		          	        position: 'bottom',
 		          	        labels:{
 		          	        	display : false,
 		          			    fontSize : 15,
 		          			    boxWidth : 10
 		          	        }
 		          	  	},
 		          	 scales: {
 		          		 xAxes: [{
 		    	        gridLines: {
 		    		          display: false,
 		    		          drawBorder: false
 		    		        },
 		    		        ticks: {
 		    		          maxTicksLimit: 10
 		    		        }
 		    		      }],
				            yAxes: [
				                {
				                    gridLines: {
			 		    		          display: false,
			 		    		          drawBorder: false
			 		    		        },
				                    ticks: {
				                    	min: 0,
				        				max: 1000,
				        				stepSize : 100,
				        		        maxTicksLimit: 10,
				        	          	padding: 10,
				                        beginAtZero: true
				                    }
				                }
				            ]
				        }, // scales
					      //수정한 부분
					        tooltips: {
					            position: 'custom'
					          }
					        
 		          	 	} //option 닫기
                   }) //document.getElementById 닫기     
					  new Chart(
							  document.getElementById('caloryChart'),{			
								  type: "bar",
			 		          	  data: {
			 		          		 labels: [
			          		              '목표 칼로리',
			          		              '현재 칼로리'
			          		            ],
			 		          	    datasets: [{
			 		          	      backgroundColor: [ 'rgba(255, 206, 86, 0.3)', 'rgba(75, 192, 192, 0.3)'],
			 		          	      data: calory
			 		          	    }]
			 		          	  },
			 		          	  
			 		          	  options: {
			 		          		responsive:true,
			 		          	    title: {
			 		          	      display: true,
			 		          	      text: "칼로리 목표달성",
			 		          	      fontSize : 15,
			 		          	      fontColor : '#9e9e9e'
			 		          	    },
			 		          	    legend: {
			 		          	    	display:false,
			 		          	        position: 'bottom',
			 		          	        labels:{
			 		          	        	display : false,
			 		          			    fontSize : 15,
			 		          			    boxWidth : 10
			 		          	        }
			 		          	  	},
			 		          	 scales: {
			 		          		 xAxes: [{
			 		    	        gridLines: {
			 		    		          display: false,
			 		    		          drawBorder: false
			 		    		        },
			 		    		        ticks: {
			 		    		          maxTicksLimit: 10
			 		    		        }
			 		    		      }],
							            yAxes: [
							                {
							                    gridLines: {
						 		    		          display: false,
						 		    		          drawBorder: false
						 		    		        },
							                    ticks: {
							                    	min: 0,
							        				max: 3000,
							        				stepSize : 1000,
							        		        maxTicksLimit: 10,
							        	          	padding: 10,
							                        beginAtZero: true
							                    }
							                }
							            ]
							        }, // scales 닫기
			 		          	 	
							        //수정한 부분
							        tooltips: {
							            position: 'custom'
							          }
			 		          	  
			 		          	  } //options 닫기
							}) //new Chart 닫기
		         
	/* 
	var xValues = ["단백질","필요 단백질"];
	var yValues = [${goal.goalProtein},${TotalProtein}-${goal.goalProtein}];
	var barColors = [
		   'rgba(255, 99, 132, 0.4)', //핑크
            'rgba(54, 162, 235, 0.4)', //파랑
            'rgba(255, 206, 86, 0.4)', //노랑
            'rgba(75, 192, 192, 0.4)',
            'rgba(153, 102, 255, 0.4)',
            'rgba(255, 159, 64, 0.4)', 
            'rgba(177, 223, 64, 0.4)' //초록
	];
	*/  
	// 수정한 부분
	Chart.Tooltip.positioners.custom = function(elements, position) {
	    if (!elements.length) {
	      return false;
	    }
	    var offset = 0;
	    //adjust the offset left or right depending on the event position
	    if (elements[0]._chart.width / 2 > position.x) {
	      offset = 20;
	    } else {
	      offset = -20;
	    }
	    return {
	      x: position.x + offset,
	      y: position.y
	    }
	  }
		   
	</script>
</body>

</html>