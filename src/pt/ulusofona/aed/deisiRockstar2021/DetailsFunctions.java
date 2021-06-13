package pt.ulusofona.aed.deisiRockstar2021;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;

public class DetailsFunctions {
    static LinkedHashMap<String, SongDetails> details = new LinkedHashMap<>();
    static ParseInfo infoDetails = new ParseInfo(0, 0);

    public static void lerDetails(String filename) throws IOException {
        FileReader songDetails = new FileReader(filename);
        BufferedReader reader = new BufferedReader(songDetails);
        details.clear();
        infoDetails.ok = 0;
        infoDetails.ignored = 0;
        String linha;
        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split("@");
            if (validDetails(dados)) {
                String ID = dados[0].trim();
                int duração = Integer.parseInt(dados[1].trim());
                int letraExplicita = Integer.parseInt(dados[2].trim());
                int popularidade = Integer.parseInt(dados[3].trim());
                double dancabilidade = Double.parseDouble(dados[4].trim());
                double vivacidade = Double.parseDouble(dados[5].trim());
                double volumeMedio = Double.parseDouble(dados[6].trim());
                SongDetails detail = new SongDetails(ID, duração, letraExplicita, popularidade, dancabilidade, vivacidade, volumeMedio); // criar o obj Utilizador
                details.put(ID, detail); //guardar o objecto
                infoDetails.ok++;
            } else {
                infoDetails.ignored++;
            }
        }
        reader.close();
    }

    public static Boolean validDetails(String[] dados){
        if ((dados.length == 7) && SongsFunctions.songs.containsKey(dados[0].trim()) && !details.containsKey(dados[0].trim())) {
            if(dados[1].contains(".") || dados[3].contains(".") || !dados[4].contains(".") || !dados[5].contains(".")
                    || !dados[6].contains(".") || (Integer.parseInt(dados[2].trim())!=1 && Integer.parseInt(dados[2].trim()) !=0)){
                return false;
            }else {
                return true;
            }
        }
        return false;
    }
}
