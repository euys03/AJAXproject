<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사진 게시판</title>
	</head>
	<body>
		<h1>사진 게시판</h1>
		<div id="photo-wraper" style="padding:100px;"></div>
		<div id="photo-btn-container" style="text-align:center;">
			<button>더보기</button>
		</div>
		
		
		
		<script>
			function fn_more() {
				$.ajax({
					url : "/photo/more",
					data : { start : 1 },
					type : "post",
					success : function() {
						
					},
					error : function() {
						
					}
				})
			}
		</script>
	</body>
</html>