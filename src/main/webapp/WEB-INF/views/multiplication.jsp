<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>곱셈</title>
</head>
<body>

<div id="formDiv"></div>
<div id="resultDiv"></div>

<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

	/* formDiv 구성 */
	/* name이 아닌 id를 이용해야만 한다! -> name의 경우 이름이 중복되기 때문에 시스템 오류로 몇번을 줄 지 모를 수 있다!
	   -> id를 이용!
	   -> firstFactor1, firstFactor2 .. secondFactor1, secondFactor1 ..
	   -> 찾을 때도 firstFactor1, firstFactor2..로 찾으면 된다!*/
	/* var last_id = $('input[type="text"]:last').attr('id');
   	last_id++;
   	$('input[type="text"]:last').append('<input type="text" name="'+last_id+' id="'+last_id+'">'); */
   
	for (var i = 1; i <= 10; i++) {
		var firstFactorTag = document.createElement('input');
		firstFactorTag.setAttribute('id', 'firstFactor'+i);
		
	   	var secondFactorTag = document.createElement('input');
	   	secondFactorTag.setAttribute('id', 'secondFactor'+i);
	   	
	   	var productTag = document.createElement('input');
	   	productTag.setAttribute('id', 'product'+i);
	   	
		$("#formDiv").append(firstFactorTag, ' × ', secondFactorTag, ' = ', productTag, '<br>');
	}
	    
   	$("#formDiv").append("<button type='button'>계산하기</button>");
	
	/* resultDiv 구성 */
	$('button').on('click', function() {

		// 배열 선언
		var dataArr = new Array();
		
		for (var i = 1; i <= 10; i++) {
			// 객체 생성
			var dataObj = new Object();
			
			// 객체에 input 값을 담는다.
			dataObj.firstFactor = $('#firstFactor' + i).val();
			dataObj.secondFactor = $('#secondFactor' + i).val();
			dataObj.product = 'product' + i;
			
			// firstFactor가 '' 이거나 secondFactor가 '' 인 경우 factor 객체를 null로 만든다.
			if (dataObj.firstFactor == '' || dataObj.secondFactor == '') { 
				dataObj = null;
			}
			
			// 배열에 생성된 객체를 삽입한다. ('null'이 포함된 상태)
			dataArr.push(dataObj);
			
		}
		
		// null을 필터링하여 제거한 배열을 만든다.
		var removeNull = dataArr.filter((element) => element != null);
		
		// 배열을 json 형태의 문자열로 만든다.
		var jsonData = JSON.stringify(removeNull);
		
		$.ajax({
			url: "/",
			contentType: "application/json",
			data: jsonData,
			method: "POST",
			success : function(returnValue) {
				printProducts(returnValue);
			}
		});
		
		function printProducts(returnValue) {
			var value = JSON.parse(returnValue);
			
			value.forEach(function(data, idx) {
				for (var key in data) {
					for (var i = 1; i <= 10; i++) {
						if (('product' + i) == key) {
							$('#product'+i).attr('value',data[key]);
						} 
					}
				}
			})
		}
		
	});
	
</script>
</body>
</html>
