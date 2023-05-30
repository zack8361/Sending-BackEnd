<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">

    <title>msi info</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://www.markuptag.com/bootstrap/5/css/bootstrap.min.css">
</head>
<body>
<div class="header">
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark" style="position:fixed; top:0; left:0; right:0; z-index:2;">
        <a class="navbar-brand" href="#">&nbsp;&nbsp;bottleProject 관리자 페이지</a>
        <ul class="navbar-nav">
            <li class="nav-item"><a class="nav-link" href="#" role="button" aria-expanded="false" onclick="showObjects();">HOME</a>
                <SCRIPT language="Javascript">
                    function showObjects() {
                        location.href = "/listAll?mode=sort&brand=All";
                    }
                </SCRIPT>
            <li class="nav-item">
                <a class="nav-link" href="#" value="#">유저관리</a></li>
            <a class="nav-link" href="#" value="#">신고된 메시지 관리</a>

<%--            <a class="nav-link" href="/logout" value="logout">로그아웃</a>--%>
        </ul>
    </nav>
</div>
<br><br><br><br>


<!-- Bootstrap JS -->
<script src="https://www.markuptag.com/bootstrap/5/js/bootstrap.bundle.min.js"></script>
</body>
</html>