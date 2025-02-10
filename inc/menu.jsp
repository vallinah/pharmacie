<%@page import="gst.CRUD" %>
<%@page import="fn.Function"%>
<%@page import="fn.LinkHandler"%>
<%@page import="annotation.AnnotationClass"%>

<menu>
    <div class="menu_header">
        <h2>MENU</h2>
        <span class="close_menu">
            <i class="bi bi-x-circle"></i>
        </span>
    </div>
    <div class="menu_body">
        <div class="acc active">
            <div class="acc_head">
                <i class="bi bi-plus"></i>
                <h1>Listes Entites</h1>
                <i class="bi bi-chevron-right"></i>
            </div>
            <div class="acc_body">
                <% for (LinkHandler link : Function.listeLink()) { %>
                    <li>
                        <%= link.giveLink() %>
                    </li>
                <% } %>
            </div>
        </div>
    </div>
    <div class="menu_footer">

    </div>
</menu>