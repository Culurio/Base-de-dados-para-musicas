package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    static ArrayList<Song> songs = new ArrayList<>();
    static ArrayList<Artista> artistas = new ArrayList<>();
    static ArrayList<SongDetails> detalhes = new ArrayList<>();
    static ParseInfo infoSong = new ParseInfo(0, 0);
    static ParseInfo infoArtist = new ParseInfo(0, 0);
    static ParseInfo infoDetails = new ParseInfo(0, 0);

    public static void lerSongs(String filename) throws IOException {
        songs.clear();
        infoSong.ok=0;
        infoSong.ignored=0;
        FileReader songsFile = new FileReader(filename);
        BufferedReader reader = new BufferedReader(songsFile);
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split("@");
            if (dados.length == 3) {
                String ID = dados[0].trim();
                String Nome = dados[1].trim();
                String ano = dados[2].trim();
                int anoLancamento = Integer.parseInt(ano);
                Song song = new Song(ID, Nome, anoLancamento); // criar o obj Utilizador
                songs.add(song); //guardar o objecto
                infoSong.ok++;
            } else {
                infoSong.ignored++;
            }
        }
        reader.close();
    }

    public static void lerArtists(String filename) throws IOException {
        FileReader songArtists = new FileReader(filename);
        BufferedReader reader = new BufferedReader(songArtists);
        infoArtist.ok=0;
        infoArtist.ignored=0;
        artistas.clear();
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split("@");
            if ((dados.length == 2)) {
                String ID = dados[0].trim();
                String Nome = dados[1].trim();
                Artista artista = new Artista(ID, Nome); // criar o obj Utilizador
                artistas.add(artista); //guardar o objecto
                infoArtist.ok++;
            } else {
                infoArtist.ignored++;
            }
        }
        reader.close();
    }

    public static void lerDetails(String filename) throws IOException {
        FileReader songDetails = new FileReader(filename);
        BufferedReader reader = new BufferedReader(songDetails);
        detalhes.clear();
        infoDetails.ok=0;
        infoDetails.ignored=0;
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split("@");
            if ((dados.length == 7)) {
                String ID = dados[0].trim();
                int duração = Integer.parseInt(dados[1].trim());
                int letraExplicita = Integer.parseInt(dados[1].trim());
                int popularidade = Integer.parseInt(dados[1].trim());
                double dancabilidade = Double.parseDouble(dados[1].trim());
                double vivacidade = Double.parseDouble(dados[1].trim());
                double volumeMedio = Double.parseDouble(dados[1].trim());
                SongDetails detalhe = new SongDetails(ID, duração, letraExplicita, popularidade, dancabilidade, vivacidade, volumeMedio); // criar o obj Utilizador
                detalhes.add(detalhe); //guardar o objecto
                infoDetails.ok++;
            } else {
                infoDetails.ignored++;
            }
        }
        reader.close();
    }

    public static void loadFiles() throws IOException {
            lerSongs("songs.txt");
            lerArtists("song_artists.txt");
            lerDetails("song_details.txt");
    }

    public static ParseInfo getParseInfo(String fileName) {
        switch (fileName) {
            case "songs.txt":
                return infoSong;
            case "song_artists.txt":
                return infoArtist;
            case "song_details.txt":
                return infoDetails;
            default:
                return new ParseInfo(0, 0);
        }
    }


    public static ArrayList<Song> getSongs() {
        return songs;
    }

    public static void main(String[] args) {
        try {
            loadFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(getSongs());
        System.out.println(getParseInfo("song_details.txt"));
        System.out.println(getParseInfo("songs.txt"));
        System.out.println(getParseInfo("song_artists.txt"));
    }
}
