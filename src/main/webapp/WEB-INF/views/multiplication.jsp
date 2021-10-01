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
	var formDivTag = "";
	for (i = 0; i < 10; i++) {
		formDivTag += "<input id='firstFactor'" + (i+1) + "/>"
						+ " × "
						+ "<input id='secondFactor'" + (i+1) + "/>"
						+ " = "
						+ "<input id='product'" + (i+1) + "/>";
		
	}
	formDivTag += "<button type='button'>계산하기</button>"
	
	$("#formDiv").html(formDivTag);
	
	/* resultDiv 구성 */
	$('button').on('click', function() {
		var factorsList = new Array();
		 
		for (j = 'a'.charCodeAt(0); j <= 'j'.charCodeAt(0); j++) {
			var i = j - 'a'.charCodeAt(0);
			var factors = new Object();
			factors.row = String.fromCharCode(j);
			factors.firstFactor = $('input[name=firstFactor]').eq(i).val();
			factors.secondFactor = $('input[name=secondFactor]').eq(i).val();

			// firstFactor가 '' 이거나 secondFactor가 '' 인 경우 factor 객체를 null로 만든다.
			if (factors.firstFactor == '' || factors.secondFactor == '') { 
				factors = null;
			}
			
			// null을 포함한 상태로 factorList 배열에 factor 객체를 넣어준다.
			factorsList.push(factors);
			
		}

		// null을 필터링하여 제거한 배열을 만든다.
		var removeNullRow = factorsList.filter((element) => element != null);
		console.log(removeNullRow);
		
		// 배열을 json 문자열로 반환한다.
		var jsonString = JSON.stringify(removeNullRow);
		console.log(jsonString);
		
		var returnValue = '[{"a":6},{"b":20},{"d":48}]';
		var value = JSON.parse(returnValue);
		/* console.log(value);
		console.log(value[1].b); */
		
		value.forEach(function(data, idx) {
			/* console.log(data); */
			for (var key in data) {
				console.log('키 : ' + key + '값 : ' + data[key]);
				if (key == 'a') {
					console.log('a : ' + data[key]);
					var src = "value=" + data[key];
					$("input[name=product]").attr('value',data[key]);
				}
			}
		})
		
		$("#resultDiv").text(jsonString);
		
		
	});
	
</script>
</body>
</html>
