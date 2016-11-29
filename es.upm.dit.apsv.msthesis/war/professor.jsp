<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<p>Profile: professor.</p>
<c:if test="${fn:length(msthesesAsTutor)>0}">
	<p>You are the tutor of the following Master theses.</p>
	<table>
		<tr>
			<th>Status</th>
			<th>Author</th>
			<th>Title</th>
			<th>Summary</th>
			<th>Secretary</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${msthesesAsTutor}" var="msthesis">
			<tr>
				<td><c:out value="${msthesis.status}" /><c:if test="${msthesis.rejected == true}"> (R)</c:if></td>
				<td><c:out value="${msthesis.author}" /></td>
				<td><c:out value="${msthesis.title}" /></td>
				<td><c:out value="${msthesis.summary}" /></td>

				<c:choose>
					<c:when test="${msthesis.status == 1}">
						<c:if test="${msthesis.rejected == false }">
							<form action="/accept?role=tutor" method="post" acceptcharset="utf-8">
								<td><input type="text" name="secretary" id="secretary"
									maxLength="255" size="20" required placeholder="Secretary" /></td>
								<td><input type="hidden" name="author" value="${msthesis.author}" />
									<input type="submit" value="Accept as tutor" />
							</form>
							<form action="/reject?role=tutor" method="post" acceptcharset="utf-8">
									<input type="hidden" name="author" value="${msthesis.author}" />
									<input type="submit" value="Reject as tutor" />
							</form>
								</td>
							</form>
						</c:if>
					</c:when>
					<c:when test="${msthesis.status == 2}">
						<td><c:out value="${msthesis.secretary }" /></td>
						<td></td>
					</c:when>
					<c:when test="${msthesis.status == 3 }">
						<td><c:out value="${msthesis.secretary }" /></td>
						<td>
						<form action="/file" method="get">
							<input id="author" name="author" type="hidden"
								value="${msthesis.author}" /> <input type="submit"
								value="Show report" />
						</form>
						<c:if test="${msthesis.rejected == false}">
						<form action="/accept?role=tutor" method="post">
							<input id="author" name="author" type="hidden"
								value="${msthesis.author}" /> <input type="submit"
								value="Accept report" />
						</form>
						<form action="/reject?role=tutor" method="post">
							<input id="author" name="author" type="hidden"
								value="${msthesis.author}" /> <input type="submit"
								value="Reject report" />
						</form>
						</c:if>
						</td>
					</c:when>
					<c:when test="${msthesis.status ge 4}">
						<td><c:out value="${msthesis.secretary }" /></td>
						<td>
						<form action="/file" method="get">
							<input id="author" name="author" type="hidden"
								value="${msthesis.author}" /> <input type="submit"
								value="Show report" />
						</form>
						</td>
					</c:when>
				</c:choose>
			</tr>
		</c:forEach>
	</table>
</c:if>
<c:if test="${fn:length(msthesesAsSecretary) > 0}">
	<p>You are secretary of the following Master theses.</p>
	<table>
		<tr>
			<th>Status</th>
			<th>Author</th>
			<th>Title</th>
			<th>Summary</th>
			<th>Tutor</th>
			<th>Actions</th>
		</tr>
		<c:forEach items="${msthesesAsSecretary}" var="msthesis">
			<tr>
				<td><c:out value="${msthesis.status}" /></td>
				<td><c:out value="${msthesis.author}" /></td>
				<td><c:out value="${msthesis.title}" /></td>
				<td><c:out value="${msthesis.summary}" /></td>
				<td><c:out value="${msthesis.tutor}" /></td>
				<td><c:choose>
						<c:when test="${msthesis.status == 4}">
							<form action="/file" method="get">
								<input id="author" name="author" type="hidden"
									value="${msthesis.author}" /> <input type="submit"
									value="Show report" />
							</form>
							<c:if test="${msthesis.rejected == false}">
							<form action="/accept?role=secretary" method="post" acceptcharset="utf-8">
								<input id="author" name="author" type="hidden"
									value="${msthesis.author}" /> <input type="submit"
									value="Pass student" />
							</form>
							<form action="/reject?role=secretary" method="post" acceptcharset="utf-8">
								<input id="author" name="author" type="hidden"
									value="${msthesis.author}" /> <input type="submit"
									value="Fail student" />
							</form>
							</c:if>
						</c:when>
						<c:when test="${msthesis.status == 4}">
							<form action="/file" method="get">
								<input id="author" name="author" type="hidden"
									value="${msthesis.author}" /> <input type="submit"
									value="Show report" />
							</form>
						</c:when>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
</c:if>