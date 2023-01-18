<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Dashboard</title>

<!-- Custom fonts for this template-->
<link href="/sbadmin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/sbadmin/css/sb-admin-2.min.css" rel="stylesheet">

 <script src="/sbadmin/vendor/jquery/jquery.min.js"></script>

</head>
  

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<ul
			class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
			id="accordionSidebar">

			     <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/member/index.do">
                <div>
                    <img src="../resources/static/logo/drd_white.png" style="max-width: 70%"></i>
                </div>
            </a>


			<!-- Divider -->
			<hr class="sidebar-divider my-0">

			<!-- Nav Item - Dashboard -->
			<li class="nav-item active"><a class="nav-link"
				href="/dashboard/read"> <i class="fas fa-fw fa-tachometer-alt"></i> <span>대시보드</span></a>
			</li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">Interface</div>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseTwo"
				aria-expanded="true" aria-controls="collapseTwo"> <i
					class="fas fa-fw fa-cog"></i> <span>트레이닝</span>
			</a>
				<div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
					data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Custom Components:</h6>
						<a class="collapse-item" href="buttons.jsp">트레이닝 달력</a> <a
							class="collapse-item" href="cards.jsp">나의 운동 프로그램</a>
					</div>
				</div></li>

			<!-- Nav Item - Utilities Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapseUtilities"
				aria-expanded="true" aria-controls="collapseUtilities"> <i
					class="fas fa-fw fa-wrench"></i> <span>커뮤니티</span>
			</a>
				<div id="collapseUtilities" class="collapse"
					aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Custom Utilities:</h6>
						<a class="collapse-item" href="utilities-color.html">커뮤니티</a> <a
							class="collapse-item" href="utilities-border.html">내 활동</a> <a
							class="collapse-item" href="utilities-animation.html">Animations</a>
						<a class="collapse-item" href="utilities-other.html">Other</a>
					</div>
				</div></li>

			<!-- Divider -->
			<hr class="sidebar-divider">

			<!-- Heading -->
			<div class="sidebar-heading">Addons</div>

			<!-- Nav Item - Pages Collapse Menu -->
			<li class="nav-item"><a class="nav-link collapsed" href="#"
				data-toggle="collapse" data-target="#collapsePages"
				aria-expanded="true" aria-controls="collapsePages"> <i
					class="fas fa-fw fa-folder"></i> <span>Pages</span>
			</a>
				<div id="collapsePages" class="collapse"
					aria-labelledby="headingPages" data-parent="#accordionSidebar">
					<div class="bg-white py-2 collapse-inner rounded">
						<h6 class="collapse-header">Login Screens:</h6>
						<a class="collapse-item" href="login.html">Login</a> <a
							class="collapse-item" href="register.html">Register</a> <a
							class="collapse-item" href="forgot-password.html">Forgot
							Password</a>
						<div class="collapse-divider"></div>
						<h6 class="collapse-header">Other Pages:</h6>
						<a class="collapse-item" href="404.html">404 Page</a> <a
							class="collapse-item" href="blank.html">Blank Page</a>
					</div>
				</div></li>

			<!-- Nav Item - Charts -->
			<li class="nav-item"><a class="nav-link" href="charts.html">
					<i class="fas fa-fw fa-chart-area"></i> <span>Charts</span>
			</a></li>

			<!-- Nav Item - Tables -->
			<li class="nav-item"><a class="nav-link" href="tables.html">
					<i class="fas fa-fw fa-table"></i> <span>Tables</span>
			</a></li>

			<!-- Divider -->
			<hr class="sidebar-divider d-none d-md-block">

			<!-- Sidebar Toggler (Sidebar) -->
			<div class="text-center d-none d-md-inline">
				<button class="rounded-circle border-0" id="sidebarToggle"></button>
			</div>

			<!-- Sidebar Message -->
			<div class="sidebar-card d-none d-lg-flex">
				<img class="sidebar-card-illustration mb-2"
					src="img/undraw_rocket.svg" alt="...">
				<p class="text-center mb-2">
					<strong>SB Admin Pro</strong> is packed with premium features,
					components, and more!
				</p>
				<a class="btn btn-success btn-sm"
					href="https://startbootstrap.com/theme/sb-admin-pro">Upgrade to
					Pro!</a>
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

					<!-- Topbar Search -->
					<form
						class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
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

						<!-- Nav Item - Alerts -->
						<li class="nav-item dropdown no-arrow mx-1"><a
							class="nav-link dropdown-toggle" href="#" id="alertsDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-bell fa-fw"></i> <!-- Counter - Alerts -->
								<span class="badge badge-danger badge-counter">3+</span>
						</a> <!-- Dropdown - Alerts -->
							<div
								class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="alertsDropdown">
								<h6 class="dropdown-header">Alerts Center</h6>
								<a class="dropdown-item d-flex align-items-center" href="#">
									<div class="mr-3">
										<div class="icon-circle bg-primary">
											<i class="fas fa-file-alt text-white"></i>
										</div>
									</div>
									<div>
										<div class="small text-gray-500">December 12, 2019</div>
										<span class="font-weight-bold">A new monthly report is
											ready to download!</span>
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="mr-3">
										<div class="icon-circle bg-success">
											<i class="fas fa-donate text-white"></i>
										</div>
									</div>
									<div>
										<div class="small text-gray-500">December 7, 2019</div>
										$290.29 has been deposited into your account!
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="mr-3">
										<div class="icon-circle bg-warning">
											<i class="fas fa-exclamation-triangle text-white"></i>
										</div>
									</div>
									<div>
										<div class="small text-gray-500">December 2, 2019</div>
										Spending Alert: We've noticed unusually high spending for your
										account.
									</div>
								</a> <a class="dropdown-item text-center small text-gray-500"
									href="#">Show All Alerts</a>
							</div></li>

						<!-- Nav Item - Messages -->
						<li class="nav-item dropdown no-arrow mx-1"><a
							class="nav-link dropdown-toggle" href="#" id="messagesDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-envelope fa-fw"></i>
								<!-- Counter - Messages --> <span
								class="badge badge-danger badge-counter">7</span>
						</a> <!-- Dropdown - Messages -->
							<div
								class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="messagesDropdown">
								<h6 class="dropdown-header">Message Center</h6>
								<a class="dropdown-item d-flex align-items-center" href="#">
									<div class="dropdown-list-image mr-3">
										<img class="rounded-circle" src="img/undraw_profile_1.svg"
											alt="...">
										<div class="status-indicator bg-success"></div>
									</div>
									<div class="font-weight-bold">
										<div class="text-truncate">Hi there! I am wondering if
											you can help me with a problem I've been having.</div>
										<div class="small text-gray-500">Emily Fowler Â· 58m</div>
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="dropdown-list-image mr-3">
										<img class="rounded-circle" src="img/undraw_profile_2.svg"
											alt="...">
										<div class="status-indicator"></div>
									</div>
									<div>
										<div class="text-truncate">I have the photos that you
											ordered last month, how would you like them sent to you?</div>
										<div class="small text-gray-500">Jae Chun Â· 1d</div>
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="dropdown-list-image mr-3">
										<img class="rounded-circle" src="img/undraw_profile_3.svg"
											alt="...">
										<div class="status-indicator bg-warning"></div>
									</div>
									<div>
										<div class="text-truncate">Last month's report looks
											great, I am very happy with the progress so far, keep up the
											good work!</div>
										<div class="small text-gray-500">Morgan Alvarez Â· 2d</div>
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="dropdown-list-image mr-3">
										<img class="rounded-circle"
											src="https://source.unsplash.com/Mv9hjnEUHR4/60x60" alt="...">
										<div class="status-indicator bg-success"></div>
									</div>
									<div>
										<div class="text-truncate">Am I a good boy? The reason I
											ask is because someone told me that people say this to all
											dogs, even if they aren't good...</div>
										<div class="small text-gray-500">Chicken the Dog Â· 2w</div>
									</div>
								</a> <a class="dropdown-item text-center small text-gray-500"
									href="#">Read More Messages</a>
							</div></li>

						<div class="topbar-divider d-none d-sm-block"></div>

						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas
									McGee</span> <img class="img-profile rounded-circle"
								src="img/undraw_profile.svg">
						</a> <!-- Dropdown - User Information -->
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<a class="dropdown-item" href="#"> <i
									class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i> Profile
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
									Settings
								</a> <a class="dropdown-item" href="#"> <i
									class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
									Activity Log
								</a>
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="#" data-toggle="modal"
									data-target="#logoutModal"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
									Logout
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
							<div class="card athlete-profile shadow mb-4" id="athlete-profile">
								<div class="card-body text-center">
									<a href="/athletes/111698769"> <img
										src="https://mdbcdn.b-cdn.net/img/new/avatars/8.webp"
										class="rounded-circle mb-3" style="width: 150px;" alt="Avatar" />

										<h5 class="mb-2">
											<strong><a href="#">${member.nickName }</a></strong>
										</h5>
										<p class="text-muted">
											<c:set var="disease" scope="session" value="${member.disease }" />
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
										</c:choose>  <span class="badge bg-primary">PRO</span>
										</p>
									</a>
									<h5 class="card-title">${member.profileComment }</h5>
									<p class="card-text">내가 등록한 헬스장
									<a href="#" class="btn btn-primary"><i class="fas fa-search-location"></i></a></p>

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
								
							</div><!-- 프로필 끝 -->
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
														운동정보 : ${item.fitnessSEQ}
														<%-- • 세트 : ${item.set} • 횟수 : ${item.count} • 무게 : ${item.weight} --%>
													</p>


												</c:forEach>
												<time class="timestamp text-medium"
												datetime="2022-12-22 20:50:00 UTC"> 날짜 :
												<fmt:formatDate value="${latestRecord. date}" pattern="yyyy-MM-dd"/>
												 </time>
												
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-stopwatch fa-5x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>

							<!-- 탭 -->
							<div class="my-4">
							<ul class="nav nav-tabs shadow" id="selectChart">
								<li class="nav-item"><a class="nav-link active"
									data-toggle="tab" href="#qwe"><i
										class="fas fa-chart-pie fa-fw me-2"></i>식단</a></li>
								<li class="nav-item"><a class="nav-link" data-toggle="tab"
									href="#asd"><i
										class="fas fa-chart-line fa-fw me-2"></i>운동목표</a></li>
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
							</div>
							</div>
							
					
							
						
							<!-- Illustrations -->
							<div class="card shadow mb-4">
								<div class="card-header py-3">
									<h6 class="m-0 font-weight-bold text-primary"><a href="#">
									<div class>목표관리
									<i class="fas fa-angle-right"></i>
									</div></a></h6>
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
						

						
					</div><!-- 오른쪽 끝 -->

					<!--LEFT Content Row -->
				
						<!-- ======================================= 왼 쪽 ===========================================================-->
						<div class="col-lg-8 mb-4">
						<!-- 차트  -->
							<div class="card shadow mb-4">
								<!-- Card Header - Dropdown -->
								<div
									class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
									<h6 class="m-0 font-weight-bold text-primary">요일별 운동시간</h6>
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
									<c:out value = ""></c:out>
										<div class="progress-bar bg-danger" role="progressbar"
											style="width: ${memberBio.weight }%" aria-valuenow="${memberBio.weight }" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
									<h4 class="small font-weight-bold">
										BMI <span class="float-right">${Math.floor(memberBio.weight / (memberBio.height * memberBio.height / 10000))}</span>
									</h4>
									<div class="progress mb-4">
										<div class="progress-bar bg-warning" role="progressbar"
											style="width: ${Math.floor(memberBio.weight / (memberBio.height * memberBio.height / 10000))}%" 
											aria-valuenow="${memberBio.weight / (memberBio.weight / 100) *(memberBio.weight / 100)}" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
									<h4 class="small font-weight-bold">
										체지방 <span class="float-right">${memberBio.bfp }%</span>
									</h4>
									<div class="progress mb-4">
										<div class="progress-bar" role="progressbar"
											style="width: ${memberBio.bfp }%" aria-valuenow="${memberBio.bfp }" aria-valuemin="0"
											aria-valuemax="100"></div>
									</div>
									<h4 class="small font-weight-bold">
										근육량 <span class="float-right">${memberBio.mp }%</span>
									</h4>
									<div class="progress mb-4">
										<div class="progress-bar bg-info" role="progressbar"
											style="width: ${memberBio.mp }%" aria-valuenow=${memberBio.mp } aria-valuemin="0"
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
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">Ã</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
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


   $(function(){
	   
	  
    $.ajax({
        url: "/dashboard/read",
        async: false,
        data: proteinAndCalory,
        type: "POST",
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        success : function(data) { // controllor에서 list<map> return 받았음
        	console.log(data)
        	protein.push(data[0]["take"]);
        	protein.push(data[0]["rest"]);
        	calory.push(data[1]["take"]);
        	calory.push(data[1]["rest"]);
        	$.each(data,function(){
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
        url: "/dashboard/readchart",
        async: false,
        type: "POST",
        dataType:"json",
        contentType: "application/json; charset=utf-8",
        success : function(map) { // controllor에서 list<map> return 받았음
        	console.log(map)
        	console.log(map.cardioMinByWeek)
        	$.each(map.cardioMinByWeek, function(index, item){
        		cardioList.push(item.totalExerciseTimeofWeek);
        	})
        	$.each(map.fitnessMinByWeek, function(index, item){
        		fitnessList.push(item.totalExerciseTimeofWeek);
        	})
        	$.each(map.goalMinByWeek, function(index, item){
        		timeList.push(item);
        	})
        	
        	$.each(map.dates, function(index, item){
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
	 <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/chart.js"></script> 
<script src="https://cdn.jsdelivr.net/npm/chartjs-adapter-date-fns/dist/chartjs-adapter-date-fns.bundle.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/date-fns/1.9.0/date_fns.min.js"
 integrity="sha512-ToehgZGJmTS39fU8sfP9/f0h2Zo6OeXXKgpdEgzqUtPfE5By1K/ZkD8Jtp5PlfdaWfGVx+Jw5j10h63wSwM1HA=="
  crossorigin="anonymous" referrerpolicy="no-referrer"></script>
   <script src="/sbadmin/js/mixedchart.js"></script>

</body>

</html>