package dao;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO {

    private static final String url = "jdbc:mysql://localhost:3306/test";
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
            ResultSet rs = st.executeQuery("SELECT * FROM CORSO");
            while (rs.next()) {
                Corso p = new Corso(rs.getInt("id"),rs.getString("nome"));
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
            st.executeUpdate("INSERT INTO corso (id, nome) VALUES (0, '" +nome+"' )");
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

    public static void deleteTuplaCorso(int id) {
        Connection conn1 = null;
        try {
            conn1 = (Connection) DriverManager.getConnection(url, user, password);
            Statement st = conn1.createStatement();
            st.executeUpdate("DELETE FROM CORSO WHERE ID = "+id+"");
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

    }

    public static void setTuplaDocente(String nome, String cognome){

    }

    public void deleteTuplaDocente(int id){

    }
}
