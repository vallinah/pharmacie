<%@page import="gst.CRUD" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>update</title>
</head>
<body>
    <%
        Class<?> cls = Class.forName(request.getParameter("cls"));
        CRUD crd = new CRUD(cls);
        out.println(crd.html_update(request.getParameter("id")));
    %>
</body>
</html>