package pt.ulusofona.aed.deisiRockstar2021;


public class Song {
    String id;
    String nome;
    int anoLancamento;
    Artista artista;
    SongDetails detalhes;

    Song() {
    }

    Song(String id, String nome, int anoLancamento) {
        this.id = id;
        this.nome = nome;
        this.anoLancamento = anoLancamento;
    }

    public String toString() {
        if (detalhes != null && artista != null) {
            String artistsThemes = artista.nrTemas.keySet() + "";
            String themes = artista.nrTemas.get(artista.nome) + "";
            String[] artistas = artistsThemes.split(",");
            Double detalhesDouble = detalhes.popularidade + 0.0;
            if (artistas.length != 1) {
                themes = "";
                for (String i : artistas) {
                    i = i.trim();
                    i = i.replace("[", "");
                    i = i.replace("]", "");
                    themes += ArtistsFunctions.nrTemas.get(i.trim()) + " / ";
                }
                themes = themes.substring(0, themes.length() - 3);
            }
            artista.nome = artista.nome.replace(",", " /");
            return id + " | " + nome + " | " + anoLancamento + " | " + (detalhes.duracao / 1000) / 60 + ":" + detalhes.duracao / (1000) % 60 + " | "
                    + detalhesDouble + " | " + artista.nome + " | (" + themes + ")";
        }
        return "";
    }
}
