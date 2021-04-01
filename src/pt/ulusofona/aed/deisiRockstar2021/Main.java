package pt.ulusofona.aed.deisiRockstar2021;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void loadFiles() throws IOException{

    }

    public static ParseInfo getParseInfo(String fileName){
        int ok=0;
        int ignore=0;
                try {
                    FileReader songsFile = new FileReader(fileName);
                    BufferedReader reader = new BufferedReader(songsFile);
                    String linha;
                    while((linha = reader.readLine()) != null) {
                        String[] dados = linha.split("@");
                        if((fileName.equals("songs.txt") && dados.length==3) || (fileName.equals("song_artists.txt") && dados.length==2)
                                || (fileName.equals("song_details.txt") && dados.length==7)) {
                            ok++;
                        }else{
                            ignore++;
                        }
                    }
                    reader.close();
                }
                catch(FileNotFoundException e) {
                    System.out.println("Ficheiro não encontrado");
                }
                catch(IOException e) {
                    System.out.println("Ocorreu um erro durante a leitura.");
                }
        return new ParseInfo(ok,ignore);
        }


    public static ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        try {
            FileReader songsFile = new FileReader("songs.txt");
            BufferedReader reader = new BufferedReader(songsFile);
            String linha;
            while((linha = reader.readLine()) != null) {
                String[] dados = linha.split("@");
                if(dados.length==3) {
                    String ID = dados[0].trim();
                    String Nome = dados[1].trim();
                    String ano = dados[2].trim();
                    int anoLancamento = Integer.parseInt(ano);
                    Song song = new Song(ID, Nome,anoLancamento);// criar o obj Utilizador
                    songs.add(song);//guardar o objecto
                }
            }
            reader.close();
        }
        catch(FileNotFoundException e) {
            System.out.println("Ficheiro não encontrado");
        }
        catch(IOException e) {
            System.out.println("Ocorreu um erro durante a leitura.");
        }
        return songs;
    }

    public static void main(String[] args) {
       System.out.println(getParseInfo("songs.txt"));
    }
}

