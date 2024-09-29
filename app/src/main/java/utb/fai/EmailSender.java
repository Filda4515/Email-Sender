package utb.fai;

import java.net.*;
import java.io.*;

public class EmailSender {
    private Socket socket;
    private InputStream input;
    private OutputStream output;
    
    /*
     * Constructor opens Socket to host/port. If the Socket throws an exception
     * during opening,
     * the exception is not handled in the constructor.
     */
    public EmailSender(String host, int port) throws UnknownHostException, IOException, InterruptedException {
        socket = new Socket(host, port);
        System.out.println("Connected to server");

        input = socket.getInputStream();
        output = socket.getOutputStream();

        byte[] buffer;
        final byte[] response = new byte[1024];
        int len;

        String message = "EHLO f_nevrala@utb.cz\r\n";
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);
        if(input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }
    }

    /*
     * Sends email from an email address to an email address with some subject and
     * text.
     * If the Socket throws an exception during sending, the exception is not
     * handled by this method.
     */
    public void send(String from, String to, String subject, String text) throws IOException, InterruptedException {
        byte[] buffer;
        final byte[] response = new byte[1024];
        int len;

        String message = "MAIL FROM:<" + from + ">\r\n";
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);
        if(input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }

        message = "RCPT TO:<" + to + ">\r\n";
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);
        if(input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }

        message = "DATA\r\n";
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);
        if(input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }

        output.write(("From: " + from + "\r\n").getBytes());
        output.write(("Subject: " + subject + "\r\n").getBytes());
        output.write(("To: " + to + "\r\n").getBytes());
        output.write(("Body: " + text + "\r\n").getBytes());
        output.write(".\r\n".getBytes());
        output.flush();

        Thread.sleep(500);
        if(input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }
    }

    /*
     * Sends QUIT and closes the socket
     */
    public void close() throws IOException, InterruptedException {
        byte[] buffer;
        final byte[] response = new byte[1024];
        int len;

        String message = "QUIT\r\n";
        buffer = message.getBytes();
        output.write(buffer, 0, buffer.length);
        output.flush();

        Thread.sleep(500);
        if(input.available() > 0) {
            len = input.read(response);
            System.out.write(response, len, len);
        }
        socket.close();
    }
}
