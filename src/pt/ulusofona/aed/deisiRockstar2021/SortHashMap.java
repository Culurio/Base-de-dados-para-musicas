package pt.ulusofona.aed.deisiRockstar2021;

import java.util.*;
import java.lang.*;

public class SortHashMap {
    public static HashMap<String, Song> sortByValue(HashMap<String, Song> hashm)
    {
        HashMap<String, Song> songs = new HashMap<>();

        for(String i : hashm.keySet()){
            if (hashm.get(i).detalhes!=null){
                songs.put(i,hashm.get(i));
            }
        }
        // Create a list from elements of HashMap
        List<Map.Entry<String, Song> > list =
                new LinkedList<Map.Entry<String, Song> >(songs.entrySet());

        // Sort the list

        Collections.sort(list, new Comparator<Map.Entry<String, Song> >() {
            public int compare(Map.Entry<String, Song> o1,
                               Map.Entry<String, Song> o2)
            {
                Double value1 = new Double(o1.getValue().detalhes.dancabilidade);
                Double value2 = new Double(o2.getValue().detalhes.dancabilidade);
                return (value1).compareTo(value2);
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, Song> temp = new LinkedHashMap<String, Song>();
        for (Map.Entry<String, Song> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}