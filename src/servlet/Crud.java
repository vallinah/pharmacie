package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Vector;

import fn.Function;
import gst.CRUD;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pages/crud")
public class Crud extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            out.println(Function.giveJson("cls", clsName));
        } catch (Exception e) {
            resp.setStatus(500);
            out.println(Function.giveJson("err", e.getMessage()));
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Enumeration<String> enumeration = req.getParameterNames();
        Vector<String> values = new Vector<>();
        try (BufferedReader read = req.getReader()) {
            String bd = read.readLine();
            String[] all = bd.split("&");
            for (String s : all) {
                values.add(URLDecoder.decode(s.split("=")[1], "UTF-8"));
            }
        } catch (Exception e) {
            resp.setStatus(500);
            out.println(Function.giveJson("err", e.getMessage()));
            return;
        }

        String clsName = null;
        String id = null;
        while (enumeration.hasMoreElements()) {
            String val = enumeration.nextElement();
            if (val.equals("cls")) {
                clsName = req.getParameter(val);
            } else if (val.equals("id")) {
                id = req.getParameter(val);
            }
        }

        try {
            CRUD crd = new CRUD(Class.forName(clsName));
            crd.update(values, id);
            out.println(Function.giveJson("cls", clsName));
        } catch (Exception e) {
            resp.setStatus(500);
            out.println(Function.giveJson("err", e.getMessage()));
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
        } catch (Exception e) {
            resp.setStatus(500);
            e.printStackTrace(out);
        }
    }
}
