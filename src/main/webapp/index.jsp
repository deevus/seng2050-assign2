<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String title = "Deal or No Deal";
%>
<jsp:useBean id="game" class="game.DealOrNoDeal" scope="session" />
<jsp:setProperty name="game" property="*" />
<!DOCTYPE html>
<html>
<head>
	<title><%=title%></title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="css/site.css">
</head>
<body class="container">
	<div class="jumbotron">
		<h1><%=title%></h1>
	</div>

	<div class="row">
		<c:forEach items="${game.getBriefcases()}" var="bc">
			<div class="col-md-3"><h4>${bc.toString()}</h4></div>
		</c:forEach>
	</div>
</body>
</html>
