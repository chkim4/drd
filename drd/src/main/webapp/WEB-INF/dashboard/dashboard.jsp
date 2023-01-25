<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>다루다HOME</title>

<!-- Custom fonts for this template-->
<link href="/sbadmin/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/sbadmin/css/sb-admin-2.min.css" rel="stylesheet">
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=xbsurilrkj&submodules=geocoder"></script>
<script src="/sbadmin/vendor/jquery/jquery.min.js"></script>
<script src="/sweetalert/sweetalert2.min.js"></script>
<link rel="stylesheet" href="/sweetalert/sweetalert2.min.css">
</head>


<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			<!-- Sidebar - Brand -->
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="/dashboard/read">
				<div>
					<img src="../resources/static/logo/drd_white.png"
						style="max-width: 70%;" />
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
			<li class="nav-item"><a class="nav-link" href="/dashboard/read">
					<i class="fas fa-fw fa-tachometer-alt"></i> <span>대시보드</span>
			</a></li>

			<!-- Nav Item - Charts -->
			<li class="nav-item"><a class="nav-link" href="/record/index.do">
					<i class="fas fa-fw fa-table"></i> <span>기록</span>
			</a></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link"
				href="/personalroutine/setpage"> <i class="fas fa-running"></i>
					<span>루틴</span></a></li>

			<!-- Nav Item - user-chart -->
			<li class="nav-item"><a class="nav-link" href="/goal/readAll">
					<i class="fas fa-chart-pie"></i> <span>목표</span>
			</a></li>
			<!-- Nav Item - FAQ -->
			<li class="nav-item"><a class="nav-link" href="/member/faqPage">
					<i class="fas fa-info-circle"></i> <span>FAQ</span>
			</a></li>


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
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

					<!-- Sidebar Toggle (Topbar) -->
					<button id="sidebarToggleTop"
						class="btn btn-link d-md-none rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>

					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">

						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none"><a
							class="nav-link dropdown-toggle" href="#" id="searchDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-search fa-fw"></i>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
								aria-labelledby="searchDropdown">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input type="text"
											class="form-control bg-light border-0 small"
											placeholder="Search for..." aria-label="Search"
											aria-describedby="basic-addon2">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"></i>
											</button>
										</div>
									</div>
								</form>
							</div></li>

						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">${member.nickName}</span>
								<img class="img-profile rounded-circle"
								src="/sbadmin/img/undraw_profile.svg">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
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
								<a class="dropdown-item" href="/mypage/readAll"> <i
									class="fas fa-user-circle text-gray-400"></i> 마이페이지
								</a>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									로그아웃
								</a>
							</div></li>

					</ul>

				</nav>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">Dashboard</h1>
					</div>
					<!-- ===================================================================================================================================== -->

					<!-- Content Row -->

					<div class="row">
						<!-- ============================= 왼쪽 ================================================================= -->

						<div class="col-lg-3 mb-4">

							<!-- 프로필-->
							<div class="card athlete-profile shadow mb-4"
								id="athlete-profile">
								<div class="card-body text-center">
									<a href="/athletes/111698769"> <img
										src="https://mdbcdn.b-cdn.net/img/new/avatars/8.webp"
										class="rounded-circle mb-3" style="width: 150px;" alt="Avatar" />

										<h5 class="mb-2">
											<strong><a href="#">${member.nickName }</a></strong>
										</h5>
										<p class="text-muted">
											<c:set var="disease" scope="session"
												value="${member.disease }" />
											<c:choose>
												<c:when test="${member.disease eq 1}">
									           당뇨
									         </c:when>
												<c:when test="${member.disease eq 2}">
									            고혈압
									         </c:when>
												<c:when test="${member.disease eq 3}">
									            기타질환
									         </c:when>
												<c:otherwise>
									            건강한 초보자
									         </c:otherwise>
											</c:choose>
											<span class="badge bg-primary">PRO</span>
										</p>
									</a>
									<h5 class="card-title">${member.profileComment }</h5>

									<c:choose>
										<c:when test="${gymInfo != null}">
											<p class="card-text">
												나의 헬스장: ${gymInfo.name } <a href="/dashboard/deleteGym"
													id="deleteGym" class="btn btn-primary"><i
													class="fas fa-trash"></i></a>
											</p>
										</c:when>
										<c:otherwise>
											<p class="card-text">
												나의 헬스장 등록하기 <a href="#" id="findGymList"
													class="btn btn-primary" data-toggle="modal"
													data-target="#myModal"><i
													class="fas fa-search-location"></i></a>
											</p>
										</c:otherwise>
									</c:choose>



								</div>
								<!-- Button to Open the Modal -->
								<!-- <button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#myModal">Open modal
								</button> -->

								<!-- The Modal -->
								<div class="modal fade" id="myModal" role="dialog">
									<div class="modal-dialog modal-xl">
										<div class="modal-content">

											<!-- Modal Header -->
											<div class="modal-header">
												<h4 class="modal-title">나의 헬스장 등록하기</h4>
												<button type="button" class="close" data-dismiss="modal">&times;</button>
											</div>

											<!-- Modal body -->
											<div class="modal-body gym-content" style="overflow: hidden;">Modal
												body..</div>

											<!-- Modal footer -->
											<div class="modal-footer">
												<button type="button" class="btn btn-danger"
													data-dismiss="modal">Close</button>
											</div>

										</div>
									</div>
								</div>
								<%-- <div class="card-footer text-muted">
									<div class="card-section">
										<div class="text-label text-small mb-8px">최근 활동</div>
										<a class="text-large hover-orange"
											href="/activities/8277089645"> <strong> ${latestRecord.cardioObj}
											<c:forEach var="item" items="${latestRecord.fitnessObj.fitnessList}">

     <p>운동정보 : ${item.fitnessSEQ} • 세트 : ${item.set} • 횟수 : ${item.count} • 무게 : ${item.weight}</p>


</c:forEach>
											${latestRecord.fitnessObj}</strong> •
											<time class="timestamp text-medium"
												datetime="2022-12-22 20:50:00 UTC"> 날짜 :
												<fmt:formatDate value="${latestRecord. date}" pattern="yyyy-MM-dd"/>
												 </time>

										</a>
									</div>
									<div class="card-section">
										<a class="btn-card-link media media-middle"
											href="/athlete/training/log">
											<div class="media-body">내 트레이닝 기록</div>
											<div class="media-right">
												<span class="app-icon-wrapper  "><span
													class="app-icon icon-caret-right icon-dark icon-md"></span></span>
											</div>
										</a>
									</div>
								</div> --%>

							</div>
							<!-- 프로필 끝 -->
							<div class="card border-left-success shadow py-2">
								<div class="card-body">
									<div class="row no-gutters align-items-center">
										<div class="col mr-2">
											<div
												class="text-medium font-weight-bold text-success text-uppercase mb-1">
												최근 운동기록</div>
											<div class="h6 mb-0 font-weight-bold text-gray-800">
												<c:forEach var="item"
													items="${latestRecord.fitnessObj.fitnessList}">

													<p>
														운동:${item.fitnessSEQ}
														<%-- • 세트 : ${item.set} • 횟수 : ${item.count} • 무게 : ${item.weight} --%>
													</p>
												</c:forEach>
												<time class="timestamp text-medium"
													datetime="2022-12-22 20:50:00 UTC">
													날짜 :
													<fmt:formatDate value="${latestRecord. date}"
														pattern="yyyy-MM-dd" />
												</time>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-stopwatch fa-5x text-gray-300"></i>
										</div>
									</div>
								</div>

								<c:if test="${latestRecord == null}">
									<div class="card-footer">
										<span class="font-weight-bold text-primary">아직 운동기록이
											없습니다</span> <span class="ml-3 font-weight-bold" title="기록하러 가기">
											<a href="/record/index.do"><img
												src="/sbadmin/img/record.png" class="mb-2" width=25px;></a>
										</span>
									</div>
								</c:if>

							</div>

							<!-- 탭 -->
							<div class="my-4">
								<ul class="nav nav-tabs shadow" id="selectChart">
									<li class="nav-item"><a class="nav-link active"
										data-toggle="tab" href="#qwe"><i
											class="fas fa-chart-pie fa-fw me-2"></i>식단</a></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#asd"><i class="fas fa-chart-line fa-fw me-2"></i>운동목표</a></li>
								</ul>
								<div class="tab-content card border-0 shadow mb-4">
									<div class="tab-pane fade show active" id="qwe">
										<!--  Card Body -->
										<div class="card-body">
											<div class="chart-pie pt-4 pb-2">
												<canvas id="myChartDoughnut"></canvas>
											</div>
											<!-- <div class="mt-4 text-center small">
										<span class="mr-2"> <i
											class="fas fa-circle text-primary"></i> 식단1
										</span> <span class="mr-2"> <i
											class="fas fa-circle text-success"></i> 식단2
										</span> <span class="mr-2"> <i class="fas fa-circle text-info"></i>
											식단3
										</span>
									</div> -->
										</div>
									</div>
									<div class="tab-pane fade" id="asd">
										<div class="card-body">
											<div class="chart-pie pt-4 pb-2">
												<canvas id="myChartDoughnut2"></canvas>

											</div>
										</div>

									</div>
									<!-- <div class="tab-pane fade" id="zxc">
									<p>Curabitur dignissim quis nunc vitae laoreet. Etiam ut
										mattis leo, vel fermentum tellus. Sed sagittis rhoncus
										venenatis. Quisque commodo consectetur faucibus. Aenean eget
										ultricies justo.</p>
								</div> -->

									<c:if test="${takeProtein == 0 || totalCalory == 0}">
										<div class="card-footer">
											<span class="font-weight-bold text-primary">아직 식단기록이
												없습니다</span> <span class="ml-3 font-weight-bold" title="기록하러 가기">
												<a href="/record/index.do"><img
													src="/sbadmin/img/record.png" class="mb-2" width=25px;></a>
											</span>
										</div>
									</c:if>

								</div>
							</div>




							<!-- Illustrations -->
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">
										<a href="#">
											<div class>
												<a href="/goal/readAll">목표관리 
													<i class="fas fa-angle-right" ></i>
												</a>
											</div>
										</a>
									</h6>
								</div>
								<!-- <div class="card-body">
									<div class="text-center">
										<img class="img-fluid px-3 px-sm-4 mt-3 mb-4"
											style="width: 25rem;" src="img/undraw_posting_photo.svg"
											alt="...">
									</div>
									<p>
										Add some quality, svg illustrations to your project courtesy
										of <a target="_blank" rel="nofollow" href="https://undraw.co/">unDraw</a>,
										a constantly updated collection of beautiful svg images that
										you can use completely free and without attribution!
									</p>
									<a target="_blank" rel="nofollow" href="https://undraw.co/">Browse
										Illustrations on unDraw &rarr;</a>
								</div> -->
							</div>
							<div class="card shadow mb-4">
								<!-- Card Header - Dropdown -->
								<!-- <div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">Revenue
										Sources</h6>
									<div class="dropdown no-arrow">
										<a class="dropdown-toggle" href="#" role="button"
											id="dropdownMenuLink" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"> <i
											class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
										</a>
										<div
											class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
											aria-labelledby="dropdownMenuLink">
											<div class="dropdown-header">Dropdown Header:</div>
											<a class="dropdown-item" href="#">Action</a> <a
												class="dropdown-item" href="#">Another action</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item" href="#">Something else here</a>
										</div>
									</div>
								</div> -->
								<!--  Card Body -->
								<!-- <div class="card-body">
									<div class="chart-pie pt-4 pb-2">
										<canvas id="myPieChart"></canvas>
									</div>
									<div class="mt-4 text-center small">
										<span class="mr-2"> <i
											class="fas fa-circle text-primary"></i> Direct
										</span> <span class="mr-2"> <i
											class="fas fa-circle text-success"></i> Social
										</span> <span class="mr-2"> <i class="fas fa-circle text-info"></i>
											Referral
										</span>
									</div>
								</div>
							</div> -->

								<!-- Approach -->
								<!--  <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Development Approach</h6>
                                </div>
                                <div class="card-body">
                                    <p>SB Admin 2 makes extensive use of Bootstrap 4 utility classes in order to reduce
                                        CSS bloat and poor page performance. Custom CSS classes are used to create
                                        custom components and custom utility classes.</p>
                                    <p class="mb-0">Before working with this theme, you should become familiar with the
                                        Bootstrap framework, especially the utility classes.</p>
                                </div>
                            </div> -->

							</div>

							<!-- Area Chart -->



						</div>
						<!-- 오른쪽 끝 -->

						<!--LEFT Content Row -->

						<!-- ======================================= 왼 쪽 ===========================================================-->
						<div class="col-lg-8 mb-4">
							<!-- 차트  -->
							<div class="card shadow mb-4">
								<!-- Card Header - Dropdown -->
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">주단위 운동시간</h6>
									<div class="dropdown no-arrow">
										<a class="dropdown-toggle" href="#" role="button"
											id="dropdownMenuLink" data-toggle="dropdown"
											aria-haspopup="true" aria-expanded="false"> <i
											class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
										</a>
										<div
											class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
											aria-labelledby="dropdownMenuLink">
											<div class="dropdown-header">Dropdown Header:</div>
											<a class="dropdown-item" href="#">Action</a> <a
												class="dropdown-item" href="#">Another action</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item" href="#">Something else here</a>
										</div>
									</div>
								</div>
								<!-- Card Body -->
								<div class="card-body">
									<div class="chart-area">
										<%--  <canvas id="myAreaChart"></canvas> --%>
										<!-- <canvas id="line-chart"></canvas> -->
										<!-- <div class="chartCard"> -->
										<!-- <div class="chartBox"> -->
										<canvas id="myChartBar"></canvas>
										<!-- </div> -->
										<!-- </div> -->
									</div>
								</div>

								<c:if test="${latestRecord == null}">
									<div class="card-footer">
										<p class="font-weight-bold text-primary text-center"
											style="margin-bottom: 0">
											아직 운동기록이 없습니다 <span class="ml-3 font-weight-bold"
												title="기록하러 가기"> <a href="/record/index.do"><img
													src="/sbadmin/img/record.png" class="mb-2" width=25px;></a>
											</span>
										</p>
									</div>
								</c:if>
							</div>


							<!-- Project Card Example -->
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary">신체지수 피드백</h6>
								</div>
								<div class="card-body">
									<h4 class="small font-weight-bold">
										몸무게<span class="float-right"> ${memberBio.weight }kg</span>
									</h4>
									<div class="progress mb-4">
										<c:out value=""></c:out>
										<div class="progress-bar bg-danger" role="progressbar"
											style="width: ${memberBio.weight }%"
											aria-valuenow="${memberBio.weight }" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
									<h4 class="small font-weight-bold">
										BMI <span class="float-right">${Math.floor(memberBio.weight / (memberBio.height * memberBio.height / 10000))}</span>
									</h4>
									<div class="progress mb-4">
										<div class="progress-bar bg-warning" role="progressbar"
											style="width: ${Math.floor(memberBio.weight / (memberBio.height * memberBio.height / 10000))}%"
											aria-valuenow="${memberBio.weight / (memberBio.weight / 100) *(memberBio.weight / 100)}"
											aria-valuemin="0" aria-valuemax="100"></div>
									</div>
									<h4 class="small font-weight-bold">
										체지방 <span class="float-right">${memberBio.bfp }%</span>
									</h4>
									<div class="progress mb-4">
										<div class="progress-bar" role="progressbar"
											style="width: ${memberBio.bfp }%"
											aria-valuenow="${memberBio.bfp }" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
									<h4 class="small font-weight-bold">
										근육량 <span class="float-right">${memberBio.mp }%</span>
									</h4>
									<div class="progress mb-4">
										<div class="progress-bar bg-info" role="progressbar"
											style="width: ${memberBio.mp }%"
											aria-valuenow=${memberBio.mp } aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
									<%-- 
									<div>
									<p>
									<c:set var="disease" scope="session" value="${member.disease }" />
										 <c:choose>
											<c:when test="${member.disease eq 1}">
									           	인슐린으로 치료받고 있는 당뇨병 환자는 운동에 의한 저혈당을 방지하기 위하여 인슐린 용량을 줄여야 하며, 일단 저혈당 증상이 발생하면 즉시 탄수화물을 섭취할 수 있어야 합니다. 
									           	자율신경병증이 있어서 	소화능력에 문제가 있는 환자는 특히 단순당 형태로 탄수화물을 섭취해야 합니다. 단순당은 설탕, 사탕, 꿀, 초콜릿, 캐러멜, 콜라, 야구르트, 주스, 사과, 배, 포도,
									           	 복상아 통조림, 파인애플 통조림 등에 많이 포함되어 있습니다.
									         </c:when>
											<c:when test="${member.disease eq 2}">
									        	고혈압 환자들의 혈압을 낮추기 위한 권고된 운동의 방법은 자전거, 걷기, 조깅, 수영과 같은 대근육 활동을 주당 3~5일 정도 비연속적으로 실시하는 것이 바람직하며, 
												1회 운동시간은 20-60분, 강도는 최대산소섭취량의 50~85%정도로 일반적으로 미국스포츠 의학회의 이전의 지침서인 “건강한 성인에 심폐와 근력 발달과 유지를 위한 권고된
												 운동의 양과 질”에서와 동일합니다.
												그러나 더 낮은 강도(40~70% VO2max)에서 운동 트레이닝은 높은 강도에서 운동보다 더욱 혈압을 낮추는 것으로 나타났습니다. 그러나 현저하게 상승된 혈압(>180/105mmHg)을 
												가진 사람들은 초기 약물학적 치료 후에 치료 양생법으로 지구력 운동 트레이닝을 추가해야 합니다.

												저항 운동이나 근력 운동만 실시하는 것은 고혈압을 가진 사람들에게 혈압을 더 낮추는데에 권고되지 않으며, 이것은 지구력 운동 트레이닝과 함께 실시하는 써키트트레이닝과 같이 
												잘 계획된 체력 프로그램의 한 부분으로 포함될 때 권고됩니다.
									         </c:when>
									      
											<c:otherwise>
									           
									         </c:otherwise>
									         </c:choose> 
									</p>
									</div> --%>
								</div>
							</div>

							<!-- Collapsable Card Example -->
							<div class="card shadow mb-4">
								<!-- Card Header - Accordion -->
								<a href="#collapseCardExample" class="d-block card-header py-3"
									data-toggle="collapse" role="button" aria-expanded="true"
									aria-controls="collapseCardExample">
									<h6 class="m-0 font-weight-bold text-primary">운동시 유의사항</h6>
								</a>
								<!-- Card Content - Collapse -->
								<div class="collapse hidden" id="collapseCardExample">
									<div class="card-body">

										<div>
											<p>
												<c:set var="disease" scope="session"
													value="${member.disease }" />
												<c:choose>
													<c:when test="${member.disease eq 1}">
									           	인슐린으로 치료받고 있는 당뇨병 환자는 운동에 의한 저혈당을 방지하기 위하여 인슐린 용량을 줄여야 하며, 일단 저혈당 증상이 발생하면 즉시 탄수화물을 섭취할 수 있어야 합니다. 
									           	자율신경병증이 있어서 	소화능력에 문제가 있는 환자는 특히 단순당 형태로 탄수화물을 섭취해야 합니다. 단순당은 설탕, 사탕, 꿀, 초콜릿, 캐러멜, 콜라, 야구르트, 주스, 사과, 배, 포도,
									           	 복상아 통조림, 파인애플 통조림 등에 많이 포함되어 있습니다.
									         </c:when>
													<c:when test="${member.disease eq 2}">
									        	고혈압 환자들의 혈압을 낮추기 위한 권고된 운동의 방법은 자전거, 걷기, 조깅, 수영과 같은 대근육 활동을 주당 3~5일 정도 비연속적으로 실시하는 것이 바람직하며, 
												1회 운동시간은 20-60분, 강도는 최대산소섭취량의 50~85%정도로 일반적으로 미국스포츠 의학회의 이전의 지침서인 “건강한 성인에 심폐와 근력 발달과 유지를 위한 권고된
												 운동의 양과 질”에서와 동일합니다.
												그러나 더 낮은 강도(40~70% VO2max)에서 운동 트레이닝은 높은 강도에서 운동보다 더욱 혈압을 낮추는 것으로 나타났습니다. 그러나 현저하게 상승된 혈압(>180/105mmHg)을 
												가진 사람들은 초기 약물학적 치료 후에 치료 양생법으로 지구력 운동 트레이닝을 추가해야 합니다.

												저항 운동이나 근력 운동만 실시하는 것은 고혈압을 가진 사람들에게 혈압을 더 낮추는데에 권고되지 않으며, 이것은 지구력 운동 트레이닝과 함께 실시하는 써키트트레이닝과 같이 
												잘 계획된 체력 프로그램의 한 부분으로 포함될 때 권고됩니다.
									         </c:when>
													<c:otherwise>
									          운동은 규칙적이고 지속적으로 실시해야 하지만, 얼마나 자주 하느냐에 따라서 그 효과가 달라질 수 있다. 운동에 참가하는 횟수를 운동 빈도라고 하는데 일반적으로 주당 운동참여 횟수(예: 3일/주)로 표시합니다.
									           비활동적인 사람은 적응을 위한 적절한 시간을 갖기 위해서 처음 몇 주 동안은 주 1～3일의 빈도로 운동을 수행해야만 합니다. 처음 몇 주 동안 과도한 부하가 주어지면 통증, 피로 및 상해 등의 발생가능성이 높기 때문에,
									            매일 연속적으로 운동을 실시하는 것은 바람직하지 않습니다. 처음 몇 주 동안 부작용이나 문제가 없다면, 몇 개월이 지난 후 주 3～4일로 그 빈도를 점차적으로 증가시킬 수 있습니다.
									         </c:otherwise>
												</c:choose>
											</p>
										</div>
									</div>
								</div>
							</div>


						</div>


					</div>
					<!-- =================== row ================================================================= -->
					<!-- </div> -->
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

		<!-- Scroll to Top Button-->
		<a class="scroll-to-top rounded" href="#page-top"> <i
			class="fas fa-angle-up"></i>
		</a>

		<!-- Logout Modal-->
		<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-body">로그아웃 하시겠습니까?</div>
					<div class="modal-footer">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">아니오</button>
						<a class="btn btn-primary" href="/member/logout.do">네</a>

					</div>
				</div>
			</div>
		</div>

		<script>
			/* var goalTimeList = "{$goalTimeList}";
			 var fitnessMinList = "{$fitnessMinList}";
			 var cardioMinList = "{$cardioMinList}";
			 var excerciseWeek = "{$excerciseWeek}";
			 */
			var proteinAndCalory = "{$proteinAndCalory}";

			var protein = [];
			var calory = [];
			var goal = [];

			var fitnessList = [];
			var cardioList = [];
			var dateList = [];
			var timeList = [];

			var mySwal = Swal.mixin({
				confirmButtonColor : '#4E73DF',
				confirmButtonText : '확인'
			})

			$(".gym-content").load("/dashboard/readGym");
			$("#deleteGym").on("click", function() {
				mySwal.fire({
					icon : 'success',
					title : '나의 헬스장 삭제 완료'

				})
			})

			$(function() {

				$.ajax({
					url : "/dashboard/read",
					async : false,
					data : proteinAndCalory,
					type : "POST",
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					success : function(data) { // controllor에서 list<map> return 받았음
						console.log(data)
						protein.push(data[0]["take"]);
						protein.push(data[0]["rest"]);
						calory.push(data[1]["take"]);
						calory.push(data[1]["rest"]);
						$.each(data, function() {
							goal.push(this["goal"]);

						})
						console.log(protein);
						console.log(calory);
						console.log(goal);

						/*  new Chart(
						 	      document.getElementById('myChartDoughnut'),{
						 	      type: 'doughnut',
						 	      data: {
						 	    	  labels: [
						 	    		    'Red',
						 	    		    'Blue'
						 	    		 
						 	    		  ],
						 	    		  datasets: [{
						 	    		    label: 'My First Dataset',
						 	    		    data:  protein,
						 	    		    backgroundColor: [
						 	    		      'rgb(255, 99, 132)',
						 	    		      'transparent'
						 	    		      /*'rgb(54, 162, 235)'
						 	    		      'rgb(255, 205, 86)'
						 	    		    ],
						 	    		    hoverOffset: 4,
						 	    		    cutout:'90%',
						 	    		    borderRadius:20
						 	    		  }]
						 	    	},
						 	      options:{
						 	     	plugins:{
						 	     		legend:{
						 	      			display:false,
						 	      		}
						 	     	},
						 	      	tooltip:{
						 	      		enabled:false
						 	      	}
						 	      },
						 	      plugins:
						 	    	  [stackedText]
						 })  */
						/* new Chart(
						      document.getElementById('myChartDoughnut2'),{
						      type: 'doughnut',
						      data: {
						    	  labels: [
						    		    'Red',
						    		    'Blue'
						    		 
						    		  ],
						    		  datasets: [{
						    		    label: 'My First Dataset',
						    		    data:  calory,
						    		    backgroundColor: [
						    		      'rgb(255, 99, 132)',
						    		      'transparent'
						    		      /*'rgb(54, 162, 235)'
						    		      'rgb(255, 205, 86)'
						    		    ],
						    		    hoverOffset: 4,
						    		    cutout:'90%',
						    		    borderRadius:20
						    		  }]
						    	},
						      options:{
						     	plugins:{
						     		legend:{
						      			display:false,
						      		}
						     	},
						      	tooltip:{
						      		enabled:false
						      	}
						      },
						      plugins:
						    	  [stackedText2]
						}) */
					}
				})
				$.ajax({
					url : "/dashboard/readchart",
					async : false,
					type : "POST",
					dataType : "json",
					contentType : "application/json; charset=utf-8",
					success : function(map) { // controllor에서 list<map> return 받았음
						console.log(map)
						console.log(map.cardioMinByWeek)
						$.each(map.cardioMinByWeek, function(index, item) {
							cardioList.push(item.totalExerciseTimeofWeek);
						})
						$.each(map.fitnessMinByWeek, function(index, item) {
							fitnessList.push(item.totalExerciseTimeofWeek);
						})
						$.each(map.goalMinByWeek, function(index, item) {
							timeList.push(item);
						})

						$.each(map.dates, function(index, item) {
							dateList.push(item);
						})

						console.log(cardioList)
						console.log(fitnessList)
						console.log(dateList)
						console.log(timeList)

					}
				})
				myChartBar.update();
				myChartDoughnut.update();
				myChartDoughnut2.update();
				/*
				 $("#findGymList").on("click", function(){
					$.ajax({
						url:"/dashboard/gymInfo",
						type:"GET",
						success: function(data){
							console.log(data)
						}
						
					})//end ajax
				})//end click 
				 */
			})
		</script>
		<!-- Bootstrap core JavaScript-->

		<script src="/sbadmin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

		<!-- Core plugin JavaScript-->
		<script src="/sbadmin/vendor/jquery-easing/jquery.easing.min.js"></script>

		<!-- Custom scripts for all pages-->
		<script src="/sbadmin/js/sb-admin-2.min.js"></script>

		<!-- Page level plugins -->
		<!-- <script src="/sbadmin/vendor/chart.js/Chart.min.js"></script> -->

		<!-- Page level custom scripts -->
		<script type="text/javascript"
			src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<script
			src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns/dist/chartjs-adapter-date-fns.bundle.min.js"></script>
		<script
			src="https://cdnjs.cloudflare.com/ajax/libs/date-fns/1.9.0/date_fns.min.js"
			integrity="sha512-ToehgZGJmTS39fU8sfP9/f0h2Zo6OeXXKgpdEgzqUtPfE5By1K/ZkD8Jtp5PlfdaWfGVx+Jw5j10h63wSwM1HA=="
			crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		<script src="/sbadmin/js/mixedchart.js"></script>
</body>

</html>