import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class genelemail {
    public static String gonderenmail;
    public static String gonderenpassword;
    public static String metinmail;
    public genelemail(String gonderenmail, String gonderenpassword,String metinmail){//constructer
        this.gonderenmail=gonderenmail;
        this.gonderenpassword=gonderenpassword;
        this.metinmail=metinmail;
    }

    public static void mailgonder() throws Exception {
        File f = new File("genelkullanıcılar.txt");
        if (!f.exists()) {
            f.createNewFile();
        }
        String dosyaAdi = "genelkullanıcılar.txt";
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
            Properties props = new Properties();;
            props.put("mail.smtp.host", "smtp-mail.outlook.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
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

