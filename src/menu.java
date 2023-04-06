import java.util.Scanner;

public class menu extends Main{

    public static int menu (){
        gir:
        System.out.println("*********************");
        System.out.println("ÜYE İŞLEMLERİ MENÜSÜ");
        System.out.println("1-Elit üye ekleme");
        System.out.println("2-Genel üye ekleme");
        System.out.println("3-Mail gönderme");
        System.out.println("*********************");
        System.out.print("Hangi işlemi gerçekleştirmek istersiniz? : ");
        Scanner scan =new Scanner(System.in);
        int karar=scan.nextInt();
        if(karar>=1 &&karar<=3)
            return karar;
        else{
            System.out.println("menudeki işlemlerin başındaki sayıları kullan(1,2,3) !!!");
            menu();
        }
        return karar;
    }

}
