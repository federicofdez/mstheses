<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<h1>Master Thesis Management System</h1>
<c:if test="${username!=null}">
	<p>Hello, ${username} - <a href="<c:url value="${logoutURL}" />">LOG OUT</a>
	</p>
</c:if>