
package entities;

import java.util.Map;


public class Porudzbina {
    private Korisnik korisnik;
    Map<Proizvod, Integer> map;

    public Porudzbina() {
    }

    public Porudzbina(Korisnik korisnik, Map<Proizvod, Integer> map) {
        this.korisnik = korisnik;
        this.map = map;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Map<Proizvod, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Proizvod, Integer> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Porudzbina{" + "korisnik=" + korisnik + ", map=" + map + '}';
    }
    
    
}
