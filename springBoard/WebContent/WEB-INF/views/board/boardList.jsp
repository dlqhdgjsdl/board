<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>list</title>
</head>
<script type="text/javascript">

	$j(document).ready(function(){
		$j(document).on("click","input#alldata",function(){
			if(this.checked==true){
				$j("input[name='codeName']:checkbox").prop("checked", "checked");
			}else{
				$j("input[name='codeName']:checkbox").removeProp("checked");
			}
		});
		$j(document).on("click","input#search",function(){
			
			var checkboxValues = [];
		    $j("input[name='codeName']:checked").each(function(i) {
		        checkboxValues.push($j(this).val());
		    });
					$j.ajax({
						url:"/board/boardSearchAction.do",
						dataType:"html",
						type:"POST",
						data:{'list':checkboxValues},
						success:function(v){
							alert("조회완료");
							$j("table").html(v);
						},
						error:function (jqXHR, testStatus, errorThrown){
							alert("실패");
						}
					})
			})
	});

</script>
<body>


<table  align="center">
	<tr>
		<td align="right">
			total : ${totalCnt}
		</td>
	</tr>
	<tr>
		<td>
		
			<table id="boardTable" border = "1">
				<tr>
					<td width="80" align="center">
						Type
					</td>
					<td width="40" align="center">
						No
					</td>
					<td width="300" align="center">
						Title
					</td>
				</tr>
				<c:forEach items="${boardList}" var="list">
					<tr>
						<td align="center">
							${list.boardType}
						</td>
						<td>
							${list.boardNum}
						</td>
						<td>
							<a href = "/board/${list.boardType}/${list.boardNum}/boardView.do?pageNo=${pageNo}">${list.boardTitle}</a>
						</td>
					</tr>	
				</c:forEach>
			</table>
			
		</td>
	</tr>
	
	<tr>
		<td align="left">
			<input type="checkbox" id="alldata"><label>전체</label>
			<c:forEach var="i" items="${listOption}">
			<input type="checkbox" name="codeName" value="${i.codeId}" ><label>${i.codeName}</label>
			</c:forEach>
			<input type="button" id="search" value="조회"> 
		</td>
	</tr>
	
	<tr>
		<td align="right">
			<a href ="/board/boardWrite.do">글쓰기</a>
		</td>
	</tr>
</table>

</body>
</html>