package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import gst.CRUD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/delete")
public class Delete extends HttpServlet  {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Class<?> cls = Class.forName(req.getParameter("cls"));
            String id = req.getParameter("id");

            CRUD crd = new CRUD(cls);
            crd.delete(id);
            resp.sendRedirect("crud.jsp?cls=" + cls.getName());
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}