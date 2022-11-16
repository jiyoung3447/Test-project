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
		let pwd = f.pwd.value;
		//유효성 체크 했다치고
		
		f.action="insert.do";  //form에있ㄴ느걸 insert.do로 보내라.
		f.method ="post";
		f.submit(); //전송
		
	}
</script>
</head>
<body>
<!-- 파일을 전송하는 폼태그에는 아래의 두 가지 속성이 "반드시" 추가되어 있어야 한다.!! -->
<form method="POST" enctype="multipart/form-data">
	<table border="1" align="center">
		<caption> :: 새 글 쓰기 ::</caption>
		<tr>
			<th>작성자</th>
			<th><input name="name"></th>
		</tr>
		<tr>
			<th>내용</th>
			<!-- wrap="on" : textarea에서 작성된 글이 길어서 다음줄로 넘어갔을 때 엔터값으로 인지 할 수 있도록 해주는 속성 -->
			<th><textarea name="content" rows="5" cols="50" wrap="on"></textarea></th>
		</tr>
		<tr>
			<th>비밀번호</th>
			<th><input type="password" name="pwd" ></th>
		</tr>
		<tr>
			<th>파일첨부</th>
			<th><input type="file" name="photo" ></th>
		</tr>
		<tr>
			<td td colspan="2" align="center">
			<input type="button" value="글쓰기" onclick="send(this.form);">
			<input type="button" value="목록으로" onclick="location.href='list.do'">
			</td>
		</tr>
	</table>
</form>

</body>
</html>