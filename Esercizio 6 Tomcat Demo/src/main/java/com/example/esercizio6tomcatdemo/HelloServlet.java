package com.example.esercizio6tomcatdemo;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import DAO.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
        DAO.registerDriver();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        ArrayList<Corso> lista = DAO.getTupleCorso();

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        for(Corso c : lista){
            out.println("<p>" + c + "</p>");
        }
        out.println("</body></html>");


    }

    public void destroy() {
    }
}