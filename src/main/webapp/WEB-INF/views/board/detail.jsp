<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 상세페이지</title>
		<!-- Ajax 추가 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	</head>
	<body>
		<h1>게시글 등록 페이지</h1>
		<form action="/board/register" method="post">
			<table align="center" border="1">
				<tr>
					<td>제목</td>
					<td><input type="text" name="boardTitle" value="${board.boardTile }"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="boardWriter" value="${board.boardWriter }"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea cols="50" rows="10" name="boardContents">${board.boardContents }</textarea></td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td><input type="file" name="boardFilename"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="등록">
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
			<!-- 댓글 영역 -->
			<!-- 댓글 등록 -->
			<table align="center" width="500" border="1">
				<tr>
					<td><textarea rows="3" cols="55" id="rContents"></textarea></td>
					<td><button id="rSubmit">등록하기</button>
				</tr>
			</table>
			<!-- 댓글 목록 -->
			<table align="center" width="500" border="1" id="replyTable">
				<thead>
					<tr>
						<!-- 댓글 갯수 -->
						<td colspan="4"><b id="replyCount"></b></td>
					</tr>
				</thead>
				<tbody>
				
				</tbody>
			</table>
			
			<script>
			/* Ajax 작성 */
				$("#rSubmit").on("click", function() {
					$.ajax({
						url : "",
						data : {}
						type : "",
						success : function() {
							
						},
						error : function() {
							
						}
					})
				})
			</script>
		</form>
	</body>
</html>