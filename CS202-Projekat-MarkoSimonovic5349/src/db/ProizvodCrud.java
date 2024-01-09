/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import entities.Korisnik;
import entities.Proizvod;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marko Simonovic
 */
public class ProizvodCrud {

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
