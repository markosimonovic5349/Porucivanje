package db;

import entities.Korisnik;
import entities.Porudzbina;
import entities.Proizvod;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PorudzbinaCrud {
    // Metoda za dodavanje porudžbine u bazu podataka
    public static void addPorudzbina(Porudzbina p) {
        try {
            DBUtil.openConnection();

            PreparedStatement st = DBUtil.con.prepareStatement("INSERT INTO `porudzbina` (`porudzbina_ID`, `userName_FK`) VALUES (NULL, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
            st.setString(1, p.getKorisnik().getUserName());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);

            }

            for (Map.Entry<Proizvod, Integer> entry : p.getMap().entrySet()) {
                String putanjaSlike = entry.getKey().getPutanjaSlike();
                int kolicina = entry.getValue();

                PreparedStatement st2 = DBUtil.con.prepareStatement("INSERT INTO `korpa` (`porudzbina_FK`, `putanjaSLike_FK`, `kolicina`) VALUES (?,?,?);");
                st2.setInt(1, id);
                st2.setString(2, putanjaSlike);
                st2.setInt(3, kolicina);

                st2.execute();

            }

            DBUtil.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Metoda za vraćanje porudžbina na osnovu korisnika
    public static List<Porudzbina> returnPorudzbineByKorisnik(Korisnik k) {
        List<Porudzbina> porudzbine = new ArrayList<>();
        List<Integer> porudzbineID = new ArrayList<>();

        try {
            DBUtil.openConnection();
            String query = "SELECT porudzbina_ID "
                    + "FROM `porudzbina` "
                    + "WHERE userName_FK  like '" + k.getUserName() + "'";
            Statement st = (Statement) DBUtil.con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                try {
                    int i = rs.getInt("porudzbina_ID");
                    porudzbineID.add(i);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            for (Integer integer : porudzbineID) {
                Map<Proizvod, Integer> putanjeKolicineMapa = new HashMap<>();
                query = "SELECT korpa.putanjaSLike_FK , korpa.kolicina "
                        + "FROM `porudzbina` "
                        + "JOIN korpa on porudzbina.porudzbina_ID = korpa.porudzbina_FK "
                        + "WHERE porudzbina_ID = " + integer.toString();

                rs = st.executeQuery(query);

                while (rs.next()) {
                    try {
                        putanjeKolicineMapa.put(db.ProizvodCrud.getProizvodByPutanja(rs.getString("putanjaSLike_FK")), rs.getInt("kolicina"));
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                porudzbine.add(new Porudzbina(k, putanjeKolicineMapa));

            }

            DBUtil.closeConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return porudzbine;

    }
}
