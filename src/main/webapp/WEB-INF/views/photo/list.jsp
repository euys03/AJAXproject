<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사진 게시판</title>
		<!-- ajax 추가 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
	</head>
	<body>
		<h1>사진 게시판</h1>
		<div id="photo-wrapper" style="padding:100px; overflow-y:auto;"></div>
		<div id="photo-btn-container" style="text-align:center;">
			<!-- 총 게시물 개수, 현재까지 불러온 개수, 몇번째 인덱스인지 -->
			<input type="hidden" id="more-var" totalCount="5" currentSum="0" currentCount="0">
		</div>
		
		
		
		<script>
			const moreVar = $("#more-var");
			/* 페이징 처리와 동일 */
			const photoWrapper = $("#photo-wrapper");
		
			// 여러번 동작 (function에 담기)
			photoMoreAjax(1);
			function photoMoreAjax(start) {
				$.ajax({
					url : "/photo/more",
					data : { "start" : start },
					type : "post",
					success : function(result) {
						let html = "";
						for(let i = 0; i < result.length; i++) {
							html += "<div>"
							html += 	"<img src='/resources/bFileUploads/" + result[i].photoFileRename + "' width='100%'>";
							html += 	"<p class='caption'>" + result[i].photoContent + "</p>";
							html += "</div>";
						}
						moreVar.val(Number(start)+1);
						// 가져온 개수만큼 누적
						moreVar.attr("currentSum", Number(moreVar.attr("currentSum")) + result.length);	// 지금까지 쿼리한 갯수
						moreVar.attr("currentCount", 0);
						
						photoWrapper.append(html);
		
					},
					error : function() {
						alert("AJAX 처리 실패!!");
					}
				});
			}
			
			// 스크롤 이벤트
			$(window).scroll(function() {
				console.log("scrolling");
				// 가장 아래에 닿았는지 확인
				let scrollTop = $(window).scrollTop();		// 0 = 가장 상단
				let innerHeight = $(window).innerHeight();	// 문서의 높이
				let scrollHeight = $("body").prop("scrollHeight");	// <body>태그의 scroll 높이
				if(scrollTop + innerHeight >= scrollHeight) {
					console.log("bottom end!");
					// 개수 제한 
					if(moreVar.attr("totalCount") != moreVar.attr("currentSum")) {
						photoMoreAjax(moreVar.val());
					}
				}
			});
			
		</script>
	</body>
</html>