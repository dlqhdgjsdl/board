<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>boardView</title>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("button#delete").click(function(){
			location.href="/board/boardDelete.do?no="+$("input#no").val();
		});
	});
 
</script> -->
<script type="text/javascript">
	/* $j(document).ready(function(){
		$j("button#delete").click(function(){
			var $frm=$j('.boardView :input')
			var param=$frm.serialize();
			$j.ajax({
				url:"/board/boardDelete.do",
				dataType:"json",
				type:"POST",
				data:param,
				success:function(data, textStatus, jqXHR){
					
					alert("성공");
				},
				error:function (jqXHR, textStatus, errorThrown){
					location.href="/board/boardList.do";
				}
				
			})
		});
	}); */
	 $j(document).ready(function(){
		$j("#delete").click(function(){
			var $frm=$j('.boardView :input')
			var param=$frm.serialize();
			$j.ajax({
				url:"/board/boardDeleteAction.do",
				dataType:"json",
				type:"POST",
				data:param,
				success:function(data, textStatus, jqXHR){
					
					alert("삭제성공");
					alert("메세지:"+data.success);
					location.href = "/board/boardList.do?pageNo="+1;
				},
				error:function (jqXHR, textStatus, errorThrown){
					alert('실패');
				}
				
			})
		});
	});
	 
</script>
</head>
<body>
<form class="boardView">
<table align="center">
	<tr>
		<td>
			<table border ="1">
				<tr>
					<td width="120" align="center">
					Title
					</td>
					<td width="400">
					${board.boardTitle}
						</td>
				</tr>
				<tr>
					<td height="300" align="center">
					Comment
					</td>
					<td>
					${board.boardComment}
					</td>
				</tr>
				<tr>
					<td align="center">
					Writer
					</td>
					<td>
					<input type="hidden" name="boardNum" id="boardNum" value="${board.boardNum}">
					<input type="hidden" name="boardType" id="boardType" value="${board.boardType}">
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="right">
			<a href="/board/boardList.do">List</a>
			<a href="/board/boardModify.do?boardNum=${board.boardNum} &boardTitle=${board.boardTitle} &boardComment=${board.boardComment} &boardType=${board.boardType}">Modify</a>
			<input id="delete" value="Delete" type="button"/>
			<!-- <a href="/board/boardDeleteAction.do" id="delete">Delete</a> -->
		</td>
	</tr>
</table>
</form>	
	
</body>
</html>