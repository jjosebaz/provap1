package ub.edu.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Excursio {
    private String nom;
    private LocalDate data;
    private Set<Especie> llistaEspecies;

    public Excursio(String titol, String dataText) {
        this.nom = titol;
        setData(dataText);
        llistaEspecies = new HashSet<>();
    }

    public Excursio(String titol, String dataText, List<Especie> especies) {
        this.nom = titol;
        setData(dataText);
        llistaEspecies = new HashSet<>(especies);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getData(){
        return this.data;
    }

    public void setData(String dataText){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.data = LocalDate.parse(dataText, formatter);
    }

    public void addEspecie(Especie especie){
        llistaEspecies.add(especie);
    }

    public boolean containsEspecie(Especie especie){
        return llistaEspecies.contains(especie);
    }

}