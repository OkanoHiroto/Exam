<%@page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	<c:param name="content">
		<h2>メニュー</h2>
		<div>
			<div><a href="StudentList.action">学生管理</a></div>
			<div><a href="TestRegist.action">成績登録</a></div>
			<div><a href="TestList.action">成績参照</a></div>
			<div><a href="SubjectList.action">科目管理</a></div>
			<div><a href="ClassList.action">クラス管理</a></div>
			<div><a href="Logout.action">ログアウト</a></div>
		</div>
	</c:param>
</c:import>