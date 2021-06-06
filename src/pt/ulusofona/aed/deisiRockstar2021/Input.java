package pt.ulusofona.aed.deisiRockstar2021;

import java.util.Scanner;

public class Input {
    public static void menu() {
        System.out.println("Welcome to DEISI Rockstar!");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (line != null && !line.equals("KTHXBYE")) {
            String result = execute(line);
            System.out.println(result);
            line = in.nextLine();
        }
    }

    public static String execute(String command) {
        String[] commands = command.split(" ");
        return switch (commands[0]) {
            case "COUNT_SONGS_YEAR" -> commandsFunctions.CountSongYears(commands[1]);
            case "GET_ARTISTS_FOR_TAG" -> commandsFunctions.GetArtistsForTag(commands[0]);
            case "GET_MOST_DANCEABLE" -> commandsFunctions.GetMostDanceable(commands[1], commands[2], commands[3]);
            default -> "Illegal command. Try again";
        };
    }
}
