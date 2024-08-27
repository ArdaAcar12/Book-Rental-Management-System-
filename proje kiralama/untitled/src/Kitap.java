import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Kitap {
    private final int id;
    private final String ad;
    private boolean kiralanmis;

    public Kitap(int id, String ad) {
        this.id = id;
        this.ad = ad;
        this.kiralanmis = false;
    }

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public boolean isKiralanmis() {
        return kiralanmis;
    }

    public void setKiralanmis(boolean kiralanmis) {
        this.kiralanmis = kiralanmis;
    }



}