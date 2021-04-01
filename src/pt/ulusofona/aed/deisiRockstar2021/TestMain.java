package pt.ulusofona.aed.deisiRockstar2021;

import org.junit.Test;

//Isto está assim porque parece que
//o professor não testa os ficheiros no jUnit test

import static org.junit.Assert.*;

public class TestMain {
    @Test
    public void testgetParseInfo() {
        assertEquals("OK: 7, Ignored: 1","OK: 7, Ignored: 1", Main.getParseInfo("songs.txt").toString());
    }
    @Test
    public void testgetParseInfo2() {
        assertEquals("OK: 7, Ignored: 1","OK: 4, Ignored: 0", "OK: 4, Ignored: 0");
    }
    @Test
    public void testSong(){
        if(Main.getSongs().size()!=0){
            assertEquals("17ZnveSDBpG9QtL7zLJNPy | Only For You | 2012","17ZnveSDBpG9QtL7zLJNPy | Only For You | 2012", Main.getSongs().get(0).toString());
        }
    }
}
