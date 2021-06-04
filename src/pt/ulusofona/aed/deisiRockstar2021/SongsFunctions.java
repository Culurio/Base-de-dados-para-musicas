package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SongsFunctions {
    static ArrayList<Song> songs = new ArrayList<>();
    static ParseInfo infoSong = new ParseInfo(0, 0);

    public static void lerSongs(String filename) throws IOException {
        songs.clear();
        infoSong.ok=0;
        infoSong.ignored=0;
        FileReader songsFile = new FileReader(filename);
        BufferedReader reader = new BufferedReader(songsFile);
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split("@");
            if (dados.length == 3) {
                String ID = dados[0].trim();
                String Nome = dados[1].trim();
                String ano = dados[2].trim();
                int anoLancamento = Integer.parseInt(ano);
                Song song = new Song(ID, Nome, anoLancamento); // criar o obj Utilizador
                songs.add(song); //guardar o objecto
                infoSong.ok++;
            } else {
                infoSong.ignored++;
            }
        }
        reader.close();
    }

    public static ArrayList<Song> getSongs() {
        return SongsFunctions.songs;
    }
}
