package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

public class ArtistsFunctions {
    static LinkedHashMap<String, Artista> artistas = new LinkedHashMap<>();
    static ParseInfo infoArtist = new ParseInfo(0, 0);
    static LinkedHashMap<String, Integer> nrTemas = new LinkedHashMap<String, Integer>();

    public static void lerArtists(String filename) throws IOException {
        FileReader songArtists = new FileReader(filename);
        BufferedReader reader = new BufferedReader(songArtists);
        infoArtist.ok = 0;
        infoArtist.ignored = 0;
        artistas.clear();
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split("@");
            if (validLine(dados)) {
                String ID = dados[0].trim();
                String Nome = filterNames(dados[1].trim());
                String[] nomes=Nome.split(",");
                for(String i:nomes) {
                    numberOfThemes(i, ID);
                }
                Artista artista = new Artista(ID, Nome); // criar o obj Utilizador
                infoArtist.ok++;
                artistas.put(ID, artista); //guardar o objecto
            } else {
                infoArtist.ignored++;
            }
        }
        reader.close();
    }

    public static String filterNames(String artist) {
        if(artist.charAt(0)=='\"'){
            artist=artist.substring(1,artist.length()-1);
        }
        artist=artist.replace('[',' ');
        artist=artist.replace(']',' ');

        return artist.trim();
    }


    public static boolean validLine(String[] dados) {
        if ((dados.length == 2) && !artistas.containsKey(dados[0].trim())) {
            String Nome = filterNames(dados[1].trim());
            String[] Nomes = Nome.split(",");
            for (String nome : Nomes) {
                if (nome.length() < 2) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public static void numberOfThemes(String artistName,String songKey){
        if(!SongsFunctions.songs.containsKey(songKey)){

        }
        else if(nrTemas.containsKey(artistName) && SongsFunctions.songs.containsKey(songKey)){
            nrTemas.replace(artistName,nrTemas.get(artistName),nrTemas.get(artistName)+1);
        }else{
            nrTemas.put(artistName,1);
        }
    }
    public static void assignThemes(){
        for (String i:SongsFunctions.songs.keySet()){
            if(nrTemas.containsKey(SongsFunctions.songs.get(i).artista.nome)){
                SongsFunctions.songs.get(i).artista.nrTemas.put(artistas.get(i).nome,nrTemas.get(artistas.get(i).nome));
            }
        }
    }
}
