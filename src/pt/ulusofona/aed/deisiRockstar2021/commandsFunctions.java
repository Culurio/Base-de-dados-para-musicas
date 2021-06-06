package pt.ulusofona.aed.deisiRockstar2021;

public class commandsFunctions {
    public static String CountSongYears(String years) {
        int yearsInt= Integer.parseInt(years);
        int count=0;
        for(String i : SongsFunctions.songs.keySet()){
            if(SongsFunctions.songs.get(i).anoLancamento==yearsInt){
                count++;
            }
        }
        return count+"";
    }

    public static String GetArtistsForTag(String tag){
        String result="";
        for(String i : SongsFunctions.songs.keySet()){
            if(SongsFunctions.songs.get(i).tag==tag){
                result +=SongsFunctions.songs.get(i).artista.nome+"\n";
            }
        }
        return result;
    }
}
