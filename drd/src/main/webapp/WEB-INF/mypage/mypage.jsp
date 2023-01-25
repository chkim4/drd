<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Color Utilities</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<!-- Custom fonts for this template-->
<link href="/sbadmin/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/sbadmin/css/sb-admin-2.min.css" rel="stylesheet">

<script src="/sweetalert/sweetalert2.min.js"></script>
<link rel="stylesheet" href="/sweetalert/sweetalert2.min.css">

<script src="/resources/jquery/mypage/mypage-script.js"></script>

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
								 <a class="dropdown-item" href="/mypage/readAll" >
                                    <i class="fas fa-user-circle text-gray-400"></i>
                                       마이페이지
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
					<h1 class="h1 mt-4 mb-4 text-gray-800" style="font-weight: 800;">마이페이지</h1>

					<!-- Content Row -->
					<div class="row">

						<!-- First Column -->
						<div class="col-lg-6">

							<!-- Custom Text Color Utilities -->
							<div class="card shadow mb-4">
								<div class="card-header py-3 font-weight-bold text-primary">회원정보
									수정</div>
								<div class="card-body">
									<form class="user" action="/mypage/updateInfo" method="post"
										id="updateInfoForm">
										<div class="form-group">
											<div class="form-group row">
												<div class="col-sm-4 mb-3 mb-sm-0">
													<input type="text" value="이메일" style="text-align: center;"
														class="form-control form-control-user" readonly>
												</div>
												<div class="col-sm-8">
													<input type="text" class="form-control form-control-user"
														id="email" name="email" readonly="readonly"
														value="${member.email }">
												</div>
												<input type="hidden" id="age" name="age" />
											</div>
										</div>


										<div class="form-group">
											<div class="form-group row">
												<div class="input-group">
													<div class="col-sm-4 mb-3 mb-sm-0">
														<input type="text" value="닉네임" style="text-align: center;"
															class="form-control form-control-user" readonly>
													</div>
													<div class="col-sm-8">
														<input type="text" class="form-control form-control-user"
															id="nickName" name="nickName" value="${member.nickName }">

														<button type="button" class="btn btn-primary btn-sm mt-2"
															style="float: right; border-radius: 10%; font-size: 10px"
															id="checkNickNameBTN">중복체크</button>

													</div>
												</div>
											</div>
										</div>

										<div class="form-group">
											<div class="form-group row">

												<div class="col-sm-4 mb-3 mb-sm-0">
													<input type="text" value="생년월일" style="text-align: center;"
														class="form-control form-control-user" readonly>
												</div>
												<div class="col-sm-8">
													<input type="date" class="form-control form-control-user"
														id="birth" name="birth" value="${member.birth}"
														readonly="readonly">
												</div>
											</div>
										</div>
										<div class="form-group">
											<div class="form-group row">
												<div class="col-sm-4 mb-3 mb-sm-0">
													<input type="text" value="성별" style="text-align: center;"
														class="form-control form-control-user" readonly>
												</div>
												<div class="col-sm-8">
													<c:choose>
														<c:when test="${member.gender eq 0}">
															<input type="text" class="form-control form-control-user"
																id="gender" name="gender" readonly="readonly" value="남성">
														</c:when>
														<c:when test="${member.gender eq 1}">
															<input type="text" class="form-control form-control-user"
																id="gender" name="gender" readonly="readonly" value="여성">
														</c:when>
													</c:choose>
												</div>
											</div>
											<div class="mt-4" style="text-align: center;">
												<button type="button" class="btn btn-primary btn mt-1"
													id="updateInfoBTN">변경하기</button>
											</div>
									</form>
								</div>
							</div>
						</div>
					</div>


					<!-- Password Column -->
					<div class="col-lg-6">

						<!-- Custom Text Color Utilities -->
						<div class="card shadow mb-4">
							<div class="card-header py-3 font-weight-bold text-primary">나의
								헬스장</div>
							<div class="card-body">
								<form class="" action="" method="post" id="" name="">
									<div class="form-group row">
										<div class="col-sm-4 mb-3 mb-sm-0">
											<input type="text" value="헬스장" style="text-align: center;"
												class="form-control form-control-user" readonly>
										</div>
										<div class="col-sm-6">
											<div class="input-group">
												<input type="text" value=" " name="gym" id="gym" class="form-control bg-light border-0 small" readonly="readonly"
												 aria-label="Search"
												aria-describedby="basic-addon2">
												<div class="input-group-append">
													<button class="btn btn-primary" type="button">
														<i class="fas fa-search fa-sm"></i>
													</button>
												</div>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
						<div class="card shadow mb-4">
							<div class="card-header py-3 font-weight-bold text-primary">비밀번호
								변경</div>
							<div class="card-body">
								<form class="password" action="/mypage/updatePass" method="post"
									id="updatePwdForm" name="updatePwdForm">
									<div class="form-group row">
										<div class="col-sm-4 mb-3 mb-sm-0">
											<input type="text" value="현재 비밀번호"
												style="text-align: center;"
												class="form-control form-control-user" readonly>
										</div>
										<div class="col-sm-6">
											<input type="password" class="form-control form-control-user"
												id="userPass" name="userPass" placeholder="기존 비밀번호를 입력하세요..">
										</div>
									</div>
									<div class="form-group row">
										<div class="col-sm-4 mb-sm-0">
											<input type="text" value="새 비밀번호" style="text-align: center;"
												class="form-control form-control-user" readonly>
										</div>
										<div class="col-sm-6">
											<input type="password" class="form-control form-control-user"
												id="newPass" name="newPass" placeholder="새 비밀번호를 입력하세요..">
										</div>
									</div>
									<div class="form-group row">
										<div class="col-sm-4 mb-3 mb-sm-0">
											<input type="text" value="새 비밀번호 확인"
												style="text-align: center;"
												class="form-control form-control-user" readonly>
										</div>
										<div class="col-sm-6 mb-4">
											<input type="password" class="form-control form-control-user"
												id="pass" name="pass" placeholder="새 비밀번호를 입력하세요..">
										</div>
									</div>
									<input type="hidden" value="${member.memberSEQ }"
										id="memberSEQ" name="memberSEQ">
									<div style="text-align: center;">
										<button type="button" class="btn btn-primary btn"
											id="updatePwdBTN">변경하기</button>
									</div>
								</form>
							</div>
						</div>
						<form class="deleteuser" action="/mypage/deleteUser" method="post"
							id="deleteForm" name="deleteForm">
							<input type="hidden" name="memberSEQ" value=${member.memberSEQ }>
							<div style="text-align: center;">
								<button type="button" class="btn"
									style="float: right; font-size: 12px;" id="deleteBTN">탈퇴하기</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->

	</div>
	<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->
	</div>

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

	<!-- Bootstrap core JavaScript-->
	<script src="/sbadmin/vendor/jquery/jquery.min.js"></script>
	<script src="/sbadmin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/sbadmin/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/sbadmin/js/sb-admin-2.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
	$("#nickName").keyup(nickName_onKeyUp);
	$("#checkNickNameBTN").click(checkNickNameBTN_onClick);
	$("#updateInfoBTN").click(updateInfoBTN_onClick);
	$("#updatePwdBTN").click(function() {
						if ($("#userPass").val().replaceAll(" ", "") == "" || $("#userPass").val() != "${member.pass}") {
							Swal.fire({
								  icon: 'error',
								  title: '',
								  text: '현재 비밀번호를 확인해주세요',
								})
							$("#userPass").focus();
							return;
						}
						if ($("#newPass").val().replaceAll(" ", "") == "" || $("#pass").val().replaceAll(" ", "") == "") {
							Swal.fire({
								  icon: 'error',
								  title: '',
								  text: '새 비밀번호를 확인해주세요',
								})
							return false
						}
						if ($("#pass").val() != $(
								"#newPass").val()) {
							Swal.fire({
								  icon: 'error',
								  title: '',
								  text: '변경 비밀번호가 일치하지 않습니다.',
								})
							$("#pass").focus();
							return false
						} if ($("#pass").val().length < 4 ) {
							Swal.fire({
								icon : 'error',
								title : '비밀번호는 최소 4글자 이상이어야 합니다!',
							})
							return false
						}
						Swal.fire({
							  icon: 'success',
							  title: '',
							  text: '변경 되었습니다. 다시 로그인해주세요',
							}).then(function(){
						$("#updatePwdForm").submit();
							})
					})
	$("#deleteBTN").click(function () {
						    Swal.fire({
						      title: '정말로 탈퇴 하시겠습니까?',
						      text: "",
						      icon: 'warning',
						      showCancelButton: true,
						      confirmButtonText: '승인',
						      cancelButtonText: '취소',
						      reverseButtons: true, // 버튼 순서 거꾸로
						    }).then((result) => {
						      if (result.isConfirmed) {
						        Swal.fire({
						        	title:'탈퇴가 완료되었습니다.',
						        	text:'',
						        	icon:'success'
						        }).then(function(){
									$("#deleteForm").submit();
								})
						      }
						    })
						  });
	}); //document.ready
</script>

</body>

</html>