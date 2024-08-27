public class Musteri {
    private final int id;
    private final String ad;
    private final String soyad;

    public Musteri(int id, String ad,String soyad) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
    }

    public int getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }
    public String getSoyad() {
        return soyad;
    }
}
