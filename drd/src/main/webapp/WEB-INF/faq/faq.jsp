<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>FAQ</title>

    <!-- Custom fonts for this template-->
    <link href="/sbadmin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/sbadmin/css/sb-admin-2.min.css" rel="stylesheet"> 
    <style>
    	th, td{ 
    		text-align: center;
    	}
    </style>

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/dashboard/read">
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
                <a class="nav-link" href="/faq/faq">
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
                    <h1 class="h3 mb-1 text-gray-800">FAQ</h1><h4 class="h6 mb-2 text-gray-800">자주 묻는 질문</h4>
                    <hr>            
                    <!-- FAQ --> 
                    <div class="faq shadow mb-4">
	                    <!-- FAQ Header - Accordion -->
	                    <a href="#FAQ1" class="d-block card-header py-3" data-toggle="collapse"
	                        role="button" aria-expanded="true" aria-controls="FAQ1">
	                        <h6 class="m-0 font-weight-bold text-primary">운동 루틴은 어떤 기준으로 추천하나요?</h6>
	                    </a>
	                    <!-- Card Content - Collapse -->
	                    <div class="collapse" id="FAQ1">
	                        <div class="card-body">
	                           <p> FITT 원칙과 헬스의 정석 책을 기반으로 추천합니다. </p>
	                           <p> FITT원칙이란 미국 스포츠의학회(ACSM)의 가이드라인을 준수하여 다음 4가지의 기준으로 개인별 운동 루틴을 설계하는 것을 의미합니다.</p>
	            			   <p> FITT원칙의 기준: F(frequency) 빈도, I(intensity) 강도, T(time) 시간, T(type) 유형</p>
	                           <p> 하지만, FITT 원칙은 구체적인 루틴을 제시하진 않기에 초보자를 위한 구체적인 운동 루틴을 담은 헬스의 정석 책을 참고하였습니다.</p>
	                           <p><a href = "https://www.verywellfit.com/f-i-t-t-principle-what-you-need-for-great-workouts-1231593"><strong>FITT 원칙에 관하여</strong></a></p>
	                           <p><a href = "http://www.hanmunhwa.com/?p=4831"><strong>헬스의 정석 출판사 페이지</strong></a></p>
	                        </div>
	                    </div>
	                </div>
	                <div class="faq shadow mb-4">
	                    <!-- FAQ Header - Accordion -->
	                    <a href="#FAQ2" class="d-block card-header py-3" data-toggle="collapse"
	                        role="button" aria-expanded="true" aria-controls="FAQ2">
	                        <h6 class="m-0 font-weight-bold text-primary">고혈압 환자인데 어떤 운동을 해야 할까요?</h6>
	                    </a>
	                    <!-- Card Content - Collapse -->
	                       <div class="collapse" id="FAQ2">
	                           <div class="card-body">
		                           <p>고혈압 환자들의 혈압을 낮추기 위한 권고된 운동의 방법은 
		                           	  <u><strong>자전거, 걷기, 조깅, 수영과 같은 대근육 활동을 주당 3~5일 정도 비연속적으로 실시</strong></u> 하는 것이 바람직합니다. 
		                           	  1회 운동시간은 20~60분, 강도는 최대산소섭취량의 50~85% 정도로 산정해야 하며 이는
		                           	  미국 스포츠 의학회의 이전의 지침서인 &ldquo;건강한 성인에 심폐와 근력 발달과 유지를 위한 권고된 운동의 양과 질&rdquo;
		                           	  에서의 기준을 반영하였습니다. </p>
								   <p>그러나 더 낮은 강도(최대산소섭취량의 40~70%)의 운동 트레이닝은 더욱 혈압을 낮추는 것으로 드러났습니다. 
								      또한, 현저하게 상승한 혈압(&gt;180/105mmHg)을 가진 사람들은 초기 약물학적 치료 후에 치료 양생법으로 지구력 운동 트레이닝을 추가해야 합니다.</p>
								   <p>저항 운동이나 근력 운동만 실시하는 것은 고혈압을 가진 사람들에게 혈압을 더 낮추는 데에 권고되지 않으며, 이것은 지구력 운동 트레이닝과 함께 실시하는 써키트 트레이닝과 같이 잘 계획된 체력 프로그램의 한 부분으로 포함될 때 권고됩니다. </p>
								   <p>이런 운동을 통한 효과를 살펴보면 가벼운 본태성 고혈압을 가진 사람들의(혈압 140~180/90~105mmHg) 수축기와 이완기 혈압을 평균 10mmHg 정도 감소시키며, 심혈관 질환에 대한 다른 위험 요소들을 향상하고, 신장 기능이상에 의한 2차성 고혈압을 가진 환자들의 혈압을 낮추며 안정 시와 보행 시 혈압을 감소시키고 혈장 지단백-지질 수치를 향상할 수 있습니다.</p>
		                           <a href="https://www.amc.seoul.kr/asan/healthstory/medicalcolumn/medicalColumnDetail.do?medicalColumnId=27507#:~:text=%EA%B3%A0%ED%98%88%EC%95%95%20%ED%99%98%EC%9E%90%EB%93%A4%EC%9D%98%20%ED%98%88%EC%95%95%EC%9D%84,%EC%9D%98%20%EC%9D%B4%EC%A0%84%EC%9D%98%20%EC%A7%80%EC%B9%A8%EC%84%9C%EC%9D%B8%20%E2%80%9C">
		                           		자료출처: 서울 아산 병원</a></strong></span></p>
	                           </div>
	                       </div>
	                   </div>
	                   <div class="faq shadow mb-4">
	                      <!-- FAQ Header - Accordion -->
	                    <a href="#FAQ3" class="d-block card-header py-3" data-toggle="collapse"
	                        role="button" aria-expanded="true" aria-controls="FAQ3">
	                        <h6 class="m-0 font-weight-bold text-primary">하루 권장 섭취 칼로리는 어떤 기준으로 산정하나요?</h6>
	                    </a>
	                    <!-- Card Content - Collapse -->
	                    <div class="collapse" id="FAQ3">
	                        <div class="card-body">
	                           <p>보건복지부와 대한영양사협회에서 출간한 2015 한국인 영양소 섭취기준 활용 가이드북의 에너지 필요량 공식을 적용하였습니다.</p>
	                           <p>하지만, 신체 활동 지수(PA)의 산출 근거가 없어서 최근 7일 운동 횟수를 기준으로 삼았고, 구체적인 내용은 아래와 같습니다.</p> 
	                           <div style="text-align: center;">
		                           <table class="table table-bordered" width="100%" cellspacing="0">
	                                  <thead>
	                                      <tr>
	                                      	<th>최근 7일 운동 횟수</th>				                             
	                                        <th>신체 활동 지수(PA)</th> 
	                                      </tr>
	                                  </thead>
	                                  <tbody>
	                    		     	<tr>
	                    		     		<td>0회</td>
	                    		     		<td>1.0(비활동적)</td>
	                    		     	</tr>
	                    		     	<tr>
	                    		     		<td>1~2회</td>
	                    		     		<td>1.1(저활동적)</td>
	                    		     	</tr>
	                    		     	<tr>
	                    		     		<td>3~5회</td>
	                    		     		<td>1.25(활동적)</td>
	                    		     	</tr>
	                    		     	<tr>
	                    		     		<td>6회 이상</td>
	                    		     		<td>1.48(매우 활동적)</td>
	                    		     	</tr>
	                    		     </tbody>
		                      	   </table> 
		                           <img src="../resources/static/etc/daily_calory.png" style="max-width: 70%;"/>
		                           <p>2015 한국인 영양소 섭취기준 활용 가이드북 22쪽 발췌</p> 
		                         </div>
	                        </div>
	                    </div>
	                </div>
	                <div class="faq shadow mb-4">
	                    <!-- FAQ Header - Accordion -->
	                    <a href="#FAQ4" class="d-block card-header py-3" data-toggle="collapse"
	                        role="button" aria-expanded="true" aria-controls="FAQ4">
	                        <h6 class="m-0 font-weight-bold text-primary">일주일 권장 섭취 단백질량은 어떤 기준으로 산정하나요?</h6>
	                    </a>
	                    <!-- Card Content - Collapse -->
	                    <div class="collapse" id="FAQ4">
	                        <div class="card-body">
	                           <p>맥마스터 대학교(McMaster University)의 마크 타놀프스키 연구에 따르면 
	                           최근 7일 운동 횟수 및 시간을 통해 적정 섭취 단백질량을 산정할 수 있습니다. 구체적인 내용은 아래와 같습니다.</p>
	                           <table class="table table-bordered" width="100%" cellspacing="0">
                                  <thead>
                                      <tr>
                                      	<th>최근 7일 운동 횟수와 평균 시간(분)</th>				                             
                                        <th>체중 1kg 당 하루 적정 섭취 단백질량(g)</th> 
                                      </tr>
                                  </thead>
                                  <tbody>
                    		     	<tr>
                    		     		<td>2회 이하</td>
                    		     		<td>0.8</td>
                    		     	</tr>
                    		     	<tr>
                    		     		<td>3~5회, 45~60분</td>
                    		     		<td>1.0</td>
                    		     	</tr>
                    		     	<tr>
                    		     		<td>5회 이상, 60분 이상</td>
                    		     		<td>1.2</td>
                    		     	</tr>
                    		     </tbody>
	                      	   </table> 
	                           <p><a href = "https://pubmed.ncbi.nlm.nih.gov/15212749/"><strong>자료 출처</strong></a>
	                           
	                        </div>
	                    </div>
	                </div>
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

</body>

</html>