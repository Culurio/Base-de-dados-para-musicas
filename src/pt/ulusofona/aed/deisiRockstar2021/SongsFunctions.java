package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SongsFunctions {
    static LinkedHashMap<String, Song> songs = new LinkedHashMap<>();
    static ArrayList<Song> getSongs = new ArrayList<>();
    static ParseInfo infoSong = new ParseInfo(0, 0);

    public static void lerSongs(String filename) throws IOException {
        songs.clear();
        infoSong.ok = 0;
        infoSong.ignored = 0;
        getSongs.clear();
        FileReader songsFile = new FileReader(filename);
        BufferedReader reader = new BufferedReader(songsFile);
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split("@");
            if (dados[0].length()!=1 && dados.length == 3 && !songs.containsKey(dados[0].trim())) {
                String ID = dados[0].trim();
                String Nome = dados[1].trim();
                String ano = dados[2].trim();
                int anoLancamento = Integer.parseInt(ano);
                Song song = new Song(ID, Nome, anoLancamento); // criar o obj Utilizador
                songs.put(ID, song); //guardar o objecto
                getSongs.add(song);
                infoSong.ok++;
            } else {
                infoSong.ignored++;
            }
        }
        reader.close();
    }



    public static void joinInfo() {
        for (String i : songs.keySet()) {
            if (ArtistsFunctions.artistas.containsKey(i) && DetailsFunctions.details.containsKey(i)) {
                songs.get(i).detalhes = DetailsFunctions.details.get(i);
                songs.get(i).artista = ArtistsFunctions.artistas.get(i);
            }
        }
    }
    public static LinkedHashMap<String, Song> onlyValidSongs(){
         LinkedHashMap<String, Song> newSongs = songs;
         ArrayList<String> invalidSongs = new ArrayList<>();
        for (String i : newSongs.keySet()) {
            if ((newSongs.get(i).detalhes == null) && (newSongs.get(i).artista == null) ) {
                invalidSongs.add(i);
            }
        }
        for(String i : invalidSongs){
            newSongs.remove(i);
        }
        return newSongs;
    }

    public static LinkedHashMap<String, Song> sortDetails(LinkedHashMap<String, Song> hm) {
        LinkedHashMap<String, Song> detailsSort;

        detailsSort = SortHashMap.sortByValue(hm);
        return detailsSort;
    }
}
