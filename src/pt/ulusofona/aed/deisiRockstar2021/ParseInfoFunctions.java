package pt.ulusofona.aed.deisiRockstar2021;

public class ParseInfoFunctions {
    public static ParseInfo getParseInfo(String fileName) {
        return switch (fileName) {
            case "songs.txt" -> SongsFunctions.infoSong;
            case "song_artists.txt" -> ArtistsFunctions.infoArtist;
            case "song_details.txt" -> DetailsFunctions.infoDetails;
            default -> new ParseInfo(0, 0);
        };
    }
}
