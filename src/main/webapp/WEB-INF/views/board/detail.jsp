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
			<!-- ***** 댓글 영역 ***** -->
			<!-- 댓글 등록 -->
			<table align="center" width="500" border="1">
				<tr>
					<td>작성자</td>
					<td><input type="text" id="rWriter" size="5"></td>
				</tr>
				<tr>
					<td><textarea rows="3" cols="55" id="rContents"></textarea></td>
					<td><button id="rSubmit">등록하기</button>
				</tr>
			</table>
			<!-- 댓글 목록 -->
			<table align="center" width="500" border="1" id="replyTable" >
				<thead>
					<tr>
						<!-- 댓글 갯수 -->
						<td colspan="4"><b id="replyCount"></b></td>
					</tr>
				</thead>
				<tbody>
					<!-- // 비워두는 것이 맞음 Ajax 통해 <script>에서 태그들 생성 -->
				</tbody>
			</table>
			
			
			<script>
				getReplyList();
			
			/* Ajax 작성 */
				
				// 1. 게시글 상세 정보 및 댓글 등록
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
				
				
				// 2. 댓글 목록 조회				
				function getReplyList() {
					const boardNo = "${board.boardNo}";
					$.ajax({
						url : "/reply/list",
						data : {"boardNo" : boardNo },
						type : "get",
						success : function(data) {
							// 댓글 개수 추가
							$("#replyCount").text("댓글 (" + data.length + ")");
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
									
									// 버튼 생성 및 링크 형태로 변경 -> <a>태그
									btnArea = $("<td width='80'>")
									.append("<a href='javascript:void(0)' onclick='modifyReply(this, \""+data[i].replyContents +"\", "+data[i].replyNo+");'>수정</a>")
									.append("<a href='javascript:void(0)' onclick='removeReply("+data[i].replyNo +");'>삭제</a>");
									
									// <tr>, <td> 등의 태그 안에 값을 넣어줌
									tr.append(rWriter);
									tr.append(rContent);
									tr.append(rCreateDate);	// tr 밑에 td 3개가 들어간 상태
									
									tr.append(btnArea);	// <a>태그 사용한 버튼을 생성
									
									tableBody.append(tr);
								}
							}
						},
						error : function() {
							alert("AJAX 처리 실패! 관리자 문의 요망");
						}
					});
			}
				
			// 2.1 수정 함수 새로 생성. 127열 'onclick='modifyReply();'
			function modifyReply(obj, replyContents, replyNo) {
// 				alert("test");
				let trModify = $("<tr>");	// <tr><td><input></td><td>수정완료</td></tr>
				trModify.append("<td colspan='3'><input type='text' id='modifyContent' size='50' value='"+replyContents+"'>");
				trModify.append("<td><button onclick='modifyReplyContents("+replyNo+");'>수정완료</button></td>");
				//console.log(obj);	// obj는 클릭하였을 때 출력된다 (this로 보내고 obj로 받기)
				trModify.append("<td><button>수정완료</button></td>");
				// jQuery 객체화
				$(obj).parent().parent().after(trModify);
			}
			
			
			// 2.2 수정완료 클릭 시 수정
			function modifyReplyContents(replyNo) {
				const modifiedContents = $("#modifyContent").val();
				$.ajax({
					url : "/reply/modify",
					data : {"replyNo" : replyNo, "replyContents" : modifiedContents},	// 댓글 번호와 수정 내용을 가져옴
					type : "post",
					success : function(data) {
						if(data == "1") {
// 							alert("댓글 수정 성공");
							getReplyList();	// 수정완료 시 새로고침하지 않아도 바로 댓글 목록 출력 
						}else{
							alert("실패 로그를 확인해주세요.");
							console.log(data);
						}
					},
					error : function() {
						alert("Ajax 처리 실패!");
					}
				});
			}
			
			
			// 2.3 댓글 삭제
			function removeReply(replyNo) {
				$.ajax({
					url : "/reply/delete",
					data : {"replyNo" : replyNo},
					type : "get",
					success : function(data) {
						if (data == "1") {
							alert("댓글 삭제 성공");
							getReplyList();	// 삭제완료 시 새로고침하지 안항도 바로 댓글 목록 확인 가능
						}else {
							alert("댓글 삭제 실패! 로그를 확인해주세요.");
							console.log(data);
						}
					},
					error : function() {
						alert("Ajax 처리 실패");
					}
				});
			}
			
			</script>
		</form>
	</body>

</html>