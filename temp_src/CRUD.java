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

@WebServlet("/pages/crud")
public class Crud extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Enumeration<String> enumeration = req.getParameterNames();
        Vector<String> values = new Vector<>();
        String clsName = null;
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
            resp.sendRedirect("crud.jsp?cls=" + clsName);
        } catch (Exception e) {
            resp.setStatus(500);
            e.printStackTrace(out);
        }
    }
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Enumeration<String> enumeration = req.getParameterNames();
        Vector<String> values = new Vector<>();
        String clsName = null;
        String id = null;
        while (enumeration.hasMoreElements()) {
            String val = enumeration.nextElement();
            if (val.equals("cls")) {
                clsName = req.getParameter(val);
            } else if (val.equals("id")) {
                id = req.getParameter(val);
            } else {
                values.add(req.getParameter(val));
            }
        }
        try {
            CRUD crd = new CRUD(Class.forName(clsName));
            crd.update(values, id);
            resp.sendRedirect("crud.jsp?cls=" + clsName);
        } catch (Exception e) {
            resp.setStatus(500);
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        try {
            Class<?> cls = Class.forName(req.getParameter("cls"));
            String id = req.getParameter("id");

            CRUD crd = new CRUD(cls);
            crd.delete(id);
            resp.sendRedirect("crud.jsp?cls=" + cls.getName());
        } catch (Exception e) {
            resp.setStatus(500);
            e.printStackTrace(out);
        }
    }
}