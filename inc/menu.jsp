<%@page import="gst.CRUD" %>
<%@page import="fn.Function"%>

<%
    Class<?> cl = Class.forName("base.ConseilDuMois");
    CRUD crud = new CRUD(cl);
%>

<div class="acc">
<div class="acc_ttr">
    <i class="bi bi-person-circle"></i>
    <h2>Listes Entites</h2>
    <i class="bi bi-chevron-right"></i>
</div>                                                                      
<div class="acc_body">
    <% for (Class<?> kilasy : Function.listeClass()) { %>
        <div class="prt">
            <i class="bi bi-person-badge"></i>
            <a href="crud.jsp?cls=<%= kilasy.getName() %>"><%= kilasy.getSimpleName() %></a>
        </div>
    <% } %>
    <div class="prt">
        <i class="bi bi-person-badge"></i>
        <a href="mouvement.jsp">Mouvement</a>
    </div>
    <div class="prt">
        <i class="bi bi-person-badge"></i>
        <a href="conseildumois.jsp">Conseil du Mois</a>
    </div>
    <div class="prt">
        <i class="bi bi-person-badge"></i>
        <a href="client.jsp">Client</a>
    </div>
    <div class="prt">
        <i class="bi bi-person-badge"></i>
        <a href="vendeur.jsp">Vendeur</a>
    </div>
</div>
</div>