<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<jsp:include page="header.jsp"/>
<div class="span12">
	<h1>${product.name}</h1>
	<p>${product.description}</p>
	<h2><f:formatNumber value="${product.price}" pattern="EUR #.00" /></h2>
	<div class="span3 thumbnail">
		<img src="img/${product.image}">
	</div>
</div>
<jsp:include page="footer.jsp"/>