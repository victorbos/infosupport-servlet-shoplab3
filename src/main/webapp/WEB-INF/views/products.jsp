<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<jsp:include page="header.jsp"/>
<div class="span12">
	<c:forEach items="${products}" var="product"> 
		<div class="span3 thumbnail">
			<form action="basket" method="post">
				<input type="hidden" name="productId" value="${product.id}"/>
				<img src="img/${product.image}">
				<h3>${product.name}</h3>
				<p>Price: &euro;
                    <f:formatNumber value="${product.price}" pattern=" #.00" />
				</p>
				<input type="submit" class="btn btn-primary" value="Koop!">
				<c:url value="product" var="productDetailsUrl">
					<c:param name="productId" value="${product.id}"/>
				</c:url>
				<a href="${productDetailsUrl}" class="btn">Details</a>
			</form>
		</div>
	</c:forEach>
</div>
<jsp:include page="footer.jsp"/>