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

    <title>다루다HOME</title>

   <!-- 템플릿 관련 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/4.1.3/js/sb-admin-2.min.js" integrity="sha512-COtY6/Rv4GyQdDShOyay/0YI4ePJ7QeKwtJIOCQ3RNE32WOPI4IYxq6Iz5JWcQpnylt/20KBvqEROZTEj/Hopw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/startbootstrap-sb-admin-2/4.1.3/css/sb-admin-2.min.css" integrity="sha512-RIG2KoKRs0GLkvl0goS0cdkTgQ3mOiF/jupXuBsMvyB3ITFpTJLnBu59eE+0R39bxDQKo2dsatA5CwHeIKVFcw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<!-- sweetalert 관련 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="/dashboard/read">
                <div>
                    <img src="../resources/static/logo/drd_white.png" style="max-width: 70%"/>
                </div>
            </a>
            
            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Nav Item - Pages Collapse Menu --> 
            <!-- 사이드 바 마이 페이지 관련. 추 후 작업 후 진행 예정 -->
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
<!--                 </a>  -->
                
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
                <div style="text-align: center;">  
	                <button type="button" class="btn btn-primary btn" onclick="location.href='/record/index.do'" >기록</button>
	                <button type="button" class="btn btn-primary btn" onclick="location.href='/personalroutine/setpage'" >루틴</button>
	                <button type="button" class="btn btn-primary btn" onclick="location.href='/dashboard/read'" >대시보드</button>
	                <button type="button" class="btn btn-primary btn" onclick="location.href='/goal/readAll'" >목표 설정</button>
	                <button type="button" class="btn btn-primary btn" onclick="location.href='/member/logout.do'" >로그아웃</button>
	           
                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2020</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

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
</body>
</html>