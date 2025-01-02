<%@page import="gst.CRUD" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD</title>
    <link rel="stylesheet" href="../assets/styles/css/crud.css">
</head>
<body>
    <%@include file="header.jsp" %>
    <section class="body">
        <%
            Class<?> cls = Class.forName(request.getParameter("cls"));
            CRUD crd = new CRUD(cls);
        %>
        <div class="ins">
            <a href="insert.jsp?cls=<%= cls.getName() %>"><i class="bi bi-plus"></i> Insertion</a>
        </div>
        <%= crd.html_liste() %>
    </section>
</body>
</html>