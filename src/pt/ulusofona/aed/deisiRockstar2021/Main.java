package pt.ulusofona.aed.deisiRockstar2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Main {

    public static void loadFiles() throws IOException {
            SongsFunctions.lerSongs("songs.txt");
            ArtistsFunctions.lerArtists("song_artists.txt");
            DetailsFunctions.lerDetails("song_details.txt");
            SongsFunctions.joinInfo();
    }


    public static void main(String[] args) {
        final long start = System.nanoTime();
        try {
            loadFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(SongDetails i : DetailsFunctions.details.values()){
            System.out.println(i.dancabilidade);
        }
        System.out.println("__________________________");
        HashMap<String, SongDetails> detailsSort=DetailsFunctions.sortDetails(DetailsFunctions.details);
        ArrayList<Double> dance = new ArrayList<Double>();
        for(SongDetails i : detailsSort.values()){
            dance.add(i.dancabilidade);
        }
        
        //Input.menu();
        final long end = System.nanoTime();

        System.out.println("Took: " + ((end - start) / 1000000) + "ms");

    }
    /*System.out.println(SongsFunctions.songs.get("7dD5DEzjhofoItSG7QwVoY"));
        System.out.println(SongsFunctions.songs.get("7dD5DEzjhofoItSG7QwVoY").artista.nome);
        System.out.println(commandsFunctions.CountSongYears("2012"));*/
}
