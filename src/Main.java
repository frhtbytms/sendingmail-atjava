import java.io.*;
import java.util.Scanner;


public class Main {


    public static String KullanıcıBilgileriiAlma() {
        Scanner scan = new Scanner(System.in);
        System.out.print("kullanıcı ad:");
        String name = scan.next();
        System.out.print("kullanıcı soyadı:");
        String surname = scan.next();
        System.out.print("kullancıcı maili: ");
        String mail = scan.next();
        String kullanıcı = name + "    " + surname + "    " + mail;
        return kullanıcı;

    }

    public static void dosya1(String newkullanıcı) throws IOException {
        int i;
        File f = new File("elitkullanıcılar.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        FileWriter fwriter = new FileWriter(f, true);
        BufferedWriter bwriter = new BufferedWriter(fwriter);
        FileReader freader = new FileReader(f);
        String line;
        BufferedReader breader = new BufferedReader(freader);

        while ((line = breader.readLine()) != null) {
            System.out.println(line);
        }
        bwriter.newLine();
        bwriter.append(newkullanıcı);
        bwriter.close();
        System.out.println("\n yeni kullanıcı eklendikten sonraki liste:");
        while ((line = breader.readLine()) != null) {
            System.out.println(line);
        }
        breader.close();


    }

    public static void dosya2(String newkullanıcı) throws IOException {
        int i;
        String line;
        File f2 = new File("genelkullanıcılar.txt");
        if (!f2.exists()) {
            f2.createNewFile();
        }
        FileWriter fWriter = new FileWriter(f2, true);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
        FileReader fReader = new FileReader(f2);
        BufferedReader bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            System.out.println(line);

        }
        bWriter.newLine();
        bWriter.append(newkullanıcı);
        bWriter.close();
        System.out.println("\n yeni kullanıcı eklendikten sonraki liste:");
        while ((line = bReader.readLine()) != null) {
            System.out.println(line);

        }
        bReader.close();
    }


    public static void main(String[] args) throws Exception {
        int karar;
        Scanner scan = new Scanner(System.in);
        String newkullanıcı;
        String from, sifre, mailmetin;
        karar = MailGondermeMenu.menu();
        switch (karar) {
            case 1:
                newkullanıcı = KullanıcıBilgileriiAlma();
                dosya1(newkullanıcı);
                break;
            case 2:
                newkullanıcı = KullanıcıBilgileriiAlma();
                dosya2(newkullanıcı);
                break;
            case 3:
                int karar2 = MailGondermeMenu.mailgonderme();
                switch (karar2) {
                    case 1:
                        System.out.print("gönderici mail: ");
                        from = scan.next();
                        System.out.print("gönderici sifre: ");
                        sifre = scan.next();
                        System.out.println("mail metni nedir:");
                        mailmetin = scan.next();
                        elitemail em = new elitemail(from, sifre, mailmetin);
                        elitemail.mailgonder();
                        break;
                    case 2:
                        System.out.print("gönderici mail: ");
                        from = scan.next();
                        System.out.print("gönderici sifre: ");
                        sifre = scan.next();
                        System.out.println("mail metni nedir:");
                        mailmetin = scan.next();
                        genelemail gm = new genelemail(from, sifre, mailmetin);
                        genelemail.mailgonder();
                        break;
                    case 3:
                        System.out.print("gönderici mail: ");
                        from = scan.next();
                        System.out.print("gönderici sifre: ");
                        sifre = scan.next();
                        System.out.println("mail metni nedir:");
                        mailmetin = scan.next();
                        elitemail em1 = new elitemail(from, sifre, mailmetin);
                        elitemail.mailgonder();
                        genelemail gm1 = new genelemail(from, sifre, mailmetin);
                        genelemail.mailgonder();
                        break;
                }
                break;
        }
    }
}