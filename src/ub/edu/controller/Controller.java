package ub.edu.controller;

import ub.edu.model.Excursio;
import ub.edu.model.CarteraSocis;
import ub.edu.model.Especie;
import ub.edu.model.Soci;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private CarteraSocis carteraSocis;   // Model
    private Map<String, Excursio> excursionsMap;
    private Map<String, Especie> especiesMap;

    public Controller() {
        iniCarteraSocis();
        iniExcursionsMap();
        especiesMap = new HashMap<>();
    }

    public void iniCarteraSocis()  {
        List<Soci> listSocis = new ArrayList<>();
        listSocis.add(new Soci("ajaleo@gmail.com", "ajaleoPassw7"));
        listSocis.add( new Soci("dtomacal@yahoo.cat", "Qwertyft5"));
        listSocis.add(new Soci("heisenberg@gmail.com", "the1whoknocks"));
        listSocis.add(new Soci("rick@gmail.com", "wabalabadapdap22"));
        listSocis.add( new Soci("nietolopez10@gmail.com", "pekFD91m2a"));
        listSocis.add(new Soci("nancyarg10@yahoo.com", "contra10LOadc"));
        listSocis.add( new Soci("CapitaCC@gmail.com", "Alistar10"));
        listSocis.add( new Soci("nauin2@gmail.com", "kaynJGL20"));
        listSocis.add( new Soci("juancarlos999@gmail.com", "staIamsA12"));
        listSocis.add( new Soci("judit121@gmail.com", "Ordinador1"));

        carteraSocis = new CarteraSocis(listSocis);
    }

    public void iniExcursionsMap() {
        excursionsMap = new HashMap<>();
        addExcursio("Museu Miró", "29/09/2021");
        addExcursio( "La Foradada", "04/10/2021");
        addExcursio( "El camí des Correu", "10/10/2021");
        addExcursio("Delta de l'Ebre", "11/10/2021");
        addExcursio("El PedraForca", "13/10/2021");
        addExcursio("Colònia Güell", "22/10/2021");
        addExcursio("Castell de Cardona", "24/10/2021");
        addExcursio("Aiguamolls de l'Empordà", "27/10/2021");
        addExcursio("Cap de Creus i Cadaqués", "01/11/2021");
        addExcursio("Aigüestortes i Sant Maurici", "03/11/2021");
    }

    private void addExcursio(String nom, String dataText){
        excursionsMap.put(nom, new Excursio(nom, dataText));
    }

    private List<Excursio> getExcursionsList(){
        return new ArrayList<>(excursionsMap.values());
    }

    public boolean isPasswordSegur(String password) {
        Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }
    public boolean isMail(String correu) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                          + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(correu);
        return matcher.find();
    }

    public Iterable<String> llistarCatalegExcursionsPerNom() {
        SortedSet<String> excursionsDisponibles = new TreeSet<>();
        if (getExcursionsList().isEmpty()) {
            excursionsDisponibles.add("No hi ha excursions disponibles");
        } else {
            for (Excursio s : getExcursionsList()) {
                excursionsDisponibles.add(s.getNom());
            }
        }
        return excursionsDisponibles;
    }

    public Iterable<String> llistarCatalegExcursionsPerData(){
        List<Excursio> sortedList = getExcursionsList();
        sortedList.sort(new Comparator<Excursio>() {
            public int compare(Excursio a1, Excursio a2) {
                return a1.getData().compareTo(a2.getData());
            }
        });

        List<String> excursionsDisponibles = new ArrayList<>();
        for (Excursio s : sortedList) {
            excursionsDisponibles.add(s.getNom());
        }

        return excursionsDisponibles;
    }

    public String findSoci(String username) {
        Soci Soci = carteraSocis.find(username);
        if (Soci!=null) return "Soci existent";
        else return "Soci Desconegut";
    }


    public String validatePassword(String b) {
        if (!isPasswordSegur(b)) {
            return "Contrassenya no segura";
        } else
            return "Contrassenya segura";
    }


    public String validateUsername(String b) {
        if (!isMail(b))
            return "Correu en format incorrecte";
        else
            return "Correu en format correcte";
    }

    public String validateRegisterSoci(String username, String password) {
        if (isMail(username) && isPasswordSegur(password)) {
            Soci Soci = carteraSocis.find(username);
            if (Soci != null) {
                return "Soci Duplicat";
            } else return "Soci Validat";
        } else return "Format incorrecte";
    }

    public String loguejarSoci(String username, String password){
        Soci soci = carteraSocis.find(username);
        if(soci == null){
            return "Correu inexistent";
        }
        if(soci.getPwd().equals(password)){
            return "Login correcte";
        }else{
            return "Contrassenya incorrecta";
        }
    }

    public String recuperarContrassenya(String username){
        Soci soci = carteraSocis.find(username);
        if(soci == null){
            return "Correu inexistent";
        }
        return soci.getPwd();
    }


    public Especie afegirEspecie(String nomEspecie){
        Especie especie;
        if(especiesMap.containsKey(nomEspecie)){
            especie = especiesMap.get(nomEspecie);
        }else{
            especie = new Especie(nomEspecie);
            especiesMap.put(nomEspecie, especie);
        }
        return especie;
    }

    public void afegirEspecieExcursio(String nomEspecie, String nomExcursio){
        Especie especie = afegirEspecie(nomEspecie);

        excursionsMap.get(nomExcursio).addEspecie(especie);

    }

    public Iterable<String> cercaExcursions(){
        SortedSet<String> especies = new TreeSet<>();

        if (especiesMap.size() == 0){
            especies.add("No hi han espècies enregistrades");
            return especies;
        }

        for (Especie especie : especiesMap.values()){
            especies.add(especie.getNom() + " " + comptarExcursionsEspecie(especie));
        }

        return especies;
    }

    private int comptarExcursionsEspecie(Especie especie){
        int count = 0;
        for (Excursio excursio : excursionsMap.values()){
            if(excursio.containsEspecie(especie)) count++;
        }
        return count;
    }

}
