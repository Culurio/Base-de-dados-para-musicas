package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.TreeSet;

public class SongsFunctions {
    static LinkedHashMap<String, Song> songs = new LinkedHashMap<>();
    static ParseInfo infoSong = new ParseInfo(0, 0);

    public static void lerSongs(String filename) throws IOException {
        songs.clear();
        infoSong.ok = 0;
        infoSong.ignored = 0;
        FileReader songsFile = new FileReader(filename);
        BufferedReader reader = new BufferedReader(songsFile);
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split("@");
            if (dados.length == 3 && !songs.containsKey(dados[0].trim())) {
                String ID = dados[0].trim();
                String Nome = dados[1].trim();
                String ano = dados[2].trim();
                int anoLancamento = Integer.parseInt(ano);
                Song song = new Song(ID, Nome, anoLancamento); // criar o obj Utilizador
                songs.put(ID, song); //guardar o objecto
                infoSong.ok++;
            } else {
                infoSong.ignored++;
            }
        }
        reader.close();
    }

    public static LinkedHashMap<String, Song> getSongs() {
        return SongsFunctions.songs;
    }

    public static void joinInfo() {
        for (String i : songs.keySet()) {
            if (ArtistsFunctions.artistas.containsKey(i) && DetailsFunctions.details.containsKey(i)) {
                songs.get(i).detalhes = DetailsFunctions.details.get(i);
                songs.get(i).artista = ArtistsFunctions.artistas.get(i);
            }
        }
    }

    public static LinkedHashMap<String, Song> sortDetails(LinkedHashMap<String, Song> hm) {
        LinkedHashMap<String, Song> detailsSort;

        detailsSort = SortHashMap.sortByValue(hm);
        return detailsSort;
    }
}
