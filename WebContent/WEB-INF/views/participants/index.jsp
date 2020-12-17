<%@page import="gg.bit.utils.matchData.vo.ParticipantsVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Match 데이터</title>
</head>
<body>
	<h1>Match 정보</h1>
	<%
	List<ParticipantsVo> list = (List<ParticipantsVo>) request.getAttribute("list");
	%>
	<% int a = 0; %>
	<% for (ParticipantsVo vo: list) { %>
	<table border="1">
		<tr>
			<th>UserName</th>
			<th>TeamId</th>
			<th>ChampionId</th>
			<th>Kill</th>
			<th>Death</th>
			<th>Assist</th>
			<th>Dealing</th>
			<th>win</th>
		</tr>
		<tr>
			<td><%= vo.getSummonerName() %></td>
			<td><%= vo.getTeamIdString()%></td>
			<td><%= vo.getChamp_name()%></td>
			<td><%= vo.getKill()%></td>
			<td><%= vo.getDeath()%></td>
			<td><%= vo.getAssist()%></td>
			<td><%= vo.getDeal()%></td>
			<td><%= vo.getWin() %></td>
		</tr>
	</table>
	<% a++; %>
	<% if ( a % 5 == 0 ) {%>
	<br/>
	<br/>
	<%} %>
	<% } %>
</body>
</html>