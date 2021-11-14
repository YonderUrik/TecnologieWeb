package com.danieleroccaforte.esercizio_7_servlet.DAO;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {

    private static final String url = "jdbc:mysql://localhost:3306/studentbooking";
    private static final String user = "root";
    private static final String password = "";

    public static void registerDriver(){
        try{
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            System.out.println("Driver correttamente registrato");
        } catch (SQLException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }

    public static ArrayList<Corso> getTupleCorso() {
        Connection conn1 = null;
        ArrayList<Corso> out = new ArrayList<>();
        try {
            conn1 = (Connection) DriverManager.getConnection(url, user, password);
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM COURSES");
            while (rs.next()) {
                Corso p = new Corso(rs.getInt("id"),rs.getString("name"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static void setTuplaCorso(String nome) {
        Connection conn1 = null;
        try {
            conn1 = (Connection) DriverManager.getConnection(url, user, password);
            Statement st = conn1.createStatement();
            st.executeUpdate("INSERT INTO courses (id, name ) VALUES (0, '" +nome+"' )");
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static void deleteTuplaCorso(String nome) {
        Connection conn1 = null;
        try {
            conn1 = (Connection) DriverManager.getConnection(url, user, password);
            Statement st = conn1.createStatement();
            st.executeUpdate("DELETE FROM COURSES WHERE NAME = '"+nome+"'");
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static ArrayList<Docente> getTupleDocente(){
        Connection conn1 = null;
        ArrayList<Docente> out = new ArrayList<>();
        try {
            conn1 = (Connection) DriverManager.getConnection(url, user, password);
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM TEACHERS");
            while (rs.next()) {
                Docente p = new Docente(rs.getInt("id"),rs.getString("name"), rs.getString("surname"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static void setTuplaDocente(String nome, String cognome){
        Connection conn1 = null;
        try {
            conn1 = (Connection) DriverManager.getConnection(url, user, password);
            Statement st = conn1.createStatement();
            st.executeUpdate("INSERT INTO teachers (id, name, surname) VALUES (0, '" +nome+"', '"+cognome+"' )");
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static void deleteTuplaDocente(String nome, String cognome){
        Connection conn1 = null;
        try {
            conn1 = (Connection) DriverManager.getConnection(url, user, password);
            Statement st = conn1.createStatement();
            st.executeUpdate("DELETE FROM teachers WHERE NAME = '"+nome+"' AND SURNAME = '"+cognome+"'");
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static ArrayList<Insegnamento> getTupleInsegnamento(){
        Connection conn1 = null;
        ArrayList<Insegnamento> out = new ArrayList<>();
        try {
            conn1 = (Connection) DriverManager.getConnection(url, user, password);
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT courses.name, teachers.name, teachers.surname FROM (teachers join teaching on (teachers.id = teaching.teacher)) join courses on (courses.id = teaching.course)");
            while (rs.next()) {
                Insegnamento p = new Insegnamento(rs.getString("courses.name"), rs.getString("teachers.name"), rs.getString("teachers.surname"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }

    public static void setTuplaInsegnamento(int course, int teacher){
        Connection conn1 = null;
        try {
            conn1 = (Connection) DriverManager.getConnection(url, user, password);
            Statement st = conn1.createStatement();
            st.executeUpdate("INSERT INTO teaching (course, teacher ) VALUES ("+course+", " +teacher+" )");
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }

    public static void deleteTuplaInsegnamento(int course, int teacher){
        Connection conn1 = null;
        try {
            conn1 = (Connection) DriverManager.getConnection(url, user, password);
            Statement st = conn1.createStatement();
            st.executeUpdate("DELETE FROM teaching WHERE course = "+course+"and teacher = "+teacher+"");
            st.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
    }
    /*
    public static ArrayList<Insegnamento> getPrenotazioniperCorso(){
    }*/

    public static ArrayList<Utente> getUtente(String email, String u_password){
        Connection conn1 = null;
        ArrayList<Utente> out = new ArrayList<>();
        try {
            conn1 = (Connection) DriverManager.getConnection(url, user, password);
            Statement st = conn1.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM users where email = '" + email + "' and password = '" + u_password + "'");
            while (rs.next()) {
                Utente p = new Utente(rs.getInt("id"), rs.getString("name"), rs.getString("surname") , rs.getString("email"), rs.getString("password"), rs.getString("role"));
                out.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            if (conn1 != null) {
                try {
                    conn1.close();
                } catch (SQLException e2) {
                    System.out.println(e2.getMessage());
                }
            }
        }
        return out;
    }
}
