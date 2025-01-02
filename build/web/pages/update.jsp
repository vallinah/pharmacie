<%@page import="gst.CRUD" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>update</title>
    <link rel="stylesheet" href="../assets/styles/css/crud.css">
    <script src="../assets/styles/js/crud.js" defer></script>
</head>
<body>
    <%@include file="header.jsp" %>
    <section class="body">
        <a class="back" href="crud.jsp?cls=<%= request.getParameter("cls") %>">Retour</a>
        <%
            Class<?> cls = Class.forName(request.getParameter("cls"));
            CRUD crd = new CRUD(cls);
            out.println(crd.html_update(request.getParameter("id")));
        %>
    </section>
</body>
</html>