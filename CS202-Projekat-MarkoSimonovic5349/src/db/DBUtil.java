package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtil {

    public static Connection con = null;
    private static String url = "jdbc:mysql://localhost/";
    private static String dbName = "cs202-projekat-markosimonovic5349";
    private static String username = "root";
    private static String password = "";

     /**
     * Method for connecting to the base, checking if everything every table is
     * created and if it isn't then it creates it
     */
    public static void initDB() {
        try {
            con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement();
            stmt.execute("CREATE DATABASE IF NOT EXISTS " + dbName);
            stmt.execute("USE " + dbName);
            stmt.execute("CREATE TABLE IF NOT EXISTS korisnik("
                    + "userName VARCHAR(30) PRIMARY KEY NOT NULL,"
                    + "password VARCHAR(30) NOT NULL,"
                    + "brojTelefona VARCHAR(30) NOT NULL,"
                    + "adresa VARCHAR(30) NOT NULL"
                    + ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS proizvod("
                    + "putanjaSlike VARCHAR(30) PRIMARY KEY NOT NULL,"
                    + "naziv VARCHAR(30) NOT NULL,"
                    + "cena double DEFAULT NULL"
                    + ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS porudzbina("
                    + "porudzbina_ID INT AUTO_INCREMENT PRIMARY KEY NOT NULL,"
                    + "userName_FK VARCHAR(30) NOT NULL"
                    + "FOREIGN KEY (userName_FK) REFERENCES korisnik (userName) ON UPDATE CASCADE ON DELETE CASCADE"
                    + ")");
            stmt.execute("CREATE TABLE IF NOT EXISTS porudzbina("
                    + "porudzbina_FK int(11) NOT NULL,"
                    + "putanjaSLike_FK varchar(30) NOT NUL,"
                    + "kolicina int(11) NOT NULL,"
                    + "FOREIGN KEY (`porudzbina_FK`) REFERENCES `porudzbina` (`porudzbina_ID`) ON UPDATE CASCADE ON DELETE CASCADE,"
                    + "FOREIGN KEY (`putanjaSLike_FK`) REFERENCES `proizvod` (`putanjaSlike`) ON UPDATE CASCADE ON DELETE CASCADE"
                    + ")");

        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method for opening connection
     *
     * @throws SQLException
     */
    public static void openConnection() throws SQLException {
        con = DriverManager.getConnection(url + dbName, username, password);
    }

    /**
     * Method for closing connection
     *
     * @throws SQLException
     */
    public static void closeConnection() throws SQLException {
        con.close();
    }
}

