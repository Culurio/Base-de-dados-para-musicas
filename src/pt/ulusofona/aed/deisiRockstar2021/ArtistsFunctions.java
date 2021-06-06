package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.TreeSet;

public class ArtistsFunctions {
    static HashMap<String, Artista> artistas = new HashMap<>();
    static ParseInfo infoArtist = new ParseInfo(0, 0);
    static TreeSet<String> idCheck = new TreeSet<>();

    public static void lerArtists(String filename) throws IOException {
        FileReader songArtists = new FileReader(filename);
        BufferedReader reader = new BufferedReader(songArtists);
        infoArtist.ok = 0;
        infoArtist.ignored = 0;
        artistas.clear();
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split("@");
            if (validLine(dados)) {
                String ID = dados[0].trim();
                String Nome = filterName(dados[1].trim());
                Artista artista = new Artista(ID, Nome); // criar o obj Utilizador
                infoArtist.ok++;
                idCheck.add(ID);
                artistas.put(ID, artista); //guardar o objecto
            } else {
                infoArtist.ignored++;
            }
        }
        reader.close();
    }

    public static String filterName(String artist) {
        return artist.substring(2, artist.length() - 2);
    }

    public static boolean validLine(String[] dados) {
        if ((dados.length == 2) && SongsFunctions.idCheck.contains(dados[0].trim()) && !idCheck.contains(dados[0].trim())) {
            String Nome = filterName(dados[1].trim());
            String[] Nomes = Nome.split(",");
            for (String nome : Nomes) {
                if (nome.length() < 2) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
