package pt.ulusofona.aed.deisiRockstar2021;

public class Cleanup {
    int music;
    int artists;

    Cleanup(int music, int artists) {
        this.music = music;
        this.artists = artists;
    }

    public String toString() {
        return "Musicas removidas: " + music + "; Artistas removidos: " + artists;
    }
}
