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
		<h1>게시글 상세 페이지</h1>
		<form action="" method="">
			<table align="center" border="1">
				<tr>
					<td>제목</td>
					<td><input type="text" name="boardTitle" value="${board.boardTitle }"></td>
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
						<input type="submit" value="수정">
						<input type="reset" value="취소">
					</td>
				</tr>
			</table>
			<!-- 댓글 영역 -->
			<!-- 댓글 등록 -->
			<table align="center" width="500" border="1">
				<tr>
					<td>작성자</td>
					<td><input type="text" id="rWriter"></td>
				</tr>
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
				getReplyList();
			
			
			/* Ajax 작성 */
				$("#rSubmit").on("click", function() {
					const boardNo = "${board.boardNo}";
					const boardWriter = $("#rWriter").val();
					const rContents = $("#rContents").val();
					$.ajax({
						url : "/reply/register",
						data : {
							"refBoardNo" : boardNo,
							"replyWriter" : boardWriter,
							"replyContents" : rContents },
						type : "post",
						success : function(result) {
							if(result == '1') {
								alert("댓글 등록 성공");
								$("#rWriter").val("");
								$("#rContents").val("");
// 								location.href = "";
							}else {
								alert("[에러 발생] 로그 확인 필요!")
								console.log(result);
							}
						},
						error : function() {
							alert("Ajax 처리 실패! 관리자 문의 요망");
							
						}
					})
				})
				
				
				/* 댓글 가져오기 */
				
				function getReplyList() {
					const boardNo = "${board.boardNo}";
					$.ajax({
						url : "/reply/list",
						data : {"boardNo" : boardNo },
						type : "get",
						success : function(data) {
							const tableBody = $("#replyTable tbody");
							tableBody.html("");
							let tr;
							let rWriter;
							let rContent;
							let rCreateDate;
							let btnArea;
							if(data.length > 0) {
								for(let i in data) {
									// <tr>, <td> 등의 태그를 코드로 생성했다고 생각하면 됨
									tr = $("<tr>");
									rWriter = $("<td width='100'>").text(data[i].replyWriter);
									rContent = $("<td>").text(data[i].replyContents);
									rCreateDate = $("<td width='100'>").text(data[i].rCreateDate);
									
									// <tr>, <td> 등의 태그 안에 값을 넣어줌
									tr.append(rWriter);
									tr.append(rContent);
									tr.append(rCreateDate);	// tr 밑에 td 3개가 들어간 상태
									
									tableBody.append(tr);
								}
							}
						},
						error : function() {
							alert("AJAX 처리 실패! 관리자 문의 요망");
						}
					});
			}
				
			</script>
		</form>
	</body>
</html>