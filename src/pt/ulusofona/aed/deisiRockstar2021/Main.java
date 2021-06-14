package pt.ulusofona.aed.deisiRockstar2021;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static String getCreativeQuery() {
        return "";
    }

    public static int getTypeOfSecondParameter() {
        return 4;
    }

    public static String getVideoUrl() {
        return "";
    }

    public static void loadFiles() throws IOException {
        SongsFunctions.lerSongs("songs.txt");
        ArtistsFunctions.lerArtists("song_artists.txt");
        DetailsFunctions.lerDetails("song_details.txt");
        SongsFunctions.joinInfo();
        ArtistsFunctions.assignThemes(SongsFunctions.songs);
    }

    public static ArrayList<Song> getSongs() {
        return SongsFunctions.getSongs;
    }

    public static ParseInfo getParseInfo(String fileName) {
        switch (fileName) {
            case "songs.txt":
                return SongsFunctions.infoSong;
            case "song_artists.txt":
                return ArtistsFunctions.infoArtist;
            case "song_details.txt":
                return DetailsFunctions.infoDetails;
            default:
                return new ParseInfo(0, 0);
        }
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
            long start = System.currentTimeMillis();
            String result = execute(line);
            System.out.println(result);
            long end = System.currentTimeMillis();
            System.out.println("Took: " + (end - start) + "ms");
            line = in.nextLine();
        }

    }

    public static String execute(String command) {
        String[] commands=null;
        String[] FirstCommand = command.split(" ");
        command = command.replace(FirstCommand[0], "");
        if (FirstCommand.length != 1) {
            command = command.replace(" " + FirstCommand[1], FirstCommand[1]);
        }
        return execute2(command,commands,FirstCommand[0]);
    }
    public static String execute2(String command,String[] commands,String FirstCommand){
        switch (FirstCommand) {
            case "COUNT_SONGS_YEAR":
                commands = command.split(" ");
                return CommandsFunctions.countSongYears(commands[0]);
            case "GET_ARTISTS_FOR_TAG":
                commands = command.split(" ");
                return CommandsFunctions.getArtistsForTag(commands[0].toUpperCase());
            case "GET_MOST_DANCEABLE":
                commands = command.split(" ");
                return CommandsFunctions.getMostDanceable(commands[0], commands[1], commands[2]);
            case "ADD_TAGS":
                return CommandsFunctions.addTag(command);
            case "REMOVE_TAGS":
                commands = command.split(";");
                return CommandsFunctions.removeTags(command);
            case "COUNT_DUPLICATE_SONGS_YEAR":
                commands = command.split(" ");
                return CommandsFunctions.countDuplicateSongYears(commands[0]);
            case "GET_UNIQUE_TAGS":
                return CommandsFunctions.getUniqueTags();
            case "GET_UNIQUE_TAGS_IN_BETWEEN_YEARS":
                commands = command.split(" ");
                return CommandsFunctions.getUniqueTagsInBetweenYears(commands[0], commands[1]);
            case  "GET_ARTISTS_ONE_SONG":
                commands = command.split(" ");
                return CommandsFunctions.getArtistsOneSong(commands[0], commands[1]);
            case "GET_TOP_ARTISTS_WITH_SONGS_BETWEEN":commands=command.split(" ");return CommandsFunctions.getTopArtistWithSongsBetween(commands[0],commands[1],commands[2]);
            case "CLEANUP":
                return CleanupFunctions.cleanup();
            default:
                return "Illegal command. Try again";
        }
    }
}