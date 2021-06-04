package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ArtistsFunctions {
    static ArrayList<Artista> artistas = new ArrayList<>();
    static ParseInfo infoArtist = new ParseInfo(0, 0);


    public static void lerArtists(String filename) throws IOException {
        FileReader songArtists = new FileReader(filename);
        BufferedReader reader = new BufferedReader(songArtists);
        infoArtist.ok=0;
        infoArtist.ignored=0;
        artistas.clear();
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split("@");
            if ((dados.length == 2)) {
                String ID = dados[0].trim();
                String Nome = dados[1].trim();
                Artista artista = new Artista(ID, Nome); // criar o obj Utilizador
                artistas.add(artista); //guardar o objecto
                infoArtist.ok++;
            } else {
                infoArtist.ignored++;
            }
        }
        reader.close();
    }
}
