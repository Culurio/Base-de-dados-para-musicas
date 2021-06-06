package pt.ulusofona.aed.deisiRockstar2021;

import java.util.*;
import java.lang.*;

public class SortHashMap {
    public static HashMap<String, SongDetails> sortByValue(HashMap<String, SongDetails> hashm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, SongDetails> > list =
                new LinkedList<Map.Entry<String, SongDetails> >(hashm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<String, SongDetails> >() {
            public int compare(Map.Entry<String, SongDetails> o1,
                               Map.Entry<String, SongDetails> o2)
            {
                Double value1 = new Double(o1.getValue().dancabilidade);
                Double value2 = new Double(o2.getValue().dancabilidade);
                return (value1).compareTo(value2);
            }
        });

        // put data from sorted list to hashmap
        HashMap<String, SongDetails> temp = new LinkedHashMap<String, SongDetails>();
        for (Map.Entry<String, SongDetails> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
