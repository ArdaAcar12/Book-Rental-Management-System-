import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Kitap> kitaplar = Arrays.asList(
                new Kitap(1, "Yüzüklerin Efendisi"),
                new Kitap(2, "Harry Potter ve Felsefe Taşı"),
                new Kitap(3, "Savaş ve Barış"),
                new Kitap(4, "Suç ve Ceza"),
                new Kitap(5, "Gülbeyaz"),
                new Kitap(6, "Küçük Prens"),
                new Kitap(7, "Sefiller"),
                new Kitap(8, "Sineklerin Tanrısı"),
                new Kitap(9, "1984"),
                new Kitap(10, "Hayvan Çiftliği"),
                new Kitap(11, "Anna Karenina"),
                new Kitap(12, "Brave New World"),
                new Kitap(13, "Yüzbaşı Ekinci"),
                new Kitap(14, "Bülbülü Öldürmek"),
                new Kitap(15, "Yıldızların Altında"),
                new Kitap(16, "Alchemist"),
                new Kitap(17, "Savaş ve Barış"),
                new Kitap(18, "Moby Dick"),
                new Kitap(19, "Yüzüklerin Efendisi"),
                new Kitap(20, "Savaş ve Barış")
        );

        List<Musteri> musteriler = Arrays.asList(
                new Musteri(1, "Ahmet", "Yılmaz"),
                new Musteri(2, "Ayşe", "Demir"),
                new Musteri(3, "Mehmet", "Kaya"),
                new Musteri(4, "Fatma", "Yurt"),
                new Musteri(5, "Ali", "Çelik"),
                new Musteri(6, "Zeynep", "Kara"),
                new Musteri(7, "Can", "Öztürk"),
                new Musteri(8, "Elif", "Arslan"),
                new Musteri(9, "Emre", "Polat"),
                new Musteri(10, "Büşra", "Yüksel"),
                new Musteri(11, "Okan", "Kaya"),
                new Musteri(12, "Derya", "Acar"),
                new Musteri(13, "Mert", "Sönmez"),
                new Musteri(14, "Gül", "Arı"),
                new Musteri(15, "Ali", "Demirtaş"),
                new Musteri(16, "Eda", "Yurt"),
                new Musteri(17, "Sinan", "Kara"),
                new Musteri(18, "Seda", "Balcı"),
                new Musteri(19, "Gökhan", "Arslan"),
                new Musteri(20, "Meltem", "Koç")
        );

            KiralamaIslemleri<Kitap> kiralamaIslemleri = new KiralamaIslemleri<>(kitaplar);

        Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("\n--- Kütüphane Yönetim Sistemi ---");
                System.out.println("1. Müşterinin Kiraladığı Kitapları Görüntüle (Ad ve Soyad ile)");
                System.out.println("2. Kitap Kiralama Yap");
                System.out.println("3. Müşteri ID'si ile Kiraladığı Kitapları Bul");
                System.out.println("4. Müşterinin Kiraladığı Kitapları Listele");
                System.out.println("5. Çıkış");
                System.out.print("Seçiminizi yapınız: ");

                int secim = scanner.nextInt();
                scanner.nextLine();

                switch (secim) {
                    case 1:
                        System.out.print("Müşteri Adını Giriniz: ");
                        String musteriAdi = scanner.nextLine();
                        System.out.print("Müşteri Soyadını Giriniz: ");
                        String musteriSoyadi = scanner.nextLine();

                        try {
                            Musteri musteri = kiralamaIslemleri.musteriAra(musteriAdi, musteriSoyadi, musteriler);
                            kiralamaIslemleri.musteriKiralananKitaplariGoster(musteri);
                        } catch (MusteriBulunamadiException e) {
                            System.err.println(e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.print("Müşteri Adını Giriniz: ");
                        String kiralamaMusteriAdi = scanner.nextLine();
                        System.out.print("Müşteri Soyadını Giriniz: ");
                        String kiralamaMusteriSoyadi = scanner.nextLine();

                        try {
                            Musteri kiralamaMusteri = kiralamaIslemleri.musteriAra(kiralamaMusteriAdi, kiralamaMusteriSoyadi, musteriler);

                            System.out.print("Kitap Adını Giriniz: ");
                            String kiralanacakKitapAdi = scanner.nextLine();

                            kiralamaIslemleri.kiralamaYap(kiralanacakKitapAdi, kiralamaMusteri);
                            System.out.println("Kitap başarıyla kiralandı.");
                        } catch (MusteriBulunamadiException | KitapBulunamadiException | KitapKiralanmisException e) {
                            System.err.println(e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.print("Müşteri ID'sini Giriniz: ");
                        int musteriId = scanner.nextInt();
                        scanner.nextLine();

                        try {
                            List<Kitap> kiralananKitaplar = kiralamaIslemleri.musteriIdIleKiralananKitaplar(musteriId);
                            if (kiralananKitaplar.isEmpty()) {
                                System.out.println("Bu ID'ye sahip müşteri henüz kitap kiralamamış.");
                            } else {
                                System.out.println("Müşteri IDsi: " + musteriId +" tarafından kiralanan kitaplar:");
                                kiralananKitaplar.forEach(kitap -> System.out.println("Kitap: " + kitap.getAd()));
                            }
                        } catch (MusteriBulunamadiException e) {
                            System.err.println(e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("Müşteri Adını Giriniz: ");
                        String ad = scanner.nextLine();
                        System.out.println("Müşteri Soyadını Giriniz: ");
                        String soyad = scanner.nextLine();

                        try {
                            Musteri musteri = kiralamaIslemleri.musteriAra(ad, soyad, musteriler);
                            kiralamaIslemleri.musteriKiralananKitaplariGoster(musteri);
                        } catch (MusteriBulunamadiException e) {
                            System.err.println(e.getMessage());
                        }
                        break;

                    case 5:
                        exit = true;
                        System.out.println("Programdan çıkılıyor...");
                        break;

                    default:
                        System.err.println("Geçersiz seçim. Lütfen tekrar deneyin.");
                }
            }
        }
    }
