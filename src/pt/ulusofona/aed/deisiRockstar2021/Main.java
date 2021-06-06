package pt.ulusofona.aed.deisiRockstar2021;

import java.io.IOException;


public class Main {

    public static void loadFiles() throws IOException {
        SongsFunctions.lerSongs("songs.txt");
        ArtistsFunctions.lerArtists("song_artists.txt");
        DetailsFunctions.lerDetails("song_details.txt");
        SongsFunctions.joinInfo();
        SongsFunctions.songs = SongsFunctions.sortDetails(SongsFunctions.songs);
    }


    public static void main(String[] args) {
        final long start = System.nanoTime();
        try {
            loadFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Input.menu();
        final long end = System.nanoTime();

        System.out.println("Took: " + ((end - start) / 1000000) + "ms");

    }
}
