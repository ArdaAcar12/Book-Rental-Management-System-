import java.util.*;
import java.util.stream.Collectors;

public class KiralamaIslemleri<Kutuphane extends Kitap> {
    private List<Kutuphane> kitaplar;
    private Map<Kutuphane, Musteri> kiralananKitaplar;

    public KiralamaIslemleri(List<Kutuphane> kitaplar) {
        this.kitaplar = kitaplar;
        this.kiralananKitaplar = new HashMap<>();
    }

    // Kitap kiralama işlemi
    public void kiralamaYap(String kitapAdi, Musteri musteri) throws KitapBulunamadiException, KitapKiralanmisException {
        Kutuphane kitap = kitapAra(kitapAdi);
        if (kitap.isKiralanmis()) {
            throw new KitapKiralanmisException("Kitap zaten kiralanmış.");
        }
        kiralananKitaplar.put(kitap, musteri);
        kitap.setKiralanmis(true);
    }

    // Kitap adına göre arama
    public Kutuphane kitapAra(String kitapAdi) throws KitapBulunamadiException {
        return kitaplar.stream()
                .filter(kitap -> kitap.getAd().equalsIgnoreCase(kitapAdi))
                .findFirst()
                .orElseThrow(() -> new KitapBulunamadiException("Kitap bulunamadı."));
    }

    // Müşteri adına ve soyadına göre arama
    public Musteri musteriAra(String ad, String soyad, List<Musteri> musteriler) throws MusteriBulunamadiException {
        return musteriler.stream()
                .filter(musteri -> musteri.getAd().equalsIgnoreCase(ad) &&
                        musteri.getSoyad().equalsIgnoreCase(soyad))
                .findFirst()
                .orElseThrow(() -> new MusteriBulunamadiException("Müşteri bulunamadı: " + ad + " " + soyad));
    }

    // Müşteri ID'si ile kiraladığı kitapları bulma
    public List<Kutuphane> musteriIdIleKiralananKitaplar(int musteriId) throws MusteriBulunamadiException {
        return kiralananKitaplar.entrySet().stream()
                .filter(entry -> entry.getValue().getId() == musteriId)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public void musteriKiralananKitaplariGoster(Musteri musteri) {
        List<Kutuphane> kitaplar = kiralananKitaplar.entrySet().stream()
                .filter(entry -> entry.getValue().equals(musteri))
                .map(Map.Entry::getKey)
                .toList();//.collect(Collectors.toList());

        if (kitaplar.isEmpty()) {
            System.out.println(musteri.getAd() + " " + musteri.getSoyad() + " tarafından kiralanan kitap bulunamadı.");
        } else {
            System.out.println(musteri.getAd() + " " + musteri.getSoyad() + " tarafından kiralanan kitaplar:");
            kitaplar.forEach(kitap -> System.out.println("Kitap: " + kitap.getAd()));
        }
    }
}