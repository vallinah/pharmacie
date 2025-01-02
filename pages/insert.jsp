<%@page import="gst.CRUD" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion</title>
    <link rel="stylesheet" href="../assets/styles/css/crud.css">
    <script src="../assets/styles/js/crud.js" defer></script>
    <script src="../assets/styles/js/insert.js" defer type="module"></script>
</head>
<body>
    <%@include file="../inc/header.jsp" %>
    <section class="body">
        <a class="back" href="crud.jsp?cls=<%= request.getParameter("cls") %>">Retour</a>
        <%
            CRUD crd = new CRUD(Class.forName(request.getParameter("cls")));
            out.println(crd.html_insert());
        %>
    </section>
</body>
</html>