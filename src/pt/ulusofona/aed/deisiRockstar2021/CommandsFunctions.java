package pt.ulusofona.aed.deisiRockstar2021;

import java.util.*;

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

    public static String GetArtistsOneSong(String year1,String year2){
        int year1Int = Integer.parseInt(year1), year2Int = Integer.parseInt(year2);
        TreeMap<String, Song> names = new TreeMap<>();
        StringBuilder result= new StringBuilder();

        for (String i : SongsFunctions.songs.keySet()) {
            if(SongsFunctions.songs.get(i).anoLancamento>=year1Int && SongsFunctions.songs.get(i).anoLancamento<=year2Int ) {
                if (!names.containsKey(SongsFunctions.songs.get(i).artista.nome)) {
                    names.put(SongsFunctions.songs.get(i).artista.nome, SongsFunctions.songs.get(i));
                } else {
                    names.remove(SongsFunctions.songs.get(i).artista.nome);
                }
            }
        }
        for (Song i : names.values()) {
            result.append(i.artista.nome).append(" | ").append(i.nome).append(" | ").append(i.anoLancamento).append("\n");
        }
        return result.toString();
    }

    public static String GetUniqueTags(){
        LinkedHashMap<String, Integer> tags = new LinkedHashMap<>();
        StringBuilder result= new StringBuilder();

        for (String i : SongsFunctions.songs.keySet()) {
            IncrementTags(tags, i);
        }
        tags=SortHashMap.SortInt(tags);
        List<String> alKeys = new ArrayList<String>(tags.keySet());
        Collections.reverse(alKeys);
        for (String i:alKeys) {
            result.append(i).append(" | ").append(tags.get(i)).append("\n");
        }
        return  result.toString();
    }
    public static String getUniqueTagsInBetweenYears(String year1, String year2) {
        LinkedHashMap<String, Integer> tags = new LinkedHashMap<>();
        StringBuilder result= new StringBuilder();
        String noResults = "No results";
        int year1Int = Integer.parseInt(year1), year2Int = Integer.parseInt(year2);

        for (String i : SongsFunctions.songs.keySet()) {
            if (SongsFunctions.songs.get(i).anoLancamento >= year1Int && SongsFunctions.songs.get(i).anoLancamento <= year2Int) {
                IncrementTags(tags, i);
            }
        }
        tags=SortHashMap.SortInt(tags);
        for(String i:tags.keySet()){
            result.append(i).append(" | ").append(tags.get(i)).append("\n");
        }
        return result.toString();
    }

    public static LinkedHashMap<String, Integer> IncrementTags(LinkedHashMap<String, Integer> tags,String hashKey){
        if (SongsFunctions.songs.get(hashKey).artista.tag.size() != 0) {
            for (String j : SongsFunctions.songs.get(hashKey).artista.tag) {
                if (tags.containsKey(j)) {
                    tags.replace(j, tags.get(j), tags.get(j) + 1);
                } else {
                    tags.put(j, 1);
                }
            }
        }
        return tags;
    }

    /*public static String getTopArtistWithSongsBetween(String nrDeArtistas,String minimoString, String maximoString){
        LinkedHashMap<String, Integer> numeroDeMusicas = new LinkedHashMap<>();
        StringBuilder resultado = new StringBuilder();
        int minimo = Integer.parseInt(minimoString), maximo = Integer.parseInt(maximoString);

        for (String s : SongsFunctions.songs.keySet()){
            if (SongsFunctions.songs.get(s).artista.nrTemas.get(s) >= minimo && SongsFunctions.songs.get(s).artista.nrTemas.get(s) <= maximo){
                if (SongsFunctions.songs.get(s).artista.nrTemas.size() != 0){
                    for (String i : SongsFunctions.songs.get(s).artista.nrTemas.keySet()){
                        if (numeroDeMusicas.containsKey(i)){
                            numeroDeMusicas.replace(i, numeroDeMusicas.get(i), numeroDeMusicas.get(i) + 1);
                        } else{
                            numeroDeMusicas.put(i, 1);
                        }
                    }
                }
            }
        }

        numeroDeMusicas = SortHashMap.SortInt(numeroDeMusicas);
        for (String f : numeroDeMusicas.keySet()){
            resultado.append(f).append(" ").append(numeroDeMusicas.get(f)).append("\n");
        }
        return resultado.toString();
    }*/
}
