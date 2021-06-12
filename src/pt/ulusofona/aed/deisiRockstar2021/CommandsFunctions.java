package pt.ulusofona.aed.deisiRockstar2021;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.TreeSet;

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

    public static String countDuplicateSongYears (String years){
        int yearsInt = Integer.parseInt(years);
        TreeSet<String> nameCheck = new TreeSet<>();
        int count = 0;
        for (String i : SongsFunctions.songs.keySet()) {
            if (SongsFunctions.songs.get(i).anoLancamento == yearsInt && nameCheck.contains(SongsFunctions.songs.get(i).nome)) {
                count++;
            }else{
                nameCheck.add(SongsFunctions.songs.get(i).nome);
            }
        }
        return count + "";
    }

    //“<Nome artista> | <Nome da música> |<Ano>\n”
    public static String GetArtistsOneSong(String year1,String year2){
        int year1Int = Integer.parseInt(year1), year2Int = Integer.parseInt(year2);
        LinkedHashMap<String, Song> names = new LinkedHashMap<String, Song>();
        String result="";
        for (String i : SongsFunctions.songs.keySet()) {
            if(SongsFunctions.songs.get(i).anoLancamento>=year1Int &&SongsFunctions.songs.get(i).anoLancamento<=year2Int ){
                if(!names.containsKey(SongsFunctions.songs.get(i).artista.nome)){
                    names.put(SongsFunctions.songs.get(i).artista.nome,SongsFunctions.songs.get(i));
                }else{
                    names.remove(SongsFunctions.songs.get(i).artista.nome);
                }
            }
        }
        for (Song i : names.values()) {
            result+= i.artista.nome + " | " + i.nome + " | " + i.anoLancamento +"\n";
        }
        return result;
    }

    public static String GetUniqueTags(){
        LinkedHashMap<String, Integer> tags = new LinkedHashMap<String, Integer>();
        String result="";
        for (String i : SongsFunctions.songs.keySet()) {
            if(SongsFunctions.songs.get(i).artista.tag.size()!=0){
                for (String j:SongsFunctions.songs.get(i).artista.tag) {
                    if(tags.containsKey(j) ){
                        tags.replace(j,tags.get(j),tags.get(j)+1);
                    }else{
                        tags.put(j,1);
                    }
                }
            }
        }
        return  tags.toString();
    }

    public static ArrayList<String> sortedName(ArrayList<String> names){
        Collections.sort(names);
        return names;
    }
    public static boolean verifyYear(int year1,int year2){
        return year1 > year2 || year1 == year2;
    }

}
