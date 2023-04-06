import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


class menu extends Main{//bu benim ilk ekran açıldığında gelem menu için oluşturduğum class
    //constructera gerek duymadım
    public static int menu1 (){//menu fonksiyonum
        System.out.println("*********************");
        System.out.println("ÜYE İŞLEMLERİ MENÜSÜ");
        System.out.println("1-Elit üye ekleme");
        System.out.println("2-Genel üye ekleme");
        System.out.println("3-Mail gönderme");
        System.out.println("*********************");
        System.out.print("Hangi işlemi gerçekleştirmek istersiniz? : ");
        Scanner scan =new Scanner(System.in);
        int karar=scan.nextInt();//kullanıcıdan kararını aldım
        if(karar>=1 &&karar<=3)//burda kullanıcınn girdiği değeri kontrol ettimki main fonksiyonda gerek kalmasın
            System.out.println("komutunuz alındı efendim.");//doğru bir sayı girdi
        else{
            System.out.println("\nmenudeki işlemlerin başındaki sayıları kullan(1,2,3) !!!");//geçersiz değer için uyarı mesajı
            menu1();//geçersiz bir değer girğiği için recursive func yaptım func içine tekara attım
        }
        return karar;//kararı fonksiyon dışına attım
    }
}


class MailGondermeMenu extends menu{//kalıtım yaptım
    //kullanıcı ana menude 3 e basınca gelicek ekran için bu classı oluşturdum
    public MailGondermeMenu(){//constructer gerek yok ama göstermek istedim
        //daha sonrasında belki bir şeyler eklerim

    }
    public static int mailgonderme() {//bu fonksiyonuda bu classdaki işlemleri tel bir satırda gerçekleştirebilmek için yaptım
        System.out.println("*********************");
        System.out.println("ÜYE İŞLEMLERİ MENÜSÜ");
        System.out.println("1-Elit üyelere mail");
        System.out.println("2-Genel üyelere mail");
        System.out.println("3-tüm üyelere mail");
        System.out.println("*********************");
        System.out.print("Hangi işlemi gerçekleştirmek istersiniz? : ");
        Scanner scan = new Scanner(System.in);
        int karar = scan.nextInt();//kararını aldım
        if (karar >= 1 && karar <= 3)//burda kullanıcınn girdiği değeri kontrol ettimki main fonksiyonda gerek kalmasın
            return karar;
        else {
            System.out.println("\nmenudeki işlemlerin başındaki sayıları kullan(1,2,3) !!!");
            mailgonderme();
        }
        return karar;
    }
}

class genelemail {
    public static String gonderenmail;//gönderen kişiden aldığım mail adres tutmak için yaptım
    public static String gonderenpassword;//gönderen kişiden aldığım mail password tutmak için yaptım
    public static String metinmail;//gönderen kişiden mail göndermek istediği metni tutmak için yaptım
    public genelemail(String gonderenmail, String gonderenpassword,String metinmail) {//constructer
        this.gonderenmail = gonderenmail;
        this.gonderenpassword = gonderenpassword;
        this.metinmail = metinmail;
    }

    public static void mailgonder() throws Exception {//mail gönderme işlemi daha düzenli bir kod olsun diye bu fonksiyonu yaptım
        String dosyaAd = "genelkullanıcılar.txt";//dosya adını sürekli yazmamak için
        File f = new File(dosyaAd);//dosyayı açtım
        if (!f.exists()) {//dosya yoksa oluştucak
            f.createNewFile();
        }
        String[] epostaDizi;//aşağıdaki list içindeki epostaları bunun içine koycam
        List<String> epostaListesi = new ArrayList<>();//dosya okumakta zorlandığım için bu yöntemi googledan öğrendim ve epostaları bu list içine atmak için bu listi oluşturdum
        Pattern desen = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");//bu özelliklere sahip satırın bölünmüş kelimelerini epostalistesi içiine atmak için desen patternini oluşturdum
        Matcher matcher;
        try {
            FileReader fileReader = new FileReader(dosyaAd);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String satir;
            while ((satir = bufferedReader.readLine()) != null) {//while döngüsü ile epostaları okuyup list içine attım
                matcher = desen.matcher(satir);
                while (matcher.find()) {
                    String eposta = matcher.group();
                    if (!epostaListesi.contains(eposta)) {
                        epostaListesi.add(eposta);
                    }
                }
            }
            fileReader.close();
            epostaDizi = new String[epostaListesi.size()];//bu ve br alt satırda list içindekileri dizi içine attım
            epostaDizi = epostaListesi.toArray(epostaDizi);
            System.out.println("gönderilcek epostalar:");
            for (String eposta : epostaDizi) {//gönderilcek e postaları ekrana yazar

                System.out.println(eposta);
            }
            Properties props = new Properties();;//bundan sonrası zaten mail gönderbilmek için googldan öğrendiklerim
            props.put("mail.smtp.host", "smtp-mail.outlook.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(gonderenmail, gonderenpassword);// mail göndercek olan kişiden aldığım bilgiler
                        }
                    });

            for (String email : epostaListesi) {
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(gonderenmail, "NoReply"));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                msg.setSubject("mesajjjjj konusu");
                msg.setText(metinmail);// mail göndercek olan kişiden aldığım metin

                Transport.send(msg);//mesaj gönderme komutu
                System.out.println(email + " adresine e-posta gönderildi.");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }



    }


}
class elitemail  {//bu classda üstteki genele mail classı ile aynı mantık sadece gönderilcek kişiler farklı
    public static String gonderenmail;
    public static String gonderenpassword;
    public static String metinmail;

    public elitemail(String gonderenmail, String gonderenpassword,String metinmail){//constructer
        this.gonderenmail=gonderenmail;
        this.gonderenpassword=gonderenpassword;
        this.metinmail=metinmail;
    }

    public static void mailgonder() throws Exception {
        File f = new File("elitkullanıcılar.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        String dosyaAdi = "elitkullanıcılar.txt";
        String[] epostaDizi;
        List<String> epostaListesi = new ArrayList<>();
        Pattern desen = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");
        Matcher matcher;
        try {
            FileReader fileReader = new FileReader(dosyaAdi);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String satir;
            while ((satir = bufferedReader.readLine()) != null) {
                matcher = desen.matcher(satir);
                while (matcher.find()) {
                    String eposta = matcher.group();
                    if (!epostaListesi.contains(eposta)) {
                        epostaListesi.add(eposta);
                    }
                }
            }
            fileReader.close();
            epostaDizi = new String[epostaListesi.size()];
            epostaDizi = epostaListesi.toArray(epostaDizi);
            System.out.println("gönderilcek epostalar:");
            for (String eposta : epostaDizi) {

                System.out.println(eposta);
            }
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp-mail.outlook.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(gonderenmail, gonderenpassword);
                        }
                    });

            for (String email : epostaListesi) {
                Message msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress(gonderenmail, "NoReply"));
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                msg.setSubject("Welcome To Java Mail API");
                msg.setText(metinmail);
                Transport.send(msg);
                System.out.println(email + " adresine e-posta gönderildi.");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }







    }


}








public class Main {


    public static String KullanıcıBilgileriiAlma() {//bu fonksiyonu kullanıcı kaydı yaparken kullanmak için oluşturdum
        Scanner scan = new Scanner(System.in);
        System.out.print("kullanıcı ad:");
        String name = scan.next();
        System.out.print("kullanıcı soyadı:");
        String surname = scan.next();
        System.out.print("kullancıcı maili: ");
        String mail = scan.next();
        String kullanıcı = name + "    " + surname + "    " + mail;//tabla birlikte name ,surname ve maili bunun içine atıp retrun yaptım
        return kullanıcı;

    }

    public static void dosyaislemelri(String newkullanıcı,int hangidosya) throws IOException {//dosya işlemler için fonksiyon
        String nereyeeklencek;//aşağıda ilk açılan menude verilen karar mesela bir ise buradan bir gelcek ve elit dosyaya kullanıcı eklencek iki gelirse genel dosyaya eklencek
        if(hangidosya==1)//kontrolu bu if else ile yaptım
            nereyeeklencek="elitkullanıcılar.txt";
        else
            nereyeeklencek="genelkullanıcılar.txt";
        File f = new File(nereyeeklencek);
        if (!f.exists()) {
            f.createNewFile();
        }
        FileWriter fwriter = new FileWriter(f, true);
        BufferedWriter bwriter = new BufferedWriter(fwriter);
        FileReader freader = new FileReader(f);
        String line;
        BufferedReader breader = new BufferedReader(freader);
        bwriter.newLine();
        bwriter.append(newkullanıcı);
        bwriter.close();
        System.out.println("\n yeni kullanıcı eklendikten sonraki liste:");
        while ((line = breader.readLine()) != null) {//yeni kullanıcı eklendikten sonra dosyanınn son hali
            System.out.println(line);
        }
        System.out.println("\n\n");
        breader.close();
    }
    public static void MailgonderenBilgileri(int kimeleregonderilcek) throws Exception {
        String from, sifre, mailmetin;//mail göndermek isteyen kişiden gerekn bilgileri aldıktan sonra tutmak için
        Scanner scan = new Scanner(System.in);
        System.out.print("gönderici mail: ");
        from = scan.next();
        System.out.print("gönderici sifre: ");
        sifre = scan.next();
        System.out.print("mail metni nedir:");
        mailmetin = scan.next();
        if(kimeleregonderilcek==1) {//elit kullanıcılara mail gondermek isteyen biri için 1 komutu gonderimis
            elitemail em = new elitemail(from, sifre, mailmetin);
            elitemail.mailgonder();
        } else if (kimeleregonderilcek==2) {//genel kullanıcılara mail gondermek isteyen biri için 2 komutu gonderimis
            genelemail gm = new genelemail(from, sifre, mailmetin);
            genelemail.mailgonder();
        }
        else{//hepsine kullanıcılara mail gondermek isteyen biri için
            elitemail em1 = new elitemail(from, sifre, mailmetin);
            elitemail.mailgonder();
            genelemail gm1 = new genelemail(from, sifre, mailmetin);
            genelemail.mailgonder();
        }

    }
    public static void main(String[] args) throws Exception {
        int i=0;
        while (i==0) {//menuler kullanıcı islemni yaptıktan sonra sürekli ekrana gelsi diye while ile sonsuz döngüye soktum
            int karar = MailGondermeMenu.menu1();//mailgondermenu kalıtım aracılığı ile ana menunu tutulduğu sunuf olan menu sınıfından istediği metoddu aldı ve kullandı o metodda adı menu ana sayfayı gösteriyo
            String newkullanıcı;//kullanıcı bilileri alma fonksiyonundan gelen bilgileri buraya attım

            switch (karar) {
                case 1:
                    newkullanıcı = KullanıcıBilgileriiAlma();//bu fonsiyon sayesinde kullanıcı bilgilerini aldım
                    dosyaislemelri(newkullanıcı, 1);//aldığım bilgiyi elit kullanıcılar textine yazdım
                    break;
                case 2:
                    newkullanıcı = KullanıcıBilgileriiAlma();//bu fonsiyon sayesinde kullanıcı bilgilerini aldım
                    dosyaislemelri(newkullanıcı, 2);//aldığım bilgiyi genel  kullanıcılar textine yazdım
                    break;
                case 3:
                    int karar2 = MailGondermeMenu.mailgonderme();//mail göndermek isteyen kişinin önüne gelmesi gereken ekranı ve kararını belirten fonk cağırdım
                    switch (karar2) {
                        case 1:
                            MailgonderenBilgileri(1);//elite kullanıcılara mail gondermek isteyen kişi için gerekli fonksiyonu çağırdım ve içine bir yazmamım nedenini gidip fonk bak anlicaksın
                            break;
                        case 2:
                            MailgonderenBilgileri(2);//genel kullanıcılara mail gondermek isteyen kişi için gerekli fonksiyonu çağırdım ve içine iki yazmamım nedenini gidip fonk bak anlicaksın
                            break;
                        case 3:
                            MailgonderenBilgileri(3);//ne gonderildiğinini onemi yok 1 ve 2 dısında bütün sayılar aynı işelemi yapcak
                            break;
                    }
                    break;
            }
        }
    }
}
