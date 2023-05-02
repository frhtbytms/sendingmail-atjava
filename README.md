# benim-depom

The first class, menu, is a subclass of Main. It has a static method called menu1 that prints a menu for user operations and takes user input to select an option. If the user enters an invalid input, the method calls itself recursively until the user enters a valid input. The method returns the user's choice as an integer.

The second class, MailGondermeMenu, is a subclass of menu. It has a static method called mailgonderme that prints a menu for sending emails to different types of users and takes user input to select an option. If the user enters an invalid input, the method calls itself recursively until the user enters a valid input. The method returns the user's choice as an integer.

The third class, genelemail, has a static method called mailgonder that sends an email to all users or specific users depending on the option selected by the user in the MailGondermeMenu class. The class has three static variables gonderenmail, gonderenpassword, and metinmail that hold the sender's email address, password, and email message, respectively. The mailgonder method reads email addresses from a file, creates an email message using the information in the static variables, and sends the email using the JavaMail API. If the file containing email addresses does not exist, the method creates the file. The email addresses are stored in an array and a list for easy access. The method uses regular expressions to match email addresses in the file.

The "mail" class includes methods for sending an email to a recipient, while the "elitemail" class is similar to the "mail" class but with the difference that it sends emails only to elite users.

At the beginning of the "mail" class, there are three variables declared as public static variables. These variables are "gonderenmail," "gonderenpassword," and "metinmail." These variables are used to store the email address of the sender, the password of the sender's email account, and the message to be sent, respectively.

Then there is a constructor for the "elitemail" class. The constructor takes three parameters: "gonderenmail," "gonderenpassword," and "metinmail." The constructor is used to initialize the three variables of the "elitemail" class.

The next method is the "mailgonder" method. This method sends an email to the recipients using the information provided in the constructor. The method first reads a file called "elitkullanıcılar.txt" that contains a list of email addresses of elite users.

The method then uses a regular expression to find email addresses in each line of the file and stores them in an ArrayList. After this, the method sets the properties for the email message and creates a session with the sender's email account.

Finally, the method sends an email to each email address in the ArrayList using the session created earlier. It sets the "From," "To," "Subject," and "Text" fields of the email message and then sends it using the "Transport.send(msg)" method.

In the "Main" class, there are three methods. The first method is "KullanıcıBilgileriiAlma," which takes user input for the name, surname, and email address and returns a concatenated string of these values.

The second method is "dosyaislemelri," which takes the concatenated string from the first method and a file number as input. It creates a file with the given file number and writes the concatenated string to the file.

The method then reads the contents of the file and prints them to the console. This method is used to create and update files containing user information.

The third method is "MailgonderenBilgileri," which takes a file number as input and prompts the user to enter their email address, password, and message to be sent. It then stores these values in variables and passes them to the "elitemail" constructor to create a new "elitemail" object.

Finally, the "mailgonder" method of the "elitemail" class is called to send the email message to the recipients. This method sends the email only to elite users if the file number is 1, otherwise, it sends the email to all users.
