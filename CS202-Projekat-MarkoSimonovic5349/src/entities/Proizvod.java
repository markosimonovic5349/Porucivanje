
package entities;


public class Proizvod {
    private String putanjaSlike;
    private String naziv;
    private double cena;

    public Proizvod() {
    }

    public Proizvod(String putanjaSlike, String naziv, double cena) {
        this.putanjaSlike = putanjaSlike;
        this.naziv = naziv;
        this.cena = cena;
    }

    public String getPutanjaSlike() {
        return putanjaSlike;
    }

    public void setPutanjaSlike(String putanjaSlike) {
        this.putanjaSlike = putanjaSlike;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "Proizvod{" + "putanjaSlike=" + putanjaSlike + ", naziv=" + naziv + ", cena=" + cena + '}';
    }
    
    
}
