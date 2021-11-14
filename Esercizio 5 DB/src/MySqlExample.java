import dao.Corso;
import dao.DAO;
import dao.Docente;

import java.util.ArrayList;

public class MySqlExample {

    /* Questo esempio dimostra come connettersi a un DB MySQL
       e recuperare dati da tabella. Per un completo, che
       mostra vari tipi di connessione, vedere:
       http://www.codejava.net/java-se/jdbc/connect-to-mysql-database-via-jdbc#CodeExample
     */
        public static void main(String[] args) {
            DAO.registerDriver();


            DAO.setTuplaCorso("Iterazione Uomo Macchina");
            //DAO.setTuplaCorso("Tecnologie Web");

            DAO.deleteTuplaCorso("Iterazione Uomo Macchina");

            //PRINT
            ArrayList<Corso> corsi = DAO.getTupleCorso();
            for (Corso c: corsi)
                System.out.println(c);
            System.out.println("FINE STAMPA CORSI");

            //DAO.setTuplaDocente("Liliana", "Ardissono");
            DAO.setTuplaDocente("Daniele", "Roccaforte");

            DAO.deleteTuplaDocente("Daniele", "Roccaforte");

            ArrayList<Docente> docenti = DAO.getTupleDocente();
            for (Docente d: docenti)
                System.out.println(d);
            System.out.println("FINE STAMPA DOCENTI");
        }
}
