
package labirentgonder;



public class Labirent {

    public Adam adam;

    private int adamX, adamY;
    private int hedefX, hedefY;

    public static final int ZEMIN = 1;
    public static final int DUVAR = 2;
    public static final int ADAM = 3;
     public static final int CIKIS = 4;
    public static final int ODUL = 5;
    public static final int KUTU = 6;
    public static final int gucluPush = 7;
    public static final int kutuCek = 8;
    public static final int hizliKos = 9;

    public static int odulSayisi = 7;
    public static int toplananOdul = 0;

    //public static boolean gucluPush = false; 
    //public static boolean kutuCek = false;
    //public static boolean hizliKos = true;
    public int matris[][];

    public Labirent() {
        adam = new Adam(0, 0);
        adamX = adamY = hedefX = hedefY = 0;
        matris = null;
    }

    public Labirent(int[][] grid) {
        adam = new Adam(0, 0);
        adamX = adamY = hedefX = hedefY = 0;
        yukle(grid);

    }

    public void yukle(int[][] grid) {

        matris = new int[grid.length][grid[0].length];

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                int hucre = grid[y][x];
                if (hucre == ADAM) {
                    adamX = x;
                    adamY = y;
                }
                if (hucre == CIKIS) {
                    hedefX = x;
                    hedefY = y;
                }

                matris[y][x] = grid[y][x];
            }
        }

    }

    public int getAdamX() {
        return adamX;
    }

    public int getAdamY() {
        return adamY;
    }

    public int getHedefX() {
        return hedefX;
    }

    public int getHedefY() {
        return hedefY;
    }

    public String toString() {

        String s = "";

        for (int y = 0; y < matris.length; y++) {
            for (int x = 0; x < matris[0].length; x++) {
                int hucre = matris[y][x];

                switch (hucre) {
                    case ZEMIN:
                        s += "-";
                        break;
                    case DUVAR:
                        s += "#";
                        break;
                    case ADAM:
                        s += "ö";
                        break;
                    case CIKIS:
                        s += (char) 169;
                        break;
                    case ODUL:
                        s += "$";
                        break;
                    case KUTU:
                        s += "%";
                        break;
                    case gucluPush:
                        s += "P";
                        break;
                    case hizliKos:
                        s += "S";
                        break;
                    case kutuCek:
                        s += "L";
                        break;
                }

            }
            s += "\n";
        }

        return s;

    }

    public boolean adaminYeriniGuncelle(int x, int y, int yon) {
        //yukarıdan argüman olarak gelen x y karakterin gitmek istediği
        //yani yönüne göre currentX +1 , currentY +1
        boolean basarili = false;

        int yonX = 0; //karakterin ilerlemek istediği x in bir fazlası
        int yonY = 0; //karakterin ilerlemek istediği y in bir fazlası
        //yani bu değişkenler sayesinde, eğer kutu iteceksek, 
        //itilecek konumda duvar vs olmadıgını kontrol edebiliyoruz
        int yonXX = x;
        int yonYY = y;

        int cekilenYonX = 0;
        int cekilenYonY = 0;

        switch (yon) {

            case 1:
                yonX = x;
                yonY = y - 1;
                yonYY = y - 2;
                break;

            case 2:
                yonX = x;
                yonY = y + 1;
                yonYY = y + 2;
                break;

            case 3:
                yonX = x + 1;
                yonXX = x + 2;
                yonY = y;
                break;

            case 4:
                yonX = x - 1;
                yonXX = x - 2;
                yonY = y;
                cekilenYonY = y;
                cekilenYonX = x + 2;
                break;

        }

        /*if(hizliKos){
                    switch(yon){
                        case 1:
                            while(matris[y][x] == ZEMIN){
                                adamiIlerlet(x, y);
                                y= y-1;
                                basarili = true;
                            }break;
                        case 2:
                            while(matris[y][x] == ZEMIN){
                                adamiIlerlet(x, y);
                                y= y+1;
                                basarili = true;
                            }break;
                        case 3:
                            while(matris[y][x] == ZEMIN){
                                adamiIlerlet(x, y);
                                x = x +1;
                                basarili = true;
                            }break;
                        case 4:
                            while(matris[y][x] == ZEMIN){
                                adamiIlerlet(x, y);
                                x = x -1;
                                basarili = true;
                            }break;
                    }
                    hizliKos = false;
                    return basarili;
                }*/
        if (adam.getSpecialPower() == 9 && adam.hizliKosLimit > 0) {
            switch (yon) {
                case 1:
                    while (matris[y][x] == ZEMIN) {
                        adamiIlerlet(x, y);
                        y = y - 1;
                        basarili = true;
                    }
                    break;
                case 2:
                    while (matris[y][x] == ZEMIN) {
                        adamiIlerlet(x, y);
                        y = y + 1;
                        basarili = true;
                    }
                    break;
                case 3:
                    while (matris[y][x] == ZEMIN) {
                        adamiIlerlet(x, y);
                        x = x + 1;
                        basarili = true;
                    }
                    break;
                case 4:
                    while (matris[y][x] == ZEMIN) {
                        adamiIlerlet(x, y);
                        x = x - 1;
                        basarili = true;
                    }
                    break;
            }
        }

        if (matris[y][x] == ZEMIN && adam.getSpecialPower() !=8) {
            adamiIlerlet(x, y);
            basarili = true;
        }
        if (matris[y][x] == ODUL) {
            toplananOdul++;

            adamiIlerlet(x, y);

            basarili = true;
        }
        if (matris[y][x] == KUTU && matris[yonY][yonX] == ZEMIN) {
            kutununYeriniGuncelle(yonX, yonY);

            adamiIlerlet(x, y);

            basarili = true;
        }
        if (matris[y][x] == KUTU && matris[yonY][yonX] == KUTU && matris[yonYY][yonXX] == ZEMIN) {
            if (adam.getSpecialPower() == gucluPush && adam.getGucluPushLimit() > 0) {
                kutununYeriniGuncelle(yonX, yonY);
                kutununYeriniGuncelle(yonXX, yonYY);

                adamiIlerlet(x, y);

                basarili = true;
            }
        }
        if (matris[y][x] == gucluPush) {
            adam.addSpecialPower(gucluPush);
            adamiIlerlet(x, y);

            basarili = true;
        }
        if (matris[y][x] == kutuCek) {
            adam.addSpecialPower(kutuCek);
            adamiIlerlet(x, y);

            basarili = true;
        }
        if (matris[y][x] == hizliKos) {
            adam.addSpecialPower(hizliKos);
            adamiIlerlet(x, y);

            basarili = true;
        }
        if (adam.getSpecialPower() == 8 && adam.kutuCekLimit > 0) {
            if (matris[y][x] == ZEMIN && matris[cekilenYonY][cekilenYonX] == KUTU) {
                

                kutununYeriniGuncelle(adamX, adamY);

                matris[y][x] = ADAM;

                adamX = x;
                adamY = y;

                matris[cekilenYonY][cekilenYonX] = ZEMIN;

                basarili = true;

            }else if(matris[y][x] == ZEMIN && matris[cekilenYonY][cekilenYonX] != KUTU){
                adamiIlerlet(x, y);
                basarili =true;
            }
        }
        if (adam.getSpecialPower() == 7 && adam.gucluPushLimit > 0) {
            if (matris[y][x] == KUTU && matris[yonY][yonX] == KUTU && matris[yonYY][yonXX] == ZEMIN) {

                kutununYeriniGuncelle(yonX, yonY);
                kutununYeriniGuncelle(yonXX, yonYY);

                adamiIlerlet(x, y);

                basarili = true;
            }
        }

        /*if(matris[y][x] == ZEMIN && matris[cekilenYonY][cekilenYonX] == KUTU && kutuCek == true){
                    
                        kutununYeriniGuncelle(adamX, adamY);
                        
                        matris[y][x] = ADAM;
                        
                        adamX = x;
                        adamY = y;
                        
                        matris[cekilenYonY][cekilenYonX] = ZEMIN; 
                        
                        basarili = true;
                    
                }*/
              if(adam.getSpecialPower() == 7){
                adam.gucluPushLimit--;
            }
            if(adam.getSpecialPower() == 8){
                adam.kutuCekLimit--;
            }
            if(adam.getSpecialPower() == 9){
                adam.hizliKosLimit--;
            }
            if(adam.gucluPushLimit == 0){
                adam.setSpecialPower(0);
            }
            if(adam.kutuCekLimit == 0){
                adam.setSpecialPower(0);
            }
            if(adam.hizliKosLimit == 0){
                adam.setSpecialPower(0);
            }
        return basarili;
    }

    public void kutununYeriniGuncelle(int x, int y) {
        matris[y][x] = KUTU;
    }

    public void adamiIlerlet(int x, int y) {
        matris[adamY][adamX] = ZEMIN; // şu an ki bulunduğu yeri boşluk olarak belirle
        matris[y][x] = ADAM; // labirentte adamın yerini değiştir
        adamX = x;
        adamY = y;
    }

}
