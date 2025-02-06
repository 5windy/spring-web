<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: KimJiyeon
  Date: 2025/02/04
  Time: 12:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="/script/header.js"></script>
    <link rel="stylesheet" href="/style/global.css">
    <title>Demo Project</title>
</head>
<body>
<header>
    <h1>Demo Project</h1>
    <nav>
        <ul>
            <li><a href="/">홈으로</a></li>
            <c:choose>
                <c:when test="${empty authUser}">
                    <li><a href="/users/signup">회원가입</a></li>
                    <li><a href="/users/signin">로그인</a></li>
                </c:when>
                <c:otherwise>
                    <li><a id="logout">로그아웃</a></li>
                </c:otherwise>
            </c:choose>
            <li><a href="/boards">게시판</a></li>
            <li><a href="/boards/write">글작성</a></li>
        </ul>
    </nav>
</header>
</body>
</html>
