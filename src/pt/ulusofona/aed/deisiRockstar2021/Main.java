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
        System.out.println(CommandsFunctions.GetArtistsOneSong("2012","2013"));
        System.out.println("Welcome to DEISI Rockstar!");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (line != null && !line.equals("KTHXBYE")) {
            long start = System.currentTimeMillis();
            String result = execute(line);
            System.out.println(result);
            long end = System.currentTimeMillis();
            System.out.println("Took: " + (end - start) + "ms");
            line = in.nextLine();
        }

    }
    public static String execute(String command) {
        String[] commands;
        String[] FirstCommand=command.split(" ");
        command=command.replace(FirstCommand[0],"");
        if(FirstCommand.length!=1){
            command=command.replace(" "+FirstCommand[1],FirstCommand[1]);
        }
        switch (FirstCommand[0]) {
            case "COUNT_SONGS_YEAR":commands=command.split(" ");return CommandsFunctions.countSongYears(commands[0]);
            case "GET_ARTISTS_FOR_TAG":commands=command.split(" "); return CommandsFunctions.getArtistsForTag(commands[0]);
            case "GET_MOST_DANCEABLE":commands=command.split(" "); return CommandsFunctions.getMostDanceable(commands[0], commands[1], commands[2]);
            case "ADD_TAGS":commands=command.split(";"); return CommandsFunctions.addTag(commands[0],commands[1]);
            case "REMOVE_TAGS":commands=command.split(";"); return CommandsFunctions.removeTags(commands[0],commands[1]);
            case "COUNT_DUPLICATE_SONGS_YEAR":commands=command.split(" "); return CommandsFunctions.countDuplicateSongYears(commands[0]);
            case "GET_UNIQUE_TAGS":return CommandsFunctions.GetUniqueTags();
            case "GET_UNIQUE_TAGS_IN_BETWEEN_YEARS":commands=command.split(" ");return CommandsFunctions.getUniqueTagsInBetweenYears(commands[0],commands[1]);
            case "CLEANUP":return  CleanupFunctions.cleanup();
            default: return "Illegal command. Try again";
        }
    }
}
