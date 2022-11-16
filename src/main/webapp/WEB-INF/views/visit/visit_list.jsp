<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/vs/resources/js/httpRequest.js"></script>

<style>
*{margin:0; padding: 0;}
	#main_box{
				width :330px;
				margin : 0 auto;}
	h1 { text-align: center;
		font-size :20px;
		margin: 10px 0;
		color : #0a516d;
		text-shadow : 2px 2px 2px #eee; }
	.visit_box{
				margin: 0 auto;
				width : 330px;
				margin-top : 30px;
				box-shadow: 2px 2px 2px #eee;
				border : 1px solid blue;}
	.type_content{
		min-height:100px;
		height:auto;
		background : #fafafa;
	}
	.type_name{
	background : #cfc;
	}
	.type_regdate{
		background : #ccf;
	}
</style>
<script>
function del(f){
	var pwd = f.pwd.value;//원본 비밀번호
	var c_pwd=f.c_pwd.value;//비교를 위한 비밀번호
	
	if(pwd != c_pwd){
	alert('비밀번호');
	return;
	}
	//Ajax를 통해 idx를 서버로 전송
	var url="delete.do";
	var param="idx="+f.idx.value;
	//delete.do?idx=1   &pwd=1111&c_pwd=1111
	sendRequest(url,param,resultFunc,"POST");
}
//삭제 결과를 확인할 콜백 메서드
function resultFunc(){
	
	if(xhr.readyState == 4 && xhr.status==200){
		//컨트롤러에서 삭제 후 return해준 데이터를 받는다
		var data = xhr.responseText;
		if(data =="no"){
			alert("삭제실패");
			return;
		}else{
			alert("삭제성공");
			location.href="list.do";
		}
		
	}
}

function modify(f){
	var pwd = f.pwd.value;
	var c_pwd = f.c_pwd.value;
	
	if(pwd != c_pwd){
		alert("비밀번호가 다릅니다.");
		return;
	}
	f.action="modify"
}

</script>
</head>
<body>
<div id="main_box">
		<h1>**방명록 리스트**</h1>
		<div align="center">
			<input type="button" value="글쓰기" onclick="location.href='insert_form.do'">
		</div>
		
		<c:forEach var="vo" items="${ list }">
 	  		<div class="visit_box">
 	  			<div class="type_content"><pre>${ vo.content }</pre></div>
 	  			<div class="type_name">${ vo.name }</div>
 	  			<div class="type_regdate">작성일 : ${ vo.regdate }</div>
 	  			
 	  			<div>
 	  				<form>
 	  					<input type="hidden" name="idx" value="${ vo.idx }">
 	  					<input type="hidden" name="pwd" value="${ vo.pwd }">
 	  					비밀번호<input type="password" name="c_pwd">
 	  					<input type="button" value="수정" onclick="modify(this.form);">
 	  					<input type="button" value="삭제" onclick="del(this.form);">
 	  				</form>
 	  			</div>
 	  		</div> <!-- visit_box -->
 	 
 	</c:forEach>	
	</div>

</body>
</html>