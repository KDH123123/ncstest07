<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자기이름-7.네트워크 프로그래밍 구현</title>
</head>
<body>
	<h1>게시글입력 테스트</h1>
	<form action="<%=request.getContextPath() %>/insert"  method="post">
		<p>
			<input type="text" name="writer" placeholder="작성자" >
		</p>
		<p>
			<input type="text" name="subject" placeholder="제목입력" required="required">
		</p>
		<p>
			<input type="text" name="content" placeholder="내용입력" required="required">
		</p>
		<p>
			<button type="submit">등록</button>
		</p>
	</form>
</body>
</html>