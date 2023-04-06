import java.util.Scanner;

public class MailGondermeMenu extends menu{
    public static int mailgonderme() {
        System.out.println("*********************");
        System.out.println("ÜYE İŞLEMLERİ MENÜSÜ");
        System.out.println("1-Elit üyelere mail");
        System.out.println("2-Genel üyelere mail");
        System.out.println("3-tüm üyelere mail");
        System.out.println("*********************");
        System.out.print("Hangi işlemi gerçekleştirmek istersiniz? : ");
        Scanner scan = new Scanner(System.in);
        int karar = scan.nextInt();
        if (karar >= 1 && karar <= 3)
            return karar;
        else {
            System.out.println("menudeki işlemlerin başındaki sayıları kullan(1,2,3) !!!");
            menu.menu();
        }
        return karar;
    }
}

