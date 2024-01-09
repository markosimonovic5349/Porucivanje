/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Marko Simonovic
 */
public class Korisnik {
    private String userName;
    private String password;
    private String brojTelefona;
    private String adresa;

    public Korisnik() {
    }

    public Korisnik(String userName, String password, String brojTelefona, String adresa) {
        this.userName = userName;
        this.password = password;
        this.brojTelefona = brojTelefona;
        this.adresa = adresa;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Korisnik{" + "userName=" + userName + ", password=" + password + ", brojTelefona=" + brojTelefona + ", adresa=" + adresa + '}';
    }

}
