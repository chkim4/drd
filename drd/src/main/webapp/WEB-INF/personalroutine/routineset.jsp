<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>다루다HOME</title>

    <!-- Custom fonts for this template-->
    <link href="/sbadmin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/sbadmin/css/sb-admin-2.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    
    <script src="/resources/jquery/personalroutine/routineset-script.js">
    	var personalRoutineSEQ = parseInt("${member.personalRoutineSEQ}")
    </script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#myroutine").append("${routine.name}<hr/>${routine.description}")
			$("#routineperiodtxt").empty()
			$("#routineperiodtxt").append("<h5>${routine.period}주 중 ${days}일 진행중<br/>(${progress}%)</h5><br/>")
			$("#routineperiodpersent").attr({"aria-valuenow":"${progress}","style":"width: ${progress}%"})
			setPersonalRoutineSEQ(${member.personalRoutineSEQ})
			$(document).on("click", "#routinelist",selectPersonalRoutine_onclick)
			$(document).on("click", ".fitness", selectFitness_onclick)
			$(document).on("click", ".cardio", selectCardio_onclick)
		})//end ready
	</script>
	<style type="text/css">
		#routinelist, .fitness, .cardio{
			cursor : pointer;
		}
	</style>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
             <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/member/index.do">
                <div>
                    <img src="../resources/static/logo/drd_white.png" style="max-width: 70%"></i>
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
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">My Routine</h1>
                    </div>



                    <div class="row">

                        <div class="col-lg-3">

                            <!-- Default Card Example -->
                            <div class="card mb-3">
                                <div class="card-header">
                                    루틴 전체
                                </div>
                                <!-- 루틴 전체 리스트(차후 일별로 정리) -->
                                <div class="card-body">
                                <div class="card mb-2">
                                <div class="card-header">
                                    ${routine.name}
                                </div>
                                <div class="card-body" id="routinelist">
                                	<h5>Fitness</h5>
									<c:forEach var="data" items="${routine_fitnessList }">
										<h6>${data.name}</h6>
									</c:forEach>
									<br/>
									<h5>Cardio</h5>
									<c:forEach var="data" items="${routine_cardioList }">
										<h6>${data.name}</h6>
									</c:forEach>
                                </div>
                                </div>
                            </div>
                            </div>
                        <div class="row">
                         <div class="col-xl-6 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">내 루틴 정보
                                            </div>
                                            <div class="row no-gutters align-items-center">
                                                <div class="col-auto">
                                                	<!-- 디폴트 루틴 정보 -->
                                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800" id="myroutine"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
						<!-- end of card1 -->
						<div class="col-xl-6 col-md-6 mb-4">
                            <div class="card border-left-info shadow h-100 py-2">
                                <div class="card-body">
                                    <div class="row no-gutters align-items-center">
                                        <div class="col mr-2">
                                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">진행률
                                            </div>
                                            <div class="row no-gutters align-items-center">
                                                <div class="col-auto">
                                                <!-- 루틴 진행 시간, 진행률 -->
                                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800" id="routineperiodtxt"></div>
                                                    
                                                </div>
                                                <div class="col">
                                                    <div class="progress progress-sm mr-2">
                                                    <!-- 루틴 진행 시간, 진행률 -->
                                                        <div class="progress-bar bg-info" role="progressbar"
                                                            style="width: 10%" aria-valuenow="50" aria-valuemin="0"
                                                            aria-valuemax="100" id="routineperiodpersent"></div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-auto">
                                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
						<!-- end of card2 -->                        
                        </div>
						<!-- end of cardrow -->
                        </div>

                        <div class="col-lg-5">

                            <div class="card mb-4">
                                <div class="card-header">
                                    무산소(Fitness)
                                </div>
                                <!-- fitnesslist 상세 정보 -->
                                <div class="card-body" id="myroutine_fitnessList">
                                <div class='w-100' style='float:left'>
                                	<div style='float:left; width:70%'>name</div>
                                	<div style='float:left; width:10%'>set</div>
                                	<div style='float:left; width:10%'>count</div>
                                	<div style='float:left; width:10%'>weight</div>
                                </div>
                                <br/>	
                                	<c:forEach var="data" items="${myroutine_fitnessList }">
										<div class='fitness w-100' style='float:left'>
											<div class='seq' style="display: none">${data.fitnessSEQ}</div>
											<div style='float:left; width:70%'>${data.name}</div>
											<div style='float:left; width:10%'>${data.set}</div>
											<div style='float:left; width:10%'>${data.count}</div>
											<div style='float:left; width:10%'>${data.weight}kg</div>
										</div>
										<br/>
									</c:forEach>
                                	
                                </div>
                            </div>

                            <div class="card mb-4">
                                <div class="card-header">
                                    유산소(Cardio)
                                </div>
                                <!-- cardiolist 상세 정보 -->
                                <div class="card-body" id="myroutine_cardioList">
	                                 <div class='w-100' style='float:left'>
		                                 <div class='w-75' style='float:left'>name</div>
		                                 <div class='w-auto' style='float:left'>time</div>
	                                 </div>
	                                 <br/>
	                                	<c:forEach var="data" items="${myroutine_cardioList }">
	                                			
											<div class='cardio w-100' style='float:left'>
												<div class='seq' style="display: none">${data.cardioSEQ}</div>
												<div class='w-75' style='float:left'>${data.name}</div>
												<div class='w-auto' style='float:left'>${data.time}분</div>
											</div>
											<br/>
										</c:forEach>
                                </div>
                            </div>

                        </div>
                        <!-- ajax or modal -->
                        <div class="col-lg-4">

                            <div class="card mb-4">
                            <div class="card-header" id="ajaxchangename">
                                도움말
                            </div>
                               <div class="c" id="check">
								name_1
								</div>
								<hr/>
                                <div class="card-body" id="ajaxchangecontents">
                                <form action="">
								 set : <input type="number" min="1" value="10" name="set"><br/>
								 count : <input type="number" min="1" value="10" name="count"><br/>
								 weight : <input type="number" min="1" value="10" name="weight"><br/>
								 <div style="text-align: right; ">
						         <a href="#" class="btn btn-success btn-icon-split">
									<span class="icon text-white-50">
									<i class="fas fa-check"></i>
									</span>
									 <span class="text">수정하기</span>
								</a>
								 <a href="#" class="btn btn-danger btn-icon-split" >
									<span class="icon text-white-50">
										<i class="fas fa-trash"></i>
									</span>
									<span class="text">삭제하기</span>
						         </a>	 
								 </div>
                                </div>
                            </div>
                        </div>
					<!-- end ajax or modal -->
                    </div>
                    <!-- end of row1 -->

                </div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->
        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

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
	
</body>

</html>