package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;
import java.util.HashMap;

public class SongsFunctions {
    static HashMap<String, Song> songs = new HashMap<>();
    static ParseInfo infoSong = new ParseInfo(0, 0);
    static TreeSet<String> idCheck = new TreeSet<>();

    public static void lerSongs(String filename) throws IOException {
        songs.clear();
        infoSong.ok=0;
        infoSong.ignored=0;
        FileReader songsFile = new FileReader(filename);
        BufferedReader reader = new BufferedReader(songsFile);
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split("@");
            if (dados.length == 3 && !idCheck.contains(dados[0].trim())) {
                String ID = dados[0].trim();
                String Nome = dados[1].trim();
                String ano = dados[2].trim();
                int anoLancamento = Integer.parseInt(ano);
                Song song = new Song(ID, Nome, anoLancamento); // criar o obj Utilizador
                songs.put(ID,song); //guardar o objecto
                idCheck.add(ID);
                infoSong.ok++;
            } else {
                infoSong.ignored++;
            }
        }
        reader.close();
    }

    public static HashMap<String, Song> getSongs() {
        return SongsFunctions.songs;
    }

    public static void joinInfo(){
        for(String i : songs.keySet()){
            if(ArtistsFunctions.artistas.containsKey(i) && DetailsFunctions.details.containsKey(i) ){
                songs.get(i).detalhes=DetailsFunctions.details.get(i);
                songs.get(i).artista=ArtistsFunctions.artistas.get(i);
            }
        }
    }
}
