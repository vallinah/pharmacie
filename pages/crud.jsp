<%@page import="gst.CRUD" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD</title>
    <link rel="stylesheet" href="../assets/photos/icons/bootstrap-icons.min.css">
</head>
<body>
    <%
        Class<?> cls = Class.forName(request.getParameter("cls"));
        CRUD crd = new CRUD(cls);
    %>
    <a href="insert.jsp?cls=<%= cls.getName() %>">Insertion</a>

    <%= crd.html_liste() %>
</body>
</html>