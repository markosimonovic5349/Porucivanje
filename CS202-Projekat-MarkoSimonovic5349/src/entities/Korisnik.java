
package entities;

import exceptions.NotValidEcveption;


public class Korisnik {

    private String userName;
    private String password;
    private String brojTelefona;
    private String adresa;
    
    public Korisnik() {
    }
    
    public Korisnik(String userName, String password, String brojTelefona, String adresa) throws NotValidEcveption {
        this.userName = userName;
        this.password = password;
        this.setBrojTelefona(brojTelefona);
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
    
    public void setBrojTelefona(String brojTelefona) throws NotValidEcveption{
        if (brojTelefona.matches("\\d+")) {
            this.brojTelefona = brojTelefona;
        } else {
            throw new NotValidEcveption("Nevalidan broj");
        }
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
