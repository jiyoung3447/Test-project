<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function send(f){
		let name = f.name.value;
		let content = f.content.value;
		let pwd = f.pwd.value;  //여기까진 유효성검사때문에 넣어주는것임.
	
		f.action= "modify.do";
		f.method ="post";
		f.submit(); //전송
		

	}
</script>
</head>
<body>
<form>   <!-- form이 넘어가니까 폼테그안에 써주기 where절에 idx가 있으므로 hidden으로 넣어주기-->
	<input type="hidden" name="idx" value="${vo.idx}">
	<table border="1" align="center">
		<caption> ::수정하기::</caption>
		<tr>
			<th>작성자</th>
			<td><input name="name" value="${vo.name}"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><pre><textarea rows="5" cols="50" name="content">${vo.content}</textarea></pre></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type="password" name="pwd" value="${vo.pwd}"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
			<input type="button" value="수정" onclick="send(this.form);">
			<input type="button" value="목록" onclick="location.href='list.do'">
			
			</td>
			 
		</tr>
		
	</table>
</form>
</body>
</html>