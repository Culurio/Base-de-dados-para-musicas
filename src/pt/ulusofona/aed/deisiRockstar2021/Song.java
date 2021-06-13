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
            String artistsThemes=artista.nrTemas.get(artista.nome)+"";
            String [] artistas=artistsThemes.split(",");
            Double detalhesDouble= detalhes.popularidade+0.0;
            if(artistas.length!=1){
                artistsThemes="";
                for (String i:artistas){
                    artistsThemes+= i+"/";
                }
            }
            return id + " | " + nome + " | " + anoLancamento + " | " + (detalhes.duracao / 1000) / 60 + ":" + detalhes.duracao / (1000) % 60 + " | " + detalhesDouble + " | " + artista.nome + " | (" + artistsThemes + ")";
        }
        return "";
    }
}
