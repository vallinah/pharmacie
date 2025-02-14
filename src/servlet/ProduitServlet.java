package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import base.Produit;
import fn.All;
import fn.Functionality;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/produit")
public class ProduitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            All all = new All(Produit.class);
            req.setAttribute("rehetra", all.getAll(""));
            RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher("/pages/produit.jsp");
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
            String idMaladie = req.getParameter("id_maladie");
            String idCatp = req.getParameter("id_categorie_personne");

            Functionality fn = new Functionality();

            // out.println("\"" + idMaladie +  "\"" + " " + "\"" + idCatp + "\"");

            req.setAttribute("rehetra", fn.getFonctinnalite(idMaladie, idCatp));
            RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher("/pages/produit.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            resp.setStatus(500);
            out.println(e.getMessage());
        }
    }
}