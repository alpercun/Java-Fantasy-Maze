
package labirentgonder;

import java.util.Scanner;

public class Oyun {

    private Labirent labirent;

    public static final int YUKARI = 1;
    public static final int ASAGI = 2;
    public static final int SAGA = 3;
    public static final int SOLA = 4;
    public static final int HATALI = 0;
    public static final int SPECIAL = 5;
    private static final int gucluPush = 7;
    private static final int kutuCek = 8;
    private static final int hizliKos = 9;

    private int skor;

    public Oyun() {
        labirent = null;
        skor = 0;
    }

    public Oyun(int[][] matris) {
        skor = 0;
        yukle(matris);
    }

    public void yukle(int[][] matris) {

        labirent = new Labirent(matris);
    }

    public void baslat() {
        while (true) {
            sahneyiYazdir();
            int yon = kullanicidanYonBilgisiAl();
            adamiHareketEttir(yon);
            if (bittiMi()) {
                break;
            }
        }

    }

    private boolean bittiMi() {
        boolean oyunBitti = false;

        if (labirent.getAdamX() == labirent.getHedefX() && labirent.getAdamY() == labirent.getHedefY()) {
            if (Labirent.toplananOdul == Labirent.odulSayisi) {
                System.out.println("**************");
                System.out.println("Tebrikler! Çıkışı buldunuz");
                System.out.println("**************");

                oyunBitti = true;
            } else {
                System.out.println("**************");
                System.out.println("Lütfen kalan ödülleri de bulunuz");
                System.out.println("**************");
            }
        }
        return oyunBitti;

    }

    private void adamiHareketEttir(int yon) {

        switch (yon) {

            case YUKARI:
                if (labirent.adaminYeriniGuncelle(labirent.getAdamX(), labirent.getAdamY() - 1, YUKARI)) {
                    skor++;
                }
                break;

            case SOLA:
                if (labirent.adaminYeriniGuncelle(labirent.getAdamX() - 1, labirent.getAdamY(), SOLA)) {
                    skor++;
                }
                break;

            case ASAGI:
                if (labirent.adaminYeriniGuncelle(labirent.getAdamX(), labirent.getAdamY() + 1, ASAGI)) {
                    skor++;
                }
                break;

            case SAGA:
                if (labirent.adaminYeriniGuncelle(labirent.getAdamX() + 1, labirent.getAdamY(), SAGA)) {
                    skor++;
                }
                break;
            case SPECIAL:
                //labirent.adam.powerMod(labirent);
                if (labirent.adam.getSpecialPower() == kutuCek) {
                    kullanicidanYonBilgisiAl();
                    adamiHareketEttir(yon);
                }
                break;
        }
    }

    private int kullanicidanYonBilgisiAl() {

        Scanner consol = new Scanner(System.in);

        System.out.print("Yön Tuşları (w = yukarı, s = aşağı, a = sol, d = sağ, p = power), seçiniz: ");
        String secimStr = consol.next();

        char secim = secimStr.charAt(0);
        int yon = HATALI;

        switch (secim) {
            case 'w':
            case 'W':
                yon = YUKARI;
                break;

            case 'a':
            case 'A':
                yon = SOLA;
                break;

            case 's':
            case 'S':
                yon = ASAGI;
                break;

            case 'd':
            case 'D':
                yon = SAGA;
                break;

            case 'p':
            case 'P':

                if (labirent.adam.getSpecialPowers() == "") {

                    System.out.println("Karakterinizin kullanacagi guc mevcut degil.");
                } else {

                    System.out.print("Hangi Gucu Kullanmak Istersiniz" + "Sahip oldugunuz gucler: " + labirent.adam.getSpecialPowers()+"Sahip oldugunuz gucler aktiflestirmek icin\n" +
                            "P,S,L tuslarindan birine basiniz");
                    String secimpbr = consol.next();
                    if (secimpbr.toLowerCase().charAt(0) == 'p' && labirent.adam.getSpecialPowers().contains("Guclu itme")) {
                        labirent.adam.setSpecialPower(gucluPush);
                    } else if (secimpbr.toLowerCase().charAt(0) == 's' && labirent.adam.getSpecialPowers().contains("Hizli kosma")) {
                        labirent.adam.setSpecialPower(hizliKos);
                    } else if (secimpbr.toLowerCase().charAt(0) == 'l' && labirent.adam.getSpecialPowers().contains("Kutu cekme")) {
                        labirent.adam.setSpecialPower(kutuCek);
                    } else {
                        System.out.println("yanlis girdi");
                    }

                }
                break;
            case 'q':
            case 'Q':
                labirent.adam.setSpecialPower(0);
                break;
        }

        return yon;

    }

    public void sahneyiYazdir() {
        System.out.println("-------------------------\n");

        System.out.println(labirent);

        System.out.println("\n Toplam Adım: " + skor);
        System.out.println("\n toplanan oduller: " + Labirent.toplananOdul);
        System.out.println("\n-------------------------\n");

        int d;

        d = labirent.adam.getSpecialPower();

        switch (d) {
            case kutuCek:
                System.out.println("kutu çekme özelliğinizi " + labirent.adam.getKutuCekLimit() + " adım daha kullanabilirsiniz");

            case hizliKos:
                System.out.println("hızlı koşma özelliğinizi " + labirent.adam.getHizliKosLimit() + " adım daha kullanabilirsiniz");

            case gucluPush:
                System.out.println("güçlü itme özelliğinizi " + labirent.adam.getGucluPushLimit() + " adım daha kullanabilirsiniz");
        }

        System.out.println("\n-------------------------\n");

    }

}
