/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import entities.Korisnik;
import java.util.Map;

/**
 *
 * @author Marko Simonovic
 */
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
