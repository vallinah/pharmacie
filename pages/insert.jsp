<%@page import="gst.CRUD" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insertion</title>
</head>
<body>
    <%
        CRUD crd = new CRUD(Class.forName(request.getParameter("cls")));
        out.println(crd.html_insert());
    %>
    </body>
</html>