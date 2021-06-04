package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DetailsFunctions {
    static ArrayList<SongDetails> detalhes = new ArrayList<>();
    static ParseInfo infoDetails = new ParseInfo(0, 0);
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
                int letraExplicita = Integer.parseInt(dados[2].trim());
                int popularidade = Integer.parseInt(dados[3].trim());
                double dancabilidade = Double.parseDouble(dados[4].trim());
                double vivacidade = Double.parseDouble(dados[5].trim());
                double volumeMedio = Double.parseDouble(dados[6].trim());
                SongDetails detalhe = new SongDetails(ID, duração, letraExplicita, popularidade, dancabilidade, vivacidade, volumeMedio); // criar o obj Utilizador
                detalhes.add(detalhe); //guardar o objecto
                infoDetails.ok++;
            } else {
                infoDetails.ignored++;
            }
        }
        reader.close();
    }
}
