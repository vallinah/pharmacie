package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import base.Produit;
import base.Vente;
import fn.All;
import fn.Functionality;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/vente")
public class VenteServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            All all = new All(Vente.class);
            req.setAttribute("rehetra", all.getAll(""));
            RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher("/pages/vente.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            resp.setStatus(500);
            out.println(e.getMessage());
            // e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            String idCategoriePersonne = req.getParameter("id_categorie_personne");
            String idModeAdministration = req.getParameter("id_mode_administration");

            Functionality fn = new Functionality();

            req.setAttribute("rehetra", fn.getFonctinnalite2(idCategoriePersonne, idModeAdministration));
            RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher("/pages/vente.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            resp.setStatus(500);
            out.println(e.getMessage());
        }
    }
}
