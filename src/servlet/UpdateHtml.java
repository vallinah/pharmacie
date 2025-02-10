package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import fn.Function;
import gst.CRUD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/update")
public class UpdateHtml extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        try {
            Class<?> cls = Class.forName(req.getParameter("cls"));
            String[] ids = req.getParameter("id").split("_");
            CRUD crd = new CRUD(cls);
            out.println(crd.html_update(ids));
        } catch (Exception e) {
            resp.setStatus(500);
            out.println(Function.giveJson("err", e.getMessage()));
        }
    }
}
