<%--
  Created by IntelliJ IDEA.
  User: KimJiyeon
  Date: 2025/02/04
  Time: 12:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <script src="/script/list.js"></script>
    <link rel="stylesheet" href="/style/list.css">
    <title>Board List</title>
</head>
<c:import url="/header" />
<body>
<div id="content-container">
    <section>
        <h2>List</h2>
        <div id="list-container">
            <div id="list-header" class="list-element">
                <div class="title">제목</div>
                <div class="author">작성자</div>
                <div class="reg-date">작성일</div>
                <div class="mod-date">수정일</div>
            </div>
            <div id="board-1" class="list-element">
                <div class="title">조회된 게시물이 없습니다.</div>
                <div class="author"></div>
                <div class="reg-date"></div>
                <div class="mod-date"></div>
            </div>

        </div>
        <div id="nav-paging"></div>
    </section>
</div>
</body>
<c:import url="/footer" />
</html>
