<%--
  Created by IntelliJ IDEA.
  User: KimJiyeon
  Date: 2025/02/04
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <link rel="stylesheet" href="/style/index.css">
</head>
<c:import url="/header" />
<body>
<div id="content-container">
    <section>
        <h2>Hello ${authUser.nickname}!</h2>
    </section>
</div>
</body>
<c:import url="/footer" />
</html>
