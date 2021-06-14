package pt.ulusofona.aed.deisiRockstar2021;

import java.util.*;


public class CommandsFunctions {
    static Boolean flag=false;
    static LinkedHashMap<String, Song> songForDetails=null;

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
            if (SongsFunctions.songs.get(i).artista != null && SongsFunctions.songs.get(i).detalhes != null) {
                for (String j : SongsFunctions.songs.get(i).artista.tag) {
                    if (j.equals(tag)) {
                        result.append(SongsFunctions.songs.get(i).artista.nome).append(";");
                    }
                }
            }
        }
        if (result.toString().length() == 0) {
            return ErrorMessages.noResults();
        }
        return result.substring(0, result.toString().length() - 1);
    }

    public static String getMostDanceable(String year1, String year2, String results) {
        int year1Int = Integer.parseInt(year1), year2Int = Integer.parseInt(year2), resultsInt = Integer.parseInt(results);
        StringBuilder result = new StringBuilder();
        ArrayList<Song> mostDanceable = new ArrayList<>();
        if(!flag){
            songForDetails = SongsFunctions.sortDetails(SongsFunctions.songs);
        }

        for (String i : songForDetails.keySet()) {
            if(songForDetails.get(i).anoLancamento>=year1Int && songForDetails.get(i).anoLancamento <= year2Int ){
                mostDanceable.add(songForDetails.get(i));
            }else {
                mostDanceable.remove(songForDetails.get(i));
            }
        }
        int danceableOrder = mostDanceable.size() - 1;

        danceableOrder = mostDanceable.size() - 1;
        for (int i = danceableOrder; i > danceableOrder - resultsInt; i--) {
            result.append(mostDanceable.get(i).nome).append(" : ").append(mostDanceable.get(i).anoLancamento).append(" : ").append(mostDanceable.get(i).detalhes.dancabilidade).append("\n");
        }
        flag=true;
        return result.toString();

    }

    public static String addTag(String resposta) {
        String[] respostas = resposta.split(";");
        String nome = respostas[0];
        String tag;
        StringBuilder result = new StringBuilder(nome + " | ");

        for (String i : SongsFunctions.songs.keySet()) {
            if (SongsFunctions.songs.get(i).artista != null && SongsFunctions.songs.get(i).artista.nome.equals(nome)) {
                for (int ii = 1; ii < respostas.length; ii++) {
                    tag = respostas[ii].toUpperCase();
                    if (!tagUsed(tag, i)) {
                        SongsFunctions.songs.get(i).artista.tag.add(tag);
                    }
                }
                for (int iii = 0; iii < SongsFunctions.songs.get(i).artista.tag.size(); iii++) {
                    result.append(SongsFunctions.songs.get(i).artista.tag.get(iii)).append(",");
                }
                return result.substring(0, result.length() - 1);
            }
        }
        return ErrorMessages.inexistent_artist();
    }

    public static Boolean tagUsed(String tag, String key) {
        if (SongsFunctions.songs.get(key).artista.tag.size() != 0) {
            for (int i = 0; i < SongsFunctions.songs.get(key).artista.tag.size(); i++) {
                if (SongsFunctions.songs.get(key).artista.tag.get(i).equals(tag)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static String removeTags(String resposta) {
        String[] respostas = resposta.split(";");
        String nome = respostas[0];
        String tag;
        StringBuilder result = new StringBuilder(nome + " | ");

        for (String i : SongsFunctions.songs.keySet()) {
            if (SongsFunctions.songs.get(i).artista != null && SongsFunctions.songs.get(i).artista.nome.equals(nome)) {
                for (int ii = 1; ii < respostas.length; ii++) {
                    tag = respostas[ii].toUpperCase();
                    if (tagUsed(tag, i)) {
                        SongsFunctions.songs.get(i).artista.tag.remove(tag);
                    }
                }
                for (int iii = 0; iii < SongsFunctions.songs.get(i).artista.tag.size(); iii++) {
                    result.append(SongsFunctions.songs.get(i).artista.tag.get(iii)).append(",");
                }
                if (SongsFunctions.songs.get(i).artista.tag.size() == 0) {
                    return result + ErrorMessages.no_tags();
                }
                return result.substring(0, result.length() - 1);
            }
        }
        return ErrorMessages.inexistent_artist();
    }

    public static String countDuplicateSongYears(String years) {
        int yearsInt = Integer.parseInt(years);
            HashSet<String> nameCheck = new HashSet<>();
        int count = 0;
        for (String i : SongsFunctions.songs.keySet()) {
            if (SongsFunctions.songs.get(i).anoLancamento == yearsInt && nameCheck.contains(SongsFunctions.songs.get(i).nome)) {
                count++;
            } else if(SongsFunctions.songs.get(i).anoLancamento == yearsInt) {
                nameCheck.add(SongsFunctions.songs.get(i).nome);
            }
        }
        return count + "";
    }

    public static String getArtistsOneSong(String year1, String year2) {
        int year1Int = Integer.parseInt(year1), year2Int = Integer.parseInt(year2);
        TreeMap<String, Song> names = new TreeMap<>();
        StringBuilder result = new StringBuilder();
        for (String i : SongsFunctions.songs.keySet()) {
            if (SongsFunctions.songs.get(i).artista != null && SongsFunctions.songs.get(i).detalhes != null) {
                if (SongsFunctions.songs.get(i).anoLancamento >= year1Int && SongsFunctions.songs.get(i).anoLancamento <= year2Int) {
                    if (!names.containsKey(SongsFunctions.songs.get(i).artista.nome)) {
                        names.put(SongsFunctions.songs.get(i).artista.nome, SongsFunctions.songs.get(i));
                    } else {
                        names.remove(SongsFunctions.songs.get(i).artista.nome);
                    }
                }
            }
        }
        for (Song i : names.values()) {
            result.append(i.artista.nome).append(" | ").append(i.nome).append(" | ").append(i.anoLancamento).append("\n");
        }
        return result.toString();
    }

    public static String getUniqueTags() {
        LinkedHashMap<String, Integer> tags = new LinkedHashMap<>();
        StringBuilder result = new StringBuilder();

        for (String i : SongsFunctions.songs.keySet()) {
            if(SongsFunctions.songs.get(i).artista != null && SongsFunctions.songs.get(i).detalhes != null ){
                incrementTags(tags, i);
            }
        }
        tags = SortHashMap.sortInt(tags);
        List<String> alKeys = new ArrayList<>(tags.keySet());
        Collections.reverse(alKeys);
        for (String i : alKeys) {
            result.append(i).append(" | ").append(tags.get(i)).append("\n");
        }
        return result.toString();
    }

    public static String getUniqueTagsInBetweenYears(String year1, String year2) {
        LinkedHashMap<String, Integer> tags = new LinkedHashMap<>();
        StringBuilder result = new StringBuilder();
        int year1Int = Integer.parseInt(year1), year2Int = Integer.parseInt(year2);

        for (String i : SongsFunctions.songs.keySet()) {
            if(SongsFunctions.songs.get(i).artista != null && SongsFunctions.songs.get(i).detalhes != null ) {
                if (SongsFunctions.songs.get(i).anoLancamento >= year1Int && SongsFunctions.songs.get(i).anoLancamento <= year2Int) {
                    incrementTags(tags, i);
                }
            }
        }
        tags = SortHashMap.sortInt(tags);
        for (String i : tags.keySet()) {
            result.append(i).append(" | ").append(tags.get(i)).append("\n");
        }
        return result.toString();
    }

    public static LinkedHashMap<String, Integer> incrementTags(LinkedHashMap<String, Integer> tags, String hashKey) {
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

    public static String getTopArtistWithSongsBetween(String nrDeArtistas,String minimoString, String maximoString){
        LinkedHashMap<String, Integer> numeroDeMusicas = new LinkedHashMap<>();
        StringBuilder resultado = new StringBuilder();
        int minimo = Integer.parseInt(minimoString), maximo = Integer.parseInt(maximoString),count=0;
        int nrDeArtistasInt =Integer.parseInt(nrDeArtistas);

        for (String s : SongsFunctions.songs.keySet()){
            if (betweenYear(s,minimo,maximo)){
                if (SongsFunctions.songs.get(s).artista.nrTemas.size() != 0){
                    for (String i : SongsFunctions.songs.get(s).artista.nrTemas.keySet()){
                        if(count<nrDeArtistasInt){
                            if (numeroDeMusicas.containsKey(i)){
                                numeroDeMusicas.replace(i, numeroDeMusicas.get(i), numeroDeMusicas.get(i) + 1);
                            } else{
                                numeroDeMusicas.put(i, 1);
                            }
                        }else{
                            break;
                        }
                    }
                }
            }
        }

        numeroDeMusicas = SortHashMap.sortInt(numeroDeMusicas);
        for (String f : numeroDeMusicas.keySet()){
            resultado.append(f).append(" ").append(numeroDeMusicas.get(f)).append("\n");
        }
        return resultado.toString();
    }

    public static Boolean betweenYear(String key,int minimo,int maximo){
        if(SongsFunctions.songs.get(key).artista != null && SongsFunctions.songs.get(key).detalhes != null ){
            if(ArtistsFunctions.artistas.get(key).nrTemas.get(SongsFunctions.songs.get(key).artista.nome) >= minimo
                    && ArtistsFunctions.artistas.get(key).nrTemas.get(SongsFunctions.songs.get(key).artista.nome) <= maximo){
                return true;
            }
        }
        return false;
    }
}
