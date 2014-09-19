<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String title = "Deal or No Deal";
%>
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

	<form method="post">
		<div id="game-board" class="row">
			<c:forEach items="${game.briefcases}" var="bc">
				<div class="col-md-3" id="${bc.key}">
					<c:if test="${bc.value.open}">
						<button type="button" class="btn btn-${bc.value.value < 200 ? 'success' : bc.value.value > 2000 ? 'danger' : 'primary'}" disabled>
							<fmt:formatNumber value="${bc.value.value}" type="currency" />
						</button>
					</c:if>
					<c:if test="${!bc.value.open}">
						<button type="submit" name="id" value="${bc.key}" class="btn btn-default">?</button>
					</c:if>
				</div>
			</c:forEach>
		</div>
		<c:if test="${game.offerRound}">
			<h2>Bank Offer: <fmt:formatNumber value="${game.offerAmount}" type="currency" /></h2>
			<div class="form-group">
				<button class="btn btn-success btn-lg btn-block" type="submit" name="deal" value="true">Deal</button>
				<button class="btn btn-danger btn-lg btn-block" type="submit" name="deal" value="false">No Deal</button>
			</div>
		</c:if>
	</form>
</body>
</html>
