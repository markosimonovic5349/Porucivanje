/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import entities.Korisnik;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Marko Simonovic
 */
public class KorisnikCrud {

    public static void addKorisnik(Korisnik user) {
        try {
            DBUtil.openConnection();
            PreparedStatement st = DBUtil.con.prepareStatement("INSERT INTO korisnik(userName,password,brojTelefona,adresa) " + "VALUES( ?,  ?,  ?, ?)");
            st.setString(1, user.getUserName());
            st.setString(2, user.getPassword());
            st.setString(3, user.getBrojTelefona());
            st.setString(4, user.getAdresa());
            st.execute();
            DBUtil.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static Korisnik getKorinsik(String x) {
        Korisnik k = null;
        try {
            DBUtil.openConnection();
            String query = "SELECT * FROM korisnik WHERE userName LIKE '" + x + "'";
            Statement st = (Statement) DBUtil.con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                String u = rs.getString("userName");
                String p = rs.getString("password");
                String t = rs.getString("brojTelefona");
                String a = rs.getString("adresa");
                k = new Korisnik(u, p, t, a);
            }
            st.close();
            
            DBUtil.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return k;
    }
}
