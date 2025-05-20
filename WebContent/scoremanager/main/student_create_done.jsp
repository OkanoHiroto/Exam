<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<h2>学生情報登録</h2>
		<p>登録が完了しました</p>
		<div><a href="StudentCreate.action">戻る</a></div>
		<div><a href="StudentList.action">学生一覧</a></div>
	</c:param>
</c:import>
