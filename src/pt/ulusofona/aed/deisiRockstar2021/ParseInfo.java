package pt.ulusofona.aed.deisiRockstar2021;

public class ParseInfo {
    int ok;
    int ignored;

    ParseInfo(int ok,int ignored){
        this.ok=ok;
        this.ignored=ignored;
    }
    public String toString() {
        return "OK: "+ ok +", Ignored: " + ignored;
    }
}
