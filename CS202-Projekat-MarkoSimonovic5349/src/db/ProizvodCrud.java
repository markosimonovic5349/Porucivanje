package db;

import entities.Proizvod;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProizvodCrud {
    // Metoda za dobijanje svih proizvoda iz baze podataka
    public static List<Proizvod> getProizvodi() {
        ArrayList<Proizvod> proizvodi = new ArrayList<Proizvod>();
        try {
            DBUtil.openConnection();
            String query = "SELECT * FROM proizvod";
            Statement st = (Statement) DBUtil.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String putanjaSlike = rs.getString("putanjaSlike");
                String naziv = rs.getString("naziv");
                double cena = rs.getDouble("cena");
                proizvodi.add(new Proizvod(putanjaSlike, naziv, cena));
            }
            st.close();
            DBUtil.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return proizvodi;
    }

    // Metoda za dobijanje proizvoda na osnovu putanje slike
    public static Proizvod getProizvodByPutanja(String x) {
        Proizvod p = null;
        try {
            DBUtil.openConnection();
            String query = "SELECT * FROM proizvod WHERE putanjaSlike LIKE '" + x + "'";
            Statement st = (Statement) DBUtil.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                String putanjaSlike = rs.getString("putanjaSlike");
                String naziv = rs.getString("naziv");
                int cena = rs.getInt("cena");
                p = new Proizvod(putanjaSlike, naziv, cena);
            }
            st.close();

            DBUtil.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
}
