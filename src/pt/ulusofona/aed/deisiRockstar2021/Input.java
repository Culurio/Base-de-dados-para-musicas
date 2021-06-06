package pt.ulusofona.aed.deisiRockstar2021;

import java.util.Scanner;

public class Input {
    public static void menu(){
        System.out.println("Welcome to DEISI Rockstar!");
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        while (line != null && !line.equals("KTHXBYE")) {
            String result = execute(line);
            System.out.println(result);
            line = in.nextLine();
        }
    }
    public static String execute(String command){
        String[] commands=command.split(" ");
        switch (commands[0]){
            case "COUNT_SONGS_YEAR":
                return commandsFunctions.CountSongYears(commands[1]);

            default:
                return "Illegal command. Try again";
        }
    }
}
