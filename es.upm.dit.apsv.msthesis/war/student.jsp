<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%
	BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();
%>

<p>Profile: student.</p>
<c:choose>
	<c:when test="${msthesis != null}">
		<p>Status of your request: ${msthesis.status}<c:if test="${msthesis.rejected == true }"> (REJECTED)</c:if></p>
		<table>
			<tr>
				<th>Author</th>
				<th>Title</th>
				<th>Summary</th>
				<th>Tutor</th>
				<th>Secretary</th>
				<th>Status</th>
				<th>Report</th>
			</tr>
			<tr>
				<td><c:out value="${msthesis.author}" /></td>
				<td><c:out value="${msthesis.title}" /></td>
				<td><c:out value="${msthesis.summary}" /></td>
				<td><c:out value="${msthesis.tutor}" /></td>
				<td><c:out value="${msthesis.secretary}" /></td>
				<td><c:out value="${msthesis.status}" /></td>
				<td><c:choose>
						<c:when test="${msthesis.status lt 2}">No report</c:when>
						<c:when test="${msthesis.status==2 and msthesis.rejected==false}">
							<form action="<%=blobstoreService.createUploadUrl("/file")%>"
								method="post" enctype="multipart/form-data">
								<input id="author" name="author" type="hidden"
									value="${msthesis.author}" /> <input type="file" name="file" /> <input
									type="submit" value="Upload report" />
							</form>
						</c:when>
						<c:when test="${msthesis.status ge 3}">
							<form action="/file" method="get">
								<input id="author" name="author" type="hidden"
									value="${msthesis.author}" /> <input type="submit"
									value="Show report" />
							</form>
						</c:when>
						<c:otherwise>
							
						</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
	</c:when>
	<c:otherwise>
		<p>New Master thesis application</p>
		<form action="/newMsThesis" method="post" acceptcharset="utf-8">
			<input type="text" name="title" id="title" maxLength="255" size="20"
				required placeholder="Title" /> <input type="text" name="summary"
				id="summary" maxLength="255" size="20" required
				placeholder="Summary" /> <input type="text" name="tutor" id="tutor"
				maxLength="255" size="20" required placeholder="tutor" /> <input
				type="submit" value="Submit" />
		</form>
	</c:otherwise>
</c:choose>