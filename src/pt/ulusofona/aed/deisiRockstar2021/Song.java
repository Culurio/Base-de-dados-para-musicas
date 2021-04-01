package pt.ulusofona.aed.deisiRockstar2021;

public class Song {
    String id;
    String nome;
    int anoLancamento;
    Artista artista;
    SongDetails detalhes;

    Song(){
    }
    Song(String id,String nome,int anoLancamento){
        this.id=id;
        this.nome=nome;
        this.anoLancamento=anoLancamento;
    }
    public String toString() {
        return id + " | " + nome + " | " + anoLancamento;
    }
}
