package pt.ulusofona.aed.deisiRockstar2021;

import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void loadFiles() throws IOException {
            SongsFunctions.lerSongs("songs.txt");
            ArtistsFunctions.lerArtists("song_artists.txt");
            DetailsFunctions.lerDetails("song_details.txt");
    }


    public static void main(String[] args) {
        try {
            loadFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ParseInfoFunctions.getParseInfo("songs.txt"));

    }
}
