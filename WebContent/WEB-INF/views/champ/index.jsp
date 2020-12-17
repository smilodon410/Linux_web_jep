<%@page import="gg.bit.utils.matchData.vo.ChampionVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Champion List</title>
</head>
<body>
	<h1>챔피언 리스트입니다</h1>
	<%
	List<ChampionVo> list = (List<ChampionVo>) request.getAttribute("list");
	%>
	
	<% for (ChampionVo vo: list) { %>
	<table border="1">
		<tr>
			<th> champ </th>
			<th><%= vo.getName() %></th>
			<br/>
		</tr>
	</table>
	<%} %>

</body>
</html>