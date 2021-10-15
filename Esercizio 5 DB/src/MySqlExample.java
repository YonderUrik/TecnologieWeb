import dao.Corso;
import dao.DAO;

import java.util.ArrayList;

public class MySqlExample {

    /* Questo esempio dimostra come connettersi a un DB MySQL
       e recuperare dati da tabella. Per un completo, che
       mostra vari tipi di connessione, vedere:
       http://www.codejava.net/java-se/jdbc/connect-to-mysql-database-via-jdbc#CodeExample
     */
        public static void main(String[] args) {
            DAO.registerDriver();


            //DAO.setTuplaCorso("Daniele");
            //DAO.setTuplaCorso("Marco");

            //DAO.deleteTuplaCorso(7);

            //PRINT
            ArrayList<Corso> corsi = DAO.getTupleCorso();
            for (Corso c: corsi)
                System.out.println(c);
            System.out.println("FINE");
        }
}
