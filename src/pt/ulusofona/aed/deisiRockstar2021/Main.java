package pt.ulusofona.aed.deisiRockstar2021;

import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void loadFiles() throws IOException {
        SongsFunctions.lerSongs("songs.txt");
        ArtistsFunctions.lerArtists("song_artists.txt");
        DetailsFunctions.lerDetails("song_details.txt");
        SongsFunctions.joinInfo();
        SongsFunctions.songs = SongsFunctions.sortDetails(SongsFunctions.songs);
    }


    public static void main(String[] args) {
        try {
            loadFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Welcome to DEISI Rockstar!");

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (line != null && !line.equals("KTHXBYE")) {
            String result = execute(line);
            long start = System.currentTimeMillis();
            System.out.println(result);
            long end = System.currentTimeMillis();
            System.out.println("Took: " + ((end - start) / 1000000) + "ms");
            line = in.nextLine();
        }

    }
    public static String execute(String command) {
        String[] commands = command.split(" ");
        switch (commands[0]) {
            case "COUNT_SONGS_YEAR": return CommandsFunctions.countSongYears(commands[1]);
            case "GET_ARTISTS_FOR_TAG": return CommandsFunctions.getArtistsForTag(commands[1]);
            case "GET_MOST_DANCEABLE": return CommandsFunctions.getMostDanceable(commands[1], commands[2], commands[3]);
            case "ADD_TAGS": return CommandsFunctions.addTag(commands[1],commands[2]);
            case "REMOVE_TAGS": return CommandsFunctions.removeTags(commands[1],commands[2]);
            case "CLEANUP": return  CleanupFunctions.cleanup();
            default: return "Illegal command. Try again";
        }
    }
}
