<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>강좌목록</title>
	<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
</head>
<body>
	<h1>[강좌목록]</h1>
	<table id="tab" border=1></table>
	<script id="temp" type="text/x-handlebars-template">
			<tr class="title">
				<td><input type="checkbox" id="checkAll"></td>
				<td>강좌번호</td>
				<td>강좌이름</td>
				<td>담당교수</td>
			</tr>

		{{#each clist}}
			<tr class="row">
				<td><input type="checkbox" class="check"></td>
				<td class="lcode">{{lcode}}</td>
				<td class="lname">{{lname}}</td>
				<td class="pname">{{pname}}</td>
			</tr>
		{{/each}}
	</script>
	
	<h1>[수강신청목록]</h1>
	<table id="tab1" border=1></table>
	<script id="temp1" type="text/x-handlebars-template">
			<tr class="title1">
				<td><input type="checkbox" id="checkAll1"></td>
				<td>학생번호</td>
				<td>학생이름</td>
				<td>점수</td>
			</tr>

		{{#each slist}}
			<tr class="row1">
				<td><input type="checkbox" class="check1"></td>
				<td class="scode">{{scode}}</td>
				<td class="sname">{{sname}}</td>
				<td class="grade">{{grade}}</td>
			</tr>
		{{/each}}
	</script>
	수강신청인원 : <span id="total"></span>
	과목평균 : <span id="avg"></span>
</body>

<script>
	$.ajax({
		type:"get",
		url:"json.jsp",
		dataType:"json",
		success:function(data){
			var temp=Handlebars.compile($("#temp").html());
			$("#tab").html(temp(data));
		}
	});
	
	$("#tab").on("click", ".row", function(){
		var lcode=$(this).find(".lcode").html();
		alert(lcode);
		$.ajax({
			type:"get",
			url:"json.jsp",
			dataType:"json",
			data:{"lcode":lcode},
			success:function(data){
				$("#total").html(data.total);
				$("#avg").html(data.avg);
				var temp=Handlebars.compile($("#temp1").html());
				$("#tab1").html(temp(data));
			}
		});		
	});
	
	//체크박스모두표시
	$("#tab").on("click", "#checkAll", function(){
		if($(this).is(":checked")){
			$("#tab .row .check").each(function(){
				$(this).prop("checked", true);
			});
		}
		else{
			$("#tab .row .check").each(function(){
				$(this).prop("checked", false);
			});
		}
	});
	
	//체크박스모두표시
	$("#tab1").on("click", "#checkAll1", function(){
		if($(this).is(":checked")){
			$("#tab1 .row1 .check1").each(function(){
				$(this).prop("checked", true);
			});
		}
		else{
			$("#tab1 .row1 .check1").each(function(){
				$(this).prop("checked", false);
			});
		}
	});
</script>

</html>