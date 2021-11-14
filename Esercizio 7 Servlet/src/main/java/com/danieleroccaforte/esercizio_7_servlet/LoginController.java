package com.danieleroccaforte.esercizio_7_servlet;

import com.danieleroccaforte.esercizio_7_servlet.DAO.DAO;
import com.danieleroccaforte.esercizio_7_servlet.DAO.Utente;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

@WebServlet(name = "LoginController", value = "/login-controller")
public class LoginController extends HttpServlet {
    @Override
    public void init() throws ServletException {
        DAO.registerDriver();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); // per essere robusti rispetto a caratteri speciali (', etc)
        ServletContext ctx = getServletContext();
        PrintWriter out = response.getWriter();
        String button = request.getParameter("button");
        RequestDispatcher rd = ctx.getRequestDispatcher("/index.html");
        if (button!=null) {
            if (button.equals("Accedi")) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String role = getRole(email,password);
                if(role == null){
                    rd = ctx.getRequestDispatcher("/index.html");
                    out.println("<p>Utente non registrato, riprova</p>");
                }else if(role.equals("administrator")){
                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(30);
                    rd = ctx.getRequestDispatcher("/administrator.html");
                }else if(role.equals("student")){
                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(30);
                    rd = ctx.getRequestDispatcher("/student.html");
                }
            }
        }
        rd.forward(request, response);
    }

    private String getRole(String email, String password){
        String role = null;
        ArrayList<Utente> utente = DAO.getUtente(email, password);
        if(utente.isEmpty()){
            return role;
        }
        if(Objects.equals(utente.get(0).getEmail(), email) && Objects.equals(utente.get(0).getPassword(),password)){
            role = utente.get(0).getRuolo();
        }
        return role;
    }
}
