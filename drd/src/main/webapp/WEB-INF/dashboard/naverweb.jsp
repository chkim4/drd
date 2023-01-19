<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>간단한 지도 표시하기</title>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=xbsurilrkj&submodules=geocoder"></script>
       <script src="/sbadmin/vendor/jquery/jquery.min.js"></script>
</head>
<body>
<div style="margin-top:20px; margin-bottom:10px; font-weight: bold;">
헬스장 지도
</div>

<div id="map" style="width:100%;height:80%;"></div>
<div id="data"></div>
<script>
var mapOptions = {
	    center: new naver.maps.LatLng(37.3595704, 127.105399),
	    zoom: 10
	};

	var map = new naver.maps.Map('map', mapOptions);

$(function(){
	
	$.ajax({
		url:'dashboard/gymInfo',
		type:'POST',
		dataType:'text',
		succsess: function(data){
			if(data){
				map = new naver.maps.Map('map', mapOptions);
				$(data).find("item").each(function(){
					var faciPointX = $(this).find("faciPointX").text();
					var faciPointY = $(this).find("faciPointY").text();
					
					marker = new naver.maps.Marker({
						position: new naver.maps.LatLng(faciPointX, faciPointY),
						map:map
					});
				});
			}
		}
	})
	
})





</script>
</body>
</html>