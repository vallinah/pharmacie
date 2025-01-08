package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

import gst.CRUD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/insert")
public class Insertion extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Enumeration<String> enumeration = req.getParameterNames();
        Vector<String> values = new Vector<>();
        String clsName = null;

        out.println(req.getParameter("nom_produit"));

        while (enumeration.hasMoreElements()) {
            String val = enumeration.nextElement();
            if (val.equals("cls")) {
                clsName = req.getParameter(val);
            } else {
                values.add(req.getParameter(val));
            }
        }
        try {
            CRUD crd = new CRUD(Class.forName(clsName));
            crd.insert(values);
            // resp.sendRedirect("crud.jsp?cls=" + clsName);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
}
