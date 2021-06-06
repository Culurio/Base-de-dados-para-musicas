package pt.ulusofona.aed.deisiRockstar2021;

import java.util.ArrayList;

public class commandsFunctions {
    public static String CountSongYears(String years) {
        int yearsInt = Integer.parseInt(years);
        int count = 0;
        for (String i : SongsFunctions.songs.keySet()) {
            if (SongsFunctions.songs.get(i).anoLancamento == yearsInt) {
                count++;
            }
        }
        return count + "";
    }

    public static String GetArtistsForTag(String tag) {
        StringBuilder result = new StringBuilder();
        for (String i : SongsFunctions.songs.keySet()) {
            if (SongsFunctions.songs.get(i).tag.equals(tag)) {
                result.append(SongsFunctions.songs.get(i).artista.nome).append("\n");
            }
        }
        return result.toString();
    }

    //<Nome tema> : <Ano> : <Dançabilidade>\n”
    public static String GetMostDanceable(String year1, String year2, String results) {

        int year1Int = Integer.parseInt(year1), year2Int = Integer.parseInt(year2), resultsInt = Integer.parseInt(results);
        StringBuilder result = new StringBuilder();
        ArrayList<Song> mostDanceable = new ArrayList<>();
        for (String i : SongsFunctions.songs.keySet()) {
            mostDanceable.add(SongsFunctions.songs.get(i));
        }
        int danceableOrder = mostDanceable.size() - 1;

        for (int i = danceableOrder; i >= 0; i--) {
            if (mostDanceable.get(i).anoLancamento >= year1Int && mostDanceable.get(i).anoLancamento <= year2Int) {
                result.append(mostDanceable.get(i).nome).append(" ").append(mostDanceable.get(i).anoLancamento).append(" ").append(mostDanceable.get(i).detalhes.dancabilidade).append("\n");
            }
        }
        return result.toString();

    }

}
