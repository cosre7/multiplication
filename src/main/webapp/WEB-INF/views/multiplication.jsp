<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>곱셈</title>
</head>
<body>

<div id="formDiv"></div>

<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript">

	/* formDiv 구성 */
	for (var i = 1; i <= 10; i++) {
		var firstFactorTag = document.createElement('input');
		firstFactorTag.setAttribute('id', 'firstFactor'+i);
		firstFactorTag.setAttribute('placeholder', '숫자1');
		firstFactorTag.setAttribute('onKeyup', "this.value=this.value.match(/^(-?)[0-9]*/g);");
		
	   	var secondFactorTag = document.createElement('input');
	   	secondFactorTag.setAttribute('id', 'secondFactor'+i);
	   	secondFactorTag.setAttribute('placeholder', '숫자2');
	   	secondFactorTag.setAttribute('onKeyup', "this.value=this.value.match(/^(-?)[0-9]*/g);");
	   	
	   	var productTag = document.createElement('input');
	   	productTag.setAttribute('id', 'product'+i);
	   	productTag.setAttribute('readonly', true); // readonly 지정 방법
	   	productTag.setAttribute('placeholder', '답');
	   	
		$("#formDiv").append(firstFactorTag, ' × ', secondFactorTag, ' = ', productTag, '<br>');
	}
	    
   	$("#formDiv").append("<button type='button'>계산하기</button>");
	
	/* 결과 구성 */
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
			
			// 둘 중 하나만 입력했을 때 "두 수 모두 입력하세요" 출력
			if (dataObj.firstFactor != '' && dataObj.secondFactor == '' ||
					dataObj.firstFactor =='' && dataObj.secondFactor != '') {
				alert("두 수 모두 입력하세요");
			}
			
			// firstFactor가 '' 이거나 secondFactor가 '' 인 경우 factor 객체를 null로 만든다.
			if (dataObj.firstFactor == '' || dataObj.secondFactor == '') {
				dataObj = null;
			}
			
			// 배열에 생성된 객체를 삽입한다. ('null'이 포함된 상태)
			dataArr.push(dataObj);
			
		}
		
		// null을 필터링하여 제거한 배열을 만든다.
		var removeNullDataArr = dataArr.filter((element) => element != null);
		
		// 배열을 json 형태의 문자열로 만든다.
		var jsonData = JSON.stringify(removeNullDataArr);
		
		$.ajax({
			url: "/",
			contentType: "application/json",
			data: jsonData,
			method: "POST"
			
		}).done(function(returnValue) {
			printProducts(returnValue);
		
		}).fail(function(error) {
			alert("계산 가능한 범위를 넘었습니다.");
		});
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
</script>
</body>
</html>
