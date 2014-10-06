<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<title>${title}</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/site.css">
</head>
<body class="container">
	<div class="jumbotron">
		<h1>${title}</h1>
		<p>Round ${game.roundNumber}</p>
	</div>

	<form method="post">
		<div id="game-board" class="row">
			<c:forEach items="${game.briefcases}" var="bc">
				<div class="col-md-3" id="${bc.key}">
					<c:choose>
						<c:when test="${bc.value.open}">
							<c:set value="${bc.value.value < 200 ? 'success' : bc.value.value > 2000 ? 'danger' : 'primary'}" var="btnClass" />
							<button type="button" class="btn btn-${btnClass}" disabled>
								<fmt:formatNumber value="${bc.value.value}" type="currency" />
							</button>
						</c:when>
						<c:otherwise>
							<c:if test="${game.offerRound}">
								<button type="submit" name="id" value="${bc.key}" class="btn btn-default" disabled>?</button>
							</c:if>
							<c:if test="${!game.offerRound}">
								<button type="submit" name="id" value="${bc.key}" class="btn btn-default">?</button>
							</c:if>
						</c:otherwise>
					</c:choose>
				</div>
			</c:forEach>
		</div>
		<c:if test="${!game.gameOver}">
			<c:choose>
				<c:when test="${game.offerRound}">
					<div class="alert alert-warning" role="alert">
						<h2><c:if test="${game.finalOffer}">Final </c:if>Bank Offer: <fmt:formatNumber value="${game.offerAmount}" type="currency" /></h2>
						<p>Please choose if you would like to accept this offer below.</p>
					</div>
					<div class="form-group">
						<button class="btn btn-success btn-lg btn-block" type="submit" name="deal" value="true">Deal</button>
						<button class="btn btn-danger btn-lg btn-block" type="submit" name="deal" value="false">No Deal</button>
					</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-warning" role="alert">
						<p>Please choose a closed briefcase to open. </p>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${game.gameOver}">
			<div class="alert alert-success" role="alert">
				<h2>Congratulations</h2>
				<h3>You have won <fmt:formatNumber value="${game.winnings}" type="currency" />!</h3>
			</div>
			<div class="form-group">
				<button class="btn btn-primary btn-lg btn-block" type="submit" name="newgame" value="true">Start New Game</button>
			</div>
		</c:if>
	</form>
</body>
</html>
