package pt.ulusofona.aed.deisiRockstar2021;

import java.util.concurrent.TimeUnit;

public class Song {
    String id;
    String nome;
    int anoLancamento;
    Artista artista;
    SongDetails detalhes;
    String tag;

    Song(){
    }
    Song(String id,String nome,int anoLancamento){
        this.id=id;
        this.nome=nome;
        this.anoLancamento=anoLancamento;
    }

    public String toString() {
        float time= detalhes.duracao/600;
        time=time/100;
        String timeString=time +"";
        timeString=timeString.replace('.',':');
        return id + " | " + nome + " | " + anoLancamento+" | "+timeString+" | "+detalhes.popularidade+" | "+artista.nome+" | ("+artista.nrTemas+")\n";
    }
}
