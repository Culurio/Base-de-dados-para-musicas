package pt.ulusofona.aed.deisiRockstar2021;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Artista {
    String id;
    String nome;
    LinkedHashMap<String, Integer> nrTemas = new LinkedHashMap<String, Integer>();
    ArrayList<String> tag= new ArrayList<String>();

    Artista() {
    }

    Artista(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
