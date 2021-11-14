package com.danieleroccaforte.esercizio_7_servlet;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import com.danieleroccaforte.esercizio_7_servlet.DAO.*;

@WebServlet(name = "loginservlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Login con successo";
        DAO.registerDriver();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        int time = 20;
        PrintWriter out = response.getWriter();
        if(request.getParameter("email")==null){
            System.out.println("sessione scaduta");
        }
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(time);
        String button = request.getParameter("button");
        switch (button) {
            //SCHERMATA LOGIN
            case "Accedi":
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                ArrayList<Utente> utente = DAO.getUtente(email, password);
                session.setAttribute("email",email);
                session.setAttribute("password", password);
                if (Objects.equals(utente.get(0).getEmail(), "") || Objects.equals(utente.get(0).getPassword(), "")){
                    session.invalidate();
                    out.println("<html><body>" +
                            "<h2>Utente non registrato</h2>" +
                            "<button type = \"button\">Riprova</button>" +
                            "</body></html>");
                } else if (Objects.equals(utente.get(0).getRuolo(), "administrator") ) {

                } else if (Objects.equals(utente.get(0).getRuolo(), "student")) {
                    out.println("<html><body>");
                    out.println("<h1>Benvenuto " + utente.get(0).getNome() + " " + utente.get(0).getCognome() + "</h1>");
                    out.println("<h3>Il tuo ruolo: " + utente.get(0).getRuolo());
                }
                break;
                //SCHERMATA AGGIUNTA DOCENTE SE AMMINISTRATORE
            case "Aggiungi":
                if(session.isNew()){
                    session.invalidate();
                    out.println("<html><body>");
                    out.println("<p>Sessione Scaduta , riaccedi</p>" +
                            "<button onclick=\"location.href='Esercizio 7 Servlet/src/main/webapp/index.html'\" >Riaccedi</input>");
                    out.println("</body></html>");
                }else {
                    String nome = request.getParameter("nome");
                    String cognome = request.getParameter("cognome");
                    if (nome != null && cognome != null) {
                        DAO.setTuplaDocente(nome, cognome);
                        ArrayList<Docente> teachers = DAO.getTupleDocente();
                        out.println("<html><body>");
                        out.println("<p>Docente aggiunto" + session.getId() + "</p>");
                        out.println("</body></html>");
                    }
                }
                break;
            case "Lista":
                ArrayList<Docente> teachers = DAO.getTupleDocente();
                out.println("<html><body>");
                for (Docente t : teachers) {
                    out.println("<p>" + t + "</p>");
                }
                out.println("</body></html>");
                break;
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void destroy() {
    }
}