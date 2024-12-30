<%@page import="fn.Function"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil</title>
    <link rel="stylesheet" href="../assets/photos/icons/bootstrap-icons.min.css">
</head>
<body>
    <h1>Hello Pharmacie</h1>

    <% for (Class<?> cls : Function.listeClass()) { %>
        <a href="insert.jsp?cls=<%= cls.getName() %>"><%= cls.getSimpleName() %></a>
        <br>
    <% } %>
</body>
</html