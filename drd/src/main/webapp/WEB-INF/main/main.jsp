<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>로그인 페이지</title>
<!-- Custom fonts for this template-->
<link href="/sbadmin/vendor/fontawesome-free/sbadmin/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link href="/sbadmin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

<!-- Custom styles for this template-->
<link href="/sbadmin/css/sb-admin-2.min.css" rel="stylesheet">
<style>
/* Make the image fully responsive */
.carousel-inner img {
	width: 100%;
	height: 100%;
}
</style>
</head>
<script type="text/javascript">


</script>

<body class="bg-gradient-primary">

	<div class="mr-5 mt-2 mb-5 font-weight-bold" style="float: right;">
		<div class="mr-3" style="float:left;"><a style="color:#fff;" href="/member/login.do">로그인</a></div>
		<div style="float:left;"><a style="color:#fff;"  href="/member/register.do">회원가입</a></div>
	</div>
	<div class="container mt-4">
		<div class="mb-4 text-center" >
			<a href="/"><img class="img fluid" width=20% src="/sbadmin/img/drd_white2.png" alt="logo" /></a>
		</div>
		<div id="myCarousel" class="carousel slide" data-ride="carousel">

			<!-- Indicators -->
			<ul class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
			</ul>

			<!-- The slideshow -->
			<div class="carousel-inner">
	
				<div class="carousel-item active">
					<img src="/sbadmin/img/slideImg1.jpg" alt="" width="1100"
						height="500">
				</div>
				<div class="carousel-item">
					<img src="/sbadmin/img/slideImg2.jpg" alt="" width="1100"
						height="500">
				</div>
	
				<a href="/member/login.do" class="btn btn-light btn-icon-split"
					style="position: absolute; opacity:90%; top: 80%; left: 15%"> <span
					class="icon text-gray-600"> <i class="fas fa-arrow-right"
						></i>
				</span> <span class="text">운동 시작하기</span>
				</a>

			</div>

			<!-- Left and right controls -->
			<a class="carousel-control-prev" href="#myCarousel" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a> <a class="carousel-control-next" href="#myCarousel"
				data-slide="next"> <span class="carousel-control-next-icon"></span>
			</a>
		</div>

		<div>
		<h2 class="mt-5" style="color:white; text-align: center;">목표 달성에 도움이 되는 기능으로 <b>즐겁게 운동하세요</b></h2>
		</div>
		<div class="row mt-5">
			<div class="col-xl-4 col-md-6 mb-4">
					<div
						class="card-header py-2 flex-row align-items-center justify-content-between" style="text-align: center; border-radius: 5px;">
						<h6 class="m-0 font-weight-bold text-primary" style="text-align: center;">신체지수 관리</h6>
					</div>
					<div class="mt-3 ml-2 mr-2" style="text-align: center; color:#bdc0cc; font-size:13px;">
					신체지수를 기록하여 </br> 달성 목표에 가까워져보세요.
					</div>
			</div>

			<div class="col-xl-4 col-md-6 mb-4">
				<div
						class="card-header py-2 flex-row align-items-center justify-content-between" style="text-align: center; border-radius: 5px;">
						<h6 class="m-0 font-weight-bold text-primary" style="text-align: center;">루틴 추천</h6>
				</div>
				<div class="mt-3 ml-2 mr-2" style="text-align: center; color:#bdc0cc; font-size:13px;">
					신체 정보를 기반으로 운동 루틴을 추천하여 </br> 트레이닝 효과를 극대화 시켜줍니다.
				</div>
			</div>

			<div class="col-xl-4 col-md-6 mb-4">
				<div
						class="card-header py-2 flex-row align-items-center justify-content-between" style="text-align: center; border-radius: 5px;">
						<h6 class="m-0 font-weight-bold text-primary" style="text-align: center;">운동 및 식단 기록</h6>
				</div>
				<div class="mt-3 ml-2 mr-2" style="text-align: center; color:#bdc0cc; font-size:13px;">
					오늘의 운동과 식단을 기록할 수 있으며 </br> 목표 달성률을 확인할 수 있습니다.
				</div>
			</div>
		</div>






	</div>





	</div>
	<!-- end container -->

	<!-- Bootstrap core JavaScript-->
	<script src="/sbadmin/vendor/jquery/jquery.min.js"></script>
	<script
		src="/sbadmin/vendor/bootstrap/sbadmin/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="/sbadmin/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="/sbadmin/js/sb-admin-2.min.js"></script>

</body>

</html>