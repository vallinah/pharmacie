package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import base.ConseilDuMois;
import fn.All;
import fn.Functionality;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/conseildumois")
public class ConseilDuMoisServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            All all = new All(ConseilDuMois.class);
            req.setAttribute("rehetra", all.getAll(""));
            RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher("/pages/conseildumois.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            PrintWriter out = resp.getWriter();
            resp.setStatus(500);
            out.println(e.getMessage());
        }   
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            String date1 = req.getParameter("date1");
            String date2 = req.getParameter("date2");

            Functionality fn = new Functionality();

            req.setAttribute("rehetra", fn.getFonctinnalite3(date1, date2));
            RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher("/pages/conseildumois.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            resp.setStatus(500);
            out.println(e.getMessage());
        }
    }
    
}