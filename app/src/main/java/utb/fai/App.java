package utb.fai;

public class App {

    public static void main(String[] args) {
        if (args.length != 6) {
            System.out.println("Parameters: <smtpServerURL> <port> <from> <to> <subject> <body>");
            return;
        }
        
        try {
            EmailSender sender = new EmailSender(args[0], Integer.parseInt(args[1]));
            sender.send(args[2], args[3], args[4], args[5]);
            sender.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
