package pt.ulusofona.aed.deisiRockstar2021;


import java.util.LinkedHashMap;

public class CleanupFunctions {
    public static String cleanup(){
        Cleanup clearInfo = new Cleanup(0, 0);
        LinkedHashMap<String, Song> songs = new LinkedHashMap<>();
        LinkedHashMap<String, Artista> artists = new LinkedHashMap<>();
        for (String i : SongsFunctions.songs.keySet()) {
            if (SongsFunctions.songs.get(i).detalhes != null || SongsFunctions.songs.get(i).artista != null ) {
                songs.put(i, SongsFunctions.songs.get(i));
            }else{
                clearInfo.music++;
            }
        }
        for (String i : ArtistsFunctions.artistas.keySet()) {
            if (SongsFunctions.songs.containsKey(i)) {
                songs.put(i, SongsFunctions.songs.get(i));
            }else{
                clearInfo.artists++;
            }
        }
        ArtistsFunctions.artistas.clear();
        ArtistsFunctions.artistas=artists;
        SongsFunctions.songs.clear();
        SongsFunctions.songs=songs;
        return ""+clearInfo;
    }
}
