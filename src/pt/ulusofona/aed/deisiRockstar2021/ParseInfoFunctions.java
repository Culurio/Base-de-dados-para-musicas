package pt.ulusofona.aed.deisiRockstar2021;

public class ParseInfoFunctions {
    public static ParseInfo getParseInfo(String fileName) {
         switch (fileName) {
             case "songs.txt": return SongsFunctions.infoSong;
             case "song_artists.txt": return ArtistsFunctions.infoArtist;
             case "song_details.txt":  return DetailsFunctions.infoDetails;
             default: return new ParseInfo(0, 0);
        }
    }
}
