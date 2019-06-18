
package labirentgonder;


public class Adam {
    
    
    

    public int xKoordinat;
    public int yKoordinat;
    
    
    

    private static final int gucluPush = 7;
    private static final int kutuCek = 8;
    private static final int hizliKos = 9;

    private int specialPower = 0;

    public int gucluPushLimit = 10;

    public int kutuCekLimit = 10;

    public int hizliKosLimit = 10;

    public Adam(int xKoordinat, int yKoordinat) {
        this.xKoordinat = xKoordinat;
        this.yKoordinat = yKoordinat;
    }

    public void powerMod(Labirent labirent) {
        int ZEMIN = 1;
        int DUVAR = 2;
        int ADAM = 3;
        int CIKIS = 4;
        int KUTU = 5;
    }
    
    public int specialPowers []=new int [3];
    
    public String getSpecialPowers(){
       String powers="";
        for(int i: specialPowers){
            powers +=  i==gucluPush  ? "Guclu itme " : "";
            powers +=  i==kutuCek  ? "Kutu cekme " : "";
            powers +=  i==hizliKos  ? "Hizli kosma " : "";
        }
        return powers;
    }
    
    public void addSpecialPower(int power){
        if(power==7)
            specialPowers[0]=power;
        if(power==8)
            specialPowers[1]=power;
        if(power==9)
            specialPowers[2]=power;
    }

    public int getKutuCekLimit() {
        return kutuCekLimit;
    }

    public int getGucluPushLimit() {
        return gucluPushLimit;
    }

    public int getHizliKosLimit() {
        return hizliKosLimit;
    }

    public int getSpecialPower() {
        return specialPower;
    }

    public void setSpecialPower(int specialPower) {
        this.specialPower = specialPower;
        System.out.println(specialPower);
    }

}
