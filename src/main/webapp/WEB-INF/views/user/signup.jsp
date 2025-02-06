<%--
  Created by IntelliJ IDEA.
  User: KimJiyeon
  Date: 2025/02/04
  Time: 12:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <script src="/script/signup.js"></script>
    <link rel="stylesheet" href="/style/form.css">
    <title>Sign up</title>
</head>
<c:import url="/header" />
<body>
<div id="content-container">
    <section>
        <h2>Sign up</h2>
        <form method="POST" action="/users/signup">
            <input type="text" name="username" id="username" placeholder="username">
            <p id="error-msg-username" class="error-msg">*중복되는 아이디는 사용이 불가합니다.</p>
            <input type="password" name="password" id="password" placeholder="password">
            <input type="text" name="nickname" id="nickname" placeholder="nickname">
            <input type="submit" value="Sign up">
        </form>
    </section>
</div>
</body>
<c:import url="/footer" />
</html>
