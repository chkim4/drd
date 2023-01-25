<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>간단한 지도 표시하기</title>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=xbsurilrkj&submodules=geocoder"></script>
 <!--   <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=xbsurilrkj"></script> -->
 
     <script src="/sbadmin/vendor/jquery/jquery.min.js"></script>
      <script src="/sweetalert/sweetalert2.min.js"></script>
	<link rel="stylesheet" href="/sweetalert/sweetalert2.min.css">
     <style type="text/css">
     #iw_inner{
     	padding: 10px;
     }
     .btn{
     	color: #fff;
    background-color: #4e73df;
    border-color: #4e73df;
    display: inline-block;
    font-weight: 400;
    text-align: center;
    vertical-align: middle;
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    line-height: 1.5;
     }
     
     </style>
</head>
<body>

<div id="map" style="width:1100px; height:400px;"></div>


<script>

//sweetalert 전역 설정
var mySwal = Swal.mixin({ 
				confirmButtonColor: '#4E73DF',		
				confirmButtonText: '확인'
			  })

var map = new naver.maps.Map('map', {
    center: new naver.maps.LatLng(37.5344508, 126.9813365),
    zoom: 13
});


$.ajax({
	type: "GET",
    	url: "/dashboard/gymInfo",
        data: {},
        success: function (response) {
        	//console.log(response.data[0])
        	//각 주소로 좌표구해서 마커만들기
        	$.each(response.data,function(){
        		//console.log(response.data);
                //console.log(this["도로명주소"]);
               
                naver.maps.Service.geocode({
                    query: this["도로명주소"]
                }, function(status, response) {
                    if (status !== naver.maps.Service.Status.OK) {
                        return alert('Something wrong!');
                    }

                    var result = response.v2, // 검색 결과의 컨테이너
                        items = result.addresses; // 검색 결과의 배열

                     console.log(response);
                     var x = response.v2.addresses[0].x;
 					var y = response.v2.addresses[0].y;
 					//좌표당 마커
 				   var markerOptions = {
 						    position: new naver.maps.LatLng(y, x),
 						    map: map
 						};
 	                var marker = new naver.maps.Marker(markerOptions);
 	                //정보창
 	               

 	          var infowindow = new naver.maps.InfoWindow({
 	              content: contentString
 	          });
 	          
 	          naver.maps.Event.addListener(marker, "click", function(e) {
 	        	    if (infowindow.getMap()) {
 	        	        infowindow.close();
 	        	    } else {
 	        	        infowindow.open(map, marker);
 	        	    }
 	        	});
 	          //infowindow.open(map, marker);
 	                
                });//end geocode
              
                var contentString = [
	                  '<div id="iw_inner">',
	                  '   <h3>'+ this["상호명"] +'</h3>',
	                  '   <p>'+ this["도로명주소"] +'</p><br />',
	                  '   <p>'+ this["전화번호"] +'</p>',
	                  '<input type="button" class="btn" id="addFitness" onclick="myFunction()" value="나의 헬스장 추가하기" data-dismiss="modal">',
	                  '</div>'
	              ].join('');
					              
               
            })//end each
           
                }//end success
            })//end ajax
          
function myFunction(){
	var data = {};
	
	const name = document.getElementById("iw_inner").childNodes[1].innerText;//이름
	const address = document.getElementById("iw_inner").childNodes[3].outerText;//주소
	const telephoneNumber = document.getElementById("iw_inner").childNodes[6].innerText;//전화번호
	
	data["name"] = name;
	data["address"] = address;
	data["telephoneNumber"] = telephoneNumber;
	
	$.ajax({
			type: "POST",
		    url: "/gym/register",
		    data:   JSON.stringify(data),
		     dataType:'text',
		     contentType:'application/json',
		     success: function () {
		    	 mySwal.fire({
					  icon: 'success',
					  title: '나의 헬스장 등록 완료'
					  
					}).then(function() {
						location.reload();
					});
					
					
		      }
	
	});
}   


            

</script>
</body>
</html>