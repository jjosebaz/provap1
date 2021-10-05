package ub.edu.model;


public class Soci {
    private String pwd;
    private String nom;


    public Soci(String nom, String pwd) {
        this.pwd = pwd;
        this.nom = nom;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }


}
