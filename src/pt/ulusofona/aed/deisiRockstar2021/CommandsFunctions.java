package pt.ulusofona.aed.deisiRockstar2021;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandsFunctions {
    public static String countSongYears(String years) {
        int yearsInt = Integer.parseInt(years);
        int count = 0;
        for (String i : SongsFunctions.songs.keySet()) {
            if (SongsFunctions.songs.get(i).anoLancamento == yearsInt) {
                count++;
            }
        }
        return count + "";
    }

    public static String getArtistsForTag(String tag) {
        StringBuilder result = new StringBuilder();
        for (String i : SongsFunctions.songs.keySet()) {
            for (String j : SongsFunctions.songs.get(i).artista.tag) {
                if (j.equals(tag)) {
                    result.append(SongsFunctions.songs.get(i).artista.nome);
                }
            }
        }
        return result.toString();
    }

    //<Nome tema> : <Ano> : <Dançabilidade>\n”
    public static String getMostDanceable(String year1, String year2, String results) {

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

    public static String addTag(String nomeDoArtista, String tag) {
        String erro = "Inexistent artist";

        for (String i : SongsFunctions.songs.keySet()) {
            if(SongsFunctions.songs.get(i).artista.nome.equals(nomeDoArtista)){
                for (String j : SongsFunctions.songs.get(i).artista.tag) {
                    if (SongsFunctions.songs.get(i).artista.tag.size()!=0 && !j.equals(tag)) {
                        SongsFunctions.songs.get(i).artista.tag.add(tag);
                        return SongsFunctions.songs.get(i).artista.nome + " | " + SongsFunctions.songs.get(i).artista.tag.toString();
                    }
                }
                if(SongsFunctions.songs.get(i).artista.tag.size()==0){
                    SongsFunctions.songs.get(i).artista.tag.add(tag);
                    return SongsFunctions.songs.get(i).artista.nome + " | " + SongsFunctions.songs.get(i).artista.tag.toString();
                }
            }
        }
        return erro;
    }

    public static String removeTags(String nomeDoArtista, String tag){
        String noTags = "No tags";
        String erro = "Inexistent artist";
        for (String i : SongsFunctions.songs.keySet()){
            if (SongsFunctions.songs.get(i).artista.nome.equals(nomeDoArtista)){
                for (String j : SongsFunctions.songs.get(i).artista.tag) {
                    if (j.equals(tag)) {
                        SongsFunctions.songs.get(i).artista.tag.remove(tag);
                        return SongsFunctions.songs.get(i).artista.nome + " | " + SongsFunctions.songs.get(i).artista.tag.toString();
                    }
                }
                return nomeDoArtista + " | " + noTags;
            }
        }
        return erro;
    }

}
