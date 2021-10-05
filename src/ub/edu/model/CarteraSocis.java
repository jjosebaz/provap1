package ub.edu.model;

import java.util.List;

public class CarteraSocis {
    private List<Soci> llista;

    public CarteraSocis(List<Soci> allSocis) {
        llista = allSocis;
    }

    public Soci find(String username) {

        for (Soci c: llista) {
            if (c.getName().equals(username)) return c;
        }
        return null;

    }

}
