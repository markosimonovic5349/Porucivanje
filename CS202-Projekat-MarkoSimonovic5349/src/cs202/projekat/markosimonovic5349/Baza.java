/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cs202.projekat.markosimonovic5349;

import entities.Proizvod;
import entities.Korisnik;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Marko Simonovic
 */
public class Baza {

    private static java.sql.Connection con = null;
    private static String url = "jdbc:mysql://localhost/cs202-projekat-markosimonovic5349";
    private static String username = "root";
    private static String password = "";

    public static void addKorisnik(Korisnik user) {
        try {
            con = DriverManager.getConnection(url, username, password);
            PreparedStatement st = con.prepareStatement("INSERT INTO korisnik(userName,password,brojTelefona,adresa) " + "VALUES( ?,  ?,  ?, ?)");
            st.setString(1, user.getUserName());
            st.setString(2, user.getPassword());
            st.setString(3, user.getBrojTelefona());
            st.setString(4, user.getAdresa());
            st.execute();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Korisnik getKorinsiks(String x) {
        Korisnik k = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM korisnik WHERE userName LIKE '" + x + "'";
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                String u = rs.getString("userName");
                String p = rs.getString("password");
                String t = rs.getString("brojTelefona");
                String a = rs.getString("adresa");
                k = new Korisnik(u, p, t, a);
            } else {
                return k;
            }
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return k;
    }

    public static List<Proizvod> getProizvodi() {
        ArrayList<Proizvod> proizvodi = new ArrayList<Proizvod>();
        try {
            con = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM proizvod";
            Statement st = (Statement) con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String putanjaSlike = rs.getString("putanjaSlike");
                String naziv = rs.getString("naziv");
                double cena = rs.getDouble("cena");
                proizvodi.add(new Proizvod(putanjaSlike, naziv, cena));
            }
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return proizvodi;
    }

    public static void addPorudzbina(Map<String, Integer> map, String user) {
        try {
            con = DriverManager.getConnection(url, username, password);

            PreparedStatement st = con.prepareStatement("INSERT INTO `porudzbina` (`porudzbina_ID`, `userName_FK`) VALUES (NULL, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, user);
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);

            }

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String putanjaSlike = entry.getKey();
                int kolicina = entry.getValue();

                PreparedStatement st2 = con.prepareStatement("INSERT INTO `korpa` (`porudzbina_FK`, `putanjaSLike_FK`, `kolicina`) VALUES (?,?,?);");
                st2.setInt(1, id);
                st2.setString(2, putanjaSlike);
                st2.setInt(3, kolicina);

                st2.execute();

            }

            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
