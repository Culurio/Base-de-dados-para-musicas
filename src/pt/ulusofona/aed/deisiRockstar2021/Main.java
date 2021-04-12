package pt.ulusofona.aed.deisiRockstar2021;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    static ArrayList < Song > songs = new ArrayList < > ();
    static ArrayList < Artista > artistas = new ArrayList < > ();
    static ArrayList < SongDetails > detalhes = new ArrayList < > ();
    static ParseInfo okSong = new ParseInfo(0,0);
    static ParseInfo okArtist = new ParseInfo(0,0);
    static ParseInfo okDetails = new ParseInfo(0,0);
    public static void loadFiles() throws IOException {
        songs = new ArrayList < > ();
        try {
            FileReader songsFile = new FileReader("songs.txt");
            BufferedReader reader = new BufferedReader(songsFile);
            FileReader songArtists = new FileReader("song_artists.txt");
            BufferedReader reader2 = new BufferedReader(songArtists);
            FileReader songDetails = new FileReader("song_details.txt");
            BufferedReader reader3 = new BufferedReader(songDetails);
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split("@");
                if ((dados.length == 3)) {
                    String ID = dados[0].trim();
                    String Nome = dados[1].trim();
                    String ano = dados[2].trim();
                    int anoLancamento = Integer.parseInt(ano);
                    Song song = new Song(ID, Nome, anoLancamento); // criar o obj Utilizador
                    songs.add(song); //guardar o objecto
                    okSong.ok++;
                }else{
                    okSong.ignored++;
                }
            }
            while ((linha = reader2.readLine()) != null) {
                String[] dados = linha.split("@");
                if ((dados.length == 2)) {
                    String ID = dados[0].trim();
                    String Nome = dados[1].trim();
                    Artista artista = new Artista(ID, Nome); // criar o obj Utilizador
                    artistas.add(artista); //guardar o objecto
                    okArtist.ok++;
                }else{
                    okArtist.ignored++;
                }
            }
            while ((linha = reader3.readLine()) != null) {
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
                    okDetails.ok++;
                }else{
                    okDetails.ignored++;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
        } catch (IOException e) {
            System.out.println("Ocorreu um erro durante a leitura.");
        }
    }

    public static ParseInfo getParseInfo(String fileName) {
        return switch (fileName) {
            case "songs.txt" -> okSong;
            case "song_artists-txt" -> okArtist;
            case "song_details.txt" -> okDetails;
            default -> new ParseInfo(0, 0);
        };
    }


    public static ArrayList < Song > getSongs() {
        return songs;
    }

    public static void main(String[] args) {
        try {
            loadFiles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(getParseInfo("song_details.txt"));
        System.out.println(artistas.get(0));
    }
}
