<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<hr />

<h3>Lista zakupów t1</h3>

<h3>Zakupy - Logowanie</h3>

<c:if test="${not empty param.login_error}">
	<p style="color: red">
		Logowanie nie powiodło się.<br /> Przyczyna:
		${SPRING_SECURITY_LAST_EXCEPTION.message}
	<p>
</c:if>


<br />
<form action="j_spring_security_check" method="POST">
	<table>
		<tr>
			<th>Login:</th>
			<th><input type="text" name="j_username" /></th>
		</tr>
		<tr>
			<th>Hasło:</th>
			<th><input type="password" name="j_password" /></th>
		</tr>
	</table>

	<input type="submit" value="Logowanie" />
</form>
