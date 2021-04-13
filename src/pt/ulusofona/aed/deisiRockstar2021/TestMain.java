package pt.ulusofona.aed.deisiRockstar2021;

import org.junit.Test;

//Isto está assim porque parece que
//o professor não testa os ficheiros no jUnit test

import static org.junit.Assert.*;

public class TestMain {
    @Test
    public void testSong1() {
        Song musica = new Song("0tStWvUMHODuLN4TIaSGab ","Loops & Tings - Radio Edit",2013);
        assertEquals("0tStWvUMHODuLN4TIaSGab | Loops & Tings - Radio Edit | 2013",
                "0tStWvUMHODuLN4TIaSGab  | Loops & Tings - Radio Edit | 2013", musica.toString());
    }
    @Test
    public void testSong2(){
        Song musica = new Song("17ZnveSDBpG9QtL7zLJNPy","Only For You",2012);
        assertEquals("17ZnveSDBpG9QtL7zLJNPy | Only For You | 2012",
                "17ZnveSDBpG9QtL7zLJNPy | Only For You | 2012", musica.toString());
    }
    @Test
    public void testParseInfo(){
        ParseInfo verification = new ParseInfo(7,1);
        assertEquals("OK: 7, Ignored: 1",verification.toString());
    }
}
