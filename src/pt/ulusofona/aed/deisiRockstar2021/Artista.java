package pt.ulusofona.aed.deisiRockstar2021;

import java.util.ArrayList;

public class Artista {
    String id;
    String nome;
    int nrTemas;
    ArrayList<String> tag= new ArrayList<String>();

    Artista() {
    }

    Artista(String id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
